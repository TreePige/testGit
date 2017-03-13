package com.rongdu.eloan.common.utils.upload;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import com.oreilly.servlet.multipart.FileRenamePolicy;

/**
 * 文件重命名策略类：为避免上传的文件出现重名和发生覆盖，上传后的文件都进行了重命名（带有时间戳）
 * @author 
 * @date 2014-10-29
 */
public class RenamePolicyCos implements FileRenamePolicy {

	public File rename(File uploadFile) {
		String newName = getNewFileName(uploadFile.getName());
		File renameFile = new File(uploadFile.getParent(), newName);
		return renameFile;
	}

	private String getNewFileName(String fileName) {
		StringBuffer newName = new StringBuffer();
		if (null != fileName && !"".equals(fileName)) {
			String type = "";
			String name = "";
			if (fileName.lastIndexOf(".") != -1) {
				type = fileName.substring(fileName.lastIndexOf("."));
				name = fileName.substring(0, fileName.lastIndexOf("."));
			}else{
				name = fileName;
			}
			newName.append(name);
			newName.append(getSuffix());
			newName.append(type);
		}
		return newName.toString();
	}
	
	
	private String getSuffix(){
		StringBuffer suffix = new StringBuffer("_");
		String now = new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss").format(new Date());
		suffix.append(now);
		suffix.append("_");
		Random random = new Random();
		String randomValue = String.valueOf(random.nextInt(1000));
		suffix.append(randomValue);
		return suffix.toString();
	}

//	public static void main(String[] args) {
//		RenamePolicyCos my = new RenamePolicyCos();
//		System.out.println(my.getNewFileName("log.txt"));
//	}

}
