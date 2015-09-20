package com.example.coffeemania;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.coffeemania.activity.DetailActivity;
import com.example.coffeemania.activity.MainActivity;
import com.example.coffeemania.misc.Coffeeshop;
import com.robotium.solo.Solo;

/**
 * A test case based on Robotium library
 */
public class UITest extends ActivityInstrumentationTestCase2<MainActivity> {

    private Solo solo;
    private int index;
    private Coffeeshop coffeeshop;

    public UITest() {
        super(MainActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        //setUp() is run before a test case is started.
        //This is where the solo object is created.
        solo = new Solo(getInstrumentation(), getActivity());
        index = (int)(Math.random() * 7);
    }

    @Override
    protected void tearDown() throws Exception {
        //tearDown() is run after a test case has finished.
        //finishOpenedActivities() will finish all the activities that have been opened during the test execution.
        solo.finishOpenedActivities();
    }

    public void testNameIsSame() throws Exception {
        // First unlock the screen.
        solo.unlockScreen();
        // As we need to get data based on location, it will take some time. Thus we need to wait
        // until we ListView is loaded.
        solo.waitForView(android.R.id.list);
        // Check that we are in the same Activity.
        solo.assertCurrentActivity("Wrong activity", MainActivity.class);

        // Get ListView instance, and its item at position 'index'.
        ListView list = (ListView) solo.getView(android.R.id.list);
        View v = list.getChildAt(index);
        // Get TextView, and save its value to 'name' variable.
        TextView tvNameMain = (TextView) v.findViewById(R.id.item_name);
        String name = (String) tvNameMain.getText();

        // Click item (index + 1) in ListView [items in clickInList starts from 1].
        solo.clickInList(index + 1);
        // Confirm that we moved to a new Activity.
        solo.assertCurrentActivity("Wrong activity", DetailActivity.class);
        TextView tvNameDetail = (TextView) solo.getView(R.id.detail_name);

        // Check whether the name of the venue in the List, and in the DetailActivity are same.
        assertEquals("Name should be the same", name, tvNameDetail.getText());
    }

    public void testAddressIsSame() throws Exception {
        // First unlock the screen.
        solo.unlockScreen();
        // As we need to get data based on location, it will take some time. Thus we need to wait
        // until we ListView is loaded.
        solo.waitForFragmentById(android.R.id.list);
        // Check that we are in the same Activity.
        solo.assertCurrentActivity("Wrong activity", MainActivity.class);

        // Get ListView instance, and its item at position 'index'.
        ListView list = (ListView) solo.getView(android.R.id.list);
        View v = list.getChildAt(index);
        // Get TextView, and save its value to 'address' variable.
        TextView tvDistanceAddressMain = (TextView) v.findViewById(R.id.item_distance_address);
        String distanceAddress = (String) tvDistanceAddressMain.getText();
        String address = distanceAddress.split(" - ")[1].trim();

        // Click item (index + 1) in ListView [items in clickInList starts from 1].
        solo.clickInList(index + 1);
        // Confirm that we moved to a new Activity.
        solo.assertCurrentActivity("Wrong activity", DetailActivity.class);
        TextView tvAddressDetail = (TextView) solo.getView(R.id.detail_address);

        // Check whether the address of the venue in the List, and in the DetailActivity are same.
        assertEquals("Address should be the same", address, tvAddressDetail.getText());
    }

    // The others are Unit Tests just to check Coffeeshop class, and its methods.
    public void testUnitCoffeeshopName() throws Exception {
        coffeeshop = new Coffeeshop();
        String name = "Cafe Pele";
        coffeeshop.setName(name);

        assertEquals("Coffeeshop name is not correctly set", name, coffeeshop.getName());
    }

    public void testUnitCoffeeshopDistance() throws Exception {
        coffeeshop = new Coffeeshop();
        int distance = 10;
        coffeeshop.setDistance(distance);

        assertEquals("Coffeeshop distance is not correctly set", distance, coffeeshop.getDistance());
    }

    public void testUnitCoffeeshopUrlNull() throws Exception {
        coffeeshop = new Coffeeshop();

        assertEquals("Coffeeshop url is not correctly set", null, coffeeshop.getUrl());
    }

    public void testUnitCoffeeshopCategoryEmpty() throws Exception {
        coffeeshop = new Coffeeshop();
        String category = "";
        coffeeshop.setCategory(category);

        assertEquals("Coffeeshop category is not correctly set", category, coffeeshop.getCategory());
    }

}
