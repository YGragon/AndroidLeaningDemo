package com.dongxi.rxdemo.pinsenction

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.dongxi.rxdemo.R
import com.dongxi.rxdemo.utils.LogUtil
import kotlinx.android.synthetic.main.activity_section_kotlin.*

class SectionKotlinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_section_kotlin)

        val list = TestData().initData()
        LogUtil.e("list=="+list.size)

        empty_recyclerView.layoutManager = LinearLayoutManager(this)


        empty_recyclerView.adapter = SectionAdapter(list)

        empty_recyclerView.addItemDecoration(SectionDecoration(TestData().getCityName(list), this, object : SectionDecoration.DecorationCallback {
            //返回标记id (即每一项对应的标志性的字符串)
            override fun getGroupId(position: Int): String {
                return if (TestData().getCityName(list).get(position) != null) {
                    TestData().getCityName(list).get(position)
                } else "-1"
            }

            //获取同组中的第一个内容
            override fun getGroupFirstLine(position: Int): String {
                return if (TestData().getCityName(list).get(position) != null) {
                    TestData().getCityName(list).get(position)
                } else ""
            }
        }))
    }


}
