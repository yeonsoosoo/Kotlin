package com.pys.ex_mediastore

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.pys.ex_mediastore.databinding.ActivityMainBinding
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    val REQUEST_PERMISSION_CAMERA_STORAGE = 201
    private val REQUEST_CAMERA_IMG_UPLOAD = 1002 //카메라 촬영 이미지 업로드
    private val REQUEST_CODE_ACTION_IMAGE_CROP = 102


    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mFile: File
    private lateinit var cameraImageUri: Uri
    private lateinit var intentCamera: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)

        mBinding.btnCamera.setOnClickListener {
            takePhoto()
        }
    }

    private fun takePhoto() {
        intentCamera = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val currentDate = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())

        val fileName = "camera_$currentDate"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)

        var file: File?
        try {
            file = File.createTempFile(
                fileName,
                ".jpg",
                storageDir
            )
            mFile = file
        } catch (e: IOException) {
            e.printStackTrace()
            return
        }

        // File 객체 URI 얻기
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            cameraImageUri = FileProvider.getUriForFile(this, BuildConfig.APPLICATION_ID + ".provider", file)
        } else {
            cameraImageUri = Uri.fromFile(file)
        }
        intentCamera.putExtra(MediaStore.EXTRA_OUTPUT, cameraImageUri)

        // 카메라 intent 포함
        if (CommonUtil.checkPermission(this, REQUEST_PERMISSION_CAMERA_STORAGE)) {
            startActivityForResult(intentCamera, REQUEST_CAMERA_IMG_UPLOAD)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQUEST_PERMISSION_CAMERA_STORAGE) {
            if(grantResults.size > 1
                /*&& grantResults[0] == PackageManager.PERMISSION_GRANTED
                && grantResults[1] == PackageManager.PERMISSION_GRANTED*/) {

            } else {
                CommonUtil.showMoveSettingDialog(this, R.string.message_camera_external_read_permission)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == REQUEST_CAMERA_IMG_UPLOAD) {
            try {
                IntentUtil.startActionCropImage(this, cameraImageUri, mFile.path, false, REQUEST_CODE_ACTION_IMAGE_CROP)

            } catch (e : Exception) {
                e.printStackTrace()
            }
        } else if(requestCode == REQUEST_CODE_ACTION_IMAGE_CROP) {
            Log.d("이미지 로드 : ", data?.data?.path.toString())
            Glide.with(this).load(data?.data).into(mBinding.imageView)
        }
    }
}