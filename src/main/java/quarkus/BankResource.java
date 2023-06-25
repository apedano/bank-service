package quarkus;


import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.ConfigProvider;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.util.Optional;

@Path("/bank")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BankResource {

    //CDI injection based configuration retrieval. The @Inject annotation is not required to semplify the code
    //Single property extraction
    @ConfigProperty(name = "bank.name", defaultValue = "Default bank name from @ConfigProperty")
    String name;

    //In case of missing configuration Optional.empty() will be injected
    @ConfigProperty(name = "app.mobilebanking")
    Optional<Boolean> mobileBanking;


    @GET
    @Path("/name")
    @Produces(MediaType.TEXT_PLAIN)
    public String getName() {
        return name;
    }

    @GET
    @Path("/name-programmatically")
    @Produces(MediaType.TEXT_PLAIN)
    public String getNameProgrammatically() {
        //Programmatic access to config
        Config config = ConfigProvider.getConfig();
        return config.getValue("bank.name", String.class);
    }


    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/mobilebanking")
    public Boolean getMobileBanking() {
        return mobileBanking.orElse(false);
    }





}
