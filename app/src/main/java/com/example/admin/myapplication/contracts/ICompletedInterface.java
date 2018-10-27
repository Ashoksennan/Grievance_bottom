package com.example.admin.myapplication.contracts;

import com.example.admin.myapplication.Pojo.GrievanceData;
import com.example.admin.myapplication.Pojo.ReceivedUpdatePojo;
import com.example.admin.myapplication.presenter.CompletedPresenter;
import com.example.admin.myapplication.presenter.FilterClass;
import com.example.admin.myapplication.presenter.PendingPresenter;
import com.example.admin.myapplication.presenter.ReceivedPresenter;

import java.util.List;

import retrofit2.Retrofit;

public interface ICompletedInterface {
    interface view{
        void showResult(String msg);
        void setRecyclerViewWithitsData(List<GrievanceData> grievanceData1);
        void setRecyclerViewWithReceivedDatas(List<ReceivedUpdatePojo> grievanceData1);
        void showFilteredList(List<GrievanceData> grievanceData2);
    }
    interface model{

    }
    interface presenter{
        List<GrievanceData> getGdata(Retrofit retrofit, String type, String district, String panchayat, String grievanceNo);
        List<ReceivedUpdatePojo> getReceivedPojo(Retrofit retrofit, String type, String district, String panchayat, String grievanceNo);
        void updateReceived(Retrofit retrofit,String UniqueId,String GrievanceNo,String District,
                            String Panchayat,String Remarks,String Status,
                            String UserDesgination,String UserId,String UserName,String EntryType);
        void filterTheItems(ReceivedPresenter receivedPresenter, PendingPresenter pendingPresenter, CompletedPresenter completedPresenter, FilterClass filterClass, String priority, String wardno, String streetName);

    }

}
