package com.athena.entertainguide.utils.notifications

import android.Manifest
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding
import com.athena.entertainguide.ui.BaseActivity


fun <VB : ViewBinding> BaseActivity<VB>.checkNotificationPermission(
    hasPermissionCallback: ((Boolean) -> Unit)? = null
) {

    val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { permissionGranted ->
        hasPermissionCallback?.invoke(permissionGranted && isNotificationEnabled())
    }

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        val permission = Manifest.permission.POST_NOTIFICATIONS
        val hasPermission = ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED

        if (hasPermission) {
            // application has the permission granted on the device, so we need to check if it is active or not
            hasPermissionCallback?.invoke(isNotificationEnabled())
        } else {
            // no information about previous permission request could be found or the
            // user removed the permission on the application settings
            requestPermissionLauncher.launch(permission)
        }
    } else {
        // sdk version is lower than 33, so the permission is granted from manifest
        // validating only through isNotificationEnabled
        hasPermissionCallback?.invoke(isNotificationEnabled())
    }
}

fun Context.isNotificationEnabled(): Boolean {

    val notificationManager: NotificationManager =
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    return notificationManager.areNotificationsEnabled()
}