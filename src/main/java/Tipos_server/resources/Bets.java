package Tipos_server.resources;

import Tipos_server.Credentials;
import Tipos_server.Ticket;
import Tipos_server.db.MySQL;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.awt.*;

@Path("/bets")
public class Bets {

    @POST
    @Path("/new")
    @Produces(MediaType.APPLICATION_JSON)
    public String newTicket(Ticket ticket){
        MySQL mySQL=new MySQL();
        boolean ret1 = mySQL.checkLogin(ticket.login);
        boolean ret2 = mySQL.checkToken(ticket.token);
        if(ret1 && ret2){
            System.out.println("Token and username are correct!");
        }
        else
        {
            System.out.println("Invalid username or token");
        }
        return "{}";
    }


}
