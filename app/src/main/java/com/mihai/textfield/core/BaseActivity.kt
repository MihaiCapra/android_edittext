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