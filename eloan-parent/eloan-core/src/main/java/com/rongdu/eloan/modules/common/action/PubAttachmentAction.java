package com.rongdu.eloan.modules.common.action;

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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
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
import com.rongdu.eloan.modules.common.domain.PubAttachment;
import com.rongdu.eloan.modules.common.service.PubAttachmentService;
import com.rongdu.eloan.modules.common.utils.ZipUtil;

/**
 * 
 *	@Description 附件
 *  @author liyc,lyc1@erongdu.com
 *  @CreatTime 2016年7月12日 下午3:05:51
 *  @since version 1.0.0
 */
@Controller
@RequestMapping("/modules/common/PubAttachmentAction")
public class PubAttachmentAction extends BaseAction {

    private Map<String, Object> map = new HashMap<String, Object>();
    private Logger logger = LoggerFactory.getLogger(PubAttachmentAction.class);

    @Autowired
    private PubAttachmentService pubAttachmentService;

    List<PubAttachment> lp = null;

    /**
     * 
     * @description 附件上传带进度
     * @param request
     * @param response
     * @throws IOException
     * @author liyc
     * @return void
     * @since  1.0.0
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

    @SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	@RequestMapping(value = "/downloadZip.htm")
    public void downloadZip(
    		HttpServletRequest request, HttpServletResponse response, 
    		@RequestParam(value="search") String search,
			@RequestParam(value="fileExistCheck", defaultValue="false") boolean fileExistCheck
			) throws Exception {
    			Map<String, Object> param = JsonUtil.parse(search, Map.class);
    			param.put("fields", "file_name");
                List<PubAttachment> list = pubAttachmentService.select(param);
                String appDir=request.getRealPath("/");
                if(fileExistCheck){
                    List<File> notExists=new ArrayList<File>();
                    for (PubAttachment rec: list) {
                        File file=new File(appDir,rec.getFilePath());
                        if(!file.exists())notExists.add(file);
                    }

                    Map result=new HashMap();
                    result.put("success",list.size()>0 && notExists.size()==0);
                    result.put("fileSurvivalCnt",list.size()-notExists.size());
                    if(list.size()==0 || notExists.size()>0){
                        String info="共<span style='color:red'>"+list.size()+"</span>个文件，有<span style='color:red'>"+notExists.size()+"</span>个文件已被删除,";
                        info+=notExists.size()==list.size()?"没有文件可下载!":"是否确认下载?";

                        result.put(Constant.RESPONSE_CODE_MSG,info);
                    }else{
                        result.put(Constant.RESPONSE_CODE_MSG,"文件正常");
                    }
                    ServletUtils.writeToResponse(response, result);
                }else{
                    String name=new String(("附件.zip").getBytes("gbk"),"iso8859-1");
                    response.setHeader("Content-Disposition","attachment;filename="+name);

                    List<File> files=new ArrayList<File>();
                    for (PubAttachment rec : list) {
                        File file=new File(appDir,rec.getFilePath());
                        if(file.exists()){
                            files.add(file);
                        }
                    }
                    ZipUtil.zipFiles(files,response.getOutputStream());
                }
    }

    /**
     * 
     * @description 原/modules/common/Attachment/query3.htm
     * @param search
     * @param request
     * @param response
     * @throws Exception
     * @author liyc
     * @return void
     * @since  1.0.0
     */
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/queryAll.htm")
    public void query3(
    		@RequestParam(value="search") String search,
    		HttpServletRequest request, HttpServletResponse response
    		) throws Exception {
    	Map<String, Object> params = JsonUtil.parse(search, Map.class);
        List<PubAttachment> files = pubAttachmentService.select(params);
//        params.put("fields","file_name");
        Map<String, Object> res = new HashMap<String, Object>();
        res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        res.put(Constant.RESPONSE_DATA, files);
        ServletUtils.writeToResponse(response, res);
    }


    
    /**
     * 
     * @description 原/modules/common/Attachment/query.htm
     * @param currentPage
     * @param pageSize
     * @param response
     * @param request
     * @author liyc
     * @return void
     * @since  1.0.0
     */
    @SuppressWarnings({ "unchecked" })
	@RequestMapping("/query.htm")
    public void query(
    		@RequestParam(value="search") String search,
    		@RequestParam(required = false, defaultValue="1") Integer currentPage, 
    		@RequestParam(required = false, defaultValue="20") Integer pageSize, 
    		HttpServletResponse response, HttpServletRequest request) 
    throws Exception {
    	Map<String, Object> res = new HashMap<String, Object>();
        Map<String, Object> params = JsonUtil.parse(search, Map.class);
        params.put("fields","file_name");
//        PageBounds bounds = new PageBounds();
//        bounds.setPage(currentPage);
//        bounds.setLimit(pageSize);
        PageHelper.startPage(currentPage, pageSize);
        List<PubAttachment> list = pubAttachmentService.select(params);
        PageInfo<PubAttachment> page = new PageInfo<>(list);
        res.put(Constant.RESPONSE_DATA, page.getList());
        res.put(Constant.RESPONSE_DATA_TOTALCOUNT, page.getTotal());
        res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        res.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
        ServletUtils.writeToResponse(response, res);
    }

