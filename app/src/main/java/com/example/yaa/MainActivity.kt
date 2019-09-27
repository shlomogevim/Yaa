package com.example.yaa

import android.graphics.Color
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.animation.LinearInterpolator
import androidx.appcompat.app.AppCompatActivity
import com.github.florent37.viewanimator.ViewAnimator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var screenHeight = 0.0f
    var counter = 0
    lateinit var mainArrayDialog:MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()




        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        screenHeight = displayMetrics.heightPixels.toFloat()





        letsSpeak.setOnClickListener {


            //  startIntroAnimation()
            //    startAnimation()

            //1
           // generalAnimayoin1()

            //2
           // animationFad()
            //3
           //  animationText()
            //4
            //  animationToSeveralView()
            //5
            //  animateDp()
            //6
                  animateColor()
            //7
           // generalAnimayoin1()

        }
    }




    private fun generalAnimayoin1() {
        ViewAnimator
            .animate(letsSpeak)
            .translationX(0f, 100f, -100f,0f)
            .duration(1000)
            .repeatCount(5)
            .start()

    }

    private fun generalAnimayoin() {
        ViewAnimator
            .animate(letsSpeak)
            .shake()
          //  .interpolator(LinearInterpolator())
            .start()

    }


    private fun animateColor() {
        ViewAnimator
            .animate(speakingTextview)
            .onStart {
                speakingTextview.text = "מה קורה נסיך"
            }
            .textColor(Color.BLACK, Color.GREEN)
            .backgroundColor(Color.WHITE, Color.BLACK)
            .duration(3000)
            .start()
    }

    private fun animateDp() {
        counter++
      /*  ViewAnimator
            .animate(happySmilly, sadSmilly)
            .dp().translationY(0f, 500f)
            .duration(3000)
            .accelerate()
            .thenAnimate(happySmilly, sadSmilly)
            .dp().translationY(500f, 0f)
            .rotation(720f * counter)
            .duration(3000)
            .decelerate()
            *//*.onStop {
                happySmilly.visibility= View.GONE
                happySmilly.animate().rotation(-720f)
                sadSmilly.animate().rotation(-720f)
                happySmilly.visibility= View.VISIBLE
            }*//*
            .start()*/
    }

    private fun animationToSeveralView() {
       /* ViewAnimator
            .animate(happySmilly, sadSmilly)
            .scale(0f, 30f, 0f)
            .duration(10000)
            .start()*/
    }

    private fun animationText() {
        ViewAnimator
            .animate(speakingTextview)
            .scale(0f, 20f, 0f)
            .duration(10000)
            .onStart {
                speakingTextview.text = "מה קורה מלך"
            }
            .start()
    }

    private fun animationFad() {
        ViewAnimator
            .animate(imageView)
            .alpha(1f, 0f)
            .duration(3000)
            .decelerate()
            .thenAnimate(imageView)
            .alpha(0f, 1f)
            .duration(3000)
            .accelerate()
            .start()
    }

    private fun startIntroAnimation() {   //https://github.com/florent37/ViewAnimator
        ViewAnimator
            .animate(titleTextView)
            .scale(0f, 1f)
            .duration(1500)
            .onStart {
                titleTextView.text = "הנה זה מתחיל !!!"
            }
            .start()
    }


    private fun startAnimation() {
        ViewAnimator
            .animate(letsSpeak)
            .alpha(0f, 1f)
            .onStart {
                titleTextView.text = "יאללה לעבודה בנות !!!"
            }
            .decelerate()
            .duration(1000)
            .thenAnimate(letsSpeak)
            .scale(0.02f, 1.5f, 0.02f)
            .rotation(360f)
            .repeatCount(2)
            .accelerate()
            .duration(5000)
            .onStop {
                titleTextView.text = "זהו זה נגמר"
                letsSpeak.scaleX = 1.0f
                letsSpeak.scaleY = 1.0f

                /* prefs.sessions = prefs.sessions + 1
                 prefs.breaths = prefs.breaths + 1
                 prefs.setDate(System.currentTimeMillis())

                 var handler = Handler()
                 var countDownTimer = object : Runnable {
                     override fun run() {
                         startActivity(Intent(this@MainActivity, MainActivity::class.java))
                         finish()
                     }
                 }
                 handler.postDelayed(countDownTimer, 100)*/
            }
            .start()
    }

    private fun getData() {
        val text=applicationContext.assets.open("myText1.txt").bufferedReader().use {
            it.readText()
        }
        mainArrayDialog=text.split("!").toMutableList()
        for (i in 0 until mainArrayDialog.size){
            val st=mainArrayDialog[i]
            if ((i%2)==0){
                manSpeaking(st)
            }else{
                godSpeaking(st)
            }
        }
    }
    private fun godSpeaking(st: String) {
        Log.d("clima"," god ==> $st")


    }

    private fun manSpeaking(st: String) {
        Log.d("clima"," man ==> $st")

    }

}




