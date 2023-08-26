 package com.example.mycasino11.view.activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.mycasino11.R
import com.example.mycasino11.constant.*

 class MainActivity : AppCompatActivity() {

     lateinit var navController: NavController

     override fun onCreate(savedInstanceState: Bundle?) {

         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_main)

         MAIN = this
         navController = Navigation.findNavController(this,R.id.id_nav_host)

         //устновка полноэкранного режима
         window.setFlags(
             WindowManager.LayoutParams.FLAG_FULLSCREEN,
             WindowManager.LayoutParams.FLAG_FULLSCREEN
         )

     }

     //функция установки рекорда EASY
     fun setRecordEasy(record:Int){
         val pref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
         pref.edit()
             .putInt(RECORD_EASY,record)
             .apply()
     }

     //функция установки рекорда MIDDLE
     fun setRecordMiddle(record:Int){
         val pref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
         pref.edit()
             .putInt(RECORD_MIDDLE,record)
             .apply()
     }

     //функция установки рекорда HARD
     fun setRecordHard(record:Int){
         val pref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
         pref.edit()
             .putInt(RECORD_HARD,record)
             .apply()
     }

     //функция получения рекорда EASY
     fun getRecordEasy():Int{
         return getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE).getInt(RECORD_EASY, 0)
     }

     //функция получения рекорда MIDDLE
     fun getRecordMiddle():Int{
         return getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE).getInt(RECORD_MIDDLE, 0)
     }

     //функция получения рекорда Hard
     fun getRecordHard():Int{
         return getSharedPreferences(APP_PREFERENCES, MODE_PRIVATE).getInt(RECORD_HARD, 0)
     }

}