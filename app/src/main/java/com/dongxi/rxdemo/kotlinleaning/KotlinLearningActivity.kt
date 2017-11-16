package com.dongxi.rxdemo.kotlinleaning

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.dongxi.rxdemo.R
import kotlinx.android.synthetic.main.activity_kotlin_learning.*

class KotlinLearningActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_learning)

        var str: String? = "Hello Kotlin"

        helloTv.text = str
        helloTv.textSize = 20f

        var list = arrayListOf<String>("aa","bb","cc")

        helloTv.setOnClickListener{
            for (i in list.indices){
                Toast.makeText(this,list[i],Toast.LENGTH_SHORT).show()
            }
        }
    }
}
