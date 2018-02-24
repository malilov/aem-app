package com.mlov.curuba.config;

import org.apache.felix.scr.annotations.*;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.osgi.service.component.ComponentContext;

import java.util.Dictionary;

@Component(metatype = true, immediate = true, label = "Curuba - Spotify API Configurations", description = "Define the configurations to use spotify API")
@Service(SpotifyApiConfig.class)
public class SpotifyApiConfig {

    @Property(label = "Client ID", description = "Client ID provided by registering the app on Spotify", value = "eab7631717634aeab92ce7bb50a3fd6f")
    public static final String CLIENT_ID = "client.id";

    @Property(label = "Client Key", description = "Client Secret Key provided by registering the app on Spotify", value = "e7b4817bb4a34bbbadc8b33a9f943918")
    public static final String CLIENT_SECRET_KEY = "client.key";

    @Property(label = "Authorization End Point",  description = "End Point to get authorization token ",value = "https://accounts.spotify.com/api/token")
    public static final String AUTHORIZATION_END_POINT = "authorization.end.point";

    @Property(label = "Albums by Artist End Point",  description = "End Point to get albums by artist",value = "https://api.spotify.com/v1/artists/%s/albums")
    public static final String ALBUMS_BY_ARTIST_END_POINT = "artist.albums.end.point";

    private String clientId;
    private String clientKey;
    private String authorizationEndPoint;
    private String artistAlbumsEndPoint;

    @SuppressWarnings("unchecked")
    @Activate
    @Modified
    public void onActivateOrSave(ComponentContext context) {
        Dictionary<String, Object> props = context.getProperties();
        setClientId(PropertiesUtil.toString(props.get(CLIENT_ID), CLIENT_ID));
        setClientKey(PropertiesUtil.toString(props.get(CLIENT_SECRET_KEY), CLIENT_SECRET_KEY));
        setAuthorizationEndPoint(PropertiesUtil.toString(props.get(AUTHORIZATION_END_POINT), AUTHORIZATION_END_POINT));
        setArtistAlbumsEndPoint(PropertiesUtil.toString(props.get(ALBUMS_BY_ARTIST_END_POINT), ALBUMS_BY_ARTIST_END_POINT));
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientKey() {
        return clientKey;
    }

    public void setClientKey(String clientKey) {
        this.clientKey = clientKey;
    }

    public String getAuthorizationEndPoint() {
        return authorizationEndPoint;
    }

    public void setAuthorizationEndPoint(String authorizationEndPoint) {
        this.authorizationEndPoint = authorizationEndPoint;
    }

    public String getArtistAlbumsEndPoint() {
        return artistAlbumsEndPoint;
    }

    public void setArtistAlbumsEndPoint(String artistAlbumsEndPoint) {
        this.artistAlbumsEndPoint = artistAlbumsEndPoint;
    }
}
