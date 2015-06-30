package com.octo.android.robospice.sample.ormlite.network;

import android.app.Application;

import com.octo.android.robospice.SpringAndroidSpiceService;
import com.octo.android.robospice.persistence.CacheManager;
import com.octo.android.robospice.persistence.ormlite.RoboSpiceDatabaseHelper;
import com.octo.android.robospice.sample.ormlite.model.CurrenWeather;
import com.octo.android.robospice.sample.ormlite.model.Day;
import com.octo.android.robospice.sample.ormlite.model.Forecast;
import com.octo.android.robospice.sample.ormlite.model.Night;
import com.octo.android.robospice.sample.ormlite.model.Weather;
import com.octo.android.robospice.sample.ormlite.model.Wind;
import com.octo.android.robospice.sample.ormlite.persistence.InDatabaseObjectPersisterFactoryWithContentProvider;

import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.xml.SimpleXmlHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Simple service
 * @author sni
 */
public class SampleSpiceService extends SpringAndroidSpiceService {

    public static final String DATABASE_NAME = "sample_database";
    public static final int DATABASE_VERSION = 1;

    private static final int WEBSERVICES_TIMEOUT = 10000;

    @Override
    public CacheManager createCacheManager(Application application) {
        CacheManager cacheManager = new CacheManager();
        List<Class<?>> classCollection = new ArrayList<Class<?>>();

        // add persisted classes to class collection, if they have a contract annotation,
        // contentprovider observers will be notified
        // of any change.
        classCollection.add(Weather.class);
        classCollection.add(CurrenWeather.class);
        classCollection.add(Day.class);
        classCollection.add(Forecast.class);
        classCollection.add(Night.class);
        classCollection.add(Wind.class);

        // init
        RoboSpiceDatabaseHelper databaseHelper = new RoboSpiceDatabaseHelper(application, DATABASE_NAME, DATABASE_VERSION);
        InDatabaseObjectPersisterFactoryWithContentProvider inDatabaseObjectPersisterFactoryWithContentProvider = new InDatabaseObjectPersisterFactoryWithContentProvider(application, databaseHelper,
                classCollection);
        cacheManager.addPersister(inDatabaseObjectPersisterFactoryWithContentProvider);
        return cacheManager;
    }

    @Override
    public RestTemplate createRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        // set timeout for requests

        HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
        httpRequestFactory.setReadTimeout(WEBSERVICES_TIMEOUT);
        httpRequestFactory.setConnectTimeout(WEBSERVICES_TIMEOUT);
        restTemplate.setRequestFactory(httpRequestFactory);

        // web services support xml responses
        SimpleXmlHttpMessageConverter simpleXmlHttpMessageConverter = new SimpleXmlHttpMessageConverter();
        FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter();
        final List<HttpMessageConverter<?>> listHttpMessageConverters = restTemplate.getMessageConverters();

        listHttpMessageConverters.add(simpleXmlHttpMessageConverter);
        listHttpMessageConverters.add(formHttpMessageConverter);
        listHttpMessageConverters.add(stringHttpMessageConverter);
        restTemplate.setMessageConverters(listHttpMessageConverters);
        return restTemplate;
    }

}
