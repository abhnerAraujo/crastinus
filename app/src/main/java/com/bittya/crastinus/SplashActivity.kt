package com.bittya.crastinus

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()

        val imageLogo: Bitmap  = BitmapFactory.decodeResource(resources, R.drawable.ic_logo_white)
        val resized = Bitmap.createScaledBitmap(imageLogo,
                kotlin.math.floor(imageLogo.width * 0.7).toInt(),
                kotlin.math.floor(imageLogo.height * 0.7).toInt(),
                false)

//        logo.setImageBitmap(resized)

        Handler().postDelayed(
                {
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                },
                1000
        )


    }

}
