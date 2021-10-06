package com.mihai.textfield.core

import android.content.Context
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

abstract class BaseActivity : AppCompatActivity() {

    /**
     * countWords - counts number of words by splitting the text using regex
     *
     * @return number of words as string
     */
    internal fun String.countWords(): String =
        this.trim().splitIgnoreEmpty("\\s+".toRegex()).size.toString()


    /**
     * splitIgnoreEmpty - splits this char sequence to a list of strings that matches
     * the regular expression and ignores empty strings
     *
     * @param regex - a regular expression
     *
     * @return a list of strings
     */
    private fun CharSequence.splitIgnoreEmpty(regex: Regex): List<String> =
        this.split(regex).filter { it.isNotEmpty() }

    /**
     * Hides soft keyboard when the view is clicked
     */
    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(this.windowToken, 0)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        currentFocus?.let {
            if (it !is EditText || it !is TextInputLayout || it !is TextInputEditText) it.hideKeyboard()
        }
        return super.dispatchTouchEvent(ev)
    }
}