package com.example.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Covid_Predict extends AppCompatActivity {
    TextView assess_info;
    Button yes, no, submit_start;
    TextView questions,result,advice;
    boolean started=false;
    CardView server_status,result_card;
    RequestQueue requestQueue;
    ProgressBar progressBar,server_progress;
    boolean server_connected =false;
    ImageView imageView;
    Switch switch2;

    int qs_no = 0;
    int q=1;
    int refresh=0;
    String[] qs_list = {
            "1. Do you have Breathing Problem or Related illness ?",
            "2. Do you have Fever ?",
            "3. Do you have Dry Cough ?",
            "4. Do you have Sore throat?",
            "5. Dou you have Running Nose",
            "6. Do you have asthma ?",
            "7. Do you have Chronic Lung Disease",
            "8. Do you have Headache ?",
            "9. Do you have Chronic Heart Disease ?",
            "10. Do you have Diabetes ?",
            "11. Do you have Hyper Tension ?",
            "12. Do you have Fatigue?",
            "13. Do you have Gastrointestinal Problems ?",
            "14. Have you made Abroad travel recently ?",
            "15. Have you made any Contact with COVID Patient ?",
            "16. Have you attended any Large Gathering recently ?",
            "17. Have you visited any public exposed covid places recently ?",
            "18. Any of your family members working in Public Exposed Places ?",
            "19. Are you Wearing Masks in public places",
            "20. Are you properly Sanitizing when you came from Outdoors"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid__predict);
        assess_info = findViewById(R.id.assess_info);
        assess_info.setSelected(true);
        yes = findViewById(R.id.assess_yes);
        no = findViewById(R.id.assess_no);
        submit_start = findViewById(R.id.assess_submit_start);
        questions=findViewById(R.id.questions);

        result_card=findViewById(R.id.result_cardview);
        result=findViewById(R.id.result_text);
        advice=findViewById(R.id.advice_text);
        progressBar=findViewById(R.id.progress_covid);
        imageView=findViewById(R.id.server_info);
        requestQueue= Volley.newRequestQueue(this);
        switch2=findViewById(R.id.switch_server);
        server_progress=findViewById(R.id.server_progress);

        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    server_progress.setVisibility(View.VISIBLE);
                    StringRequest c = new StringRequest(Request.Method.GET, "https://covidsymptomspredict.herokuapp.com/", new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Toast.makeText(getApplicationContext(),"Server Connected Successfully",Toast.LENGTH_SHORT).show();
                            switch2.setChecked(true);
                            server_connected=true;
                            server_progress.setVisibility(View.GONE);

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getApplicationContext(),"Server Not Connected. Plz try again",Toast.LENGTH_SHORT).show();
                            switch2.setChecked(false);
                            server_connected=false;
                            server_progress.setVisibility(View.GONE);
                        }
                    });
                    requestQueue.add(c);


                }
                else
                {
                    server_connected=false;
                    Toast.makeText(Covid_Predict.this, "Enable Server to take covid assessment", Toast.LENGTH_SHORT).show();
                }

            }
        });



        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast toast= new Toast(Covid_Predict.this);
                toast.setDuration(Toast.LENGTH_LONG);
                View layout= LayoutInflater.from(Covid_Predict.this).inflate(R.layout.toast_layout,null, false);
                TextView l1= layout.findViewById(R.id.toast_text);
                l1.setText("Start your test only when server is on indicated by green colour. Red color shows server is iff");
                toast.setView(layout);
                toast.setGravity(Gravity.BOTTOM,0,0);
                toast.show();

            }
        });
        final JSONObject data=new JSONObject();
        try {
            data.put("p1",Integer.MAX_VALUE);
            data.put("p2",Integer.MAX_VALUE);
            data.put("p3",Integer.MAX_VALUE);
            data.put("p4",Integer.MAX_VALUE);
            data.put("p5",Integer.MAX_VALUE);
            data.put("p6",Integer.MAX_VALUE);
            data.put("p7",Integer.MAX_VALUE);
            data.put("p8",Integer.MAX_VALUE);
            data.put("p9",Integer.MAX_VALUE);
            data.put("p10",Integer.MAX_VALUE);
            data.put("p11",Integer.MAX_VALUE);
            data.put("p12",Integer.MAX_VALUE);
            data.put("p13",Integer.MAX_VALUE);
            data.put("p14",Integer.MAX_VALUE);
            data.put("p15",Integer.MAX_VALUE);
            data.put("p16",Integer.MAX_VALUE);
            data.put("p17",Integer.MAX_VALUE);
            data.put("p18",Integer.MAX_VALUE);
            data.put("p19",Integer.MAX_VALUE);
            data.put("p20",Integer.MAX_VALUE);

        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_LONG).show();
        }





        submit_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(refresh==1)
                {
                    questions.setText("Are you Ready for the questions ?");
                    submit_start.setText("START");
                    result_card.setVisibility(View.INVISIBLE);
                    qs_no=0;
                    q=1;
                    started=false;
                    refresh=0;
                }
                else if(!started)
                {
                    if(!server_connected)
                    {
                        Toast.makeText(getApplicationContext(),"Connect to Server & try again",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        started = true;
                        questions.setText(qs_list[qs_no]);
                        submit_start.setVisibility(View.GONE);
                        yes.setVisibility(View.VISIBLE);
                        no.setVisibility(View.VISIBLE);
                        qs_no++;
                    }
                }
                else
                {
                    String url="https://covidsymptomspredict.herokuapp.com/covid_predict/";
                    questions.setText("Please wait for the results..");
                    progressBar.setVisibility(View.VISIBLE);
                    JsonObjectRequest x = new JsonObjectRequest(Request.Method.POST, url, data, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                String covid= response.getString("covid");
                                switch(covid)
                                {
                                    case "1":
                                        progressBar.setVisibility(View.INVISIBLE);
                                        result_card.setBackgroundColor(Color.parseColor("#FFEB4A4A"));
                                        result.setText("High Risk of Covid Infection");
                                        advice.setText("Please check nearby Hospitals");
                                        result_card.setVisibility(View.VISIBLE);

                                        break;
                                    case "0":
                                        progressBar.setVisibility(View.INVISIBLE);
                                        result_card.setBackgroundColor(Color.parseColor("#3DA83F"));
                                        result.setText("Low Risk of Covid Infection");
                                        advice.setText("Stay Safe & Follow safety Protocols");
                                        result_card.setVisibility(View.VISIBLE);

                                        break;

                                }
                            } catch (JSONException e) {
                                Toast.makeText(Covid_Predict.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(Covid_Predict.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                    submit_start.setText("Another Test ?");
                    refresh=1;


                    requestQueue.add(x);

                }
            }
        });
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(qs_no>=20)
                {
                    String qs = "p" + q;
                    try {
                        data.put(qs, 1);
                        questions.setText("Submit to get the results..");
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    yes.setVisibility(View.GONE);
                    no.setVisibility(View.GONE);
                    submit_start.setText("Submit");
                    submit_start.setVisibility(View.VISIBLE);
                }
                else {
                    String qs = "p" + q;
                    q++;
                    try {
                        data.put(qs, 1);
                        questions.setText(qs_list[qs_no]);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    qs_no++;

                }
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(qs_no>=20)
                {
                    String qs = "p" + q;
                    try {
                        data.put(qs, 0);
                        questions.setText("Submit to get the results..");
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    yes.setVisibility(View.GONE);
                    no.setVisibility(View.GONE);
                    submit_start.setText("Submit");
                    submit_start.setVisibility(View.VISIBLE);
                }
                else {
                    String qs = "p" + q;
                    q++;
                    try {
                        data.put(qs, 0);
                        questions.setText(qs_list[qs_no]);
                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    qs_no++;

                }
            }

        });


    }
}
