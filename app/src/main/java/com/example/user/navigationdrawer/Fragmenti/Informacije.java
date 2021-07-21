package com.example.user.navigationdrawer.Fragmenti;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.navigationdrawer.R;

/**
 * Created by Matevz on 20. 12. 2016.
 */

public class Informacije extends Fragment {

    View myView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.informacije1, container, false);
        return myView;
    }
}