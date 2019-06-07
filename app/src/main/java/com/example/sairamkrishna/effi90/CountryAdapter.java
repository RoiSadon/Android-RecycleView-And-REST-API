package com.example.sairamkrishna.effi90;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import static com.example.sairamkrishna.effi90.MainActivity.countryList;

/**
 * Created by ankit on 27/10/17.
 */

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.ViewHolder> {



    private Context context;
    private List<Country> list;

    //ctor
    public CountryAdapter( Context context, List<Country> list) {
        this.context = context;
        this.list = list;
    }

 // taking control over the textviews
    class CountryViewHolder extends RecyclerView.ViewHolder {
        TextView Name;
        TextView nativeName;

        public CountryViewHolder(View itemView) {
            super(itemView);
            Name = (TextView) itemView.findViewById(R.id.tv_name);
            nativeName = (TextView) itemView.findViewById(R.id.tv_native_name);
        }
    }
    //displaying the items in the list as I want
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.single_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       //getting the country I want from the list
        Country country= list.get(position);
//initializing variables according to the country
        String name = country.name;
        String nativeName = country.nativeName;

        //setting the text
        holder.textName.setText(name);
        holder.textNativeName.setText(nativeName);



    }

    @Override
    //getting list size
    public int getItemCount() {
        if (list == null){
            return 0;
        }
        return list.size();
    }
//setting the country data to my list.
    public void setCountryData (List<Country> countryData){
        list = countryData;
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textName, textNativeName;

        public ViewHolder(View itemView) {
            super(itemView);

            textName = itemView.findViewById(R.id.main_name);
            textNativeName= itemView.findViewById(R.id.main_native_name);

            //checking for clicks on the list and entering the country that have been tapped
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(context, secondActivity.class);
                    Country curCountry = countryList.get(getAdapterPosition());
                    myIntent.putExtra("borders",curCountry.borders);
                    context.startActivity(myIntent);

                }
            });
        }
    }

}