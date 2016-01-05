package com.sciencedefine.iwcfformulasheet;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.ListFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


import com.sciencedefine.iwcfformulasheet.dummy.DummyContent;

import org.w3c.dom.Text;

/**
 * A fragment representing a list of Items.
 * <p />
 * <p />
 * Activities containing this fragment MUST implement the {@link }
 * interface.
 */
public class FormulaTitlesFragment extends ListFragment {
    public static final String PREF = "CalculatorPref";
    public final static String UNITS = "units";
    private String unit;
    private CharSequence[] unitStringArray;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    // TODO: Rename and change types of parameters
    public static FormulaTitlesFragment newInstance(String param1, String param2) {
        FormulaTitlesFragment fragment = new FormulaTitlesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public FormulaTitlesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.i("state", "FormulaTitlesFragment onCreate");

        super.onCreate(savedInstanceState);
        // Retain this Fragment across Activity reconfigurations
        //setRetainInstance(true);

        // TODO: Change Adapter to display your content
        //setListAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.formula_titles)));
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.i("State", "onActivityCreated called from ForTitFrag");
        final SharedPreferences unitPref = getActivity().getSharedPreferences(PREF, Context.MODE_PRIVATE);
        unit = unitPref.getString(UNITS, "api");
        if (unit.equals("api")){
            unitStringArray = getResources().getTextArray(R.array.api_units);
        }
        if (unit.equals("metric_10")){
            unitStringArray = getResources().getTextArray(R.array.metric_units_constant_10);
        }
        if (unit.equals("metric_102")){
            unitStringArray = getResources().getTextArray(R.array.metric_units_00981);
        }
        if (unit.equals("metric_00981")){
            unitStringArray = getResources().getTextArray(R.array.metric_units_00981);
        }
        if (unit.equals("SI")){
            unitStringArray = getResources().getTextArray(R.array.SI_units);
        }
        setListAdapter(new ArrayAdapter<CharSequence>(getActivity(), android.R.layout.simple_list_item_1, formulaTitlesWithUnits(getResources().getTextArray(R.array.formula_titles), unitStringArray)));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.i("State", "FormulaTitlesFragment onCreateView");
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        if (null != mListener) {
            // Notify the active callbacks interface (the activity, if the
            // fragment is attached to one) that an item has been selected.
            mListener.onFragmentInteraction(position);
        }
    }

    /**
    * This interface must be implemented by activities that contain this
    * fragment to allow an interaction in this fragment to be communicated
    * to the activity and potentially other fragments contained in that
    * activity.
    * <p>
    * See the Android Training lesson <a href=
    * "http://developer.android.com/training/basics/fragments/communicating.html"
    * >Communicating with Other Fragments</a> for more information.
    */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(int position);
    }

    //private String title_unit = getResources().getStringArray(R.array.formula_titles)[1];
    private CharSequence[] formulaTitlesWithUnits(CharSequence[] title, CharSequence[] unit) {
        CharSequence[] title_and_unit = new CharSequence[30];
        for (int i = 0; i < 30; i++){
            title_and_unit[i] = TextUtils.concat(title[i]," (", unit[i], ")");
        }
        return title_and_unit;
    }

}
