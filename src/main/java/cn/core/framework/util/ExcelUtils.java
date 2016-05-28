package cn.core.framework.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**   
* poi 读取excel 支持2003 --2007 及以上文件
* @author tanzhuo   
* @date 2015年9月29日   
*/

public class ExcelUtils {
	
	/**
	 * 合并方法，读取excel文件
	 * 根据文件名自动识别读取方式
	 * 支持97-2013格式的excel文档
	 * @param path 路径
	 * @param fileName 文件名
	 * @return 返回列表内容格式：
	 *  每一行数据都是以对应列的表头为key 内容为value 比如 excel表格为：
	 * ===============
	 *  A | B | C | D
	 * ===|===|===|===
	 *  1 | 2 | 3 | 4
	 * ---|---|---|--- 
	 *  a | b | c | d
	 * ---------------
	 * 返回值 map：
	 *   map1:   A:1 B:2 C:3 D:4
	 *   map2:   A:a B:b C:d D:d
	 * @throws java.io.IOException
	 */
	public static List<Map<String,Object>> readExcel(String path,String fileName) throws Exception{
		//准备返回值列表
		List<Map<String, Object>> valueList=new ArrayList<Map<String,Object>>();
        String ExtensionName=getExtensionName(fileName);
        
		String filePath = path+fileName;

		if(ExtensionName.equalsIgnoreCase("xls")){
			valueList=readExcel2003(filePath);
		}else if(ExtensionName.equalsIgnoreCase("xlsx")) {
			valueList=readExcel2007(filePath);
		}
        return valueList;
	}
	
