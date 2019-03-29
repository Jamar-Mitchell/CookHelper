package com.uottawa.vedant.cookhelper;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Vedant on 2016-12-04.
 */
// temporary placeholder for a fifth help page
public class tab5Ingredients extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab5ingredients, container, false);
        return rootView;
    }
}