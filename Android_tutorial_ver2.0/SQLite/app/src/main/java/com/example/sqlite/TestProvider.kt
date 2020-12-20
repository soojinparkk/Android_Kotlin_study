package com.example.sqlite

import android.content.ContentProvider
import android.content.ContentValues
import android.database.Cursor
import android.net.Uri

class TestProvider : ContentProvider() {

    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?): Int {
        TODO("Implement this to handle requests to delete one or more rows")
        val helper = DBHelper(context!!)
        var db = helper.writableDatabase

        // 삭제한 갯수 return
        return db.delete("TestTable", selection, selectionArgs)
    }

    override fun getType(uri: Uri): String? {
        TODO(
            "Implement this to handle requests for the MIME type of the data" +
                    "at the given URI"
        )
    }

    override fun insert(uri: Uri, values: ContentValues?): Uri? {
        TODO("Implement this to handle requests to insert a new row.")
        val helper = DBHelper(context!!)
        var db = helper.writableDatabase

        db.insert("TestTable", null, values)

        return uri
    }

    override fun onCreate(): Boolean {
        TODO("Implement this to initialize your content provider on startup.")
    }

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ): Cursor? {
        TODO("Implement this to handle query requests from clients.")
        val helper = DBHelper(context!!)
        var db = helper.writableDatabase

        return db.query("TestTable", projection, selection, selectionArgs, null, null, sortOrder)
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ): Int {
        TODO("Implement this to handle requests to update one or more rows.")
        val helper = DBHelper(context!!)
        var db = helper.writableDatabase

        // 업데이트한 갯수 return
        return db.update("TestTable", values, selection, selectionArgs)
    }
}
