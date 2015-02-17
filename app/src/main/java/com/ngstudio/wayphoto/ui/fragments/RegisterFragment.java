package com.ngstudio.wayphoto.ui.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.ngstudio.wayphoto.R;
import com.ngstudio.wayphoto.ui.activities.LoginActivity;
import com.ngstudio.wayphoto.ui.dialogs.AlertDialogBase;

import java.util.Map;

public class RegisterFragment extends BaseFragment<LoginActivity> {

    public static final String SAVED_NUMBER = "saved_number";
    public static final String SAVED_NAME = "saved_name";

    private EditText etPhoneNumber, etUserName;
    private EditText etPin;
    private TextView tvClearInfo;
    private Button buttonRegister;
    private Spinner spinnerLanguage;
    ToggleButton tbLanguageOption;

    //private AlertDialogBase alertPinType;
    //private String language = CodesMap.getLanguageMap().get(CodesMap.getDefaultLanguageKey());

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //alertPinType = new AlertDialogBase(activity);
        //alertPinType.setTitle(R.string.title_pin_type);
        //TextView text = new TextView(activity);
        //int padding = getResources().getDimensionPixelSize(R.dimen.margin_widget_default_small);
        //text.setPadding(padding,padding,padding,padding);
        //text.setText(R.string.content_pin_type);
        //text.setTextColor(Color.WHITE);
        //alertPinType.setCustomView(text);

        /*alertPinType.setCancelable(false);
        alertPinType.addNeutralButton(R.string.text_button_ok,new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //SharedPreferences settings = getActivity().getPreferences(Context.MODE_PRIVATE);
                //settings.edit().clear().commit();
            }
        });*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResID(), container, false);

        return view;
    }

    public boolean hasConnection(final Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        wifiInfo = cm.getActiveNetworkInfo();
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        return false;
    }

    void saveEnterData() {
        SharedPreferences sPref = getActivity().getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sPref.edit();
        ed.putString(SAVED_NUMBER, etPhoneNumber.getText().toString());
        ed.putString(SAVED_NAME, etUserName.getText().toString());
        ed.commit();
    }

    void loadEnterData() {
        String number = getActivity().getPreferences(Context.MODE_PRIVATE).getString(SAVED_NUMBER, "");
        String name = getActivity().getPreferences(Context.MODE_PRIVATE).getString(SAVED_NAME, "");
        if (!number.isEmpty()) {
            etPhoneNumber.setText(number);
            etUserName.setText(name);
            //alertPinType.show();
        }
    }

    void clearInfo() {
        etPhoneNumber.getText().clear();
        etUserName.getText().clear();
        etPin.getText().clear();
    }

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_register;
    }
}
