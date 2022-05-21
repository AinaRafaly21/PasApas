package com.example.pasapas.fragment;

import androidx.fragment.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.pasapas.MainActivity;
import com.example.pasapas.R;
import com.example.pasapas.activity.CreationEnfant;
import com.example.pasapas.activity.Login;
import com.example.pasapas.adapter.AdapterEnfant;
import com.example.pasapas.model.Users;
import com.example.pasapas.tools.Tools;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EnfantsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EnfantsFragment extends Fragment {

    private ListView listView;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EnfantsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EnfantsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EnfantsFragment newInstance(String param1, String param2) {
        EnfantsFragment fragment = new EnfantsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Button button = getActivity().findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                create();
            }
        });
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profil, container, false);
        Users users = Tools.getUser(this.getActivity().getSharedPreferences("userIdentity", Context.MODE_PRIVATE));
        listView = view.findViewById(R.id.listview);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                details();
            }
        });
        listView.setAdapter(new AdapterEnfant(getContext(),users.getEnfants()));
        return view;

    }

    void create(){
        Intent intent = new Intent(getActivity(), CreationEnfant.class);
        startActivity(intent);
    }

    void details(){
        Intent intent = new Intent(getActivity(), MainActivity.class);
        startActivity(intent);
    }
}