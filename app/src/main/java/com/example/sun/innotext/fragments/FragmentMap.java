package com.example.sun.innotext;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.baidu.mapapi.map.MapView;

import java.util.Map;

public class FragmentMap extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_map, container, false);
        MapView mapView= view.findViewById(R.id.baidumap);
        mapView.removeViewAt(1);
        return view;
    }

}
