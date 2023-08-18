package api.test;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.endpoints.UserEndpoints;
import api.payload.User;
import api.utilities.DataProviders;
import io.restassured.response.Response;

public class DatadrivenTest {

	//creating multiple user
	//dataProviderClass=DataProviders.class (it is coming from another class)
	@Test(priority=1,dataProvider="Data",dataProviderClass=DataProviders.class)
	//all the parameter sequence should match with the excel sheet
	public void testPostUser(String userId,String userName,String fname,String lname,String usermailid,String pwd,String ph)
	{
		//user is our pojo class 
		User userPayload=new User();
		userPayload.setId(Integer.parseInt(userId));
		userPayload.setUsername(userName);
		userPayload.setFirstname(fname);
		userPayload.setLastName(lname);
		userPayload.setEmail(usermailid);
		userPayload.setPassword(pwd);
		userPayload.setPhone(ph);
		Response response=UserEndpoints.createuser(userPayload);
		 
		Assert.assertEquals(response.statusCode(), 200);
	}
	@Test(priority=2,dataProvider="UserNames",dataProviderClass=DataProviders.class)
	public void testDeleteUserByName(String UserName)
	{
		Response response=UserEndpoints.deleteuser(UserName);
		Assert.assertEquals(response.getStatusCode(),(200));
	}
	
}
