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

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link MainActivity}
 * in two-pane mode (on tablets) or a {@link DetailActivity}
 * on handsets.
 */
public class DetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String COFFEESHOP_NAME = "coffeeshop_name";
    public static final String COFFEESHOP_LOCATION_LAT = "coffeeshop_location_lat";
    public static final String COFFEESHOP_LOCATION_LNG = "coffeeshop_location_lng";
    public static final String COFFEESHOP_DISTANCE = "coffeeshop_distance";
    public static final String COFFEESHOP_CITY = "coffeeshop_city";
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
    private int coffeeShopDistance;
    private String coffeeShopCity;
    private String coffeeShopAddress;
    private String coffeeShopCategory;
    private int coffeeShopCheckins;
    private int coffeeShopUsers;
    private int coffeeShopTip;
    private String coffeeShopPhone;
    private String coffeeShopFormattedPhone;
    private String coffeeShopUrl;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public DetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(COFFEESHOP_NAME)) {
            coffeeShopName = getArguments().getString(COFFEESHOP_NAME);
            coffeeShopLocationLat = getArguments().getString(COFFEESHOP_LOCATION_LAT);
            coffeeShopLocationLng = getArguments().getString(COFFEESHOP_LOCATION_LNG);
            coffeeShopDistance = getArguments().getInt(COFFEESHOP_DISTANCE, 0);
            coffeeShopCity = getArguments().getString(COFFEESHOP_CITY);
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

        // Show the dummy content as text in a TextView.
        if (coffeeShopName != null) {
            ((TextView) rootView.findViewById(R.id.detail_name)).setText(coffeeShopName);
            ((TextView) rootView.findViewById(R.id.detail_category)).setText(coffeeShopCategory);
            ((TextView) rootView.findViewById(R.id.detail_address)).setText(coffeeShopAddress);
            ((TextView) rootView.findViewById(R.id.detail_checkins)).setText(String.valueOf(coffeeShopCheckins));
            ((TextView) rootView.findViewById(R.id.detail_users)).setText(String.valueOf(coffeeShopUsers));
            ((TextView) rootView.findViewById(R.id.detail_tip)).setText(String.valueOf(coffeeShopTip));
        }

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

        return rootView;
    }

    private void showToast(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
