package com.example.user.navigationdrawer.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;


@Root(strict = false)
public class RssFeedResponse {

    @Element
    private Channel channel;

    public Channel getChannel() {
        return channel;
    }

    @Root(strict = false)
    public static class Channel {

        @ElementList(inline = true)
        private List<FeedItem> feedItems;
        //tukaj imammo list itemov
        public List<FeedItem> getFeedItems() {
            return feedItems;
        }
    }
}
