package com.example.sensortryout

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(),SensorEventListener {

    private lateinit var sensorManager : SensorManager
    private lateinit var accelerometer : Sensor
    private  lateinit var xAxisText: TextView
    private  lateinit var yAxisText: TextView
    private  lateinit var zAxisText: TextView

    var TAG: String="Mainactivity"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        xAxisText = findViewById(R.id.xAxis)
        yAxisText = findViewById(R.id.yAxis)
        zAxisText = findViewById(R.id.zAxis)


        Log.d(TAG,"onCreate: Inititilazing sensors")
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        sensorManager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_NORMAL)
        Log.d(TAG,"Registered accelerometer")
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        Log.d(TAG,"Error: no onAccuracyChanged method implemented!")
    }

    override fun onSensorChanged(event: SensorEvent?) {
        xAxisText.text ="X axis:"+ event!!.values[0].toString()
        yAxisText.text = "Y axis:"+event.values[1].toString()
        zAxisText.text = "Z axis:"+event.values[2].toString()

    }

    override fun onResume() {
        super.onResume()
        sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
    }

    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }
}
