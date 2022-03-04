package android.iut.td1.niggli.cookieclicker;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Shop extends AppCompatActivity {

    int compteur;
    int nbrCM = 0;
    int prix_cookie_miteux = 10;
    TextView aff_prix_cookie_miteux, affichage_argent, aff_nbr_cm;
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
        affichage_argent = (TextView)findViewById(R.id.Affichage_shop);
        aff_nbr_cm = (TextView)findViewById(R.id.nbr_miteux);
        aff_prix_cookie_miteux = (TextView)findViewById(R.id.Affichage_prix_miteux);
        Intent intent = getIntent();
        compteur = intent.getIntExtra("Compteur",0);
        nbrCM = intent.getIntExtra("nbr_cm",0);
        prix_cookie_miteux = intent.getIntExtra("prix_cm",10);


        affichage_argent.setText(Integer.toString(compteur));
        aff_nbr_cm.setText("Nbr:"+ nbrCM);
        aff_prix_cookie_miteux.setText("Prix:"+ prix_cookie_miteux);


        ImageButton retour = (ImageButton)findViewById(R.id.Button_retour);


        if (savedInstanceState != null){
            nbrCM =savedInstanceState.getInt("NBR_CM");
            aff_nbr_cm.setText("Nbr:"+nbrCM);
            prix_cookie_miteux = savedInstanceState.getInt("prix_cm");
            aff_prix_cookie_miteux.setText("Prix:"+prix_cookie_miteux);

        }





    }

    public void gotoRetour(View v){
        affichage_argent = (TextView)findViewById(R.id.Affichage_shop);
        String s_moula = affichage_argent.getText().toString();
        int moula = Integer.parseInt(s_moula);
        Intent i = new Intent();
        i.putExtra("C_ret",moula);
        i.putExtra("nbr_cm",nbrCM);
        i.putExtra("prix_cm",prix_cookie_miteux);
        setResult(RESULT_OK,i);
        finish();
    }

    public void Achat_CM(View view){
        if (compteur > prix_cookie_miteux) {
            compteur = compteur - prix_cookie_miteux;
            affichage_argent.setText(Integer.toString(compteur));
            nbrCM++;
            prix_cookie_miteux = (int) ((int)(prix_cookie_miteux + nbrCM*2)*1.25);
            aff_prix_cookie_miteux.setText("Prix:"+ prix_cookie_miteux);
            aff_nbr_cm.setText("Nbr:"+ nbrCM);

            //setAugmentation(1);
        }
        else {
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

}