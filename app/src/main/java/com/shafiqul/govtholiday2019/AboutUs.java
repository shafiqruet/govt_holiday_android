package com.shafiqul.govtholiday2019;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

public class AboutUs extends AppCompatActivity {

    InterstitialAd mInterstitialAd;
    private AdView mAdView;
    public static final String GOVT_ABOUT_NAME = "GovtAboutFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setTitle("About");

        //mInterstitialAd = new InterstitialAd(this);


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.color_statusbar));
        }

        mAdView = (AdView) findViewById(R.id.adView);



        // Load ads into Interstitial Ads

        AdRequest adRequest = new AdRequest.Builder()
                //.addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                // Check the LogCat to get your test device ID
                //.addTestDevice("7913E282C5FF4C6DB46D6E7C56A2094E")
                .build();

        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdOpened() {
                super.onAdOpened();
            }
        });

        mAdView.loadAd(adRequest);

        // remove code start here
        /*mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));
        mInterstitialAd.loadAd(adRequest);*/
        // remove code start here

        TextView description = (TextView) findViewById(R.id.description);
        String styledText = "<p style='color:#673AB7'>Get all Govt holiday information easy and quickly then you can do by this apps.</p>" +
                "<p>From this apps you can see all govt holiday information </p>" +
                "<p>Govt holiday by list both 2018 and 2019</p>" +
                "<p>Govt holiday by year both 2018 and 2019</p>" +
                "<p>Current Version: 1.1</p>" +
                "<p>Developed by: Shafiqul</p>" +
                "Gmail: shafiqruet@gmail.com";
        description.setText(Html.fromHtml(styledText));

        SharedPreferences prefs = getSharedPreferences(GOVT_ABOUT_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = getSharedPreferences(GOVT_ABOUT_NAME, MODE_PRIVATE).edit();
        int count = prefs.getInt("govt_about_count", 0);

        count++;

        if (count == 1) {
            // remove code start here
            /*Handler handler = new Handler();
            handler.postDelayed(new Runnable(){
                @Override
                public void run() {
                    showInterstitial();
                }
            },1000);*/
            // remove code start here
            editor.putInt("govt_about_count", 0);
            editor.apply();
        } else {

            // save updated count value
            editor.putInt("govt_about_count", count);
            editor.apply();
        }


    }

    private void showInterstitial() {
        /*if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }*/
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
