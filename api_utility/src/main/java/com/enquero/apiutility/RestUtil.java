package com.enquero.apiutility;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapper;
import io.restassured.mapper.ObjectMapperDeserializationContext;
import io.restassured.mapper.ObjectMapperSerializationContext;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.*;
import static io.restassured.RestAssured.basePath;

public class RestUtil {
    //Global Setup Variables
    public static String path; //Rest request path

    /*
    ***Sets Base URI***
    Before starting the test, we should set the RestAssured.baseURI
    */
    public static void setBaseURI (String baseURI){
        RestAssured.baseURI = baseURI;
    }

    /*
    ***Sets base path***
    Before starting the test, we should set the RestAssured.basePath
    */
    public static void setBasePath(String basePathTerm){
        RestAssured.basePath = basePathTerm;
    }

    /*
    ***Reset Base URI (after test)***
    After the test, we should reset the RestAssured.baseURI
    */
    public static void resetBaseURI (){
        RestAssured.baseURI = null;
    }

    /*
    ***Reset base path (after test)***
    After the test, we should reset the RestAssured.basePath
    */
    public static void resetBasePath(){
        RestAssured.basePath = null;
    }

    /*
    ***Sets ContentType***
    We should set content type as JSON or XML before starting the test
    */
    public static void setContentType (ContentType Type){
        given().contentType(Type);
    }

    /*
    ***search query path***
    It is  equal to "barack obama/videos.json?num_of_videos=4"
    */
    public static void  createSearchQueryPath(String endpoint)
    {
        path = baseURI+basePath+endpoint;
        System.out.println("path is : "+path);
        //path = searchTerm + "/" + jsonPathTerm + "?" + param + "=" + paramValue;
    }

    /*
    ***Returns response***
    We send "path" as a parameter to the Rest Assured'a "get" method
    and "get" method returns response of API
    */
    public static Response getResponse() {
        //System.out.print("path: " + path +"\n");
        return get(path);
    }
    public static Response postResponse( )
    {
        return RestAssured.post(path);
    }
    public static Response deleteResponse()
    {
        return RestAssured.delete(path);
    }

    /*
     ***Returns JsonPath object***
     * First convert the API's response to String type with "asString()" method.
     * Then, send this String formatted json response to the JsonPath class and return the JsonPath
     */
    public static JsonPath getJsonPath(Response r)
    {
        String resString = r.asString();
       // log.info(resString);
       // JsonPath j=new JsonPath(resString);
        //JSONObject jsonObject = new JSONObject(resString);
        //JSONObject myResponse = jsonObject.getJSONObject("MyResponse");
        //JSONArray tsmresponse = (JSONArray) myResponse.get("listTsm");
        //https://dzone.com/articles/how-to-parse-json-data-from-a-rest-api-using-simpl
        JsonPath j = r.jsonPath();
        return  j;
    }

}
