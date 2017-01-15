package com.crunchify.tutorials;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
 
import org.json.JSONObject;
 
/**
 * @author Crunchify.com
 * 
 */
 
public class CrunchifyRESTServiceClient {
	public static void main(String[] args) {
		String string = "{"+
        "\"tutorials\": {"+
        "\"id\":\"Crunchify\","+
        "\"topic\": \"REST Service\","+
        "\"description\": \"This is REST Service Example by Crunchify.\""+
    "}"+
"}\";";
		try {
  
			JSONObject jsonObject = new JSONObject(string);
			System.out.println(jsonObject);
 
			// Step2: Now pass JSON File Data to REST Service
			try {
				URL url = new URL("http://localhost:8080/jersey/api/crunchifyService");
				URLConnection connection = url.openConnection();
				connection.setDoOutput(true);
				connection.setRequestProperty("Content-Type", "application/json");
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
				out.write(jsonObject.toString());
				out.close();
 
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                
				String result = "";
				String line;
				while ((line = in.readLine()) != null) {
					result += line + "\n";
				}
				System.out.println("\n"+result);
				System.out.println("\nCrunchify REST Service Invoked Successfully..");
				
				in.close();
			} catch (Exception e) {
				System.out.println("\nError while calling Crunchify REST Service");
				System.out.println(e);
			}
 

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
