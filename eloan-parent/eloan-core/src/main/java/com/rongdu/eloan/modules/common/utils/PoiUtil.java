package com.rongdu.eloan.modules.common.utils;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/2/3.
 */
public class PoiUtil {
    private HSSFWorkbook wb = new HSSFWorkbook();
    private Map<String,String> titleMap=new HashMap<String, String>();
    private Map<String,Render> renders=new HashMap<String, Render>();
    public interface Render{
        Object render(Object value);
    }
    public PoiUtil registerRender(String key,Render render){
        renders.put(key,render);
        return this;
    }
    public PoiUtil registerRender(Object[][] renders){
        this.renders=new HashMap<String, Render>();
        for(Object[] render:renders){
            this.renders.put((String)render[0],(Render)render[1]);
        }
        return this;
    }
    /**
     *
     * @param title     中文
     * @param name      字段
     * @return
     */
    public PoiUtil registerTitlaMap(String title,String name){
        titleMap.put(title,name);
        return this;
    }

    public PoiUtil registerTitlaMap(Object[][] titleNames){
        titleMap=new LinkedHashMap<String, String>();
        renders=new HashMap<String, Render>();
        for(Object[] o:titleNames){
            if(o.length==3 && o[2]!=null){
                renders.put((String)o[1],(Render)o[2]);
            };
            titleMap.put((String)o[0],(String)o[1]);
        }
        return this;
    }
    public PoiUtil resetTitleMap(Map<String,String> titleMap){
        this.titleMap=titleMap;
        return this;
    }
    public void writeXls(Map<String,List<Map<String,Object>>> sheets,OutputStream os){
        try {
            for(String sheetName:sheets.keySet()){
                writeXls(sheetName,sheets.get(sheetName),null);
            }
            wb.write(os);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void write(OutputStream os){
        try {
            wb.write(os);
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void writeXls(String sheetName,List<Map<String,Object>> recs){
        writeXls(sheetName,recs,null);
    }
    public void writeXls(String sheetName,List<Map<String,Object>> recs,OutputStream os){
        try {
            HSSFSheet sheet = sheetName==null?wb.createSheet():wb.createSheet(sheetName);
            HSSFRow row=null;

            row=sheet.createRow(0);

            int colIndex=0;
            for(String title:titleMap.keySet()){
                int col=colIndex++;
                row.createCell(col).setCellValue(title);
//                sheet.autoSizeColumn(col);
                sheet.setColumnWidth(col, 4000);
            }
            int rowIndex=1;
            for(Map<String,Object> rec:recs){
                row=sheet.createRow(rowIndex++);
                colIndex=0;
                for(String title:titleMap.keySet()){
                    String name=titleMap.get(title);
                    Object value=rec.get(name);
                    Render render=renders.get(name);
                    if(render!=null)value=render.render(value);
                    row.createCell(colIndex++).setCellValue(value == null ? null : value.toString());
                }
            }
            if(os!=null){
                wb.write(os);
                os.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
