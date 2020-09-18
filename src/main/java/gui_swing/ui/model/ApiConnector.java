package gui_swing.ui.model;




import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import gui_swing.ui.model.filters.ListsF;
import gui_swing.ui.model.pojo.*;

import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Collection;
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

    List<Term> responseList =new ArrayList<>();

    String apiURI = ConfigManager.getApiURI()+"/api/";
    public ApiConnector() {
    }

    public List<Term> getAllTerm() {


            webTarget = client.target(apiURI).path("term");
            //webTarget = client.target(apiURI).path("terms/50");
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

    public List<Term> getTermsMatchedToTag(Float id) {
        Long idLong = id.longValue();
        webTarget = client.target(apiURI).path("term/tag/"+idLong+"/matched");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<List<Term>>(){});
    }

    public String getContentOfTerm(Long termId, Long version) {
        webTarget = client.target(apiURI).path("term/id/"+termId+"/version/"+version);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<String>(){});

    }

    public Collection<? extends Long> findAllTermsWitchHeaderContains(String text) {

        webTarget = client.target(apiURI).path("term/header/contains/"+text);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<List<Long>>(){});
    }

    public void getTermsByFullText(ArrayList<Long> idsOfFoundTerms) {

        webTarget = client.target(apiURI).path("term/fulltext");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        Response response =  invocationBuilder.post(Entity.entity(idsOfFoundTerms,MediaType.APPLICATION_JSON));
        responseList = response.readEntity(new GenericType<List<Term>>(){});

        if(responseList.isEmpty())
            this.setEmpty(true);
        else
            this.setEmpty(false);

    }

    public Collection<? extends Long> findTermsWitchAllVersionContentContains(String text) {
        webTarget = client.target(apiURI).path("term/content/contains/all/"+text);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<List<Long>>(){});
    }

    public Collection<? extends Long> findTermsWitchActualVersionContentContains(String text) {
        webTarget = client.target(apiURI).path("term/content/contains/actual/"+text);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<List<Long>>(){});
    }

    public ArrayList<Term> getTermReferences(Integer termId) {
        webTarget = client.target(apiURI).path("term/references/"+termId);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<ArrayList<Term>>(){});
    }

    public ArrayList<Term> getTermForWhomThisTermIsReferenced(Integer termId) {
        webTarget = client.target(apiURI).path("term/referenced/"+termId);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<ArrayList<Term>>(){});
    }

    public ArrayList<Term> getTermReferencesProposal(Integer termId) {
        webTarget = client.target(apiURI).path("term/references/proposal/"+termId);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<ArrayList<Term>>(){});
    }

    public ArrayList<Term> getTermForWhomThisTermIsReferencedProposal(Integer termId) {
        webTarget = client.target(apiURI).path("term/referenced/proposal/"+termId);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<ArrayList<Term>>(){});
    }

    public Boolean addReferenceToTerm(Integer termId, Integer referenceId) {
        webTarget = client.target(apiURI).path("term/reference/add/"+termId+"/"+referenceId);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<Boolean>(){});

    }
    public Boolean addReferencedToTerm(Integer termId, Integer referencedId) {
        webTarget = client.target(apiURI).path("term/referenced/add/"+termId+"/"+referencedId);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<Boolean>(){});

    }

    public Boolean deleteTermReference(Integer termId, Integer referenceId) {
        webTarget = client.target(apiURI).path("term/reference/delete/"+termId+"/"+referenceId);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<Boolean>(){});
    }

    public Boolean deleteTermReferenced(Integer termId, Integer referenceId) {
        webTarget = client.target(apiURI).path("term/referenced/delete/"+termId+"/"+referenceId);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<Boolean>(){});
    }

    public void updateTermHistory(Long aLong, TermHistory termHistory) {
        webTarget = client.target(apiURI).path("termHistory/add/"+aLong);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        invocationBuilder.post(Entity.entity(termHistory,MediaType.APPLICATION_JSON));

    }

    public String getCreatedByOfTermHistory(Long id) {
        webTarget = client.target(apiURI).path("termHistory/createdBy/"+id);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<String>(){});
    }


    public TermHistory getTermHistoryToTermWidthVersion(Integer termId, Integer versionNumber) {
        webTarget = client.target(apiURI).path("termHistory/term/"+termId+"/version/"+versionNumber);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<TermHistory>(){});
    }

    public ArrayList<User> getAllEnabledUsers() {
        webTarget = client.target(apiURI).path("users/enabled");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<ArrayList<User>>(){});
    }

    public ArrayList<String> getAllRolesName() {
        webTarget = client.target(apiURI).path("roles/names");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<ArrayList<String>>(){});
    }

    public User getUser(Integer id) {
        webTarget = client.target(apiURI).path("user/"+id);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<User>(){});
    }

    public Boolean hideUser(Integer id) {
        webTarget = client.target(apiURI).path("user/hide/"+id);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
       return invocationBuilder.get(new GenericType<Boolean>(){});
    }

    public Boolean updateUser(User user) {
        webTarget = client.target(apiURI).path("user");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        Response response =  invocationBuilder.put(Entity.entity(user,MediaType.APPLICATION_JSON));
        return response.readEntity(new GenericType<Boolean>(){});

    }
    public void addUser(User user) {
        webTarget = client.target(apiURI).path("user");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        Response response =  invocationBuilder.post(Entity.entity(user,MediaType.APPLICATION_JSON));


    }


    public UserRole getUserRole(Long id) {
        webTarget = client.target(apiURI).path("userRole/user/"+id);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<UserRole>(){});
    }

    public boolean isUserExist(String text) {
        webTarget = client.target(apiURI).path("user/exist/"+text);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<Boolean>(){});
    }

    public User getUser(String login) {
        String body = "{\"login\":\""+login+"\"}";
        webTarget = client.target(apiURI).path("user/login");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        Response response = invocationBuilder.post(Entity.entity(body,MediaType.APPLICATION_JSON));
        return response.readEntity(new GenericType<User>(){});


    }

    public void setNewPassword(Long id, String password) {
        String body  = "{\"id\":\""+id+"\",\"password\":\""+password+"\"}";
        webTarget = client.target(apiURI).path("user/setPassword");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        Response response = invocationBuilder.put(Entity.entity(body,MediaType.APPLICATION_JSON));
        //return response.readEntity(new GenericType<User>(){});
    }

    public ArrayList<Term> getTermsByIds(ArrayList<Long> termsArray) {

        webTarget = client.target(apiURI).path("terms/getByIds");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        Response response = invocationBuilder.post(Entity.entity(termsArray,MediaType.APPLICATION_JSON));
        return response.readEntity(new GenericType<ArrayList<Term>>(){});
    }

    public ArrayList<TermHistory> getAllTermHistoriesOfTerm(Long id) {
        webTarget = client.target(apiURI).path("term/termHistories/"+id);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<ArrayList<TermHistory>>(){});
    }

    public Integer getVerses(Long authorId, Long termId) {
        webTarget = client.target(apiURI).path("term/"+termId+"/author/"+authorId);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<Integer>(){});
    }

    public void termImport(Term term) throws JsonProcessingException {
        webTarget = client.target(apiURI).path("term/import");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(term);

        //System.out.println(json);

        //System.out.println(obj.toString());
          invocationBuilder.post(Entity.json(json));

    }

    public ArrayList<UserRole> getUserRoles() {
        webTarget = client.target(apiURI).path("userRoles");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<ArrayList<UserRole>>(){});
    }

    public UserRole getUserRole(String name) {
        webTarget = client.target(apiURI).path("userRole/name/"+name);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<UserRole>(){});
    }

    public Boolean updateUserRole(UserRole userRole) {
        webTarget = client.target(apiURI).path("userRole");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        Response response =  invocationBuilder.put(Entity.entity(userRole,MediaType.APPLICATION_JSON));
        return response.readEntity(new GenericType<Boolean>(){});
    }

    public boolean isUserRoleExist(String text) {
        webTarget = client.target(apiURI).path("userRole/exist/"+text);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<Boolean>(){});
    }

    public void addUserRole(String text) {
        webTarget = client.target(apiURI).path("userRole/"+text);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        invocationBuilder.post( Entity.text(text));
    }

    public boolean isUserRoleHasUsers(String name) {
        webTarget = client.target(apiURI).path("userRole/users/"+name);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<Boolean>(){});
    }

    public void deleteUserRole(String name) {
        webTarget = client.target(apiURI).path("userRole/"+name);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        invocationBuilder.delete(new GenericType<String>(){});
    }

    public boolean isTermExist(String name) {
        webTarget = client.target(apiURI).path("term/exist/"+name);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
       return  invocationBuilder.get(new GenericType<Boolean>(){});
    }

    public Term getTermOfTermHistory(Integer termHistoryId) {
        webTarget = client.target(apiURI).path("term/termHistoryId/"+termHistoryId);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<Term>(){});
    }

    public ArrayList<TermHistoryComment> getAllCommentsOfTerm(Long id) {
        webTarget = client.target(apiURI).path("termHistoryComments/term/"+id);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<ArrayList<TermHistoryComment>>(){});
    }

    public ArrayList<TermHistoryComment> getAllCommentsOfTermHistory(Long id) {
        webTarget = client.target(apiURI).path("termHistoryComments/termHistory/"+id);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<ArrayList<TermHistoryComment>>(){});
    }

    public String getCommentCreatedBy(Long id) {
        webTarget = client.target(apiURI).path("termHistoryComment/createdBy/"+id);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<String>(){});
    }
    public String getCommentModifiedBy(Long id) {
        webTarget = client.target(apiURI).path("termHistoryComment/modifiedBy/"+id);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<String>(){});
    }

    public Integer getNextCommentLp(Long id) {
        webTarget = client.target(apiURI).path("termHistoryComment/getNextLp/term/"+id);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<Integer>(){});
    }

    public void updateTermHistoryWithComment(TermHistory termHistory) {
        webTarget = client.target(apiURI).path("termHistory/updateWithComment");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        invocationBuilder.put(Entity.entity(termHistory,MediaType.APPLICATION_JSON));
    }

    public void createTermHistoryComment(Long id, TermHistoryComment termHistoryComment) {
        webTarget = client.target(apiURI).path("termHistoryComment/"+id);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        invocationBuilder.post( Entity.entity(termHistoryComment,MediaType.APPLICATION_JSON));
    }

    public TermHistoryComment getTermHistoryComment(Long id, Integer commentLp) {
        webTarget = client.target(apiURI).path("termHistoryComment/term/"+id+"/lp/"+commentLp);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<TermHistoryComment>(){});
    }

    public void updateTermHistoryComment(Long id, TermHistoryComment termHistoryComment) {
        webTarget = client.target(apiURI).path("termHistoryComment/"+id);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        invocationBuilder.put( Entity.entity(termHistoryComment,MediaType.APPLICATION_JSON));
    }

    public void deleteComment(Long id, Integer commentLp) {
        webTarget = client.target(apiURI).path("termHistoryComment/term/"+id+"/lp/"+commentLp);
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
         invocationBuilder.delete();
    }

    public String getLoginOfCommentModifier(Long id) {
        webTarget = client.target(apiURI).path("termHistoryComment/"+id+"/ModifiedBy");
        invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON).header(HttpHeaders.AUTHORIZATION, "Bearer " + ConfigManager.getJwtToken());
        return invocationBuilder.get(new GenericType<String>(){} );
    }
}
