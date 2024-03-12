package com.norm.myrecordplayaudio.playback

import java.io.File

interface AudioPlayer {
    fun playFile(file: File)
    fun stop()
}