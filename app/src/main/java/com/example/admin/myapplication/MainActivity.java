package com.example.admin.myapplication;

import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.admin.myapplication.Adapter.StreetAdapter;
import com.example.admin.myapplication.Pojo.FilterPojo;
import com.example.admin.myapplication.Pojo.GrievanceData;
import com.example.admin.myapplication.Pojo.ReceivedUpdatePojo;
import com.example.admin.myapplication.Pojo.Street_Pojo;
import com.example.admin.myapplication.comman.CommanDatas;
import com.example.admin.myapplication.contracts.IReceivedInterface;
import com.example.admin.myapplication.di.components.DaggerMainComponent;
import com.example.admin.myapplication.di.module.ReceivedModule;
import com.example.admin.myapplication.fragments.Completed;
import com.example.admin.myapplication.fragments.Dashboard;
import com.example.admin.myapplication.fragments.Pending;
import com.example.admin.myapplication.fragments.Received;
import com.example.admin.myapplication.listener.RecyclerClickListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    private TextView mTextMessage;

    @BindView(R.id.tv_apply)TextView tv_apply;
    @BindView(R.id.tv_reset)TextView tv_reset;
    @BindView(R.id.tv_low)TextView tv_low;
    @BindView(R.id.tv_medium)TextView tv_medium;
    @BindView(R.id.tv_high)TextView tv_high;
    @BindView(R.id.tv_wardno)TextView tv_wardno;
    @BindView(R.id.tv_street)TextView tv_street;

    BottomSheetBehavior sheetBehavior;
    @BindView(R.id.bottom_sheet)
    LinearLayout layoutBottomSheet;

    @BindView(R.id.img_close)
    ImageView bootSheetClose;

    String selectedTab;

    String priority="low";
    String wardno="wd-21";
    String streetname = "47fjhf";

    FilterPojo filteredDatas = new FilterPojo();
    Typeface avvaiyarfont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        avvaiyarfont = Typeface.createFromAsset(this.getAssets(), "fonts/avvaiyar.ttf");

        mTextMessage = (TextView) findViewById(R.id.message);
        ButterKnife.bind(this);
        getSupportActionBar().setTitle("DashBoard");

        //loading the default fragment
        loadFragment(new Dashboard());
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
//        DaggerMainComponent.builder().receivedModule(new ReceivedModule(this)).build().inject(this);
//        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
//        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
        initBottomSheet();
        onClickFunctions();
    }

    private void onClickFunctions() {
        tv_apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(selectedTab.equalsIgnoreCase("Received")){
                    if(CommanDatas.sReceivedList.size()>0){
//                        Toast.makeText(MainActivity.this, "has received", Toast.LENGTH_SHORT).show();
                        filteredDatas.setCatagory(selectedTab);
                        EventBus.getDefault().postSticky(filteredDatas);
                        sheetBehavior.setHideable(true);
                        sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                    }else{
                        Toast.makeText(MainActivity.this, "Please Wait..Loading..", Toast.LENGTH_SHORT).show();
                    }
                }else if(selectedTab.equalsIgnoreCase("Pending")){
                    if(CommanDatas.sPendingList.size()>0){
                        filteredDatas.setCatagory(selectedTab);
                        EventBus.getDefault().postSticky(filteredDatas);
                        sheetBehavior.setHideable(true);
                        sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                    }else{
                        Toast.makeText(MainActivity.this, "Please Wait..Loading..", Toast.LENGTH_SHORT).show();
                    }
                }else if(selectedTab.equalsIgnoreCase("Completed")){
                    if(CommanDatas.sCompletedList.size()>0){
                        filteredDatas.setCatagory(selectedTab);
                        EventBus.getDefault().postSticky(filteredDatas);
                        sheetBehavior.setHideable(true);
                        sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                    }else{
                        Toast.makeText(MainActivity.this, "Please Wait..Loading..", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
        tv_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FilterPojo filteredDatas1 = new FilterPojo();
                filteredDatas1.setCatagory(selectedTab);
                EventBus.getDefault().postSticky(filteredDatas1);

                filteredDatas.setPriority("Low");
                tv_low.setBackground(getResources().getDrawable(R.drawable.ed_border_box_linear));
                tv_low.setTextColor(getResources().getColor(R.color.white));
                tv_low.setTypeface(tv_low.getTypeface(),Typeface.BOLD);

                tv_medium.setBackground(getResources().getDrawable(R.drawable.edittext_e));
                tv_medium.setTypeface(tv_low.getTypeface(),Typeface.NORMAL);
                tv_medium.setTextColor(getResources().getColor(R.color.normalcolor));

                tv_high.setBackground(getResources().getDrawable(R.drawable.edittext_e));
                tv_high.setTypeface(tv_low.getTypeface(),Typeface.NORMAL);
                tv_high.setTextColor(getResources().getColor(R.color.normalcolor));

                tv_wardno.setText("Ward No");

                tv_street.setText("Street Name");
                tv_street.setTypeface(Typeface.DEFAULT,Typeface.NORMAL);


            }
        });
        tv_low.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filteredDatas.setPriority("Low");
                tv_low.setBackground(getResources().getDrawable(R.drawable.ed_border_box_linear));
                tv_low.setTextColor(getResources().getColor(R.color.white));
                tv_low.setTypeface(tv_low.getTypeface(),Typeface.BOLD);

                tv_medium.setBackground(getResources().getDrawable(R.drawable.edittext_e));
                tv_medium.setTypeface(tv_low.getTypeface(),Typeface.NORMAL);
                tv_medium.setTextColor(getResources().getColor(R.color.normalcolor));

                tv_high.setBackground(getResources().getDrawable(R.drawable.edittext_e));
                tv_high.setTypeface(tv_low.getTypeface(),Typeface.NORMAL);
                tv_high.setTextColor(getResources().getColor(R.color.normalcolor));

            }
        });
        tv_medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filteredDatas.setPriority("Medium");
                tv_medium.setBackground(getResources().getDrawable(R.drawable.ed_border_box_linear));
                tv_medium.setTextColor(getResources().getColor(R.color.white));
                tv_medium.setTypeface(tv_low.getTypeface(),Typeface.BOLD);

                tv_low.setBackground(getResources().getDrawable(R.drawable.edittext_e));
                tv_low.setTypeface(tv_low.getTypeface(),Typeface.NORMAL);
                tv_low.setTextColor(getResources().getColor(R.color.normalcolor));

                tv_high.setBackground(getResources().getDrawable(R.drawable.edittext_e));
                tv_high.setTypeface(tv_low.getTypeface(),Typeface.NORMAL);
                tv_high.setTextColor(getResources().getColor(R.color.normalcolor));
            }
        });
        tv_high.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                filteredDatas.setPriority("High");
                tv_high.setBackground(getResources().getDrawable(R.drawable.ed_border_box_linear));
                tv_high.setTextColor(getResources().getColor(R.color.white));
                tv_high.setTypeface(tv_low.getTypeface(),Typeface.BOLD);

                tv_low.setBackground(getResources().getDrawable(R.drawable.edittext_e));
                tv_low.setTypeface(tv_low.getTypeface(),Typeface.NORMAL);
                tv_low.setTextColor(getResources().getColor(R.color.normalcolor));

                tv_medium.setBackground(getResources().getDrawable(R.drawable.edittext_e));
                tv_medium.setTypeface(tv_low.getTypeface(),Typeface.NORMAL);
                tv_medium.setTextColor(getResources().getColor(R.color.normalcolor));
            }
        });
        tv_wardno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (getSupportActionBar().getTitle().toString()){
                    case "Received":
                        if(CommanDatas.sReceivedList.size()>0)
                            setSpinnerWardNo(CommanDatas.sReceivedList);
                        break;
                    case "Pending":
                        if(CommanDatas.sPendingList.size()>0)
                            setSpinnerWardNo(CommanDatas.sPendingList);
                        break;
                    case "Completed":
                        if(CommanDatas.sCompletedList.size()>0)
                            setSpinnerWardNo(CommanDatas.sCompletedList);
                        break;
                }

            }
        });
        tv_street.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Street_Pojo> streetList = new ArrayList<>();
                switch (getSupportActionBar().getTitle().toString()){
                    case "Received":
                        if(CommanDatas.sReceivedList.size()>0){
                            for(int i=0;i<CommanDatas.sReceivedList.size();i++){
                                if(CommanDatas.sReceivedList.get(i).getStreetName()!=null)
                                    streetList.add(new Street_Pojo(CommanDatas.sReceivedList.get(i).getStreetName()));
                            }
                        }
                        break;
                    case "Pending":
                        if(CommanDatas.sPendingList.size()>0){
                            for(int i=0;i<CommanDatas.sPendingList.size();i++){
                                if(CommanDatas.sPendingList.get(i).getStreetName()!=null)
                                    streetList.add(new Street_Pojo(CommanDatas.sPendingList.get(i).getStreetName()));
                            }
                        }
                        break;
                    case "Completed":
                        if(CommanDatas.sCompletedList.size()>0){
                            for(int i=0;i<CommanDatas.sCompletedList.size();i++){
                                if(CommanDatas.sCompletedList.get(i).getStreetName()!=null)
                                    streetList.add(new Street_Pojo(CommanDatas.sCompletedList.get(i).getStreetName()));
                            }
                        }
                        break;
                }
                showStreetValuesInAlert(streetList);
            }
        });

    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }


    private void initBottomSheet() {
        sheetBehavior = BottomSheetBehavior.from(layoutBottomSheet);
        sheetBehavior.setHideable(true);
        sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        sheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                switch (newState){
                    case BottomSheetBehavior.STATE_HIDDEN:
                        break;

                }
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {

            }
        });



        bootSheetClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sheetBehavior.setHideable(true);
                sheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case  R.id.action_filter:
                switch(getSupportActionBar().getTitle().toString()){
                    case "Received":
                        selectedTab = "Received";
                        if(sheetBehavior.getState()!=BottomSheetBehavior.STATE_EXPANDED){
                            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        }else{
                            sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                        }
                    break;
                    case "Pending":
                        selectedTab = "Pending";
                        if(sheetBehavior.getState()!=BottomSheetBehavior.STATE_EXPANDED){
                            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        }
                    break;
                    case "Completed":
                        selectedTab = "Completed";
                        if(sheetBehavior.getState()!=BottomSheetBehavior.STATE_EXPANDED){
                            sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        }
                    break;
                }
               /* if(getSupportActionBar().getTitle().equals("Received")){
                    selectedTab = "Received";
                    if(sheetBehavior.getState()!=BottomSheetBehavior.STATE_EXPANDED){
                        sheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                    }else{
                        sheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    }
                }*/


        }
        return super.onOptionsItemSelected(item);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.navigation_dashboard:
                getSupportActionBar().setTitle("DashBoard");
