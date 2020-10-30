package com.example.mapsactivity

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallback: MyLocationCallBack

    private val REQUEST_ACCESS_FINE_LOACTION = 1000

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

                Log.d("Maps", "위도: $latitude, 경도: $longitude")
            }
        }
    }

    override fun onResume() {
        super.onResume()

        // 권한 요청
        permissionCheck(cancel = {
            // 위치 정보가 필요한 이유 다이얼로그로 표시
            showPermissionInfoDialog()
        }, ok = {
            // 현재 위치를 주기적으로 요청 (권한이 필요한 부분)
            addLocationListener()
        })
    }

    // 권한 요청 코드를 작성했지만 별도의 메서드로 해당 코드 블록을 분리할 경우
    // 안드로이드에서 에러로 판단
    // (권한이 필요한 코드의 주변에 직접 작성한 권한 요청 코드만 인식)
    // -> 권한 요청을 무시하는 주석 추가함
    @SuppressLint("MissingPermission")
    private fun addLocationListener() {
        fusedLocationProviderClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }

    override fun onPause() {
        super.onPause()

        // 위치 요청 취소
        removeLocationListener()
    }

    private fun removeLocationListener() {
        // 현재 위치 요청 삭제
        fusedLocationProviderClient.removeLocationUpdates(locationCallback)
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


    private fun permissionCheck(cancel: () -> Unit, ok: () -> Unit) {
        // 위치 권한이 있는지 검사
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // 위치 권한이 없을 경우
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {
                // 이전에 위치 권한을 한 번 거부한 적이 있는 경우에 실행할 함수
                cancel()
            } else {
                // 권한 요청
                ActivityCompat.requestPermissions(this,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_ACCESS_FINE_LOACTION)
            }
        } else {
            // 위치 권한이 있을 경우
            ok()
        }
    }

    private fun showPermissionInfoDialog() {
        alert ("현재 위치 정보를 얻으려면 위치 권한이 필요합니다", "권한이 필요한 이유"){
            yesButton {
                // 권한 요청
                ActivityCompat.requestPermissions(this@MapsActivity,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    REQUEST_ACCESS_FINE_LOACTION)
            }
            noButton {  }
        }.show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_ACCESS_FINE_LOACTION -> {
                if (grantResults.isNotEmpty()
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // 권한 허용
                    addLocationListener()
                } else {
                    // 권한 거부
                    toast("권한 거부 됨")
                }
                return
            }
        }
    }
}