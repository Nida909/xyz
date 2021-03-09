package com.example.chatting;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RiderMain extends AppCompatActivity implements View.OnClickListener {
TextView mLoc,dropLoc,cName,contact,tt;
Button btn;
    FirebaseDatabase database;
    DatabaseReference ref;
    private static final int RC_LOCATION_REQUEST = 1234;
    private int RC_LOCATION_ON_REQUEST = 1235;
    private Button mTrackButton;
    private LocationRequest locationRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rider_main);

        checkLocationSettingsRequest();
        setUpLocationRequest();
        mLoc=(TextView)findViewById(R.id.PickUp);
        dropLoc=(TextView)findViewById(R.id.DropOff);
        cName=(TextView)findViewById(R.id.CustomerName);
       contact=(TextView)findViewById(R.id.Contact);
        tt=(TextView)findViewById(R.id.txt1);
        btn=(Button)findViewById(R.id.Ride);
        btn.setOnClickListener(this);
        database = FirebaseDatabase.getInstance();
        ref = database.getReference();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.hasChild("Orderlocation")) {
                   btn.setVisibility(View.VISIBLE);
                   tt.setText("Order Details");
contact.setText("Customer Contact = "+snapshot.child("Orderlocation").child("Location").child("CustomerContact").getValue());
mLoc.setText("Pick Up Location = "+snapshot.child("Orderlocation").child("Location").child("MilkmanLoc").getValue());
dropLoc.setText("Drop Off Location = "+snapshot.child("Orderlocation").child("Location").child("DropOffLoc").getValue());
cName.setText("Customer Name = "+snapshot.child("Orderlocation").child("Location").child("CustomerName").getValue());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    @Override
    public void onClick(View v) {
        if(R.id.Ride == v.getId()){

            startService(new Intent(this, LocatioTrackerService.class));
            Intent intent=new Intent(this, MapsActivity1.class);
            startActivity(intent);
        }
    }
    private void setUpLocationRequest() {
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(10000);
        locationRequest.setFastestInterval(5000);
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    }
    private void startLocationUpdates() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION }, RC_LOCATION_REQUEST );
        }else {
            //startService(new Intent(this, LocationTrackerService.class));
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_LOCATION_ON_REQUEST && resultCode == Activity.RESULT_OK){
            Log.e("tag", "Resolution done");
            startLocationUpdates();
        }
    }
    private void checkLocationSettingsRequest(){

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        SettingsClient locationClient = LocationServices.getSettingsClient(this);
        Task<LocationSettingsResponse> locationSettings = locationClient.checkLocationSettings(builder.build());

        locationSettings.addOnCompleteListener(new OnCompleteListener<LocationSettingsResponse>() {
            @Override
            public void onComplete(@NonNull Task<LocationSettingsResponse> task) {
                if(!task.isSuccessful()){
                    Log.e("tag", "Exception" + task.getException().getMessage());
                    if(task.getException() instanceof ResolvableApiException){
                        // show permission request to user
                        try {
                            ResolvableApiException resolvable = (ResolvableApiException) task.getException();
                            resolvable.startResolutionForResult(RiderMain.this, RC_LOCATION_ON_REQUEST);
                        } catch (IntentSender.SendIntentException sendEx) {
                            // Ignore the error.
                        }
                    }
                }else{
                    startLocationUpdates();
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == RC_LOCATION_REQUEST && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            startLocationUpdates();
        }
    }
}