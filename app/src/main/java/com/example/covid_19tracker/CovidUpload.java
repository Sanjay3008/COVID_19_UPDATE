package com.example.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;
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
import io.realm.mongodb.mongo.result.InsertOneResult;

import android.app.ActivityManager;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputEditText;

import org.bson.Document;

import java.security.InvalidKeyException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.regex.Pattern;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


public class CovidUpload extends AppCompatActivity {

    String app_ID="coviddb-nrtyu";
    private  App app;
    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;



    TextInputEditText p1,p2,p3,p4,p5,p6,p7,p8,p9,p10;
    Button upload_database;
    ProgressBar progressBar;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_covid_upload2);
        p1=findViewById(R.id.Famil_Id);
        p2=findViewById(R.id.Total_Family_Members);
        p3=findViewById(R.id.Covid_Positive_Count);
        p4=findViewById(R.id.State);
        p5=findViewById(R.id.District);
        p6=findViewById(R.id.Area);
        p7=findViewById(R.id.pincode);
        p8=findViewById(R.id.admin_email);
        p9=findViewById(R.id.admin_pass);
        p10 = findViewById(R.id.Address);
        upload_database=findViewById(R.id.Upload_Database);
        progressBar=findViewById(R.id.progress_upload);


        Realm.init(this);
        app = new App(new AppConfiguration.Builder(app_ID).build());




        upload_database.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);

                final String Family_ID = p1.getText().toString().toUpperCase();
                final String Total = p2.getText().toString().toUpperCase();
                final String Covid_positive =p3.getText().toString().toUpperCase();
                final String address = p10.getText().toString().toUpperCase();
                final String State = p4.getText().toString().toUpperCase();
                final String District =p5.getText().toString().toUpperCase();
                final String Area = p6.getText().toString().toUpperCase();
                final String Pincode = p7.getText().toString().toUpperCase();
                final String A_Email = p8.getText().toString();
                final String A_Pass = p9.getText().toString();




                if(Family_ID.length()<8)
                {
                    Toast.makeText(CovidUpload.this, "Please Enter Valid Family ID of length 8", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
                else if(!Family_ID.substring(0,6).equals("INDGOV"))
                {
                    Toast.makeText(getApplicationContext(),"Family Id not matched. Please ensure that Correct Family Id is given",Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
                else if (!Family_ID.isEmpty() && !Total.isEmpty() && !Covid_positive.isEmpty() && !State.isEmpty() && !District.isEmpty()
                        && !Area.isEmpty() && !Pincode.isEmpty() && !A_Email.isEmpty() && !A_Pass.isEmpty() && !address.isEmpty()) {
                    Credentials credentials = Credentials.emailPassword(A_Email, A_Pass);

                    app.loginAsync(credentials, new App.Callback<User>() {
                        @Override
                        public void onResult(App.Result<User> result) {
                            if (result.isSuccess()) {

                                Toast.makeText(getApplicationContext(), "Logged", Toast.LENGTH_SHORT).show();
                                User user = app.currentUser();
                                mongoClient = user.getMongoClient("mongodb-atlas");
                                mongoDatabase = mongoClient.getDatabase("Covid");
                                final MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("UsersData");
//
                                Document query = new Document().append("Family ID",Family_ID);
                                mongoCollection.findOne(query).getAsync(new App.Callback<Document>() {
                                    @Override
                                    public void onResult(App.Result<Document> result) {
                                        if(result.isSuccess())
                                        {
                                            boolean duplicate=false;

                                            if(result.get()!=null)
                                            {
                                                Document curr=result.get();
                                                if(curr.getString("Family ID").equals(Family_ID))
                                                {
                                                    duplicate=true;
                                                    Toast.makeText(CovidUpload.this, "Duplicate!!! Family Id Already Found", Toast.LENGTH_SHORT).show();
                                                    progressBar.setVisibility(View.GONE);
                                                }
                                            }

                                            if(!duplicate)
                                            {
                                                mongoCollection.insertOne(new Document("email", A_Email).append("Family ID", Family_ID).append("Total Family Members", Total)
                                                        .append("Covid Positive Counts", Covid_positive).append("Address",address).append("State", State).append("District", District)
                                                        .append("Area", Area).append("Pincode", Pincode)).getAsync(new App.Callback<InsertOneResult>() {
                                                    @Override
                                                    public void onResult(App.Result<InsertOneResult> result) {
                                                        if (result.isSuccess()) {
                                                            Toast.makeText(CovidUpload.this, "Uploaded succesfully", Toast.LENGTH_SHORT).show();
                                                            p1.setText("");
                                                            p2.setText("");
                                                            p3.setText("");
                                                            p4.setText("");
                                                            p5.setText("");
                                                            p6.setText("");
                                                            p7.setText("");
                                                            p8.setText("");
                                                            p9.setText("");
                                                            p10.setText("");
                                                            p1.requestFocus();
                                                            progressBar.setVisibility(View.GONE);

                                                        } else {
                                                            Toast.makeText(CovidUpload.this, "Upload failed!", Toast.LENGTH_SHORT).show();
                                                            progressBar.setVisibility(View.GONE);
                                                        }
                                                    }
                                                });
                                            }
                                        }
                                    }
                                });


                                }
//

                            else {
                                Toast.makeText(CovidUpload.this, "Logging Failed. Check Admin Email or Password", Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);
                            }
                        }
                    });


                }
                else
                {
                    Toast.makeText(CovidUpload.this, "Some fields are missing. Please Check & try again", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);
                }
            }
        });







    }
}
