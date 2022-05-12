package com.example.r2devprosproject

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.MediaStore
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.r2devprosproject.databinding.UserAddressLayoutBinding
import timber.log.Timber


class UserAddressLayoutActivity : AppCompatActivity() {
    private lateinit var binding: UserAddressLayoutBinding
    lateinit var name: String
    lateinit var avatar: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("UserAddressLayoutActivity_TAG: onCreate: ")
        super.onCreate(savedInstanceState)
        name = intent.getStringExtra(CONTACT_NAME) ?: ""
        avatar = intent.getStringExtra(CONTACT_PHOTO)?.toUri() ?: Uri.EMPTY

        binding = UserAddressLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadInfo()
    }

    private fun loadInfo() {
        Timber.d("UserAddressLayoutActivity_TAG: loadInfo: ")

        binding.tvName.text = name

        if (avatar.toString().isNotEmpty()) {
            binding.ivAvatar.setImageBitmap(MediaStore.Images.Media.getBitmap(this.contentResolver, avatar))
        }
    }
}