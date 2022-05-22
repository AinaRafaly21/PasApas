package com.example.pasapas.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.pasapas.R;
import com.example.pasapas.activity.CoursActivity;
import com.example.pasapas.adapter.AdapterMatiere;
import com.example.pasapas.service.GlobalService;
import com.example.pasapas.tools.ResponseArray;
import com.example.pasapas.tools.RetrofitClientInstance;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CoursFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CoursFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CoursFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CoursFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CoursFragment newInstance(String param1, String param2) {
        CoursFragment fragment = new CoursFragment();
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
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_cours, container, false);
        CircularProgressIndicator progressIndicator = view.findViewById(R.id.circular);
        getCourses(view, progressIndicator);
        return view;
    }


    void getCourses(View view, CircularProgressIndicator progressIndicator) {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("userIdentity", Context.MODE_PRIVATE);
        String token = sharedPreferences.getString("token", null);
        GlobalService globalService = RetrofitClientInstance.getRetrofitInstance().create(GlobalService.class);
        globalService.courses(token).enqueue(
                new Callback<ResponseArray>() {
                    @Override
                    public void onResponse(Call<ResponseArray> call, Response<ResponseArray> response) {
                        System.out.println(response.body().toString());
                        if(response.body().getCode() == 200) {
//                            text.setText(response.body().code);
                            System.out.println("Body = " + response.body().getData().toString());
                            Gson gson = new Gson();
                            Type type = new TypeToken<List<String>>(){}.getType();
                            List<String> array = gson.fromJson(response.body().getData(), type);
                            progressIndicator.hide();
                            generateList(array, view);
                        }else {
                            progressIndicator.hide();
                            Toast.makeText(getContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseArray> call, Throwable t) {
                        // if error occurs in network transaction then we can get the error in this method.
                        System.out.println(t.getMessage());
                        progressIndicator.hide();
                        Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_LONG).show(); //dismiss progress dialog
                    }
                });
    }

    void details(String nom) {
        System.out.println("Details cours ** " + nom);
        Intent intent = new Intent(getActivity(), CoursActivity.class);
        intent.putExtra("nomCours", nom);
        startActivity(intent);
    }

    void generateList(List<String> list, View view) {
        ListView listView = view.findViewById(R.id.listMatieres);
        listView.setAdapter(new AdapterMatiere(getContext(),list));
        listView.setClickable(true);
        System.out.println("ListView == " + listView.toString());
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                details(list.get(i));
            }
        });

    }
}