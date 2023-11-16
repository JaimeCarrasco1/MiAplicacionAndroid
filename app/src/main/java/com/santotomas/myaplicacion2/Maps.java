package com.santotomas.myaplicacion2;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceManager;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import android.Manifest;
import android.view.View;
import android.widget.Button;

import org.osmdroid.api.IMapController;
import org.osmdroid.config.Configuration;
import org.osmdroid.library.BuildConfig;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

public class Maps extends FragmentActivity {
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 1;
    private MapView mapView;
    private MyLocationNewOverlay myLocationOverlay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configuración de osmdroid
        Configuration.getInstance().load(this, PreferenceManager.getDefaultSharedPreferences(this));
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);

        setContentView(R.layout.activity_maps);

        mapView = findViewById(R.id.mapview);
        mapView.setMultiTouchControls(true);

        // Ubicación para Street View en OSM (Latitud y Longitud de ejemplo)
        GeoPoint streetViewLocation = new GeoPoint(-33.663459, -70.924866);

        // Marcador para indicar la ubicación de Street View
        Marker streetViewMarker = new Marker(mapView);
        streetViewMarker.setPosition(streetViewLocation);
        streetViewMarker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        streetViewMarker.setTitle("Quality BarberShop");
        streetViewMarker.setTextLabelFontSize(40);
        mapView.getOverlays().add(streetViewMarker);

        // Centrar el mapa en la ubicación de Street View
        mapView.getController().setCenter(streetViewLocation);
        mapView.getController().setZoom(15.0);

        // Inicializar el overlay de ubicación actual
        GpsMyLocationProvider locationProvider = new GpsMyLocationProvider(this);
        myLocationOverlay = new MyLocationNewOverlay(locationProvider, mapView);
        mapView.getOverlays().add(myLocationOverlay);

        // Verificar y solicitar permisos de ubicación
        if (checkLocationPermission()) {
            zoomToMyLocation();
        } else {
            requestLocationPermission();
        }

        Button btnZoomToMyLocation = findViewById(R.id.btnZoomToMyLocation);
        btnZoomToMyLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                zoomToMyLocation();
            }
        });
    }

    private void zoomToMyLocation() {
        // Verificar y solicitar permisos de ubicación
        if (checkLocationPermission()) {
            myLocationOverlay.enableMyLocation();
            myLocationOverlay.enableFollowLocation();

            // Obtener la ubicación actual
            GeoPoint myLocation = myLocationOverlay.getMyLocation();

            if (myLocation != null) {
                // Centrar el mapa en la ubicación actual y aplicar un zoom
                IMapController mapController = mapView.getController();
                mapController.setCenter(myLocation);
                mapController.setZoom(17.1); // Puedes ajustar el nivel de zoom según tus preferencias
            }
        } else {
            requestLocationPermission();
        }
    }

    private boolean checkLocationPermission() {
        int fineLocationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        int coarseLocationPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        return fineLocationPermission == PackageManager.PERMISSION_GRANTED &&
                coarseLocationPermission == PackageManager.PERMISSION_GRANTED;
    }

    private void requestLocationPermission() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                REQUEST_PERMISSIONS_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSIONS_REQUEST_CODE) {
            if (checkLocationPermission()) {
                // Permiso concedido, configurar el mapa con la ubicación actual
                zoomToMyLocation();
            } else {
                // Permiso no concedido, manejar según sea necesario
            }
        }
    }

    public void RegresarMain (View view){
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }
}
