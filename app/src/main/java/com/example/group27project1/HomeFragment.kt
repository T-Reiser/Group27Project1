package com.example.group27project1

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {
    private lateinit var teamA: Team
    private lateinit var teamAName: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        teamA = Team()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        teamAName = view.findViewById(R.id.teamA_name) as EditText

        return view
        //return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onStart() {
        super.onStart()

        val teamANameWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                TODO("Not yet implemented")
            }

            override fun onTextChanged(sequence: CharSequence, start: Int, before: Int, count: Int) {
                teamA.name = sequence.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
                TODO("Not yet implemented")
            }
        }
    }
}