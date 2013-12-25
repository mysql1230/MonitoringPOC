package com.tss.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tss.bean.City;
import com.tss.bean.Execution;
import com.tss.bean.Group;
import com.tss.bean.RootNode;
import com.tss.bean.Task;
import com.tss.helper.DataHelper;

/**
 * Servlet implementation class POCRequest
 */
public class APIRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public APIRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("text/javascript");
		RootNode rootNode = DataHelper.getRootNode();
		List<City> cityList = null;
		List<Group> groupList = null;
		int totalCity = 0;
		int totalGroup = 0;
		int maxGroupCount = 0;
		StringBuffer sbResponse = new StringBuffer();
		cityList = rootNode.getCityList();
		totalCity = cityList.size();
		String[][] listGroupMatrix = null;
		City city = null;
		Group group = null;
		int cityId = 0;
		int groupId = 0;
		int taskId = 0;
		int exeId = 0;
		for(int i=0;i<totalCity;i++ ){
			groupList = cityList.get(i).getGroupList();
			if(groupList.size()>maxGroupCount){
				//store size of maximum no of group 
				maxGroupCount = groupList.size();
			}
			
		}
		//maxGroupCount = 4;
		System.out.println("req=" + request.getParameter("req"));
		if(request.getParameter("req").equals("getGroupList")){
			listGroupMatrix = new String[maxGroupCount+1][totalCity];
			int ok=0,running=0,failed=0;
			List<Task> taskList = null;
			String taskStatus="";
			String taskColor="";
			for(int i=0;i<totalCity;i++ ){
				taskList = null;
				city = cityList.get(i);
				listGroupMatrix[0][i]=city.getCityName();
				groupList = city.getGroupList();
				taskStatus="";
				taskColor="";
				for(int j=0;j<maxGroupCount;j++ ){
					group = groupList.get(j);
					taskList = group.getTaskList();
					ok=0;failed=0;running=0;
					for(Task taskBean: taskList){
						if(taskBean.getStatus().equalsIgnoreCase("o")){
							ok++;
						} else if(taskBean.getStatus().equalsIgnoreCase("f")){
							failed++;
						} else if(taskBean.getStatus().equalsIgnoreCase("r")){
							running++;
						}
					}
					if(failed>0){
						taskColor="#FF3636";
					} else if (running>0){
						taskColor="#FFBD26";
					} else {
						taskColor="#A1FFA1";
					}
					listGroupMatrix[j+1][i]= ""+city.getCityId() + "," + group.getGroupId() + "," + group.getGroupName()+","+taskColor;
				}
			}
			for(int i=0;i<maxGroupCount+1;i++){
				if(sbResponse.length()>0){
					sbResponse.append("@");
				}
				for(int j=0;j<totalCity;j++){
					if(j==totalCity-1){
						sbResponse.append(listGroupMatrix[i][j]);
					}else{
						sbResponse.append(listGroupMatrix[i][j]+"|");
					}
				}
			}
			
			DataHelper.setListGroups(""+sbResponse);
			response.sendRedirect("listgroup.jsp");
		} else if(request.getParameter("req").equals("getTaskList")){
			
			String cityName = "";
			String groupName = "";
			List<Task> taskList = null;
			List<Execution> executionList = null;
			Task task = null;
			
			if(request.getParameter("cityId")!=null){
				cityId = Integer.parseInt(request.getParameter("cityId"));
			}	
			if(request.getParameter("groupId")!=null){
				groupId = Integer.parseInt(request.getParameter("groupId"));
			}
			System.out.println("cityId-" + cityId + ", groupId-"+ groupId);
			if(cityId!=0 && groupId!=0){
				
				cityList = rootNode.getCityList();
				city=getCity(cityList, cityId);
				cityName = city.getCityName();
				groupList = city.getGroupList();
				group = getGroup(groupList, groupId); 
				groupName = group.getGroupName();
				taskList = group.getTaskList();
				
				sbResponse.append(cityName+"|"+groupName+"|"+"Last Execution|All Executions");
				String exeStatus ="";
				String taskStatus ="";
				String exeColor ="";
				String taskColor = "";
				for(Task taskBean : taskList){
					taskId = taskBean.getTaskId();
					if(taskBean.getStatus().equalsIgnoreCase("o")){
						taskStatus="OK";
						taskColor="#A1FFA1";
					} else if(taskBean.getStatus().equalsIgnoreCase("f")){
						taskStatus="Failed";
						taskColor="#FF3636";
					} else if(taskBean.getStatus().equalsIgnoreCase("r")){
						taskStatus="Running";
						taskColor="#FFBD26";
					}
					sbResponse.append("@<a href='APIRequest?req=getExeList&cityId="+cityId+"&groupId="+groupId+"&taskId="+taskId+"'>"+taskBean.getTaskName() + "</a>|" + taskStatus + "|" + taskBean.getDescription());
					executionList = taskBean.getExeList();
					int i=0;
					sbResponse.append("|");
					
					for(Execution executionBean :executionList){
						exeId = executionBean.getExeId();
						//green - #A1FFA1
						//orange - #FFBD26
						//red - #FF3636
						if(executionBean.getStatus().equalsIgnoreCase("o")){
							exeStatus="OK";
							exeColor="#A1FFA1";
						} else if(executionBean.getStatus().equalsIgnoreCase("f")){
							exeStatus="Failed";
							exeColor="#FF3636";
						} else if(executionBean.getStatus().equalsIgnoreCase("r")){
							exeStatus="Running";
							exeColor="#FFBD26";
						}
						
							
						sbResponse.append("<a style='color:"+exeColor+"' href='APIRequest?req=getExeDetails&cityId="+cityId+"&groupId="+groupId+"&taskId="+taskId+"&exeId="+exeId+"'>"+executionBean.getDescription() +" "+ new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(executionBean.getTimeWhen()) +" "+ exeStatus +"</a>");
						if(i<1){
							sbResponse.append(",");
						} else {
							sbResponse.append(",<a style='color:#A1FFA1' href='APIRequest?req=getExeList&cityId="+cityId+"&groupId="+groupId+"&taskId="+taskId+"'>Show all</a>");
							break;
						}
						i++;
					}
					
				}
			}
			DataHelper.setListGroups(""+sbResponse);
			System.out.println(sbResponse);
			response.sendRedirect("listtask.jsp");
		} else if(request.getParameter("req").equals("getExeDetails")){
			if(request.getParameter("cityId")!=null){
				cityId = Integer.parseInt(request.getParameter("cityId"));
			}	
			if(request.getParameter("groupId")!=null){
				groupId = Integer.parseInt(request.getParameter("groupId"));
			}
			if(request.getParameter("taskId")!=null){
				taskId = Integer.parseInt(request.getParameter("taskId"));
			}
			if(request.getParameter("exeId")!=null){
				exeId = Integer.parseInt(request.getParameter("exeId"));
			}
			if(cityId!=0 && groupId!=0 && taskId!=0 && exeId!=0){
				System.out.println("cityId-"+cityId+", groupId-"+groupId+", taskId-"+taskId+", exeId-"+exeId);
				Execution exeBean = (Execution)getList(cityList, cityId, groupId, taskId, exeId);
				sbResponse.append("<th>Description</th><th>DateTime</th><th>Status</th><th>Sauce</th>");
				String exeStatus = null, exeColor = null;
				if(exeBean.getStatus().equalsIgnoreCase("o")){
					exeStatus="OK";
					exeColor="#A1FFA1";
				} else if(exeBean.getStatus().equalsIgnoreCase("f")){
					exeStatus="Failed";
					exeColor="#FF3636";
				} else if(exeBean.getStatus().equalsIgnoreCase("r")){
					exeStatus="Running";
					exeColor="#FFBD26";
				}
				sbResponse.append("@<td bgcolor='"+exeColor+"'>"+exeBean.getDescription()+"</td><td bgcolor='"+exeColor+"'>"+exeBean.getTimeWhen()+"</td><td bgcolor='"+exeColor+"'>"+exeStatus+"</td><td bgcolor='"+exeColor+"'><a href=SauceRequest?url="+exeBean.getUrl()+" >log</a></td>");
				DataHelper.setListGroups(""+sbResponse);
			}
			response.sendRedirect("listexecutiondetail.jsp");
		} else if(request.getParameter("req").equals("getExeList")){
			if(request.getParameter("cityId")!=null){
				cityId = Integer.parseInt(request.getParameter("cityId"));
			}	
			if(request.getParameter("groupId")!=null){
				groupId = Integer.parseInt(request.getParameter("groupId"));
			}
			if(request.getParameter("taskId")!=null){
				taskId = Integer.parseInt(request.getParameter("taskId"));
			}
			
			if(cityId!=0 && groupId!=0 && taskId!=0){
				System.out.println("cityId-"+cityId+", groupId-"+groupId+", taskId-"+taskId);
				Task task = (Task)getList(cityList, cityId, groupId, taskId);
				String taskName = task.getTaskName();
				List<Execution> exeList = task.getExeList();
				
				sbResponse.append("<th>"+taskName+"</th>");
				String exeColor = null;
				for(Execution exeBean: exeList){
					if(exeBean.getStatus().equalsIgnoreCase("o")){
						exeColor="#A1FFA1";
					} else if(exeBean.getStatus().equalsIgnoreCase("f")){
						exeColor="#FF3636";
					} else if(exeBean.getStatus().equalsIgnoreCase("r")){
						exeColor="#FFBD26";
					}
					sbResponse.append("@<td bgcolor='"+exeColor+"'><a href ='APIRequest?req=getExeDetails&cityId="+cityId+"&groupId="+groupId+"&taskId="+taskId+"&exeId="+exeBean.getExeId()+"'>"+exeBean.getDescription() + " " + exeBean.getTimeWhen() + "</a></td>");
				}
				DataHelper.setListGroups(""+sbResponse);
			}
			response.sendRedirect("listexecution.jsp");
		}
	}
	private Object getList(List<City> cityList, int cityId, int groupId){
		City city = getCity(cityList, cityId);
		return  getGroup(city.getGroupList(), groupId);
	}
	private Object getList(List<City> cityList, int cityId, int groupId, int taskId){
		Group group = (Group)getList(cityList, cityId, groupId);
		return getTask(group.getTaskList(), taskId);
	}
	private Object getList(List<City> cityList, int cityId, int groupId, int taskId, int exeId){
		Task task = (Task) getList(cityList, cityId, groupId, taskId);
		return getExecution(task.getExeList(), exeId);
	}
	private City getCity(List<City> cityList, int cityId){
		for(City cityBean : cityList){
			if(cityBean.getCityId()==cityId){
				return cityBean;
			}
		}
		return null;
	}
	private Group getGroup(List<Group> groupList, int groupId){
		for(Group groupBean:groupList){
			if(groupBean.getGroupId()==groupId){
				return groupBean;
			}
		}
		return null;
	}
	private Task getTask(List<Task> taskList, int taskId){
		for(Task taskBean:taskList){
			if(taskBean.getTaskId()==taskId){
				return taskBean;
			}
		}
		return null;
	}
	private Execution getExecution(List<Execution> exeList, int exeId){
		for(Execution exeBean:exeList){
			if(exeBean.getExeId()==exeId){
				return exeBean;
			}
		}
		return null;
	}
	
}