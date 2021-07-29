package com.example.colour_guessing_2_1

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlin.system.exitProcess


class ResultActivity : AppCompatActivity() {

    private var backPressedTime:Long = 0
    lateinit var backToast:Toast
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val resultLabel = findViewById<TextView>(R.id.resultLabel)
        val totalScoreLabel = findViewById<TextView>(R.id.totalScoreLabel)

        val score = intent.getIntExtra("RIGHT_ANSWER_COUNT", 0)
        val question = intent.getIntExtra("Total_Question", 0)


        //val sharedPreferences = getSharedPreferences("QUIZ_DATA", Context.MODE_PRIVATE)
       // var totalScore = sharedPreferences.getInt("TOTAL_SCORE", 0)
       // totalScore += score

        resultLabel.text = "$score / $question "
        totalScoreLabel.text = "Your Score is $score out of $question"

        // Update total score.

        // Update total score.
      //  val editor = sharedPreferences.edit()
      //  editor.putInt("TOTAL_SCORE", totalScore)
       // editor.apply()
    }

    fun returnTop(view: View?) {
        startActivity(Intent(applicationContext, MainActivity::class.java))
    }

    fun Quit (view: View?){


       // this@ResultActivity.finish()
       // exitProcess(0)
      //  finishAffinity()
        AlertDialog.Builder(this)
            //.setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle("Closing App")
            .setMessage("Are you sure you want to close the  app?")
            .setPositiveButton("Yes",
                DialogInterface.OnClickListener { dialog, which ->finishAffinity()
                })
            .setNegativeButton("No", null)


            .show()

    }

    /*override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle("Closing App")
            .setMessage("Are you sure you want to close the  app?")
            .setPositiveButton("Yes",
                DialogInterface.OnClickListener { dialog, which -> finish(); System.exit(0)
                })
            .setNegativeButton("No", null)
            .show()
    }*/

   /* override fun onBackPressed() {
        backToast = Toast.makeText(this, "Press back again to leave the app.", Toast.LENGTH_LONG)
        if (backPressedTime + 2000 > System.currentTimeMillis()) {
            backToast.cancel()
            super.onBackPressed()
            return
        } else {
            backToast.show()
        }
        backPressedTime = System.currentTimeMillis()
    }*/
}