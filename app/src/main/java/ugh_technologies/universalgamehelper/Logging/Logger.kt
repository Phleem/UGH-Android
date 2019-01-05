package ugh_technologies.universalgamehelper.Logging

import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter

class Logger {

    fun writeToLog(text: String){
        val file = File("sdcard/log.file")

        if(!file.exists()) file.createNewFile()

        val buf = BufferedWriter(FileWriter(file,true))
        buf.append(text)
        buf.newLine()
        buf.close()
    }

}