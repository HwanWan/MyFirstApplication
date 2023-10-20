package com.jnu.student.Fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jnu.student.R;
import com.tencent.tencentmap.mapsdk.maps.model.LatLng;
import com.tencent.tencentmap.mapsdk.maps.CameraUpdateFactory;
import com.tencent.tencentmap.mapsdk.maps.TencentMap;
import com.tencent.tencentmap.mapsdk.maps.model.Marker;
import com.tencent.tencentmap.mapsdk.maps.model.MarkerOptions;
import com.tencent.tencentmap.mapsdk.maps.model.TileOverlayOptions;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TencentMapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TencentMapFragment extends Fragment {
    private com.tencent.tencentmap.mapsdk.maps.MapView mapView = null;


    public TencentMapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment TencentMapFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TencentMapFragment newInstance() {
        TencentMapFragment fragment = new TencentMapFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_tencent_map, container, false);
        mapView = rootView.findViewById(R.id.mapView);

        TencentMap tencentMap = mapView.getMap();

        // 暨南大学珠海校区的经纬度
        LatLng point1 = new LatLng(22.249938, 113.534327);
        // 【1】设置地图中心点（暨南大学珠海校区）、层级（15）
        tencentMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point1,16.5F));


        // 【2】添加标记点及响应事件
        // 创建标记
        Marker marker = tencentMap.addMarker(new MarkerOptions(point1).title("暨大"));
        // 设置标记点击事件
        tencentMap.setOnMarkerClickListener(new TencentMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {
                // 处理标记点击事件的逻辑
                Toast.makeText(requireContext(),"点击标记的响应事件",Toast.LENGTH_SHORT);
                return true; // 返回true表示事件已处理
            }
        });
        return rootView;
    }
    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }
    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }
    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }
    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}