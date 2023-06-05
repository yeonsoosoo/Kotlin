package com.pys.ex_mediastore

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import java.io.File

object IntentUtil {

    fun startActionCropImage(
        context: Context,
        uri: Uri?,
        path: String,
        isAspect: Boolean,
        requestCode: Int
    ) {
        context.grantUriPermission(
            BuildConfig.APPLICATION_ID + ".provider",
            uri,
            Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_GRANT_READ_URI_PERMISSION
        )
        val intent = Intent("com.android.camera.action.CROP")
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION)
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        intent.setDataAndType(uri, "image/*")

//		intent.putExtra("outputX", 10); // crop한 이미지의 최대 x축 크기. 디폴트는 영역크기
//		intent.putExtra("outputY", 10); // crop한 이미지의 최대 y축 크기. 디폴트는 영역크기
        if (isAspect) {
            intent.putExtra("aspectX", 10) // crop 박스의 x축 비율. 영역 비율
            intent.putExtra("aspectY", 10) // crop 박스의 y축 비율. 영역 비율
        }
        intent.putExtra("scale", true)
        //intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(File(path)))
        //intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString())
        //		intent.putExtra("return-data", true); //동작안하는 기기가 있다...

        //"com.android.gallery3d", "com.google.android.apps.plus" 둘 중 하나가 실행. 없으면 다른 선택가능한 리스트 노출
        /*val targets = ArrayList<String>()
        targets.add("com.sec.android.")
        targets.add("com.android.")
        targets.add("com.google.android.")*/
        (context as Activity).startActivityForResult(
            /*setIntentLaunchTargetPackage(
                context,
                intent,
                targets
            )*/intent, requestCode
        )
    }

    /**
     * Intent를 실행가능한 앱 목록 중 지정된 타겟을 설정. 먼저 찾은 아이템으로 설정. 찾은 아이템이 없다면 원본 Intent 그대로 리턴된다
     */
    private fun setIntentLaunchTargetPackage(
        context: Context,
        settingIntent: Intent,
        targetPackages: java.util.ArrayList<String>
    ): Intent? {
        val pkgAppsList = context.packageManager.queryIntentActivities(settingIntent, 0)
        for (resolveInfo in pkgAppsList) {
            val info = resolveInfo.activityInfo
            val packageName = info.packageName
            for (target in targetPackages) {
                if (packageName.startsWith(target)) {
                    Log.d("launch packageName=", packageName)
                    settingIntent.setPackage(packageName)
                    return settingIntent
                }
            }
        }
        return settingIntent
    }
}