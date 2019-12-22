package net.hassannazar;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 *
 * @author hassannazar.net
 */
@ApplicationPath("v1")
@OpenAPIDefinition(
        info = @Info(
                title = "Fruit Service",
                version = "v1",
                contact = @Contact(
                        name = "Hassan Nazar",
                        email = "hassan.nazar94@gmail.com",
                        url = "https://www.hassannazar.net"
                )
        )
)
public class JaxRSApplication extends Application {

}
