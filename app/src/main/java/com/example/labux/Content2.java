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
 * Use the {@link Content2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Content2 extends Fragment {

    Bundle Extras;
    String a;
    RecyclerView recyclerView;
    RecyclerView.Adapter rvAdapter;
    rvAdapter.rvClickListener listener;
    RecyclerView.LayoutManager layoutManager;
    String[] pPrice = {"Rp 301.000", "Rp 316.000", "Rp 391.000", "Rp 264.000", "Rp 226.000", "Rp 196.000"};
    String[] pname = {"Aespa - The 2nd Mini Album 'Girls' (Real World Ver.)", "NCT DREAM - The 2nd Album Repackage ’Beatbox’ (Digipack Ver.)", "Red Velvet - ’The ReVe Festival 2022 - Feel My Rhythm’(ReVe Ver.)", "Super Junior - Special Single Album 'The Road : Winter for Spring'", "Baekhyun - The 3rd Mini Album ‘Bambi’ (Jewel Case Ver.)", "SuperM - The 1st Album 'Super One' Limited Edition Cassette"};
    int[] pImage = {R.drawable.aespa_album, R.drawable.nctd_album, R.drawable.rv_album, R.drawable.suju_album, R.drawable.baekhyun_album, R.drawable.superm_album};

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Content2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Content2.
     */
    // TODO: Rename and change types and number of parameters
    public static Content2 newInstance(String param1, String param2) {
        Content2 fragment = new Content2();
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
        View view = inflater.inflate(R.layout.fragment_content2, container, false);

        setOnClickListener();

        // Add the following lines to create RecyclerView
        recyclerView = view.findViewById(R.id.rv2);
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