package com.rongdu.eloan.common.utils;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class ApprovalResultsUtil {
	
	
	public static HashSet<Map<String, String>> getApprovalResults(String roleName){
		SAXReader reader = new SAXReader();
		File file = new File("doc/wdk/approvalResults.xml");
		Document document;
		HashSet<Map<String, String>> allPossibleComments = new HashSet<Map<String, String>>();
		try {
			document = reader.read(file);
			Element root = document.getRootElement();
			List<Element> childElements = root.elements();
			for (Element child : childElements) {
				if(roleName.equals(child.attributeValue("id"))){
					List<Element> childs = child.elements();
					for (Element ch : childs) {
						List<Element> childs_three = ch.elements();
						for (Element c : childs_three) {
							Map<String,String> results = new HashMap<String, String>();
							results.put("text",c.elementText("text"));
							results.put("value",c.elementText("value"));
							allPossibleComments.add(results);
						}
					}
				}else
					continue;
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return allPossibleComments;
	}
}
