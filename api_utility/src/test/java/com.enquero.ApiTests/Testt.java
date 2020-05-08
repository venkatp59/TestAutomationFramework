/*
package com.enquero.ApiTests;


import com.enquero.PayLoads.PayLoad;
import com.enquero.apiutility.RestUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertTrue;
public class Testt {
    private Response res = null; //Response object
    private JsonPath jp = null; //JsonPath object
    Properties pr=new Properties();

    @BeforeTest
    public void beforeTest() throws IOException {
        //System.out.println("Present Project Directory : "+ System.getProperty("user.dir"));
        FileInputStream f=new FileInputStream(System.getProperty("user.dir")+ "/Demo.properties");
        pr.load(f);
    }

    @Test
    public void getLatestVersion(){
        String baseURI=pr.getProperty("baseURI");
        String basePath=pr.getProperty("basePath");
        String endpoint=pr.getProperty("latestVersionEndpoint");
        String queryParam="";
        //String validateParam="releaseDate";
        JsonPath getLatestVersionVal = getRequest(baseURI, basePath, endpoint, queryParam);
        String releaseDate = getLatestVersionVal.get("releaseDate");
        System.out.println("releaseDate is - "+releaseDate);
    }

*/
/*
    @Test
    public void postTest(){
        String baseURI=pr.getProperty("baseURI");
        String basePath=pr.getProperty("basePath");
        String endpoint=pr.getProperty("latestversionendpoint");
        String queryParam="";
        String validateParam="releaseDate";
        JsonPath x = getRequest(baseURI, basePath, endpoint, queryParam);

    }*//*

   */
/*public JSONObject getRequest (String baseURI,String basePath, String endpoint, String queryParam){

       RestAssured.baseURI=baseURI;
       String getRequestResource =basePath+endpoint;
              res=  given().queryParam(queryParam)
               .contentType(ContentType.JSON)
               .when()
               .get(getRequestResource)
               .then().assertThat()
               .statusCode(200).extract().response();
               System.out.println("To String ::"+res.getBody().asString());
               jp = RestUtil.getJsonPath(res);
               return jp;
   }*//*

    public JsonPath postRequest (String baseURI,String basePath, String endpoint, String queryParam,String payload){

        PayLoad payLoad=new PayLoad();
        RestAssured.baseURI=baseURI;
        String getRequestResource =basePath+endpoint;
        res=  given().queryParam(queryParam).body(payLoad.getPayloadLatestVersion())
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then().assertThat()
                .statusCode(201).extract().response();
        System.out.println("To String ::"+res.getBody().asString());
        jp = RestUtil.getJsonPath(res);
        return jp;
    }

    @AfterClass
    public void afterTest (){
        //Reset Values
        RestUtil.resetBaseURI();
        RestUtil.resetBasePath();
    }

    //*******************
    //***Local Methods***
    //*******************
    //Prints Attributes
*/
/*    private void DateDuration (JsonPath jp) {
        for(int i=0; i < HelperMethods.getIdList(jp).size(); i++ ) {
            System.out.println("Title: " + jp.get("items.title[" + i + "]"));
            System.out.println("pubDate: " + jp.get("items.pubDate[" + i + "]"));
            System.out.println("duration: " + jp.get("items.duration[" + i + "]"));
            System.out.print("\n");
        }
    }*//*




}
*/
