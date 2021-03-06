package com.example.coffeemania.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coffeemania.activity.DetailActivity;
import com.example.coffeemania.activity.MainActivity;
import com.example.coffeemania.R;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link MainActivity}
 * in two-pane mode (on tablets) or a {@link DetailActivity}
 * on handsets.
 */
public class DetailFragment extends Fragment {
    // Constants to hold fragment argument keys.
    public static final String COFFEESHOP_NAME = "coffeeshop_name";
    public static final String COFFEESHOP_LOCATION_LAT = "coffeeshop_location_lat";
    public static final String COFFEESHOP_LOCATION_LNG = "coffeeshop_location_lng";
    public static final String COFFEESHOP_DISTANCE = "coffeeshop_distance";
    public static final String COFFEESHOP_ADDRESS = "coffeeshop_address";
    public static final String COFFEESHOP_CATEGORY = "coffeeshop_category";
    public static final String COFFEESHOP_CHECKINS = "coffeeshop_checkins";
    public static final String COFFEESHOP_USERS = "coffeeshop_users";
    public static final String COFFEESHOP_TIP = "coffeeshop_tip";
    public static final String COFFEESHOP_PHONE = "coffeeshop_phone";
    public static final String COFFEESHOP_FORMATTED_PHONE = "coffeeshop_formatted_phone";
    public static final String COFFEESHOP_URL = "coffeeshop_url";

    private String coffeeShopName;
    private String coffeeShopLocationLat;
    private String coffeeShopLocationLng;
    private String coffeeShopAddress;
    private String coffeeShopCategory;
    private int coffeeShopCheckins;
    private int coffeeShopUsers;
    private int coffeeShopTip;
    private String coffeeShopPhone;
    private String coffeeShopFormattedPhone;
    private String coffeeShopUrl;

    private MapView mapView;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Get and save data passed from DetailActivity or MainActivity.
        if (getArguments().containsKey(COFFEESHOP_NAME)) {
            coffeeShopName = getArguments().getString(COFFEESHOP_NAME);
            coffeeShopLocationLat = getArguments().getString(COFFEESHOP_LOCATION_LAT);
            coffeeShopLocationLng = getArguments().getString(COFFEESHOP_LOCATION_LNG);
            coffeeShopAddress = getArguments().getString(COFFEESHOP_ADDRESS);
            coffeeShopCategory = getArguments().getString(COFFEESHOP_CATEGORY);
            coffeeShopCheckins = getArguments().getInt(COFFEESHOP_CHECKINS, 0);
            coffeeShopUsers = getArguments().getInt(COFFEESHOP_USERS, 0);
            coffeeShopTip = getArguments().getInt(COFFEESHOP_TIP, 0);
            coffeeShopPhone = getArguments().getString(COFFEESHOP_PHONE);
            coffeeShopFormattedPhone = getArguments().getString(COFFEESHOP_FORMATTED_PHONE);
            coffeeShopUrl = getArguments().getString(COFFEESHOP_URL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

        // If coffeeshop name is passed and not null, set all related data to appropriate views.
        if (coffeeShopName != null) {
            ((TextView) rootView.findViewById(R.id.detail_name)).setText(coffeeShopName);
            ((TextView) rootView.findViewById(R.id.detail_category)).setText(coffeeShopCategory);
            ((TextView) rootView.findViewById(R.id.detail_address)).setText(coffeeShopAddress);
            ((TextView) rootView.findViewById(R.id.detail_checkins)).setText(String.valueOf(coffeeShopCheckins));
            ((TextView) rootView.findViewById(R.id.detail_users)).setText(String.valueOf(coffeeShopUsers));
            ((TextView) rootView.findViewById(R.id.detail_tip)).setText(String.valueOf(coffeeShopTip));
        }

        // Make address data clickable, and when clicked it is opened by Maps application.
        rootView.findViewById(R.id.detail_group_address).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                String location = coffeeShopLocationLat + "," + coffeeShopLocationLng;
                String label = Uri.encode(coffeeShopName);
                intent.setData(Uri.parse("geo:0,0?q=" + location + "(" + label + ")"));
                startActivity(intent);
            }
        });

        // Not all coffeeshops have phone numbers, if they have we make it visible (by default, it
        // is hidden in the layout), set its number, and make it clickable (i.e. when clicked, the
        // number will be passed to Dialer app).
        if (coffeeShopPhone != null) {
            ((TextView) rootView.findViewById(R.id.detail_phone)).setText(coffeeShopFormattedPhone);
            rootView.findViewById(R.id.detail_group_phone).setVisibility(View.VISIBLE);
            rootView.findViewById(R.id.detail_group_phone).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + coffeeShopPhone));
                    startActivity(intent);
                }
            });
        }

        // Not all coffeeshops have web-site, if they have we make it visible (by default, it
        // is hidden in the layout), set its URL, and make it clickable (i.e. when clicked, the
        // URL will be passed to browser app).
        if (coffeeShopUrl != null) {
            ((TextView) rootView.findViewById(R.id.detail_url)).setText(coffeeShopUrl);
            rootView.findViewById(R.id.detail_group_url).setVisibility(View.VISIBLE);
            rootView.findViewById(R.id.detail_group_url).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse(coffeeShopUrl));
                    startActivity(intent);
                }
            });
        }

        // If the venue has number of checked ins, we show it via Toast upon clicking.
        rootView.findViewById(R.id.detail_group_checkins).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String checkinMessage = "";
                if (coffeeShopCheckins == 0) {
                    checkinMessage = "No one checked in at this venue yet";
                } else if (coffeeShopCheckins == 1) {
                    checkinMessage = "One person checked in at this venue";
                } else {
                    checkinMessage = coffeeShopCheckins + " people checked in at this venue";
                }
                showToast(checkinMessage);
            }
        });

        // If the venue has number of checked in users, we show it via Toast upon clicking.
        rootView.findViewById(R.id.detail_group_users).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userMessage = "";
                if (coffeeShopUsers == 0) {
                    userMessage = "No user checked in at this venue yet";
                } else if (coffeeShopUsers == 1) {
                    userMessage = "One user checked in at this venue";
                } else {
                    userMessage = coffeeShopUsers + " users checked in at this venue";
                }
                showToast(userMessage);
            }
        });

        // If the venue has any info about tips, we show it via Toast upon clicking.
        rootView.findViewById(R.id.detail_group_tip).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tipMessage = "";
                if (coffeeShopTip == 0) {
                    tipMessage = "No user tipped at this venue yet";
                } else if (coffeeShopTip == 1) {
                    tipMessage = "One user tipped at this venue";
                } else {
                    tipMessage = "Users tipped " + coffeeShopTip + " times at this venue";
                }
                showToast(tipMessage);
            }
        });

        // Initialize MapView and GoogleMap.
        mapView = (MapView) rootView.findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);
        GoogleMap googleMap = mapView.getMap();
        googleMap.getUiSettings().setMyLocationButtonEnabled(false);
        try {
            MapsInitializer.initialize(getContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Get new location and zoom, and pass it to GoogleMap.
        LatLng location = new LatLng(Double.parseDouble(coffeeShopLocationLat),
                Double.parseDouble(coffeeShopLocationLng));
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(location, 15);
        googleMap.moveCamera(cameraUpdate);
        googleMap.addMarker(new MarkerOptions().position(location).title(coffeeShopName));

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        // Handle onResume of MapView.
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        // Handle onPause of MapView.
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Handle onDestroy of MapView.
        mapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        // On low memory, take appropriate actions for MapView.
        mapView.onLowMemory();
    }

    // Helper method to show Toast message.
    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
