package com.tss.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class JenkinsJob {

	public static void main(String[] args) {
		String jenkinsURL = "http://localhost:8080";
		String jobName = "test";
		String newJobName = "copiedJob";
		
		/*
		 * The API token is available in your personal configuration page. 
		 * Click your name on the top right corner on every page, then click "Configure" to see your API token. (The URL $root/me/configure is a good shortcut.) 
		 * You can also change your API token from here.
		 */
		String token = "8f984e03c543c053ca9c40702facd6e9";
		String username = "test";
		copyJob(jenkinsURL,jobName,newJobName,username,token);
	}
	
	public static String copyJob(String jenkinsURL, String jobName, String newJobName) { 
		return copyJob(jenkinsURL,jobName,newJobName,"","");
	}
	
	public static String copyJob(String jenkinsURL, String jobName, String newJobName, String username, String token) {  
		String url = jenkinsURL +"/createItem";
		String params = "name=" +newJobName+ "&mode=copy&from=" +jobName;
        String result = "";
        BufferedReader in = null;
        PrintWriter out = null;  
        try{
            java.net.URL connURL = new java.net.URL(url);  
            java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL.openConnection();  
            httpConn.setRequestProperty( "User-Agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.0)" );
            
            if (username != null && ! username.trim().equals("")){
	            String userPassword = username.trim() + ":" + token.trim();
	            String encoding = new sun.misc.BASE64Encoder().encode(userPassword.getBytes());
	            httpConn.setRequestProperty("Authorization", "Basic " + encoding); 
            }
            httpConn.setDoInput(true);  
            httpConn.setDoOutput(true);  
            out = new PrintWriter(httpConn.getOutputStream());  
            out.write(params);  
            out.flush();  
            httpConn.connect();
            if (httpConn.getResponseCode() == 200 ) {
            	in = new BufferedReader(new InputStreamReader(httpConn.getInputStream(), "UTF-8"));  
            }else{
        		in = new BufferedReader(new InputStreamReader(httpConn.getErrorStream(), "UTF-8"));  
        	}
            String line;  
            while ((line = in.readLine()) != null) {  
                result += line;  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (out != null) {  
                    out.close();  
                }  
                if (in != null) {  
                    in.close();  
                }  
            } catch (IOException ex) {  
                ex.printStackTrace();  
            }  
        }  
        return result;  
    }  

}
