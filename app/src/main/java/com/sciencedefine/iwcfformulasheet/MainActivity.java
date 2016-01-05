package com.sciencedefine.iwcfformulasheet;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;


public class MainActivity extends Activity implements FormulaTitlesFragment.OnFragmentInteractionListener {
    public static final String PREF = "CalculatorPref";
    public final static String UNITS = "units";
    private String unit;
    private FormulaExpressionsFragment mFormulaExpressionsFragment;
    //private AdView mainAdView;
    //private InterstitialAd mInterstitial;
    //private int mPosition;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("state", "Main Activity onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

//        mainAdView = new AdView(this);
//        mainAdView.setAdUnitId(getResources().getString(R.string.ad_unit_id));
//        mainAdView.setAdSize(AdSize.BANNER);
//        //mainAdView.setAdListener(new ToastAdListener(this));
//        RelativeLayout layout = (RelativeLayout) findViewById(R.id.fragment_container);
//        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
//                RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
//        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
//        //mainAdView.setBackgroundColor(0xffffffff);
//        layout.addView(mainAdView, params);
//        mainAdView.loadAd(new AdRequest.Builder().build());

//        mInterstitial = new InterstitialAd(this);
//        mInterstitial.setAdUnitId(getResources().getString(R.string.ad_unit_id_interstitial));
//        mInterstitial.setAdListener(new AdListener() {
//            @Override
//            public void onAdClosed() {
//                super.onAdClosed();
//
//
//
//            }
//
//            @Override
//            public void onAdFailedToLoad(int errorCode) {
//                super.onAdFailedToLoad(errorCode);
//            }
//
//            @Override
//            public void onAdLoaded() {
//                super.onAdLoaded();
//                mInterstitial.show();
//            }
//
//        });
        // Check that the activity is using the layout version with
        // the fragment_container FrameLayout
        if (findViewById(R.id.fragment_container) != null) {

            // However, if we're being restored from a previous state,
            // then we don't need to do anything and should return or else
            // we could end up with overlapping fragments.
            if (savedInstanceState != null) {
                return;
            }

            // Create a new Fragment to be placed in the activity layout

            FormulaTitlesFragment mFormulaTitlesFragment = new FormulaTitlesFragment();

            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.list, mFormulaTitlesFragment);
            fragmentTransaction.commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        final SharedPreferences unitPref = getSharedPreferences(PREF, MODE_PRIVATE);
        SharedPreferences.Editor unitPrefEditor = unitPref.edit();
        unit = unitPref.getString(UNITS, "api");
        int id = item.getItemId();
        if (id == R.id.api_units && ! unit.equals("api")) {
            unitPrefEditor.putString(UNITS, "api");
            unitPrefEditor.commit();
            finish();
            startActivity(getIntent());
            Toast.makeText(getApplicationContext(), "Unit System Changed to API", Toast.LENGTH_LONG).show();
            return true;
        }
        if (id == R.id.metric_units_10 && ! unit.equals("metric_10")) {
            unitPrefEditor.putString(UNITS, "metric_10");
            unitPrefEditor.commit();
            finish();
            startActivity(getIntent());
            Toast.makeText(getApplicationContext(), "Unit System Changed to Metric 10", Toast.LENGTH_LONG).show();
            return true;
        }
        if (id == R.id.metric_units_102 && ! unit.equals("metric_10.2")) {
            unitPrefEditor.putString(UNITS, "metric_102");
            unitPrefEditor.commit();
            finish();
            startActivity(getIntent());
            Toast.makeText(getApplicationContext(), "Unit System Changed to Metric 10.2", Toast.LENGTH_LONG).show();
            return true;
        }
        if (id == R.id.metric_units_00981 && ! unit.equals("metric_0.0981")) {
            unitPrefEditor.putString(UNITS, "metric_00981");
            unitPrefEditor.commit();
            finish();
            startActivity(getIntent());
            Toast.makeText(getApplicationContext(), "Unit System Changed to Metric 0.0981", Toast.LENGTH_LONG).show();
            return true;
        }
        if (id == R.id.SI_units && ! unit.equals("SI")) {
            unitPrefEditor.putString(UNITS, "SI");
            unitPrefEditor.commit();
            finish();
            startActivity(getIntent());
            Toast.makeText(getApplicationContext(), "Unit System Changed to SI", Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onFragmentInteraction(int position) {
        Log.i("State", "onFragmentInteraction Entered");
        //mInterstitial.loadAd(new AdRequest.Builder().build());
        //mPosition = position;
        if (mFormulaExpressionsFragment == null)
            mFormulaExpressionsFragment = new FormulaExpressionsFragment();
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.list, mFormulaExpressionsFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
        getFragmentManager().executePendingTransactions();
        mFormulaExpressionsFragment.displayFormulaExpression(position);
        //mFormulaExpressionsFragment.displayFormulaExpression(position);
        //mFormulaExpressionsFragment.displayFormulaCalculator(position);
    }
}