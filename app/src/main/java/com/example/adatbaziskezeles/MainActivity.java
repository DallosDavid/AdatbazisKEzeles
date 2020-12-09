package com.example.adatbaziskezeles;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnOlvas,btnRogzit,btnModisitas,btnTorles;
    TextView textAdatok;
    DBHelper adatbazis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            ini();
            onClickLisener();
    }

    private void adatLekerdes() {
        Cursor adatok= adatbazis.adatLekerdezes();
        if (adatok== null)
        {
            Toast.makeText(this, "Sikertelen adat lekerdezes", Toast.LENGTH_SHORT).show();
            return;
        }
        if (adatok.getCount() == 0)
        {
            Toast.makeText(this, "Nincs lekérdeszheto adat", Toast.LENGTH_SHORT).show();
            return;
        }
        StringBuffer stringBuffer= new StringBuffer();
        while (adatok.moveToNext())
        {
                stringBuffer.append("ID:"+adatok.getString(0)+"\n");
                stringBuffer.append("Nev:"+adatok.getString(1)+"\n");
                stringBuffer.append("Email:"+adatok.getString(2)+"\n");
                stringBuffer.append("Jegy:"+adatok.getString(3)+"\n\n");
        }
        textAdatok.setText(stringBuffer.toString());
        Toast.makeText(this, "Sikeres lekerdezes", Toast.LENGTH_SHORT).show();

    }
    private void onClickLisener() {
        btnOlvas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adatLekerdes();
            }
        });

        btnRogzit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RogzitesAct.class));
                finish();

            }
        });

        btnModisitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent modositas=new Intent(MainActivity.this,ModositActivity.class);
                startActivity(modositas);
                finish();

            }
        });

        btnTorles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent torles=new Intent(MainActivity.this,TorlesAct.class);
                startActivity(torles);
                finish();

            }
        });
        textAdatok.setMovementMethod(new ScrollingMovementMethod());

    }


    private void ini() {

        btnOlvas = (Button) findViewById(R.id.btn_olvas);
        btnRogzit = (Button)findViewById(R.id.btn_rogzites);
        btnModisitas= (Button)findViewById(R.id.btn_modositas);
        btnTorles = (Button)findViewById(R.id.btn_torles);
        textAdatok = (TextView) findViewById(R.id.text_adatok);
        adatbazis= new DBHelper(MainActivity.this);

    }
}