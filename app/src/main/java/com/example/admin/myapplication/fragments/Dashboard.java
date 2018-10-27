package com.example.admin.myapplication.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.myapplication.Pojo.DashboardPojo;
import com.example.admin.myapplication.R;
import com.example.admin.myapplication.chart.MyValueFormatter;
import com.example.admin.myapplication.di.components.DaggerDashboardComponent;
import com.example.admin.myapplication.di.module.DashboardModule;
import com.example.admin.myapplication.di.module.NetworkModule;
import com.example.admin.myapplication.presenter.DashoboardPresenter;
import com.example.admin.myapplication.contracts.IdashboardInterface;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Retrofit;

public class Dashboard extends Fragment implements IdashboardInterface.view {
@BindView(R.id.bar_chart)
BarChart bar_chart;

@BindView(R.id.tv_received_date) TextView tv_received_date;
@BindView(R.id.tv_pending_date) TextView tv_pending_date;

@BindView(R.id.tv_received_application_count) TextView tv_received_application_count;
@BindView(R.id.tv_pending_application_count) TextView tv_pending_application_count;
@BindView(R.id.tv_completed_application_count) TextView tv_completed_application_count;

View dashBoardView;
@Inject
DashoboardPresenter dashoboardPresenter;
@Inject
Retrofit retrofit;

String mType="Abstract";
String mDistrict="Krishnagiri";
String mPanchayat="Mathigiri";
String mGrievanceNo="";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        dashBoardView = inflater.inflate(R.layout.dashboard_fragment, container, false);
        ButterKnife.bind(this,dashBoardView);
        DaggerDashboardComponent.builder().networkModule(new NetworkModule("http://www.predemos.com")).dashboardModule(new DashboardModule(this)).build().inject(this);
        initBarChart();
        dashoboardPresenter.getDashboardData(retrofit,mType,mDistrict,mPanchayat,mGrievanceNo);
        tv_received_date.setText(dashoboardPresenter.getDate());
        return dashBoardView;
    }
    void initBarChart(){
        bar_chart.setDrawBarShadow(false);
        bar_chart.setDrawValueAboveBar(true);

        bar_chart.setMaxVisibleValueCount(50);
        bar_chart.setPinchZoom(false);
        bar_chart.setDrawGridBackground(false);
        bar_chart.animateXY(1000,1000);
        bar_chart.getAxisRight().setDrawGridLines(false);
        bar_chart.getAxisLeft().setDrawGridLines(false);
        bar_chart.getXAxis().setDrawGridLines(false);
        XAxis xAxis = bar_chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        bar_chart.getXAxis().setDrawAxisLine(true);

        bar_chart.setPinchZoom(false);
        bar_chart.setDoubleTapToZoomEnabled(false);

        YAxis rightAxis = bar_chart.getAxisRight();
        rightAxis.setEnabled(false);

        Description description = new Description();
        description.setEnabled(false);
        bar_chart.setDescription(description);    // Hide the description
        bar_chart.getAxisLeft().setDrawLabels(true);
        bar_chart.getAxisRight().setDrawLabels(false);
        bar_chart.getXAxis().setDrawLabels(false);

        bar_chart.getLegend().setEnabled(true);   // Hide the legend
    }

    @Override
    public void showResult(String msg) {
        Log.e("message=>",msg);
//        Toast.makeText(getActivity(), msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setBarChartUsingActualDatas(BarData barData, DashboardPojo dashboardPojo) {
        barData.setValueFormatter(new MyValueFormatter());
        bar_chart.setData(barData);
        bar_chart.invalidate();
        if(dashboardPojo!=null){
            tv_received_application_count.setText(Integer.toString(dashboardPojo.getReceived())+" Applications");
            tv_pending_application_count.setText(Integer.toString(dashboardPojo.getProcess())+" Applications");
            tv_completed_application_count.setText(Integer.toString(dashboardPojo.getCompleted())+" Applications");
        }


    }
}
