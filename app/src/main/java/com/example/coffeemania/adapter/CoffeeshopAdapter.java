package com.example.coffeemania.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.coffeemania.misc.Coffeeshop;
import com.example.coffeemania.R;

import java.text.DecimalFormat;
import java.util.List;

public class CoffeeshopAdapter extends BaseAdapter {
    List<Coffeeshop> mList;
    Context mContext;

    public CoffeeshopAdapter(Context context, List<Coffeeshop> list) {
        mContext = context;
        mList = list;
    }

    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder viewHolder;

        if (view == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            view = inflater.inflate(R.layout.list_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.tvName = (TextView) view.findViewById(R.id.item_name);
            viewHolder.tvDistanceAddress = (TextView) view.findViewById(R.id.item_distance_address);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Coffeeshop item = mList.get(position);
        if (item != null) {
            if (viewHolder.tvName != null) {
                viewHolder.tvName.setText(item.getName());
            }

            String distance = "";
            if (item.getDistance() < 1000) {
                distance = item.getDistance() + "m";
            } else {
                DecimalFormat decimalFormat = new DecimalFormat("#.#");
                distance = decimalFormat.format(item.getDistance() / 1000d) + "km";
            }

            if (viewHolder.tvDistanceAddress != null) {
                viewHolder.tvDistanceAddress.setText(distance + "   -   " + item.getFormattedAddress());
            }
        }

        return view;
    }

    public void updateResults(List<Coffeeshop> list) {
        mList = list;
        notifyDataSetChanged();
    }

    private static class ViewHolder {
        protected TextView tvName;
        protected TextView tvDistanceAddress;
    }
}
