package api_demo;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class RegressionTest {
    @Test(priority = 1, groups = {"regression"})
    public void test_create_employee_with_data(){
        String employeeName = "";
        Integer employeeAge = 0;
        Integer employeeSalary = 0;
        String postURL = "";
        try {
            InputStream inputStream = new FileInputStream("src/test/resources.properties");
            Properties properties = new Properties();
            properties.load(inputStream);
            employeeName = properties.getProperty("employeeName");
            employeeAge = Integer.valueOf(properties.getProperty("employeeAge"));
            employeeSalary = Integer.valueOf(properties.getProperty("employeeSalary"));
            postURL = properties.getProperty("createEmployeeURL");

        } catch (Exception exception){
        }
        String requestBody = "{\"name\":\""+employeeName+"\",\"salary\":\""+employeeSalary+"\",\"age\":\""+employeeAge+"\"}";
        //Response response = RequestDemo.createEmployeeWithData(employeeName,employeeAge,employeeSalary);
        Response response = APIUtility.PostAPIRequest(postURL,requestBody);
        System.out.println(response.getStatusCode());
        System.out.println(response);
        System.out.println(response.getBody().asString());
    }

    @Test(priority = 2, groups = {"pets"})
    public void test_get_pet_details(){
        Response response = RequestDemo.getPetDetails();
        System.out.println(response.getStatusCode());
        System.out.println(response);
        System.out.println(response.getBody().asString());
    }
}
