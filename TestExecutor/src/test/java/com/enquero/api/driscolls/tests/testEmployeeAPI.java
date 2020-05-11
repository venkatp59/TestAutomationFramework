package com.enquero.api.driscolls.tests;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.enquero.api.driscolls.endpoints.Endpoints;
import com.enquero.api.driscolls.pojo.employeeDetails;
import com.enquero.apiutility.RestUtil;
import com.enquero.reporter.ExtentTestReporter;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

import static com.enquero.apiutility.RestUtil.deleteJsonPath;
import static com.enquero.apiutility.RestUtil.getStringResponse;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;


public class testEmployeeAPI {
    public ExtentReports extentReports;
    private Response res = null; //Response object
    private JsonPath jp = null; //JsonPath object
    public Properties pr ;
    public String baseURI ="";
    public String basePath ="";
    public String endpoint = "";
    public String queryParam = "";
    public static String empid ="";
//    public ExcelUtils excelutils;


    @BeforeTest
    public void beforeTest() throws IOException {
        System.out.println("Present Project Directory : "+ System.getProperty("user.dir"));
        pr = new Properties();
//        FileInputStream f = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/com/enquero/api/driscolls/Input.properties");
//        pr.load(f);
//        baseURI = pr.getProperty("baseURI");
//        basePath = pr.getProperty("basePath");
        baseURI = Endpoints.APP_BASE_URI;
        basePath = Endpoints.APP_BASE_PATH;
        RestAssured.baseURI = baseURI;
        // ExtentTestReporter.getTest();
    }

    @Test(priority = 0)
    public void getEmployeeDetails(){

        endpoint = Endpoints.EMPLOYEES;
        System.out.println(baseURI);
        ExtentTestReporter.getTest().log(Status.INFO,"Base URI is : " + baseURI);
        JsonPath getEmpDetails = getRequest(baseURI, basePath, endpoint, queryParam);
        String status = getEmpDetails
                .getString("status");
        ExtentTestReporter.getTest().log(Status.INFO,"Response Body Status is : " + status);
        System.out.println("Status : " + status);
        Assert.assertEquals("success",status);
        ExtentTestReporter.getTest().log(Status.INFO,"Expected and Actual is matched");
        ExtentTestReporter.getTest().log(Status.INFO,"Test Passed Successfully");
    }

    @Test(priority = 1)
    public void postEmployeeDetails(){

//        String expname = excelutils.getCellData("testcase","EmpName",2);
//        String salary = excelutils.getCellData("testcase","EmpSal",2);
//        String age = excelutils.getCellData("testcase","EmpAge",2);
        String expname = "ApiAutoTest" + RestUtil.GenerateRandomNumber(3);
        String salary = RestUtil.GenerateRandomNumber(4);
        String age = RestUtil.GenerateRandomNumber(2);
        String namepath = "data.name";

        endpoint = basePath + Endpoints.CREATE_EMPLOYEE;
        System.out.println("endpoint is --> " + endpoint );
        ExtentTestReporter.getTest().log(Status.INFO,"Base URI is : " + baseURI);

        employeeDetails empdetails = new employeeDetails(expname, salary, age);
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(empdetails)
                .post(endpoint)
                .then()
                .assertThat()
                .statusCode(Endpoints.STATUSCODE).extract().response();

        System.out.println(response.getBody().asString());
        String actname = getStringResponse(response,namepath);
        String actstatus = getStringResponse(response,"status");
        empid = getStringResponse(response,"data.id");

        System.out.println("employee id is : " + empid);
        ExtentTestReporter.getTest().log(Status.INFO,"Response Body Status is : " + actstatus);

        Assert.assertEquals(actstatus,"success");
        Assert.assertEquals(actname,expname);

        ExtentTestReporter.getTest().log(Status.INFO,"Employee details created Successfully");
    }

    @Test(priority = 2)
    public void getEmployeeIdDetails(){
        endpoint = Endpoints.EMPLOYEE_ID;
        queryParam = "";
//        System.out.println("base URI is : " + baseURI);
//        System.out.println("Employee ID endpoint is :: " + endpoint);
//
//        Response response = getResuest( endpoint ,  "");
//        System.out.println("response is :  "  +  response);
//        System.out.println("ressponse body is : " + response.getBody().asString());
//        System.out.println("Status Code is :" + response.getStatusCode());
//
//        ExtentTestReporter.getTest().log(Status.INFO,"Test Passed Successfully");

        JsonPath getEmpDetails = getRequest(baseURI, basePath, endpoint);
        String status = getEmpDetails
                .getString("status");
//        System.out.println(status);
        String actempname = getEmpDetails.getString("data.employee_name");
        Assert.assertNotNull(actempname);

        Assert.assertEquals("success",status);
        ExtentTestReporter.getTest().log(Status.INFO,"Test Passed Successfully");
    }

