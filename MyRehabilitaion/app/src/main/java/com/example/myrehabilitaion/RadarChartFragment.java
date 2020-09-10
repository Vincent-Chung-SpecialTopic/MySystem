package com.example.myrehabilitaion;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class RadarChartFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstancestate) {
        View root = inflater.inflate(R.layout.activity_radar_chart, container, false);
        RadarChart radarChart = root.findViewById(R.id.radarChart);

        ArrayList<RadarEntry> visitorsForFirstWebsite = new ArrayList<>();

        visitorsForFirstWebsite.add(new RadarEntry(420));
        visitorsForFirstWebsite.add(new RadarEntry(475));
        visitorsForFirstWebsite.add(new RadarEntry(508));
        visitorsForFirstWebsite.add(new RadarEntry(660));
        visitorsForFirstWebsite.add(new RadarEntry(550));
        visitorsForFirstWebsite.add(new RadarEntry(630));
        visitorsForFirstWebsite.add(new RadarEntry(470));

        RadarDataSet radarDataSetForFirstWebsite = new RadarDataSet(visitorsForFirstWebsite, "Website 1");
        radarDataSetForFirstWebsite.setColor(Color.BLUE);
        radarDataSetForFirstWebsite.setLineWidth(2f);
        radarDataSetForFirstWebsite.setValueTextColor(Color.BLUE);
        radarDataSetForFirstWebsite.setValueTextSize(14f);

        ArrayList<RadarEntry> visitorsForSecondWebsite = new ArrayList<>();

        visitorsForSecondWebsite.add(new RadarEntry(310));
        visitorsForSecondWebsite.add(new RadarEntry(420));
        visitorsForSecondWebsite.add(new RadarEntry(685));
        visitorsForSecondWebsite.add(new RadarEntry(820));
        visitorsForSecondWebsite.add(new RadarEntry(490));
        visitorsForSecondWebsite.add(new RadarEntry(730));
        visitorsForSecondWebsite.add(new RadarEntry(200));

        RadarDataSet radarDataSetForSecondWebsite = new RadarDataSet(visitorsForSecondWebsite, "Website 2");
        radarDataSetForSecondWebsite.setColor(Color.BLUE);
        radarDataSetForSecondWebsite.setLineWidth(2f);
        radarDataSetForSecondWebsite.setValueTextColor(Color.BLUE);
        radarDataSetForSecondWebsite.setValueTextSize(14f);

        RadarData radarData = new RadarData();
        radarData.addDataSet(radarDataSetForFirstWebsite);
        radarData.addDataSet(radarDataSetForSecondWebsite);

        String[] labels = {"2014", "2015", "2016", "2017", "2018", "2019", "2020"};

        XAxis xAxis = radarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));

        radarChart.getDescription().setText("Radar Chart Example");
        radarChart.setData(radarData);

        return root;

    }
}
