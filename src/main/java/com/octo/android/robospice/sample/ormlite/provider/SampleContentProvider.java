package com.octo.android.robospice.sample.ormlite.provider;

import com.octo.android.robospice.sample.ormlite.model.CurrenWeather;
import com.octo.android.robospice.sample.ormlite.network.SampleSpiceService;
import com.octo.android.robospice.sample.ormlite.persistence.RoboSpiceContentProvider;

import java.util.Arrays;
import java.util.List;

public class SampleContentProvider extends RoboSpiceContentProvider {

    @Override
    public List<Class<?>> getExposedClasses() {
        return Arrays.<Class<?>>asList(CurrenWeather.class);
    }

    @Override
    public String getDatabaseName() {
        return SampleSpiceService.DATABASE_NAME;
    }

    @Override
    public int getDatabaseVersion() {
        return SampleSpiceService.DATABASE_VERSION;
    }
}
