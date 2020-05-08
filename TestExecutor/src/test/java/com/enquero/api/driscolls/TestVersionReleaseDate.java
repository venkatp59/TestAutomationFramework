package com.enquero.api.driscolls;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.enquero.apiutility.RestUtil;
import com.enquero.reporter.ExtentTestReporter;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;
public class TestVersionReleaseDate {
    public ExtentReports extentReports;
    private Response res = null; //Response object
    private JsonPath jp = null; //JsonPath object
    Properties pr = new Properties();

    @BeforeTest
    public void beforeTest() throws IOException {
        System.out.println("Present Project Directory : "+ System.getProperty("user.dir"));
        FileInputStream f = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/com/enquero/api/driscolls/Input.properties");
        pr.load(f);
        ExtentTestReporter.getTest();
    }

    @Test(enabled = true)

    public void jiraLatestVersion() throws InterruptedException {
        String baseURI = pr.getProperty("baseURI");
        String basePath = pr.getProperty("basePath");
        String endpoint = pr.getProperty("latestVersionEndpoint");
        String queryParam = "";
        //String validateParam="releaseDate";
        JsonPath getLatestVersionVal = getRequest(baseURI, basePath, endpoint, queryParam);
        //ExtentTestReporter.getTest().log(Status.INFO,"getLatestVersionVal is : " +getLatestVersionVal.toString());
        String releaseDate = getLatestVersionVal.getString("releaseDate");
        ExtentTestReporter.getTest().log(Status.INFO,"Release Date is : " +releaseDate );
        System.out.println("releaseDate is - " + releaseDate);
        Assert.assertNotEquals("null",releaseDate);
        ExtentTestReporter.getTest().log(Status.INFO,"Jira Latest version Details Matched and Passed Successfully");
        //Thread.sleep(10000);
    }
    @Test(enabled = true)

    public void confluenceLatestVersion() throws InterruptedException {
        String baseURI = pr.getProperty("baseURI");
        String basePath = pr.getProperty("basePath");
        String endpoint = pr.getProperty("confluenceVersionEndpoint");
        String queryParam = "";
        //String validateParam="releaseDate";
        JsonPath getLatestVersionVal = getRequest(baseURI, basePath, endpoint, queryParam);
        //ExtentTestReporter.getTest().log(Status.INFO,"getLatestVersionVal is : " +getLatestVersionVal.toString());

        /*String name = getLatestVersionVal.getString("name");
        ExtentTestReporter.getTest().log(Status.INFO,"Product Name is : " +name );
        Assert.assertNotEquals("null",name);*/
        int buildNumber =getLatestVersionVal.getInt("buildNumber");
        ExtentTestReporter.getTest().log(Status.INFO,"BuildNumber is : " +buildNumber );
       // System.out.println("releaseDate is - " + releaseDate);
        Assert.assertNotEquals("null",buildNumber);
        ExtentTestReporter.getTest().log(Status.INFO,"Confluence Latest version Details Matched and Passed successfully");
        //Thread.sleep(10000);
        String releaseDate = getLatestVersionVal.getString("releaseDate");
        ExtentTestReporter.getTest().log(Status.INFO,"Release Date is : " +releaseDate );
        Assert.assertNotEquals("null",releaseDate);
    }
    @Test(enabled = true)

