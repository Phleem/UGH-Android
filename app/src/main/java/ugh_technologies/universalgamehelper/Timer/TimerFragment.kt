package ugh_technologies.universalgamehelper.Timer

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.TimeUtils
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import android.widget.EditText
import kotlinx.android.synthetic.*
import ugh_technologies.universalgamehelper.R
import java.util.*
import kotlin.concurrent.timerTask

class TimerFragment : Fragment(), OnClickListener {


    lateinit var startStopButton: Button
    lateinit var resetButton: Button
    lateinit var chrono: Chronometer

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.timer_fragment_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        startStopButton = view!!.findViewById(R.id.startstop)
        resetButton = view!!.findViewById(R.id.reset)
        chrono = view!!.findViewById(R.id.time)

    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            startStopButton.id -> startStopClicked()
            resetButton.id -> resetClicked()
        }
    }

    private fun startStopClicked() {
        if(chrono.text == 0.toString()) {
            chrono.start()
        }
        else {
           chrono.stop()
        }
    }

    private fun resetClicked() {
        chrono.stop()
        chrono.text = 0.toString()
    }
}