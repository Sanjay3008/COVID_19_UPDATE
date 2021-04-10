package com.example.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
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
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.utils.ViewPortHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class jammu extends AppCompatActivity implements View.OnClickListener {

    TextView c,r,d,a,dailyy,test,vacci;
    RequestQueue requestQueue;
    BarChart tnline;
    TableLayout tntable;
    Button con_bt,death_bt,recov_bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jammu);

//        tnline=findViewById(R.id.tn_line_chart);

        con_bt=findViewById(R.id.confirmed_jammu_khasmir_g);
        recov_bt=findViewById(R.id.recovered_jammu_khasmir_g);
        death_bt=findViewById(R.id.jammu_khasmir_death_g);

        con_bt.setOnClickListener(this);
        recov_bt.setOnClickListener(this);
        death_bt.setOnClickListener(this);
        requestQueue= Volley.newRequestQueue(this);
        tntable=findViewById(R.id.jammu_khasmir_state_table);

        c=findViewById(R.id.jammu_khasmir_confirmed);
        r=findViewById(R.id.jammu_khasmir_recovered);
        d=findViewById(R.id.jammu_khasmir_death);
        a=findViewById(R.id.jammu_khasmir_active);

        String url_test_vacc ="https://api.covid19india.org/v4/min/data.min.json";

        test=findViewById(R.id.tested_counts_jam);
        vacci=findViewById(R.id.vaccinated_counts_jam);
        JsonObjectRequest request_test= new JsonObjectRequest(Request.Method.GET, url_test_vacc, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject TT= response.getJSONObject("JK").getJSONObject("total");
                    String tested= TT.getString("tested");
                    String va=TT.getString("vaccinated");
                    test.setText(tested);
                    vacci.setText(va);
                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),error.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(request_test);


        String url="https://api.covid19india.org/v2/state_district_wise.json";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                String districtname[];
                int active[];
                int confirmed[];
                int recovered[];
                int death[];
                try {
                    JSONObject tn = response.getJSONObject(15);
                    JSONArray districtData= tn.getJSONArray("districtData");
                    int length=districtData.length();
                    districtname=new String[length];
                    confirmed=new int[length];
                    active=new int[length];
                    recovered=new int[length];
                    death=new int[length];
                    for(int i=0;i<length;i++){
                        JSONObject district = districtData.getJSONObject(i);
                        districtname[i]=district.getString("district");
                        active[i]=district.getInt("active");
                        confirmed[i]=district.getInt("confirmed");
                        recovered[i]=district.getInt("recovered");
                        death[i]=district.getInt("deceased");
                    }
                    int totalactive=0;
                    int totalconfirmed=0;
                    int totalrecovered=0;
                    int totaldeath=0;
                    for (int i:active) {
                        totalactive+=i;
                    }
                    for (int i:confirmed) {
                        totalconfirmed+=i;
                    }
                    for (int i:recovered) {
                        totalrecovered+=i;
                    }
                    for (int i:death) {
                        totaldeath+=i;
                    }
                    c.setText(String.valueOf(totalconfirmed));
                    a.setText(String.valueOf(totalactive));
                    r.setText(String.valueOf(totalrecovered));
                    d.setText(String.valueOf(totaldeath));

                    for(int i=0;i<1;i++) {
                        View  tablerow = LayoutInflater.from(jammu.this).inflate(R.layout.tn_row_table,null,false);
                        TextView d_name = tablerow.findViewById(R.id.tn_district_name);
                        TextView d_active = tablerow.findViewById(R.id.tn_district_active);
                        TextView d_recover = tablerow.findViewById(R.id.tn_district_recovered);
                        TextView d_confirmed = tablerow.findViewById(R.id.tn_district_confirmed);
                        TextView d_death = tablerow.findViewById(R.id.tn_district_death);
                        d_name.setText("DISTRICT");
                        d_name.setTextColor(Color.rgb(0,0,0));
                        d_active.setText("ACTIVE");
                        d_active.setTextColor(Color.rgb(221,44,0));
                        d_recover.setText("RECOVERED");
                        d_recover.setTextColor(Color.rgb(45,125,50));
                        d_confirmed.setText("CONFIRMED");
                        d_confirmed.setTextColor(Color.rgb(239,108,0));
                        d_death.setText("DEATH");
                        d_death.setTextColor(Color.rgb(27,153,253));
                        tntable.addView(tablerow);
                    }
                    for(int i=0;i<length;i++){
                        View  tablerow = LayoutInflater.from(jammu.this).inflate(R.layout.tn_row_table,null,false);
                        TextView d_name = tablerow.findViewById(R.id.tn_district_name);
                        TextView d_active = tablerow.findViewById(R.id.tn_district_active);
                        TextView d_recover = tablerow.findViewById(R.id.tn_district_recovered);
                        TextView d_confirmed = tablerow.findViewById(R.id.tn_district_confirmed);
                        TextView d_death = tablerow.findViewById(R.id.tn_district_death);
                        d_name.setText(districtname[i]);
                        d_active.setText(String.valueOf(active[i]));
                        d_recover.setText(String.valueOf(recovered[i]));
                        d_confirmed.setText(String.valueOf(confirmed[i]));
                        d_death.setText(String.valueOf(death[i]));
                        tntable.addView(tablerow);
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

    }
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.confirmed_jammu_khasmir_g:
                String URL = "https://api.covid19india.org/states_daily.json";
                RequestQueue requestQueue1 = Volley.newRequestQueue(jammu.this);

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String date_con[];
                        tnline = findViewById(R.id.jammu_khasmir_bar_chart);
                        int con[];
                        ArrayList<String> date = new ArrayList<>();
                        ArrayList<BarEntry> dataValues = new ArrayList<>();
                        try {
                            JSONArray tn = response.getJSONArray("states_daily");
                            int i = 0, j = 0;
                            con = new int[(tn.length()) / 3];
                            while (i < tn.length()) {
                                JSONObject daily = tn.getJSONObject(i);
                                date.add(daily.getString("date"));
                                dataValues.add(new BarEntry(j, daily.getInt("jk")));
                                i = i + 3;
                                j++;
                            }

                            BarDataSet set = new BarDataSet(dataValues, "Confirmed Daily");
                            set.setColor(Color.rgb(245, 40, 36));
                            BarData data = new BarData(set);
                            data.setBarWidth(0.9f);
                            XAxis axis = tnline.getXAxis();
                            axis.setPosition(XAxis.XAxisPosition.TOP);
                            axis.setDrawGridLines(false);
                            axis.setDrawAxisLine(false);
                            axis.setGranularity(1f);
                            axis.setLabelCount(date.size());
                            axis.setValueFormatter(new IndexAxisValueFormatter(date));
                            axis.setLabelRotationAngle(270);
                            tnline.setVisibility(View.VISIBLE);
                            tnline.setData(data);
                            tnline.animateY(2000);
                            tnline.setVisibleXRangeMaximum(10);
                            tnline.setDragEnabled(true);
                            tnline.moveViewToX(data.getEntryCount());
                            tnline.invalidate();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Error! Check Your Internet Connection and try again Later",Toast.LENGTH_SHORT).show();

                    }
                });
                requestQueue1.add(jsonObjectRequest);
                break;
            case R.id.recovered_jammu_khasmir_g:
                String URL1 = "https://api.covid19india.org/states_daily.json";
                RequestQueue requestQueue2 = Volley.newRequestQueue(jammu.this);

                JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.GET, URL1, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String date_con[];
                        tnline = findViewById(R.id.jammu_khasmir_bar_chart);
                        int con[];
                        ArrayList<String> date = new ArrayList<>();
                        ArrayList<BarEntry> dataValues = new ArrayList<>();
                        try {
                            JSONArray tn = response.getJSONArray("states_daily");
                            int i = 1, j = 0;
                            while (i < tn.length()) {
                                JSONObject daily = tn.getJSONObject(i);
                                date.add(daily.getString("date"));
                                dataValues.add(new BarEntry(j, daily.getInt("jk")));
                                i = i + 3;
                                j++;
                            }

                            BarDataSet set = new BarDataSet(dataValues, "Recovered Daily");
                            set.setColor(Color.rgb(98, 175, 43));
                            BarData data = new BarData(set);
                            data.setBarWidth(0.9f);
                            XAxis axis = tnline.getXAxis();
                            axis.setPosition(XAxis.XAxisPosition.TOP);
                            axis.setDrawGridLines(false);
                            axis.setDrawAxisLine(false);
                            axis.setGranularity(1f);
                            axis.setLabelCount(date.size());
                            axis.setValueFormatter(new IndexAxisValueFormatter(date));
                            axis.setLabelRotationAngle(270);
                            tnline.setVisibility(View.VISIBLE);
                            tnline.setData(data);
                            tnline.animateY(2000);
                            tnline.setVisibleXRangeMaximum(10);
                            tnline.setDragEnabled(true);
                            tnline.moveViewToX(data.getEntryCount());
                            tnline.invalidate();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Error! Check Your Internet Connection and try again Later",Toast.LENGTH_SHORT).show();

                    }
                });
                requestQueue2.add(jsonObjectRequest1);
                break;
            case R.id.jammu_khasmir_death_g:
                String URL2 = "https://api.covid19india.org/states_daily.json";
                RequestQueue request3 = Volley.newRequestQueue(jammu.this);

                JsonObjectRequest jsonObjectRequest2 = new JsonObjectRequest(Request.Method.GET, URL2, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String date_con[];
                        tnline = findViewById(R.id.jammu_khasmir_bar_chart);
                        int con[];
                        ArrayList<String> date = new ArrayList<>();
                        ArrayList<BarEntry> dataValues = new ArrayList<>();
                        try {
                            JSONArray tn = response.getJSONArray("states_daily");
                            int i = 2, j = 0;
                            while (i < tn.length()) {
                                JSONObject daily = tn.getJSONObject(i);
                                date.add(daily.getString("date"));
                                dataValues.add(new BarEntry(j, daily.getInt("jk")));
                                i = i + 3;
                                j++;
                            }

                            BarDataSet set = new BarDataSet(dataValues, "Death Daily");
                            set.setColor(Color.rgb(131, 132, 136));
                            BarData data = new BarData(set);
                            data.setBarWidth(0.9f);
                            XAxis axis = tnline.getXAxis();
                            axis.setPosition(XAxis.XAxisPosition.TOP);
                            axis.setDrawGridLines(false);
                            axis.setDrawAxisLine(false);
                            axis.setGranularity(1f);
                            axis.setLabelCount(date.size());
                            axis.setValueFormatter(new IndexAxisValueFormatter(date));
                            axis.setLabelRotationAngle(270);
                            tnline.setVisibility(View.VISIBLE);
                            tnline.setData(data);
                            tnline.animateY(2000);
                            tnline.setVisibleXRangeMaximum(10);
                            tnline.setDragEnabled(true);
                            tnline.moveViewToX(data.getEntryCount());
                            tnline.invalidate();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(),"Error! Check Your Internet Connection and try again Later",Toast.LENGTH_SHORT).show();
                    }
                });
                request3.add(jsonObjectRequest2);
                break;
        }
    }
}
