package com.tugas.logindanhome

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "user.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "user_table"
        private const val COL_ID = "ID"
        private const val COL_USERNAME = "USERNAME"
        private const val COL_PASSWORD = "PASSWORD"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($COL_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COL_USERNAME TEXT, $COL_PASSWORD TEXT)"
        db.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertData(username: String, password: String): Boolean {
        val db = writableDatabase
        val contentValues = ContentValues().apply {
            put(COL_USERNAME, username)
            put(COL_PASSWORD, password)
        }
        val result = db.insert(TABLE_NAME, null, contentValues)
        db.close()
        return result != -1L
    }

    fun checkUser(username: String, password: String): Boolean {
        val columns = arrayOf(COL_ID)
        val db = readableDatabase
        val selection = "$COL_USERNAME = ? AND $COL_PASSWORD = ?"
        val selectionArgs = arrayOf(username, password)

        val cursor = db.query(TABLE_NAME, columns, selection, selectionArgs, null, null, null)
        val cursorCount = cursor.count
        cursor.close()
        db.close()

        return cursorCount > 0
    }
}