package com.mlov.curuba.models;

public class Artist  {

    private static final String NAME = "name";
    private static final String LAST_NAME = "lastName";
    private static final String ARTIST_ID = "spotifyId";
    private static final String ARTISTS_SET = "setArtistFields";


    private String name;
    private String lastName;
    private String artistId;
    private String artistsJson;



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
