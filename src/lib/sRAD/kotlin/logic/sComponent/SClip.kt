package lib.sRAD.kotlin.logic.sComponent

import javax.sound.sampled.Clip
import javax.sound.sampled.AudioInputStream
import java.lang.Runnable
import java.io.File
import javax.sound.sampled.AudioSystem
import javax.sound.sampled.UnsupportedAudioFileException
import java.io.IOException
import javax.sound.sampled.DataLine
import javax.sound.sampled.LineUnavailableException

class SClip(path: String?) {
    private var audioClip: Clip? = null
    private var audioStream: AudioInputStream? = null

    fun play() {
        object : Thread(Runnable {
            audioClip!!.framePosition = 0
            audioClip!!.start()
        }) {}.start()
    }

    fun loop() {
        object : Thread(Runnable {
            audioClip!!.framePosition = 0
            audioClip!!.loop(Clip.LOOP_CONTINUOUSLY)
        }) {}.start()
    }

    fun stop() {
        audioClip!!.stop()
    }

    //clip method
    init {
        //create an AudioInputStream from a given sound file
        val audioFile = File(path)
        try {
            audioStream = AudioSystem.getAudioInputStream(audioFile)
        } catch (e: UnsupportedAudioFileException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        //acquire audio format and create a DataLine.Info object
        val format = audioStream!!.format
        val info = DataLine.Info(Clip::class.java, format)

        //obtain the Clip
        try {
            audioClip = AudioSystem.getClip()
            audioClip!!.open(audioStream)
        } catch (e: LineUnavailableException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}