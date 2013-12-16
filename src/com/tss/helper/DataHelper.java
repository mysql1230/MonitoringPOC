package com.tss.helper;
import java.io.File;
import java.net.URL;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.tss.bean.RootNode;


public class DataHelper {
	private static RootNode rootNode;
	private static String listGroups;
	private static void loadBeans(){
		try{
			//File file = new File("D:\\file.xml");
			URL url = new URL("http://techsharksolutions.com/file.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(RootNode.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			RootNode rootNode = (RootNode) jaxbUnmarshaller.unmarshal(url);
			DataHelper.setRootNode(rootNode);
			System.out.println(rootNode.getCityList().get(1).getGroupList().get(1).getGroupName());
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	public static void setRootNode(RootNode rootNode) {
		DataHelper.rootNode = rootNode;
	}
	public static RootNode getRootNode() {
		if(rootNode==null)
			loadBeans();
		return rootNode;
	}
	public static String getListGroups() {
		return listGroups;
	}
	public static void setListGroups(String listGroups) {
		DataHelper.listGroups = listGroups;
	}
}
