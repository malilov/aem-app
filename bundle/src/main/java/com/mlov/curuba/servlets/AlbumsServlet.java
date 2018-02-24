package com.mlov.curuba.servlets;

import com.mlov.curuba.core.AlbumsService;
import com.mlov.curuba.http.CallManagerService;
import com.mlov.curuba.http.impl.CallManagerServiceImpl;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.sling.SlingServlet;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

@Service({Servlet.class})
@SlingServlet(label = "Spotify servlet ", description = "Spotify servlet ",
        paths = {"/bin/spotify/albums"}, methods = "GET", metatype = true)
public class AlbumsServlet extends SlingAllMethodsServlet {

    @Reference
    private AlbumsService albumsService;


    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        String result = "Processed";
        PrintWriter writer = response.getWriter();
        albumsService.getArtistAlbums("4dpARuHxo51G3z768sgnrY");
      //  callManagerService.getJson("a", CallManagerServiceImpl.CallType.ARTIST_ALBUMS);
       /* HttpOperationsImpl httpOperationsImpl = new HttpOperationsImpl();
        requestManager.buildRequest();
        SpotifyRequest spotifyRequest = requestManager.getSpotifyRequest();
        result = httpOperationsImpl.post(spotifyRequest.getUri(), spotifyRequest.getHeaders()
                .toArray(new Header[spotifyRequest.getHeaders().size()]), spotifyRequest.getBody());*/
      //  callManagerService.getJson("", CallManagerServiceImpl.CallType.ARTIST_ALBUMS);
        result = result + "VALOR:" ;
        writer.write(result);
        writer.flush();
        writer.close();
    }
}
