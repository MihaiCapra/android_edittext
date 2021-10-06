package com.mihai.textfield.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel() {
    val wordCount = MutableLiveData<String>()
    val text = MutableLiveData("")
}