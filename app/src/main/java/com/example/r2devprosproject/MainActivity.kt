package com.example.r2devprosproject

import android.content.Intent
import android.content.res.Configuration
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.os.PersistableBundle
import android.provider.MediaStore
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import com.example.r2devprosproject.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var image = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("MainActivity_TAG: onCreate: ")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bindViews()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        Timber.d("MainActivity_TAG: onConfigurationChanged: ")
        super.onConfigurationChanged(newConfig)

        Toast.makeText(this, "Image: $image", Toast.LENGTH_SHORT).show()
        loadImage()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Timber.d("MainActivity_TAG: onActivityResult: requestCode: $requestCode")
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_CANCELED) {
            Timber.d("MainActivity_TAG: No image loaded: ")
            // action cancelled
        }
        if (resultCode == RESULT_OK) {
            Timber.d("MainActivity_TAG: onActivityResult: IMAGE: $image")
            image = data?.data.toString()
            loadImage()
        }
    }

    private fun bindViews() {
        binding.btnUpload.setOnClickListener {
            Timber.d("MainActivity_TAG: bindViews: Upload Image")

            val gallery = Intent()
            gallery.type = "image/*"
            gallery.action = Intent.ACTION_GET_CONTENT

            startActivityForResult(Intent.createChooser(gallery, getString(R.string.upload_button)), PICK_IMAGE)
        }

        binding.btnAddress.setOnClickListener {
            Timber.d("MainActivity_TAG: Go to Address: ")

            val intent = Intent(this@MainActivity, UserAddressLayoutActivity::class.java).apply {
                putExtras(Bundle().apply {
                    putString(CONTACT_PHOTO, image)
                    putString(CONTACT_NAME, "${binding.etName.text} ${binding.etLastName.text}")
                })
            }
            startActivity(intent)
        }
    }

    private fun loadImage() {
        Timber.d("MainActivity_TAG: loadInfo: ")
        if (image.isNotEmpty()) {
            binding.ivAvatar.setImageBitmap(MediaStore.Images.Media.getBitmap(this.contentResolver, image.toUri()))
        }
    }
}