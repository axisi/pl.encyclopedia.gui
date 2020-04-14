package gui_swing.ui.model;




import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import gui_swing.ui.model.filters.ListsF;
import org.json.JSONObject;

import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

public  class ApiConnector {
     Client client = ClientBuilder.newClient();;
     WebTarget  webTarget;
     Invocation.Builder invocationBuilder;
     Boolean isEmpty = false;
     private Long selectedAuthorId;

    public Long getSelectedAuthorId() {
        return selectedAuthorId;
    }

    public void setSelectedAuthorId(Long selectedAuthorId) {
        this.selectedAuthorId = selectedAuthorId;
    }

    public Boolean getEmpty() {
        return isEmpty;
    }

    public void setEmpty(Boolean empty) {
        isEmpty = empty;
    }

    List<Term> responseList ;

    String apiURI = ConfigManager.getApiURI()+"/api/";
    public ApiConnector() {
    }

    public List<Term> getAllTerm() {


            webTarget = client.target(apiURI).path("term");
       /* System.out.println(

                webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken()).get(String.class)
        );*/

            invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
            responseList = invocationBuilder.get(new GenericType<List<Term>>() {
            });
            return responseList;
        }


    /*public List<Author> getAuthorsOfTerm(Long id){

        webTarget = client.target(apiURI).path("term/"+id+"/termHistory/actual");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        Long termHistoryId = invocationBuilder.get(new GenericType<Long>(){});

        webTarget = client.target(apiURI).path("term/termhistory/"+termHistoryId+"/authors");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return  invocationBuilder.get(new GenericType<List<Author>>(){});
    }*/


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



    public void getTermsByFilter(ListsF listsF) throws JsonProcessingException {
        webTarget = client.target(apiURI).path("term/filtered");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        //JSONObject obj = new JSONObject(listsF.getCategoryF());
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(listsF);
        //System.out.println(json);

        //System.out.println(obj.toString());
        Response response =  invocationBuilder.post(Entity.json(json));
        responseList = response.readEntity(new GenericType<List<Term>>(){});
        if(responseList.get(0).getId()==-1L)
            this.setEmpty(true);
        else
            this.setEmpty(false);
        //System.out.println(response.getEntity());

        //System.out.println(webTarget.getConfiguration());

    }

    public ArrayList<String> getAllCategoriesString() {

        webTarget = client.target(apiURI).path("categories");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return  invocationBuilder.get(new GenericType<ArrayList<String>>(){});
    }

    public ArrayList<String> getAllSubcategoriesString() {
        webTarget = client.target(apiURI).path("subcategories");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return  invocationBuilder.get(new GenericType<ArrayList<String>>(){});
    }

    public ArrayList<String> getAllStatusesString() {
        webTarget = client.target(apiURI).path("statuses");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return  invocationBuilder.get(new GenericType<ArrayList<String>>(){});
    }

    public ArrayList<String> getStatusesOfSubcategory(String subcategory) {

        Long subcategoryId = getSubcategoryId(subcategory);

        webTarget = client.target(apiURI).path("statuses/subcategory/"+subcategoryId);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return  invocationBuilder.get(new GenericType<ArrayList<String>>(){});
    }

    public ArrayList<String> getAllAuthors() {
        webTarget = client.target(apiURI).path("authors/string");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return  invocationBuilder.get(new GenericType<ArrayList<String>>(){});
    }

    public String addNewTerm(Term term) throws JsonProcessingException {
        webTarget = client.target(apiURI).path("term");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(term);

        //System.out.println(json);

        //System.out.println(obj.toString());
        Response response =  invocationBuilder.post(Entity.json(json));
        Term response1=response.readEntity(new GenericType<Term>(){});
       //System.out.println(response1.getTags().get(0).getName());
        return  "Pomyślnie założono hasło o id: '"+response1.getId() +"' oraz tytule: '" + response1.getTitle()+"'.";
    }

