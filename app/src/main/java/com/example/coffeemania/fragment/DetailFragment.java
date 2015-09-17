package com.example.coffeemania.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
            coffeeShopCheckins = getArguments().getInt(COFFEESHOP_CHECKINS);
            coffeeShopUsers = getArguments().getInt(COFFEESHOP_USERS);
            coffeeShopTip = getArguments().getInt(COFFEESHOP_TIP);
            coffeeShopPhone = getArguments().getString(COFFEESHOP_PHONE);
            coffeeShopFormattedPhone = getArguments().getString(COFFEESHOP_FORMATTED_PHONE);
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
            ((TextView) rootView.findViewById(R.id.detail_city)).setText(coffeeShopCity);
            ((TextView) rootView.findViewById(R.id.detail_address)).setText(coffeeShopAddress);
            ((TextView) rootView.findViewById(R.id.detail_checkins)).setText(String.valueOf(coffeeShopCheckins));
            ((TextView) rootView.findViewById(R.id.detail_users)).setText(String.valueOf(coffeeShopUsers));
            ((TextView) rootView.findViewById(R.id.detail_tip)).setText(String.valueOf(coffeeShopTip));
            ((TextView) rootView.findViewById(R.id.detail_lat)).setText(coffeeShopLocationLat);
            ((TextView) rootView.findViewById(R.id.detail_lng)).setText(coffeeShopLocationLng);
        }

        return rootView;
    }
}
