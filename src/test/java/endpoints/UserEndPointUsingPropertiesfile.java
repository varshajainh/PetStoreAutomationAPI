package endpoints;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.User;

public class UserEndPointUsingPropertiesfile {
	
/* To hold all the Create,Read,Update,Delete Requests to the User module
 * All the given() and when() part is available here for User module
 * 	
 */
	//To load properties file in the system using java resource bundle
	static ResourceBundle getURL()
	{
		ResourceBundle routes=ResourceBundle.getBundle("routes");
		return routes;
	}
	
	
	public static Response CreateUser(User payload)
	{
		
		String post_URL=getURL().getString("post_URL");
	Response response= given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.body(payload)
				  .when()
				  	.post(post_URL);
		
		return response;
	}
	
	public static Response ReadUser(String Username)
	{
		String get_URL=getURL().getString("get_URL");
	Response response= given()
						.pathParam("username", Username)
				  .when()
				  	.get(get_URL);
		
		return response;
	}

	public static Response UpdateUser(String Username,User payload)
	{
		String put_URL=getURL().getString("put_URL");
	Response response= given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.body(payload)
					.pathParam("username", Username)
				  .when()
				  	.put(put_URL);
		
		return response;
	}
	
	public static Response DeleteUser(String Username)
	{
		
		String delete_URL=getURL().getString("delete_URL");
	Response response= given()
					.pathParam("username", Username)
				  .when()
				  	.put(delete_URL);
		
		return response;
	}
	
}
