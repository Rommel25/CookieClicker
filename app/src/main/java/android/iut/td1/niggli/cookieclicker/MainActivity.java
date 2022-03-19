package android.iut.td1.niggli.cookieclicker;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int compteur = 0;
    private TextView affichage;
    int nbr_cm = 0;
    int prix_cookie_miteux = 10;
    int nbr_um = 0;
    int prix_usine_miteux = 30;
    int nbr_jc = 0;
    int prix_jeune_cookie = 100;
    private SharedPreferences myPreferences;
    private SharedPreferences.Editor editor;
    //private Shop shop = new Shop();
    final int REQUEST_CODE = 42;
    private ImageButton btn;
    private final Handler handler = new Handler();
    public int compteursec = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btn_cookie);
        affichage = findViewById(R.id.Affichage);
        ImageButton shop = findViewById(R.id.btn_shop);

        //Intent intent = getIntent();
        //compteur = intent.getIntExtra("Compteur",0);
        //nbr_cm = intent.getIntExtra("NBR_CM",0);
        //prix_cookie_miteux = intent.getIntExtra("Prix",prix_cookie_miteux);


        if (savedInstanceState != null){
            compteur =savedInstanceState.getInt("Compteur");
            affichage.setText(Integer.toString(compteur));
        }
        compteur = PrefConfig.loadTotalFromPref(this);
        nbr_cm = PrefConfig.loadCMFromPref(this);
        prix_cookie_miteux = PrefConfig.loadCMPFromPref(this);
        nbr_um = PrefConfig.loadUMFromPref(this);
        affichage.setText(Integer.toString(compteur));
        runnable.run();

        //myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        myPreferences = getSharedPreferences("android.iut.td1.niggli.cookieclicker", Activity.MODE_PRIVATE);
        editor = myPreferences.edit();

    }

    /**public void SaveCompteur(){
        myPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = myPreferences.edit();
        editor.putInt("moulagashared", 0);
        editor.commit();
    }

    public void LoadCompteur(){
        myPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        compteur = myPreferences.getInt("moulagashared", 0);
        affichage.setText(Integer.toString(compteur));
    }**/

    public void gotoShop(View v){
        Intent i = new Intent(MainActivity.this, Shop.class);
        i.putExtra("Compteur",compteur);
        i.putExtra("nbr_cm",nbr_cm);
        i.putExtra("prix_cm",prix_cookie_miteux);
        i.putExtra("prix_um",prix_usine_miteux);
        i.putExtra("nbr_um",nbr_um);
        i.putExtra("prix_jc",prix_jeune_cookie);
        i.putExtra("nbr_jc",nbr_jc);
        startActivityForResult(i,REQUEST_CODE);
    }

    public void gotoSettings(View v){
        Intent i = new Intent(MainActivity.this, Settings.class);
        startActivity(i);
    }

    public void CountUP(View view){
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.btn_anim);
        btn.startAnimation(myAnim);
        int add = 1 + (nbr_cm*1) + (nbr_jc*5);
        compteur+= add;
        affichage.setText(Integer.toString(compteur));
        PrefConfig.saveTotalInPref(getApplicationContext(),compteur);
        PrefConfig.saveCmInPref(getApplicationContext(),nbr_cm);
        PrefConfig.saveCmPInPref(getApplicationContext(),prix_cookie_miteux);
        //PrefConfig.saveTotalInPref(getApplicationContext(),nbr_jc);
    }

    public void AutoCount(){
        compteur += nbr_um;
        affichage.setText(Integer.toString(compteur));
        PrefConfig.saveUMInPref(getApplicationContext(),nbr_um);
        PrefConfig.saveTotalInPref(getApplicationContext(),compteur);
    }

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this,2000);
            AutoCount();
        }
    };

    /**@Override
    protected void onResume() {
        super.onResume();
        runnable.run();
    }**/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data!=null) {
            compteur = data.getIntExtra("C_ret",0);
            nbr_cm = data.getIntExtra("nbr_cm",0);
            prix_cookie_miteux = data.getIntExtra("prix_cm",10);
            nbr_um = data.getIntExtra("nbr_um",0);
            prix_usine_miteux = data.getIntExtra("prix_um",30);
            nbr_jc = data.getIntExtra("nbr_jc",nbr_jc);
            prix_jeune_cookie = data.getIntExtra("prix_jc",prix_jeune_cookie);
            affichage.setText(Integer.toString(compteur));
        }
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