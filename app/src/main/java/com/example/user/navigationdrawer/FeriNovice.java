package com.example.user.navigationdrawer;

import android.app.Activity;
import android.app.Application;
import android.util.Log;

import com.example.user.navigationdrawer.api.BbcRssApi;

import retrofit.ErrorHandler;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.converter.SimpleXMLConverter;

/**
 * Created by Matevz on 20. 12. 2016.
 */
public class FeriNovice extends Application {
    // instanca nasega api servisa.
    private BbcRssApi bbcRssApi;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeFeedApi();
    }
    //Vrne api za uporabo v drugih komponentah

    public BbcRssApi getBccRssApi() {
        return bbcRssApi;
    }

    //Inicicalizacija implementiranega retrofit API servisa

    private void initializeFeedApi() {
        RestAdapter feedRestAdapter = new RestAdapter.Builder()
                .setConverter(new SimpleXMLConverter())
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setErrorHandler(new ErrorHandler() {
                    @Override
                    public Throwable handleError(final RetrofitError cause) {
                        Log.e("BbcRestApi", "Napaka nimam dostopa do omrezja!: ");
                        return cause;
                    }
                })
                .setEndpoint("https://feri.um.si/") //nastavi url za pvoezavo do strani

                .build();

        bbcRssApi = feedRestAdapter.create(BbcRssApi.class);
    }
}
