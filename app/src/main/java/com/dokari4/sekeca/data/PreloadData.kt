package com.dokari4.sekeca.data

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dokari4.sekeca.R
import com.dokari4.sekeca.data.local.Model
import com.dokari4.sekeca.data.local.QuestionDatabase
import com.google.gson.JsonArray
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader

class PreloadData(private val context: Context): RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(Dispatchers.IO).launch {
            fillWithStartingData(context)
        }
    }

    private suspend fun fillWithStartingData(context: Context) {
        //obtaining instance of data access object
        val dao = QuestionDatabase.getInstance(context)?.dao

        // using try catch to load the necessary data
        try {
            //creating variable that holds the loaded data
            val data = loadJSONArray(context)
            if (data != null){
                //looping through the variable as specified fields are loaded with data
                for (i in 0 until data.length()){

                    //variable to obtain the JSON object
                    val item = data.getJSONObject(i)

                    //Using the JSON object to assign data
                    val question = item.getString("question")
                    val category = item.getString("category")

                    //data loaded to the ModelEntity
                    val modelEntity = Model(
                        question = question,
                        category = category,
                        answer = null,
                        score = null
                    )

                    //using dao to insert data to the database
                    dao?.insertData(modelEntity)
                }
            }
        }
        //error when exception occurs
        catch (e: JSONException) {
            Log.d("fillWithStartingNotes:", "$e")
        }
    }

    //Load JSON Array
    private fun loadJSONArray(context: Context): JSONArray? {
        //obtain input byte
        val inputStream = context.resources.openRawResource(R.raw.question)
        //using Buffered reader to read the inputstream byte
        BufferedReader(inputStream.reader()).use {
            return JSONArray(it.readText())
        }
    }
}