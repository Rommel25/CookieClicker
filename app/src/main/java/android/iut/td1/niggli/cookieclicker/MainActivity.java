package android.iut.td1.niggli.cookieclicker;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
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
    private SharedPreferences myPreferences;
    private SharedPreferences.Editor editor;
    //private Shop shop = new Shop();
    final int REQUEST_CODE = 42;
    private ImageButton btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (ImageButton)findViewById(R.id.btn_cookie);
        affichage = (TextView)findViewById(R.id.Affichage);
        ImageButton shop = (ImageButton)findViewById(R.id.btn_shop);

        //Intent intent = getIntent();
        //compteur = intent.getIntExtra("Compteur",0);
        //nbr_cm = intent.getIntExtra("NBR_CM",0);
        //prix_cookie_miteux = intent.getIntExtra("Prix",prix_cookie_miteux);


        if (savedInstanceState != null){
            compteur =savedInstanceState.getInt("Compteur");
            affichage.setText(Integer.toString(compteur));
        }

        affichage.setText(Integer.toString(compteur));


    }

    public void gotoShop(View v){
        Intent i = new Intent(MainActivity.this, Shop.class);
        i.putExtra("Compteur",compteur);
        i.putExtra("nbr_cm",nbr_cm);
        i.putExtra("prix_cm",prix_cookie_miteux);
        startActivityForResult(i,REQUEST_CODE);
    }

    public void CountUP(View view){
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.btn_anim);
        btn.startAnimation(myAnim);
        int add = nbr_cm+1;
        compteur+= add;
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data!=null) {
            compteur = (int) data.getIntExtra("C_ret",0);
            nbr_cm = (int) data.getIntExtra("nbr_cm",0);
            prix_cookie_miteux = (int) data.getIntExtra("prix_cm",10);
            affichage.setText(Integer.toString(compteur));
        }
    }
}