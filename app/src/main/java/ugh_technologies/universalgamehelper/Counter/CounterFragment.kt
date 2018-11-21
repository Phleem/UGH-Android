package ugh_technologies.universalgamehelper.Counter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import ugh_technologies.universalgamehelper.R
import java.util.*

class CounterFragment: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.counter_layout, container, false)

        val button = view.findViewById(R.id.counter_button) as Button

        val random = Random()

        var state = 1

        button.setOnClickListener {
            button.text = state.toString()
            state++
        }
        return view
    }
}