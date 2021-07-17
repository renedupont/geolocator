package org.renedupont.geolocator;

import com.maxmind.geoip2.exception.GeoIp2Exception;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.NoSuchElementException;

@Path("/geolocation")
public class GeolocatorResource {

    @Inject
    GeolocatorService geolocatorService;

    @GET
    public Response city(@QueryParam("host") String host) {
        try {
            if (host == null) {
                throw new UnknownHostException("Query param 'host' is missing!");
            }
            return Response.ok(geolocatorService.getCityResponse(host)).build();
        } catch (UnknownHostException | NoSuchElementException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (IOException | GeoIp2Exception e) {
            return Response.serverError().entity(e.getMessage()).build();
        }
    }

}