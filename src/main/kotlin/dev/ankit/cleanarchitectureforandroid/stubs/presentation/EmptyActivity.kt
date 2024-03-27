package dev.ankit.cleanarchitectureforandroid.stubs.presentation

import com.android.tools.idea.wizard.template.RecipeExecutor
import com.android.tools.idea.wizard.template.escapeKotlinIdentifier

fun emptyLauncherActivity(
    packageName: String,
    activityClass: String
) = """
package ${escapeKotlinIdentifier(packageName)}

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import ${packageName}.R
import dagger.hilt.android.AndroidEntryPoint
    
@AndroidEntryPoint
class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_launcher)

    }
}
""".trimIndent()