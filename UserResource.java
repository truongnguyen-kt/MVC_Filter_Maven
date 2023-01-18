/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package resources.user;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author 12345
 */
@Path("user")
public class UserResource extends ResourceConfig{
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public User getData(User us){
        return us;
    }
    
    @POST
    public User postData(@RequestBody User user){
        return user;
    }
}
