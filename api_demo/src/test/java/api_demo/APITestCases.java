package api_demo;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class APITestCases {
    @Test
    public void test_pet_store_get(){
        String petId = PropertyReader.getValueFromProperties("getPetId");
        String petStoreURL = PropertyReader.getValueFromProperties("petAPIURL")+petId;
        Response response = APIUtility.GetAPIRequest(petStoreURL);
        System.out.println(response.getStatusCode());
        System.out.println(response);
        System.out.println(response.getBody().asString());
        // Parsing Response Body
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);
        String petName = jsonPath.getString("name");
        System.out.println(petName);
        Assert.assertEquals(petName,"Peter");
        String petTags = jsonPath.getString("tags");
        System.out.println(petTags);
        String categoryName = jsonPath.getString("category.name");
        Assert.assertEquals(categoryName,"dogs");
        String tagOne = jsonPath.getString("tags[0].name");
        System.out.println(tagOne);
        String tagTwo = jsonPath.getString("tags[1].name");
        System.out.println(tagTwo);
    }

    @Test
    public void test_get_activities(){
        String activitiesURL = PropertyReader.getValueFromProperties("activitiesURL");
        Response response = APIUtility.GetAPIRequest(activitiesURL);
        System.out.println(response.getStatusCode());
        System.out.println(response);
        System.out.println(response.getBody().asString());
    }

    @Test
    public void test_create_pet(){
        String petPostURL = PropertyReader.getValueFromProperties("postPetURL");
        String petName = PropertyReader.getValueFromProperties("petName");
        String petId = PropertyReader.getValueFromProperties("petId");
        String petStatus = PropertyReader.getValueFromProperties("petStatus");
        String requestBody = "{\n" +
                "  \"id\": "+petId+",\n" +
                "  \"category\": {\n" +
                "    \"id\": 0,\n" +
                "    \"name\": \"cats\"\n" +
                "  },\n" +
                "  \"name\": \""+petName+"\",\n" +
                "  \"photoUrls\": [\n" +
                "    \"string\"\n" +
                "  ],\n" +
                "  \"tags\": [\n" +
                "    {\n" +
                "      \"id\": 0,\n" +
                "      \"name\": \"string\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"status\": \""+petStatus+"\"\n" +
                "}";
        Response response = APIUtility.PostAPIRequest(petPostURL,requestBody);
        System.out.println(response.getStatusCode());
        System.out.println(response);
        System.out.println(response.getBody().asString());
        }
}
