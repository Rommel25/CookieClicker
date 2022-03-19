package android.iut.td1.niggli.cookieclicker;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class Settings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

    }

    public void gotoGH(View v){
        Intent browser = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/Rommel25/CookieClicker"));
        startActivity(browser);
    }
}