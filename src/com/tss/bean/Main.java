package com.tss.bean;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			File file = null;
			JAXBContext jaxbContext = null;
			RootNode rootNode = null;
			/*
			 * created beans and create xml
			 */
			rootNode = fillBeans();
			file = new File("D:\\file.xml");

			jaxbContext = JAXBContext.newInstance(RootNode.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			// output pretty printed
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

			jaxbMarshaller.marshal(rootNode, file);
			jaxbMarshaller.marshal(rootNode, System.out);
			
			/*
			 * Load xml and load beans
			 */
			 file = new File("D:\\file.xml");
			 jaxbContext = JAXBContext.newInstance(RootNode.class);
	 
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			 rootNode = (RootNode) jaxbUnmarshaller.unmarshal(file);
			//System.out.println(rootNode.getCityList().get(1).getGroupList().get(1).getGroupName());
			JSONObject routNodeJSON = new JSONObject(rootNode);
			JSONObject jsonObjectGroup = null;
			
			
			
			System.out.println(routNodeJSON);
			JSONArray jsonArrayCity, jsonArrayGroup;
			try {
					jsonArrayCity = routNodeJSON.getJSONArray("cityList");
					for(int i=0;i< jsonArrayCity.length();i++){
						System.out.println(jsonArrayCity.get(i));
						jsonObjectGroup = jsonArrayCity.getJSONObject(i);
						jsonArrayGroup = jsonObjectGroup.getJSONArray("groupList");
						for(int j=0;j<jsonArrayGroup.length();j++){
							System.out.println(jsonArrayGroup.get(j));
						}
					}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
			

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	private static RootNode fillBeans() {
		RootNode rootNode = new RootNode();
		Group group = null;
		Task task= null;
		Execution exe = null;
		List<City> cityList = new ArrayList<City>();
		List<Group> groupList = null;
		List<Task> taskList = new ArrayList<Task>();
		List<Execution> exeList = new ArrayList<Execution>();
		
		groupList = new ArrayList<Group>();
		City city = new City(1,"London");
		
		group = new Group(1,"Task Group 1",new Date());
		task = new Task(1,"Task 1", "o", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		
		exeList = new ArrayList<Execution>();
		task = new Task(2,"Task 2", "f", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "f", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		
		exeList = new ArrayList<Execution>();
		task = new Task(3,"Task 3", "r", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "r", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		
		group.setTaskList(taskList);
		groupList.add(group);
		
		
		
		
		group = new Group(2,"Task Group 2",new Date());
		taskList = new ArrayList<Task>();
		exeList = new ArrayList<Execution>();
		task = new Task(1,"Task 1", "o", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		
		exeList = new ArrayList<Execution>();
		task = new Task(2,"Task 2", "o", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		
		exeList = new ArrayList<Execution>();
		task = new Task(3,"Task 3", "o", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		group.setTaskList(taskList);
		groupList.add(group);
		
		group = new Group(3,"Task Group 3",new Date());
		exeList = new ArrayList<Execution>();
		taskList = new ArrayList<Task>();
		task = new Task(1,"Task 1", "o", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		
		exeList = new ArrayList<Execution>();
		task = new Task(2,"Task 2", "o", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		
		exeList = new ArrayList<Execution>();
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		
		exeList = new ArrayList<Execution>();
		task = new Task(3,"Task 3", "o", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		group.setTaskList(taskList);
		groupList.add(group);
		
		group = new Group(4,"Task Group 4",new Date());
		exeList = new ArrayList<Execution>();
		taskList = new ArrayList<Task>();
		task = new Task(1,"Task 1", "o", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		
		exeList = new ArrayList<Execution>();
		task = new Task(2,"Task 2", "o", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		
		exeList = new ArrayList<Execution>();
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		
		exeList = new ArrayList<Execution>();
		task = new Task(3,"Task 3", "o", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		group.setTaskList(taskList);
		groupList.add(group);
		
		city.setGroupList(groupList);
		cityList.add(city);
		
		groupList = new ArrayList<Group>();
		city = new City(2,"NY");
		
		group = new Group(1,"NY Tasks 1",new Date());
		exeList = new ArrayList<Execution>();
		taskList = new ArrayList<Task>();
		task = new Task(1,"Task 1", "o", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		
		exeList = new ArrayList<Execution>();
		task = new Task(2,"Task 2", "o", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		
		exeList = new ArrayList<Execution>();
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		
		exeList = new ArrayList<Execution>();
		task = new Task(3,"Task 3", "o", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		group.setTaskList(taskList);
		groupList.add(group);
		
		group = new Group(2,"Group 2",new Date());
		exeList = new ArrayList<Execution>();
		taskList = new ArrayList<Task>();
		task = new Task(1,"Task 1", "o", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		
		exeList = new ArrayList<Execution>();
		task = new Task(2,"Task 2", "o", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		
		exeList = new ArrayList<Execution>();
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		
		exeList = new ArrayList<Execution>();
		task = new Task(3,"Task 3", "o", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		group.setTaskList(taskList);
		groupList.add(group);
		
		group = new Group(3,"AAA",new Date());
		exeList = new ArrayList<Execution>();
		taskList = new ArrayList<Task>();
		task = new Task(1,"Task 1", "o", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		
		exeList = new ArrayList<Execution>();
		task = new Task(2,"Task 2", "o", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		
		exeList = new ArrayList<Execution>();
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		
		exeList = new ArrayList<Execution>();
		task = new Task(3,"Task 3", "o", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		group.setTaskList(taskList);
		groupList.add(group);
		
		group = new Group(4,"BBB",new Date());
		exeList = new ArrayList<Execution>();
		taskList = new ArrayList<Task>();
		task = new Task(1,"Task 1", "o", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		
		exeList = new ArrayList<Execution>();
		task = new Task(2,"Task 2", "o", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		
		exeList = new ArrayList<Execution>();
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		
		exeList = new ArrayList<Execution>();
		task = new Task(3,"Task 3", "o", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		group.setTaskList(taskList);
		groupList.add(group);
		
		city.setGroupList(groupList);
		cityList.add(city);
		
		groupList = new ArrayList<Group>();
		city = new City(3, "Boston");
		
		group = new Group(1,"AA",new Date());
		exeList = new ArrayList<Execution>();
		taskList = new ArrayList<Task>();
		task = new Task(1,"Task 1", "o", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		
		exeList = new ArrayList<Execution>();
		task = new Task(2,"Task 2", "o", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		
		exeList = new ArrayList<Execution>();
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		
		exeList = new ArrayList<Execution>();
		task = new Task(3,"Task 3", "o", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		group.setTaskList(taskList);
		groupList.add(group);
		
		group = new Group(2,"AAA",new Date());
		exeList = new ArrayList<Execution>();
		taskList = new ArrayList<Task>();
		task = new Task(1,"Task 1", "o", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		
		exeList = new ArrayList<Execution>();
		task = new Task(2,"Task 2", "o", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		
		exeList = new ArrayList<Execution>();
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		
		exeList = new ArrayList<Execution>();
		task = new Task(3,"Task 3", "o", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		group.setTaskList(taskList);
		groupList.add(group);
		
		group = new Group(3,"Tasks Group 2",new Date());
		exeList = new ArrayList<Execution>();
		taskList = new ArrayList<Task>();
		task = new Task(1,"Task 1", "o", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		
		exeList = new ArrayList<Execution>();
		task = new Task(2,"Task 2", "o", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		
		exeList = new ArrayList<Execution>();
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		
		exeList = new ArrayList<Execution>();
		task = new Task(3,"Task 3", "o", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		group.setTaskList(taskList);
		groupList.add(group);
		
		group = new Group(4,"AAAA",new Date());
		exeList = new ArrayList<Execution>();
		taskList = new ArrayList<Task>();
		task = new Task(1,"Task 1", "o", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		
		exeList = new ArrayList<Execution>();
		task = new Task(2,"Task 2", "o", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		
		exeList = new ArrayList<Execution>();
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		
		exeList = new ArrayList<Execution>();
		task = new Task(3,"Task 3", "o", "All task Done");
		exe = new Execution(1,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(2,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		exe = new Execution(3,new Date(), "o", "All Execution Done");
		exeList.add(exe);
		task.setExeList(exeList);
		taskList.add(task);
		group.setTaskList(taskList);
		groupList.add(group);
		
		city.setGroupList(groupList);
		cityList.add(city);
		
		rootNode.setCityList(cityList);
		return rootNode;
	}
}