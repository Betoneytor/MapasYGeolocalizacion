package com.example.googlemaps;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

public class MainActivity extends AppCompatActivity {
    private EditText txtLaD,txtLoD,txtLaO,txtLoO;
    private Button btnRuta;
    public static Intent intent = new Intent();

    private FusedLocationProviderClient fusedLocationClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnRuta=findViewById(R.id.btnRuta);
        txtLaD=findViewById(R.id.txtLaD);
        txtLoD=findViewById(R.id.txtLoD);
        txtLaO=findViewById(R.id.txtLaO);
        txtLoO=findViewById(R.id.txtLoO);

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {

                            txtLaO.setText(String.valueOf(location.getLatitude()));
                            txtLoO.setText(String.valueOf(location.getLongitude()));



                        }else {
                            Log.i("MiUltUbi", "No hay ubicaciòn, verifique si conexión y gps");}

                    }
                });
        btnRuta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Origen
                intent.putExtra("LatitudCasa", txtLaD.getText().toString());
                intent.putExtra("LongitudCasa",txtLoD.getText().toString());

                //Destino
                intent.putExtra("latituActual",txtLaO.getText().toString());
                intent.putExtra("longitudActual",txtLoO.getText().toString());


                Intent intent = new Intent(getApplicationContext(),MapsActivity.class);
                startActivity(intent);
            }
        });

    }
}
