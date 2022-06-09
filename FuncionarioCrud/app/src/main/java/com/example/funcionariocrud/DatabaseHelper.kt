package com.example.funcionariocrud

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, 1) {
    companion object {
        const val DATABASE_NAME = "FuncionarioCrud.db"
        const val TABLE_NAME = "funcionario"
        const val COL_1 = "ID"
        const val COL_2 = "NOME"
        const val COL_3 = "CARGO"
        const val COL_4 = "DATA_ADMISSAO"
        const val COL_5 = "ENDERECO"
    }

    override fun onCreate(database: SQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE $TABLE_NAME (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "NOME TEXT," +
                    "CARGO TEXT," +
                    "DATA_ADMISSAO TEXT," +
                    "ENDERECO TEXT" +
                    ")"
        )
    }

    override fun onUpgrade(database: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        database.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(database)
    }

    fun getAll(): Cursor {
        return writableDatabase.rawQuery(
            "SELECT * FROM $TABLE_NAME",
            null
        )
    }

    fun insert(
        nome: String,
        cargo: String,
        dataAdmissao: String,
        endereco: String
    ) {
        val contentValues = ContentValues()

        contentValues.let {
            it.put(COL_2, nome)
            it.put(COL_3, cargo)
            it.put(COL_4, dataAdmissao)
            it.put(COL_5, endereco)
        }

        writableDatabase.insert(TABLE_NAME, null, contentValues)
    }

    fun update(
        id: String,
        nome: String,
        cargo: String,
        dataAdmissao: String,
        endereco: String
    ): Boolean {
        val contentValues = ContentValues()

        contentValues.let {
            it.put(COL_1, id)
            it.put(COL_2, nome)
            it.put(COL_3, cargo)
            it.put(COL_4, dataAdmissao)
            it.put(COL_5, endereco)
        }

        writableDatabase.update(TABLE_NAME, contentValues, "ID = ?", arrayOf(id))
        return true
    }

    fun delete(id: String): Int {
        return writableDatabase.delete(
            TABLE_NAME,
            "ID = ?",
            arrayOf(id)
        )
    }
}