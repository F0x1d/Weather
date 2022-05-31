package com.f0x1d.weather.ui.activity.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

abstract class BaseActivity<D : ViewBinding> : AppCompatActivity() {
    protected lateinit var binding: D

    abstract fun inflateBinding(): D?

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inflateBinding().also {
            if (it != null) {
                setContentView(it.root)
                binding = it
            }
        }
    }
}