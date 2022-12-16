package android.iut.td1.niggli.cookieclicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Shop extends AppCompatActivity {

    int compteur;
    int nbrCM,nbrJC, nbrUM;
    int prix_cookie_miteux = 10;
    int prix_usine_miteuse = 30;
    int prix_jeune_cookie = 100;
    Button btn_cm;
    TextView aff_prix_cookie_miteux, affichage_argent, aff_nbr_cm, aff_prix_usine_miteux, aff_nbr_um, aff_prix_jeune_cookie;
    /*public int augmentation = 1;

    public int getAugmentation() {
        return augmentation;
    }

    public void setAugmentation(int augmentation) {
        this.augmentation += augmentation;
    }*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        getSupportActionBar().setTitle("Shop du Dr. Sarrazinzin");
        affichage_argent = findViewById(R.id.Affichage_shop);
        //affichage des prix
        aff_prix_cookie_miteux = findViewById(R.id.Affichage_prix_cmiteux);
        aff_prix_usine_miteux = findViewById(R.id.Affichage_prix_usine_miteuse);
        aff_prix_jeune_cookie = findViewById(R.id.Affichage_prix_jeune_cookie);

        btn_cm = findViewById(R.id.achat_CM);

        Intent intent = getIntent();
        compteur = intent.getIntExtra("Compteur",0);
        nbrCM = intent.getIntExtra("nbr_cm",0);
        prix_cookie_miteux = intent.getIntExtra("prix_cm",10);
        prix_usine_miteuse = intent.getIntExtra("prix_um",30);
        nbrUM = intent.getIntExtra("nbr_um",0);
        prix_jeune_cookie = intent.getIntExtra("prix_jc",100);
        nbrJC = intent.getIntExtra("nbr_jc",0);


        affichage_argent.setText(Integer.toString(compteur));
        aff_prix_cookie_miteux.setText("Prix:"+ prix_cookie_miteux);
        aff_prix_jeune_cookie.setText("Prix : "+prix_jeune_cookie);
        aff_prix_usine_miteux.setText("Prix:"+ 30);


        ImageButton retour = findViewById(R.id.Button_retour);


        if (savedInstanceState != null){
            nbrCM =savedInstanceState.getInt("NBR_CM");
            aff_nbr_cm.setText("Nbr:"+nbrCM);
            prix_cookie_miteux = savedInstanceState.getInt("prix_cm");
            aff_prix_cookie_miteux.setText("Prix:"+prix_cookie_miteux);

        }


    }

    public void gotoRetour(View v){
        affichage_argent = findViewById(R.id.Affichage_shop);
        String s_moula = affichage_argent.getText().toString();
        int moula = Integer.parseInt(s_moula);
        Intent i = new Intent();
        i.putExtra("C_ret",moula);
        i.putExtra("nbr_cm",nbrCM);
        i.putExtra("prix_cm",prix_cookie_miteux);
        i.putExtra("nbr_um",nbrUM);
        i.putExtra("prix_um",prix_usine_miteuse);
        i.putExtra("nbr_jc",nbrJC);
        i.putExtra("prix_jc",prix_jeune_cookie);
        setResult(RESULT_OK,i);
        finish();
    }

    public void Achat_CM(View view){
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.btn_anim);
        btn_cm.startAnimation(myAnim);
        if (compteur > prix_cookie_miteux) {
            compteur = compteur - prix_cookie_miteux;
            affichage_argent.setText(Integer.toString(compteur));
            nbrCM++;
            prix_cookie_miteux = (int) ((prix_cookie_miteux + nbrCM*2) *1.25);
            aff_prix_cookie_miteux.setText("Prix:"+ prix_cookie_miteux);
        }
        else {
            Toast.makeText(Shop.this,
                    "Pas assez de moulaga", Toast.LENGTH_SHORT).show();
        }
    }

    public void Achat_JC(View view){
        if (compteur >= prix_jeune_cookie) {
            compteur = compteur - prix_jeune_cookie;
            affichage_argent.setText(Integer.toString(compteur));
            nbrJC++;
            prix_jeune_cookie = (int) ((prix_jeune_cookie + nbrJC*2) *1.25);
            aff_prix_jeune_cookie.setText("Prix:"+ prix_jeune_cookie);
            PrefConfig.saveTotalInPref(getApplicationContext(),compteur);
            //setAugmentation(1);
        }
        else {
            Toast.makeText(Shop.this,
                    "Pas assez de moulaga", Toast.LENGTH_SHORT).show();
        }
    }

    public void Achat_UM(View v){
        if(compteur >= prix_usine_miteuse){
            compteur = compteur - prix_usine_miteuse;
            affichage_argent.setText(Integer.toString(compteur));
            nbrUM++;
            prix_usine_miteuse = (int) ((30 + nbrUM*3) *1.5);
            aff_prix_usine_miteux.setText("Prix :"+prix_usine_miteuse);
            PrefConfig.saveTotalInPref(getApplicationContext(),compteur);
        }
        else{
            Toast.makeText(Shop.this,
                    "Pas assez de moulaga", Toast.LENGTH_SHORT).show();
        }
    }

   /** public boolean onOptionsItemSelected(MenuItem item){
        Intent myIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivityForResult(myIntent, 0);
        return true;
    }**/

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt("Prix_cookie_m",prix_cookie_miteux);
        outState.putInt("NBR_CM",nbrCM);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.putInt("Prix_cookie_m",prix_cookie_miteux);
        savedInstanceState.putInt("NBR_CM",nbrCM);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PrefConfig.saveTotalInPref(getApplicationContext(),compteur);
        PrefConfig.saveCmPInPref(getApplicationContext(),prix_cookie_miteux);
        PrefConfig.saveUMPInPref(getApplicationContext(),prix_usine_miteuse);
    }
}