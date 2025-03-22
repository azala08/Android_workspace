package com.example.speak

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.speak.R.*
import java.util.Locale

class MainActivity : AppCompatActivity(), TextToSpeech.OnInitListener {
    private lateinit var listView: ListView
    private lateinit var list: MutableList<String>

    private lateinit var tts: TextToSpeech

    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var mediaPlayer1: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        listView = findViewById(id.list)

        list = ArrayList()
        tts = TextToSpeech(applicationContext, this)
        mediaPlayer = MediaPlayer.create(applicationContext, raw.lion)
        mediaPlayer1 = MediaPlayer.create(applicationContext, raw.tiger)

        registerForContextMenu(listView)
        list.add("Lion")
        list.add("Tiger")
        var adapter = ArrayAdapter(applicationContext, android.R.layout.simple_list_item_1, list)
        listView.adapter = adapter
    }


    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        menuInflater.inflate(R.menu.context, menu)

        super.onCreateContextMenu(menu, v, menuInfo)
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {

        var acm: AdapterView.AdapterContextMenuInfo =
            item.menuInfo as AdapterView.AdapterContextMenuInfo
        var pos = acm.position

        //Toast.makeText(applicationContext, ""+pos, Toast.LENGTH_SHORT).show()

        when (item.itemId) {
            R.id.voice_speak -> {
                tts.speak(list[pos].toString(), TextToSpeech.QUEUE_ADD, null)

                Toast.makeText(applicationContext, "Speak", Toast.LENGTH_SHORT).show()
            }

            id.voice_sound -> {
                if (pos == 0) {
                    mediaPlayer.start()
                } else {
                    mediaPlayer1.start()
                }

                Toast.makeText(applicationContext, "Sound", Toast.LENGTH_SHORT).show()
            }
        }

        return super.onContextItemSelected(item)
    }

    override fun onInit(p0: Int) {
        var status = null
        if (status == TextToSpeech.SUCCESS) {
            val result = tts.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Toast.makeText(this, "TTS Language not supported", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "TTS Initialized successfully", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "TTS Initialization failed", Toast.LENGTH_SHORT).show()
        }
    }
}




