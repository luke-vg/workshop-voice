package com.example.voice

import android.app.Activity
import android.os.Bundle
import android.speech.SpeechRecognizer
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast
import android.content.Intent
import android.speech.RecognizerIntent
import android.widget.TextView
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private val RQ_SPEECH_REC = 102
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button: Button = findViewById(R.id.btn_button)
        button.setOnClickListener {
            askSpeechInput()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RQ_SPEECH_REC && resultCode == Activity.RESULT_OK) {
            val result = data?.getStringArrayExtra(RecognizerIntent.EXTRA_RESULTS)
            var texthingy: TextView = findViewById<TextView>(R.id.textje) as TextView
            texthingy = result?.get(0).toString() as TextView
        }
    }

    private fun askSpeechInput() {
        if (!SpeechRecognizer.isRecognitionAvailable(this)) {
            Toast.makeText(this, "Herkenning niet beschikbaar", Toast.LENGTH_SHORT).show()
        } else {
            val i = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)
            i.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.ENGLISH)
            i.putExtra(RecognizerIntent.EXTRA_PROMPT, "You were saying?")
            startActivityForResult(i, RQ_SPEECH_REC)
        }
    }
}