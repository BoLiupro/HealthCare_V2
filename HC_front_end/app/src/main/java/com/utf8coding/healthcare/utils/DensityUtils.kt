package com.utf8coding.healthcare.utils

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.WindowManager


object DensityUtils  {
    fun dp2px(context: Context, dpVal: Float): Float {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dpVal, context.resources.displayMetrics
        )
    }

    fun sp2px(context: Context, spVal: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            spVal, context.resources.displayMetrics
        ).toInt()
    }

    fun px2dp(context: Context, pxVal: Int): Float {
        val scale = context.resources.displayMetrics.density
        return pxVal / scale
    }

    fun px2sp(context: Context, pxVal: Float): Float {
        return pxVal / context.resources.displayMetrics.scaledDensity
    }

    fun dps2pxs(context:Context, array: ArrayList<Float>): ArrayList<Float>{
        val tempArray = ArrayList<Float>()
        for (f in array){
            tempArray.add(dp2px(context, f))
        }
        return tempArray
    }

    fun horizontalDp(context: Context): Float{
        val manager = context.applicationContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val point = Point()
        manager.defaultDisplay.getSize(point)
        return px2dp(context, point.x)
    }

    fun verticalDp(context: Context): Float{
        val manager = context.applicationContext.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val point = Point()
        manager.defaultDisplay.getSize(point)
        return px2dp(context, point.y)
    }
}