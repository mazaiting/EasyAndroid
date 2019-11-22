package com.mazaiting.widget.fragment

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment

import java.util.Calendar
import java.util.Objects

/***
 * @author mazaiting
 * @date 2019-06-14
 * @decription 日期选择对话框
 */
class DatePickerDialogFragment : DialogFragment(), DatePickerDialog.OnDateSetListener {
    /**
     * 获取日历
     */
    private val calendar = Calendar.getInstance()
    /**
     * 年
     */
    private var mYear = calendar.get(Calendar.YEAR)
    /**
     * 月
     */
    private var mMonth = calendar.get(Calendar.MONTH)
    /**
     * 日
     */
    private var mDayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
    /**
     * 日期选择监听
     */
    private var mDatePickerListener: DatePickerListener? = null

    /**
     * 获取时间
     * @return 时间字符串
     */
    val date: String
        get() = this.mYear.toString() + "-" + this.mMonth + "-" + this.mDayOfMonth

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        // 返回日期控件
        return DatePickerDialog(
            this.activity!!, this,
            mYear, mMonth, mDayOfMonth
        )
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, dayOfMonth: Int) {
        this.mYear = year
        this.mMonth = month
        this.mDayOfMonth = dayOfMonth

        if (null != this.mDatePickerListener) {
            this.mDatePickerListener!!.onDataSet(date)
        }
    }

    /**
     * 设置日期选择监听
     * @param datePickerListener 日期选择监听
     */
    fun setDatePickerListener(datePickerListener: DatePickerListener) {
        this.mDatePickerListener = datePickerListener
    }

    /**
     * 日期选择监听
     */
    interface DatePickerListener {
        /**
         * 数据设置
         * @param date 日期
         */
        fun onDataSet(date: String)
    }
}