    public void bitbucketLatestVersion() throws InterruptedException {
        String baseURI = pr.getProperty("baseURI");
        String basePath = pr.getProperty("basePath");
        String endpoint = pr.getProperty("bitbucketVersionEndpoint");
        String queryParam = "";
        //String validateParam="releaseDate";
        JsonPath getLatestVersionVal = getRequest(baseURI, basePath, endpoint, queryParam);
        ExtentTestReporter.getTest().log(Status.INFO,"getLatestVersionVal is : " +getLatestVersionVal.toString());

       // String name = getLatestVersionVal.getJSONObject("name").toString();
        /*String name = getLatestVersionVal.getString("name");
        ExtentTestReporter.getTest().log(Status.INFO,"Product Name is : " +name );
        Assert.assertNotEquals("null",name);*/
        int buildNumber =getLatestVersionVal.getInt("buildNumber");
        ExtentTestReporter.getTest().log(Status.INFO,"BuildNumber is : " +buildNumber );
        //System.out.println("releaseDate is - " + releaseDate);
        Assert.assertNotEquals("null",buildNumber);
        ExtentTestReporter.getTest().log(Status.INFO,"BitBucket Latest version Details Matched and Passed successfully");
       // Thread.sleep(10000);
        String releaseDate = getLatestVersionVal.getString("releaseDate");
        ExtentTestReporter.getTest().log(Status.INFO,"Release Date is : " +releaseDate );
        Assert.assertNotEquals("null",releaseDate);
    }
    @Test
    public void fecruLatestVersion() throws InterruptedException {
        String baseURI = pr.getProperty("baseURI");
        String basePath = pr.getProperty("basePath");
        String endpoint = pr.getProperty("fecruVersionEndpoint");
        String queryParam = "";
        //String validateParam="releaseDate";
        JsonPath getLatestVersionVal = getRequest(baseURI, basePath, endpoint, queryParam);
        //ExtentTestReporter.getTest().log(Status.INFO,"getLatestVersionVal is : " +getLatestVersionVal.toString());

        String name = getLatestVersionVal.getString("name");
        ExtentTestReporter.getTest().log(Status.INFO,"Product Name is : " +name );
        Assert.assertNotEquals("null",name);
        int buildNumber = getLatestVersionVal.getInt("buildNumber");
        ExtentTestReporter.getTest().log(Status.INFO,"BuildNumber is : " +buildNumber );
        // System.out.println("releaseDate is - " + releaseDate);
        Assert.assertNotEquals("null",buildNumber);
        ExtentTestReporter.getTest().log(Status.INFO,"Fecru Latest version Details Matched and Passed successfully");
       // Thread.sleep(10000);
        String releaseDate = getLatestVersionVal.getString("releaseDate");
        ExtentTestReporter.getTest().log(Status.INFO,"Release Date is : " +releaseDate );
        Assert.assertNotEquals("null",releaseDate);
    }
    /* @Test
     public void postTest(){
         String baseURI=pr.getProperty("baseURI");
         String basePath=pr.getProperty("basePath");
         String endpoint=pr.getProperty("latestversionendpoint");
         String queryParam="";
         String validateParam="releaseDate";
         JsonPath x = getRequest(baseURI, basePath, endpoint, queryParam);

     }*/
    public JsonPath getRequest(String baseURI, String basePath, String endpoint, String queryParam) {

        RestAssured.baseURI = baseURI;
        String getRequestResource = basePath + endpoint;
        res = given().queryParam(queryParam)
                .contentType(ContentType.JSON)
                .when()
                .get(getRequestResource)
                .then().assertThat()
                .statusCode(200).extract().response();
       // System.out.println("To String ::" + res.getBody().asString());
        jp = RestUtil.getJsonPath(res);
        return jp;
    }

    public JsonPath postRequest(String baseURI, String basePath, String endpoint, String queryParam, String payload) {

        //PayLoad payLoad=new PayLoad();
        RestAssured.baseURI = baseURI;
        String getRequestResource = basePath + endpoint;
        res = given().queryParam(queryParam)//.body(payLoad.getPayloadLatestVersion())
                .contentType(ContentType.JSON)
                .when()
                .post(endpoint)
                .then().assertThat()
                .statusCode(201).extract().response();
        System.out.println("To String ::" + res.getBody().asString());
        jp = RestUtil.getJsonPath(res);
        return jp;
    }

    @AfterClass
    public void afterTest() {
        //Reset Values
        RestUtil.resetBaseURI();
        RestUtil.resetBasePath();
    }
}