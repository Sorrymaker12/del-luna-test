package com.example.labux;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Content1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Content1 extends Fragment {

    RecyclerView recyclerView;
    RecyclerView.Adapter rvAdapter;
    rvAdapter.rvClickListener listener;
    RecyclerView.LayoutManager layoutManager;

    String[] pPrice = {"Rp 962.260", "Rp 740.770", "Rp 518.539", "Rp 828.000", "Rp 677.000", "Rp 305.000"};
    String[] pname = {"BTS - Love Yourself Hoodie", "EXO - Don't Mess Up My Tempo Hoodie", "NCT 127 - Neo Zone Short Sleeve T shirts", "KAI - Short Sleeve T Shirts with Photo Tag", "WayV - 3rd Anniversary T-Shirt", "Red Velvet - Really Bad Boy White T-Shirt"};
    int[] pImage = {R.drawable.bts_hoodie, R.drawable.exo_hoodie, R.drawable.nct127_cloth, R.drawable.kai_cloth, R.drawable.wayv_cloth, R.drawable.rv_cloth};

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Content1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Content1.
     */
    // TODO: Rename and change types and number of parameters
    public static Content1 newInstance(String param1, String param2) {
        Content1 fragment = new Content1();
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
        View view = inflater.inflate(R.layout.fragment_content1, container, false);

        setOnClickListener();

        recyclerView = view.findViewById(R.id.rv1);
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
                startActivity(intent);
            }
        };
    }
}