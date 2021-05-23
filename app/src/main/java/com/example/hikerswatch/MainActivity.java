package com.example.hikerswatch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    LocationManager locationManager;
    LocationListener locationListener;


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {


                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);


            }
        }
    }
        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
            TextView latitude=findViewById(R.id.latitude);
            TextView longitude=findViewById(R.id.longitude);
            TextView altitude=findViewById(R.id.altitude);
            TextView accuracy=findViewById(R.id.accuracy);
            TextView address=findViewById(R.id.address);
            Geocoder geocoder=new Geocoder(getApplicationContext(), Locale.getDefault());


        locationManager =(LocationManager) this.getSystemService(Context.LOCATION_SERVICE);

        locationListener= new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                TextView latitude=findViewById(R.id.latitude);
                TextView longitude=findViewById(R.id.longitude);
                TextView altitude=findViewById(R.id.altitude);
                TextView accuracy=findViewById(R.id.accuracy);
                TextView address=findViewById(R.id.address);
                latitude.setText("Latitude: "+location.getLatitude());
                longitude.setText("Longitude: "+location.getLongitude());
                altitude.setText("Altitude: "+location.getAltitude());
                accuracy.setText("Accurracy: "+location.getAccuracy()+"%");
                String s="";
                Geocoder geocoder=new Geocoder(getApplicationContext(), Locale.getDefault());
                try {
                    List<Address> addressList=geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
                    if (addressList!=null&&addressList.size()>0){
                        if (addressList.get(0).getSubThoroughfare()!=null)
                            s+=addressList.get(0).getSubThoroughfare()+",\n";
                        if (addressList.get(0).getThoroughfare()!=null)
                            s+=addressList.get(0).getThoroughfare()+",\n";
                        if (addressList.get(0).getSubLocality()!=null)
                            s+=addressList.get(0).getSubLocality()+",\n";
                        if (addressList.get(0).getLocality()!=null)
                            s+=addressList.get(0).getLocality()+",\n";
                        if (addressList.get(0).getSubAdminArea()!=null)
                            s+=addressList.get(0).getSubAdminArea()+",\n";
                        if (addressList.get(0).getAdminArea()!=null)
                            s+=addressList.get(0).getAdminArea()+",\n";
                        if (addressList.get(0).getPostalCode()!=null)
                            s+=addressList.get(0).getPostalCode()+",\n";
                        if (addressList.get(0).getCountryName()!=null)
                            s+=addressList.get(0).getCountryName()+",\n";
                        if (addressList.get(0).getAddressLine(1)!=null)
                            s+=addressList.get(0).getAddressLine(1)+",\n";
                        address.setText("Address: \n"+s);

                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},1);
        }
        else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
            Location llocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            latitude.setText("Latitude: "+llocation.getLatitude());
            longitude.setText("Longitude: "+llocation.getLongitude());
            altitude.setText("Altitude: "+llocation.getAltitude());
            accuracy.setText("Accurracy: "+llocation.getAccuracy());
            String s="";
            geocoder=new Geocoder(getApplicationContext(), Locale.getDefault());
            try {
                List<Address> addressList=geocoder.getFromLocation(llocation.getLatitude(),llocation.getLongitude(),1);
                if (addressList!=null&&addressList.size()>0){
                    if (addressList.get(0).getSubThoroughfare()!=null){
                        s+=addressList.get(0).getSubThoroughfare();
                    }
                    if (addressList.get(0).getSubThoroughfare()!=null)
                        s+=addressList.get(0).getSubThoroughfare()+",\n";
                    if (addressList.get(0).getThoroughfare()!=null)
                        s+=addressList.get(0).getThoroughfare()+",\n";
                    if (addressList.get(0).getSubLocality()!=null)
                        s+=addressList.get(0).getSubLocality()+",\n";
                    if (addressList.get(0).getLocality()!=null)
                        s+=addressList.get(0).getLocality()+",\n";
                    if (addressList.get(0).getSubAdminArea()!=null)
                        s+=addressList.get(0).getSubAdminArea()+",\n";
                    if (addressList.get(0).getAddressLine(1)!=null)
                        s+=addressList.get(0).getAddressLine(1)+",\n";
                    if (addressList.get(0).getAdminArea()!=null)
                        s+=addressList.get(0).getAdminArea()+",\n";
                    if (addressList.get(0).getPostalCode()!=null)
                        s+=addressList.get(0).getPostalCode()+",\n";
                    if (addressList.get(0).getCountryName()!=null)
                        s+=addressList.get(0).getCountryName()+",\n";
                    address.setText("Address: \n"+s);

                }
            } catch (IOException e) {
                e.printStackTrace();
            }




        }



    }
}
