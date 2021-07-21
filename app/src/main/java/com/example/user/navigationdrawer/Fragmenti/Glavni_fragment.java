package com.example.user.navigationdrawer.Fragmenti;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.navigationdrawer.FeriNovice;
import com.example.user.navigationdrawer.R;
import com.example.user.navigationdrawer.api.BbcRssApi;
import com.example.user.navigationdrawer.model.FeedItem;
import com.example.user.navigationdrawer.model.RssFeedResponse;
import com.example.user.navigationdrawer.ui.article.ArtikelAktiviti;
import com.example.user.navigationdrawer.ui.feed.FeedItemAdapter;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by Matevz on 20. 12. 2016.
 */
public class Glavni_fragment extends Fragment{

    private final FeedItemAdapter.OnFeedItemClickListener onFeedItemClickListener // poslusalec za klik na naso kartico cardlayouta
            = new FeedItemAdapter.OnFeedItemClickListener() {
        @Override
        public void onItemClicked(final FeedItem item) {
            ArtikelAktiviti.launch(getActivity().getApplicationContext(), item.getLink()); // v nas artikel activity vnesemo link kliknjenega carda
        }
    };

    View myView;
    private RecyclerView feedRecyclerView;
    String feed;
    ProgressDialog progress;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.activity_rss_feed, container, false);

        feedRecyclerView = (RecyclerView) myView.findViewById(R.id.recyclerView_rssFeed); // poisce nas recycler view katerega najdemo s pomocjo idja
        feedRecyclerView.setHasFixedSize(true);
        feedRecyclerView.setLayoutManager(new LinearLayoutManager(myView.getContext()));


        feed = getArguments().getString("feed");//iz bundla dobimo ven vrednost, ki smo jo nastavili. glej main activity
        loadFeed();
        return myView; // returna nam nas layout, kjer se nahaja recyclerView
    }
    private BbcRssApi getBccRssApi() {
        return ((FeriNovice) getActivity().getApplication()).getBccRssApi();
    }

     Callback callback = new Callback<RssFeedResponse>() {
        @Override
        public void success(final RssFeedResponse rssFeedResponse, final Response response) {
            // javi nam da smo uspesno pridobili novice s pomojcjo snacbara".
            Snackbar.make(feedRecyclerView,
                    String.format("Dobili ste %d novic/e!", rssFeedResponse.getChannel()
                            .getFeedItems()
                            .size()),
                    Snackbar.LENGTH_SHORT)
                    .show();

            //ustvarimo adapter, 1. paramter je seznam itemov, drugi parameter pa action listener
            FeedItemAdapter adapter = new FeedItemAdapter(rssFeedResponse.getChannel().getFeedItems(),
                    onFeedItemClickListener);
            //feedrecycleru nastavimo adapter
            feedRecyclerView.setAdapter(adapter);
            // To je potrebno uvesti zaradi progressdialoga, saj lahko v nasprotnem primeru, ta povzroci, da program crasha.
            if(progress != null)
                progress.dismiss();
        }

        @Override
        public void failure(final RetrofitError error) {
            // uporabimo snackbar za izpis napake
            final Snackbar snackbar = Snackbar.make(feedRecyclerView, "Error! " + error, Snackbar.LENGTH_INDEFINITE);
            snackbar.setAction(getString(R.string.msg_retry),
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            loadFeed();
                            snackbar.dismiss();
                        }
                    });
            snackbar.show();

            // v primeru napaka se poklice zgornja funckija
            Log.e("napaka", "Error when retrieving feed", error);
        }
    };

    private void loadFeed() {
        //uporabljamo retrofit knjiznico, da fetchamo iteme feeda

        //reponse se prinese do glavnega andorid threada. To je potrebno za modifikacijo nasega viewa

        // implemenitali smo tudi preprost progress deialog, v primeru, ko cakamo da se zavihek z novicami nalaga
        //https://developer.android.com/reference/android/app/ProgressDialog.html

        progress = ProgressDialog.show(getActivity(), "Nalagam",
                "Prosim počakajte", true);
        // switch, ki zove get v nasem retrofit vmesniku, kateri poklice rss stran glede na izbiro v navigation draweru main aktivitija
        switch(feed){
            case "obvestila":
                getBccRssApi().getObvestila(callback);
                break;
            case "novice":
                getBccRssApi().getNovice(callback);
                break;
            case "dogodki":
                getBccRssApi().getDogodki(callback);
                break;
            case "odeska":
                getBccRssApi().getOdeska(callback);
                break;

        }

    }
}
