package com.darien.openweathertest.controllers;

import com.darien.openweathertest.db.Zip;
import com.darien.openweathertest.db.ZipDao;
import com.darien.openweathertest.pojo.Forecast;
import com.darien.openweathertest.services.OpenWeatherMapService;
import com.darien.openweathertest.view.OnFragmentInteractionListener;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import de.greenrobot.dao.query.QueryBuilder;
import retrofit2.Call;
import retrofit2.http.Query;

/**
 * Created by Darien
 * on 5/26/16.
 */
public class WeatherController {

    @Inject
    OpenWeatherMapService service;

    @Inject
    ZipDao zipDao;

    @Inject
    public WeatherController() { }

    public Call<Forecast> getForecast(String zipCode) {
        return service.getForecastByZip(String.format("%s,us", zipCode));
    }

    public void addZipCodeToDatabase(Zip entity) {
        // Remove current mark if another current is selected
        if (entity.getCurrent()) {
            List<Zip> currents = zipDao.queryBuilder().where(ZipDao.Properties.Current.eq(true)).list();
            for (Zip current: currents) {
                current.setCurrent(false);
                zipDao.update(current);
            }
        }

        // Do not duplicate zip codes
        List<Zip> exists = zipDao.queryBuilder().where(ZipDao.Properties.ZipCode.eq(entity.getZipCode())).list();
        if (exists != null && exists.size() > 0) {
            entity.setId(exists.get(0).getId());
        }
        zipDao.insertOrReplace(entity);
    }

    public List<Zip> getZipCodeOrdered() {
        // if no current mark exists then return the most recent
        return zipDao.queryBuilder().orderDesc(ZipDao.Properties.Current).orderDesc(ZipDao.Properties.Date).list();
    }

    public Zip getDefaultZipCode() {
        // if no current mark exists then return the most recent
        return zipDao.queryBuilder().orderDesc(ZipDao.Properties.Current).orderDesc(ZipDao.Properties.Date).limit(1).unique();
    }
}
