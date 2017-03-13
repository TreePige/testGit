package com.rongdu.eloan.modules.common.utils;

import java.io.*;
import java.util.Collection;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by lsk on 2015/7/22.
 */
public class ZipUtil {
    public static void zipFiles(Collection<File> flist,OutputStream out){
        try {
            ZipOutputStream zout=new ZipOutputStream(new BufferedOutputStream(out));
            int len=0;
            byte[] buf=new byte[4096];
            for (File file : flist) {
                zout.putNextEntry(new ZipEntry(file.getName()));
                BufferedInputStream bin=new BufferedInputStream(new FileInputStream(file));
                while((len=bin.read(buf))>0){
                    zout.write(buf,0,len);
                }
                zout.closeEntry();
                bin.close();
            }
            zout.finish();
            zout.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
