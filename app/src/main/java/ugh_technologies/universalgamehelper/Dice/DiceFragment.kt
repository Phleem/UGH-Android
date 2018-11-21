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

        val button = view.findViewById(R.id.dice_button) as Button

        val random = Random()

        button.setOnClickListener {
            button.text = random.nextInt(7).toString()
        }
        return view
    }
}