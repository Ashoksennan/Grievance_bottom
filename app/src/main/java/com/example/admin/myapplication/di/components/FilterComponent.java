package com.example.admin.myapplication.di.components;

import com.example.admin.myapplication.di.module.PendingModule;
import com.example.admin.myapplication.di.module.ReceivedModule;
import com.example.admin.myapplication.presenter.FilterClass;

import javax.inject.Singleton;

import dagger.Component;

@Component(modules = {PendingModule.class,ReceivedModule.class})
@Singleton
public interface FilterComponent {
    void inject(FilterClass filterClass);
}
