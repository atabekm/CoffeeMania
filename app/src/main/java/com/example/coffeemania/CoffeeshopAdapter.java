package com.example.coffeemania;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

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
            viewHolder.tvDistance = (TextView) view.findViewById(R.id.item_distance);
            viewHolder.tvAddress = (TextView) view.findViewById(R.id.item_address);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Coffeeshop item = mList.get(position);
        if (item != null) {
            if (viewHolder.tvName != null) {
                viewHolder.tvName.setText(position + 1 + ". " + item.getName());
            }

            if (viewHolder.tvDistance != null) {
                viewHolder.tvDistance.setText(String.valueOf(item.getDistance()));
            }

            if (viewHolder.tvAddress != null) {
                viewHolder.tvAddress.setText(item.getFormattedAddress());
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
        protected TextView tvDistance;
        protected TextView tvAddress;
    }
}
