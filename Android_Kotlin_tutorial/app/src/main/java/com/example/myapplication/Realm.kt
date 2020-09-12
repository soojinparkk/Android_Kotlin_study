package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.realm.Realm
import io.realm.RealmConfiguration
import kotlinx.android.synthetic.main.activity_realm.*

class Realm : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_realm)

        Realm.init(this)
        val config: RealmConfiguration = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .build()
            // -> 메소드 체이닝 (보통 builder 패턴에 많이 사용)

        Realm.setDefaultConfiguration(config)
        val realm = Realm.getDefaultInstance()

        save_Realm_Btn.setOnClickListener {
            // 하나의 작업 단위 -> Transaction
            realm.executeTransaction {
                with(it.createObject(School::class.java)) {
                    this.name = "University"
                    this.location = "Seoul"
                }
            }
        }

        load_Realm_Btn.setOnClickListener {
            realm.executeTransaction {
                val data = it.where(School::class.java).findFirst()
                Log.d("dataa", "data: $data")
            }
        }

        delete_Realm_Btn.setOnClickListener {
            realm.executeTransaction {
                // 전체 삭제
                it.where(School::class.java).findAll().deleteAllFromRealm()

                // 첫번째 줄만 삭제
                // it.where(School::class.java).findFirst().deleteFromRealm()
            }
        }
    }
}
