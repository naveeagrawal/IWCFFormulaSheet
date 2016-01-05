package com.sciencedefine.iwcfformulasheet;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;

import java.util.ArrayList;

public class FormulaExpressionsFragment extends Fragment {
    public static final String PREF = "CalculatorPref";
    public final static String UNITS = "units";
    private InterstitialAd mInterstitial;
    private AdView mAdView;
    private int currentPosition = -1;
    private ArrayList<EditText> myEditTextList;
    private int size;
    private String answer = "";
    public static final double CONSTANT = 0.052;
    private TextView mTextView;
    private TextView mFormulaTitle;
    private LinearLayout formulaExpressionsLayout;
    private View rootView;
    private View formulaCalculator;
    private String unit;
    private CharSequence[] unitStringArray;
    private CharSequence[] expressionStringArray;
    private int[] formulaLayout;
    public static int[] formulaLayout_api = {
            R.layout.formula_1,
            R.layout.formula_2,
            R.layout.formula_3,
            R.layout.formula_4,
            R.layout.formula_5,
            R.layout.formula_6,
            R.layout.formula_7,
            R.layout.formula_8,
            R.layout.formula_9,
            R.layout.formula_10,
            R.layout.formula_11,
            R.layout.formula_12,
            R.layout.formula_13,
            R.layout.formula_14,
            R.layout.formula_15,
            R.layout.formula_16,
            R.layout.formula_17,
            R.layout.formula_18,
            R.layout.formula_19,
            R.layout.formula_20,
            R.layout.formula_21,
            R.layout.formula_22,
            R.layout.formula_23,
            R.layout.formula_24,
            R.layout.formula_25,
            R.layout.formula_26,
            R.layout.formula_27,
            R.layout.formula_28,
            R.layout.formula_29,
            R.layout.formula_30
    };

    public static int[] formulaLayout_metric_00981 = {
            R.layout.formula_metric_00981_1,
            R.layout.formula_metric_00981_2,
            R.layout.formula_metric_00981_3,
            R.layout.formula_metric_00981_4,
            R.layout.formula_metric_00981_5,
            R.layout.formula_metric_00981_6,
            R.layout.formula_metric_00981_7,
            R.layout.formula_metric_00981_8,
            R.layout.formula_metric_00981_9,
            R.layout.formula_metric_00981_10,
            R.layout.formula_metric_00981_11,
            R.layout.formula_metric_00981_12,
            R.layout.formula_metric_00981_13,
            R.layout.formula_metric_00981_14,
            R.layout.formula_metric_00981_15,
            R.layout.formula_metric_00981_16,
            R.layout.formula_metric_00981_17,
            R.layout.formula_metric_00981_18,
            R.layout.formula_metric_00981_19,
            R.layout.formula_metric_00981_20,
            R.layout.formula_metric_00981_21,
            R.layout.formula_metric_00981_22,
            R.layout.formula_metric_00981_23,
            R.layout.formula_metric_00981_24,
            R.layout.formula_metric_00981_25,
            R.layout.formula_metric_00981_26,
            R.layout.formula_metric_00981_27,
            R.layout.formula_metric_00981_28,
            R.layout.formula_metric_00981_29,
            R.layout.formula_metric_00981_30
    };

    public static int[] formulaLayout_SI = {
            R.layout.formula_si_1,
            R.layout.formula_si_2,
            R.layout.formula_si_3,
            R.layout.formula_si_4,
            R.layout.formula_si_5,
            R.layout.formula_si_6,
            R.layout.formula_si_7,
            R.layout.formula_si_8,
            R.layout.formula_si_9,
            R.layout.formula_si_10,
            R.layout.formula_si_11,
            R.layout.formula_si_12,
            R.layout.formula_si_13,
            R.layout.formula_si_14,
            R.layout.formula_si_15,
            R.layout.formula_si_16,
            R.layout.formula_si_17,
            R.layout.formula_si_18,
            R.layout.formula_si_19,
            R.layout.formula_si_20,
            R.layout.formula_si_21,
            R.layout.formula_si_22,
            R.layout.formula_si_23,
            R.layout.formula_si_24,
            R.layout.formula_si_25,
            R.layout.formula_si_26,
            R.layout.formula_si_27,
            R.layout.formula_si_28,
            R.layout.formula_si_29,
            R.layout.formula_si_30
    };

