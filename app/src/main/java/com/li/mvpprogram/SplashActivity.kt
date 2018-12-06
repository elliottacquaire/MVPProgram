package com.li.mvpprogram

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.li.mvpprogram.patternLock.PatternLockActivity
import kotlinx.android.synthetic.main.activity_main_main.*

class SplashActivity : AppCompatActivity() , View.OnClickListener{
    override fun onClick(v: View?) {
        when(v?.id){
            R.id.button -> jumpActivity()
            R.id.button1 -> jumpPatternLockActivity()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_main)
        val buttonStr = stringFromJNI() + stringFromJNIToNative()
        button.text = stringFromJNI()
        button.setOnClickListener(this)
        button1.text = stringFromJNIToNative()
        button1.setOnClickListener(this)

    }

    private fun jumpActivity(){
        val intent = Intent()
        intent.setClass(this,MainActivity().javaClass)
        startActivity(intent)
    }

    private fun jumpPatternLockActivity(){
        val intent = Intent()
        intent.setClass(this, PatternLockActivity().javaClass)
        startActivity(intent)
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    external fun stringFromJNIToNative(): String

    companion object {

        // Used to load the 'native-lib' library on application startup.
        init {
            System.loadLibrary("native-lib")
            System.loadLibrary("native_hello")
        }
    }
}
