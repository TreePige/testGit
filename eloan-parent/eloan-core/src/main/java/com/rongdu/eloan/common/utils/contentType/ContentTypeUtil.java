package com.rongdu.eloan.common.utils.contentType;

public class ContentTypeUtil {

	 public static String getcontentType(String pextFileName)  //获得文件扩展名，by gisoracle

	   {
	      String pext=pextFileName.toLowerCase();
	      if (pext.equals(".xls"))
	      {
	        return "application/vnd.ms-excel";
	      }
	      else if (pext.equals(".doc"))
	      {
	        return "application/msword";
	      }else if (pext.equals(".docx"))
	      {
	        return "application/vnd.ms-word.document.12";
	      }
	      else if (pext.equals(".txt"))
	      {
	        return "text/plain";
	      }
	      else if (pext.equals(".pdf"))
	      {
	        return "application/pdf";
	      }
	      else if (pext.equals(".jpg")||pext.equals(".jpeg"))
	      {
	        return "image/jpeg";
	      }
	      else if (pext.equals(".ppt"))
	      {
	        return "application/vnd.ms-powerpoint";
	      }
	      else if (pext.equals(".gif"))
	      {
	        return "image/gif";
	      }
	      return "text/html";
	    }
}
