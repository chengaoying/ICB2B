package cn.core.framework.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;


/**
 * 统一异常处理
 * @author jc
 * @date 2015-8-1
 */
public class ExceptionResolver implements HandlerExceptionResolver{
	
	private static final Log log = LogFactory.getLog(ExceptionResolver.class);

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public ModelAndView resolveException(HttpServletRequest req,
			HttpServletResponse res, Object obj, Exception ex) {
		Map model = new HashMap();
		StringPrintWriter strintPrintWriter = new StringPrintWriter();  
        ex.printStackTrace(strintPrintWriter);  
        model.put("error", ex.getMessage());
        log.error(strintPrintWriter.getString());
        //log.error(ex.getMessage());
		return new ModelAndView("error",model);
	}
	
	/**
	 * 内部类：异常信息输出转换
	 * @author JC
	 * @date 2015-8-1
	 */
	private class StringPrintWriter extends PrintWriter{  
	  
	    public StringPrintWriter(){  
	        super(new StringWriter());  
	    }  
	     
	    public StringPrintWriter(int initialSize) {  
	          super(new StringWriter(initialSize));  
	    }  
	     
	    public String getString() {  
	          flush();  
	          return ((StringWriter) this.out).toString();  
	    }  
	     
	    @Override  
	    public String toString() {  
	        return getString();  
	    }  
	} 

}
