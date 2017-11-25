package com.dongxi.rxdemo.pinsenction

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.support.v7.widget.RecyclerView
import android.text.TextPaint
import android.text.TextUtils
import android.view.View
import com.dongxi.rxdemo.utils.DensityUtil
import java.util.*

/**
 * Created by macmini002 on 17/11/25.
 */
class SectionDecoration(private val dataList: ArrayList<String>
                        , context: Context, private val callback: DecorationCallback) : RecyclerView.ItemDecoration() {
    private val textPaint: TextPaint
    private val paint: Paint
    private val topGap: Int
    private val alignBottom: Int
    private val fontMetrics: Paint.FontMetrics


    init {
        val res = context.resources
        //设置悬浮栏的画笔---paint
        paint = Paint()
        paint.color = Color.parseColor("#EDEDEE")

        //设置悬浮栏中文本的画笔
        textPaint = TextPaint()
        textPaint.isAntiAlias = true
        textPaint.textSize = DensityUtil.dp2px(context, 18F).toFloat()
        textPaint.color = Color.BLACK
        textPaint.textAlign = Paint.Align.LEFT
        fontMetrics = Paint.FontMetrics()
        //决定悬浮栏的高度等
        topGap = res.getDimensionPixelSize(44)
        //决定文本的显示位置等
        alignBottom = res.getDimensionPixelSize(6)
    }

    /**
     * 为悬浮栏预留空间
     * 只有第一个Item或者新的省份才为悬浮栏预留空间
     * @param outRect
     * @param view
     * @param parent
     * @param state
     */
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State?) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)

        val groupId = callback.getGroupId(position)
        if (groupId == "-1") {
            return
        }
        //只有是同一组的第一个才显示悬浮栏
        if (position == 0 || isFirstInGroup(position)) {
            //第一个item预留空间
            outRect.top = topGap
            if (dataList[position] == "") {
                outRect.top = 0
            }
        } else {
            outRect.top = 0
        }
    }

    /**
     * 在绘制 Item 之前调用
     * @param c
     * @param parent
     * @param state
     */
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State?) {
        super.onDraw(c, parent, state)
        val left = parent.paddingLeft
        val right = parent.width - parent.paddingRight
        // 获取当前RecyclerView的Item数量
        val childCount = parent.childCount

        for (i in 0 until childCount) {
            val view = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(view)
            val groupId = callback.getGroupId(position)

            // 错误的标志符
            if (groupId == "-1") {
                return
            }

            val textLine = callback.getGroupFirstLine(position) // 获取到标题内容


            if (textLine == "") {
                return
            } else {
                if (position == 0 || isFirstInGroup(position)) {
                    val top = (view.top - topGap).toFloat()
                    val bottom = view.top.toFloat()
                    //绘制悬浮栏
                    c.drawRect(left.toFloat(), top - topGap, right.toFloat(), bottom, paint)
                    //绘制文本
                    c.drawText(textLine, left.toFloat(), bottom, textPaint)
                }
            }
        }
    }

    /**
     * 在绘制 Item 之后调用
     * @param c
     * @param parent
     * @param state
     */
    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State?) {
        super.onDrawOver(c, parent, state)
        val itemCount = state!!.itemCount
        val childCount = parent.childCount
        val left = parent.left + parent.paddingLeft
        val right = parent.width - parent.paddingRight

        var currentGroupName = "" //标记上一个item对应的Group
        var nextGroupName = "-1"   //当前item对应的Group
        for (i in 0 until childCount) {
            val view = parent.getChildAt(i)
            val position = parent.getChildAdapterPosition(view)

            currentGroupName = nextGroupName
            nextGroupName = callback.getGroupId(position)
            if (nextGroupName == "-1" || nextGroupName == currentGroupName) continue

            val textLine = callback.getGroupFirstLine(position)
            if (TextUtils.isEmpty(textLine)) continue

            val viewBottom = view.bottom
            var top = Math.max(topGap, view.top).toFloat()  //top 决定当前顶部第一个悬浮Group的位置
            //下一个和当前不一样移动当前
            if (position + 1 < itemCount) {
                val nextGroupId = callback.getGroupId(position + 1)
                //组内最后一个view进入了header
                if (nextGroupId != nextGroupName && viewBottom < top) {
                    top = viewBottom.toFloat()
                }
            }
            //textY - topGap决定了悬浮栏绘制的高度和位置
            c.drawRect(left.toFloat(), top - topGap, right.toFloat(), top, paint)

            //文字竖直居中显示
            val fm = textPaint.fontMetrics
            val baseLine = top - (topGap - (fm.bottom - fm.top)) / 2 - fm.bottom
            c.drawText(textLine, (left + 2 * alignBottom).toFloat(), baseLine, textPaint)
        }
    }


    /**
     * 判断是不是组中的第一个位置
     * 根据前一个组名，判断当前是否为新的组
     * @param position
     * @return
     */
    private fun isFirstInGroup(position: Int): Boolean {
        if (position == 0) {
            return true
        } else {
            // 因为是根据 字符串内容的相同与否 来判断是不是同一组的，所以此处的标记id 要是String类型
            // 如果只是做联系人列表，悬浮框里显示的只是一个字母，则标记id直接用 int 类型就行了
            val preGroupName = callback.getGroupId(position - 1)
            val currentGroupName = callback.getGroupId(position)
            //判断前一个字符串 与 当前字符串 是否相同

            return if (preGroupName == currentGroupName) {
                false
            } else {
                true
            }
        }
    }

    //定义一个接口方便外界的调用
    interface DecorationCallback {
        fun getGroupId(position: Int): String

        fun getGroupFirstLine(position: Int): String
    }

}