    public static int[] formulaLayout_metric_102 = {
            R.layout.formula_metric_102_1,
            R.layout.formula_metric_102_2,
            R.layout.formula_metric_102_3,
            R.layout.formula_metric_00981_4,
            R.layout.formula_metric_00981_5,
            R.layout.formula_metric_00981_6,
            R.layout.formula_metric_102_7,
            R.layout.formula_metric_102_8,
            R.layout.formula_metric_00981_9,
            R.layout.formula_metric_00981_10,
            R.layout.formula_metric_102_11,
            R.layout.formula_metric_102_12,
            R.layout.formula_metric_102_13,
            R.layout.formula_metric_00981_14,
            R.layout.formula_metric_00981_15,
            R.layout.formula_metric_00981_16,
            R.layout.formula_metric_102_17,
            R.layout.formula_metric_00981_18,
            R.layout.formula_metric_00981_19,
            R.layout.formula_metric_102_20,
            R.layout.formula_metric_102_21,
            R.layout.formula_metric_00981_22,
            R.layout.formula_metric_00981_23,
            R.layout.formula_metric_102_24,
            R.layout.formula_metric_102_25,
            R.layout.formula_metric_00981_26,
            R.layout.formula_metric_00981_27,
            R.layout.formula_metric_00981_28,
            R.layout.formula_metric_00981_29,
            R.layout.formula_metric_102_30
    };

    public static int[] formulaLayout_metric_10 = {
            R.layout.formula_metric_10_1,
            R.layout.formula_metric_10_2,
            R.layout.formula_metric_10_3,
            R.layout.formula_metric_10_4,
            R.layout.formula_metric_00981_5,
            R.layout.formula_metric_00981_6,
            R.layout.formula_metric_10_7,
            R.layout.formula_metric_10_8,
            R.layout.formula_metric_10_9,
            R.layout.formula_metric_10_10,
            R.layout.formula_metric_10_11,
            R.layout.formula_metric_10_12,
            R.layout.formula_metric_10_13,
            R.layout.formula_metric_10_14,
            R.layout.formula_metric_10_15,
            R.layout.formula_metric_00981_16,
            R.layout.formula_metric_10_17,
            R.layout.formula_metric_00981_18,
            R.layout.formula_metric_10_19,
            R.layout.formula_metric_10_20,
            R.layout.formula_metric_10_21,
            R.layout.formula_metric_00981_22,
            R.layout.formula_metric_00981_23,
            R.layout.formula_metric_10_24,
            R.layout.formula_metric_10_25,
            R.layout.formula_metric_00981_26,
            R.layout.formula_metric_00981_27,
            R.layout.formula_metric_00981_28,
            R.layout.formula_metric_00981_29,
            R.layout.formula_metric_10_30,
    };

