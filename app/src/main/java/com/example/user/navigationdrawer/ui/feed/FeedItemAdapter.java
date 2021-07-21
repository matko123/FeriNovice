package com.example.user.navigationdrawer.ui.feed;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.user.navigationdrawer.R;
import com.example.user.navigationdrawer.model.FeedItem;

import java.util.List;


public class FeedItemAdapter extends RecyclerView.Adapter<FeedItemAdapter.ViewHolder> {

    private final List<FeedItem> dataset;
    private final OnFeedItemClickListener onFeedItemClickListener;

    public FeedItemAdapter(final List<FeedItem> dataset,
                           OnFeedItemClickListener onFeedItemClickListener) {
        this.dataset = dataset;
        this.onFeedItemClickListener = onFeedItemClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent,
                                         int viewType) {
        // ustvaris novi item, ki ga napolnis z layout viewfeeditem, card layout
        // inflatamo cardview v nas recyclerview
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_feed_item, parent, false);
        return new ViewHolder(view);
    }



    //veze podatke oziroma novico na card
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindItem(dataset.get(position), onFeedItemClickListener);
    }

    // Vrne nam velikost nasega dataseta
    @Override
    public int getItemCount() {
        return dataset.size();
    }

    //Poslusalec, ko je nas feed item kliknjen

    public interface OnFeedItemClickListener {

        void onItemClicked(FeedItem item);
    }

     //Prikaz nasih cardov v recyclervuvu


    static class ViewHolder extends RecyclerView.ViewHolder {

        private final View rootView;
        private final TextView titleTextView;
        private final TextView descriptionTextView;

        ViewHolder(View view) {
            super(view);
            rootView = view;
            titleTextView = (TextView) view.findViewById(R.id.textView_title);
            descriptionTextView = (TextView) view.findViewById(R.id.textView_description);
        }


        //Veze Feed item podatke  na nas View
        //nastavlamo clicklistenere in podatke temu viewu
        void bindItem(final FeedItem feedItem, final OnFeedItemClickListener clickListener) {
            rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {
                    clickListener.onItemClicked(feedItem);
                }
            });
            titleTextView.setText(feedItem.getTitle());
            descriptionTextView.setText(feedItem.getDescription());
        }
    }
}
