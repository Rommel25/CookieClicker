package android.iut.td1.niggli.cookieclicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Shop extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        getSupportActionBar().setTitle("Shop Dr. Sarrazinzin");
        TextView affichage_cookies = (TextView)findViewById(R.id.Affichage_shop);
        Intent intent = getIntent();
        int compteur = intent.getIntExtra("Compteur",0);
        affichage_cookies.setText(Integer.toString(compteur));


    }
}