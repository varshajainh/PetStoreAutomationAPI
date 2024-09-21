package endpoints;

public class Routes {
	
/* List of all the urls for every module
 * 
 * 	
 */
	public static String base_url="https://petstore.swagger.io/v2";

	//User Module
	/* Create user(post): https://petstore.swagger.io/v2/user
	 * Get User(get):https://petstore.swagger.io/v2/user/{username}
	 * Update User(put):https://petstore.swagger.io/v2/user/{username}
	 * Delete User(delete): https://petstore.swagger.io/v2/user/{username}
	 * 
	 */
	
	public static String post_URL= base_url+"/user";
	public static String get_URL= base_url+"/user/{username}";
	public static String put_URL= base_url+"/user/{username}";
	public static String delete_URL= base_url+"/user/{username}";
}
