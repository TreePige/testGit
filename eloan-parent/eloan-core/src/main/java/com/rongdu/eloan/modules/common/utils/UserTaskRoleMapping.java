package com.rongdu.eloan.modules.common.utils;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserTaskRoleMapping {
	
	// TODO FHJ 现在只是信贷这边的mapping，缺少车贷，需要后续将信息整合起来。
	public static Map<String, List<String>> roleToTasksMapping = new HashMap<String, List<String>>();
	
	static {
		try {
			// 解析文件，生成document对象
			DocumentBuilder builder = DocumentBuilderFactory.newInstance()
					.newDocumentBuilder();
//			Document document = builder
//					.parse(new File(
//							"E:/Workspace1222/eloan/src/main/resources/config/bpm/eloan.partial.version.bpmn20.xml"));
//            Document document = builder.parse(new File("classpath: /config/bpm/eloan.partial.version.bpmn20.xml"));
//            URL fileUrl = UserTaskRoleMapping.class.getResource("/eloan.partial.version.bpmn20.xml");
            InputStream stream = UserTaskRoleMapping.class.getResourceAsStream("/config/bpm/eloan.partial.version.bpmn20.xml");

            Document document = builder.parse(stream);
            // 生成XPath对象
			XPath xpath = XPathFactory.newInstance().newXPath();

			// 获取节点值
			NodeList userTasks = (NodeList) xpath.evaluate(
					"/definitions/process/userTask", document,
					XPathConstants.NODESET);


			for (int i = 0; i < userTasks.getLength(); i++) {
				Node userTask = userTasks.item(i);

				String taskName = (String) xpath.evaluate("@id", userTask,
						XPathConstants.STRING);
				String roleName = (String) xpath.evaluate("documentation",
						userTask, XPathConstants.STRING);

				List<String> taskList = roleToTasksMapping.get(roleName);
				if (taskList == null) {
					taskList = new ArrayList<String>();
				}

				taskList.add(taskName);

				roleToTasksMapping.put(roleName, taskList);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
