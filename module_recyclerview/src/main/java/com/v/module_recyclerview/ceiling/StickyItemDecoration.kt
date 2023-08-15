package com.v.module_recyclerview.ceiling

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.TypedValue
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.v.module_recyclerview.R

/**
 * ClassName: StickyItemDecoration
 * Description:
 *
 * @author zhuming
 * @package_name com.v.module_recyclerview.ceiling
 * @date 2023/8/15 23:07
 */
class StickyItemDecoration(val context: Context, val mList: MutableList<Bean> = mutableListOf()) :
    RecyclerView.ItemDecoration() {

    private var paint: Paint? = null
    private var textPaint: Paint = Paint()
    private var dividerHeight: Float = dp2Px(50).toFloat()

    init {
        paint = Paint()
        paint?.color = context.resources.getColor(R.color.colorAccent)
        paint?.style = Paint.Style.FILL

        textPaint.color = context.resources.getColor(R.color.C10)
        textPaint.style = Paint.Style.FILL
        textPaint.textSize = sp2Px(14).toFloat()
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)
        if (isGroupFirst(position)) {
            outRect.top = dividerHeight.toInt()
        } else {
            outRect.top = 0
        }
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(canvas, parent, state)
        val childCount = parent.childCount
        for (i in 0 until childCount) {
            val childView = parent.getChildAt(i)
            val childAdapterPos = parent.getChildAdapterPosition(childView)
            val groupName = getGroupName(childAdapterPos)
            val left = childView.paddingLeft
            val right = childView.paddingRight
            paint?.let {
                if (isGroupFirst(childAdapterPos)) {
                    val top = childView.top - dividerHeight
                    val bottom = childView.top.toFloat()
                    canvas.drawRect(
                        left.toFloat(),
                        top,
                        (childView.width - right).toFloat(),
                        bottom,
                        it
                    )
                    groupName ?: return
                    val baseLine =
                        (top + bottom) / 2f - (textPaint.descent() + textPaint.ascent()) / 2f
                    canvas.drawText(groupName, left + dp2Px(10).toFloat(), baseLine, textPaint)
                }
            }
        }
    }

    override fun onDrawOver(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(canvas, parent, state)
        val firstVisiblePos =
            (parent.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
        // 这里必须获取itemView 不能通过parent.getChildAt的方式，原因是getChildAt的方式会出现top准确的情况
        val view = parent.findViewHolderForAdapterPosition(firstVisiblePos)?.itemView
        view ?: return
        val top = parent.paddingTop
        val left = parent.paddingLeft
        val right = parent.width - view.paddingRight
        paint?.let {
            if (isGroupFirst(firstVisiblePos)) {
                val bottom = Math.min(dividerHeight, view.bottom.toFloat())
                canvas.drawRect(
                    left.toFloat(),
                    top + view.top - dividerHeight,
                    right.toFloat(),
                    top + bottom,
                    it
                )
                val baseLine = ((top + bottom) - (textPaint.ascent() + textPaint.descent())) / 2f
                canvas.drawText(
                    getGroupName(firstVisiblePos)!!,
                    left + dp2Px(10).toFloat(),
                    baseLine,
                    textPaint
                )
            } else {
                canvas.drawRect(
                    left.toFloat(),
                    top.toFloat(),
                    right.toFloat(),
                    top.toFloat() + dividerHeight,
                    it
                )
                val baseLine =
                    ((top + top + dividerHeight) - (textPaint.ascent() + textPaint.descent())) / 2f
                canvas.drawText(
                    getGroupName(firstVisiblePos)!!,
                    left + dp2Px(10).toFloat(),
                    baseLine,
                    textPaint
                )
            }
        }
    }

    private fun getGroupName(position: Int): String {
        return mList[position].groupName
    }

    private fun isGroupFirst(position: Int): Boolean {
        return if (position == 0) {
            true
        } else {
            val lastGroupName = mList[position - 1].groupName
            val currentGroupName = mList[position].groupName
            lastGroupName != currentGroupName
        }
    }

    private fun dp2Px(dpValue: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dpValue.toFloat(),
            context.resources.displayMetrics
        ).toInt()
    }

    private fun sp2Px(spValue: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            spValue.toFloat(),
            context.resources.displayMetrics
        ).toInt()
    }
}