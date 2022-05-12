package com.example.r2devprosproject

import android.R.attr
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.r2devprosproject.databinding.ActivityMainBinding
import timber.log.Timber


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var addImage: ImageView
    private lateinit var imageUri: Uri

    override fun onCreate(savedInstanceState: Bundle?) {
        Timber.d("MainActivity_TAG: onCreate: ")
        super.onCreate(savedInstanceState)

        setBinding()
        bindViews()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Timber.d("MainActivity_TAG: onActivityResult: requestCode: $requestCode")
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_CANCELED) {
            // action cancelled
        }
        if (resultCode == RESULT_OK) {
            val image = data?.data
            binding.ivAvatar.setImageBitmap(MediaStore.Images.Media.getBitmap(this.contentResolver, image))
        }
    }

    private fun setBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun bindViews() {
        binding.btnUpload.setOnClickListener {
            Timber.d("MainActivity_TAG: bindViews: Upload Image")

            val gallery = Intent()
            gallery.type = "image/*"
            gallery.action = Intent.ACTION_GET_CONTENT

            startActivityForResult(Intent.createChooser(gallery, "${getString(R.string.upload_button)}"), PICK_IMAGE)
        }
        binding.btnAddress.setOnClickListener {
            Timber.d("MainActivity_TAG: Go to Address: ")

//            val image = binding.ivAvatar.drawToBitmap()
//            val intent = Intent(this@MainActivity, UserAddressLayoutActivity::class.java)
//            intent.putExtra(CONTACT_NAME, R)
//            startActivity(intent)
        }
    }

    companion object {
        const val CONTACT_NAME = "contact-name"
        const val CONTACT_PHOTO = "contact-bitmap"
        const val PICK_IMAGE = 1
    }
}