package com.darien.openweathertest.db.conf;

import android.database.sqlite.SQLiteDatabase;

import com.darien.openweathertest.WeatherApplication;
import com.darien.openweathertest.db.DaoMaster;
import com.darien.openweathertest.db.DaoSession;
import com.darien.openweathertest.db.ZipDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Darien
 * on 5/26/16.
 */
@Module(
        library = true,
        complete = false
)
public class DatabaseModule {

    @Singleton
    @Provides
    DaoMaster.DevOpenHelper provideDevOpenHelper() {
        return new DaoMaster.DevOpenHelper(WeatherApplication.CONTEXT, "weather-db", null);
    }

    @Singleton
    @Provides
    SQLiteDatabase provideDevOpenHelper(DaoMaster.DevOpenHelper helper) {
        return helper.getWritableDatabase();
    }

    @Singleton
    @Provides
    DaoMaster provideDaoMaster(SQLiteDatabase db) {
        return new DaoMaster(db);
    }

    @Singleton
    @Provides
    DaoSession provideDaoMaster(DaoMaster daoMaster) {
        return daoMaster.newSession();
    }

    @Singleton
    @Provides
    public ZipDao provideZipDao(DaoSession daoSession) {
        return daoSession.getZipDao();
    }

}
