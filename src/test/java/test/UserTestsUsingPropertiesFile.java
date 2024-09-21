package test;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import endpoints.UserEndPoint;
import endpoints.UserEndPointUsingPropertiesfile;
import io.restassured.response.Response;
import payloads.User;

/* Has all the validations for User modul
 * then() part is available here
 * 
 * 
 */
public class UserTestsUsingPropertiesFile {
	
	Faker faker;
	User userPayLoad;
	public Logger logger;
	
	@BeforeClass
	void setupData() {
		
		
		faker= new Faker();
		userPayLoad =new User();
		
		userPayLoad.setId(faker.idNumber().hashCode());
		userPayLoad.setUsername(faker.name().username());
		userPayLoad.setFirstName(faker.name().firstName());
		userPayLoad.setLastName(faker.name().lastName());
		userPayLoad.setEmail(faker.internet().safeEmailAddress());
		userPayLoad.setPassword(faker.internet().password(5, 10));
		userPayLoad.setPhone(faker.phoneNumber().cellPhone());
	
		logger=LogManager.getLogger(this.getClass());//log initiazation in before class
	}

	@Test(priority=1)
	public void testPostUser()
	{
		logger.info("********************Creating user************");
		
		Response response= UserEndPointUsingPropertiesfile.CreateUser(userPayLoad);		 
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK");
		logger.info("********************Created user************");	
		
	}
	
	@Test(priority=2, dependsOnMethods = {"testPostUser"})
	public void testGetUser()
	{
		logger.info("********************Getting user************");
		Response response= UserEndPointUsingPropertiesfile.ReadUser(this.userPayLoad.getUsername());		 
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK");
		Assert.assertEquals(response.body().jsonPath().getString("username"),this.userPayLoad.getUsername());
			
		
	}
	
	@Test(priority=3, dependsOnMethods = {"testPostUser"})
	public void testUpdateUser()
	{
		//update user
		logger.info("********************Updating user************");
		userPayLoad.setFirstName(faker.name().firstName());
		userPayLoad.setLastName(faker.name().lastName());
		userPayLoad.setEmail(faker.internet().safeEmailAddress());
		
		Response response= UserEndPointUsingPropertiesfile.UpdateUser(this.userPayLoad.getUsername(),userPayLoad);		 
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK");
	//	Assert.assertEquals(response.body().jsonPath().getString("username"),this.userPayLoad.getUsername());
		
		logger.info("********************To check if it is updated************");
		//To check if it is updated
		Response responseafterupdate= UserEndPointUsingPropertiesfile.ReadUser(this.userPayLoad.getUsername());		 
		
		Assert.assertEquals(responseafterupdate.getStatusCode(), 200);
		Assert.assertEquals(responseafterupdate.getStatusLine(),"HTTP/1.1 200 OK");
		Assert.assertEquals(responseafterupdate.body().jsonPath().getString("username"),this.userPayLoad.getUsername());
		
			
		
	}
	
	//@Test(priority=4,dependsOnMethods = {"testUpdateUser","testPostUser"})
	public void testDeleteUser()
	{
		Response response= UserEndPointUsingPropertiesfile.DeleteUser(this.userPayLoad.getUsername());		 
		
		Assert.assertEquals(response.getStatusCode(), 200);
		Assert.assertEquals(response.getStatusLine(),"HTTP/1.1 200 OK");

			
		
	}
}
