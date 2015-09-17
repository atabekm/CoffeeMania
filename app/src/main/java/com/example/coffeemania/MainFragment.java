package com.example.coffeemania;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A list fragment representing a list of Items. This fragment
 * also supports tablet devices by allowing list items to be given an
 * 'activated' state upon selection. This helps indicate which item is
 * currently being viewed in a {@link DetailFragment}.
 * <p/>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class MainFragment extends ListFragment {

    /**
     * The serialization (saved instance state) Bundle key representing the
     * activated item position. Only used on tablets.
     */
    private static final String STATE_ACTIVATED_POSITION = "activated_position";

    /**
     * The fragment's current callback object, which is notified of list item
     * clicks.
     */
    private Callbacks mCallbacks = sDummyCallbacks;

    // The current activated item position. Only used on tablets.
    private int mActivatedPosition = ListView.INVALID_POSITION;

    // List of Coffeeshop items
    private List<Coffeeshop> list;

    private CoffeeshopAdapter adapter;

    private final String URL = "https://api.foursquare.com/v2/venues/search?client_id=ACAO2JPKM1MXHQJCK45IIFKRFR2ZVL0QASMCBCG5NPJQWF2G&client_secret=YZCKUYJ1WHUV2QICBXUBEILZI1DMPUIDP5SHV043O04FKBHL&v=20140806&m=swarm&query=coffee&";

    /**
     * A callback interface that all activities containing this fragment must
     * implement. This mechanism allows activities to be notified of item
     * selections.
     */
    public interface Callbacks {
        /**
         * Callback for when an item has been selected.
         */
        public void onItemSelected(Coffeeshop c);
    }

    /**
     * A dummy implementation of the {@link Callbacks} interface that does
     * nothing. Used only when this fragment is not attached to an activity.
     */
    private static Callbacks sDummyCallbacks = new Callbacks() {
        @Override
        public void onItemSelected(Coffeeshop c) {
        }
    };

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public MainFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list = new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        adapter = new CoffeeshopAdapter(getContext(), list);
        setListAdapter(adapter);

        fetchCoffeeShops("-33.814620,151.106089");

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Restore the previously serialized activated item position.
        if (savedInstanceState != null
                && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION)) {
            setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
        }
    }

    @Override
    public void onAttach(Context activity) {
        super.onAttach(activity);

        // Activities containing this fragment must implement its callbacks.
        if (!(activity instanceof Callbacks)) {
            throw new IllegalStateException("Activity must implement fragment's callbacks.");
        }

        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();

        // Reset the active callbacks interface to the dummy implementation.
        mCallbacks = sDummyCallbacks;
    }

    @Override
    public void onListItemClick(ListView listView, View view, int position, long id) {
        super.onListItemClick(listView, view, position, id);

        // Notify the active callbacks interface (the activity, if the
        // fragment is attached to one) that an item has been selected.
        mCallbacks.onItemSelected(list.get(position));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mActivatedPosition != ListView.INVALID_POSITION) {
            // Serialize and persist the activated item position.
            outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
        }
    }

    /**
     * Turns on activate-on-click mode. When this mode is on, list items will be
     * given the 'activated' state when touched.
     */
    public void setActivateOnItemClick(boolean activateOnItemClick) {
        // When setting CHOICE_MODE_SINGLE, ListView will automatically
        // give items the 'activated' state when touched.
        getListView().setChoiceMode(activateOnItemClick
                ? ListView.CHOICE_MODE_SINGLE
                : ListView.CHOICE_MODE_NONE);
    }

    private void setActivatedPosition(int position) {
        if (position == ListView.INVALID_POSITION) {
            getListView().setItemChecked(mActivatedPosition, false);
        } else {
            getListView().setItemChecked(position, true);
        }

        mActivatedPosition = position;
    }

    private void fetchCoffeeShops(final String lonLng) {
        JsonObjectRequest request = new JsonObjectRequest(URL + "ll=" + lonLng, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject resp = response.getJSONObject("response");
                    JSONArray venues = resp.getJSONArray("venues");
                    for (int i = 0; i < venues.length(); i++) {
                        Coffeeshop c = new Coffeeshop();
                        JSONObject item = venues.getJSONObject(i);

                        c.setName(item.getString("name"));

                        if (item.has("contact")) {
                            JSONObject contact = item.getJSONObject("contact");

                            if (contact.has("phone")) {
                                c.setPhone(contact.getString("phone"));
                            }

                            if (contact.has("formattedPhone")) {
                                c.setFormattedPhone(contact.getString("formattedPhone"));
                            }
                        }

                        JSONObject location = item.getJSONObject("location");
                        c.setLat(location.getString("lat"));
                        c.setLng(location.getString("lng"));
                        c.setDistance(location.getInt("distance"));
                        if (location.has("city")) {
                            c.setCity(location.getString("city"));
                        }
                        JSONArray address = location.getJSONArray("formattedAddress");
                        StringBuilder formattedAddress = new StringBuilder();
                        for (int j = 0; j < address.length(); j++) {
                            formattedAddress.append(address.getString(j));
                            if (j != address.length() - 1)
                            formattedAddress.append(", ");
                        }
                        c.setFormattedAddress(formattedAddress.toString());

                        JSONArray category = item.getJSONArray("categories");
                        c.setCategory(category.getJSONObject(0).getString("name"));

                        JSONObject stats = item.getJSONObject("stats");
                        c.setCheckinsCount(stats.getInt("checkinsCount"));
                        c.setUsersCount(stats.getInt("usersCount"));
                        c.setTipCount(stats.getInt("tipCount"));

                        list.add(c);
                    }

                    Collections.sort(list);
                    adapter.updateResults(list);

                } catch (JSONException e) {
                    Log.e("JSONException", e.toString());
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VolleyError", error.toString());
            }
        });

        // Add request to the queue
        AppController.getInstance().getRequestQueue().add(request);
    }
}
