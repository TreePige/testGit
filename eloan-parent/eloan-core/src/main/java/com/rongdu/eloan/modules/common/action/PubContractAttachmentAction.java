package com.rongdu.eloan.modules.common.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oreilly.servlet.MultipartRequest;
import com.rongdu.eloan.common.context.Constant;
import com.rongdu.eloan.common.utils.JsonUtil;
import com.rongdu.eloan.common.utils.ServletUtils;
import com.rongdu.eloan.common.utils.upload.HttpServletRequestProxy;
import com.rongdu.eloan.common.utils.upload.ProgressUtil;
import com.rongdu.eloan.common.utils.upload.RenamePolicyCos;
import com.rongdu.eloan.common.web.action.BaseAction;
import com.rongdu.eloan.modules.common.domain.PubContractAttachment;
import com.rongdu.eloan.modules.common.service.PubContractAttachmentService;
import com.rongdu.eloan.modules.common.utils.ZipUtil;

/**
 * User: wulb DateTime:2016-08-29 10:50:06 details: 合同附件信息,Action请求层 source:
 * 代码生成器
 */
@RequestMapping("/modules/common/PubContractAttachmentAction")
@Controller
public class PubContractAttachmentAction extends BaseAction {
	private Logger logger = LoggerFactory.getLogger(PubContractAttachmentAction.class);
	/**
	 * 合同附件信息的Service
	 */
	@Autowired
	private PubContractAttachmentService pubContractAttachmentService;

