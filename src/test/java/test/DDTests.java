package test;

import org.testng.Assert;

import org.testng.annotations.Test;

import endpoints.UserEndPoint;
import io.restassured.response.Response;
import payloads.User;
import utilities.DataProviders;


public class DDTests {

	@Test(priority=1, dataProvider="Data", dataProviderClass=DataProviders.class)
	public void testPostUser(String ID,String UserName,String FirstName,String LastName,String Email,String Password,String PhoneNo)
	{
		User userPayLoad= new User();
		userPayLoad.setId(Integer.parseInt(ID));
		userPayLoad.setUsername(UserName);
		userPayLoad.setFirstName(FirstName);
		userPayLoad.setLastName(LastName);
		userPayLoad.setEmail(Email);
		userPayLoad.setPassword(Password);
		userPayLoad.setPhone(PhoneNo);
	
		Response response= UserEndPoint.CreateUser(userPayLoad);		 
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK");
			
		
	}

	@Test(priority = 2, dataProvider="UserNames",dataProviderClass = DataProviders.class)
	public void testReadUserByUserName(String UserName)
	{
		Response response= UserEndPoint.ReadUser(UserName);		 
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK");

	}
	
	
}
