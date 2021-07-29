package com.example.colour_guessing_2_1


import android.content.Intent
import android.graphics.Color
import android.graphics.PorterDuff
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.speech.RecognizerIntent
import android.speech.tts.TextToSpeech
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.colour_guessing_2_1.databinding.ActivityMainBinding
import java.util.*


class MainActivity : AppCompatActivity()
{

    //var r = String()
     //val result


    private lateinit var  binding: ActivityMainBinding

     public  var stringg: String?= null
    private var rightAnswerCount = 0
     var totalQueCount = 0

    private val textToSpeechEngine: TextToSpeech by lazy {
        // Pass in context and the listener.
        TextToSpeech(
            this,
            TextToSpeech.OnInitListener { status ->
                if (status == TextToSpeech.SUCCESS) {
                    textToSpeechEngine.language = Locale.UK
                }
            }
        )
    }
   // var textToSpeech: TextToSpeech? = null


    private var colours= mutableListOf(
        R.drawable.white,
        R.drawable.grey,
        R.drawable.lblue,
        R.drawable.blue,
        R.drawable.green,
        R.drawable.black,
        R.drawable.brown,
        R.drawable.orange,
        R.drawable.pink,
        R.drawable.purple,
        R.drawable.red,
        R.drawable.yellow
    )
     val myMap: Map<Int, String> = mapOf<Int, String>(R.drawable.blue to "blue",R.drawable.lblue to "sky blue", R.drawable.green to "green",R.drawable.black to "black",R.drawable.brown to "Brown",R.drawable.orange to "orange" ,R.drawable.pink to "pink", R.drawable.purple to "purple",R.drawable.red to "red", R.drawable.yellow to "yellow",R.drawable.white to "white",R.drawable.grey to "Grey")
     override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

         //textToSpeech = TextToSpeech(this, this)
       //  textToSpeech!!.setLanguage(Locale.US)
        // textToSpeech.setSpeechRate(0.95f)

        var mediaPlaayer = MediaPlayer.create(this, R.raw.welcome)
         mediaPlaayer?.start()

         Run.after(4500, {
             var p = (colours).shuffled().first()
             stringg = myMap.getValue(p)
             binding.imageViewColour.setImageResource(p)

         })



        // binding.imageViewColour.setImageResource((colours).shuffled().first())
        binding.randomBtn.setOnClickListener {
          var   r= (colours).shuffled().first()
             stringg = myMap.getValue(r)
           // r.toString()



            binding.imageViewColour.setImageResource(r)
            //Snackbar.make(it,"Random Number is $r ", Snackbar.LENGTH_LONG).show()
        }

