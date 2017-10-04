package com.example.hamza.contacts;

import android.app.Dialog;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lv;
    Button btn;
    Contacts contact;
    ContactsListner mLeDeviceListAdapter;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Database_Handler db = new Database_Handler(this);
        listview = (ListView) findViewById(R.id.listview);

        //------------------------------Creating new Contact------------------------
        btn = (Button) findViewById(R.id.btn_create);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.create_contact);
                dialog.setTitle("Create New Contact");
                dialog.getWindow().setLayout(ActionBar.LayoutParams.FILL_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);


                final EditText name = (EditText) dialog.findViewById(R.id.name);
                final EditText number = (EditText) dialog.findViewById(R.id.number);
                final TextView title = (TextView) dialog.findViewById(R.id.title);
                title.setText("Create New Contact");
                Button btn_save = (Button) dialog.findViewById(R.id.save);
                Button btn_cancel = (Button) dialog.findViewById(R.id.cancel);
                btn_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String temp_name, temp_number;
                        temp_name = name.getText().toString();
                        temp_number = number.getText().toString();
                        if (!temp_name.isEmpty() && !temp_number.isEmpty()) {
                            Contacts contact = new Contacts();
                            contact.setName(temp_name);
                            contact.setNumber(temp_number);
                            db.addContact(contact);
                            dialog.cancel();
                            updatedData(db.getAllContacts());
                           /* Contacts.records.clear();
                            Contacts.records = db.getAllContacts();*/
                        } else {
                            Toast.makeText(MainActivity.this, "Please Enter Name and Number", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });
                dialog.show();
            }
        });


        // Update contact
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, final int position, long arg3) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.edit_contact);
                dialog.setTitle("Edit/Delete Contact");
                dialog.getWindow().setLayout(ActionBar.LayoutParams.FILL_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);
                final EditText name = (EditText) dialog.findViewById(R.id.name);
                final EditText number = (EditText) dialog.findViewById(R.id.number);
                final TextView title = (TextView) dialog.findViewById(R.id.title);
                title.setText("Edit/Delete Contact");
                name.setText(contact.records.get(position).getName());
                number.setText(contact.records.get(position).getNumber());
                Button btn_save = (Button) dialog.findViewById(R.id.save);
                btn_save.setText("Update");
                Button btn_cancel = (Button) dialog.findViewById(R.id.cancel);
                Button btn_delete = (Button) dialog.findViewById(R.id.delete);
                btn_save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String temp_name, temp_number;
                        temp_name = name.getText().toString();
                        temp_number = number.getText().toString();
                        if (!temp_name.isEmpty() && !temp_number.isEmpty()) {

                            contact.records.get(position).setName(temp_name);
                            contact.records.get(position).setNumber(temp_number);
                            db.updateContact(contact.records.get(position));
                            updatedData(db.getAllContacts());
                            dialog.cancel();


                        } else {
                            Toast.makeText(MainActivity.this, "Please Enter Name and Number", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                    }
                });

                btn_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.cancel();
                        db.deleteContact(contact.records.get(position));
                        updatedData(db.getAllContacts());
                    }
                });
                dialog.show();


            }
        });


        Contacts.records = db.getAllContacts();
        for (int i = 0; i < Contacts.records.size(); i++) {
            Toast.makeText(getApplicationContext(), Contacts.records.get(i).getName() + "", Toast.LENGTH_SHORT).show();
        }
        mLeDeviceListAdapter = new
                ContactsListner(this, R.layout.recycler_list_item, Contacts.records);
        listview.setAdapter(mLeDeviceListAdapter);

    }

    public void updatedData(List<Contacts> itemsArrayList) {

        mLeDeviceListAdapter.clear();

        if (itemsArrayList != null) {

            for (Contacts object : itemsArrayList) {

                mLeDeviceListAdapter.insert(object, mLeDeviceListAdapter.getCount());
            }
        }

        mLeDeviceListAdapter.notifyDataSetChanged();

    }


}
