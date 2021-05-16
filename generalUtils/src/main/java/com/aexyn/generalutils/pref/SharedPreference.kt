package com.aexyn.generalutils.pref

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.util.*


class ReadPref {
    var TAG = "ReadPref"
    var ctx: Context
    var prefs: SharedPreferences

    constructor(ctx: Context) {
        this.ctx = ctx
        prefs = PreferenceManager.getDefaultSharedPreferences(ctx)
    }

    constructor(ctx: Context, prefName: String?) {
        this.ctx = ctx
        prefs = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    }

    fun getBooleanValue(key: String?): Boolean {
        return prefs.getBoolean(key, false)
    }

    fun getBooleanValueTrue(key: String?): Boolean {
        return prefs.getBoolean(key, true)
    }

    fun getStringValue(key: String?): String? {
        return prefs.getString(key, "")
    }

    fun getDoubleValue(key: String?): Float {
        return prefs.getFloat(key, 0f)
    }

    fun getIntValue(key: String?): Int {
        return prefs.getInt(key, -1)
    }

    fun keyExixst(key: String?): Boolean {
        return prefs.contains(key)
    }

    fun <T> getArrayList(
        key: String?,
        classType: Type?
    ): ArrayList<T> {
        var type =
            object : TypeToken<List<T>?>() {}.type
        type =
            TypeToken.getParameterized(MutableList::class.java, classType).type
        return Gson().fromJson(prefs.getString(key, ""), type)
    }

    fun <T> getObject(key: String?, classType: Type?): T {
        return Gson().fromJson(prefs.getString(key, ""), classType)
    }
}

class SavePref {
    var TAG = "SavePref"
    var ctx: Context
    var prefs: SharedPreferences

    constructor(ctx: Context) {
        this.ctx = ctx
        prefs = PreferenceManager.getDefaultSharedPreferences(ctx)
    }

    constructor(ctx: Context, prefName: String?) {
        this.ctx = ctx
        prefs = ctx.getSharedPreferences(prefName, Context.MODE_PRIVATE)
    }

    fun saveBoolean(key: String?, value: Boolean?) {
        val editor = prefs.edit()
        editor.putBoolean(key, value!!)
        editor.commit()
    }

    fun saveString(key: String?, value: String?) {
        val editor = prefs.edit()
        editor.putString(key, value)
        editor.commit()
    }

    fun saveFloat(key: String?, value: Double) {
        val editor = prefs.edit()
        editor.putFloat(key, value.toFloat())
        editor.commit()
    }

    fun <T> saveArrayList(key: String?, arrayList: ArrayList<T>?) {
        val editor = prefs.edit()
        editor.putString(key, Gson().toJson(arrayList))
        editor.commit()
    }

    fun <T> saveObject(key: String?, t: T) {
        val editor = prefs.edit()
        editor.putString(key, Gson().toJson(t))
        editor.commit()
    }

    fun saveInt(key: String?, value: Int) {
        val editor = prefs.edit()
        editor.putInt(key, value)
        editor.commit()
    }

    fun remove(key: String?) {
        val editor = prefs.edit()
        editor.remove(key)
        editor.commit()
    }

    fun removeAll() {
        val editor = prefs.edit()
        editor.clear()
        editor.commit()
    }
}