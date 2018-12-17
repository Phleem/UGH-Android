package ugh_technologies.universalgamehelper.Notes

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.*
import ugh_technologies.universalgamehelper.R


class NotesFragment: Fragment(){
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.notes_layout, container, false)
        val listBox = view.findViewById(R.id.TextList) as ListView
        val inputBox = view.findViewById(R.id.InputBox) as EditText

        val list: ArrayList<String> = ArrayList()
        val adapter: ArrayAdapter<String> = ArrayAdapter<String>(view.context, listBox.id, list)
        listBox.adapter = adapter

        inputBox.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView, actionId: Int,event : KeyEvent): Boolean {
                // If the event is a key-down event on the "enter" button
                if (actionId == EditorInfo.IME_ACTION_SEARCH
                                || actionId == EditorInfo.IME_ACTION_DONE
                                || event.action == KeyEvent.ACTION_DOWN
                                && event.keyCode == KeyEvent.KEYCODE_ENTER) {
                    // Perform action on key press
                    Toast.makeText(v.context, inputBox.text, Toast.LENGTH_SHORT).show()
                    list.add(inputBox.text.toString())
                    adapter.add(inputBox.text.toString())
                    inputBox.text.clear()
                    return true
                }
                return false
            }
        })
        return view
    }
}