package com.example.admin.myapplication.di.components;

import com.example.admin.myapplication.di.module.FilterModule;
import com.example.admin.myapplication.di.module.NetworkModule;
import com.example.admin.myapplication.di.module.PendingModule;
import com.example.admin.myapplication.fragments.Pending;

import javax.inject.Singleton;

import dagger.Component;
@Singleton
@Component(modules = {PendingModule.class,NetworkModule.class,FilterModule.class})
public interface PendingComponent {
    void injectPendingMethod(Pending pending);
}
