package com.example.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.RealmResultTask;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;
import io.realm.mongodb.mongo.iterable.MongoCursor;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.bson.Document;

import java.util.ArrayList;

public class view_covid_user extends AppCompatActivity {

    TextInputEditText state, district, area;
    SharedPreferences sharedPreferences1;
    SharedPreferences.Editor editor1;
    Button set_b, update_b;

    TableLayout tableLayout;
    TableRow tableRow;

    String app_ID = "coviddb-nrtyu";
    private App app;
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;

    String user_email = "trackcovid21@gmail.com";
    String user_pass = "LL6K9VgKeyJh@AQ";
    ProgressBar loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_covid_user);
        state = findViewById(R.id.State_user);
        district = findViewById(R.id.District_user);
        area = findViewById(R.id.Area_user);
        tableLayout = findViewById(R.id.covid_family_Table);
        loading = findViewById(R.id.family_loading_progressbar);

        Realm.init(this);
        app = new App(new AppConfiguration.Builder(app_ID).build());


        set_b = findViewById(R.id.set_data);
        update_b = findViewById(R.id.update_data);

        sharedPreferences1 = getApplicationContext().getSharedPreferences("Info", Context.MODE_PRIVATE);
        editor1 = sharedPreferences1.edit();

        if (!sharedPreferences1.getBoolean("set_", false)) {
            set_b.setEnabled(true);
            editor1.clear();
            editor1.putString("State", "");
            editor1.putString("District", "");
            editor1.putString("Area", "");
            editor1.putBoolean("set_", false);
            editor1.commit();

        } else {

            set_b.setEnabled(false);
            String s = sharedPreferences1.getString("State", "");
            String d = sharedPreferences1.getString("District", "");
            String a = sharedPreferences1.getString("Area", "");
            state.setText(s);
            district.setText(d);
            area.setText(a);

            covid_person_info();
        }
        set_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String state_name = state.getText().toString().toUpperCase();
                final String district_name = district.getText().toString().toUpperCase();
                final String area_name = area.getText().toString().toUpperCase();

                if (!state_name.isEmpty() && !district_name.isEmpty() && !area_name.isEmpty()) {
                    set_b.setEnabled(false);
                    state.clearFocus();
                    district.clearFocus();
                    area.clearFocus();
                    editor1.putString("State", state_name);
                    editor1.putString("District", district_name);
                    editor1.putString("Area", area_name);
                    editor1.putBoolean("set_", true);
                    editor1.commit();

                    covid_person_info();
                } else {
                    Toast.makeText(getApplicationContext(), "Fields are missing!!!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        update_b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                set_b.setEnabled(true);

                state.setText("");
                district.setText("");
                area.setText("");
                editor1.clear();
                editor1.putString("State", "");
                editor1.putString("District", "");
                editor1.putString("Area", "");
                editor1.putBoolean("set_", false);
                editor1.commit();
                state.requestFocus();

                tableLayout.removeAllViews();


            }
        });


    }

    public void covid_person_info() {
        Credentials credentials = Credentials.emailPassword(user_email, user_pass);

        loading.setVisibility(View.VISIBLE);

        app.loginAsync(credentials, new App.Callback<User>() {
            @Override
            public void onResult(App.Result<User> result) {
                if (result.isSuccess()) {
                    User user = app.currentUser();
                    mongoClient = user.getMongoClient("mongodb-atlas");
                    mongoDatabase = mongoClient.getDatabase("Covid");
                    final MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("UsersData");

                    String s = sharedPreferences1.getString("State", "");
                    String d = sharedPreferences1.getString("District", "");
                    String a = sharedPreferences1.getString("Area", "");

                    Document query = new Document().append("State", s).append("District", d).append("Area", a);
                    RealmResultTask<MongoCursor<Document>> resultTask = mongoCollection.find(query).iterator();

                    resultTask.getAsync(new App.Callback<MongoCursor<Document>>() {
                        @Override
                        public void onResult(App.Result<MongoCursor<Document>> result) {
                            if (result.isSuccess()) {
                                final ArrayList<String> covid_counts = new ArrayList<>();
                                final ArrayList<String> Total_Members = new ArrayList<>();
                                final ArrayList<String> Addres = new ArrayList<>();

                                MongoCursor<Document> curr = result.get();
                                if (!curr.hasNext()) {
                                    Toast.makeText(getApplicationContext(), "Entered Details Not Matched or Not Registered", Toast.LENGTH_SHORT).show();
                                    state.setText("");
                                    district.setText("");
                                    area.setText("");
                                    set_b.setEnabled(true);
                                    state.setFocusable(true);
                                    district.setFocusable(true);
                                    area.setFocusable(true);
                                }
                                boolean infected = false;
                                while (curr.hasNext()) {
                                    Document curr_family = curr.next();
                                    String covid_count = curr_family.getString("Covid Positive Counts");
                                    String total = curr_family.getString("Total Family Members");
                                    String Ad = curr_family.getString("Address");
                                    if (Integer.parseInt(covid_count) > 0) {
                                        covid_counts.add(covid_count);
                                        Total_Members.add(total);
                                        Addres.add(Ad);
                                        infected = true;
                                    }

                                }
                                if (covid_counts.size() > 0) {
                                    for (int i = 0; i < covid_counts.size(); i++) {

                                        View tablerow = LayoutInflater.from(view_covid_user.this).inflate(R.layout.covid_person_info, null, false);
                                        TextView count = tablerow.findViewById(R.id.covid_count_);
                                        TextView total = tablerow.findViewById(R.id.family_total);
                                        TextView add  =tablerow.findViewById(R.id.family_address);
                                        count.setText(covid_counts.get(i));
                                        total.setText(Total_Members.get(i));
                                        add.setText(Addres.get(i));
                                        tableLayout.addView(tablerow);
                                    }
                                } else if(!infected) {
                                    View tablerow = LayoutInflater.from(view_covid_user.this).inflate(R.layout.no_covid_found, null, false);
                                    TextView info = tablerow.findViewById(R.id.no_family_info);
                                    info.setText("Absence of Covid Infection");
                                    tableLayout.addView(tablerow);
                                }
                                loading.setVisibility(View.INVISIBLE);


                            } else {
                                Toast.makeText(view_covid_user.this, result.getError().toString(), Toast.LENGTH_SHORT).show();
                                loading.setVisibility(View.INVISIBLE);
                            }
                        }
                    });
                } else {
                    Toast.makeText(view_covid_user.this, "Invalid Login", Toast.LENGTH_SHORT).show();
                    loading.setVisibility(View.INVISIBLE);
                }
            }
        });

    }
}
