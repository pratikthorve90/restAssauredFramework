package restAssauredFramework;
import static io.restassured.RestAssured.given;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import Utilities.ReusableMethods;


public class TwitterAPI {
	
	
	Properties prop = new Properties();
	String ConsumerKey = null;
	String ConsumerSecret = null;
	String AccessToken = null;
	String TokenSecret = null;
	String baseURI = null;
	String tweetID = null;	


	@BeforeTest
	public void beforeTest() throws IOException
	{
		FileInputStream fis = new FileInputStream("C:\\Users\\x173479\\Desktop\\My stuff\\Selenium\\Final\\Code\\restAssauredFramework\\src\\main\\resources\\resources.properties");
		prop.load(fis);
		ConsumerKey 		= prop.getProperty("ConsumerKey");
		ConsumerSecret 		= prop.getProperty("ConsumerSecret");
		AccessToken 		= prop.getProperty("AccessToken");
		TokenSecret 		= prop.getProperty("TokenSecret");
		baseURI 			= prop.getProperty("TwitterURI");		
	}
	
	
	@Test
	public void agetTweet()
	{
		RestAssured.baseURI = baseURI;
		
		Response res = given().auth().oauth(ConsumerKey, ConsumerSecret, AccessToken, TokenSecret).
				
		when().
		get("/home_timeline.json").
		
		then().contentType(ContentType.JSON).and().
		extract().response();
		
		JsonPath js = ReusableMethods.rawtoJson(res);
		System.out.println(js.get("id") + "   : " + js.get("text"));
		
	}
	
	@Test
	public void bpostTweet()
	{
		RestAssured.baseURI = baseURI;
		
		Response res = given().auth().oauth(ConsumerKey, ConsumerSecret, AccessToken, TokenSecret).
		queryParam("status", "This is a Rest Tweet1").	
		
		when().
		post("/update.json").
		
		then().contentType(ContentType.JSON).
		extract().response();
		
		JsonPath js = ReusableMethods.rawtoJson(res);
		tweetID = js.getString("id");
	}
	
	@Test
	public void cdeleteTweet() throws InterruptedException	
	{
		
		Thread.sleep(5000);
		RestAssured.baseURI = baseURI;
		Response res = given().auth().oauth(ConsumerKey, ConsumerSecret, AccessToken, TokenSecret).
				
		when().
		post("/destroy/"+tweetID+".json").
		
		then().contentType(ContentType.JSON).
		extract().response();	

		
	}


}
