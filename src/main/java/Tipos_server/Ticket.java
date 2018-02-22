package Tipos_server;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ticket {
    @JsonProperty("bet1")
    public String bet1;
    @JsonProperty("bet2")
    public String bet2;
    @JsonProperty("bet3")
    public String bet3;
    @JsonProperty("bet4")
    public String bet4;
    @JsonProperty("bet5")
    public String bet5;
    @JsonProperty("login")
    public String login;
    @JsonProperty("token")
    public String token;

}
