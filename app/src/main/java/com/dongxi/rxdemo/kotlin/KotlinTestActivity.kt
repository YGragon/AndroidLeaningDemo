package com.dongxi.rxdemo.kotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.dongxi.rxdemo.R
import kotlinx.android.synthetic.main.activity_kotlin_learning.*

class KotlinTestActivity : AppCompatActivity() {

    var TAG = "KotlinTestActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kotlin_test)

        var string: String? = "Hello Kotlin"

        helloTv.text = string
        helloTv.textSize = 20f

        var list = arrayListOf<String>("aa","bb","cc")

        helloTv.setOnClickListener{
            for (i in list.indices){
                Toast.makeText(this,list[i], Toast.LENGTH_SHORT).show()
            }
        }
        printSum(4,5)
        var sum = sum(2, 3)
        Log.e(TAG,printSum(4,5).toString()+"==有返回值求和")
        Log.e(TAG,sum.toString()+"==有返回值求和")
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