    public String addNewAuthor(Author author) {
        webTarget = client.target(apiURI).path("author");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        //ObjectMapper objectMapper = new ObjectMapper();
        //String json = objectMapper.writeValueAsString(author);
        Response response =  invocationBuilder.post(Entity.entity(author,MediaType.APPLICATION_JSON));
        Author author1 =response.readEntity(new GenericType<Author>(){});
        Float floatValue = author1.getId();
        selectedAuthorId = floatValue.longValue();
        return "Pomyślnie założono autora o id '" +(int)author1.getId() + "' - Imię:'"+author1.getName()+"', Nazwisko: '"+ author1.getSurname()+"'" ;

    }

    public ArrayList<Author> getAllAuthorsObject() {
        webTarget = client.target(apiURI).path("authors");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return  invocationBuilder.get(new GenericType<ArrayList<Author>>(){});
    }

    public Integer howManyTerms(int intValue) {
        webTarget = client.target(apiURI).path("authors/count/"+intValue);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
       return  invocationBuilder.get(new GenericType<Integer>(){});
    }


    public Author getAuthor(Integer valueAt) {
        webTarget = client.target(apiURI).path("author/"+valueAt);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return  invocationBuilder.get(new GenericType<Author>(){});
    }


    public String deleteAuthor(int id) {
        webTarget = client.target(apiURI).path("author/"+id);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        Integer response = invocationBuilder.delete(new GenericType<Integer>(){});
        if(response==-1)
            return "Autor o podanym numerze nie isniteje w bazie danych.";
        else
            return "Autor o ID: "+response+ " został pomyślnie usunięty";
    }

    public String updateAuthor(Author author) {
        webTarget = client.target(apiURI).path("author");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        Response response =  invocationBuilder.put(Entity.entity(author,MediaType.APPLICATION_JSON));
        Author author1 =response.readEntity(new GenericType<Author>(){});
        Float floatValue = author1.getId();
        selectedAuthorId = floatValue.longValue();
        return "Pomyślnie zaktualizowano autora o id '" +(int)author1.getId() + "' - Imię:'"+author1.getName()+"', Nazwisko: '"+ author1.getSurname()+"'" ;

    }

    public Term getTerm(int id) {
        webTarget = client.target(apiURI).path("term/"+id);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return  invocationBuilder.get(new GenericType<Term>(){});
    }

    public String getTermCategory(Integer integerId) {
        webTarget = client.target(apiURI).path("term/"+integerId+"/category/string");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return  invocationBuilder.get(new GenericType<String>(){});
    }
    public String getTermSubcategory(Integer integerId) {
        webTarget = client.target(apiURI).path("term/"+integerId+"/subcategory/string");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return  invocationBuilder.get(new GenericType<String>(){});
    }

    public boolean isSigned(Integer integerId) {
        webTarget = client.target(apiURI).path("term/"+integerId+"/signed");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return  invocationBuilder.get(new GenericType<Boolean>(){});
    }

    public boolean isSubcategoryExist(String param) {
        webTarget = client.target(apiURI).path("subcategory/"+param+"/exist");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return  invocationBuilder.get(new GenericType<Boolean>(){});
    }

    public String createSubcategory(String text) {
        webTarget = client.target(apiURI).path("subcategory/");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());

        Response response =  invocationBuilder.post( Entity.text(text));

