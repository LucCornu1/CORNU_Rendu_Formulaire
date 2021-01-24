package com.example.myformulaireapp;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class SecondFragment extends Fragment {

    TextView name, mail, C, CPP, Java, Pascal, JS, Php, Csharp, amount = null;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_second, container, false);
    }

    @SuppressLint({"SetTextI18n", "ResourceAsColor"})
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name = (TextView) view.findViewById(R.id.textview_Name);
        mail = (TextView) view.findViewById(R.id.textview_Mail);
        C = (TextView) view.findViewById(R.id.textview_C);
        CPP = (TextView) view.findViewById(R.id.textview_Cpp);
        Java = (TextView) view.findViewById(R.id.textview_Java);
        Pascal = (TextView) view.findViewById(R.id.textview_Pascal);
        JS = (TextView) view.findViewById(R.id.textview_Javascript);
        Php = (TextView) view.findViewById(R.id.textview_Php);
        Csharp = (TextView) view.findViewById(R.id.textview_Csharp);
        amount = (TextView) view.findViewById(R.id.textview_Playtime);

        if (getArguments().getString("FirstName") != null && getArguments().getString("Name") != null) {
            name.setText(getArguments().getString("Civilite") + getArguments().getString("Name") + " " + getArguments().getString("FirstName"));
            mail.setText("Votre email : "+getArguments().getString("FirstName")+"."+getArguments().getString("Name")+"@ludus-academie.fr");
        }else{
            name.setText("Veuillez entrer les champs correspondants");
        }

        if (getArguments().getString("Hour") != null) {
            amount.setText("Votre temps de jeu est : "+getArguments().getString("Hour"));
        }else{
            amount.setText("0");
        }


        if (getArguments().getBoolean("C")) {
            C.setTextColor(getResources().getColor(R.color.good));
        }else{
            C.setTextColor(getResources().getColor(R.color.bad));
        }

        if (getArguments().getBoolean("C++")) {
            CPP.setTextColor(getResources().getColor(R.color.good));
        }else{
            CPP.setTextColor(getResources().getColor(R.color.bad));
        }

        if (getArguments().getBoolean("Java")) {
            Java.setTextColor(getResources().getColor(R.color.good));
        }else{
            Java.setTextColor(getResources().getColor(R.color.bad));
        }

        if (getArguments().getBoolean("Pascal")) {
            Pascal.setTextColor(getResources().getColor(R.color.good));
        }else{
            Pascal.setTextColor(getResources().getColor(R.color.bad));
        }

        if (getArguments().getBoolean("Javascript")) {
            JS.setTextColor(getResources().getColor(R.color.good));
        }else{
            JS.setTextColor(getResources().getColor(R.color.bad));
        }

        if (getArguments().getBoolean("PHP")) {
            Php.setTextColor(getResources().getColor(R.color.good));
        }else{
            Php.setTextColor(getResources().getColor(R.color.bad));
        }

        if (getArguments().getBoolean("C#")) {
            Csharp.setTextColor(getResources().getColor(R.color.good));
        }else{
            Csharp.setTextColor(getResources().getColor(R.color.bad));
        }

        view.findViewById(R.id.button_second).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }
}