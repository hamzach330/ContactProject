package com.example.hamza.contacts;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.daimajia.swipe.adapters.BaseSwipeAdapter;

import java.util.List;

/**
 * Created by muhammad.tayyab on 10/2/2017.
 */

public class ContactsListner extends ArrayAdapter<Contacts> {

    private Context mContext;
    LayoutInflater inflater;

    public ContactsListner(@NonNull Context context, @LayoutRes int resource, @NonNull List<Contacts> objects) {
        super(context, resource, objects);
        inflater = ((Activity) context).getLayoutInflater();
        this.mContext = context;
        Contacts.records = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder = null;
        //Integer selected_position = -1;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.recycler_list_item, null);
            viewHolder = new ViewHolder();

            viewHolder.tv1 = (TextView) convertView.findViewById(R.id.txt_name);
            viewHolder.tv2 = (TextView) convertView.findViewById(R.id.txt_number);

            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();
        viewHolder.tv1.setText(Contacts.records.get(position).getName());
        viewHolder.tv2.setText(Contacts.records.get(position).getNumber());
        notifyDataSetChanged();
        return convertView;
    }

    private static class ViewHolder {
        TextView tv1, tv2;
    }

}
