package com.example.coffeemania.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.coffeemania.misc.Coffeeshop;
import com.example.coffeemania.R;
import com.example.coffeemania.fragment.DetailFragment;
import com.example.coffeemania.fragment.MainFragment;


/**
 * An activity representing a list of Items. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link DetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p/>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link MainFragment} and the item details
 * (if present) is a {@link DetailFragment}.
 * <p/>
 * This activity also implements the required
 * {@link MainFragment.Callbacks} interface
 * to listen for item selections.
 */
public class MainActivity extends AppCompatActivity implements MainFragment.Callbacks {

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-large and
            // res/values-sw600dp). If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;

            // In two-pane mode, list items should be given the
            // 'activated' state when touched.
            ((MainFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.item_list))
                    .setActivateOnItemClick(true);
        }

        // TODO: If exposing deep links into your app, handle intents here.
    }

    /**
     * Callback method from {@link MainFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(Coffeeshop c) {
        if (mTwoPane) {
            // In two-pane mode, show the detail view in this activity by
            // adding or replacing the detail fragment using a
            // fragment transaction.
            Bundle arguments = new Bundle();
            arguments.putString(DetailFragment.COFFEESHOP_NAME, c.getName());
            arguments.putString(DetailFragment.COFFEESHOP_LOCATION_LAT, c.getLat());
            arguments.putString(DetailFragment.COFFEESHOP_LOCATION_LNG, c.getLng());
            arguments.putInt(DetailFragment.COFFEESHOP_DISTANCE, c.getDistance());
            arguments.putString(DetailFragment.COFFEESHOP_CITY, c.getCity());
            arguments.putString(DetailFragment.COFFEESHOP_ADDRESS, c.getFormattedAddress());
            arguments.putString(DetailFragment.COFFEESHOP_CATEGORY, c.getCategory());
            arguments.putInt(DetailFragment.COFFEESHOP_CHECKINS, c.getCheckinsCount());
            arguments.putInt(DetailFragment.COFFEESHOP_USERS, c.getUsersCount());
            arguments.putInt(DetailFragment.COFFEESHOP_TIP, c.getTipCount());
            arguments.putString(DetailFragment.COFFEESHOP_PHONE, c.getPhone());
            arguments.putString(DetailFragment.COFFEESHOP_FORMATTED_PHONE, c.getFormattedPhone());

            DetailFragment fragment = new DetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit();

        } else {
            // In single-pane mode, simply start the detail activity
            // for the selected item ID.
            Intent detailIntent = new Intent(this, DetailActivity.class);
            detailIntent.putExtra(DetailFragment.COFFEESHOP_NAME, c.getName());
            detailIntent.putExtra(DetailFragment.COFFEESHOP_LOCATION_LAT, c.getLat());
            detailIntent.putExtra(DetailFragment.COFFEESHOP_LOCATION_LNG, c.getLng());
            detailIntent.putExtra(DetailFragment.COFFEESHOP_DISTANCE, c.getDistance());
            detailIntent.putExtra(DetailFragment.COFFEESHOP_CITY, c.getCity());
            detailIntent.putExtra(DetailFragment.COFFEESHOP_ADDRESS, c.getFormattedAddress());
            detailIntent.putExtra(DetailFragment.COFFEESHOP_CATEGORY, c.getCategory());
            detailIntent.putExtra(DetailFragment.COFFEESHOP_CHECKINS, c.getCheckinsCount());
            detailIntent.putExtra(DetailFragment.COFFEESHOP_USERS, c.getUsersCount());
            detailIntent.putExtra(DetailFragment.COFFEESHOP_TIP, c.getTipCount());
            detailIntent.putExtra(DetailFragment.COFFEESHOP_PHONE, c.getPhone());
            detailIntent.putExtra(DetailFragment.COFFEESHOP_FORMATTED_PHONE, c.getFormattedPhone());
            startActivity(detailIntent);
        }
    }
}
