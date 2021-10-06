package com.mihai.textfield.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import com.mihai.textfield.R
import com.mihai.textfield.constants.Params
import com.mihai.textfield.core.BaseActivity
import com.mihai.textfield.core.countWords
import com.mihai.textfield.databinding.ActivityMainBinding
import com.mihai.textfield.viewmodel.MainActivityViewModel

class MainActivity : BaseActivity() {
    private val mainActivityViewModel: MainActivityViewModel by viewModels()
    private lateinit var mainActivityBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        mainActivityBinding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_main
        )
        mainActivityBinding.lifecycleOwner = this
        mainActivityBinding.mainViewModel = mainActivityViewModel

        savedInstanceState?.let {
            mainActivityViewModel.text.value = it.getString(Params.SAVED_STATE_TEXT)
            mainActivityViewModel.wordCount.value = it.getString(Params.SAVED_STATE_WORD_COUNT)
        }
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)

        mainActivityBinding.apply {
            textInput.addTextChangedListener {
                it?.let {
                    mainActivityViewModel.apply {
                        text.value = it.toString()
                        wordCount.value = it.toString().countWords()
                    }
                }
            }

            insertBtn.setOnClickListener {
                textInput.append(getString(R.string.default_paragraph))
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.apply {
            putString(Params.SAVED_STATE_TEXT, mainActivityViewModel.text.value)
            putString(Params.SAVED_STATE_WORD_COUNT, mainActivityViewModel.wordCount.value)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainActivityViewModel.apply {
            text.removeObservers(this@MainActivity)
            wordCount.removeObservers(this@MainActivity)
        }
    }
}