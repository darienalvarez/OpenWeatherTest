package com.darien.openweathertest;

import android.app.Application;
import android.content.Context;

import java.util.List;

import dagger.ObjectGraph;

/**
 * Created by Darien
 * on 5/26/16.
 */
public class WeatherApplication extends Application {

    private ObjectGraph objectGraph;
    public static Context CONTEXT;

    @Override
    public void onCreate() {
        super.onCreate();

        WeatherApplication.CONTEXT = getApplicationContext();
        initializeDependencyInjector();
    }

    /**
     * Inject every dependency declared in the object with the @Inject annotation if the dependency
     * has been already declared in a module and already initialized by Dagger.
     *
     * @param object to inject.
     */
    public void inject(Object object) {
        objectGraph.inject(object);
    }

    /**
     * Extend the dependency container graph will new dependencies provided by the modules passed as
     * arguments.
     *
     * @param modules used to populate the dependency container.
     */
    public ObjectGraph plus(List<Object> modules) {
        if (modules == null) {
            throw new IllegalArgumentException(
                    "You can't plus a null module, review your getModules() implementation");
        }
        return objectGraph.plus(modules.toArray());
    }

    private void initializeDependencyInjector() {
        objectGraph = ObjectGraph.create(new WeatherApplicationModule(this));
        objectGraph.inject(this);
        objectGraph.injectStatics();
    }
}
