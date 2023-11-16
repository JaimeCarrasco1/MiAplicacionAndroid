package com.santotomas.myaplicacion2;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.os.PowerManager;

public class ProximityService extends Service implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor proximitySensor;
    private PowerManager.WakeLock wakeLock;

    @Override
    public void onCreate() {
        super.onCreate();

        // Inicializar el SensorManager y el sensor de proximidad
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);

        // Inicializar el WakeLock para mantener la pantalla encendida
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PROXIMITY_SCREEN_OFF_WAKE_LOCK, "ProximityService:WakeLock");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Registrar el SensorEventListener
        sensorManager.registerListener(this, proximitySensor, SensorManager.SENSOR_DELAY_NORMAL);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        // Liberar recursos cuando se detenga el servicio
        sensorManager.unregisterListener(this);
        wakeLock.release();
        super.onDestroy();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // Manejar cambios en la proximidad
        float proximityValue = event.values[0];

        if (proximityValue < proximitySensor.getMaximumRange()) {
            // Objeto cerca, apagar la pantalla
            if (!wakeLock.isHeld()) {
                wakeLock.acquire();
            }
        } else {
            // Objeto no está cerca, encender la pantalla
            if (wakeLock.isHeld()) {
                wakeLock.release();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // No se usa en este ejemplo
    }

    @Override
    public IBinder onBind(Intent intent) {
        // No se necesita para un servicio sin enlace
        return null;
    }
}