package com.example.admin.myapplication.retrofit_interfaces;

import com.example.admin.myapplication.Pojo.DashboardPojo;
import com.example.admin.myapplication.Pojo.GrievanceData;
import com.example.admin.myapplication.Pojo.ReceivedUpdatePojo;
import com.example.admin.myapplication.Pojo.ReceivedUpdateResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

public interface RetrofitInterface {
    @GET("/Etown/ETPanchayatGrievanceDetails")
    Call<List<GrievanceData>> getGrievanceDatas(@Header("accessToken")String accessToken,
                                               @Query("Type")String Type,
                                               @Query("District")String District,
                                               @Query("Panchayat")String Panchayat,
                                               @Query("GrievanceNo")String GrievanceNo
                                          );
    @GET("/Etown/ETPanchayatGrievanceDetails")
    Call<List<ReceivedUpdatePojo>> getGrievanceReceivedDatas(@Header("accessToken")String accessToken,
                                                     @Query("Type")String Type,
                                                     @Query("District")String District,
                                                     @Query("Panchayat")String Panchayat,
                                                     @Query("GrievanceNo")String GrievanceNo
    );
    @GET("/Etown/PanchayatComplaintResponse")
    Call<ReceivedUpdateResult> updateResult(@Header("accessToken")String accessToken,
                                            @Query("UniqueId")String UniqueId,
                                            @Query("GrievanceNo")String GrievanceNo,
                                            @Query("District")String District,
                                            @Query("Panchayat")String Panchayat,
                                            @Query("Remarks")String Remarks,
                                            @Query("Status")String Status,
                                            @Query("UserDesgination")String UserDesgination,
                                            @Query("UserId")String UserId,
                                            @Query("UserName")String UserName,
                                            @Query("EntryType")String EntryType
                                            );
    @GET("/Etown/ETPanchayatGrievanceDetails")
    Call<List<DashboardPojo>> getDashboardDatas(@Header("accessToken")String accessToken,
                                                @Query("Type")String Type,
                                                @Query("District")String District,
                                                @Query("Panchayat")String Panchayat,
                                                @Query("GrievanceNo")String GrievanceNo
                                                );


}
