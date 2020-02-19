package gui_swing.ui.model;




import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public  class ApiConnector {
     Client client = ClientBuilder.newClient();;
     WebTarget  webTarget;
     Invocation.Builder invocationBuilder;

    List<Term> responseList ;

    String apiURI = "http://localhost:8080/api/";
    public ApiConnector() {
    }

    public void getAllTerm() {


        webTarget = client.target(apiURI).path("term");
       /* System.out.println(

                webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken()).get(String.class)
        );*/

       invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
       responseList = invocationBuilder.get(new GenericType<List<Term>>(){});

    }
    public List<Author> getAuthorsOfTerm(Long id){

        webTarget = client.target(apiURI).path("term/"+id+"/termHistory/actual");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        Long termHistoryId = invocationBuilder.get(new GenericType<Long>(){});

        webTarget = client.target(apiURI).path("term/termhistory/"+termHistoryId+"/authors");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return  invocationBuilder.get(new GenericType<List<Author>>(){});
    }


    public List<Term> getResponseList() {
        return responseList;
    }

    public void setResponseList(List<Term> responseList) {
        this.responseList = responseList;
    }

    public ArrayList<String> getAllTags() {
        webTarget = client.target(apiURI).path("tags/");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return  invocationBuilder.get(new GenericType<ArrayList<String>>(){});
    }
}
