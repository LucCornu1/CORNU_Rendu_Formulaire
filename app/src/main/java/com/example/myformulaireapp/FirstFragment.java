package com.example.myformulaireapp;

import android.annotation.SuppressLint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FirstFragment extends Fragment implements TextWatcher {

    private TextView text_Date = null;
    private TextView text_Mail = null;

    private EditText input_Name = null;
    private EditText input_FirstName = null;
    private EditText input_NumberHour = null;

    private String sName = null;
    private String sFirstName = null;
    private String sHour = null;
    private String sCivilite = "Mrs.";

    private RadioGroup group_Civilite = null;

    LinearLayout checkLayout = null;
    CheckBox checkbox = null;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        text_Date = (TextView) view.findViewById(R.id.textview_date);
        text_Mail = (TextView) view.findViewById(R.id.textview_mail);

        input_Name = (EditText) view.findViewById(R.id.editTextName);
        input_FirstName = (EditText) view.findViewById(R.id.editTextFirstName);
        input_NumberHour = (EditText) view.findViewById(R.id.editTextHourGame);

        group_Civilite = (RadioGroup) view.findViewById(R.id.radioSex);

        checkLayout = view.findViewById(R.id.checkLayout);

        input_Name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sName = (String) input_Name.getText().toString();
            }
        });

        input_FirstName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sFirstName = (String) input_FirstName.getText().toString();
            }
        });

        input_NumberHour.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                sHour = (String) input_NumberHour.getText().toString();

                if (sHour.length() > 0)
                {
                    int nHour = Integer.parseInt(sHour);

                    if (nHour <= 1)
                    {
                        input_NumberHour.setTextColor(getResources().getColor(R.color.good));
                        Toast.makeText(getContext(), "Correct", Toast.LENGTH_SHORT).show();
                    }else if (nHour >= 5) {
                        input_NumberHour.setTextColor(getResources().getColor(R.color.bad));
                        Toast.makeText(getContext(), "Addict", Toast.LENGTH_LONG).show();
                    }else {
                        input_NumberHour.setTextColor(getResources().getColor(R.color.attention));
                        Toast.makeText(getContext(), "Attention", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        setDate(text_Date);

        group_Civilite.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //Log.d("test", "*************\n\n\n\n\n\n\n\n\n\n\n\n "+checkedId+" \n\n\n\n\n\n\n\n\n\n\n\n*************");
                if (checkedId == R.id.radioMale)
                {
                    sCivilite = getResources().getString(R.string.mister);
                }else if (checkedId == R.id.radioFemale){
                    sCivilite = getResources().getString(R.string.miss);
                }
                if (sName != null)
                {
                    Toast.makeText(getContext(), "Welcome "+sCivilite+sName, Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.findViewById(R.id.button_validate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setMail(text_Mail, sName, sFirstName);
            }
        });

        view.findViewById(R.id.button_first).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle Form = new Bundle();
                Form.putString("Name", sName);
                Form.putString("Civilite", sCivilite);
                Form.putString("FirstName", sFirstName);
                Form.putString("Hour", sHour);

                int n = (int) checkLayout.getChildCount();
                String sString = null;

                for (int i = 0; i < n; i++)
                {
                    checkbox = (CheckBox) checkLayout.getChildAt(i);
                    // sString = checkbox.getText().toString();
                    // Log.d("test", "*************\n\n\n\n\n\n "+sString+" \n\n\n\n\n\n*************");
                    Form.putBoolean(checkbox.getText().toString(), checkbox.isChecked());
                }

                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment, Form);
            }
        });

        view.findViewById(R.id.button_reset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                input_Name.setText(null);
                input_FirstName.setText(null);
                input_NumberHour .setText(null);

                int n = (int) checkLayout.getChildCount();
                for (int i = 0; i < n; i++)
                {
                    checkbox = (CheckBox) checkLayout.getChildAt(i);
                    checkbox.setChecked(false);
                }
            }
        });
    }

    public void setDate (TextView view) {
        Date today = Calendar.getInstance().getTime();//getting date
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy"); // formating according to my need
        String date = formatter.format(today);
        view.setText(date);
        view.setTypeface(null, Typeface.ITALIC);
        view.setTextColor(getResources().getColor(R.color.autumn));
    }

    @SuppressLint("SetTextI18n")
    public void setMail (TextView view, String sName, String sFirstName) {
        view.setText(sFirstName+"."+sName+"@ludus-academie.fr");
    }





    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}