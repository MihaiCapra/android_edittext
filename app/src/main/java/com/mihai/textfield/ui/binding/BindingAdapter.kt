package com.mihai.textfield.ui.binding

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import com.mihai.textfield.R

@BindingAdapter("android:hint")
fun TextInputLayout.hint(value: String?) {
    hint = value?.let {
        if (it.isNotEmpty() && it != "0") it else context.getString(R.string.text)
    } ?: context.getString(R.string.text)
}