package api.endpoints;

/* swagger url= https://petstore.swagger.io/
 * 
 * url=uniform resource locator
 * create user(post)=https://petstore.swagger.io/v2/user
 * Get User(Get)=https://petstore.swagger.io/v2/user/{username}
 * Post user(Put)=https://petstore.swagger.io/v2/user/{username}
 * Delete user(Delete)=https://petstore.swagger.io/v2/user/{username}
 */


public class Routes {
	/*common url for all the request,user is the module 
	it will change for other module */
	public static String base_url="https://petstore.swagger.io/v2/";
	
	//user module
	public static String post_url=base_url+ "user";
	public static String get_url=base_url+ "user/{username}";
	public static String put_url=base_url+ "user/{username}";
	public static String delete_url=base_url+ "user/{username}";
	
	//store module
	  //create store module url
	
	//pet module
	  //create pet module url

	
}
