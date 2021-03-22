package com.ayosef.colorbuster.ui.main

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.JsonObject

data class phrase(val colorId:Int, val hexString: String, val rgb: JsonObject, val name:String)
class vocabulary: ArrayList<phrase>()
class WordList(jsonString: String){
    var terms = ArrayList<phrase>()
    init {
        val gson = Gson()
        terms = gson.fromJson(jsonString, vocabulary::class.java)
        Log.d("Terms", terms.toString())
    }
}
class MainViewModel(app: Application) : AndroidViewModel(app) {
    private lateinit var wordList: WordList
    private val _vocabulary = MutableLiveData<List<phrase>>()
    var vocabulary: LiveData<List<phrase>> =_vocabulary

    init {
        val jsonString = app.assets.open("data.json").bufferedReader().use { it.readText() }
        wordList = WordList(jsonString)
        _vocabulary.value=wordList.terms
    }

}