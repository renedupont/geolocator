package org.renedupont.geolocator;

import com.maxmind.db.CHMCache;
import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import com.maxmind.geoip2.model.CityResponse;
import io.quarkus.runtime.Startup;
import org.eclipse.microprofile.config.ConfigProvider;

import javax.enterprise.context.ApplicationScoped;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

/**
 * Service that leverages the MaxMind GeoIP2 Java API (https://github.com/maxmind/GeoIP2-java)
 */
@Startup
@ApplicationScoped
public class GeolocatorService {

    private DatabaseReader reader;

    public GeolocatorService() throws IOException {
        var dbPath = ConfigProvider.getConfig().getValue("GEOLITE2_DATABASE_PATH", String.class);
        var database = new File(dbPath);
        reader = new DatabaseReader.Builder(database).withCache(new CHMCache()).build();
    }

    public CityResponse getCityResponse(String host) throws IOException, GeoIp2Exception {
        return reader.city(InetAddress.getByName(host));
    }

}
