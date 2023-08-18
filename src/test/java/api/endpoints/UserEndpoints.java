package api.endpoints;
//userEndPoints.java
//Created for perform CRUD operation on the user module
import static io.restassured.RestAssured.*;


import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class UserEndpoints {
	public static Response createuser(User payload)
	{
		Response response=given()
				//contenttype and accept are coming from swagger
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.body(payload)
				.when()
				.post(Routes.post_url);
		//using this response we can do validation
		return response;
	}
	//which user name you want to get the info
	public static Response readuser(String userName)
	{
		Response response=given()
				.pathParam("username", userName)
				.when()
				.get(Routes.get_url);
		return response;
		
	}
	//which user and what is the data you want to update(parameter)
	public static Response updateuser(String userName,User payload)
	{
		Response response=given()
						 .contentType(ContentType.JSON)
						 .accept(ContentType.JSON)
						 .pathParam("username", userName)
						 .body(payload)
						 .when()
						 .put(Routes.put_url);
		return response;
			
	}
	public static Response deleteuser(String userName)
	{
		Response response=given()
						  .pathParam("username", userName)
						  .when()
						  .delete(Routes.delete_url);
		return response;
	}
	
}