    public static String[] formulaExpression = {
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("state", "FormulaExpressionFragment onCreate");

        super.onCreate(savedInstanceState);
        // Retain this Fragment across Activity reconfigurations
        setRetainInstance(true);
        currentPosition = -1;

        final SharedPreferences unitPref = getActivity().getSharedPreferences(PREF, Context.MODE_PRIVATE);
        unit = unitPref.getString(UNITS, "api");
        //Toast.makeText(getActivity(), unit, Toast.LENGTH_SHORT).show();
        if (unit.equals("api")){
            unitStringArray = getResources().getTextArray(R.array.api_units);
            expressionStringArray = getResources().getTextArray(R.array.formula_expressions);
            formulaLayout = formulaLayout_api;
        }
        if (unit.equals("metric_10")){
            unitStringArray = getResources().getTextArray(R.array.metric_units_constant_10);
            expressionStringArray = getResources().getTextArray(R.array.formula_expressions_metric_10);
            formulaLayout = formulaLayout_metric_10;
        }
        if (unit.equals("metric_102")){
            unitStringArray = getResources().getTextArray(R.array.metric_units_00981);
            expressionStringArray = getResources().getTextArray(R.array.formula_expressions_metric_102);
            formulaLayout = formulaLayout_metric_102;
        }
        if (unit.equals("metric_00981")){
            unitStringArray = getResources().getTextArray(R.array.metric_units_00981);
            expressionStringArray = getResources().getTextArray(R.array.formula_expressions_metric_00981);
            formulaLayout = formulaLayout_metric_00981;
        }
        if (unit.equals("SI")){
            unitStringArray = getResources().getTextArray(R.array.SI_units);
            expressionStringArray = getResources().getTextArray(R.array.formula_expressions_SI);
            formulaLayout = formulaLayout_SI;
        }
        mInterstitial = new InterstitialAd(getActivity());
        mInterstitial.setAdUnitId(getResources().getString(R.string.ad_unit_id_interstitial));
        mInterstitial.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                super.onAdFailedToLoad(errorCode);
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                mInterstitial.show();
            }

        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i("state", "FormulaExpressionFragment onCreateView");

        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_formula_expressions, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("State", "onActivityCreated called from ForExpFrag");
        if (currentPosition != -1) {
            displayFormulaExpression(currentPosition);
            Log.i("State", "displayFormulaExpression() called from onActivityCreated of ForExpFrag");
            recallAnswer();
        }
    }

    void displayFormulaExpression(int position) {
        Log.i("state", "displayFormulaExpression() Entered");
        currentPosition = position;
        //mAdView = (AdView) getView().findViewById(R.id.adView);
        //mAdView.setAdListener(new ToastAdListener(this));
        //mAdView.loadAd(new AdRequest.Builder().build());
        mTextView = (TextView) getView().findViewById(R.id.formula_expression_view);
        mFormulaTitle = (TextView) getView().findViewById(R.id.formula_title_view);
        mTextView.setText(expressionStringArray[position]);
        mFormulaTitle.setText(TextUtils.concat(getResources().getTextArray(R.array.formula_titles)[position], " (", unitStringArray[position], ")"));
        mFormulaTitle.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        displayFormulaCalculator(position);
    }

    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

    void displayFormulaCalculator(final int position) {
        Log.i("state", "displayFormulaCalculator() Entered");
        formulaExpressionsLayout = (LinearLayout) rootView.findViewById(R.id.formula_expression_layout);
        LayoutInflater inflater = LayoutInflater.from(getActivity().getApplicationContext());
        formulaCalculator = inflater.inflate(formulaLayout[position], formulaExpressionsLayout, false);
        formulaExpressionsLayout.addView(formulaCalculator);
        Button btn = new Button(getActivity().getApplicationContext());
        formulaExpressionsLayout.addView(btn);
        btn.setId(0);
        btn.setText(getResources().getString(R.string.calculate));
        params.setMargins(5, 0, 5, 5);
        btn.setLayoutParams(params);
        btn.setBackgroundColor(0xff000000);
        btn.setTextColor(0xffffffff);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                float[] values = getValues();
                if (values != null) {
                    if (unit.equals("api")){
                        calculate(position, values);
                        hideSoftKeyboard(getActivity(), view);
                        //mInterstitial.loadAd(new AdRequest.Builder().build());
                    }
                    if (unit.equals("metric_00981")){
                        calculate_00981(position, values);
                        hideSoftKeyboard(getActivity(), view);
                    }
                    if (unit.equals("SI")){
                        calculate_SI(position, values);
                        hideSoftKeyboard(getActivity(), view);
                    }
                    if (unit.equals("metric_10")){
                        calculate_10(position, values);
                        hideSoftKeyboard(getActivity(), view);
                    }
                    if (unit.equals("metric_102")){
                        calculate_102(position, values);
                        hideSoftKeyboard(getActivity(), view);
                    }
                }
            }
        });
        RelativeLayout relativeLayout = (RelativeLayout) getView().findViewById(R.id.rel);
        myEditTextList = new ArrayList<EditText>();
        for (int i = 0; i < relativeLayout.getChildCount(); i++) {
            if (relativeLayout.getChildAt(i) instanceof EditText) {
                EditText editTextInList = (EditText) relativeLayout.getChildAt(i);
                myEditTextList.add(editTextInList);
            }
        }

        size = myEditTextList.size();
        if (size > 0) {
            EditText lastEditText = myEditTextList.get(size - 1);
            for (int k = 0; k < size - 1; k++)
                myEditTextList.get(k).setNextFocusDownId(myEditTextList.get(k + 1).getId());
            for (int s = 0; s < size; s++)
                myEditTextList.get(s).setTextSize(15);

            lastEditText.setImeOptions(EditorInfo.IME_ACTION_DONE);
            lastEditText.setImeActionLabel(getResources().getString(R.string.calculate), EditorInfo.IME_ACTION_DONE);
            lastEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                    if (i == EditorInfo.IME_ACTION_DONE) {
                        float[] values = getValues();
                        if (values != null) {
                            if (unit.equals("api")){
                                calculate(position, values);
                                hideSoftKeyboard(getActivity(), getView().getRootView());
                                //mInterstitial.loadAd(new AdRequest.Builder().build());
                            }
                            if (unit.equals("metric_00981")){
                                calculate_00981(position, values);
                                hideSoftKeyboard(getActivity(), getView().getRootView());
                            }
                            if (unit.equals("SI")){
                                calculate_SI(position, values);
                                hideSoftKeyboard(getActivity(), getView().getRootView());
                            }
                            if (unit.equals("metric_10")){
                                calculate_10(position, values);
                                hideSoftKeyboard(getActivity(), getView().getRootView());
                            }
                            if (unit.equals("metric_102")){
                                calculate_102(position, values);
                                hideSoftKeyboard(getActivity(), getView().getRootView());
                            }
                        }
                    }
                    return false;
                }
            });
        }
    }

    public void calculate(int position, float[] values) {
        double result = 0;
        String unit = "";
        switch (position) {
            case 0:
                result = values[0]*CONSTANT*values[1];
                unit = "psi";
                break;
            case 1:
                result = values[0]*CONSTANT;
                unit = "psi/ft";
                break;
            case 2:
                result = values[0]/(values[1]*CONSTANT);
                unit = "ppg";
                break;
            case 3:
                result = values[0]+values[1];
                unit = "psi";
                break;
            case 4:
                result = values[0]*values[1];
                unit = "bbl/min";
                break;
            case 5:
                result = values[0]/values[1];
                unit = "ft/min";
                break;
            case 6:
                result = values[0]/(values[1]*CONSTANT)+values[2];
                unit = "ppg";
                break;
            case 7:
                result = values[0]/(values[1]*CONSTANT)+values[2];
                unit = "ppg";
                break;
            case 8:
                result = values[0]*values[1]*values[1]/(values[2]*values[2]);
                unit = "psi";
                break;
            case 9:
                result = values[0]*values[1]/values[2];
                unit = "psi";
                break;
            case 10:
                result = values[0]/(values[1]*CONSTANT)+values[2];
                unit = "ppg";
                break;
            case 11:
                result = (values[0]-values[1])*CONSTANT*values[2];
                unit = "psi";
                break;
            case 12:
                result = values[0]/(values[1]*CONSTANT)+values[2];
                unit = "ppg";
                break;
            case 13:
                result = values[0]+values[1];
                unit = "psi";
                break;
            case 14:
                result = (values[0]/values[1])*values[2];
                unit = "psi";
                break;
            case 15:
                result = (values[0]-values[1])*1500/(35.8-values[2]);
                unit = "lb/bbl";
                break;
            case 16:
                result = values[0]/(values[1]*CONSTANT);
                unit = "ft/hr";
                break;
            case 17:
                break;
            case 18:
                result = (values[0]/values[1]-values[2]/values[3])*values[4];
                unit = "gallons";
                break;
            case 19:
                result = values[0]*CONSTANT*values[1]/(values[2]-values[3]);
                unit = "psi/ft";
                break;
            case 20:
                result = values[0]*CONSTANT*values[1]/(values[2]-values[3]);
                unit = "psi/ft";
                break;
            case 21:
                result = values[0]*values[1]/values[2];
                unit = "ft";
                break;
            case 22:
                result = values[0]*values[1]/values[2];
                unit = "ft";
                break;
            case 23:
                result = (values[0]-values[1])*(values[2]-values[3])/(values[4]*CONSTANT*values[5]);
                unit = "ft";
                break;
            case 24:
                result = (values[0]-values[1])*(values[2]-values[3])/(values[4]*CONSTANT*values[5]);
                unit = "ft";
                break;
            case 25:
                result = values[0]*values[1]/(values[2]-values[3]);
                unit = "bbl";
                break;
            case 26:
                result = values[0]*values[1]*values[2]/(values[3]-values[4]);
                unit = "bbl";
                break;
            case 27:
                result = values[0]*(values[1]/values[2]-1);
                unit = "bbl";
                break;
            case 28:
                result = ((values[0]+values[1])*values[2]-values[3]*values[4])/(values[5]-values[6]-values[7]);
                unit = "ppg";
                break;
            case 29:
                result = values[0]*CONSTANT*values[1]*values[2]/(values[3]+values[4]);
                unit = "psi";
                break;
        }
        TextView answerTextView = (TextView) getView().findViewById(R.id.answer);
        CharSequence answer = TextUtils.concat(String.format("%.3f", result)," ",getResources().getTextArray(R.array.api_units)[position]);
        answerTextView.setText(answer);
        Toast toast = Toast.makeText(getActivity(), answer, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void calculate_00981(int position, float[] values) {
        double result = 0;
        String unit = "";
        switch (position) {
            case 0:
                result = values[0]*0.0981*values[1];
                unit = "bar";
                break;
            case 1:
                result = values[0]*0.0981;
                unit = "bar/m";
                break;
            case 2:
                result = values[0]/(values[1]*0.0981);
                unit = "kg/L";
                break;
            case 3:
                result = values[0]+values[1];
                unit = "bar";
                break;
            case 4:
                result = values[0]*values[1];
                unit = "L/min";
                break;
            case 5:
                result = values[0]/values[1];
                unit = "m/min";
                break;
            case 6:
                result = values[0]/(values[1]*0.0981)+values[2];
                unit = "kg/L";
                break;
            case 7:
                result = values[0]/(values[1]*0.0981)+values[2];
                unit = "kg/L";
                break;
            case 8:
                result = values[0]*values[1]*values[1]/(values[2]*values[2]);
                unit = "bar";
                break;
            case 9:
                result = values[0]*values[1]/values[2];
                unit = "bar";
                break;
            case 10:
                result = values[0]/(values[1]*0.0981)+values[2];
                unit = "kg/L";
                break;
            case 11:
                result = (values[0]-values[1])*0.0981*values[2];
                unit = "bar";
                break;
            case 12:
                result = values[0]/(values[1]*0.0981)+values[2];
                unit = "kg/L";
                break;
            case 13:
                result = values[0]+values[1];
                unit = "bar";
                break;
            case 14:
                result = (values[0]/values[1])*values[2];
                unit = "bar";
                break;
            case 15:
                result = (values[0]-values[1])*4.2/(4.2-values[2]);
                unit = "kg/L";
                break;
            case 16:
                result = values[0]/(values[1]*0.0981);
                unit = "m/hr";
                break;
            case 17:
                break;
            case 18:
                result = (values[0]/values[1]-values[2]/values[3])*values[4];
                unit = "L";
                break;
            case 19:
                result = values[0]*0.0981*values[1]/(values[2]-values[3]);
                unit = "bar/m";
                break;
            case 20:
                result = values[0]*0.0981*values[1]/(values[2]-values[3]);
                unit = "bar/m";
                break;
            case 21:
                result = values[0]*values[1]/values[2];
                unit = "m";
                break;
            case 22:
                result = values[0]*values[1]/values[2];
                unit = "m";
                break;
            case 23:
                result = (values[0]-values[1])*(values[2]-values[3])/(values[4]*0.0981*values[5]);
                unit = "m";
                break;
            case 24:
                result = (values[0]-values[1])*(values[2]-values[3])/(values[4]*0.0981*values[5]);
                unit = "m";
                break;
            case 25:
                result = values[0]*values[1]/(values[2]-values[3]);
                unit = "L";
                break;
            case 26:
                result = values[0]*values[1]*values[2]/(values[3]-values[4]);
                unit = "L";
                break;
            case 27:
                result = values[0]*(values[1]/values[2]-1);
                unit = "L";
                break;
            case 28:
                result = ((values[0]+values[1])*values[2]-values[3]*values[4])/(values[5]-values[6]-values[7]);
                unit = "kg/L";
                break;
            case 29:
                result = values[0]*0.0981*values[1]*values[2]/(values[3]+values[4]);
                unit = "bar";
                break;
        }
        TextView answerTextView = (TextView) getView().findViewById(R.id.answer);
        CharSequence answer = TextUtils.concat(String.format("%.3f", result)," ",getResources().getTextArray(R.array.formula_expressions_metric_00981)[position]);
        answerTextView.setText(answer);
        Toast toast = Toast.makeText(getActivity(), answer, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void calculate_SI(int position, float[] values) {
        double result = 0;
        String unit = "";
        switch (position) {
            case 0:
                result = values[0]*0.00981*values[1];
                break;
            case 1:
                result = values[0]*0.00981;
                break;
            case 2:
                result = values[0]/(values[1]*0.00981);
                break;
            case 3:
                result = values[0]+values[1];
                break;
            case 4:
                result = values[0]*values[1];
                break;
            case 5:
                result = values[0]/values[1];
                break;
            case 6:
                result = values[0]/(values[1]*0.00981)+values[2];
                break;
            case 7:
                result = values[0]/(values[1]*0.00981)+values[2];
                break;
            case 8:
                result = values[0]*values[1]*values[1]/(values[2]*values[2]);
                break;
            case 9:
                result = values[0]*values[1]/values[2];
                break;
            case 10:
                result = values[0]/(values[1]*0.00981)+values[2];
                break;
            case 11:
                result = (values[0]-values[1])*0.00981*values[2];
                break;
            case 12:
                result = values[0]/(values[1]*0.00981)+values[2];
                break;
            case 13:
                result = values[0]+values[1];
                break;
            case 14:
                result = (values[0]/values[1])*values[2];
                break;
            case 15:
                result = (values[0]-values[1])*4200/(4200-values[2]);
                break;
            case 16:
                result = values[0]/(values[1]*0.00981);
                break;
            case 17:
                break;
            case 18:
                result = (values[0]/values[1]-values[2]/values[3])*values[4];
                break;
            case 19:
                result = values[0]*0.00981*values[1]/(values[2]-values[3]);
                break;
            case 20:
                result = values[0]*0.00981*values[1]/(values[2]-values[3]);
                break;
            case 21:
                result = values[0]*values[1]/values[2];
                break;
            case 22:
                result = values[0]*values[1]/values[2];
                break;
            case 23:
                result = (values[0]-values[1])*(values[2]-values[3])/(values[4]*0.00981*values[5]);
                break;
            case 24:
                result = (values[0]-values[1])*(values[2]-values[3])/(values[4]*0.00981*values[5]);
                break;
            case 25:
                result = values[0]*values[1]/(values[2]-values[3]);
                break;
            case 26:
                result = values[0]*values[1]*values[2]/(values[3]-values[4]);
                break;
            case 27:
                result = values[0]*(values[1]/values[2]-1);
                break;
            case 28:
                result = ((values[0]+values[1])*values[2]-values[3]*values[4])/(values[5]-values[6]-values[7]);
                break;
            case 29:
                result = values[0]*0.00981*values[1]*values[2]/(values[3]+values[4]);
                break;
        }
        TextView answerTextView = (TextView) getView().findViewById(R.id.answer);
        CharSequence answer = TextUtils.concat(String.format("%.3f", result)," ",getResources().getTextArray(R.array.SI_units)[position]);
        answerTextView.setText(answer);
        Toast toast = Toast.makeText(getActivity(), answer, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void calculate_10(int position, float[] values) {
        double result = 0;
        switch (position) {
            case 0:
                result = values[0]*values[1]/10;
                break;
            case 1:
                result = values[0]/10;
                break;
            case 2:
                result = values[0]*10/values[1];
                break;
            case 3:
                result = values[0]+values[1];
                break;
            case 4:
                result = values[0]*values[1];
                break;
            case 5:
                result = values[0]/values[1];
                break;
            case 6:
                result = values[0]*10/values[1]+values[2];
                break;
            case 7:
                result = values[0]*10/values[1]+values[2];
                break;
            case 8:
                result = values[0]*values[1]*values[1]/(values[2]*values[2]);
                break;
            case 9:
                result = values[0]*values[1]/values[2];
                break;
            case 10:
                result = values[0]*10/values[1]+values[2];
                break;
            case 11:
                result = (values[0]-values[1])*values[2]/10;
                break;
            case 12:
                result = values[0]*10/values[1]+values[2];
                break;
            case 13:
                result = values[0]+values[1];
                break;
            case 14:
                result = (values[0]/values[1])*values[2];
                break;
            case 15:
                result = (values[0]-values[1])*4.2/(4.2-values[2]);
                break;
            case 16:
                result = values[0]*10/values[1];
                break;
            case 17:
                break;
            case 18:
                result = (values[0]/values[1]-values[2]/values[3])*values[4];
                break;
            case 19:
                result = values[0]*values[1]/((values[2]-values[3])*10);
                break;
            case 20:
                result = values[0]*values[1]/((values[2]-values[3])*10);
                break;
            case 21:
                result = values[0]*values[1]/values[2];
                break;
            case 22:
                result = values[0]*values[1]/values[2];
                break;
            case 23:
                result = (values[0]-values[1])*(values[2]-values[3])*10/(values[4]*values[5]);
                break;
            case 24:
                result = (values[0]-values[1])*(values[2]-values[3])*10/(values[4]*values[5]);
                break;
            case 25:
                result = values[0]*values[1]/(values[2]-values[3]);
                break;
            case 26:
                result = values[0]*values[1]*values[2]/(values[3]-values[4]);
                break;
            case 27:
                result = values[0]*(values[1]/values[2]-1);
                break;
            case 28:
                result = ((values[0]+values[1])*values[2]-values[3]*values[4])/(values[5]-values[6]-values[7]);
                break;
            case 29:
                result = values[0]*values[1]*values[2]/((values[3]+values[4])*10);
                break;
        }
        TextView answerTextView = (TextView) getView().findViewById(R.id.answer);
        CharSequence answer = TextUtils.concat(String.format("%.3f", result)," ",getResources().getTextArray(R.array.metric_units_constant_10)[position]);
        answerTextView.setText(answer);
        Toast toast = Toast.makeText(getActivity(), answer, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public void calculate_102(int position, float[] values) {
        double result = 0;
        switch (position) {
            case 0:
                result = values[0]*values[1]/10.2;
                break;
            case 1:
                result = values[0]/10.2;
                break;
            case 2:
                result = values[0]*10.2/values[1];
                break;
            case 3:
                result = values[0]+values[1];
                break;
            case 4:
                result = values[0]*values[1];
                break;
            case 5:
                result = values[0]/values[1];
                break;
            case 6:
                result = values[0]*10.2/values[1]+values[2];
                break;
            case 7:
                result = values[0]*10.2/values[1]+values[2];
                break;
            case 8:
                result = values[0]*values[1]*values[1]/(values[2]*values[2]);
                break;
            case 9:
                result = values[0]*values[1]/values[2];
                break;
            case 10:
                result = values[0]*10.2/values[1]+values[2];
                break;
            case 11:
                result = (values[0]-values[1])*values[2]/10.2;
                break;
            case 12:
                result = values[0]*10.2/values[1]+values[2];
                break;
            case 13:
                result = values[0]+values[1];
                break;
            case 14:
                result = (values[0]/values[1])*values[2];
                break;
            case 15:
                result = (values[0]-values[1])*4.2/(4.2-values[2]);
                break;
            case 16:
                result = values[0]*10.2/values[1];
                break;
            case 17:
                break;
            case 18:
                result = (values[0]/values[1]-values[2]/values[3])*values[4];
                break;
            case 19:
                result = values[0]*values[1]/((values[2]-values[3])*10.2);
                break;
            case 20:
                result = values[0]*values[1]/((values[2]-values[3])*10.2);
                break;
            case 21:
                result = values[0]*values[1]/values[2];
                break;
            case 22:
                result = values[0]*values[1]/values[2];
                break;
            case 23:
                result = (values[0]-values[1])*(values[2]-values[3])*10.2/(values[4]*values[5]);
                break;
            case 24:
                result = (values[0]-values[1])*(values[2]-values[3])*10.2/(values[4]*values[5]);
                break;
            case 25:
                result = values[0]*values[1]/(values[2]-values[3]);
                break;
            case 26:
                result = values[0]*values[1]*values[2]/(values[3]-values[4]);
                break;
            case 27:
                result = values[0]*(values[1]/values[2]-1);
                break;
            case 28:
                result = ((values[0]+values[1])*values[2]-values[3]*values[4])/(values[5]-values[6]-values[7]);
                break;
            case 29:
                result = values[0]*values[1]*values[2]/((values[3]+values[4])*10.2);
                break;
        }
        TextView answerTextView = (TextView) getView().findViewById(R.id.answer);
        CharSequence answer = TextUtils.concat(String.format("%.3f", result)," ",getResources().getTextArray(R.array.metric_units_00981)[position]);
        answerTextView.setText(answer);
        Toast toast = Toast.makeText(getActivity(), answer, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


    private float[] getValues() {
        for (int i = 0; i < size; i++) {
            EditText editTextListItem = myEditTextList.get(i);
                if (TextUtils.isEmpty(editTextListItem.getText())) {
                    Toast toast = Toast.makeText(getActivity(), "Enter " + editTextListItem.getHint(), Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER, 0, 0);
                    toast.show();
                    editTextListItem.requestFocus();
                    TextView answerTextView = (TextView) getView().findViewById(R.id.answer);
                    answerTextView.setText("Answer");
                    return null;
                }
            }
        if (size > 0) {
            float[] values = new float[0];
            if (!myEditTextList.isEmpty()) {
                values = new float[size];
                for (int j = 0; j < size; j++) {
                    values[j] = Float.valueOf(myEditTextList.get(j).getText().toString());
                }
            }
            return values;
        }
        return null;
    }

    private void recallAnswer(){
        if (answer == "")
            return;
        TextView answerTextView = (TextView) getView().findViewById(R.id.answer);
        answerTextView.setText(answer);
    }

    public static void hideSoftKeyboard (Activity activity, View view)
    {
        InputMethodManager imm = (InputMethodManager)activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
    }
}