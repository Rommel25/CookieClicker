package android.iut.td1.niggli.cookieclicker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int compteur = 0;
    private TextView affichage;
    private SharedPreferences myPreferences;
    private SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton btn = (ImageButton)findViewById(R.id.btn_cookie);
        affichage = (TextView)findViewById(R.id.Affichage);
        ImageButton shop = (ImageButton)findViewById(R.id.btn_shop);

        Intent intent = getIntent();
        compteur = intent.getIntExtra("Compteur",0);

        if (savedInstanceState != null){
            compteur =savedInstanceState.getInt("Compteur");
            affichage.setText(Integer.toString(compteur));
        }

        shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Shop.class);
                i.putExtra("Compteur",compteur);
                startActivity(i);

            }
        });

        affichage.setText(Integer.toString(compteur));

        myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //myPreferences = getSharedPreferences("android.iut.td1.niggli.cookieclicker", Context.MODE_PRIVATE);
        editor = myPreferences.edit();

        editor.putInt("compteur",compteur);
        editor.commit();

        int repriseCompteur = myPreferences.getInt("compteur",0);
    }

    public void CountUP(View view){
        compteur++;
        affichage.setText(Integer.toString(compteur));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("Compteur",compteur);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.putInt("compteur",compteur);
    }
}