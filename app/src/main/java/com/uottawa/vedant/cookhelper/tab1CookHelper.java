package com.uottawa.vedant.cookhelper;

/**
 * Created by Jamar on 12/3/2016.
 */

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//first tab of the help activity page
public class tab1CookHelper extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1cookhelper, container, false);
        return rootView;
    }
}