    /**
     * 
     * @description 原/modules/common/deletes3.htm 删除附件
     * @param request
     * @param response
     * @author liyc
     * @return void
     * @since  1.0.0
     */
    @SuppressWarnings({ "unchecked", "deprecation" })
	@RequestMapping(value = "/deletes.htm")
    public void deletes(
    		HttpServletRequest request, HttpServletResponse response,
    		@RequestParam(value="ids", required=true)String ids
    		) throws Exception{
    	Map<String, Object> res = new HashMap<String, Object>();
    	List<Integer> idList = JsonUtil.parse(ids, List.class);
    	List<Long> idL = new ArrayList<Long>();
    	for (Integer id : idList) {
    		idL.add(id.longValue());
		}
        pubAttachmentService.deletes(idL,new File(request.getRealPath("/")));
        res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        res.put(Constant.RESPONSE_CODE_MSG, Constant.OPERATION_SUCCESS);
        ServletUtils.writeToResponse(response, res);
    }

    /**
     * 
     * @description 原/modules/common/upload3.htm 附件上传
     * @param request
     * @param response
     * @author liyc
     * @return void
     * @since  1.0.0
     */
    @SuppressWarnings({ "unchecked", "deprecation" })
	@RequestMapping(value = "/upload.htm")
    public void upload3(final HttpServletRequest request, final HttpServletResponse response) throws Exception{
        //校验图片上传张数
    	Map<String, Object> params = new HashMap();   
    	// 设置上传文件的大小，超过这个大小 将抛出IOException异常，默认大小是1M。
        int inmaxPostSize = 200 * 1024 * 1024;
    	MultipartRequest multirequest = null;
        RenamePolicyCos myRenamePolicyCos = new RenamePolicyCos();
        HttpServletRequestProxy srp = new HttpServletRequestProxy(request);
        multirequest = new MultipartRequest(srp, getSaveDir(request).getAbsolutePath(), inmaxPostSize, "utf8", myRenamePolicyCos);
    	Map<String, Object> data=JSONObject.parseObject(multirequest.getParameter("data"),Map.class);
    	params.put("projectId", Long.valueOf(data.get("projectId").toString()));
    	params.put("processInstanceId", Long.valueOf(data.get("processInstanceId").toString()));
    	params.put("btype", data.get("btype").toString());
        List<PubAttachment> files = pubAttachmentService.select(params);
        if(files.size()==10)
        	throw new RuntimeException("上传附件数量超过限制");
        Enumeration<String> filedFileNames = multirequest.getFileNames();
        logger.info("        upload:");
        logger.info(JsonUtil.toString(multirequest));

        //返回数据
        Map<String, Object> result = new HashMap<String, Object>();

        if (null != filedFileNames && filedFileNames.hasMoreElements()) {
            String fieldName = filedFileNames.nextElement();

            File uploadFile = multirequest.getFile(fieldName);
            String uri= com.rongdu.eloan.common.utils.StringUtil.getRelativePath(uploadFile,new File(request.getRealPath("/")));

            

            PubAttachment attachment = new PubAttachment();
            attachment.setFilePath(uri);
            attachment.setFileName(multirequest.getOriginalFileName(fieldName));
            attachment.setNewfileName(uploadFile.getName());
            attachment.setFileSize(new BigDecimal(uploadFile.length()).divide(new BigDecimal(1024)).setScale(0, RoundingMode.CEILING).longValue());
            attachment.setOperatorId(getLoginUser(request).getId());
            attachment.setProcessInstanceId(Long.valueOf(data.get("processInstanceId").toString()));
            attachment.setProjectId(Long.valueOf(data.get("projectId").toString()));
            attachment.setBtype(data.get("btype").toString());
            Long id = pubAttachmentService.insert(attachment);

            result.put("id",attachment.getId());
            result.put("createtime",new Date());
            result.put("uri",uri);
        }else{
            throw new RuntimeException("没有文件可上传");
        }
        
        Map<String, Object> res = new HashMap<String, Object>();
        res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        res.put(Constant.RESPONSE_DATA, result);
        res.put("success", "true");
        ServletUtils.writeToResponse(response, res);
    }

