package api_demo;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class RequestDemo {
    // create a method to get employee details
    // create a rest assured request
    // When we want to use existing classes of any framework
    // Create instance of class
    // Syntax#1 Classname name = new Classname();
    // Syntax#2 Classtwo two = //code to get details();
    public static Response getEmployeeDetails(){
        Response response = given().get("https://dummy.restapiexample.com/api/v1/employee/3435");
        return response;
    }

    public static Response getPetDetails(){
        Response response = given().get("https://petstore.swagger.io/v2/pet/123");
        return response;
    }
    public static Response createEmployee(){
        Response response = given().accept(ContentType.JSON)
        .body("{\"name\":\"name_two\",\"salary\":\"10000\",\"age\":\"29\"}").post("https://dummy.restapiexample.com/api/v1/create");
        return response;
    }

    public static Response createEmployeeWithData(String name, Integer age, Integer salary){
        String requestBody = "{\"name\":\""+name+"\",\"salary\":\""+salary+"\",\"age\":\""+age+"\"}";
        Response response = given().accept(ContentType.JSON)
                .body(requestBody).post("https://dummy.restapiexample.com/api/v1/create");
        return response;
    }

    public static Response errorScenario(){
        Response response = given().accept(ContentType.JSON)
                .body("{}").post("https://dummy.restapiexample.com/api/v1/create");
        return response;
    }

    public static Response updateEmployee(){
        Response response = given().body("{\"name\":\"test_two\"}").put("https://dummy.restapiexample.com/api/v1/update/1");
        return response;
    }

    public static Response deleteEmployee(){
        Response response = given().delete("https://dummy.restapiexample.com/api/v1/delete/2");
        return response;
    }
}
