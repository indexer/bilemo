package com.indexer.bilemo.splash

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.WindowInsets
import android.view.WindowManager.LayoutParams.FLAG_FULLSCREEN
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.indexer.bilemo.MainActivity
import com.indexer.bilemo.databinding.ActivitySplashBinding
import com.indexer.bilemo.login.entities.LoginUser
import com.indexer.bilemo.splash.viewmodel.SplashViewModel
import com.indexer.bilemo.utils.launchActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivitySplashBinding

    private val splashViewModel: SplashViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            window.addFlags(FLAG_FULLSCREEN)
        }

        lifecycleScope.launch {
            delay(1000)
            val currentUser = LoginUser(
                "yemonkyaw",
                "#123567Abc", "Singapore"
            )
            splashViewModel.insertLoginUser(currentUser)
            launchActivity<MainActivity> { }
            finish()
        }
    }

}