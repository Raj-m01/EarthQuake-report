package com.example.quakereport;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;


import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.Math.floor;


public class CustomAdapter extends ArrayAdapter<info> {



    public CustomAdapter(@NonNull Context context, int resource, @NonNull ArrayList<info> earthqaukes) {
        super(context,0, earthqaukes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View ConvertView = convertView;

        if(ConvertView==null){

            ConvertView = LayoutInflater.from(getContext()).inflate(R.layout.quakelist,parent,false);

        }

        info trans = getItem(position);
        TextView text1 =  ConvertView.findViewById(R.id.text1);
        TextView lndmrk = ConvertView.findViewById(R.id.lndmrk);
        TextView loc = ConvertView.findViewById(R.id.loc);
        TextView text3 = ConvertView.findViewById(R.id.date);
        TextView text4 = ConvertView.findViewById(R.id.time);


        //Magnitude
        Double mag = trans.giveMag();
        DecimalFormat df = new DecimalFormat("0.0");
        String nmag = df.format(mag);
        text1.setText(nmag);



        //Date and Time
        long timeinms = trans.giveDate();
        Date date = new Date(timeinms);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd yyyy");
        String finalDate = dateFormat.format(date);
        text3.setText(finalDate);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat timeformat = new SimpleDateFormat("hh:mm a");
        String formattedTime = timeformat.format(date);
        text4.setText(formattedTime);


        //Location
        String rawLoc = trans.giveLocation();
        if(rawLoc.contains("of")){
            String[] store = rawLoc.split("of",2);
            String str1 = store[0]+"of";
            lndmrk.setText(str1);
            loc.setText(store[1]);
        }else {
            loc.setText(rawLoc);
        }


        //set background color based on magnitude
        GradientDrawable magnitudeCircle = (GradientDrawable) text1.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(mag);

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);






        return ConvertView;
    }


    public int getMagnitudeColor(double mag){

        int magFloor,colorId;
        magFloor = (int) floor(mag);



        switch (magFloor){

            case 0:
            case 1: colorId = R.color.magnitude1;
            break;
            case 2: colorId = R.color.magnitude2;
            break;
            case 3: colorId = R.color.magnitude3;
            break;
            case 4: colorId = R.color.magnitude4;
                break;
            case 5: colorId = R.color.magnitude5;
                break;
            case 6: colorId = R.color.magnitude6;
                break;
            case 7: colorId = R.color.magnitude7;
                break;
            case 8: colorId = R.color.magnitude8;
                break;
            case 9: colorId = R.color.magnitude9;
                break;
            default: colorId = R.color.magnitude10plus;



        }


        return ContextCompat.getColor(getContext(), colorId);
    }
    }


