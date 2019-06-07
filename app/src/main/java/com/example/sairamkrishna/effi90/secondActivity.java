package com.example.sairamkrishna.effi90;
/*
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class secondActivity extends AppCompatActivity {
    ArrayAdapter arrayAdapter;
    ListView listView;
    public  void getBorderCode(String[] names){
        // make request for each country code
        for ( String name : names) {
            String url = "https://restcountries.eu/rest/v2/alpha/"+name;
            StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        // parse reponse as an json object and get the name
                        JSONObject obj = new JSONObject(response);
                        String borderName = obj.getString("name");
                        // add the name to the adapter
                        arrayAdapter.add(borderName);
                        // setting the adapter to the list
                        listView.setAdapter(arrayAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // Anything you want
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String bordersString = getIntent().getStringExtra("borders").replace("[","").replace("]","");


        List<String> bordersList= new ArrayList<>();
        for (String data : bordersString.replace("\"", "").split(","))
            bordersList.add(data);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1 , bordersList);
        ListView listView = findViewById(R.id.mListView);
        listView.setAdapter(arrayAdapter);


        String[] borders = bordersString.replace("\"","").split(",");

        getBorderCode(borders);
    }
}
*/

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class secondActivity extends AppCompatActivity {
    ArrayAdapter arrayAdapter;
    ListView listView;


    public  void getNames(String[] names){
        // make request for each country code
        for ( String code : names) {
            String url = "https://restcountries.eu/rest/v2/alpha/"+code;
            StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        // parse reponse as an json object and get the name
                        JSONObject obj = new JSONObject(response);
                        String borderName = obj.getString("name");
                        // add the name to the adapter
                        arrayAdapter.add(borderName);

                        String borderNativeName = obj.getString("nativeName");
                        // add the name to the adapter
                        arrayAdapter.add(borderNativeName);
                        // setting the adapter to the list
                        listView.setAdapter(arrayAdapter);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    // Anything you want
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        listView = findViewById(R.id.mListView);

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1);
        // delete the chars unnecessary
        String border = getIntent().getStringExtra("borders").replace("[","").replace("]","");

            // spliting to array of the elements seperated at the ","
            String[] borders = border.replace("\"","").split(",");
            getNames(borders);


    }
}


