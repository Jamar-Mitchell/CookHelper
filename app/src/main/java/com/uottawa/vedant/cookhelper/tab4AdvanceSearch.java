package com.uottawa.vedant.cookhelper;

/**
 * Created by Jamar on 12/3/2016.
 */
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Jamar on 12/1/2016.
 */
//Fourth tab of the help activity page

public class tab4AdvanceSearch extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab4advancesearch, container, false);
        return rootView;
    }
}
