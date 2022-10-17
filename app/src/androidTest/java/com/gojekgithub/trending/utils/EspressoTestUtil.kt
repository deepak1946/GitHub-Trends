package com.gojekgithub.trending.utils

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import androidx.test.rule.ActivityTestRule

/**
 * Utility methods for espresso tests.
 * Created by Deepak Singh
 */
object EspressoTestUtil {

    fun disableAnimations(
        activityTestRule: ActivityTestRule<out FragmentActivity?>
    ) {
        activityTestRule.activity?.supportFragmentManager?.registerFragmentLifecycleCallbacks(
                object : FragmentManager.FragmentLifecycleCallbacks() {
                    override fun onFragmentViewCreated(
                        fm: FragmentManager,
                        f: Fragment,
                        v: View,
                        savedInstanceState: Bundle?
                    ) {
                        traverseViews(v)
                    }
                }, true)
    }

    private fun traverseViews(view: View) {
        if (view is ViewGroup) {
            traverseViewGroup(view)
        } else {
            if (view is ProgressBar) {
                disableProgressBarAnimation(view)
            }
        }
    }

    private fun traverseViewGroup(view: ViewGroup) {
        if (view is RecyclerView) {
            disableRecyclerViewAnimations(view)
        } else {
            val count = view.childCount
            for (i in 0 until count) {
                traverseViews(view.getChildAt(i))
            }
        }
    }

    private fun disableRecyclerViewAnimations(view: RecyclerView) {
        view.itemAnimator = null
    }

    private fun disableProgressBarAnimation(progressBar: ProgressBar) {
        progressBar.indeterminateDrawable = ColorDrawable(Color.BLUE)
    }
}