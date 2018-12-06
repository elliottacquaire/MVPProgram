/**/

package com.li.mvpprogram.utils.audio;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.text.TextUtils;
import com.li.mvpprogram.BaseApplication;
import com.li.mvpprogram.utils.ToastUtils;

import java.io.IOException;

/**
 * [语音播放管理类]
 */
public class AudioPlayManager {

    private MediaPlayer mediaPlayer;

    private AudioPlayManager() {
    }

    public static AudioPlayManager getInstance() {
        return SingletonHolder.instance;
    }

    public void startPlay(String url, MediaPlayer.OnCompletionListener completionListener) {
        startPlay(url, completionListener, null);
    }

    /**
     * 播放在线音频, 带播放完成回调
     *
     * @param url
     */
    public void startPlay(String url, MediaPlayer.OnCompletionListener completionListener, MediaPlayer.OnErrorListener errorListener) {
        if (TextUtils.isEmpty(url)) {
            ToastUtils.show(BaseApplication.getInstance().getApplicationContext(), "该语音无法播放！");
            return;
        }

        stopPlay();

        if (mediaPlayer == null) {
            mediaPlayer = new MediaPlayer();
        }

        try {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(url);
            if (completionListener != null) {
                mediaPlayer.setOnCompletionListener(completionListener); // 设置播放完成监听
            }
            if (errorListener != null) {
                mediaPlayer.setOnErrorListener(errorListener);
            }

//            mediaPlayer.setOnPreparedListener(mp -> mp.start());
            // 异步播放在线音频流，防止阻塞UI线程
            mediaPlayer.prepareAsync();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 播放在线音频,不带播放完成回调
     *
     * @param url
     */
    public void startPlay(String url) {
        startPlay(url, null);
    }


    /**
     * 停止播放音频
     */
    public void stopPlay() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.reset();
            mediaPlayer.release();
            mediaPlayer = null;
        }

    }

    /**
     * 当前是否正在播放
     *
     * @return
     */
    public boolean isPlaying() {
        if (mediaPlayer == null) {
            return false;
        }
        return mediaPlayer.isPlaying();
    }


    static class SingletonHolder {
        static AudioPlayManager instance = new AudioPlayManager();

        SingletonHolder() {

        }
    }
}
