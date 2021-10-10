package com.indexer.bilemo.utils


import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.widget.Toast
import com.fondesa.kpermissions.PermissionStatus
import com.fondesa.kpermissions.request.PermissionRequest
import com.indexer.bilemo.R
import java.security.MessageDigest


inline fun <reified T : Any> Activity.launchActivity(
    requestCode: Int = -1,
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this)
    intent.init()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        startActivityForResult(intent, requestCode, options)
    } else {
        startActivityForResult(intent, requestCode)
    }
}


inline fun <reified T : Any> Context.launchActivity(
    options: Bundle? = null,
    noinline init: Intent.() -> Unit = {}
) {
    val intent = newIntent<T>(this)
    intent.init()
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        startActivity(intent, options)
    } else {
        startActivity(intent)
    }
}

fun <T> genericCastOrNull(anything: Any?, clazz: Class<T>): T? {
    return if (clazz.isInstance(anything)) {
        anything as T
    } else null
}

fun getHashFromPasswordString(password: String): String {
    val charset = Charsets.UTF_8
    val byteArray = password.toByteArray(charset)
    val digest = MessageDigest.getInstance("SHA-256")
    val hash = digest.digest(byteArray)
    return hash.fold("", { str, it -> str + "%02x".format(it) })
}

inline fun <reified T : Any> newIntent(context: Context): Intent =
    Intent(context, T::class.java)

internal fun Context.showGrantedToast(permissions: List<PermissionStatus>) {
    val msg =
        getString(R.string.granted_permissions, permissions.toMessage<PermissionStatus.Granted>())
    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}

internal fun Context.showRationaleDialog(
    permissions: List<PermissionStatus>,
    request: PermissionRequest
) {
    val msg = getString(
        R.string.rationale_permissions,
        permissions.toMessage<PermissionStatus.Denied.ShouldShowRationale>()
    )

    AlertDialog.Builder(this)
        .setTitle(R.string.permissions_required)
        .setMessage(msg)
        .setPositiveButton(R.string.request_again) { _, _ ->
            // Send the request again.
            request.send()
        }
        .setNegativeButton(android.R.string.cancel, null)
        .show()
}

internal fun Context.showPermanentlyDeniedDialog(permissions: List<PermissionStatus>) {
    val msg = getString(
        R.string.permanently_denied_permissions,
        getString(R.string.require_permission)
    )

    AlertDialog.Builder(this)
        .setTitle(R.string.permissions_required)
        .setMessage(msg)
        .setPositiveButton(R.string.action_settings) { _, _ ->
            // Open the app's settings.
            val intent = createAppSettingsIntent()
            startActivity(intent)
        }
        .setNegativeButton(android.R.string.cancel, null)
        .show()
}

private fun Context.createAppSettingsIntent() = Intent().apply {
    action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
    data = Uri.fromParts("package", packageName, null)
}

private inline fun <reified T : PermissionStatus> List<PermissionStatus>.toMessage(): String =
    filterIsInstance<T>()
        .joinToString { it.permission }














