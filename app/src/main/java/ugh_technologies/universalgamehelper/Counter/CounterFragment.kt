package ugh_technologies.universalgamehelper.Counter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import ugh_technologies.universalgamehelper.R
import java.util.*

class CounterFragment: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.counter_layout, container, false)

        var state = 0
        var increment = 1
        val text = view.findViewById(R.id.counter_text) as TextView

        val button2 = view.findViewById(R.id.plus_button) as Button
        button2.setOnClickListener {
            state += increment
            text.text = state.toString()
        }
        val button3 = view.findViewById(R.id.minus_button) as Button
        button3.setOnClickListener {
            state -= increment
            text.text = state.toString()
        }
        val button4 = view.findViewById(R.id.one_button) as Button
        button4.setOnClickListener {
            increment=1
        }
        val button5 = view.findViewById(R.id.ten_button) as Button
        button5.setOnClickListener {
            increment=10
        }
        val button6 = view.findViewById(R.id.hundred_button) as Button
        button6.setOnClickListener {
            increment=100
        }
        return view
    }
}