package com.example.coffeemania;

import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.coffeemania.activity.DetailActivity;
import com.example.coffeemania.activity.MainActivity;
import com.example.coffeemania.misc.Coffeeshop;
import com.robotium.solo.Solo;

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
        solo.unlockScreen();
        solo.waitForView(android.R.id.list);
        solo.assertCurrentActivity("Wrong activity", MainActivity.class);

        ListView list = (ListView) solo.getView(android.R.id.list);
        View v = list.getChildAt(index);
        TextView tvNameMain = (TextView) v.findViewById(R.id.item_name);
        String name = (String) tvNameMain.getText();

        solo.clickInList(index + 1);
        solo.assertCurrentActivity("Wrong activity", DetailActivity.class);
        TextView tvNameDetail = (TextView) solo.getView(R.id.detail_name);

        assertEquals("Name should be the same", name, tvNameDetail.getText());
    }

    public void testAddressIsSame() throws Exception {
        solo.unlockScreen();
        solo.waitForFragmentById(android.R.id.list);
        solo.assertCurrentActivity("Wrong activity", MainActivity.class);

        ListView list = (ListView) solo.getView(android.R.id.list);
        View v = list.getChildAt(index);
        TextView tvDistanceAddressMain = (TextView) v.findViewById(R.id.item_distance_address);
        String distanceAddress = (String) tvDistanceAddressMain.getText();
        String address = distanceAddress.split(" - ")[1].trim();

        solo.clickInList(index + 1);
        solo.assertCurrentActivity("Wrong activity", DetailActivity.class);
        TextView tvAddressDetail = (TextView) solo.getView(R.id.detail_address);

        assertEquals("Address should be the same", address, tvAddressDetail.getText());
    }

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
