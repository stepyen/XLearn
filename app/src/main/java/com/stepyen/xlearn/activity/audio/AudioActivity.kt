package com.stepyen.xlearn.activity.audio

import com.stepyen.xlearn.R
import com.stepyen.xlearn.base.BasePageActivity
import kotlinx.android.synthetic.main.activity_audio.*
import java.io.File

/**
 * date：2020-02-15
 * author：stepyen
 * description：音频
 *
 */
class AudioActivity :BasePageActivity(){

    private var audioPath = ""
    companion object{

    }
    override fun initData() {
        audioPath = "${externalCacheDir.absolutePath}/audio/"
        File(audioPath).apply{
            if (!exists()) {
                mkdirs()
            }
        }
    }

    override fun initView() {
        addView(R.layout.activity_audio)

        initMedia()
        initAudio()
    }


    private fun initMedia() {

        val mediaRecorderManager = MediaManager(audioPath)
        btn_media_start.setOnClickListener {
            mediaRecorderManager.start()
        }
        btn_media_stop.setOnClickListener {
            mediaRecorderManager.stop()
        }
        btn_media_playback.setOnClickListener {
            mediaRecorderManager.playback()
        }
        btn_media_stop_playback.setOnClickListener {
            mediaRecorderManager.stopPlayback()
        }



    }

    private fun initAudio() {
    }

}