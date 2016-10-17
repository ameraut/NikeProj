package controller;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/user")
public class UserService {


    //First Step process
    @GET
    @Path("/{name}")
    public String sayHello(@PathParam("name") String name) {
        return  "Hi from Jersey REST Service: " + name;
   }
}
