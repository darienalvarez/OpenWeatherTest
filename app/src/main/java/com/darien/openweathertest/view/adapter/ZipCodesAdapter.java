package com.darien.openweathertest.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.darien.openweathertest.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Darien
 * on 5/27/16.
 */
public class ZipCodesAdapter extends ArrayAdapter<String> {

    private LayoutInflater mInflater;

    public ZipCodesAdapter(Context context, List<String> data) {
        super(context, R.layout.item_zip_code, data);
        mInflater = LayoutInflater.from(context);
    }

    @Override public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {

            view = mInflater.inflate(R.layout.item_zip_code, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }

        if (position == 0) {
            holder.mTextView.setText(getContext().getString(R.string.default_item, getItem(position)));
        } else {
            holder.mTextView.setText(getItem(position));
        }

        return view;
    }

    static class ViewHolder {
        @BindView(R.id.zipCodeTextView)
        TextView mTextView;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
