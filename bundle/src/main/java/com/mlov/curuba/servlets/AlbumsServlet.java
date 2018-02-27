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

    private static final String ARTIST_ID = "artistId";


    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        String result = "Processed";
        PrintWriter writer = response.getWriter();

        String artistId = request.getParameter(ARTIST_ID);
        result = result + albumsService.getArtistAlbums(artistId);
        writer.write(result);
        writer.flush();
        writer.close();
    }
}
