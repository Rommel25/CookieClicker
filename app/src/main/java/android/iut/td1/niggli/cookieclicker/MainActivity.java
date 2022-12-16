package android.iut.td1.niggli.cookieclicker;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
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
    int prix_usine_miteuse = 30;
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


        if (savedInstanceState != null){
            compteur =savedInstanceState.getInt("Compteur");
            affichage.setText(Integer.toString(compteur));
        }
        compteur = PrefConfig.loadTotalFromPref(this);
        nbr_cm = PrefConfig.loadCMFromPref(this);
        prix_cookie_miteux = PrefConfig.loadCMPFromPref(this);
        nbr_um = PrefConfig.loadUMFromPref(this);
        prix_usine_miteuse = PrefConfig.loadUMPFromPref(this);
        affichage.setText(Integer.toString(compteur));
        runnable.run();

        //myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        myPreferences = getSharedPreferences("android.iut.td1.niggli.cookieclicker", Activity.MODE_PRIVATE);
        editor = myPreferences.edit();


     /**   AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent notificationIntent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
        notificationIntent.addCategory("android.intent.category.DEFAULT");

        PendingIntent broadcast = PendingIntent.getBroadcast(this, 100, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Calendar cal = Calendar.getInstance();

        cal.add(Calendar.MINUTE, Calendar.HOUR);
        alarmManager.setRepeating(AlarmManager.RTC, cal.getTimeInMillis(), 6000000, broadcast);**/


    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem menuItem){
        switch(menuItem.getItemId()){
            case R.id.info:
                new AlertDialog.Builder(getApplicationContext())
                        .setTitle("CookieClicker")
                        .setMessage("This project has been made by Paul Niggli");
            case R.id.github:
                Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Rommel25/CookieClicker"));
                startActivity(browser);
            default:
                return super.onOptionsItemSelected(menuItem);
        }
    }


    public void gotoShop(View v){
        Intent i = new Intent(MainActivity.this, Shop.class);
        i.putExtra("Compteur",compteur);
        i.putExtra("nbr_cm",nbr_cm);
        i.putExtra("prix_cm",prix_cookie_miteux);
        i.putExtra("prix_um",prix_usine_miteuse);
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
        PrefConfig.saveUMInPref(getApplicationContext(),nbr_um);
        PrefConfig.saveUMPInPref(getApplicationContext(),prix_usine_miteuse);

    }

    public void AutoCount(){
        compteur += nbr_um;
        affichage.setText(Integer.toString(compteur));
        PrefConfig.saveTotalInPref(getApplicationContext(),compteur);
        PrefConfig.saveCmInPref(getApplicationContext(),nbr_cm);
        PrefConfig.saveCmPInPref(getApplicationContext(),prix_cookie_miteux);
        PrefConfig.saveUMInPref(getApplicationContext(),nbr_um);
        PrefConfig.saveUMPInPref(getApplicationContext(),prix_usine_miteuse);

    }

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(this,2000);
            AutoCount();
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK && data!=null) {
            compteur = data.getIntExtra("C_ret",0);
            nbr_cm = data.getIntExtra("nbr_cm",0);
            prix_cookie_miteux = data.getIntExtra("prix_cm",10);
            nbr_um = data.getIntExtra("nbr_um",0);
            prix_usine_miteuse = data.getIntExtra("prix_um",30);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        PrefConfig.saveTotalInPref(getApplicationContext(),compteur);
        PrefConfig.saveCmInPref(getApplicationContext(),nbr_cm);
        PrefConfig.saveCmPInPref(getApplicationContext(),prix_cookie_miteux);
        PrefConfig.saveUMInPref(getApplicationContext(),nbr_um);
        PrefConfig.saveUMPInPref(getApplicationContext(),prix_usine_miteuse);
    }

}