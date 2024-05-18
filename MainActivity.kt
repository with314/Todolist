package com.happyworld.sampletodo

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.widget.ListView
import androidx.appcompat.app.AlertDialog
//import androidx.core.view.ViewCompat
//import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //View の取得
        val btnAdd: Button = findViewById(R.id.btnAdd)
        val lv:ListView = findViewById(R.id.lv)

        //1)アダプター に入れてListnViewにセット
        val adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            mutableListOf()//
        )
        lv.adapter = adapter

        //2)　＋ボタンでアラートダイアログ
        btnAdd.setOnClickListener{

            val et =EditText(this)

            AlertDialog.Builder(this)
                .setTitle("項目の追加")
                .setMessage("何をする？")
                .setView(et)
                .setPositiveButton("追加" ){ _, _ ->
                    val myTodo =et.text.toString()
                    adapter.add(myTodo)
                }
                .setNegativeButton("キャンセル",null)
                .show()
        }

        lv.setOnItemClickListener{_, _, i, _ ->
            AlertDialog.Builder(this)
                .setTitle("削除しますか")
                .setPositiveButton("Yes"){_, _ ->
                    adapter.remove(adapter.getItem(i))
                }
                .setNegativeButton("No",null)
                .show()
        }
    }
}