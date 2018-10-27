package com.example.admin.myapplication.presenter;

import android.util.Log;

import com.example.admin.myapplication.Pojo.GrievanceData;
import com.example.admin.myapplication.comman.CommanDatas;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FilterClass {


    @Inject
    public FilterClass() {
    }
    public void filterTheItems(ReceivedPresenter receivedPresenter,PendingPresenter pendingPresenter,CompletedPresenter completedPresenter,String priority, String wardno, String streetName,List<GrievanceData> DataList,String catagory) {
       switch (catagory)
       {
           case "Pending":
               Log.e(priority+"=>"+wardno,"<==="+streetName);
               if(priority.equalsIgnoreCase("null") &&wardno.equalsIgnoreCase("null")&& streetName.equalsIgnoreCase("null")){
                   pendingPresenter.informFilteredListToPrensenter(DataList);
               }
               if(!priority.equalsIgnoreCase("null") &&!wardno.equalsIgnoreCase("null")&& !streetName.equalsIgnoreCase("null")){
                   pendingPresenter.informFilteredListToPrensenter(filteredList( priority,  wardno,  streetName,DataList));
               }
               else if(!priority.equalsIgnoreCase("null") && !wardno.equalsIgnoreCase("null")){
                   pendingPresenter.informFilteredListToPrensenter(filteredListBasedOnPriorityWard( priority,  wardno,DataList));
               }else if(!wardno.equalsIgnoreCase("null") && !streetName.equalsIgnoreCase("null")){
                   pendingPresenter.informFilteredListToPrensenter(filteredListBasedOnWardStreet( wardno,  streetName,DataList));
               }else if(!priority.equalsIgnoreCase("null") && !streetName.equalsIgnoreCase("null")){
                   pendingPresenter.informFilteredListToPrensenter(filteredListBasedOnPriorityStreet( priority,  streetName,DataList));
               }else if(!priority.equalsIgnoreCase("null")){
                   pendingPresenter.informFilteredListToPrensenter(filteredListBasedOnPriority( priority,DataList));
               }else if(!wardno.equalsIgnoreCase("null")){
                   pendingPresenter.informFilteredListToPrensenter(filteredListBasedOnWardNo( wardno,DataList));
               }else if(!streetName.equalsIgnoreCase("null")){
                   pendingPresenter.informFilteredListToPrensenter(filteredListBasedOnStreetName( streetName,DataList));
               }
               break;
           case "Received":
               Log.e(priority+"=>"+wardno,"<==="+streetName);
               if(priority.equalsIgnoreCase("null") &&wardno.equalsIgnoreCase("null")&& streetName.equalsIgnoreCase("null")){
                   receivedPresenter.informFilteredListToPrensenter(DataList);
               }
               if(!priority.equalsIgnoreCase("null") &&!wardno.equalsIgnoreCase("null")&& !streetName.equalsIgnoreCase("null")){
                 receivedPresenter.informFilteredListToPrensenter(filteredList( priority,  wardno,  streetName,DataList));
               }
               else if(!priority.equalsIgnoreCase("null") && !wardno.equalsIgnoreCase("null")){
                   completedPresenter.informFilteredListToPrensenter(filteredListBasedOnPriorityWard( priority,  wardno,DataList));
               }else if(!wardno.equalsIgnoreCase("null") && !streetName.equalsIgnoreCase("null")){
                   receivedPresenter.informFilteredListToPrensenter(filteredListBasedOnWardStreet( wardno,  streetName,DataList));
               }else if(!priority.equalsIgnoreCase("null") && !streetName.equalsIgnoreCase("null")){
                   receivedPresenter.informFilteredListToPrensenter(filteredListBasedOnPriorityStreet( priority,  streetName,DataList));
               }else if(!priority.equalsIgnoreCase("null")){
                   receivedPresenter.informFilteredListToPrensenter(filteredListBasedOnPriority( priority,DataList));
               }else if(!wardno.equalsIgnoreCase("null")){
                   receivedPresenter.informFilteredListToPrensenter(filteredListBasedOnWardNo( wardno,DataList));
               }else if(!streetName.equalsIgnoreCase("null")){
                   receivedPresenter.informFilteredListToPrensenter(filteredListBasedOnStreetName( streetName,DataList));
               }
               break;
           case "Completed":
               Log.e(priority+"=>"+wardno,"<==="+streetName);
               if(priority.equalsIgnoreCase("null") &&wardno.equalsIgnoreCase("null")&& streetName.equalsIgnoreCase("null")){
                   completedPresenter.informFilteredListToPrensenter(DataList);
               }
               if(!priority.equalsIgnoreCase("null") &&!wardno.equalsIgnoreCase("null")&& !streetName.equalsIgnoreCase("null")){
                   completedPresenter.informFilteredListToPrensenter(filteredList( priority,  wardno,  streetName,DataList));
               }
               else if(!priority.equalsIgnoreCase("null") && !wardno.equalsIgnoreCase("null")){
                   completedPresenter.informFilteredListToPrensenter(filteredListBasedOnPriorityWard( priority,  wardno,DataList));
               }else if(!wardno.equalsIgnoreCase("null") && !streetName.equalsIgnoreCase("null")){
                   completedPresenter.informFilteredListToPrensenter(filteredListBasedOnWardStreet( wardno,  streetName,DataList));
               }else if(!priority.equalsIgnoreCase("null") && !streetName.equalsIgnoreCase("null")){
                   completedPresenter.informFilteredListToPrensenter(filteredListBasedOnPriorityStreet( priority,  streetName,DataList));
               }else if(!priority.equalsIgnoreCase("null")){
                   completedPresenter.informFilteredListToPrensenter(filteredListBasedOnPriority( priority,DataList));
               }else if(!wardno.equalsIgnoreCase("null")){
                   completedPresenter.informFilteredListToPrensenter(filteredListBasedOnWardNo( wardno,DataList));
               }else if(!streetName.equalsIgnoreCase("null")){
                   completedPresenter.informFilteredListToPrensenter(filteredListBasedOnStreetName( streetName,DataList));
               }
               break;

       }

    }

    List<GrievanceData> filteredList(String priority, String wardno, String streetName,List<GrievanceData> DataList){
        List<GrievanceData> myList = new ArrayList<>();
        for(int i=0;i<DataList.size();i++){
            if(DataList.get(i).getPriority().equalsIgnoreCase(priority) &&
                    DataList.get(i).getWardNo().equalsIgnoreCase(wardno)&&
                    DataList.get(i).getStreetName().equalsIgnoreCase(streetName)){
                Log.e(DataList.get(i).getPriority(),"calling==><==="+priority);
                Log.e(DataList.get(i).getWardNo(),"calling==><==="+wardno);
                Log.e(DataList.get(i).getStreetName(),"calling==><==="+streetName);

                myList.add(DataList.get(i));
            }
        }
        return myList;
    }
    List<GrievanceData> filteredListBasedOnPriorityWard(String priority, String wardno,List<GrievanceData> DataList){
        List<GrievanceData> myList = new ArrayList<>();
        for(int i=0;i<DataList.size();i++){
            if(DataList.get(i).getPriority().equalsIgnoreCase(priority) &&
                    DataList.get(i).getWardNo().equalsIgnoreCase(wardno)){
                Log.e(DataList.get(i).getPriority(),"calling==><==="+priority);
                Log.e(DataList.get(i).getWardNo(),"calling==><==="+wardno);

                myList.add(DataList.get(i));
            }
        }
        return myList;
    }
    List<GrievanceData> filteredListBasedOnWardStreet(String wardno, String streetName,List<GrievanceData> DataList){
        List<GrievanceData> myList = new ArrayList<>();
        for(int i=0;i<DataList.size();i++){
            if(DataList.get(i).getWardNo().equalsIgnoreCase(wardno)&&
                    DataList.get(i).getStreetName().equalsIgnoreCase(streetName)){
                Log.e(DataList.get(i).getWardNo(),"calling==><==="+wardno);
                Log.e(DataList.get(i).getStreetName(),"calling==><==="+streetName);

                myList.add(DataList.get(i));
            }
        }
        return myList;
    }
    List<GrievanceData> filteredListBasedOnPriorityStreet(String priority, String streetName,List<GrievanceData> DataList){
        List<GrievanceData> myList = new ArrayList<>();
        for(int i=0;i<DataList.size();i++){
            if(DataList.get(i).getPriority().equalsIgnoreCase(priority)&&
                    DataList.get(i).getStreetName().equalsIgnoreCase(streetName)){
                Log.e(DataList.get(i).getPriority(),"calling==><==="+priority);
                Log.e(DataList.get(i).getStreetName(),"calling==><==="+streetName);

                myList.add(DataList.get(i));
            }
        }
        return myList;
    }
    List<GrievanceData> filteredListBasedOnPriority(String priority,List<GrievanceData> DataList){
        List<GrievanceData> myList = new ArrayList<>();
        for(int i=0;i<DataList.size();i++){
            if(DataList.get(i).getPriority().equalsIgnoreCase(priority)){
                Log.e(DataList.get(i).getPriority(),"calling==><==="+priority);

                myList.add(DataList.get(i));
            }
        }
        return myList;
    }
    List<GrievanceData> filteredListBasedOnWardNo(String wardno,List<GrievanceData> DataList){
        List<GrievanceData> myList = new ArrayList<>();
        for(int i=0;i<DataList.size();i++){
            if(DataList.get(i).getWardNo().equalsIgnoreCase(wardno)){
                Log.e(DataList.get(i).getWardNo(),"calling==><==="+wardno);

                myList.add(DataList.get(i));
            }
        }
        return myList;
    }
    List<GrievanceData> filteredListBasedOnStreetName(String street,List<GrievanceData> DataList){
        List<GrievanceData> myList = new ArrayList<>();
        for(int i=0;i<DataList.size();i++){
            if(DataList.get(i).getStreetName().equalsIgnoreCase(street)){
                Log.e(DataList.get(i).getStreetName(),"calling==><==="+street);

                myList.add(DataList.get(i));
            }
        }
        return myList;
    }
}
