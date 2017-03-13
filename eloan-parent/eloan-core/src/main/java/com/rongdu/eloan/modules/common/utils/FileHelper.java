/**
 * @title FileHelper.java
 * @package com.rongdu.eloan.modules.common.util
 * @author 吴国成
 * @version V1.0
 * created 2014年10月30日
 */
package com.rongdu.eloan.modules.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.rongdu.eloan.common.utils.PdfFontProvider;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

/**
 * 生成文件辅助类
 * @version 1.0
 * @author 吴国成
 * @created 2014年10月30日 下午7:19:43
 */

public class FileHelper {
	private static final Logger log = LoggerFactory.getLogger(FileHelper.class);
	private static final String realpath = System.getProperty("user.dir");
	
	/**
	 * 根据模板文件创建HTML文件
	 * @param templateFilePath   模板文件路经  /src/main/webapp/WEB-INF/templates
	 * @param templateFileName   模板文件名
	 * @param htmlFilePath  html文件存放路经
	 * @param dataMap  需要替换的值
	 * @version 1.0
	 * @author 吴国成
	 * @created 2014年10月30日
	 */
	public static void createHtmlFile(String templateFilePath, String templateFileName, String htmlFilePath, Map<String,Object> dataMap){
		try {       
	        Configuration cfg = new Configuration();       
	        cfg.setDirectoryForTemplateLoading(new File(templateFilePath));  //加载freemarker模板文件       
	        cfg.setObjectWrapper(new DefaultObjectWrapper());   //设置对象包装器  
	       
	        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);   //设计异常处理器  
	               
	        Template template = cfg.getTemplate(templateFileName);  //获取指定模板文件 
	        	
	        File file = new File(htmlFilePath);
	        if(!file.getParentFile().exists()) file.getParentFile().mkdir();
	        
	        Writer out = new OutputStreamWriter(new FileOutputStream(htmlFilePath),"UTF-8");  //定义输入文件，默认生成在工程根目录  
	        
	        //生成 html文件 
	        template.process(dataMap, out);
	        out.flush();
	        out.close();
	        log.info("html文件生成完成");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void createHtmlFile(String templateFilePath, String templateFileName, OutputStream output, Map<String,Object> dataMap){
		try {       
	        Configuration cfg = new Configuration();       
	        cfg.setDirectoryForTemplateLoading(new File(templateFilePath));  //加载freemarker模板文件       
	        cfg.setObjectWrapper(new DefaultObjectWrapper());   //设置对象包装器  
	       
	        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);   //设计异常处理器  
	               
	        Template template = cfg.getTemplate(templateFileName);  //获取指定模板文件 
	        	
	        
	        Writer out = new OutputStreamWriter(output,"UTF-8");  //定义输入文件，默认生成在工程根目录  
	        
	        //生成 html文件 
	        template.process(dataMap, out);
	        out.flush();
	        out.close();
	        log.info("html文件生成完成");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建PDF文件
	 * @param htmlFile html文件路经
	 * @param pdfFile  pdf文件存放路经
	 * @version 1.0
	 * @author 吴国成
	 * @created 2014年10月30日
	 */
	public static void createPdfFile(String htmlFile,String pdfFile){         
    	try { 
    		/*
	        File file = new File(pdfFile);
	        if(!file.getParentFile().exists()) file.getParentFile().mkdir();
	        
            Document document = new Document(PageSize.A4, 30, 30, 30, 30);
            document.setMargins(50, 50, 30, 30);   // // 左，右，上，下       
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            writer.flush();
            document.open();
             
            XMLWorkerHelper wh = XMLWorkerHelper.getInstance();
            InputStream cssInput = null;
	        
            wh.parseXHtml(writer, document, new FileInputStream(htmlFile), cssInput,new PdfFontProvider());
              
            document.close();
            */
    		
	        File file = new File(pdfFile);
	        if(!file.getParentFile().exists()) file.getParentFile().mkdir();
	        
	     	     
	        Rectangle rect = new Rectangle(PageSize.A4);  // 设置页面大小  
	        rect.setBackgroundColor(BaseColor.WHITE);     // 页面背景色  	  
	       
	        
            Document document = new Document(rect);
            if(htmlFile.indexOf("costlist") >0 || htmlFile.indexOf("pledgelist")>0){//费用清单定宽处理
            	document.setMargins(30, 20, 20, 20);   // // 左，右，上，下
            }else{
            	document.setMargins(70, 70, 20, 30);   // // 左，右，上，下
            }

            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            writer.flush();
  
            document.open(); 
             
            XMLWorkerHelper wh = XMLWorkerHelper.getInstance();
            InputStream cssInput = null;
	        
            wh.parseXHtml(writer, document, new FileInputStream(htmlFile), cssInput,new PdfFontProvider());        
            
            document.close();
	        writer.close();	     
            log.info("pdf文件生成完成");    
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
