package com.example.sqlite

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

// 네 번째 인자: 유지보수 단계에서 DB 구조를 변경했을 경우 인자값(= 버전) 변경
// onUpgrade()와 관련지어 사용
class DBHelper(context: Context): SQLiteOpenHelper(context, "Test.db", null, 1) {

    // 해당 이름을 가진 DB가 있는지 없는지 확인 후
    // 있으면 onCreate() 호출하지 않고 DB open만 함
    // 없으면 onCreate() 호출
    // 실제로 사용될 table 생성
    override fun onCreate(db: SQLiteDatabase?) {
        Log.d("msg", "on Create")

        var sql = "create table TestTable (" +
                "idx integer primary key autoincrement, " +
                "textData text not null, " +
                "intData integer not null, " +
                "floatData real not null, " +
                "dateData date not null" +
                ")"
        db?.execSQL(sql)
    }

    // table 구조 변경
    // 첫 번째 인자: 예전 버전 값
    // 두 번째 인자: 최신 버전 값
    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.d("msg", "old: $oldVersion new: $newVersion")
    }
}