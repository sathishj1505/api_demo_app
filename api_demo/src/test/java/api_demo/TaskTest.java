package api_demo;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class TaskTest {
    // resource.properties
    // PropertyReader
    // APIUtility
    // Test Case
    @Test(priority = 1, groups = {"task"} , testName = "get user details", description = "get user edtails")
    public void test_get_user_email(){
        // GET VARIABLES/ DATA
        String api_url = PropertyReader.getValueFromProperties("taskAPIURL");
        String userId = PropertyReader.getValueFromProperties("taskuserId");

        // GET THE RESPONSE
        Response response = APIUtility.GetAPIRequest(api_url+userId);
        // VERIFY RESPONSE CODE
        Assert.assertEquals(response.getStatusCode(),200);
        //console = System.out.println
        // VERIFY RESPONSE BODY DATA
        String responseBody = response.getBody().asString();
        // PARSING USING JSONPATH
        JsonPath jsonPath = new JsonPath(responseBody);
        String email = jsonPath.getString("email");
        System.out.println(email);
        // ASSERT THE DATA
    }

    @Test(priority = 2, groups = {"task"}, testName = "get user id posts")
    public void test_userid_posts(){
        List<String> listUsers = new ArrayList<String>();
        Integer userId = Integer.valueOf(PropertyReader.getValueFromProperties("taskuserId"));

        // get all 100 posts
        String api_url = PropertyReader.getValueFromProperties("taskUserPostsURL");

        Response response = APIUtility.GetAPIRequest(api_url);
        Assert.assertEquals(response.getStatusCode(), 200);

        // filter out with userId
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        System.out.println(jsonPath.getString("[0].userId"));
        //[0].userId
        //[1].userId
        //.... [99].userId
        // filter records one by one
        // for loop
        // String test = jsonPath.getString("");
        // for 0, 0<100 (true), 0+1 = 1
            // [0].userId
        // for 1, 1<100 (true), 1+1 = 2
            // [1].userId
        for(int i=0;i<100;i++){
            if(Integer.valueOf(jsonPath.getString("["+i+"].userId"))==userId){
                listUsers.add(String.valueOf(i)+": post Id is: "+
                        jsonPath.getString("["+i+"].id"));
            }
        }
        System.out.println(listUsers);
    }

    @Test(priority = 3, groups = {"task"}, testName = "post user request")
    public void test_post_user_request(){
        String api_url = PropertyReader.getValueFromProperties("taskUserPostsURL");
        String userId = PropertyReader.getValueFromProperties("taskuserId");
        String requestBody = "{\n" +
                "    \"userId\": "+userId+",\n" +
                "    \"title\": \"test title post req\",\n" +
                "    \"body\": \"test body post req\"\n" +
                "}";

        Response response = APIUtility.PostAPIRequest(api_url, requestBody);
        Assert.assertEquals(response.getStatusCode(), 201);
        System.out.println(response.getBody().asString());
    }
}