	/**
	 * 分页查询数据
	 * 
	 * @param request
	 *            页面的request
	 * @param response
	 *            页面的response
	 * @param currentPage
	 *            当前页数
	 * @param pageSize
	 *            每页限制
	 * @param searchParams
	 *            查询条件
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/query.htm")
	public void query(HttpServletResponse response, HttpServletRequest request,
			@RequestParam(value = "currentPage") Integer currentPage,
			@RequestParam(value = "pageSize") Integer pageSize,
			@RequestParam(value = "searchParams", required = false) String searchParams) throws Exception {
		// 返回给页面的Map
		Map<String, Object> returnMap = new HashMap<String, Object>();
		Map<String, Object> paramap = new HashMap<>();
		// 对json对象进行转换
		if (!StringUtils.isEmpty(searchParams)) {
			paramap = JsonUtil.parse(searchParams, Map.class);
		}
		PageHelper.startPage(currentPage, pageSize);
		List<PubContractAttachment> pubContractAttachments = pubContractAttachmentService.getPageListByMap(paramap);
		PageInfo<PubContractAttachment> page = new PageInfo<PubContractAttachment>(pubContractAttachments);
		returnMap.put(Constant.RESPONSE_DATA, page.getList());
		returnMap.put(Constant.RESPONSE_DATA_TOTALCOUNT, page.getTotal());
		returnMap.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		returnMap.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		// 返回给页面
		ServletUtils.writeToResponse(response, returnMap);
	}

	/**
	 * 查询所有，不包含分页
	 * 
	 * @param search
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryAll.htm")
	public void query3(@RequestParam(value = "search") String search, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> params = JsonUtil.parse(search, Map.class);
		List<PubContractAttachment> files = pubContractAttachmentService.getPageListByMap(params);
		Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		res.put(Constant.RESPONSE_DATA, files);
		ServletUtils.writeToResponse(response, res);
	}

	/**
	 * 附件上传带进度
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value = "/progress.htm")
	public void getProgress(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String progressId = request.getParameter("progressId");
		Map<String, Object> responseMap = new HashMap<String, Object>();
		if (progressId == null || "".equals(progressId)) {
			responseMap.put("success", "false");
			responseMap.put("progress", "0");
			return;
		}
		String percent = "0";
		if (ProgressUtil.progressMap.get(progressId) == null) {
			logger.info("progressMethod 未查找到 progressId=" + progressId);
			ProgressUtil.progressMap.put(progressId, 0);
		} else {
			percent = String.valueOf(ProgressUtil.progressMap.get(progressId));
		}
		responseMap.put("success", "true");
		responseMap.put("progress", percent);
		com.rongdu.eloan.common.utils.ServletUtils.writeToResponse(response, responseMap);
	}

	@RequestMapping(value = "/getfilesize.htm")
	public void getFilesize(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Integer filesize = request.getContentLength();
		Map<String, Object> responseMap = new HashMap<String, Object>();
		responseMap.put("success", "true");
		responseMap.put("filesize", String.valueOf(filesize));
		com.rongdu.eloan.common.utils.ServletUtils.writeToResponse(response, responseMap);
	}

	/**
	 * 附件上传
	 * 
	 * @param request
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings({ "unchecked", "deprecation" })
	@RequestMapping(value = "/upload.htm")
	public void upload(final HttpServletRequest request, final HttpServletResponse response) throws Exception {
		// 设置上传文件的大小，超过这个大小 将抛出IOException异常，默认大小是1M。
		int inmaxPostSize = 200 * 1024 * 1024;
		MultipartRequest multirequest = null;
		RenamePolicyCos myRenamePolicyCos = new RenamePolicyCos();
		HttpServletRequestProxy srp = new HttpServletRequestProxy(request);
		multirequest = new MultipartRequest(srp, getSaveDir(request).getAbsolutePath(), inmaxPostSize, "utf8",
				myRenamePolicyCos);
		Enumeration<String> filedFileNames = multirequest.getFileNames();
		logger.info("        upload:");
		logger.info(JsonUtil.toString(multirequest));
		// 返回数据
		Map<String, Object> result = new HashMap<String, Object>();
		if (null != filedFileNames && filedFileNames.hasMoreElements()) {
			String fieldName = filedFileNames.nextElement();
			File uploadFile = multirequest.getFile(fieldName);
			String uri = com.rongdu.eloan.common.utils.StringUtil.getRelativePath(uploadFile,
					new File(request.getRealPath("/")));
			Map<String, Object> data = JSONObject.parseObject(multirequest.getParameter("data"), Map.class);
			PubContractAttachment attachment = new PubContractAttachment();
			attachment.setState(1);
			attachment.setFilePath(uri);
			attachment.setFileName(multirequest.getOriginalFileName(fieldName));
			attachment.setNewfileName(uploadFile.getName());
			attachment.setFileSize(new BigDecimal(uploadFile.length()).divide(new BigDecimal(1024))
					.setScale(0, RoundingMode.CEILING).longValue());
			attachment.setCreateTime(new Date());
			attachment.setCreator(this.getLoginUser(request).getId());
			attachment.setEffectiveNode(Long.valueOf(String.valueOf(data.get("effectiveNode"))));
			attachment.setName(String.valueOf(data.get("name")));
			attachment.setState(Long.valueOf(String.valueOf(data.get("state"))));
			pubContractAttachmentService.insert(attachment);
			result.put("id", attachment.getId());
			result.put("createtime", attachment.getCreateTime());
			result.put("uri", uri);
		} else {
			throw new RuntimeException("没有文件可上传");
		}
		Map<String, Object> res = new HashMap<String, Object>();
		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		res.put(Constant.RESPONSE_DATA, result);
		res.put("success", true);
		ServletUtils.writeToResponse(response, res);
	}

	@SuppressWarnings("deprecation")
	private File getSaveDir(String dirName, HttpServletRequest request) {
		if (dirName == null)
			dirName = new SimpleDateFormat("yyyy-MM").format(new Date());
		final File fileDir = new File(request.getRealPath("/Contract/" + dirName));
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		return fileDir;
	}

	private File getSaveDir(HttpServletRequest request) {
		return getSaveDir(null, request);
	}

	/**
	 * 附件批量删除
	 * 
	 * @param request
	 * @param response
	 * @param ids
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@RequestMapping(value = "/deletes.htm")
	public void deletes(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "ids[]", required = true) String ids[]) throws Exception {
		Map<String, Object> res = new HashMap<String, Object>();
		int successcount = 0;
		for (String id : ids) {
			long contractid = Long.parseLong(id);
			PubContractAttachment pubContractAttachment = pubContractAttachmentService.getItemInfoById(contractid);
			String path = pubContractAttachment.getFilePath();
			if (path != null) {
				File f = new File(new File(request.getRealPath("/")), path.toString());
				if (f.exists()) {
					f.delete();
				}
			}
			int count = pubContractAttachmentService.deleteById(contractid);
			if (count > 0) {
				successcount++;
			}
		}
		if (successcount == ids.length) {
			res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
		} else {
			res.put(Constant.RESPONSE_CODE, Constant.FAIL_CODE_VALUE);
			res.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_FAIL);
		}
		ServletUtils.writeToResponse(response, res);
	}

	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	@RequestMapping(value = "/downloadZip.htm")
	public void downloadZip(HttpServletRequest request, HttpServletResponse response,
			@RequestParam(value = "search") String search,
			@RequestParam(value = "fileExistCheck", defaultValue = "false") boolean fileExistCheck) throws Exception {
		Map<String, Object> param = JsonUtil.parse(search, Map.class);
		List<PubContractAttachment> list = pubContractAttachmentService.getPageListByMap(param);
		String appDir = request.getRealPath("/");
		if (fileExistCheck) {
			List<File> notExists = new ArrayList<File>();
			for (PubContractAttachment rec : list) {
				File file = new File(appDir, rec.getFilePath());
				if (!file.exists())
					notExists.add(file);
			}
			Map result = new HashMap();
			result.put("success", list.size() > 0 && notExists.size() == 0);
			result.put("fileSurvivalCnt", list.size() - notExists.size());
			if (list.size() == 0 || notExists.size() > 0) {
				String info = "共<span style='color:red'>" + list.size() + "</span>个文件，有<span style='color:red'>"
						+ notExists.size() + "</span>个文件已被删除,";
				info += notExists.size() == list.size() ? "没有文件可下载!" : "是否确认下载?";
				result.put(Constant.RESPONSE_CODE_MSG, info);
			} else {
				result.put(Constant.RESPONSE_CODE_MSG, "文件正常");
			}
			ServletUtils.writeToResponse(response, result);
		} else {
			String name = new String(("附件.zip").getBytes("gbk"), "iso8859-1");
			response.setHeader("Content-Disposition", "attachment;filename=" + name);
			List<File> files = new ArrayList<File>();
			for (PubContractAttachment rec : list) {
				File file = new File(appDir, rec.getFilePath());
				if (file.exists()) {
					files.add(file);
				}
			}
			ZipUtil.zipFiles(files, response.getOutputStream());
		}
	}

	@SuppressWarnings("deprecation")
	@RequestMapping("/download/isFileExists")
	public void isFileExists(String filename, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		logger.info("        " + request.getRealPath("/"));
		Map<String, Object> res = new HashMap<String, Object>();
		String name = filename.replaceFirst("^/Contract", "");
		name = "/Contract/" + name;
		File file = new File(request.getRealPath("/"), name);
		boolean exists = file.exists();
		res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
		res.put("exists", exists);
		if (!exists) {
			res.put("filename", file.getName());
			res.put(Constant.RESPONSE_CODE_MSG, "文件不存在:" + file.getName());
		}
		ServletUtils.writeToResponse(response, res);
	}

	@SuppressWarnings("deprecation")
	@RequestMapping("/download/{dir}/{fileName}")
	public void download(@PathVariable("dir") String dir, @PathVariable("fileName") String fileName,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;
		String separator = File.separator;
		String webRoot = request.getRealPath("/Contract").replaceAll(separator + "$", "");
		File downLoadFile = new File(webRoot + separator + dir + separator + fileName);
		if (!downLoadFile.exists()) {
			JSONObject json = new JSONObject();
			json.put("success", false);
			json.put("msg", "文件不存在:" + fileName);
			com.rongdu.eloan.common.utils.ServletUtils.writeToResponse(response, json);
		}
		try {
			long fileLength = downLoadFile.length();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-disposition",
					"attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(downLoadFile));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[4096];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null) {
				bis.close();
			}
		}
	}
}
