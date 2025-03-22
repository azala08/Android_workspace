package com.example.task_fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment

class BlankFragment : Fragment() {

    private var buttonCount: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_blank, container, false)
        val buttonContainer = view.findViewById<LinearLayout>(R.id.buttonContainer)

        // Generate buttons dynamically
        for (i in 1..buttonCount) {
            val button = Button(requireContext()).apply {
                text = "Button $i"
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
            }
            buttonContainer.addView(button)
        }
        return view
    }
    companion object {
        // Create a new instance of the fragment with arguments
        fun newInstance(buttonCount: Int): BlankFragment {
            val fragment = BlankFragment()
            val args = Bundle()
            args.putInt("buttonCount", buttonCount)
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Retrieve the button count from the arguments
        arguments?.let {
            buttonCount = it.getInt("buttonCount", 0)
        }
    }
}
