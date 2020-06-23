package com.test.techtest;

import java.util.List;
import java.util.Objects;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Path("/spring-resource")
@Service
public class SpringRequestResourceClass {

  @Autowired GoodbyeService goodbyeService;

  @Autowired List<GreetService> greetServiceList;

  @Path("/goodbye")
  @GET
  @Consumes
  public String sayGoodbye(@QueryParam("goodbye") String goodBye) {
    return goodbyeService.sayGoodbye(goodBye);
  }

  private GreetService getService(Class<?> clazz) {
    for (GreetService greetService : greetServiceList) {
      if (clazz.isAssignableFrom(greetService.getClass())) {
        return greetService;
      }
    }
    return null;
  }

  @Path("/english-greet")
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String englishGreet(@QueryParam("greet") String greet) {
    return Objects.requireNonNull(getService(EnglishGreetService.class)).sayHello(greet);
  }

  @Path("/chinese-greet")
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public String chineseGreet(@QueryParam("greet") String greet) {
    return Objects.requireNonNull(getService(ChineseGreetService.class)).sayHello(greet);
  }
}
