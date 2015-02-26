package org.ligi.axt.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public abstract class SimpleStringAdapter extends BaseAdapter {

    public abstract String getString(int position);

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView tv = new TextView(parent.getContext());
        tv.setText(getString(position));
        //tv.setTextSize(parent.getResources().getDimension(R.d));
        return tv;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        TextView tv = new TextView(parent.getContext());
        tv.setText(getString(position));
        tv.setTextSize(24.0f);
        return tv;
    }

}