    @SuppressWarnings("deprecation")
	private File getSaveDir(String dirName,HttpServletRequest request){
        if(dirName==null)dirName=new SimpleDateFormat("yyyy-MM").format(new Date());
        final File fileDir = new File(request.getRealPath("/FileUploadDir/"+dirName));
        if (!fileDir.exists()) {
            fileDir.mkdirs();
        }
        return fileDir;
    }

    private File getSaveDir(HttpServletRequest request){
        return getSaveDir(null,request);
    }

    @SuppressWarnings("deprecation")
	@RequestMapping("/download/isFileExists")
    public void isFileExists(
    		String filename, 
    		HttpServletRequest request, HttpServletResponse response
    		) throws Exception{
    	logger.info("        "+request.getRealPath("/"));
        Map<String, Object> res = new HashMap<String, Object>();
        String name=filename.replaceFirst("^/FileUploadDir","");
        name="/FileUploadDir/"+name;

        File file=new File(request.getRealPath("/"), name);
        boolean exists = file.exists();
        res.put(Constant.RESPONSE_CODE, Constant.SUCCEED_CODE_VALUE);
        res.put("exists", exists);
        if (!exists) {
        	res.put("filename",file.getName());
        	res.put(Constant.RESPONSE_CODE_MSG, "文件不存在:" + file.getName());
        }
        ServletUtils.writeToResponse(response, res);
    }

    @SuppressWarnings("deprecation")
	@RequestMapping("/download/{dir}/{fileName}")
    public void download(@PathVariable("dir") String dir, @PathVariable("fileName") String fileName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("text/html;charset=utf-8");
        request.setCharacterEncoding("UTF-8");
        java.io.BufferedInputStream bis = null;
        java.io.BufferedOutputStream bos = null;
        String separator = File.separator;

        String webRoot = request.getRealPath("/FileUploadDir").replaceAll(separator + "$", "");

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
            response.setHeader("Content-disposition", "attachment; filename=" + new String(fileName.getBytes("utf-8"), "ISO8859-1"));
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
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
    }

    /**
     * 根据流程ID获取某合同的答订成功上传的图片
     *
     * @param request
     * @param response
     * @version 1.0
     * @author 吴国成
     * @created 2015年3月3日
     */
    @SuppressWarnings("deprecation")
	@RequestMapping("/getContractPic.htm")
    public void queryContractPicByProcessInstanceId(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> param = new HashMap<String, Object>();
        Map<String, Object> res = new HashMap<String, Object>();
        long id = this.paramLong(request, "id");
        String flag = this.paramString(request, "flag");

        try {
            param.put("processInstanceId", id);
            param.put("btype", "Contract_Pic");
            if (null == lp || lp.size() == 0 || "initdata".equals(flag)) {
                lp = pubAttachmentService.select(param);

                for (int i = 0; i < lp.size(); i++) {
                    PubAttachment pubAttachment = lp.get(i);
                    String path = pubAttachment.getFilePath();
                    File file = new File(request.getRealPath("/") + "/" + path);
                    if (!file.exists()) {
                        //pubAttachment.setPath("/resources/img/s.gif");
                        lp.clear();
                        break;
                    }
                }
            }

            res.put("datas", lp);
            res.put("dataCount", lp.size());
            ServletUtils.writeToResponse(response, res);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

