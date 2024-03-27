package dev.ankit.cleanarchitectureforandroid.stubs

fun emptyActivity(
    packageName: String
) = """
package $packageName.presentation.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import $packageName.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}    
""".trimIndent()