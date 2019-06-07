package com.example.sairamkrishna.effi90;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    //this is the url for the JSON
    private String url = "https://restcountries.eu/rest/v2/all";

    private RecyclerView mList;
    static Context context;

    private LinearLayoutManager linearLayoutManager;
    private DividerItemDecoration dividerItemDecoration;
    public static List<Country> countryList;
    private RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mList = findViewById(R.id.main_list);

        countryList = new ArrayList<>();


        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        dividerItemDecoration = new DividerItemDecoration(mList.getContext(), linearLayoutManager.getOrientation());

        context = getApplicationContext();


        getData();
    }

    private void getData() {
        final ProgressDialog progressDialog = new ProgressDialog(this);
      // loading dialog while processing the data from the URL
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        // new request
        StringRequest stringRequest= new StringRequest(url, new Response.Listener<String>() {


            @Override
            public void onResponse(String stringRequest) {
                try {
                    // getting the respose into the json array

                    JSONArray arr = new JSONArray(stringRequest);

                    for (int i = 0; i < arr.length(); i++) {

                        //parsing every element in the array to JSONobject
                        JSONObject jsonObject = arr.getJSONObject(i);
                    //getting the elements I want
                        Country country = new Country(jsonObject.getString("name"), jsonObject.getString("nativeName"),jsonObject.getString("borders"));
                        Log.i("json",country.name);
                         //add the country to the list
                        countryList.add(country);
                    }
                    //set adapter for the recyclerView
                    adapter = new CountryAdapter(getApplicationContext(),countryList);
                    mList.setHasFixedSize(true);
                    mList.setLayoutManager(linearLayoutManager);
                    mList.addItemDecoration(dividerItemDecoration);
                    mList.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                    progressDialog.dismiss();
                }

                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley", error.toString());
                progressDialog.dismiss();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}