package com.example.admin.myapplication.di.components;

import com.example.admin.myapplication.di.module.DashboardModule;
import com.example.admin.myapplication.di.module.NetworkModule;
import com.example.admin.myapplication.fragments.Dashboard;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {DashboardModule.class,NetworkModule.class})
@Singleton
public interface DashboardComponent {
    void inject(Dashboard dashboard);
}
