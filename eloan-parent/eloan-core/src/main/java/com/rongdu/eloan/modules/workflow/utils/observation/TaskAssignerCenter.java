package com.rongdu.eloan.modules.workflow.utils.observation;


public class TaskAssignerCenter{
	public static ThreadLocal<String> taskAssignee=new ThreadLocal<String>();
	public static boolean isNew = false;

    public static String processDefinitionId = "";
}
