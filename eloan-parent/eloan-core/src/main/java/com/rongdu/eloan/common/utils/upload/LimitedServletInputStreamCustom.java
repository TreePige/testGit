package com.rongdu.eloan.common.utils.upload;

import java.io.IOException;

import javax.servlet.ServletInputStream;

/**输入流包装扩展类：在原有输入流功能基础上，进行相应扩展*/
public class LimitedServletInputStreamCustom extends ServletInputStream {
  
  /** input stream we are filtering */
  private ServletInputStream in;
  
  /** number of bytes to read before giving up */
  private int totalExpected;
  
  /** number of bytes we have currently read */
  private int totalRead = 0;
  
  private int percentV = -1;
  private String progressId="DEFAULT_PROGRESS_ID";
  
  public LimitedServletInputStreamCustom(ServletInputStream in, int totalExpected) {
    this.in = in;
    this.totalExpected = totalExpected;
  }
  
  public LimitedServletInputStreamCustom(ServletInputStream in, int totalExpected,String progressId) {
	    this.in = in;
	    this.totalExpected = totalExpected;
	    this.progressId = progressId;
	  }

public  void setProgressPercent(String progressId,int totalRead,int totalExpected){
	  
	  percentV= (totalRead* 100)/totalExpected ;
	  
	  if(!ProgressUtil.progressMap.containsKey(progressId)){
		  ProgressUtil.progressMap.put(progressId, percentV);
		  System.out.println("init key:"+progressId+" percentV:"+percentV);
		  
	  }
	  if (percentV> ProgressUtil.progressMap.get(progressId)){		  
		  //update 
		  ProgressUtil.progressMap.put(progressId,percentV);
		  System.out.println("percentV:"+percentV);
	  }
  }
  
  public int ProgressComplete(String id){
	  return 0;
  }
  

  /**
   * Implement length limitation on top of the <code>read</code> method of 
   * the wrapped <code>ServletInputStream</code>.
   *
   * @return     the next byte of data, or <code>-1</code> if the end of the
   *             stream is reached.
   * @exception  IOException  if an I/O error occurs.
   */
  public int read() throws IOException {
    if (totalRead >= totalExpected) {
    	ProgressComplete(progressId);
      return -1;
    }

    int result = in.read();
    if (result != -1) {
      totalRead++;
      setProgressPercent(progressId,totalRead,totalExpected);
    }
    return result;
  }
  
  /**
   * Implement length limitation on top of the <code>read</code> method of 
   * the wrapped <code>ServletInputStream</code>.
   *
   * @param      b     destination buffer.
   * @param      off   offset at which to start storing bytes.
   * @param      len   maximum number of bytes to read.
   * @return     the number of bytes read, or <code>-1</code> if the end of
   *             the stream has been reached.
   * @exception  IOException  if an I/O error occurs.
   */
  public int read( byte b[], int off, int len ) throws IOException {
    int result, left = totalExpected - totalRead;
    if (left <= 0) {
    	ProgressComplete(progressId);
      return -1;
    } else {
      result = in.read(b, off, Math.min(left, len));
    }
    if (result > 0) {
      totalRead += result;
      setProgressPercent(progressId,totalRead+off,totalExpected); //totalRead+off
    }
    return result;    
  }
}
