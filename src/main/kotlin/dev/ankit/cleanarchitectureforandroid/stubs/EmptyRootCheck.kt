package dev.ankit.cleanarchitectureforandroid.stubs

import com.android.tools.idea.wizard.template.PackageName
import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.escapeKotlinIdentifier

fun emptyRootCheck(packageName: PackageName): String {
    val nativeLibraryDir = "$".plus("nativeLibraryDir")

    return """
    package ${escapeKotlinIdentifier(packageName)}.data.utils

    import android.annotation.SuppressLint
    import android.app.Activity
    import android.app.AlertDialog
    import android.content.Context
    import android.content.pm.PackageManager
    import android.os.Build
    import android.provider.Settings
    import android.util.Log
    import androidx.core.app.ActivityCompat.finishAffinity
    import java.io.BufferedReader
    import java.io.File
    import java.io.InputStreamReader


    object RootUtil {

        private val isDeviceRooted: Boolean
        get() = checkRootMethod1() || checkRootMethod2() || checkRootMethod3()

    private fun checkRootMethod1(): Boolean {
        val buildTags = Build.TAGS
        return buildTags != null && buildTags.contains("test-keys")
    }

    private fun checkRootMethod2(): Boolean {
        val paths = arrayOf(
            "/system/app/Superuser.apk",
            "/sbin/su",
            "/system/bin/su",
            "/system/xbin/su",
            "/data/local/xbin/su",
            "/data/local/bin/su",
            "/system/sd/xbin/su",
            "/system/bin/failsafe/su",
            "/data/local/su",
            "/su/bin/su"
        )
        for (path in paths) {
            if (File(path).exists()) return true
        }
        return false
    }

    private fun checkRootMethod3(): Boolean {
        var process: Process? = null
        return try {
            process = Runtime.getRuntime().exec(arrayOf("/system/xbin/which", "su"))
            val `in` = BufferedReader(InputStreamReader(process.inputStream))
            `in`.readLine() != null
        } catch (t: Throwable) {
            false
        } finally {
            process?.destroy()
        }
    }

    private fun searchForMagisk(context: Context): Boolean {
        val pm: PackageManager = context.packageManager
        var isExists = false
        @SuppressLint("QueryPermissionsNeeded") val installedPackages = pm.getInstalledPackages(0)
        for (i in installedPackages.indices) {
            val info = installedPackages[i]
            val appInfo = info.applicationInfo
            val nativeLibraryDir = appInfo.nativeLibraryDir
            Log.i("Magisk Detection", "Checking App: $nativeLibraryDir")
            val f = File("$nativeLibraryDir/libstub.so")
            isExists = f.exists()
        }
        return isExists
    }

    fun appCheck(activity: Activity) {
        if (isDeviceRooted || searchForMagisk(activity)) {
            showDialog(activity,"Android device is rooted so you can't continue.")
        }


        if (Settings.Global.getInt(activity.contentResolver, Settings.Global.ADB_ENABLED, 0) == 1) {
            showDialog(
                activity, "To proceed with Application please turn off USB Debugging and try again.")
        }
    }

    private fun showDialog(activity: Activity, message: String){
        val builder = AlertDialog.Builder(activity)
        builder.setTitle("Alert")
        builder.setMessage(message)
        builder.setPositiveButton("Okay") { dialog, which ->
            dialog.cancel()
            finishAffinity(activity)
        }
        builder.setCancelable(false)
        builder.show()
    }   
}
""".trimIndent()
}