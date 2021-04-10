package com.example.covid_19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class corona_helpline extends AppCompatActivity implements View.OnClickListener {

    Button share, tamilnadu, andaman, andra, arunachal, assam, bihar, chandigarh, chattisgarh, delhi, damanddieu, goa, gujarat, himachal, haryana, jharkhand, jammu, karnataka, kerala, ladakh, lakshadweep, maharastra, meghalaya, manipur, madhya_pradesh, mizoram, nagaland, odisha, punjab, pudhucherry, rajasthan, sikkim, telangana, tripura, uttarpradesh, uttarkhand, westbengal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona_helpline);

        andaman = findViewById(R.id.andaman_phone);
        andra = findViewById(R.id.andra_phone);
        arunachal = findViewById(R.id.arunachal_phone);
        assam = findViewById(R.id.assam_phone);
        bihar = findViewById(R.id.bihar_phone);
        chandigarh = findViewById(R.id.chandigarh_phone);
        chattisgarh = findViewById(R.id.chattis_phone);
        delhi = findViewById(R.id.delhi_phone);
        damanddieu = findViewById(R.id.daman_diu_phone);
        tamilnadu = findViewById(R.id.tamil_nadu_phone);
        goa = findViewById(R.id.goa_phone);
        gujarat = findViewById(R.id.gujarat_phone);
        himachal = findViewById(R.id.himachal_phone);
        haryana = findViewById(R.id.haryana_phone);
        jharkhand = findViewById(R.id.jharkhand_phone);
        jammu = findViewById(R.id.jammu_phone);
        karnataka = findViewById(R.id.karnataka_phone);
        kerala = findViewById(R.id.kerala_phone);
        ladakh = findViewById(R.id.ladakh_phone);
        lakshadweep = findViewById(R.id.lakshadweep_phone);
        maharastra = findViewById(R.id.maharastra_phone);
        meghalaya = findViewById(R.id.meghalaya_phone);
        manipur = findViewById(R.id.manipur_phone);
        madhya_pradesh = findViewById(R.id.madhya_phone);
        mizoram = findViewById(R.id.mizoram_phone);
        nagaland = findViewById(R.id.nagaland_phone);
        odisha = findViewById(R.id.odisha_phone);
        punjab = findViewById(R.id.punjab_phone);
        pudhucherry = findViewById(R.id.pudhucherry_phone);
        rajasthan = findViewById(R.id.rajasthan_phone);
        sikkim = findViewById(R.id.sikkim_phone);
        telangana = findViewById(R.id.telangana_phone);
        tripura = findViewById(R.id.tripura_phone);
        uttarpradesh = findViewById(R.id.uttar_pradesh_phone);
        uttarkhand = findViewById(R.id.uttarkhand_phone);
        westbengal = findViewById(R.id.west1_phone);


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


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tamil_nadu_phone:
                tamilnadu.setEnabled(false);
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+"04429510500"));
                startActivity(intent);
                break;
            case R.id.andaman_phone:
                andaman.setEnabled(false);
                Intent intent1 =  new Intent(Intent.ACTION_DIAL);
                intent1.setData(Uri.parse("tel:"+"03192232102"));
                startActivity(intent1);
                break;
            case R.id.andra_phone:
                andra.setEnabled(false);
                Intent intent2 = new Intent(Intent.ACTION_DIAL);
                intent2.setData(Uri.parse("tel:"+"08662410978"));
                startActivity(intent2);
                break;
            case R.id.arunachal_phone:
                arunachal.setEnabled(false);
                Intent intent3 = new Intent(Intent.ACTION_DIAL);
                intent3.setData(Uri.parse("tel:"+"9436055743"));
                startActivity(intent3);
                break;
            case R.id.assam_phone:
                assam.setEnabled(false);
                Intent intent4 = new Intent(Intent.ACTION_DIAL);
                intent4.setData(Uri.parse("tel:"+"6913347770"));
                startActivity(intent4);
                break;
            case R.id.bihar_phone:
                bihar.setEnabled(false);
                Intent intent5 = new Intent(Intent.ACTION_DIAL);
                intent5.setData(Uri.parse("tel:"+"104"));
                startActivity(intent5);
                break;
            case R.id.chandigarh_phone:
                chandigarh.setEnabled(false);
                Intent intent6 = new Intent(Intent.ACTION_DIAL);
                intent6.setData(Uri.parse("tel:"+"9779558282"));
                startActivity(intent6);
                break;
            case R.id.chattis_phone:
                chattisgarh.setEnabled(false);
                Intent intent7 = new Intent(Intent.ACTION_DIAL);
                intent7.setData(Uri.parse("tel:"+"104"));
                startActivity(intent7);
                break;
            case R.id.delhi_phone:
                delhi.setEnabled(false);
                Intent intent8 = new Intent(Intent.ACTION_DIAL);
                intent8.setData(Uri.parse("tel:"+"01122307145"));
                startActivity(intent8);
                break;
            case R.id.daman_diu_phone:
                damanddieu.setEnabled(false);
                Intent intent9 = new Intent(Intent.ACTION_DIAL);
                intent9.setData(Uri.parse("tel:"+"104"));
                startActivity(intent9);
                break;
            case R.id.goa_phone:
                goa.setEnabled(false);
                Intent intent10 = new Intent(Intent.ACTION_DIAL);
                intent10.setData(Uri.parse("tel:"+"104"));
                startActivity(intent10);
                break;
            case R.id.gujarat_phone:
                gujarat.setEnabled(false);
                Intent intent11 = new Intent(Intent.ACTION_DIAL);
                intent11.setData(Uri.parse("tel:"+"104"));
                startActivity(intent11);
                break;
            case R.id.himachal_phone:
                himachal.setEnabled(false);
                Intent intent12 = new Intent(Intent.ACTION_DIAL);
                intent12.setData(Uri.parse("tel:"+"104"));
                startActivity(intent12);
                break;
            case R.id.haryana_phone:
                haryana.setEnabled(false);
                Intent intent13 = new Intent(Intent.ACTION_DIAL);
                intent13.setData(Uri.parse("tel:"+"8558893911"));
                startActivity(intent13);
                break;
            case R.id.jharkhand_phone:
                jharkhand.setEnabled(false);
                Intent intent14 = new Intent(Intent.ACTION_DIAL);
                intent14.setData(Uri.parse("tel:"+"104"));
                startActivity(intent14);
                break;
            case R.id.jammu_phone:
                jammu.setEnabled(false);
                Intent intent15 = new Intent(Intent.ACTION_DIAL);
                intent15.setData(Uri.parse("tel:"+"01912520982"));
                startActivity(intent15);
                break;
            case R.id.karnataka_phone:
                karnataka.setEnabled(false);
                Intent intent16 = new Intent(Intent.ACTION_DIAL);
                intent16.setData(Uri.parse("tel:"+"104"));
                startActivity(intent16);
                break;
            case R.id.kerala_phone:
                kerala.setEnabled(false);
                Intent intent17 = new Intent(Intent.ACTION_DIAL);
                intent17.setData(Uri.parse("tel:"+"04712552056"));
                startActivity(intent17);
                break;
            case R.id.ladakh_phone:
                ladakh.setEnabled(false);
                Intent intent18 = new Intent(Intent.ACTION_DIAL);
                intent18.setData(Uri.parse("tel:"+"01982256462"));
                startActivity(intent18);
                break;
            case R.id.lakshadweep_phone:
                lakshadweep.setEnabled(false);
                Intent intent19 = new Intent(Intent.ACTION_DIAL);
                intent19.setData(Uri.parse("tel:"+"104"));
                startActivity(intent19);
                break;
            case R.id.maharastra_phone:
                maharastra.setEnabled(false);
                Intent intent20 = new Intent(Intent.ACTION_DIAL);
                intent20.setData(Uri.parse("tel:"+"02026127394"));
                startActivity(intent20);
                break;
            case R.id.meghalaya_phone:
                meghalaya.setEnabled(false);
                Intent intent21 = new Intent(Intent.ACTION_DIAL);
                intent21.setData(Uri.parse("tel:"+"108"));
                startActivity(intent21);
                break;
            case R.id.manipur_phone:
                manipur.setEnabled(false);
                Intent intent22 = new Intent(Intent.ACTION_DIAL);
                intent22.setData(Uri.parse("tel:"+"3852411668"));
                startActivity(intent22);
                break;
            case R.id.mizoram_phone:
                mizoram.setEnabled(false);
                Intent intent23 = new Intent(Intent.ACTION_DIAL);
                intent23.setData(Uri.parse("tel:"+"102"));
                startActivity(intent23);
                break;
            case R.id.nagaland_phone:
                nagaland.setEnabled(false);
                Intent intent24 = new Intent(Intent.ACTION_DIAL);
                intent24.setData(Uri.parse("tel:"+"7005539653"));
                startActivity(intent24);
                break;
            case R.id.odisha_phone:
                odisha.setEnabled(false);
                Intent intent25 = new Intent(Intent.ACTION_DIAL);
                intent25.setData(Uri.parse("tel:"+"9439994859"));
                startActivity(intent25);
                break;
            case R.id.punjab_phone:
                punjab.setEnabled(false);
                Intent intent26 =new Intent(Intent.ACTION_DIAL);
                intent26.setData(Uri.parse("tel:"+"104"));
                startActivity(intent26);
                break;
            case R.id.pudhucherry_phone:
                pudhucherry.setEnabled(false);
                Intent intent27 = new Intent(Intent.ACTION_DIAL);
                intent27.setData(Uri.parse("tel:"+"104"));
                startActivity(intent27);
                break;
            case R.id.rajasthan_phone:
                rajasthan.setEnabled(false);
                Intent intent28 = new Intent(Intent.ACTION_DIAL);
                intent28.setData(Uri.parse("tel:"+"01412225624"));
                startActivity(intent28);
                break;
            case R.id.sikkim_phone:
                sikkim.setEnabled(false);
                Intent intent29 = new Intent(Intent.ACTION_DIAL);
                intent29.setData(Uri.parse("tel:"+"104"));
                startActivity(intent29);
                break;
            case R.id.telangana_phone:
                telangana.setEnabled(false);
                Intent intent30 = new Intent(Intent.ACTION_DIAL);
                intent30.setData(Uri.parse("tel:"+"104"));
                startActivity(intent30);
                break;
            case R.id.tripura_phone:
                tripura.setEnabled(false);
                Intent intent31 = new Intent(Intent.ACTION_DIAL);
                intent31.setData(Uri.parse("tel:"+"03812315879"));
                startActivity(intent31);
                break;
            case R.id.uttar_pradesh_phone:
                uttarpradesh.setEnabled(false);
                Intent intent32 = new Intent(Intent.ACTION_DIAL);
                intent32.setData(Uri.parse("tel:"+"18001805145"));
                startActivity(intent32);
                break;
            case R.id.uttarkhand_phone:
                uttarkhand.setEnabled(false);
                Intent intent33 = new Intent(Intent.ACTION_DIAL);
                intent33.setData(Uri.parse("tel:"+"104"));
                startActivity(intent33);
                break;
            case R.id.west1_phone:
                westbengal.setEnabled(false);
                Intent intent34 = new Intent(Intent.ACTION_DIAL);
                intent34.setData(Uri.parse("tel:"+"1800313444222"));
                startActivity(intent34);
                break;
            case R.id.madhya_phone:
                madhya_pradesh.setEnabled(false);
                Intent intent35 = new Intent(Intent.ACTION_DIAL);
                intent35.setData(Uri.parse("tel:"+"104"));
                startActivity(intent35);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        //statebutton
        andaman = findViewById(R.id.andaman_phone);
        andra = findViewById(R.id.andra_phone);
        arunachal = findViewById(R.id.arunachal_phone);
        assam = findViewById(R.id.assam_phone);
        bihar = findViewById(R.id.bihar_phone);
        chandigarh = findViewById(R.id.chandigarh_phone);
        chattisgarh = findViewById(R.id.chattis_phone);
        delhi = findViewById(R.id.delhi_phone);
        damanddieu = findViewById(R.id.daman_diu_phone);
        tamilnadu = findViewById(R.id.tamil_nadu_phone);
        goa = findViewById(R.id.goa_phone);
        gujarat = findViewById(R.id.gujarat_phone);
        himachal = findViewById(R.id.himachal_phone);
        haryana = findViewById(R.id.haryana_phone);
        jharkhand = findViewById(R.id.jharkhand_phone);
        jammu = findViewById(R.id.jammu_phone);
        karnataka = findViewById(R.id.karnataka_phone);
        kerala = findViewById(R.id.kerala_phone);
        ladakh = findViewById(R.id.ladakh_phone);
        lakshadweep = findViewById(R.id.lakshadweep_phone);
        maharastra = findViewById(R.id.maharastra_phone);
        meghalaya = findViewById(R.id.meghalaya_phone);
        manipur = findViewById(R.id.manipur_phone);
        madhya_pradesh = findViewById(R.id.madhya_phone);
        mizoram = findViewById(R.id.mizoram_phone);
        nagaland = findViewById(R.id.nagaland_phone);
        odisha = findViewById(R.id.odisha_phone);
        punjab = findViewById(R.id.punjab_phone);
        pudhucherry = findViewById(R.id.pudhucherry_phone);
        rajasthan = findViewById(R.id.rajasthan_phone);
        sikkim = findViewById(R.id.sikkim_phone);
        telangana = findViewById(R.id.telangana_phone);
        tripura = findViewById(R.id.tripura_phone);
        uttarpradesh = findViewById(R.id.uttar_pradesh_phone);
        uttarkhand = findViewById(R.id.uttarkhand_phone);
        westbengal = findViewById(R.id.west1_phone);

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
    }
}
