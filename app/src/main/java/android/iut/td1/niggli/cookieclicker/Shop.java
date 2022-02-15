package android.iut.td1.niggli.cookieclicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Shop extends AppCompatActivity {

    int compteur;
    int prix_cookie_miteux = 10;
    TextView aff_cookie_miteux, affichage_cookies;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        getSupportActionBar().setTitle("Shop du Dr. Sarrazinzin");
        affichage_cookies = (TextView)findViewById(R.id.Affichage_shop);
        Intent intent = getIntent();
        compteur = intent.getIntExtra("Compteur",0);
        affichage_cookies.setText(Integer.toString(compteur));
        aff_cookie_miteux = (TextView)findViewById(R.id.Affichage_prix_miteux);
        ImageButton retour = (ImageButton)findViewById(R.id.Button_retour);

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Shop.this, MainActivity.class);
                i.putExtra("Compteur",compteur);
                startActivity(i);

            }
        });

    }

    public void Achat_CM(View view){
        compteur = compteur -10;
        affichage_cookies.setText(Integer.toString(compteur));
        prix_cookie_miteux += prix_cookie_miteux * 0.1;
        aff_cookie_miteux.setText(Integer.toString(prix_cookie_miteux));
    }

    public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }
}