    @Test(priority = 3, dependsOnMethods = "postEmployeeDetails")
    public void updateEmployeeDetails(){

        String expname = "ApiAutoTest" + RestUtil.GenerateRandomNumber(3);
        String salary = RestUtil.GenerateRandomNumber(4);
        String age = RestUtil.GenerateRandomNumber(2);
        String namepath = "data.name";

        String endpoint = basePath + Endpoints.UPDATE_EMPLOYEE + empid;
        System.out.println("Update employee end point is : " + endpoint);

        employeeDetails empdetails = new employeeDetails(expname, salary, age);

        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .body(empdetails)
                .put(endpoint)
                .then()
                .assertThat()
                .statusCode(Endpoints.STATUSCODE).extract().response();

        System.out.println("Response Body is : " + response.getBody().asString());

        String actname = getStringResponse(response,namepath);
        String actstatus = getStringResponse(response,"status");
//        empid = getStringResponse(response,"id");
        ExtentTestReporter.getTest().log(Status.INFO,"Response Body Status is : " + actstatus);
        Assert.assertEquals(actstatus,"success");
//        Assert.assertEquals(actname,expname);

        ExtentTestReporter.getTest().log(Status.INFO,"Employee details updated Successfully");
    }


    @Test(priority = 4, dependsOnMethods = "updateEmployeeDetails")
    public void deleteEmployeeRecord(){

        String endpoint = basePath + Endpoints.DELETE_EMPLOYEE + empid;
        System.out.println("endpoint is : " + endpoint);

        String message = deleteJsonPath(endpoint, "")
                .getString("status");
        Assert.assertNotNull(message);
//        System.out.println(message);
        ExtentTestReporter.getTest().log(Status.INFO,"Employee details deleted Successfully");
    }


//    @Test(enabled = true)
//    public void jiraLatestVersion() throws InterruptedException {
//        String endpoint = pr.getProperty("latestVersionEndpoint");
//        String queryParam = "";
//        //String validateParam="releaseDate";
//        JsonPath getLatestVersionVal = getRequest(baseURI, basePath, endpoint, queryParam);
//        //ExtentTestReporter.getTest().log(Status.INFO,"getLatestVersionVal is : " +getLatestVersionVal.toString());
//        String releaseDate = getLatestVersionVal.getString("releaseDate");
//        ExtentTestReporter.getTest().log(Status.INFO,"Release Date is : " +releaseDate );
//        System.out.println("releaseDate is - " + releaseDate);
//        Assert.assertNotEquals("null",releaseDate);
//        ExtentTestReporter.getTest().log(Status.INFO,"Jira Latest version Details Matched and Passed Successfully");
//        //Thread.sleep(10000);
//    }
//
//    @Test(enabled = true)
//    public void confluenceLatestVersion() throws InterruptedException {
//        String baseURI = pr.getProperty("baseURI");
//        String basePath = pr.getProperty("basePath");
//        String endpoint = pr.getProperty("confluenceVersionEndpoint");
//        String queryParam = "";
//        //String validateParam="releaseDate";
//        JsonPath getLatestVersionVal = getRequest(baseURI, basePath, endpoint, queryParam);
//        //ExtentTestReporter.getTest().log(Status.INFO,"getLatestVersionVal is : " +getLatestVersionVal.toString());
//
//        /*String name = getLatestVersionVal.getString("name");
//        ExtentTestReporter.getTest().log(Status.INFO,"Product Name is : " +name );
//        Assert.assertNotEquals("null",name);*/
//        int buildNumber =getLatestVersionVal.getInt("buildNumber");
//        ExtentTestReporter.getTest().log(Status.INFO,"BuildNumber is : " +buildNumber );
//        // System.out.println("releaseDate is - " + releaseDate);
//        Assert.assertNotEquals("null",buildNumber);
//        ExtentTestReporter.getTest().log(Status.INFO,"Confluence Latest version Details Matched and Passed successfully");
//        //Thread.sleep(10000);
//        String releaseDate = getLatestVersionVal.getString("releaseDate");
//        ExtentTestReporter.getTest().log(Status.INFO,"Release Date is : " +releaseDate );
//        Assert.assertNotEquals("null",releaseDate);
//    }
//
//    @Test(enabled = true)
//    public void bitbucketLatestVersion() throws InterruptedException {
//        String baseURI = pr.getProperty("baseURI");
//        String basePath = pr.getProperty("basePath");
//        String endpoint = pr.getProperty("bitbucketVersionEndpoint");
//        String queryParam = "";
//        //String validateParam="releaseDate";
//        JsonPath getLatestVersionVal = getRequest(baseURI, basePath, endpoint, queryParam);
//        ExtentTestReporter.getTest().log(Status.INFO,"getLatestVersionVal is : " +getLatestVersionVal.toString());
//
//        // String name = getLatestVersionVal.getJSONObject("name").toString();
//        /*String name = getLatestVersionVal.getString("name");
//        ExtentTestReporter.getTest().log(Status.INFO,"Product Name is : " +name );
//        Assert.assertNotEquals("null",name);*/
//        int buildNumber =getLatestVersionVal.getInt("buildNumber");
//        ExtentTestReporter.getTest().log(Status.INFO,"BuildNumber is : " +buildNumber );
//        //System.out.println("releaseDate is - " + releaseDate);
//        Assert.assertNotEquals("null",buildNumber);
//        ExtentTestReporter.getTest().log(Status.INFO,"BitBucket Latest version Details Matched and Passed successfully");
//        // Thread.sleep(10000);
//        String releaseDate = getLatestVersionVal.getString("releaseDate");
//        ExtentTestReporter.getTest().log(Status.INFO,"Release Date is : " +releaseDate );
//        Assert.assertNotEquals("null",releaseDate);
//    }
//
//    @Test
//    public void fecruLatestVersion() throws InterruptedException {
//        String baseURI = pr.getProperty("baseURI");
//        String basePath = pr.getProperty("basePath");
//        String endpoint = pr.getProperty("fecruVersionEndpoint");
//        String queryParam = "";
//        //String validateParam="releaseDate";
//        JsonPath getLatestVersionVal = getRequest(baseURI, basePath, endpoint, queryParam);
//        //ExtentTestReporter.getTest().log(Status.INFO,"getLatestVersionVal is : " +getLatestVersionVal.toString());
//
//        String name = getLatestVersionVal.getString("name");
//        ExtentTestReporter.getTest().log(Status.INFO,"Product Name is : " +name );
//        Assert.assertNotEquals("null",name);
//        int buildNumber = getLatestVersionVal.getInt("buildNumber");
//        ExtentTestReporter.getTest().log(Status.INFO,"BuildNumber is : " +buildNumber );
//        // System.out.println("releaseDate is - " + releaseDate);
//        Assert.assertNotEquals("null",buildNumber);
//        ExtentTestReporter.getTest().log(Status.INFO,"Fecru Latest version Details Matched and Passed successfully");
//        // Thread.sleep(10000);
//        String releaseDate = getLatestVersionVal.getString("releaseDate");
//        ExtentTestReporter.getTest().log(Status.INFO,"Release Date is : " +releaseDate );
//        Assert.assertNotEquals("null",releaseDate);
//    }

