package com.dongxi.demo.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.dongxi.rxdemo.R
import kotlinx.android.synthetic.main.activity_kotlin_learning.*
import kotlinx.android.synthetic.main.item_thumb_up.*

class KotlinTestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_test)

        var str: String? = "Hello Kotlin"

        helloTv.text = str
        helloTv.textSize = 20f

        var list = arrayListOf<String>("aa","bb","cc")

        helloTv.setOnClickListener{
            for (i in list.indices){
                Toast.makeText(this,list[i], Toast.LENGTH_SHORT).show()
            }
        }
        printSum(4,5)
        Toast.makeText(this,sum(2,3)+"",Toast.LENGTH_SHORT).show()
    }

    /**
     * 方法
     */
    fun sum(a: Int, b: Int): Int {   // Int 参数，返回值 Int
        return a + b
    }

    /**
     * 无返回值
     */
    fun printSum(a: Int, b: Int): Unit {
        print(a + b)
    }
}
