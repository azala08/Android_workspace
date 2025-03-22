package com.example.taskexample

import android.content.Context
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import java.util.Locale

class AnimalAdapter(
    private val context: Context,
    private val animals: List<Animal>
) : RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder>() {

    private var textToSpeech: TextToSpeech? = null

    init {
        // Initialize TextToSpeech with context
        textToSpeech = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech?.language = Locale.US
            }
        }
    }

    class AnimalViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageAnimal: ShapeableImageView = view.findViewById(R.id.imageAnimal)
        val textAnimalName: TextView = view.findViewById(R.id.textAnimalName)
        val buttonSpeak: ImageButton = view.findViewById(R.id.buttonSpeak)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnimalViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_animal, parent, false)
        return AnimalViewHolder(view)
    }

    override fun onBindViewHolder(holder: AnimalViewHolder, position: Int) {
        val animal = animals[position]
        holder.imageAnimal.setImageResource(animal.imageResId)
        holder.textAnimalName.text = animal.name

        holder.buttonSpeak.setOnClickListener {
            textToSpeech?.speak(animal.name, TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    override fun getItemCount() = animals.size

    fun cleanup() {
        textToSpeech?.stop()
        textToSpeech?.shutdown()
    }
}