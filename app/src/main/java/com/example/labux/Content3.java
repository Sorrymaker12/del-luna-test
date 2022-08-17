package com.example.labux;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import android.content.Intent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Content3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Content3 extends Fragment {

    Bundle Extras;
    String a;
    RecyclerView recyclerView;
    RecyclerView.Adapter rvAdapter;
    rvAdapter.rvClickListener listener;
    RecyclerView.LayoutManager layoutManager;
    String[] pPrice = {"Rp 602.000", "Rp 301.000", "Rp 828.000", "Rp 527.000", "Rp 271.000", "Rp 602.000"};
    String[] pname = {"NCT 127 - BAKER HOUSE Room Kit", "NCT DREAM - Glitch Mode Pop Up Card Holder+ID Card Set", "Aespa - Official Fanlight (Lightstick)", "Red Velvet - 'Feel My Rhythm’ Necklace + Exclusive Photo Card", "WayV - 3rd Anniversary Tattoo + Sticker Set 2019 Ver.", "TVXQ! - 18th ANNIVERSARY Memory Frame Fragrance"};
    int[] pImage = {R.drawable.nct127_other, R.drawable.nctd_other, R.drawable.aespa_other, R.drawable.rv_other, R.drawable.wayv_other, R.drawable.tvxq_other};


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Content3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Content3.
     */
    // TODO: Rename and change types and number of parameters
    public static Content3 newInstance(String param1, String param2) {
        Content3 fragment = new Content3();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Extras = getActivity().getIntent().getExtras();
        a = Extras.getString("username");
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_content3, container, false);

        setOnClickListener();
        // Add the following lines to create RecyclerView
        recyclerView = view.findViewById(R.id.rv3);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        rvAdapter = new rvAdapter(getActivity(), pname, pPrice, pImage, listener);
        recyclerView.setAdapter(rvAdapter);
        return view;
    }

    private void setOnClickListener() {
        listener = new rvAdapter.rvClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getContext(), ItemDetail.class);
                intent.putExtra("name", pname[position]);
                intent.putExtra("price", pPrice[position]);
                intent.putExtra("image", pImage[position]);
                intent.putExtra("username", a);
                startActivity(intent);
            }
        };
    }
}