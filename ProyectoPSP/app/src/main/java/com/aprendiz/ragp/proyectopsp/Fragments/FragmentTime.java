package com.aprendiz.ragp.proyectopsp.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aprendiz.ragp.proyectopsp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentTime extends Fragment {


    public FragmentTime() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fragment_time, container, false);
    }

}