    /* @Test
     public void postTest(){
         String baseURI=pr.getProperty("baseURI");
         String basePath=pr.getProperty("basePath");
         String endpoint=pr.getProperty("latestversionendpoint");
         String queryParam="";
         String validateParam="releaseDate";
         JsonPath x = getRequest(baseURI, basePath, endpoint, queryParam);

     }*/

    public Response getResuest(String endpoint , String queryParam){
        Response response = null;



        try {
            response = RestAssured.given().contentType(ContentType.JSON)
                    .when()
                    .get(endpoint).then().extract().response();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  response;
    }

    public JsonPath getRequest(String baseURI, String basePath, String endpoint, String queryParam) {

        RestAssured.baseURI = baseURI;
        String getRequestResource = basePath + endpoint;
        System.out.println("Path is : " + getRequestResource);

        res = given().queryParam(queryParam)
                .contentType(ContentType.JSON)
                .when()
                .get(getRequestResource)
                .then().assertThat()
                .statusCode(200).extract().response();

        System.out.println("response is " + res);

        // System.out.println("To String ::" + res.getBody().asString());
        jp = RestUtil.getJsonPath(res);
        return jp;
    }

    public JsonPath getRequest(String baseURI, String basePath, String endpoint) {

        RestAssured.baseURI = baseURI;
        String getRequestResource = basePath + endpoint;
        System.out.println("Path is : " + getRequestResource);

        res = given().contentType(ContentType.JSON)
                .when()
                .get(getRequestResource)
                .then().assertThat()
                .statusCode(200).extract().response();

        System.out.println("response is " + res);

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