	/**
	 * 读取97-2003格式
	 * @param filePath 文件路径
	 * @throws java.io.IOException
	 */
	public static List<Map<String,Object>> readExcel2003(String filePath) throws IOException{
		//返回结果集
		List<Map<String,Object>> valueList=new ArrayList<Map<String,Object>>();
        FileInputStream fis=null;
        HSSFWorkbook wookbook=null;
		try {
            fis=new FileInputStream(filePath);
			wookbook = new HSSFWorkbook(fis);	// 创建对Excel工作簿文件的引用
			HSSFSheet sheet = wookbook.getSheetAt(0);	// 在Excel文档中，第一张工作表的缺省索引是0
			int rows = sheet.getPhysicalNumberOfRows();	// 获取到Excel文件中的所有行数­
			Map<Integer,String> keys=new HashMap<Integer, String>();
			int cells=0;
			// 遍历行­（第1行  表头） 准备Map里的key
			HSSFRow firstRow = sheet.getRow(0);
			if (firstRow != null) {
				// 获取到Excel文件中的所有的列
				cells = firstRow.getPhysicalNumberOfCells();
				// 遍历列
				for (int j = 0; j < cells; j++) {
					// 获取到列的值­
					try {
						HSSFCell cell = firstRow.getCell(j);
						String cellValue = getCellValue(cell);
						keys.put(j,cellValue);						
					} catch (Exception e) {
						e.printStackTrace();	
					}
				}
			}
			// 遍历行­（从第二行开始）
			for (int i = 1; i < rows; i++) {
				// 读取左上端单元格(从第二行开始)
				HSSFRow row = sheet.getRow(i);
				// 行不为空
				if (row != null) {
					//准备当前行 所储存值的map
					Map<String, Object> val=new HashMap<String, Object>();
					
					boolean isValidRow = false;
					
					// 遍历列
					for (int j = 0; j < cells; j++) {
						// 获取到列的值­
						try {
							HSSFCell cell = row.getCell(j);
							String cellValue = getCellValue(cell);
							val.put(keys.get(j),cellValue);	
							if(!isValidRow && cellValue!=null && cellValue.trim().length()>0){
								isValidRow = true;
							}
						} catch (Exception e) {
							e.printStackTrace();		
						}
					}
					//第I行所有的列数据读取完毕，放入valuelist
					if(isValidRow){
						valueList.add(val);
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
            fis.close();
            wookbook.close();
        }
        return valueList;
	}
	/**
	 * 读取2007-2013格式
	 * @param filePath 文件路径
	 * @return
	 * @throws java.io.IOException
	 */
	public static List<Map<String,Object>> readExcel2007(String filePath) throws IOException{
		List<Map<String,Object>> valueList=new ArrayList<Map<String,Object>>();
        FileInputStream fis =null;
        XSSFWorkbook xwb=null;
        try {
            fis =new FileInputStream(filePath);
            xwb = new XSSFWorkbook(fis);	// 构造 XSSFWorkbook 对象，strPath 传入文件路径
            XSSFSheet sheet = xwb.getSheetAt(0);			// 读取第一章表格内容
            // 定义 row、cell
            XSSFRow row;
            // 循环输出表格中的第一行内容   表头
            Map<Integer, String> keys=new HashMap<Integer, String>();
            row = sheet.getRow(0);
            if(row !=null){
                for (int j = row.getFirstCellNum(); j <=row.getPhysicalNumberOfCells(); j++) {
                    // 通过 row.getCell(j).toString() 获取单元格内容，
                    if(row.getCell(j)!=null){
                        if(!row.getCell(j).toString().isEmpty()){
                            keys.put(j, row.getCell(j).toString());
                        }
                    }else{
                        keys.put(j, "K-R1C"+j+"E");
                    }
                }
            }
            // 循环输出表格中的从第二行开始内容
            for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getPhysicalNumberOfRows(); i++) {
                row = sheet.getRow(i);
                if (row != null) {
                    boolean isValidRow = false;
                    Map<String, Object> val = new HashMap<String, Object>();
                    for (int j = row.getFirstCellNum(); j <= row.getPhysicalNumberOfCells(); j++) {
                        XSSFCell cell = row.getCell(j);
                        if (cell != null) {
                            String cellValue = null;
                            if(cell.getCellType()==XSSFCell.CELL_TYPE_NUMERIC){
                                if(DateUtil.isCellDateFormatted(cell)){
                                    cellValue = new DataFormatter().formatRawCellContents(cell.getNumericCellValue(), 0, "yyyy-MM-dd HH:mm:ss");
                                }
                                else{
                                    cellValue = String.valueOf(cell.getNumericCellValue());
                                }
                            }
                            else{
                                cellValue = cell.toString();
                            }
                            if(cellValue!=null&&cellValue.trim().length()<=0){
                                cellValue=null;
                            }
                            val.put(keys.get(j), cellValue);
                            if(!isValidRow && cellValue!= null && cellValue.trim().length()>0){
                                isValidRow = true;
                            }
                        }
                    }
                    // 第I行所有的列数据读取完毕，放入valuelist
                    if (isValidRow) {
                        valueList.add(val);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            fis.close();
            xwb.close();
        }
        return valueList;
	}
	
	/**
	 * 文件操作 获取文件扩展名
	 * 
	 * @param filename 文件名称包含扩展名
	 * @return
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}
	
	/**
	 * 解决excel类型问题，获得数值  
	 * 
	 * @param cell HSSFCell
	 * @return 列的值
	 */
	private static String getCellValue(HSSFCell cell) {
		String cellValue=null;
		if (cell == null)
			return null;
		switch (cell.getCellType()) {
			//数值型  
			case HSSFCell.CELL_TYPE_NUMERIC:
				if(HSSFDateUtil.isCellDateFormatted(cell)){
					//如果是date类型则 ，获取该cell的date值  
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					cellValue=sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue()));
					break;
				}else {// 纯数字  
					Double doubleValue = cell.getNumericCellValue();
	                cellValue = doubleValue.toString(); 
	                //解决1234.0  去掉后面的.0  
	                if(null!=cellValue&&!"".equals(cellValue.trim())){  
	                     String[] item = cellValue.split("[.]");  
	                     if(1<item.length&&"0".equals(item[1])){  
	                    	 cellValue=item[0];  
	                     }  
	                }  
	            }  
				break;
			case HSSFCell.CELL_TYPE_STRING:		
				//字符串类型   
				cellValue=String.valueOf(cell.getStringCellValue());
				break;
			case HSSFCell.CELL_TYPE_FORMULA:
				 //读公式计算值
				cellValue=String.valueOf(cell.getCellFormula());
				if (cellValue.equals("NaN")) {// 如果获取的数据值为非法值,则转换为获取字符串  
					cellValue = cell.getStringCellValue().toString();  
	            }  
				break;
			case HSSFCell.CELL_TYPE_BLANK:
				// 空值 
				cellValue=null;
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN:
				// 布尔类型  
				Boolean booleanValue=cell.getBooleanCellValue();
				cellValue=booleanValue.toString();
				break;
			case HSSFCell.CELL_TYPE_ERROR:
				// 故障  
				cellValue=String.valueOf(cell.getErrorCellValue());
				break;
		}
		if(cellValue!=null&&cellValue.trim().length()<=0){
			cellValue=null;
		}
		return cellValue;
	}
}
