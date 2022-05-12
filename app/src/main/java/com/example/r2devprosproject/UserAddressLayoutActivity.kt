package com.example.r2devprosproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.r2devprosproject.databinding.ActivityMainBinding
import timber.log.Timber
import javax.net.ssl.SSLSessionBindingEvent

class UserAddressLayoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("UserAddressLayoutActivity_TAG: onCreate: ")
        super.onCreate(savedInstanceState)

        setBinding()
        bindViews()
    }

    override fun onResume() {
        Timber.d("UserAddressLayoutActivity_TAG: onResume: ")
        super.onResume()

        //binding.ivAvatar.setImageDrawable()
    }

    private fun setBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun bindViews()
    {

    }
}