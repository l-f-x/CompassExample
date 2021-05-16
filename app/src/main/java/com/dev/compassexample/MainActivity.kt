package com.dev.compassexample

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import com.dev.compassexample.databinding.ActivityMainBinding

private lateinit var binding: ActivityMainBinding
private var sensor: SensorManager?=null
private var degree:Float?=0f
class MainActivity : AppCompatActivity(), SensorEventListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //инициализация всех сенсоров
         sensor= getSystemService(SENSOR_SERVICE) as SensorManager
    }

    override fun onResume() {
        super.onResume()
        sensor?.registerListener(this, sensor?.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_FASTEST)
    }

    override fun onPause() {
        super.onPause()
        sensor?.unregisterListener(this)
    }
    override fun onSensorChanged(event: SensorEvent?) {
        var degreeNow= event?.values?.get(0)
        val rotate=RotateAnimation(degree!!,(-degreeNow!!),Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f)
        rotate.duration=200
        rotate.fillAfter=true
        degree=-degreeNow
        binding.compassImgview.startAnimation(rotate)
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
    }
}