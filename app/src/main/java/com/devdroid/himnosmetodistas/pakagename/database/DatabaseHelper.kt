package com.devdroid.himnosmetodistas.pakagename.database

import android.content.ContentValues
import android.content.Context
import com.devdroid.himnosmetodistas.pakagename.models.Hymn
import com.devdroid.himnosmetodistas.pakagename.models.HymnTitle
import com.readystatesoftware.sqliteasset.SQLiteAssetHelper

class DatabaseHelper(context: Context): SQLiteAssetHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {


    fun selectHymnById(id: Int): Hymn{
        val queryCursor = readableDatabase.query("tHymn", null, "id = $id", null, null, null, null)
        queryCursor.moveToFirst()
        val hymn = Hymn(queryCursor.getInt(0), queryCursor.getInt(1), queryCursor.getString(2), queryCursor.getString(3), queryCursor.getString(4))
        queryCursor.close()
        close()
        return hymn
    }

    fun selectAllTitles(): ArrayList<HymnTitle>{
        val result = ArrayList<HymnTitle>()
        val queryCursor = readableDatabase.query("tHymn", null, null, null, null, null, null)
        while (queryCursor.moveToNext()){
            result.add(HymnTitle(queryCursor.getInt(0), queryCursor.getInt(1), queryCursor.getString(2)))
        }
        queryCursor.close()
        close()
        return result
    }

    fun selectAllFavorites(): ArrayList<HymnTitle>{
        val result = ArrayList<HymnTitle>()
        val queryCursor = readableDatabase.query("tHymn", null, "favorite = true", null, null, null, null)
        queryCursor.moveToFirst()
        while(queryCursor.moveToNext()){
            result.add(HymnTitle(queryCursor.getInt(0), queryCursor.getInt(1), queryCursor.getString(2)))
        }
        queryCursor.close()
        close()
        return result
    }

    private fun isHymnFavorite(id: Int): Boolean{
        val queryCursor = readableDatabase.query("tHymn", null, "id = $id", null, null, null, null)
        queryCursor.moveToFirst()
        val isTrue = queryCursor.getString(4) == "true"
        queryCursor.close()
        close()
        return isTrue
    }

    fun toggleIsHymnFavorite(id: Int): Boolean {
        val editHymn = ContentValues()
        if (isHymnFavorite(id)){
            editHymn.put("favorite", "false")
        }else{
            editHymn.put("favorite", "true")
        }
        val res = writableDatabase.update("tHymn", editHymn, "id = $id", null)
        close()
        return res == -1
    }

    companion object{
        private const val DATABASE_NAME = "database.db"
        private const val DATABASE_VERSION = 1
    }
}