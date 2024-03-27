package dev.ankit.cleanarchitectureforandroid.stubs

import com.android.tools.idea.wizard.template.escapeKotlinIdentifier
import org.jetbrains.kotlin.lombok.utils.capitalize

fun emptyApplication(packageName: String, itemName: String, disableScreenshot: Boolean): String {
    val additionalImports =
        if(disableScreenshot) {
          """
            import android.app.Activity
            import android.os.Bundle
            import android.view.WindowManager
          """.trimIndent()
        } else {
            """
            """.trimIndent()
        }
    val doDisableScreenshot =
        if (!disableScreenshot) {
            """ 
            """.trimIndent()
        } else {
            """
        {
            override fun onCreate() {
                super.onCreate()
                if (BuildConfig.FLAVOR == "prod")
                    disableScreenShot()
            }

            private fun disableScreenShot() {
                registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
                    override fun onActivityCreated(p0: Activity, p1: Bundle?) {
                        p0.window.setFlags(
                            WindowManager.LayoutParams.FLAG_SECURE,
                            WindowManager.LayoutParams.FLAG_SECURE
                        )
                    }

                    override fun onActivityStarted(p0: Activity) {
                        TODO("Not yet implemented")
                    }

                    override fun onActivityResumed(p0: Activity) {
                        TODO("Not yet implemented")
                    }

                    override fun onActivityPaused(p0: Activity) {
                        TODO("Not yet implemented")
                    }

                    override fun onActivityStopped(p0: Activity) {
                        TODO("Not yet implemented")
                    }

                    override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {
                        TODO("Not yet implemented")
                    }

                    override fun onActivityDestroyed(p0: Activity) {
                        TODO("Not yet implemented")
                    }
                })
            }
        }
    """.trimIndent()
        }
    return """
package ${escapeKotlinIdentifier(packageName)}

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
$additionalImports

/**
 * Go the project pane in project explorer, find readme.txt file, and read and apply the instructions provided..
 * You'll be able to run the project smoothly
 */

@HiltAndroidApp
class ${itemName.capitalize()}Application: Application()
$doDisableScreenshot
"""
}