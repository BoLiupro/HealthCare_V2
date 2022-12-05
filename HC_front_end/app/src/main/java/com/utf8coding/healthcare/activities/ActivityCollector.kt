package com.utf8coding.healthcare.activities

import android.app.Activity
import android.util.Log

object ActivityCollector {
    private val activities = ArrayList<Activity>()
    fun addActivity(activity: Activity) {
        activities.add(activity)
        Log.i("activity collector:", "entering: ${activity.localClassName}")
    }
    fun removeActivity(activity: Activity) {
        activities.remove(activity)
    }
    fun finishAll() {
        for (activity in activities) {
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
        Log.i("activity collector:", "finish all called.")
        activities.clear()
    }
}
