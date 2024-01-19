package com.example.mymusicapp

import android.media.Image
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.net.toUri
import com.squareup.picasso.Picasso

class MusicPlayActivity : AppCompatActivity() {
        var totaltime:Int=0
    lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_music_play)

             val musictitle=findViewById<TextView>(R.id.musictext)
            val musicimg=findViewById<ImageView>(R.id.musicplayimage)
        val sk=findViewById<SeekBar>(R.id.seekBar)

            val playmusic=intent.getStringExtra("Music")
        if (playmusic != null) {
            mediaPlayer=MediaPlayer.create(this,playmusic.toUri())
            mediaPlayer.setVolume(1.0f,1.0f)

        }
        val btnplay=findViewById<ImageView>(R.id.Play)
        btnplay.setOnClickListener{
            mediaPlayer.start()
        }
        val btnpause=findViewById<ImageView>(R.id.pause)
        val btnreplay=findViewById<ImageView>(R.id.Loop)
        btnpause.setOnClickListener{
            mediaPlayer.pause()
        }
        btnreplay.setOnClickListener{
            mediaPlayer.seekTo(0)
            mediaPlayer.start()
        }


        val mt=intent.getStringExtra("MusicTitle")
        val mim=intent.getStringExtra("MusicCover")
        musictitle.text="PLAYING NOW"+ mt
        Picasso.get().load(mim).into(musicimg)
        totaltime=mediaPlayer.duration
        sk.max=totaltime
        sk.setOnSeekBarChangeListener(object:SeekBar.OnSeekBarChangeListener{
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {

                if(fromUser) {
                  mediaPlayer.seekTo(progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {

            }

        })
        val handle= Handler()
        handle.postDelayed(object:Runnable{
            override fun run() {
                sk.progress= mediaPlayer.currentPosition
                handle.postDelayed(this,1000)
            }

        },0) }



    }
