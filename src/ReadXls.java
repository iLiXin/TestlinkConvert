import java.io.FileInputStream;  
import java.io.InputStream;  
import java.util.ArrayList;  
import java.util.List;  
import jxl.Cell;  
import jxl.Sheet;  
import jxl.Workbook;  
import jxl.WorkbookSettings;  
  
/** 
 * JxlĿǰֻ֧��Excel 2003�汾 
 * ֧��Excel�Ķ������� 
 */  
public class ReadXls {  
      
	
	/*public static void main(String args[]) throws Exception{
		readExcel("f://my.xls");
	}*/
 
    public static List<String> readExcel(String excelfilePath) throws Exception {  
        String data="";
        InputStream ins = new FileInputStream(excelfilePath); //��ȡxls�ļ�  
        WorkbookSettings setEncode = new WorkbookSettings(); //���ö��ļ�����  
        setEncode.setEncoding("GBK");  
        Workbook rwb = Workbook.getWorkbook(ins, setEncode);  
        List<String> alldata = new ArrayList<String>();  
        alldata.clear();  
        Sheet[] sheets=rwb.getSheets();//��õ�ǰExcel���м���sheet  
        int pages = sheets.length; //��ñ���  
          
        //��excel���е����ݶ�ȡ����  
        //�ڴ�Excel�ж�ȡ���ݵ�ʱ����Ҫ֪��ÿ��sheet�м��У����Ƕ�����  
        for(int i=0; i<pages; i++) {  
            Sheet sheet = rwb.getSheet(i);             
           
            //ѭ����ȡÿһ�е�ȫ������Ŀ������  
            int rows = sheet.getRows();		//��
            int cols = sheet.getColumns();  //��  
            	for(int r=1; r<rows; r++){
            		for(int c=0; c<cols; c++) {  //��ѭ��,Excel�������Ǵӣ�0��0����ʼ  
                                      
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