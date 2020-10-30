package com.example.mapsactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: MyLocationCallBack

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
                .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        locationInit()
    }

    // 위치 정보를 얻기 위한 각종 초기화
    private fun locationInit() {
        fusedLocationProviderClient = FusedLocationProviderClient(this)

        locationCallback = MyLocationCallBack()

        locationRequest = LocationRequest()

        // GPS 우선
        // priority = 정확도
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

        // 업데이트 인터벌
        // 위치 정보가 없을 때는 업데이트 안 함
        // 상황에 따라 짧아질 수 있음, 정확하지 않음
        // 다른 앱에서 짧은 인터벌로 위치 정보를 요청하면 짧아질 수 있음
        locationRequest.interval = 10000

        // 정확함, 이것보다 짧은 업데이트는 하지 않음
        locationRequest.fastestInterval = 5000

        // -> 이 요청은 GPS를 사용하여 가장 정확한 위치를 요구하면서
        // 10초마다 위치 정보를 갱신함
        // 그 사이에 다른 앱에서 위치를 갱신했다면 5초마다 확인하여
        // 그 값을 활용하여 베터리를 절약함
    }

    inner class MyLocationCallBack: LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult?) {
            super.onLocationResult(locationResult)

            val location = locationResult?.lastLocation

            location?.run {
                // location 객체가 null이 아닐 때
                // 14 level로 확대하고 현재 위치로 카메라 이동
                val latLng = LatLng(latitude, longitude)
                mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17f))
            }
        }
    }

    override fun onResume() {
        super.onResume()

        addLocationListener()
    }

    private fun addLocationListener() {
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}