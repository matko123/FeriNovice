package com.example.user.navigationdrawer.api;



import com.example.user.navigationdrawer.model.RssFeedResponse;

import retrofit.Callback;
import retrofit.http.GET;

/**
  Uporaba retrofit vmesnika
 */
public interface BbcRssApi {

   //S pomocjo GET anotacij glede na izbran tab v nasem main aktivitijon dobimo zeljeno rss stran.

   @GET("/obvestila/rss")
    void getObvestila(Callback<RssFeedResponse> feedItemsCallback);

    @GET("/dogodki/rss")
    void getDogodki(Callback<RssFeedResponse> feedItemsCallback);

    @GET("/novice/rss")
    void getNovice(Callback<RssFeedResponse> feedItemsCallback);

    @GET("/odeska/rss")
    void getOdeska(Callback<RssFeedResponse> feedItemsCallback);

}
