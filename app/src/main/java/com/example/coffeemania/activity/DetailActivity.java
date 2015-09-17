package com.example.coffeemania.activity;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.MenuItem;

import com.example.coffeemania.R;
import com.example.coffeemania.fragment.DetailFragment;

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on handset devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a {@link MainActivity}.
 * <p/>
 * This activity is mostly just a 'shell' activity containing nothing
 * more than a {@link DetailFragment}.
 */
public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Show the Up button in the action bar.
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(DetailFragment.COFFEESHOP_NAME, getIntent().getStringExtra(DetailFragment.COFFEESHOP_NAME));
            arguments.putString(DetailFragment.COFFEESHOP_LOCATION_LAT, getIntent().getStringExtra(DetailFragment.COFFEESHOP_LOCATION_LAT));
            arguments.putString(DetailFragment.COFFEESHOP_LOCATION_LNG, getIntent().getStringExtra(DetailFragment.COFFEESHOP_LOCATION_LNG));
            arguments.putInt(DetailFragment.COFFEESHOP_DISTANCE, getIntent().getIntExtra(DetailFragment.COFFEESHOP_DISTANCE, 0));
            arguments.putString(DetailFragment.COFFEESHOP_CITY, getIntent().getStringExtra(DetailFragment.COFFEESHOP_CITY));
            arguments.putString(DetailFragment.COFFEESHOP_ADDRESS, getIntent().getStringExtra(DetailFragment.COFFEESHOP_ADDRESS));
            arguments.putString(DetailFragment.COFFEESHOP_CATEGORY, getIntent().getStringExtra(DetailFragment.COFFEESHOP_CATEGORY));
            arguments.putInt(DetailFragment.COFFEESHOP_CHECKINS, getIntent().getIntExtra(DetailFragment.COFFEESHOP_CHECKINS, 0));
            arguments.putInt(DetailFragment.COFFEESHOP_USERS, getIntent().getIntExtra(DetailFragment.COFFEESHOP_USERS, 0));
            arguments.putInt(DetailFragment.COFFEESHOP_TIP, getIntent().getIntExtra(DetailFragment.COFFEESHOP_TIP, 0));
            arguments.putString(DetailFragment.COFFEESHOP_PHONE, getIntent().getStringExtra(DetailFragment.COFFEESHOP_PHONE));
            arguments.putString(DetailFragment.COFFEESHOP_FORMATTED_PHONE, getIntent().getStringExtra(DetailFragment.COFFEESHOP_FORMATTED_PHONE));
            DetailFragment fragment = new DetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.item_detail_container, fragment)
                    .commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. Use NavUtils to allow users
            // to navigate up one level in the application structure. For
            // more details, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            NavUtils.navigateUpTo(this, new Intent(this, MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}