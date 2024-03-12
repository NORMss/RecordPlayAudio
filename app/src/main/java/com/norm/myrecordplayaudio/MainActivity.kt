package com.norm.myrecordplayaudio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import com.norm.myrecordplayaudio.playback.AndroidAudioPlayer
import com.norm.myrecordplayaudio.record.AndroidAudioRecorder
import com.norm.myrecordplayaudio.ui.theme.MyRecordPlayAudioTheme
import java.io.File

class MainActivity : ComponentActivity() {
    private val recorder by lazy {
        AndroidAudioRecorder(applicationContext)
    }

    private val player by lazy {
        AndroidAudioPlayer(applicationContext)
    }

    private var audioFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.RECORD_AUDIO),
            0
        )
        super.onCreate(savedInstanceState)
        setContent {
            MyRecordPlayAudioTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Button(
                            onClick = {
                                File(cacheDir, "audio.mp3").also {
                                    recorder.start(it)
                                    audioFile = it
                                }
                            }
                        ) {
                            Text(
                                text = "Start recording"
                            )
                        }
                        Button(
                            onClick = {
                                recorder.stop()
                            }
                        ) {
                            Text(
                                text = "Stop recording"
                            )
                        }
                        Button(
                            onClick = {
                                player.playFile(audioFile ?: return@Button)
                            }
                        ) {
                            Text(
                                text = "Play"
                            )
                        }
                        Button(
                            onClick = {
                                player.stop()
                            }
                        ) {
                            Text(
                                text = "Stop play"
                            )
                        }
                    }
                }
            }
        }
    }
}