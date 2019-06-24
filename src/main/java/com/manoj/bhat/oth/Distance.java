package com.manoj.bhat.oth;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;

public class Distance extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener, LocationListener {

    GoogleApiClient googleApiClient;
    LocationRequest locationRequest;
    double latitude, longitude;
    double coordinates[] = new double[2];
    TextView textView, textView1;
    Button button;
    EditText ans;

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (googleApiClient.isConnected())
            googleApiClient.disconnect();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_distance);
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
        //latitude = QuestionDecide.coordinates[QuestionDecide.currentQues][0];
        //longitude = QuestionDecide.coordinates[QuestionDecide.currentQues][1];
        Intent intent = getIntent();
        coordinates = intent.getDoubleArrayExtra("coord");
        latitude = coordinates[0];
        longitude = coordinates[1];
        textView = (TextView) findViewById(R.id.textView2);
        textView1 = (TextView) findViewById(R.id.textView5);
        button = (Button) findViewById(R.id.button4);
        ans = (EditText) findViewById(R.id.pqr);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setInterval(1000);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(googleApiClient, locationRequest, this);
    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this,"connection suspended",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this,"connection failed",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLocationChanged(Location location) {
        double dist = distanceInKmBetweenEarthCoordinates(location.getLatitude(), location.getLongitude(), latitude, longitude)*1000;

        textView1.setText(""+(int)dist+"m");
        //Toast.makeText(this,""+latitude+" "+longitude,Toast.LENGTH_LONG).show();
        if (dist < 30) {
            textView.setText("Look For the code");
            button.setVisibility(View.VISIBLE);
            ans.setVisibility(View.VISIBLE);
        }
        else {
            textView.setText("Distance from checkpoint");
            button.setVisibility(View.GONE);
            ans.setVisibility(View.GONE);
        }
    }

    public double degreesToRadians(double degrees) {
        return degrees * Math.PI / 180;
    }

    public  double distanceInKmBetweenEarthCoordinates(double lat1, double lon1, double lat2, double lon2) {
        double earthRadiusKm = 6371;

        double dLat = degreesToRadians(lat2-lat1);
        double dLon = degreesToRadians(lon2-lon1);

        lat1 = degreesToRadians(lat1);
        lat2 = degreesToRadians(lat2);

        double a = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        return earthRadiusKm * c;
    }

    public void subkey(View view) {
        String key = ans.getText().toString();
        int flag=0;
        if (key.equals(QuestionDecide.c[QuestionDecide.currentQues])) {
            if (QuestionDecide.id[QuestionDecide.ver][7] == 0) {
                Intent intent = new Intent();
                startActivity(intent);
            }
            Intent intent = new Intent(this,Question.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this,"Incorrect key",Toast.LENGTH_LONG).show();
        }
    }
}
