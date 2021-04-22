package com.example.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;
import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;
import io.realm.mongodb.mongo.MongoClient;
import io.realm.mongodb.mongo.MongoCollection;
import io.realm.mongodb.mongo.MongoDatabase;
import io.realm.mongodb.mongo.result.UpdateResult;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import org.bson.Document;

public class update_covid extends AppCompatActivity {
    String app_ID="coviddb-nrtyu";
    private App app;
    ProgressBar progressBar_u;
    TextInputEditText u1,u2,u3,u4;
    Button update_database;

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_covid);

        Realm.init(this);
        app = new App(new AppConfiguration.Builder(app_ID).build());

        progressBar_u=findViewById(R.id.progress_update);
        update_database=findViewById(R.id.Update_Database);

        u1=findViewById(R.id.Family_Id_update);
        u2=findViewById(R.id.Covid_Positive_Count_update);
        u3=findViewById(R.id.admin_email_update);
        u4=findViewById(R.id.admin_pass_update);

        update_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar_u.setVisibility(View.VISIBLE);
                final String Family_ID_U = u1.getText().toString();
                final String Covid_positive_U = u2.getText().toString();
                final String A_Email_U = u3.getText().toString();
                final String A_Pass_U = u4.getText().toString();

                if(!Family_ID_U.substring(0,6).equals("INDGOV"))
                {
                    Toast.makeText(getApplicationContext(),"Enter a valid Family Id",Toast.LENGTH_SHORT).show();
                }

                else if (!Family_ID_U.isEmpty() && !Covid_positive_U.isEmpty() && !A_Email_U.isEmpty() && !A_Pass_U.isEmpty())
                {
                    Credentials credentials = Credentials.emailPassword(A_Email_U, A_Pass_U);

                    app.loginAsync(credentials, new App.Callback<User>() {
                        @Override
                        public void onResult(App.Result<User> result) {
                            if (result.isSuccess()) {
                                User user = app.currentUser();
                                mongoClient = user.getMongoClient("mongodb-atlas");
                                mongoDatabase = mongoClient.getDatabase("Covid");
                                final MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("UsersData");

                                final Document query = new Document().append("Family ID",Family_ID_U);


                                mongoCollection.findOne(query).getAsync(new App.Callback<Document>() {
                                    @Override
                                    public void onResult(App.Result<Document> result) {
                                        if (result.isSuccess()) {

                                            if(result.get()!=null)
                                            {
                                                Document curr=result.get();

                                                if(curr.getString("Family ID").equals(Family_ID_U) )
                                                {
                                                    if(!curr.getString("email").equals(A_Email_U))
                                                    {
                                                        Toast.makeText(update_covid.this, "Entered Admin don't have rights to Update!", Toast.LENGTH_SHORT).show();
                                                        u3.setText("");
                                                        u4.setText("");
                                                        progressBar_u.setVisibility(View.GONE);
                                                    }
                                                    else {
                                                        Toast.makeText(update_covid.this, "User Found", Toast.LENGTH_SHORT).show();
                                                        curr.append("Covid Positive Counts", Covid_positive_U);
                                                        mongoCollection.updateOne(query, curr).getAsync(new App.Callback<UpdateResult>() {
                                                            @Override
                                                            public void onResult(App.Result<UpdateResult> result_u) {
                                                                if (result_u.isSuccess()) {
                                                                    Toast.makeText(update_covid.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                                                                    u1.setText("");
                                                                    u2.setText("");
                                                                    u3.setText("");
                                                                    u4.setText("");
                                                                    u1.requestFocus();
                                                                    progressBar_u.setVisibility(View.GONE);
                                                                } else {
                                                                    Toast.makeText(update_covid.this, "Update Failed", Toast.LENGTH_SHORT).show();
                                                                    progressBar_u.setVisibility(View.GONE);
                                                                }
                                                            }
                                                        });
                                                    }
                                                }
                                                else
                                                {
                                                    Toast.makeText(update_covid.this, "Family Id Not Found", Toast.LENGTH_SHORT).show();
                                                    progressBar_u.setVisibility(View.GONE);
                                                }
                                            }
                                            else
                                            {
                                                Toast.makeText(update_covid.this, "Family Id Not Found", Toast.LENGTH_SHORT).show();
                                                progressBar_u.setVisibility(View.GONE);
                                            }

                                        }
                                        else
                                        {
                                            Toast.makeText(update_covid.this, "Invalid Admin. Sign In Failed", Toast.LENGTH_SHORT).show();
                                            progressBar_u.setVisibility(View.GONE);
                                        }
                                    }
                                });
//
                            }
                        }
                    });
                }
            }

        });






    }
}
