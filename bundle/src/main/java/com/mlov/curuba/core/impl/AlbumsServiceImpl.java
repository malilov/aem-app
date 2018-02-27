package com.mlov.curuba.core.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mlov.curuba.exceptions.UnauthorizedException;
import com.mlov.curuba.http.CallManagerService;
import com.mlov.curuba.core.AlbumsService;
import com.mlov.curuba.http.impl.CallManagerServiceImpl;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;

@Component(metatype = true, immediate = true, label = "Albums Operations", description = "Albums Operations")
@Service(AlbumsService.class)
public class AlbumsServiceImpl implements AlbumsService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Reference
    private CallManagerService callManagerService;

    public String getArtistAlbums(String artistId) {
        String albums = null;
        try {
            albums = callManagerService.getJson(artistId, CallManagerServiceImpl.CallType.ARTIST_ALBUMS);
        } catch (UnauthorizedException e) {
            logger.error("Was not possible get API authorization",e);
        } catch (IOException e) {
            logger.error("Was not possible to perform operation by networking issues",e);
        }
        return albums;
    }

}
