package ugh_technologies.universalgamehelper.Dice

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import ugh_technologies.universalgamehelper.R
import java.util.Random

class DiceFragment: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.dice_layout, container, false)

        val random = Random()
        var bound = 6

        val button = view.findViewById(R.id.dice_button) as Button
        button.setOnClickListener {
            button.text = (random.nextInt(bound) + 1).toString()
        }

        val button0 = view.findViewById(R.id.d6_button) as Button
        button0.setOnClickListener {
            bound = 6
        }

        val button1 = view.findViewById(R.id.d10_button) as Button
        button1.setOnClickListener {
            bound = 10
        }

        val button2 = view.findViewById(R.id.d20_button) as Button
        button2.setOnClickListener {
            bound = 20
        }
        return view
    }
}