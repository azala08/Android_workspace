package com.example.meditationapp.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.meditationapp.R


class VoiceFragment : Fragment() {
    private var voiceType: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            voiceType = it.getString(ARG_VOICE_TYPE)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_voice, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<TextView>(R.id.voiceTypeText).text = "$voiceType Voice Content"
    }

    companion object {
        private const val ARG_VOICE_TYPE = "voice_type"

        fun newInstance(voiceType: String) =
            VoiceFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_VOICE_TYPE, voiceType)
                }
            }
    }
}
