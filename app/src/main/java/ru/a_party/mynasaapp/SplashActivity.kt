package ru.a_party.mynasaapp

import android.animation.Animator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.view.animation.Animation
import android.widget.ImageView

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val view = findViewById<ImageView>(R.id.nasaLogo)
            .animate()
            .rotation(0f)
            .setDuration(5000)
            .setListener(object : Animator.AnimatorListener{
                override fun onAnimationStart(animation: Animator?) {
                    //TODO("Not yet implemented")
                }

                override fun onAnimationEnd(animation: Animator?) {
                    startActivity(Intent(this@SplashActivity,MainActivity::class.java))
                    finish()
                }

                override fun onAnimationCancel(animation: Animator?) {
                    //TODO("Not yet implemented")
                }

                override fun onAnimationRepeat(animation: Animator?) {
                    //TODO("Not yet implemented")
                }

            })



    }

    override fun onPostCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onPostCreate(savedInstanceState, persistentState)

    }
}