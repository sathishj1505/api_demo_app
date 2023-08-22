package api_demo;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class APIUtility {
    public static Response GetAPIRequest(String api_url){
        RestAssured.useRelaxedHTTPSValidation();
        Response response = given()
                //.auth()
                //.oauth2("<token>")
                //.basic("username","password")
                //.header("Authorization","Bearer"+"token")
                .get(api_url);
        return response;
    }

    public static Response PostAPIRequest(String api_url, String body){
        Response response = given().accept(ContentType.JSON).body(body).contentType("application/json").post(api_url);
        return response;
    }
    public static Response putAPIRequest(String api_url, String body){
        Response response = given().accept(ContentType.JSON).contentType("application/json")
                .body(body).put(api_url);
        return response;
    }

    public static Response deleteRequest(String api_url){
        Response response = given().delete(api_url);
        return response;
    }
}
