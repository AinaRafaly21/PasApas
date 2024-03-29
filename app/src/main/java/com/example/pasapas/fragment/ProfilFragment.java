package com.example.pasapas.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.pasapas.MainActivity;
import com.example.pasapas.R;
import com.example.pasapas.activity.Login;
import com.example.pasapas.adapter.AdapterEnfant;
import com.example.pasapas.model.Enfants;
import com.example.pasapas.model.Users;
import com.example.pasapas.tools.Tools;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class ProfilFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public ProfilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfilFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfilFragment newInstance(String param1, String param2) {
        ProfilFragment fragment = new ProfilFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profil, container, false);
        setKids(view);
        // Inflate the layout for this fragment
        return view;
    }

    void setKids(View view) {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("userIdentity", Context.MODE_PRIVATE);
        Integer indexKids = sharedPreferences.getInt("index", 0);
        Users users = Tools.getUser(sharedPreferences);
        Enfants enfants = users.getEnfants().get(indexKids);
        TextView textView = view.findViewById(R.id.nomAccueil);
        textView.setText(enfants.getNom() + " - " + enfants.getAge() + " ans");
    }
}