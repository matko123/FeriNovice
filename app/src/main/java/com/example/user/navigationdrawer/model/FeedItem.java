package com.example.user.navigationdrawer.model;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

@Root(strict = false, name = "item")
public class FeedItem {
    //podatki o nasih itemih, ki jih bomo fetchali
    @Element
    private String title;

    @Element
    private String description;

    @Element
    private String link;

    @Element
    private String pubDate;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }
}
