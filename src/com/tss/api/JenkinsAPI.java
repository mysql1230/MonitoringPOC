package com.tss.api;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
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

	private static String apiURL = "http://localhost:8080/api/xml?depth=2&xpath=/*/job/.&wrapper=jenkins";
	private static String localXML = "D:\\develop\\workspace\\MonitoringPOC\\xml.xml";
	
	public static void main(String[] args) throws IOException, JAXBException {
		RootNode rootNode = fillRootNode();
		
		File file = new File("D:\\jobs.xml");
		JAXBContext jaxbContext = JAXBContext.newInstance(RootNode.class);
		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

		// output pretty printed
		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		jaxbMarshaller.marshal(rootNode, file);
		jaxbMarshaller.marshal(rootNode, System.out);
	}

	public static RootNode fillRootNode() {
		// get Jenkins job list
		List<Task> taskList = getTaskList(); 
		
		List<Group> groupList = new ArrayList<Group>();
		Group group = new Group(1,"Tasks Group 1",new Date());
		group.setTaskList(taskList);
		groupList.add(group);
		
		List<City> cityList = new ArrayList<City>();
		City city = new City(1,"London");
		city.setGroupList(groupList);
		cityList.add(city);
		
		RootNode rootNode = new RootNode();
		rootNode.setCityList(cityList);
		System.out.println(rootNode);
		System.out.println(cityList);
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
	
	private static List<Task> getTaskList() {
		DOMParser parser = new DOMParser(); 
		Document document = parser.parse(localXML); 
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
	            	  Date timeWhen = new Date();
	            	  
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
