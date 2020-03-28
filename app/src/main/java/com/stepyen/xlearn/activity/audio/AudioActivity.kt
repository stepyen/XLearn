package com.stepyen.xlearn.activity.audio

import com.stepyen.common.utils.L
import com.stepyen.xlearn.DataResouceManager
import com.stepyen.xlearn.R
import com.stepyen.common.BasePageActivity
import kotlinx.android.synthetic.main.activity_audio.*
import java.io.File
import java.io.FileOutputStream

/**
 * date：2020-02-15
 * author：stepyen
 * description：音频
 *
 *
 *
 */
class AudioActivity :BasePageActivity(){
    // 录音地址
    private var audioPath = ""
    var mp3Path:String = ""
    var mp4Path:String = ""


    private val mediaPlayerManager = MediaPlayerManager()

    override fun initData() {
        audioPath = "${externalCacheDir?.absolutePath}/audio/"
        File(audioPath).apply{
            if (!exists()) {
                mkdirs()
            }
        }

        // 从asset中复制资源到sd卡下
        DataResouceManager().apply {
            mp3Path = copyMP3FromAssets()
            mp4Path = copyMP4FromAssets()
        }

    }

    override fun initView() {
        addView(R.layout.activity_audio)

        initMedia()
        initAudio()
        initMediaPlayer()
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


    private fun initMediaPlayer() {

        btn_MediaPlayer_start.setOnClickListener {
            mediaPlayerManager.startFileDataSource(mp3Path)
        }

        btn_MediaPlayer_pause.setOnClickListener {
            mediaPlayerManager.pause()
        }

        btn_MediaPlayer_resume.setOnClickListener {
            mediaPlayerManager.resume()
        }

        btn_MediaPlayer_release.setOnClickListener {
            mediaPlayerManager.release()
        }

    }

    override fun onResume() {
        super.onResume()
        mediaPlayerManager.resume()
    }

    override fun onPause() {
        super.onPause()
        mediaPlayerManager.pause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayerManager.release()
    }

}