package ugh_technologies.universalgamehelper.Timer

import android.os.Bundle
import android.os.SystemClock
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Chronometer
import ugh_technologies.universalgamehelper.R


class TimerFragment : Fragment(){


    lateinit var startStopButton: Button
    lateinit var chrono: Chronometer
    private var isChronoRunning = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.timer_fragment_layout, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        startStopButton = view!!.findViewById(R.id.startstop)
        startStopButton.setOnClickListener { startStopClicked() }


        chrono = view!!.findViewById(R.id.time)
        chrono.base = SystemClock.elapsedRealtime()
    }

    private fun startStopClicked() {
        if(isChronoRunning) {
            chrono.stop()
            isChronoRunning = false
            startStopButton.setText("Start")
        }
        else {
            chrono.base = SystemClock.elapsedRealtime()
            chrono.start()
            isChronoRunning = true
            startStopButton.setText("Stop")
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        chrono.stop()
    }
}