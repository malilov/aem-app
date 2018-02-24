package com.mlov.curuba.http.impl;

import com.mlov.curuba.http.CallManagerService;
import com.mlov.curuba.http.SpotifyOperationsRenombrarme;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;

@Component(metatype = true, immediate = true, label = "Spotify Operations", description = "Spotify Operations")
@Service(SpotifyOperationsRenombrarme.class)
public class SpotifyOperationsRenombrarmeImpl implements SpotifyOperationsRenombrarme {

    @Reference
    private CallManagerService callManagerService;

    public String getArtistAlbums(String artistId) {

        return null;
    }
}
