/*************************************************************************************************************************
 * Class Name: MapsActivity.java                                                                                        *
 *                                                                                                                       *
 * Purpose: This is the map fragment class that displays a map with the location of the restaurant marked                                                                                         *
 *                                                                                                                       *
 *************************************************************************************************************************/

package edu.niu.android.cuisineapp;

import edu.niu.android.cuisineapp.databinding.ActivityMapsBinding;
import androidx.fragment.app.FragmentActivity;
import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import androidx.core.content.ContextCompat;
import androidx.core.app.ActivityCompat;
import android.Manifest;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import android.content.pm.PackageManager;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback
{

    private GoogleMap mMap;
    private static final int LOCATION_REQUEST_CODE = 101;
    private ActivityMapsBinding binding;
    String restaurantName = null;
    DataBaseManager db = new DataBaseManager(this);
    edu.niu.android.cuisineapp.Location restaurantLocation = null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,0);
        restaurantName = getIntent().getStringExtra("name");
        restaurantLocation = db.getLocationFromDatabase(restaurantName);
        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        if (mMap != null) {
            int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);

            if (permission == PackageManager.PERMISSION_GRANTED) {
                mMap.setMyLocationEnabled(true);

                if (restaurantLocation != null) {
                    try {
                        double latitude = Double.parseDouble(restaurantLocation.getLatitude());
                        double longitude = Double.parseDouble(restaurantLocation.getLongitude());
                        Log.d("longitude", String.valueOf(longitude));
                        Log.d("latitude", String.valueOf(latitude));
                        LatLng locationLatLng = new LatLng(latitude, longitude);

                        CameraUpdate update = CameraUpdateFactory.newLatLngZoom(locationLatLng, 14f);
                        mMap.moveCamera(update);

                        // Add a marker to the map at the restaurant location
                        mMap.addMarker(new MarkerOptions().position(locationLatLng).title(restaurantName));
                    } catch (NumberFormatException e) {
                        Toast.makeText(this, "Invalid latitude or longitude", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(this, "Restaurant location not found", Toast.LENGTH_SHORT).show();
                }
            } else {
                requestPermission(Manifest.permission.ACCESS_FINE_LOCATION, LOCATION_REQUEST_CODE);
            }
        }
    }


    protected void requestPermission(String permissionType, int requestCode)
    {

        ActivityCompat.requestPermissions(this,
                new String[]{permissionType}, requestCode
        );

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode)
        {
            case LOCATION_REQUEST_CODE:
            {

                if (grantResults.length == 0
                        || grantResults[0] != PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(this,
                            "Unable to show location - permission required", Toast.LENGTH_LONG).show();
                }
                else
                {
                    SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                            .findFragmentById(R.id.map);
                    mapFragment.getMapAsync(this);
                }
            }
        }
    }
}