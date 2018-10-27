package com.example.admin.myapplication.contracts;

import com.example.admin.myapplication.Pojo.DashboardPojo;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Retrofit;

public interface IdashboardInterface {
    interface view{
        void showResult(String msg);
        void setBarChartUsingActualDatas(BarData barData,DashboardPojo dashboardPojo);
    }
    interface presenter{
        PieData getDataForPieChart();
        void getDataForBarChart(List<DashboardPojo> dashboardPojoList);
        void getDashboardData(Retrofit retrofit, String type, String district, String panchayat, String grievanceNo);
        String getDate();
    }
    interface model{

    }
}
