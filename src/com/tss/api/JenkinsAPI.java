package com.tss.api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.tss.bean.City;
import com.tss.bean.Execution;
import com.tss.bean.Group;
import com.tss.bean.RootNode;
import com.tss.bean.Task;

public class JenkinsAPI {

	public static void main(String[] args) throws IOException, JAXBException {
		String jenkinsURL = "http://localhost:8080";
		String jobXMLFileName = "D:\\file.xml";
		
		//Jenkins login username
		String username = "test";
		/*
		 * The API token is available in your personal configuration page. 
		 * Click your name on the top right corner on every page, then click "Configure" to see your API token. (The URL $root/me/configure is a good shortcut.) 
		 * You can also change your API token from here.
		 */
		String token = "8f984e03c543c053ca9c40702facd6e9";
		
		generateJobsXML(jenkinsURL,jobXMLFileName,username,token);
	}


	private static void generateJobsXML(String jenkinsURL, String jobXMLName, String username, String token) throws JAXBException, PropertyException, MalformedURLException, IOException {
		String apiTempXML = "api_temp.xml";
		getJenkinsInfo(jenkinsURL,apiTempXML,username,token);
		RootNode rootNode = fillRootNode(apiTempXML);
		new File(apiTempXML).delete();
		
		File file = new File(jobXMLName);
		JAXBContext jaxbContext = JAXBContext.newInstance(RootNode.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(rootNode, file);
		jaxbMarshaller.marshal(rootNode, System.out);
	}
	
	//no Jenkins Authentication
	private static void generateJobsXML(String jenkinsURL, String jobXMLName) throws JAXBException, PropertyException, MalformedURLException, IOException {
		generateJobsXML(jenkinsURL,jobXMLName,"","");
	}
	
	
	private static void getJenkinsInfo(String jenkinsURL, String outFile, String username, String token)
			throws MalformedURLException, IOException {
		String apiURL = jenkinsURL + "/api/xml?depth=2&xpath=/*/job/.&wrapper=jenkins";
		java.net.URL connURL = new java.net.URL(apiURL);  
        java.net.HttpURLConnection httpConn = (java.net.HttpURLConnection) connURL.openConnection();
        
        if (username != null && ! username.trim().equals("")){
            String userPassword = username.trim() + ":" + token.trim();
            String encoding = new sun.misc.BASE64Encoder().encode(userPassword.getBytes());
            httpConn.setRequestProperty("Authorization", "Basic " + encoding); 
        }
        httpConn.connect();
        BufferedReader reader = new BufferedReader(new InputStreamReader(
        		httpConn.getInputStream()));
        String lines;
        BufferedWriter output = new BufferedWriter(new FileWriter(outFile));
        while ((lines = reader.readLine()) != null) {
        	output.write(lines);
        }
        reader.close();
        output.close();
        httpConn.disconnect();
	}

	
	public static RootNode fillRootNode(String xmlFileName) {
		// get Jenkins job list
		List<Task> taskList = getTaskList(xmlFileName); 
		
		List<Group> groupList = new ArrayList<Group>();
		Group group = new Group(1,"Task Group 1",new Date());
		group.setTaskList(taskList);
		groupList.add(group);
		
		List<City> cityList = new ArrayList<City>();
		City city = new City(1,"London");
		city.setGroupList(groupList);
		cityList.add(city);
		
		RootNode rootNode = new RootNode();
		rootNode.setCityList(cityList);
		return rootNode;
	}
	
	
	public static class DOMParser { 
		   DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance(); 
		   //Load and parse XML file into DOM 
		   public Document parse(String filePath) { 
		      Document document = null; 
		      try { 
		         //DOM parser instance 
		         DocumentBuilder builder = builderFactory.newDocumentBuilder(); 
		         //parse an XML file into a DOM tree 
		         document = builder.parse(new File(filePath)); 
		      } catch (ParserConfigurationException e) { 
		         e.printStackTrace();  
		      } catch (SAXException e) { 
		         e.printStackTrace(); 
		      } catch (IOException e) { 
		         e.printStackTrace(); 
		      } 
		      return document; 
		   }
		}
	
	private static List<Task> getTaskList(String xml) {
		DOMParser parser = new DOMParser(); 
		Document document = parser.parse(xml); 
		//get root element 
        Element rootElement = document.getDocumentElement(); 

        List<Task> taskList = new ArrayList<Task>();
        NodeList nodeList = rootElement.getElementsByTagName("job"); 
        if(nodeList != null) { 
           for (int i = 0 ; i < nodeList.getLength(); i++) { 
              Element element = (Element)nodeList.item(i); 
              String taskName = element.getElementsByTagName("name").item(0).getTextContent(); 
              String concurrentBuild = element.getElementsByTagName("concurrentBuild").item(0).getTextContent(); 
              String jobDescription = concurrentBuild.equals("false")? "All Execution Done" : "Building";
              String jobStatus = element.getElementsByTagName("color").item(0).getTextContent(); 
              jobStatus = jobStatus.equals("red")? "f" : "o";
              
              Task task = new Task(i+1, taskName, jobStatus, jobDescription);
              List<Execution> exeList = new ArrayList<Execution>();
              NodeList jobList = element.getElementsByTagName("build");
              if (jobList != null){
	              for (int j = 0 ; j < jobList.getLength(); j++) {
	            	  Element job = (Element)jobList.item(j);
	            	  String exeId = job.getElementsByTagName("number").item(0).getTextContent();
	            	  
	            	  String building = job.getElementsByTagName("building").item(0).getTextContent();
	            	  String buildDescription = building.equals("false")? "All Execution Done" : "Building";
	            	  
	            	  String result = job.getElementsByTagName("result").item(0).getTextContent();
	            	  String buildStatus = result.equals("FAILURE")? "f" : "o";
	            	  
	            	  String id = job.getElementsByTagName("id").item(0).getTextContent();
	            	  Date timeWhen;
	            	  SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss"); 
	                  try {  
	                	  timeWhen = sdf.parse(id);  
	                  } catch (ParseException pe) {  
	                	  timeWhen = new Date();
	                	  System.out.println(pe.getMessage());
	                  }  
	            	  
	            	  Execution exe = new Execution(new Integer(exeId),timeWhen, buildStatus, buildDescription);
	            	  exeList.add(exe);
//	            	  System.out.println(exeId +"_" +buildStatus +"_" +timeWhen);
	              }
              }
              task.setExeList(exeList);
              taskList.add(task);
           } 
        }
        
        return taskList;
	}
	
	

}
