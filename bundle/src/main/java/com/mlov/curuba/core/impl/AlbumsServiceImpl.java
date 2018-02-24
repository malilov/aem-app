package com.mlov.curuba.core.impl;

import com.mlov.curuba.http.CallManagerService;
import com.mlov.curuba.core.AlbumsService;
import com.mlov.curuba.http.impl.CallManagerServiceImpl;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;

import java.io.IOException;

@Component(metatype = true, immediate = true, label = "Albums Operations", description = "Albums Operations")
@Service(AlbumsService.class)
public class AlbumsServiceImpl implements AlbumsService {

    @Reference
    private CallManagerService callManagerService;

    public String getArtistAlbums(String artistId) {
        try {
            callManagerService.getJson(artistId, CallManagerServiceImpl.CallType.ARTIST_ALBUMS);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
