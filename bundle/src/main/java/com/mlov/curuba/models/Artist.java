package com.mlov.curuba.models;

import com.adobe.cq.sightly.WCMUsePojo;

import javax.jcr.Node;

public class Artist extends WCMUsePojo {

    private static final String NAME = "name";
    private static final String LAST_NAME = "lastName";
    private static final String ARTIST_ID = "spotifyId";

    private String name;
    private String lastName;
    private String artistId;

    public void activate() throws Exception {
        Node node = getResource().adaptTo(Node.class);
        setName(node.getProperty(NAME).getString());
        setLastName(node.getProperty(LAST_NAME).getString());
        setArtistId(node.getProperty(ARTIST_ID).getString());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }
}
