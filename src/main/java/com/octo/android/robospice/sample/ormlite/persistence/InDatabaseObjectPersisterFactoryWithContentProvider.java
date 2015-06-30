package com.octo.android.robospice.sample.ormlite.persistence;

import android.app.Application;

import com.octo.android.robospice.persistence.ormlite.InDatabaseObjectPersisterFactory;
import com.octo.android.robospice.persistence.ormlite.RoboSpiceDatabaseHelper;

import java.util.List;

/**
 * Created by Simon on 2015/06/29.
 */
public class InDatabaseObjectPersisterFactoryWithContentProvider extends InDatabaseObjectPersisterFactory {

    public InDatabaseObjectPersisterFactoryWithContentProvider(Application application, RoboSpiceDatabaseHelper databaseHelper, List<Class<?>> listHandledClasses) {
        super(application, databaseHelper, ContractHelper.getContractClasses(listHandledClasses));
    }

}