         binding.btnSpeak.setOnClickListener(View.OnClickListener {

             getSpeechInput()
             //delay(1000)
             Run.after(5000, {
                 totalQueCount ++
                 if (stringg == binding.txvResult.text) {

                     rightAnswerCount = rightAnswerCount+ 1

                     val textView = findViewById<TextView>(R.id.textView4)
                     textView.setText("Correct Answer").toString()
                     val textViewValue = textView.text
                     textView.setTextColor(Color.parseColor("#FFFFFF"))
                     textView.setBackgroundResource(R.color.green);
                     textView.visibility = View.VISIBLE
                     textView.postDelayed(Runnable { textView.setVisibility(View.INVISIBLE) }, 3000)


                     /*  val toast = Toast.makeText(applicationContext, "Correct Answer", Toast.LENGTH_LONG)
                       toast.view!!.background.setColorFilter(ContextCompat.getColor(applicationContext, R.color.green), PorterDuff.Mode.SRC_IN)
                       val textView = toast.view!!.findViewById(android.R.id.message) as TextView
                       textView.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
                       textView.setTextSize(18F)


                       toast.setGravity(Gravity.LEFT,170,270)
                       toast.show()*/
                     //KToasty.success(this, "Success!", Toast.LENGTH_SHORT, true).show()
                    /* val toast = Toast.makeText(applicationContext,"<b>Correct Answer !</b>",Toast.LENGTH_LONG)
                     toast.setGravity(Gravity.LEFT,170,270)
                    // myToast.getView()?.setBackgroundColor(Color.parseColor("#F6AE2D"));
                     val toastMessage = toast.view!!.findViewById(android.R.id.message) as TextView
                     toastMessage.setTextColor(Color.parseColor("#008000"))


//View view =myToast.getView();

                     toast.show()
*/
                     //Toast.makeText(this@MainActivity, "Correct Answer!", Toast.LENGTH_LONG).show()
                     //toast.view?.background!!.setColorFilter(ContextCompat.getColor(context, R.color.white), PorterDuff.Mode.SRC_IN)

                     var mediaPlayer = MediaPlayer.create(this, R.raw.correct)
                     mediaPlayer?.start()


                 } else {

                     val textView = findViewById<TextView>(R.id.textView3)
                     textView.setText("Wrong Answer!  The correct answer is " + stringg).toString()
                     val textViewValue = textView.text
                     textView.setTextColor(Color.parseColor("#FFFFFF"))
                     textView.setBackgroundResource(R.color.red);
                     textView.visibility = View.VISIBLE
                     textView.postDelayed(Runnable { textView.setVisibility(View.INVISIBLE) }, 3000)
                    /* val toast = Toast.makeText(applicationContext, "Wrong Answer !!                                  The correct answer is " + stringg+".", Toast.LENGTH_LONG)
                     toast.view!!.background.setColorFilter(ContextCompat.getColor(applicationContext, R.color.red), PorterDuff.Mode.SRC_IN)
                     val textView = toast.view!!.findViewById(android.R.id.message) as TextView
                     textView.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
                     textView.setTextSize(18F)
                     toast.setGravity(Gravity.LEFT,70,270)
                     toast.show()*/
                    // Toast.makeText(this@MainActivity, "Wrong Answer!  The correct answer is " + stringg, Toast.LENGTH_LONG).show()
        /*             val myToast = Toast.makeText(applicationContext,"Wrong Answer !!                                  The correct answer is " + stringg+".",Toast.LENGTH_LONG)
                     myToast.setGravity(Gravity.LEFT,200,270)
                    // myToast.getView()?.setBackgroundColor(Color.GREEN)
                     val toastMessage = myToast.view!!.findViewById(android.R.id.message) as TextView
                     toastMessage.setTextColor(Color.RED)

                     myToast.show()
*/
                     val text = "Wrong Answer!  The correct answer is " + stringg
                     if (text.isNotEmpty()) {

                       //  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                        //     textToSpeechEngine.speak(text, TextToSpeech.QUEUE_FLUSH, null, "tts1")
                       //  } else {

                             textToSpeechEngine.speak(text, TextToSpeech.QUEUE_FLUSH, null)
                       //  }
                     } else {
                         Toast.makeText(this, "Text cannot be empty", Toast.LENGTH_LONG).show()
                     }
                 }
             })
         })

         binding.exitBtn.setOnClickListener{
             val intent = Intent(applicationContext, ResultActivity::class.java)
             intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount)
             intent.putExtra("Total_Question", totalQueCount)


             startActivity(intent)
         }


         /*     binding.switchColour.setOnCheckedChangeListener { _, b ->
                  toggleColour(b)
              }

      */
    }



    

    private fun getSpeechInput()
    {
        val intent = Intent(
            RecognizerIntent
            .ACTION_RECOGNIZE_SPEECH)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault())
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Guess the Colour !")

        if (intent.resolveActivity(packageManager) != null)
        {
            //var a = 
                startActivityForResult(intent, 10)
        } else
        {
            Toast.makeText(this, "Your Device Doesn't Support Speech Input", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            10 -> if (resultCode == RESULT_OK && data != null)
            {
                 val result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)

                binding.txvResult.text = result!![0]
               // return result!![0]
            }
        }
    }

    override fun onPause() {
        textToSpeechEngine.stop()
        super.onPause()
    }

    override fun onDestroy() {
        textToSpeechEngine.shutdown()
        super.onDestroy()
    }

  /*   private fun toggleColour(b: Boolean)
  {
        if (b) {
            binding.imageViewColour.setImageResource(R.drawable.blue)
            binding.switchColour.text = "switch to red"
              }
        else {
            binding.imageViewColour.setImageResource(R.drawable.red)
            binding.switchColour.text = "switch to blue"
            }
    }

  */
}


class Run
{
     companion object {
        fun after(delay: Long, process: () -> Unit) {
            Handler().postDelayed({
                process()
            }, delay)
        }
     }
}

