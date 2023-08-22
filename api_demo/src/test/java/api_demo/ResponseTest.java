package api_demo;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ResponseTest {
    @Test(priority = 1, groups = {"smoke"})
    public void test_case_one(){
        Response response = RequestDemo.getEmployeeDetails();
        System.out.println(response.getStatusCode());
        System.out.println(response);
        System.out.println(response.getBody().asString());
        //Assertion : pass, fail
        //Assert.assertEquals(response.getStatusCode(),200);
        //Assert.assertTrue(response.getBody().asString().contains("Tiger"));
    }

    @Test(priority = 2, groups = {"smoke"})
    public void test_case_two(){
        Response response = RequestDemo.createEmployee();
        System.out.println(response.getStatusCode());
        System.out.println(response);
        System.out.println(response.getBody().asString());
        //Assertion : pass, fail
        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertTrue(response.getBody().asString().contains("Successfully"));
    }

    @Test(priority = 3, groups = {"regression"})
    public void test_case_three(){
        Response response = RequestDemo.errorScenario();
        System.out.println(response.getStatusCode());
        System.out.println(response);
        System.out.println(response.getBody().asString());
        //Assertion : pass, fail
        //Assert.assertEquals(response.getStatusCode(),200);
        //Assert.assertTrue(response.getBody().asString().contains("Successfully"));
    }

    @Test(priority = 4, groups = {"smoke"})
    public void test_case_four(){
        Response response = RequestDemo.updateEmployee();
        System.out.println(response.getStatusCode());
        System.out.println(response);
        System.out.println(response.getBody().asString());
        //Assertion : pass, fail
        //Assert.assertEquals(response.getStatusCode(),200);
        //Assert.assertTrue(response.getBody().asString().contains("Successfully"));
    }

    @Test(priority = 5, groups = {"regression","smoke"})
    public void test_case_five(){
        Response response = RequestDemo.deleteEmployee();
        System.out.println(response.getStatusCode());
        System.out.println(response);
        System.out.println(response.getBody().asString());
        //Assertion : pass, fail
        //Assert.assertEquals(response.getStatusCode(),200);
        //Assert.assertTrue(response.getBody().asString().contains("Successfully"));
    }

}
