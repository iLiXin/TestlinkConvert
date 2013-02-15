import java.io.FileInputStream;  
import java.io.InputStream;  
import java.util.ArrayList;  
import java.util.List;  
import jxl.Cell;  
import jxl.Sheet;  
import jxl.Workbook;  
import jxl.WorkbookSettings;  
  
/** 
 * Jxl目前只支持Excel 2003版本 
 * 支持Excel的多表读数据 
 */  
public class ReadXls {  
      
	
	/*public static void main(String args[]) throws Exception{
		readExcel("f://my.xls");
	}*/
 
    public static List<String> readExcel(String excelfilePath) throws Exception {  
        String data="";
        InputStream ins = new FileInputStream(excelfilePath); //读取xls文件  
        WorkbookSettings setEncode = new WorkbookSettings(); //设置读文件编码  
        setEncode.setEncoding("GBK");  
        Workbook rwb = Workbook.getWorkbook(ins, setEncode);  
        List<String> alldata = new ArrayList<String>();  
        alldata.clear();  
        Sheet[] sheets=rwb.getSheets();//获得当前Excel表共有几个sheet  
        int pages = sheets.length; //获得表数  
          
        //将excel表中的数据读取出来  
        //在从Excel中读取数据的时候不需要知道每个sheet有几行，有那多少列  
        for(int i=0; i<pages; i++) {  
            Sheet sheet = rwb.getSheet(i);             
           
            //循环读取每一行的全部列数目的内容  
            int rows = sheet.getRows();		//列
            int cols = sheet.getColumns();  //行  
            	for(int r=1; r<rows; r++){
            		for(int c=0; c<cols; c++) {  //行循环,Excel的行列是从（0，0）开始  
                                      
            			Cell excelRows = sheet.getCell(c, r);  
            			String strRow = excelRows.getContents();  
            			data+=(strRow+",");
                    
            		}
            		alldata.add(data);
            		data="";
            	}
              
        }  
                                ins.close();  

        System.out.println(alldata);

        return alldata;  
    } 
}