package endpoints;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import payloads.User;

public class UserEndPoint {
	
/* To hold all the Create,Read,Update,Delete Requests to the User module
 * All the given() and when() part is available here for User module
 * 	
 */
	
	
	public static Response CreateUser(User payload)
	{
	Response response= given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.body(payload)
				  .when()
				  	.post(Routes.post_URL);
		
		return response;
	}
	
	public static Response ReadUser(String Username)
	{
	Response response= given()
						.pathParam("username", Username)
				  .when()
				  	.get(Routes.get_URL);
		
		return response;
	}

	public static Response UpdateUser(String Username,User payload)
	{
	Response response= given()
					.contentType(ContentType.JSON)
					.accept(ContentType.JSON)
					.body(payload)
					.pathParam("username", Username)
				  .when()
				  	.put(Routes.put_URL);
		
		return response;
	}
	
	public static Response DeleteUser(String Username)
	{
	Response response= given()
					.pathParam("username", Username)
				  .when()
				  	.put(Routes.delete_URL);
		
		return response;
	}
	
}
