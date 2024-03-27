package dev.ankit.cleanarchitectureforandroid.stubs

fun emptyMainActivityLayout() = """
    <?xml version="1.0" encoding="utf-8"?>
    <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@color/white"
        tools:context=".presentation.view.MainActivity">

        <TextView
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:text="This is Main Activity" />
        
    </androidx.constraintlayout.widget.ConstraintLayout>
""".trimIndent()

fun emptyLauncherActivityLayout() = """
    <?xml version="1.0" encoding="utf-8"?>
    <androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        xmlns:tools="http://schemas.android.com/tools"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="@color/white"
        tools:context=".LauncherActivity">
        
        <TextView
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:text="This is Launcher Activity" />

    </androidx.constraintlayout.widget.ConstraintLayout>
""".trimIndent()


fun emptyLoaderFragment() = """
    <?xml version="1.0" encoding="utf-8"?>
    <FrameLayout xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:tools="http://schemas.android.com/tools"
        android:id="@+id/loaderContainer"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#80000000"
        android:clickable="true"
        android:focusable="true"
        tools:context=".presentation.widget.loader.LoaderFragment">

        <ProgressBar
            android:layout_width="100dp"
            android:layout_height="100dp"
            android:layout_gravity="center"
            android:background="@drawable/loader_bg"
            android:padding="20dp" />

    </FrameLayout>
""".trimIndent()

fun emptyLoaderBg() = """
    <?xml version="1.0" encoding="utf-8"?>
    <shape android:shape="rectangle" xmlns:android="http://schemas.android.com/apk/res/android">
        <corners android:radius="20dp"/>
        <solid android:color="@color/white" />
    </shape>
""".trimIndent()

fun emptyThemesXml() = """
    <resources>
        <!-- Base application theme -->
        <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
            <item name="colorPrimary">@color/colorPrimary</item>
            <item name="colorPrimaryDark">@color/colorPrimaryDark</item>
            <item name="colorAccent">@color/colorAccent</item>
            <item name="android:textColorPrimary">@color/textColorPrimary</item>
            <item name="android:textColorSecondary">@color/textColorSecondary</item>
        </style>

        <style name="ActionBarFromAppTheme" parent="AppTheme">
            <item name="windowActionBar">true</item>
            <item name="windowActionBarOverlay">true</item>
        </style>

        <style name="CustomSplashScreen" parent="Theme.SplashScreen">
            <item name="windowSplashScreenBackground">@color/white</item>
            <item name="windowSplashScreenAnimatedIcon">@mipmap/ic_launcher</item>
            <item name="windowSplashScreenAnimationDuration">1000</item>
            <item name="postSplashScreenTheme">@style/AppTheme</item>
        </style>
    </resources>
""".trimIndent()

fun emptyColoursXml() = """
    <?xml version="1.0" encoding="utf-8"?>
    <resources>
        <color name="purple_200">#FFBB86FC</color>
        <color name="purple_500">#FF6200EE</color>
        <color name="purple_700">#FF3700B3</color>
        <color name="teal_200">#FF03DAC5</color>
        <color name="teal_700">#FF018786</color>
        <color name="black">#FF000000</color>
        <color name="white">#FFFFFFFF</color>
        
        <!-- Day Theme Colors -->
        <color name="colorPrimary">#075E54</color>
        <color name="colorPrimaryDark">#004D40</color>
        <color name="colorAccent">#25D366</color>
        <color name="textColorPrimary">#000000</color>
        <color name="textColorSecondary">#757575</color>

        <!-- Night Theme Colors -->
        <color name="nightColorPrimary">#212121</color>
        <color name="nightColorPrimaryDark">#000000</color>
        <color name="nightColorAccent">#4CAF50</color>
        <color name="nightTextColorPrimary">#FFFFFF</color>
        <color name="nightTextColorSecondary">#B0B0B0</color>
    </resources>
""".trimIndent()