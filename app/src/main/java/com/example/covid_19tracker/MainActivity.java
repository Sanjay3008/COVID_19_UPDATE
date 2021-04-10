
package com.example.covid_19tracker;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.material.circularreveal.CircularRevealHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int request_case_permission =1;

    ProgressBar progressBar;
    String city,state;

    RequestQueue requestQueue;
    Button share,tamilnadu,andaman,andra,arunachal,assam,bihar,chandigarh,chattisgarh,delhi,damanddieu,goa,gujarat
            ,himachal,haryana,jharkhand,jammu,karnataka,kerala,ladakh,lakshadweep,maharastra,meghalaya,manipur,madhya_pradesh,mizoram
            ,nagaland,odisha,punjab,pudhucherry,rajasthan,sikkim,telangana,tripura,uttarpradesh,uttarkhand,westbengal;
    PieChart pieChart;
    ViewPager viewPager;
    TableLayout tableLayout;
    TextView st_text,ACTIVE,RECOVERED,CONFIRMED,DEATH,current_loc,curr_conf,curr_activ,curr_recov,curr_death,head,current_state
            ,current_date,state_daily_confirmed,state_daily_recovered,state_daily_death,heading_state,
    state_tested,state_vaccinated, test_cout_total, vacci_cout_total,test1,test2,statetest;
    private Integer[] images={R.drawable.s1,R.drawable.s2,R.drawable.s3,R.drawable.s4,R.drawable.s5,R.drawable.s6
    ,R.drawable.s7,R.drawable.s8,R.drawable.s9,R.drawable.s10,R.drawable.s12,R.drawable.s13,R.drawable.s14};
    CardView qanda,symptoms,prevention,helpline,maps;
    LinearLayout one,two,three,four,current_head,location,current_state_head,current_state_test_vacc;

    LocationManager locationManager;
    FusedLocationProviderClient fusedLocationProviderClient;
    HorizontalScrollView state_scroll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        ///current locaton ids

        qanda=findViewById(R.id.QandA);
        symptoms=findViewById(R.id.Symptoms);
        symptoms.setOnClickListener(this);
        qanda.setOnClickListener(this);
        prevention=findViewById(R.id.prevention);
        prevention.setOnClickListener(this);
        helpline=findViewById(R.id.helpline);
        helpline.setOnClickListener(this);
        maps=findViewById(R.id.hospital_nearby);
        maps.setOnClickListener(this);


        //statebutton
        andaman=findViewById(R.id.andaman);
        andra=findViewById(R.id.andra);
        arunachal=findViewById(R.id.arunachal);
        assam=findViewById(R.id.assam);
        bihar=findViewById(R.id.bihar);
        chandigarh=findViewById(R.id.chandigarh);
        chattisgarh=findViewById(R.id.chattisgarh);
        delhi=findViewById(R.id.delhi);
        damanddieu=findViewById(R.id.daman_diu);
        tamilnadu=findViewById(R.id.tamil_nadu);
        goa=findViewById(R.id.goa);
        gujarat=findViewById(R.id.gujarat);
        himachal=findViewById(R.id.himachal);
        haryana=findViewById(R.id.haryana);
        jharkhand=findViewById(R.id.jharkhand);
        jammu=findViewById(R.id.jammu);
        karnataka=findViewById(R.id.karnataka);
        kerala=findViewById(R.id.kerala);
        ladakh=findViewById(R.id.ladakh);
        lakshadweep=findViewById(R.id.lakshadweep);
        maharastra=findViewById(R.id.maharastra);
        meghalaya=findViewById(R.id.meghalaya);
        manipur=findViewById(R.id.manipur);
        madhya_pradesh=findViewById(R.id.madhya_pradesh);
        mizoram=findViewById(R.id.mizoram);
        nagaland=findViewById(R.id.nagaland);
        odisha=findViewById(R.id.odisha);
        punjab=findViewById(R.id.punjab);
        pudhucherry=findViewById(R.id.puducherry);
        rajasthan=findViewById(R.id.rajasthan);
        sikkim=findViewById(R.id.sikkim);
        telangana=findViewById(R.id.telangana);
        tripura=findViewById(R.id.tripura);
        uttarpradesh=findViewById(R.id.uttar);
        uttarkhand=findViewById(R.id.uttarkhand);
        westbengal=findViewById(R.id.west);


        tamilnadu.setOnClickListener(this);
        andaman.setOnClickListener(this);
        andra.setOnClickListener(this);
        arunachal.setOnClickListener(this);
        assam.setOnClickListener(this);
        bihar.setOnClickListener(this);
        chandigarh.setOnClickListener(this);
        chattisgarh.setOnClickListener(this);
        delhi.setOnClickListener(this);
        damanddieu.setOnClickListener(this);
        goa.setOnClickListener(this);
        gujarat.setOnClickListener(this);
        himachal.setOnClickListener(this);
        haryana.setOnClickListener(this);
        jharkhand.setOnClickListener(this);
        jammu.setOnClickListener(this);
        karnataka.setOnClickListener(this);
        kerala.setOnClickListener(this);
        ladakh.setOnClickListener(this);
        lakshadweep.setOnClickListener(this);
        maharastra.setOnClickListener(this);
        manipur.setOnClickListener(this);
        meghalaya.setOnClickListener(this);
        madhya_pradesh.setOnClickListener(this);
        mizoram.setOnClickListener(this);
        nagaland.setOnClickListener(this);
        odisha.setOnClickListener(this);
        punjab.setOnClickListener(this);
        pudhucherry.setOnClickListener(this);
        rajasthan.setOnClickListener(this);
        sikkim.setOnClickListener(this);
        telangana.setOnClickListener(this);
        tripura.setOnClickListener(this);
        uttarpradesh.setOnClickListener(this);
        uttarkhand.setOnClickListener(this);
        westbengal.setOnClickListener(this);



        state_scroll=findViewById(R.id.state_button_scroll);
        state_scroll.setSmoothScrollingEnabled(true);

        current_state_test_vacc=findViewById(R.id.current_state_test_vacc);

        requestQueue = Volley.newRequestQueue(this);
        st_text=findViewById(R.id.state_text);
        // text view
        ACTIVE = findViewById(R.id.active1_counts);
        CONFIRMED = findViewById(R.id.confirmed_counts);
        RECOVERED = findViewById(R.id.recovered_counts);
        DEATH = findViewById(R.id.death_counts);
        state_tested=findViewById(R.id.state_tested);
        state_vaccinated=findViewById(R.id.state_vacciated);
        test_cout_total=findViewById(R.id.tested_counts_total);
        vacci_cout_total=findViewById(R.id.vaccinated_counts_total);
        test1=findViewById(R.id.test1);
        test2=findViewById(R.id.test2);
        statetest=findViewById(R.id.current_state_test);

        tableLayout=findViewById(R.id.state_table);

        share = findViewById(R.id.share_btn);

        pieChart = findViewById(R.id.piechart);

        viewPager=findViewById(R.id.viewslider);
        viewPager.setPageMargin(30);
        slidingimageAdapter slidingimageAdapter = new slidingimageAdapter(this,images);
        viewPager.setAdapter(slidingimageAdapter);


        //current location covid status
        grantPermission();

        final Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                scrollstate();
            }
        }, 0, 3000);
        state_scroll.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                timer.cancel();
                return false;

            }
        });


        String url="https://api.covid19india.org/v2/state_district_wise.json";
        String url_test_vacc ="https://api.covid19india.org/v4/min/data.min.json";

        JsonObjectRequest request_test= new JsonObjectRequest(Request.Method.GET, url_test_vacc, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject TT= response.getJSONObject("TT").getJSONObject("total");
                    String tested= TT.getString("tested");
                    String vacci=TT.getString("vaccinated");
                    test_cout_total.setText(tested);
                    vacci_cout_total.setText(vacci);
                } catch (JSONException e) {
                    Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        String[] state_names = new String[response.length()];
                        int[] state_count_active = new int[response.length()];
                        int[] state_count_confirmed = new int[response.length()];
                        int[] state_count_recovered = new int[response.length()];
                        int[] state_count_death = new int[response.length()];
                        try {
                            for(int i=0;i<response.length();i++){
                                JSONObject jsonObject = response.getJSONObject(i);
                                state_names[i]=jsonObject.getString("state");
                                JSONArray district = jsonObject.getJSONArray("districtData");
                                int confirmed_count=0;
                                int active_count=0;
                                int recovered_count=0;
                                int death_count=0;
                                for(int j=0;j<district.length();j++){
                                     JSONObject eachdistrict = district.getJSONObject(j);
                                     int c_count= eachdistrict.getInt("confirmed");
                                     int a_count=eachdistrict.getInt("active");
                                     int r_count = eachdistrict.getInt("recovered");
                                     int d_count=eachdistrict.getInt("deceased");
                                     confirmed_count+=c_count;
                                     active_count+=a_count;
                                     recovered_count+=r_count;
                                     death_count+=d_count;
                                     state_count_active[i]=active_count;
                                     state_count_confirmed[i]=confirmed_count;
                                     state_count_recovered[i]=recovered_count;
                                     state_count_death[i]=death_count;
                                }
                            }
                            int total_confirmed=0;
                            int total_active=0;
                            int total_recoverd=0;
                            int total_death=0;
                            for(int i=0;i<state_count_active.length;i++){
                                total_active=total_active+state_count_active[i];
                            }
                            for(int i=0;i<state_count_confirmed.length;i++){
                                total_confirmed=total_confirmed+state_count_confirmed[i];
                            }
                            for(int i=0;i<state_count_recovered.length;i++){
                                total_recoverd=total_recoverd+state_count_recovered[i];
                            }
                            for(int i=0;i<state_count_death.length;i++){
                                total_death=total_death+state_count_death[i];
                            }
                            ACTIVE.setText(String.valueOf(total_active));
                            CONFIRMED.setText(String.valueOf(total_confirmed));
                            RECOVERED.setText(String.valueOf(total_recoverd));
                            DEATH.setText(String.valueOf(total_death));
                            ArrayList<PieEntry> data = new ArrayList<PieEntry>();
                            data.add(new PieEntry(total_active,"Active"));
                            data.add(new PieEntry(total_recoverd,"Recovered"));
                            data.add(new PieEntry(total_confirmed,"Confirmed"));
                            data.add(new PieEntry(total_death,"Death"));
                            int color[] = new int[]{Color.rgb(221,44,0),Color.rgb(46,125,50),
                                    Color.rgb(238,108,0),Color.rgb(27,153,253)};
                            PieDataSet pieDataSet = new PieDataSet(data,"");
                            pieDataSet.setColors(color);
                            PieData  pieData = new PieData(pieDataSet);
                            pieData.setValueTextSize(12f);
                            pieData.setValueTextColor(Color.rgb(255,255,255));
                            pieChart.setVisibility(View.INVISIBLE);
                            pieChart.setData(pieData);
                            pieChart.setRotationAngle(180f);
                            try{
                                wait(100);
                            }catch (Exception e){}
                            pieChart.setVisibility(View.VISIBLE);
                            pieChart.setCenterTextSize(20);
                            pieChart.setDrawEntryLabels(false);
                            pieChart.setTransparentCircleRadius(0f);
                            pieChart.setHoleRadius(30);
                            pieChart.getDescription().setEnabled(false);
                            pieChart.invalidate();

                            for(int i=0;i<1;i++){
                                View tableRow = LayoutInflater.from(MainActivity.this).inflate(R.layout.row_table,null,false);
                                TextView statename = tableRow.findViewById(R.id.state_name);
                                TextView stateactive = tableRow.findViewById(R.id.state_active);
                                TextView staterecover = tableRow.findViewById(R.id.state_recovered);
                                TextView stateconfirmed = tableRow.findViewById(R.id.state_confirmed);
                                TextView statedeath = tableRow.findViewById(R.id.state_death);
                                statename.setText("STATE");
                                statename.setTextColor(Color.rgb(0,0,0));
                                stateactive.setText("ACTIVE");
                                stateactive.setTextColor(Color.rgb(221,44,0));
                                staterecover.setText("RECOVERED");
                                staterecover.setTextColor(Color.rgb(45,125,50));
                                stateconfirmed.setText("CONFIRMED");
                                stateconfirmed.setTextColor(Color.rgb(239,108,0));
                                statedeath.setText("DEATH");
                                statedeath.setTextColor(Color.rgb(27,153,253));
                                tableLayout.addView(tableRow);
                            }
                            for(int i=0;i<response.length();i++){
                                View tableRow = LayoutInflater.from(MainActivity.this).inflate(R.layout.row_table,null,false);
                                TextView statename = tableRow.findViewById(R.id.state_name);
                                TextView stateactive = tableRow.findViewById(R.id.state_active);
                                TextView staterecover = tableRow.findViewById(R.id.state_recovered);
                                TextView stateconfirmed = tableRow.findViewById(R.id.state_confirmed);
                                TextView statedeath = tableRow.findViewById(R.id.state_death);
                                statename.setText(state_names[i]);
                                stateactive.setText(String.valueOf(state_count_active[i]));
                                staterecover.setText(String.valueOf(state_count_recovered[i]));
                                stateconfirmed.setText(String.valueOf(state_count_confirmed[i]));
                                statedeath.setText(String.valueOf(state_count_death[i]));
                                tableLayout.addView(tableRow);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
        requestQueue.add(request_test);
    }


    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.tamil_nadu:
                tamilnadu.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else{
                    Intent intent = new Intent(this,Tamilnadu.class);
                    startActivity(intent);
                }
                break;
            case R.id.andaman:
                andaman.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent1 = new Intent(this, andaman.class);
                    startActivity(intent1);
                }
                break;
            case R.id.andra:
                andra.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent2 = new Intent(this, andra_pradesh.class);
                    startActivity(intent2);
                }
                break;
            case R.id.arunachal:
                arunachal.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent3 = new Intent(this, arunachal_pradesh.class);
                    startActivity(intent3);
                }
                break;
            case R.id.assam:
                assam.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent4 = new Intent(this, assam.class);
                    startActivity(intent4);
                }
                break;
            case R.id.bihar:
                bihar.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent5 = new Intent(this, bihar.class);
                    startActivity(intent5);
                }
                break;
            case R.id.chandigarh:
                chandigarh.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent6 = new Intent(this, chandigarh.class);
                    startActivity(intent6);
                }
                break;
            case R.id.chattisgarh:
                chattisgarh.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent7 = new Intent(this, chattisgarh.class);
                    startActivity(intent7);
                }
                break;
            case R.id.delhi:
                delhi.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent8 = new Intent(this, delhi.class);
                    startActivity(intent8);
                }
                break;
            case R.id.daman_diu:
                damanddieu.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent9 = new Intent(this, damanddieu.class);
                    startActivity(intent9);
                }
                break;
            case R.id.goa:
                goa.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent10 = new Intent(this, goa.class);
                    startActivity(intent10);
                }
                break;
            case R.id.gujarat:
                gujarat.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent11 = new Intent(this, gujarat.class);
                    startActivity(intent11);
                }
                break;
            case R.id.himachal:
                himachal.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent12 = new Intent(this, himachal.class);
                    startActivity(intent12);
                }
                break;
            case R.id.haryana:
                haryana.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent13 = new Intent(this, haryana.class);
                    startActivity(intent13);
                }
                break;
            case R.id.jharkhand:
                jharkhand.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent14 = new Intent(this, jharkhand.class);
                    startActivity(intent14);
                }
                break;
            case R.id.jammu:
                jammu.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent15 = new Intent(this, jammu.class);
                    startActivity(intent15);
                }
                break;
            case R.id.karnataka:
                karnataka.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent16 = new Intent(this, karnataka.class);
                    startActivity(intent16);
                }
                break;
            case R.id.kerala:
                kerala.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent17 = new Intent(this, kerala.class);
                    startActivity(intent17);
                }
                break;
            case R.id.ladakh:
                ladakh.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent18 = new Intent(this, ladakh.class);
                    startActivity(intent18);
                }
                break;
            case R.id.lakshadweep:
                lakshadweep.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent19 = new Intent(this, lakshadweep.class);
                    startActivity(intent19);
                }
                break;
            case R.id.maharastra:
                maharastra.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent20 = new Intent(this, maharastra.class);
                    startActivity(intent20);
                }
                break;
            case R.id.meghalaya:
                meghalaya.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent21 = new Intent(this, meghalaya.class);
                    startActivity(intent21);
                }
                break;
            case R.id.manipur:
                manipur.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent22 = new Intent(this, manipur.class);
                    startActivity(intent22);
                }
                break;
            case R.id.mizoram:
                mizoram.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent23 = new Intent(this, mizoram.class);
                    startActivity(intent23);
                }
                break;
            case R.id.nagaland:
                nagaland.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent24 = new Intent(this, nagaland.class);
                    startActivity(intent24);
                }
                break;
            case R.id.odisha:
                odisha.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent25 = new Intent(this, odisha.class);
                    startActivity(intent25);
                }
                break;
            case R.id.punjab:
                punjab.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent26 = new Intent(this, punjab.class);
                    startActivity(intent26);
                }
                break;
            case R.id.puducherry:
                pudhucherry.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent27 = new Intent(this, pudhucherry.class);
                    startActivity(intent27);
                }
                break;
            case R.id.rajasthan:
                rajasthan.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent28 = new Intent(this, rajasthan.class);
                    startActivity(intent28);
                }
                break;
            case R.id.sikkim:
                sikkim.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent29 = new Intent(this, sikkim.class);
                    startActivity(intent29);
                }
                break;
            case R.id.telangana:
                telangana.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent30 = new Intent(this, telungana.class);
                    startActivity(intent30);
                }
                break;
            case R.id.tripura:
                tripura.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent31 = new Intent(this, tripura.class);
                    startActivity(intent31);
                }
                break;
            case R.id.uttar:
                uttarpradesh.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent32 = new Intent(this, uttar_pradesh.class);
                    startActivity(intent32);
                }
                break;
            case R.id.uttarkhand:
                uttarkhand.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent33 = new Intent(this, uttarkhand.class);
                    startActivity(intent33);
                }
                break;
            case R.id.west:
                westbengal.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent34 = new Intent(this, west_bengal.class);
                    startActivity(intent34);
                }
                break;
            case R.id.madhya_pradesh:
                madhya_pradesh.setEnabled(false);
                if(!isonline()){
                    Toast.makeText(getApplicationContext(),"Check Your Interner Connection",Toast.LENGTH_SHORT).show();
                    onResume();
                }
                else {
                    Intent intent35 = new Intent(this, madhya_pradesh.class);
                    startActivity(intent35);
                }
                break;
            case R.id.QandA:
                qanda.setEnabled(false);
                Intent intent36 = new Intent(this,c_question.class);
                startActivity(intent36);
                break;
            case R.id.Symptoms:
                symptoms.setEnabled(false);
                Intent intent37 = new Intent(this,c_symptom.class);
                startActivity(intent37);
                break;
            case R.id.prevention:
                prevention.setEnabled(false);
                Intent intent38 = new Intent(this,corona_prevention.class);
                startActivity(intent38);
                break;
            case R.id.helpline:
                helpline.setEnabled(false);
                Intent intent39 = new Intent(this,corona_helpline.class);
                startActivity(intent39);
                break;
            case R.id.hospital_nearby:
                if(locationenabled()){
                    maps.setEnabled(false);
                    final LocationRequest locationRequest = LocationRequest.create();
                    locationRequest.setInterval(10000);
                    locationRequest.setFastestInterval(1000);
                    locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
                    LocationServices.getFusedLocationProviderClient(MainActivity.this)
                            .requestLocationUpdates(locationRequest,new LocationCallback() {
                                @Override
                                public void onLocationResult(LocationResult locationResult) {
                                    super.onLocationResult(locationResult);
                                    LocationServices.getFusedLocationProviderClient(MainActivity.this)
                                            .removeLocationUpdates(this);
                                    if(locationResult!=null && locationResult.getLocations().size()>0) {
                                        int index = locationResult.getLocations().size() - 1;
                                        double lat = locationResult.getLocations().get(index).getLatitude();
                                        double lng = locationResult.getLocations().get(index).getLongitude();
                                        String url = "geo:"+lat+","+lng+"?q=hospitals near me";
                                        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(url)));
                                    }
                                }
                            },Looper.getMainLooper());
                }else{
                    Toast.makeText(this,"Turn on you location",Toast.LENGTH_SHORT).show();
                    maps.setEnabled(true);
                }



        }

    }
    private boolean locationenabled(){
        LocationManager locationManager= (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) && locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }
    @Override
        protected void onResume(){
        super.onResume();
        qanda=findViewById(R.id.QandA);
        symptoms=findViewById(R.id.Symptoms);
        prevention=findViewById(R.id.prevention);
        helpline=findViewById(R.id.helpline);
        maps=findViewById(R.id.hospital_nearby);


        //statebutton
        andaman=findViewById(R.id.andaman);
        andra=findViewById(R.id.andra);
        arunachal=findViewById(R.id.arunachal);
        assam=findViewById(R.id.assam);
        bihar=findViewById(R.id.bihar);
        chandigarh=findViewById(R.id.chandigarh);
        chattisgarh=findViewById(R.id.chattisgarh);
        delhi=findViewById(R.id.delhi);
        damanddieu=findViewById(R.id.daman_diu);
        tamilnadu=findViewById(R.id.tamil_nadu);
        goa=findViewById(R.id.goa);
        gujarat=findViewById(R.id.gujarat);
        himachal=findViewById(R.id.himachal);
        haryana=findViewById(R.id.haryana);
        jharkhand=findViewById(R.id.jharkhand);
        jammu=findViewById(R.id.jammu);
        karnataka=findViewById(R.id.karnataka);
        kerala=findViewById(R.id.kerala);
        ladakh=findViewById(R.id.ladakh);
        lakshadweep=findViewById(R.id.lakshadweep);
        maharastra=findViewById(R.id.maharastra);
        meghalaya=findViewById(R.id.meghalaya);
        manipur=findViewById(R.id.manipur);
        madhya_pradesh=findViewById(R.id.madhya_pradesh);
        mizoram=findViewById(R.id.mizoram);
        nagaland=findViewById(R.id.nagaland);
        odisha=findViewById(R.id.odisha);
        punjab=findViewById(R.id.punjab);
        pudhucherry=findViewById(R.id.puducherry);
        rajasthan=findViewById(R.id.rajasthan);
        sikkim=findViewById(R.id.sikkim);
        telangana=findViewById(R.id.telangana);
        tripura=findViewById(R.id.tripura);
        uttarpradesh=findViewById(R.id.uttar);
        uttarkhand=findViewById(R.id.uttarkhand);
        westbengal=findViewById(R.id.west);

        qanda.setEnabled(true);
        symptoms.setEnabled(true);
        prevention.setEnabled(true);
        andaman.setEnabled(true);
        andra.setEnabled(true);
        arunachal.setEnabled(true);
        assam.setEnabled(true);
        bihar.setEnabled(true);
        chandigarh.setEnabled(true);
        chattisgarh.setEnabled(true);
        delhi.setEnabled(true);
        damanddieu.setEnabled(true);
        tamilnadu.setEnabled(true);
        goa.setEnabled(true);
        kerala.setEnabled(true);
        gujarat.setEnabled(true);
        haryana.setEnabled(true);
        himachal.setEnabled(true);
        jharkhand.setEnabled(true);
        jammu.setEnabled(true);
        karnataka.setEnabled(true);
        ladakh.setEnabled(true);
        lakshadweep.setEnabled(true);
        maharastra.setEnabled(true);
        meghalaya.setEnabled(true);
        manipur.setEnabled(true);
        madhya_pradesh.setEnabled(true);
        mizoram.setEnabled(true);
        nagaland.setEnabled(true);
        odisha.setEnabled(true);
        punjab.setEnabled(true);
        pudhucherry.setEnabled(true);
        rajasthan.setEnabled(true);
        sikkim.setEnabled(true);
        telangana.setEnabled(true);
        tripura.setEnabled(true);
        uttarkhand.setEnabled(true);
        uttarpradesh.setEnabled(true);
        westbengal.setEnabled(true);
        helpline.setEnabled(true);
        maps.setEnabled(true);
    }
    private boolean isonline() {
        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo()!=null && connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    private void grantPermission() {
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)
                !=PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION},100);
        }
        else{
            getLocation();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 100 && grantResults.length>0){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){
                boolean gpsenabled=false;
                boolean networkenabled=false;
                LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
                try{
                    gpsenabled=lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
                    networkenabled=lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
                }catch (Exception e){}
                if(!gpsenabled){
                    Context context;
                   AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                   dialog.setMessage("Please Enable GPS Location");
                   dialog.setPositiveButton("GPS Settings", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {
                           Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                           MainActivity.this.startActivity(intent);
                       }
                   });
                   dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                       @Override
                       public void onClick(DialogInterface dialog, int which) {

                       }
                   });
                   dialog.show();
                }
                else if(!networkenabled){
                    Context context;
                    AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setMessage("Please Enable Network");
                    dialog.setPositiveButton("Network Settings", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Intent intent = new Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS);
                            MainActivity.this.startActivity(intent);
                        }
                    });
                    dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    dialog.show();
                }
                getLocation();
            }
            else
            {
                grantPermission();
            }
        }
    }

    public void getLocation(){

            progressBar=findViewById(R.id.progressbar);
            progressBar.setVisibility(View.VISIBLE);
            final LocationRequest locationRequest = new LocationRequest();
            locationRequest.setInterval(10000);
            locationRequest.setFastestInterval(1000);
            locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            LocationServices.getFusedLocationProviderClient(MainActivity.this)
                    .requestLocationUpdates(locationRequest,new LocationCallback(){
                        @Override
                        public void onLocationResult(LocationResult locationResult) {
                            super.onLocationResult(locationResult);
                            LocationServices.getFusedLocationProviderClient(MainActivity.this)
                                    .removeLocationUpdates(this);
                            if(locationResult!=null && locationResult.getLocations().size()>0){
                                int index=locationResult.getLocations().size()-1;
                                double latitude=locationResult.getLocations().get(index).getLatitude();
                                double longitude=locationResult.getLocations().get(index).getLongitude();
                                String url="https://api.covid19india.org/v2/state_district_wise.json";
                                String url2 = "https://api.covid19india.org/states_daily.json";
                                String url_test_vacc ="https://api.covid19india.org/v4/min/data.min.json";
                                try{
                                    progressBar.setVisibility(View.VISIBLE);
                                    Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                                    List<Address>  addresses  =geocoder.getFromLocation(latitude,longitude,1);
                                    String x[]=addresses.get(0).getAddressLine(0).split(" ");
                                    state=addresses.get(0).getAdminArea();
                                    city=addresses.get(0).getSubAdminArea();
                                    current_loc=findViewById(R.id.current_location);
                                    curr_activ=findViewById(R.id.current_active);
                                    curr_recov=findViewById(R.id.current_recovered);
                                    curr_death=findViewById(R.id.current_death);
                                    curr_conf=findViewById(R.id.current_confirmed);
                                    String loc =city+","+state;
                                    current_loc.setText(loc);
                                    progressBar.setVisibility(View.GONE);
                                    head=findViewById(R.id.heading);
                                    one=findViewById(R.id.current1);
                                    two=findViewById(R.id.current2);
                                    three=findViewById(R.id.current3);
                                    four=findViewById(R.id.current4);
                                    current_head=findViewById(R.id.current_head);
                                    location=findViewById(R.id.location);


                                    //state daily bix updates
                                    heading_state=findViewById(R.id.heading_state);
                                    current_state_head=findViewById(R.id.current_state_head);
                                    current_state_head.setVisibility(View.VISIBLE);
                                    current_state=findViewById(R.id.current_state);
                                    current_date=findViewById(R.id.current_date);
                                     state_daily_confirmed=findViewById(R.id.state_daily_confirmed);
                                     state_daily_recovered=findViewById(R.id.state_daily_recovered);
                                     state_daily_death=findViewById(R.id.state_daily_death);


                                    RequestQueue volly = Volley.newRequestQueue(MainActivity.this);
                                    final HashMap<String,String> map1 = new HashMap<>();
                                    map1.put("Andaman and Nicobar Islands","AN");
                                    map1.put("Andhra Pradesh","AP");
                                    map1.put("Arunachal Pradesh","AR");
                                    map1.put("Assam","AS");
                                    map1.put("Bihar","BR");
                                    map1.put("Chandigarh","CH");
                                    map1.put("Chhattisgarh","CT");
                                    map1.put("Dadra and Nagar Haveli","DN");
                                    map1.put("Delhi","DL");
                                    map1.put("Goa","GA");
                                    map1.put("Gujarat","GJ");
                                    map1.put("Haryana","HR");
                                    map1.put("Himachal Pradesh","HP");
                                    map1.put("Jammu and Kashmir","JK");
                                    map1.put("Jharkhand","JH");
                                    map1.put("Karnataka","KA");
                                    map1.put("Kerala","KL");
                                    map1.put("Lakshadweep","LD");
                                    map1.put("Ladakh","LA");
                                    map1.put("Madhya Pradesh","MP");
                                    map1.put("Maharashtra","MH");
                                    map1.put("Manipur","MN");
                                    map1.put("Meghalaya","ML");
                                    map1.put("Mizoram","MZ");
                                    map1.put("Nagaland","NL");
                                    map1.put("Odisha","OR");
                                    map1.put("Puducherry","PY");
                                    map1.put("Punjab","PB");
                                    map1.put("Rajasthan","RJ");
                                    map1.put("Sikkim","SK");
                                    map1.put("Tamil Nadu","TN");
                                    map1.put("Telangana","TG");
                                    map1.put("Tripura","TR");
                                    map1.put("Uttar Pradesh","UP");
                                    map1.put("Uttarakhand","UT");
                                    map1.put("West Bengal","WB");
                                    final HashMap<String,String> map = new HashMap<>();
                                    map.put("Andaman and Nicobar Islands","an");
                                    map.put("Andhra Pradesh","ap");
                                    map.put("Arunachal Pradesh","ar");
                                    map.put("Assam","as");
                                    map.put("Bihar","br");
                                    map.put("Chandigarh","ch");
                                    map.put("Chhattisgarh","ct");
                                    map.put("Dadra and Nagar Haveli","dn");
                                    map.put("Daman and Diu","dd");
                                    map.put("Delhi","dl");
                                    map.put("Goa","ga");
                                    map.put("Gujarat","gj");
                                    map.put("Haryana","hr");
                                    map.put("Himachal Pradesh","hp");
                                    map.put("Jammu and Kashmir","jk");
                                    map.put("Jharkhand","jh");
                                    map.put("Karnataka","ka");
                                    map.put("Kerala","kl");
                                    map.put("Lakshadweep","ld");
                                    map.put("Ladakh","la");
                                    map.put("Madhya Pradesh","mp");
                                    map.put("Maharashtra","mh");
                                    map.put("Manipur","mn");
                                    map.put("Meghalaya","ml");
                                    map.put("Mizoram","mz");
                                    map.put("Nagaland","nl");
                                    map.put("Odisha","or");
                                    map.put("Puducherry","py");
                                    map.put("Punjab","pb");
                                    map.put("Rajasthan","rj");
                                    map.put("Sikkim","sk");
                                    map.put("Tamil Nadu","tn");
                                    map.put("Telangana","tg");
                                    map.put("Tripura","tr");
                                    map.put("Uttar Pradesh","up");
                                    map.put("Uttarakhand","ut");
                                    map.put("West Bengal","wb");

                                    JsonObjectRequest request_state_test= new JsonObjectRequest(Request.Method.GET, url_test_vacc, null,
                                            new Response.Listener<JSONObject>() {
                                                @Override
                                                public void onResponse(JSONObject response) {
                                                    String state_name=map1.get(state);
                                                    try {
                                                        JSONObject state_o=response.getJSONObject(state_name).getJSONObject("total");
                                                        String state_test=state_o.getString("tested");
                                                        String state_vacci=state_o.getString("vaccinated");
                                                        state_tested.setText(state_test);
                                                        state_vaccinated.setText(state_vacci);
                                                       statetest.setText(state);
                                                        test1.setVisibility(View.VISIBLE);
                                                        test2.setVisibility(View.VISIBLE);
                                                        current_state_test_vacc.setVisibility(View.VISIBLE);

                                                    } catch (JSONException e) {
                                                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                                    }
                                                }
                                            }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {

                                        }
                                    });

                                    JsonObjectRequest request1 = new JsonObjectRequest(Request.Method.GET, url2, null,
                                            new Response.Listener<JSONObject>() {
                                                @Override
                                                public void onResponse(JSONObject response) {
                                                    try {
                                                        JSONArray array = response.getJSONArray("states_daily");
                                                        int size = array.length();
                                                        int confirmed_daily_pos = size-3;
                                                        int recovered_daily_pos = size-2;
                                                        int death_daily_pos = size-1;
                                                        JSONObject object_confirmed = array.getJSONObject(confirmed_daily_pos);
                                                        JSONObject object_recovered = array.getJSONObject(recovered_daily_pos);
                                                        JSONObject object_death = array.getJSONObject(death_daily_pos);
                                                        heading_state.setText("Your State Covid Daily Updates");
                                                        heading_state.setVisibility(View.VISIBLE);
                                                        current_state.setText(state);
                                                        current_date.setText(object_confirmed.getString("date"));
                                                        String state_name=map.get(state);
                                                        state_daily_confirmed.setText(String.valueOf(object_confirmed.getInt(state_name)));
                                                        state_daily_recovered.setText(String.valueOf(object_recovered.getInt(state_name)));
                                                        state_daily_death.setText(String.valueOf(object_death.getInt(state_name)));

                                                    } catch (JSONException e) {
                                                        e.printStackTrace();
                                                    }
                                                }
                                            }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                            Toast.makeText(MainActivity.this, "Check internet!error", Toast.LENGTH_SHORT).show();
                                        }
                                    });

                                    JsonArrayRequest request= new JsonArrayRequest(Request.Method.GET, url, null,
                                            new Response.Listener<JSONArray>() {
                                                @Override
                                                public void onResponse(JSONArray response) {
                                                    String states[]=new String[response.length()];
                                                    try{
                                                        boolean flag=false;
                                                       for(int i=0;i<response.length();i++){
                                                            JSONObject object = response.getJSONObject(i);
                                                            states[i]=object.getString("state");
                                                            if(states[i].equals(state)){
                                                                JSONArray array = object.getJSONArray("districtData");
                                                                String cityo[]=new String[array.length()];
                                                                for(int j=0;j<array.length();j++){
                                                                    JSONObject cityobject = array.getJSONObject(j);
                                                                    cityo[j]=cityobject.getString("district");
                                                                    if(cityo[j].equals(city)){
                                                                        flag=true;
                                                                        curr_activ.setText(String.valueOf(cityobject.getInt("active")));
                                                                        curr_conf.setText(String.valueOf(cityobject.getInt("confirmed")));
                                                                        curr_recov.setText(String.valueOf(cityobject.getInt("recovered")));
                                                                        curr_death.setText(String.valueOf(cityobject.getInt("deceased")));
                                                                        head.setText("Your District Covid19 Status");
                                                                        head.setVisibility(View.VISIBLE);
                                                                        current_head.setVisibility(View.VISIBLE);
                                                                        location.setVisibility(View.VISIBLE);
                                                                        one.setVisibility(View.VISIBLE);
                                                                        two.setVisibility(View.VISIBLE);
                                                                        three.setVisibility(View.VISIBLE);
                                                                        four.setVisibility(View.VISIBLE);

                                                                    }
                                                                }
                                                            }
                                                        }
                                                       if(!flag){
                                                           head.setText("Yours Location");
                                                           head.setVisibility(View.VISIBLE);
                                                           current_head.setVisibility(View.VISIBLE);
                                                           location.setVisibility(View.VISIBLE);
                                                       }
                                                    }catch (Exception e){e.printStackTrace();}
                                                }
                                            }, new Response.ErrorListener() {
                                        @Override
                                        public void onErrorResponse(VolleyError error) {
                                                Toast.makeText(getApplicationContext(),"Check internet",Toast.LENGTH_SHORT).show();
                                        }
                                    });
                                    progressBar.setVisibility(View.GONE);
                                    volly.add(request);
                                    volly.add(request1);
                                    volly.add(request_state_test);

                                }catch (Exception e){e.printStackTrace();}
                            }
                        }
                    },Looper.getMainLooper());
    }

    public void scrollstate(){
        state_scroll=findViewById(R.id.state_button_scroll);
        state_scroll.fullScroll(HorizontalScrollView.FOCUS_RIGHT);
        state_scroll.fullScroll(HorizontalScrollView.FOCUS_LEFT);
    }

}



