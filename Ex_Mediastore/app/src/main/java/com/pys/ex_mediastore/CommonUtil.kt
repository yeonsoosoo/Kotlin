package com.pys.ex_mediastore

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object CommonUtil {

    fun checkPermission(activity : Activity, requestCode : Int): Boolean {

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        activity,
                        Manifest.permission.CAMERA
                    )
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                        activity,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                        activity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )
                ) {
                }
                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(
                        Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    ),
                    requestCode
                )
                return false
            }
        } else {
            //os 13
            if (ContextCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.CAMERA
                ) != PackageManager.PERMISSION_GRANTED
                || ContextCompat.checkSelfPermission(
                    activity,
                    Manifest.permission.READ_MEDIA_IMAGES
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(
                        activity,
                        Manifest.permission.CAMERA
                    )
                    || ActivityCompat.shouldShowRequestPermissionRationale(
                        activity,
                        Manifest.permission.READ_MEDIA_IMAGES
                    )
                ) {

                }

                ActivityCompat.requestPermissions(
                    activity,
                    arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_MEDIA_IMAGES),
                    requestCode
                )
                return false
            }
        }
        return true
    }

    fun showMoveSettingDialog(activity: Activity, guideMessage: Int) {
        val alertDialog: AlertDialog.Builder = getCompatDialog(activity)
        alertDialog.setTitle(activity.getString(R.string.app_name))
        alertDialog.setMessage(activity.getString(guideMessage))
        alertDialog.setCancelable(false)
        alertDialog.setPositiveButton(
            activity.getString(R.string.app_setting)
        ) { dialog, which ->
            val intent = Intent()
            intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
            val uri = Uri.fromParts("package", activity.packageName, null)
            intent.data = uri
            activity.startActivity(intent)
        }
        alertDialog.setNegativeButton(
            activity.getString(R.string.alert_btn_cancel)
        ) { dialog, which -> }
        alertDialog.show()
    }

    /**
     * 호환성 다이얼로그 빌더 리턴
     * @return [AlertDialog.Builder]
     */
    fun getCompatDialog(context: Context?): AlertDialog.Builder {
        return AlertDialog.Builder(context, androidx.appcompat.R.style.Base_Theme_AppCompat_Light_Dialog_Alert)
    }
}