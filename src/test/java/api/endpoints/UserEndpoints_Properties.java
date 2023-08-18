package api.endpoints;
//userEndPoints.java
//Created for perform CRUD operation on the user module
import static io.restassured.RestAssured.*;

import java.util.ResourceBundle;

import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints_Properties {
	//method is created for getting the URL's from properties file
	public static ResourceBundle getURL()
	{
		//load properties file
		ResourceBundle routes=ResourceBundle.getBundle("routes");
		return routes;
	}
	public static Response createuser(User payload)
	{
		//getting the string post url from getURL --from properties file routes
		String post_url=getURL().getString("post_url");
		
		Response response=given()
				//contenttype and accept are coming from swagger
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.post(post_url);
		//using this response we can do validation
		return response;
	}
	//which user name you want to get the info
	public static Response readuser(String userName)
	{
		String get_url=getURL().getString("get_url");

		Response response=given()
				.pathParam("username", userName)
				.when()
				.get(get_url);
		return response;
		
	}
	//which user and what is the data you want to update(parameter)
	public static Response updateuser(String userName,User payload)
	{
		String update_url=getURL().getString("update_url");

		Response response=given()
						 .contentType(ContentType.JSON)
						 .accept(ContentType.JSON)
						 .pathParam("username", userName)
						 .body(payload)
						 .when()
						 .put(update_url);
		return response;
			
	}
	public static Response deleteuser(String userName)
	{
		String delete_url=getURL().getString("update_url");

		Response response=given()
						  .pathParam("username", userName)
						  .when()
						  .delete(delete_url);
		return response;
	}
	
}
