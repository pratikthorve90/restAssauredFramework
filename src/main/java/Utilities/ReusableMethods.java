package Utilities;

import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

public class ReusableMethods {
	
	
	public static XmlPath rawtoXml(Response res)
	{
	
		String responseString = res.asString();
		XmlPath xml = new XmlPath(responseString);
		return xml;
		
	}
	
	public static JsonPath rawtoJson(Response res)
	{
	
		String responseString = res.asString();
		JsonPath js = new JsonPath(responseString);
		return js;
		
		
	}
	

}

