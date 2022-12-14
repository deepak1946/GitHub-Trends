package com.gojekgithub.trending.utils

import android.content.res.Resources
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.CheckResult
import androidx.annotation.ColorInt
import androidx.annotation.DrawableRes
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher


/**
 * Custom Matcher used to match background colorRes [ColorInt] and color hexCode value
 * Espresso LifeCycle
 * Created by : Deepak Singh
 */
class DrawableMatcher private constructor(private val expectedId: Int) :
    TypeSafeMatcher<View>(
        View::class.java
    ) {

    override fun matchesSafely(target: View): Boolean {
        if (target !is ImageView) {
            return false
        }
        val imageView: ImageView = target
        if (expectedId < 0) {
            return imageView.drawable == null
        }
        val resources: Resources = target.context.resources
        val expectedDrawable: Drawable = resources.getDrawable(expectedId)
            ?: return false
        val bitmap = getBitmap(imageView.drawable)
        val otherBitmap = getBitmap(expectedDrawable)
        return bitmap.sameAs(otherBitmap)
    }

    private fun getBitmap(drawable: Drawable): Bitmap {
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }

    override fun describeTo(description: Description) {
        description.appendText("with drawable resId: $expectedId")
    }

    companion object {
        @CheckResult
        fun withDrawable(@DrawableRes drawableRes: Int): Matcher<View> {
            return DrawableMatcher(drawableRes)
        }

    }
}