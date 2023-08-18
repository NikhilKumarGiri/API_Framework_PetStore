package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndpoints;
import api.payload.User;
import io.restassured.response.Response;

public class UserTest {
	Faker faker;
	//to pass this faker data to pojo class(which is User class)
	User userPayload;
	
	//declaring logger class
	public Logger logger;
	
	//execute before all the test classes
	@BeforeClass
	public void setUp()
	{
		faker=new Faker();
		userPayload=new User();
		
		userPayload.setId(faker.idNumber().hashCode());
		userPayload.setUsername(faker.name().username());
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		userPayload.setPassword(faker.internet().password(5,10));
		userPayload.setPhone(faker.phoneNumber().cellPhone());
	
		//logs
		logger=LogManager.getLogger(this.getClass());
	}
	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("*****Creating User*****");
		Response response=UserEndpoints.createuser(userPayload);
		response.then().log().all();
		Assert.assertEquals(response.statusCode(), 200);
		logger.info("*****User is Created*****");

	}
	@Test(priority=2)
	public void testGetUserByName()
	{
		logger.info("*****Get User info*****");

		//get the username from pojo class
		Response response=UserEndpoints.readuser(this.userPayload.getUsername());
		response.then().log().all();
		//when we use assert we have to use get status code
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("*****User info is Displayed*****");

		
	}
	@Test(priority=3)
	public void testUpdateUserByName ()
	{
		//update data using payload 
		userPayload.setFirstname(faker.name().firstName());
		userPayload.setLastName(faker.name().lastName());
		userPayload.setEmail(faker.internet().safeEmailAddress());
		//2nd pramamater user payload is updated
		Response response=UserEndpoints.updateuser(this.userPayload.getUsername(),userPayload);
		response.then().log().body();
		Assert.assertEquals(response.getStatusCode(),200);
		logger.info("*****User is Updated*****");

		//checking data after update
		Response responseAfterUpdate=UserEndpoints.readuser(this.userPayload.getUsername());
		responseAfterUpdate.then().log().body();
		Assert.assertEquals(responseAfterUpdate.getStatusCode(),200);


		
	}
	@Test(priority=4)
	public void testDeleteUserByName()
	{
		
		Response response=UserEndpoints.deleteuser(this.userPayload.getUsername());
	    Assert.assertEquals(response.getStatusCode(),200);
		logger.info("*****User is Deleted*****");

	}
}