        return "Pomyślnie założono nową podkategorie '"+text+"' o ID: '"+response.readEntity(new GenericType<Integer>(){})+"'.";

    }

    public Boolean isSubcategoryRemovable(String toString) {
        webTarget = client.target(apiURI).path("subcategory/"+toString+"/removable");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return  invocationBuilder.get(new GenericType<Boolean>(){});
    }

    public Long getSubcategoryId(String subcategory) {
        webTarget = client.target(apiURI).path("subcategory/"+subcategory);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<Long>(){});
    }

    public String deleteSubcategory(Long subcategoryId) {
        webTarget = client.target(apiURI).path("subcategory/"+subcategoryId);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        Integer response = invocationBuilder.delete(new GenericType<Integer>(){});
        return response.toString();
    }

    public String updateSubcategoryStatuses(String toString, ArrayList<String> statuses) {
        webTarget = client.target(apiURI).path("subcategory/"+ toString +"/statuses");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        Response response =  invocationBuilder.put(Entity.json(statuses));
        Integer id = response.readEntity(new GenericType<Integer>(){});
        return "Statusy podkategorii o ID = '"+id+"' zostały pomyślnie zaktualizowane.";
    }

    public boolean isTagExist(String text) {
        webTarget = client.target(apiURI).path("tags/"+ text +"/exist");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return  invocationBuilder.get(new GenericType<Boolean>(){});
    }

    public Integer addNewTag(Tag tag) {
        webTarget = client.target(apiURI).path("tags/");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        Response response =  invocationBuilder.post(Entity.entity(tag,MediaType.APPLICATION_JSON));
        Tag tagResponse = response.readEntity(new GenericType<Tag>(){});
        Float f = tagResponse.getId();

        return f.intValue();
    }

    public boolean isTagRemovable(String toString) {
        webTarget = client.target(apiURI).path("tags/"+toString+"/removable");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return  invocationBuilder.get(new GenericType<Boolean>(){});
    }

    public String deleteTag(String s) {
        webTarget = client.target(apiURI).path("tags/"+s);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        Integer response = invocationBuilder.delete(new GenericType<Integer>(){});
        return response.toString();
    }

    public Long getTagId(String toString) {
        webTarget = client.target(apiURI).path("tags/"+toString+"/id");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<Long>(){});
    }

    public String updateTag(Tag tag) {
        webTarget = client.target(apiURI).path("tags/");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        Response response =  invocationBuilder.put(Entity.entity(tag,MediaType.APPLICATION_JSON));

        Tag tagResponse = response.readEntity(new GenericType<Tag>(){});
        Float id = tagResponse.getId();
        return id.intValue()+"";
    }

    public Tag getTag(Long tagId) {
        webTarget = client.target(apiURI).path("tags/"+tagId);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<Tag>(){});
    }

    public Boolean isTagMarksTerm(Long tagId, Long id) {
        webTarget = client.target(apiURI).path("tags/"+tagId+"/term/"+id);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<Boolean>(){});
    }

    public void updateTagMarksTerm(Long tagId, Long termId, Boolean result) {
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(tagId);
        objects.add(termId);
        objects.add(result);
        webTarget = client.target(apiURI).path("tags/term/update");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        invocationBuilder.put(Entity.json(objects));
    }

    public ArrayList<Float> getAllAuthorsIdsFloat() {
        webTarget = client.target(apiURI).path("authors/ids");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<ArrayList<Float>>(){});
    }

    public Long isAuthorAssignToTerm(Float f, Integer integerId) {
        webTarget = client.target(apiURI).path("author/"+f.intValue()+"/term/"+integerId);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<Long>(){});
    }

    public List<Author> getAuthorsOfTerm(Long id) {
        webTarget = client.target(apiURI).path("term/authors/"+id);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<List<Author>>(){});
    }

    public boolean isAuthorExist(String name, String surname) {
        webTarget = client.target(apiURI).path("author/name/"+name+"/surname/"+surname);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<Boolean>(){});
    }

    public boolean isAuthorRemovable(Long selectedAuthorId) {
        webTarget = client.target(apiURI).path("author/"+selectedAuthorId.intValue()+"/removable");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<Boolean>(){});
    }


    public TermHistory getActualTermHistoryOfTerm(Integer termId) {
        webTarget = client.target(apiURI).path("term/"+termId+"/termHistory/actual");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        Long termHistoryId = invocationBuilder.get(new GenericType<Long>(){});
        return this.getTermHistory(termHistoryId);
    }

    public TermHistory getTermHistory(Long id){
        webTarget = client.target(apiURI).path("term/termHistory/"+id);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<TermHistory>(){});
    }

    public ArrayList<String> getStatusesOfTerm(Integer termId) {
        webTarget = client.target(apiURI).path("term/"+termId+"/statuses");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<ArrayList<String>>(){});
    }

    public String getStatusOfTermHistory(Long id) {
        webTarget = client.target(apiURI).path("termHistory/"+id+"/status");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<String>(){});
    }

    public Integer updateTerm(Term newTerm) throws JsonProcessingException {
        webTarget = client.target(apiURI).path("term");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(newTerm);

        Response response =  invocationBuilder.put(Entity.json(json));
        Term response1=response.readEntity(new GenericType<Term>(){});

        return response1.getId().intValue();
    }
}