//                toolbar.setTitle("DashBoard");
                fragment = new Dashboard();
                break;

            case R.id.navigation_received:
                getSupportActionBar().setTitle("Received");
//                toolbar.setTitle("Received");
                fragment = new Received();
                break;

            case R.id.navigation_pending:
                getSupportActionBar().setTitle("Pending");
//                toolbar.setTitle("Pending");
                fragment = new Pending();
                break;

            case R.id.navigation_completed:
                getSupportActionBar().setTitle("Completed");
//                toolbar.setTitle("Completed");
                fragment = new Completed();
                break;
        }

        return loadFragment(fragment);
    }

    public void setSpinnerWardNo(List<GrievanceData> myList) {
        final ArrayList<String> arrayList=new ArrayList<>();
        for(int i=0;i<myList.size();i++){
            arrayList.add(myList.get(i).getWardNo());
        }



        //Removing repeating elements
        Set<String> hs = new HashSet<>();
        hs.addAll(arrayList);
        arrayList.clear();
        arrayList.addAll(hs);

        final SpinnerDialog spinnerDialog = new SpinnerDialog(this, arrayList, "Select Designation", R.style.DialogAnimations_SmileWindow, "CLOSE");



        spinnerDialog.showSpinerDialog();

        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String s, int i) {
                tv_wardno.setText(arrayList.get(i));
                filteredDatas.setWard(arrayList.get(i));

            }
        });
    }

    public void showStreetValuesInAlert(final ArrayList<Street_Pojo> data_list) {

        //Removing repeating elements
        Set<Street_Pojo> hs = new HashSet<>();
        hs.addAll(data_list);
        data_list.clear();
        data_list.addAll(hs);

        AlertDialog.Builder mBuilder = new AlertDialog.Builder(this);
        View v2 = getLayoutInflater().inflate(R.layout.recycler_alert, null);



        mBuilder.setPositiveButton("CLOSE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });



        RecyclerView recyclerView = (RecyclerView) v2.findViewById(R.id.recycler);
        if (recyclerView != null) {
            recyclerView.setHasFixedSize(true);
        }

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(v2.getContext());
        recyclerView.setLayoutManager(layoutManager);


       final StreetAdapter streetAdapter = new StreetAdapter(data_list);
        recyclerView.setAdapter(streetAdapter);
        streetAdapter.notifyDataSetChanged();

        mBuilder.setView(v2);
        final AlertDialog dialog = mBuilder.create();
        dialog.show();


        recyclerView.addOnItemTouchListener(new RecyclerClickListener(this, new RecyclerClickListener.OnItemTouchListener() {
            @Override
            public void OnItemCLikc(View view, int position) {
                filteredDatas.setStreet(streetAdapter.getList().get(position).getmStreetName());
                tv_street.setText(streetAdapter.getList().get(position).getmStreetName());
                tv_street.setTypeface(avvaiyarfont);
                dialog.dismiss();
            }
        }));
    }

}
