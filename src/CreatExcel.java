import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.DocumentException;
import org.dom4j.Element;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;


public class CreatExcel {
	
	//测试用main函数
	/*public static void main(String args[]){
		try {
			creat(new ReadXml().Read(),strFileName);
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	*/
	
	//过滤html标签
	public static String filterHtml(String str) {

		Pattern pattern = Pattern.compile( "<([^>]*)>");

		Matcher matcher = pattern.matcher(str);

		StringBuffer sb = new StringBuffer();

		boolean result1 = matcher.find();

		while (result1) {

			matcher.appendReplacement(sb, "");

			result1 = matcher.find();

		}

		matcher.appendTail(sb);

		return sb.toString();

		}
	
	//生成excel文件
	public  void creat(List[] input,String strFileName) throws RowsExceededException, WriteException, DocumentException{
		try {
			
			String newFile = strFileName.substring(0, strFileName.length()-4);
			System.out.println("newfile"+newFile);
            WritableWorkbook book  =  Workbook.createWorkbook(new File(newFile+".xls" ));
            WritableSheet sheet  =  book.createSheet("第一页 ",0 );
             //生成标题
             //标题字体
            WritableFont font1 = new  WritableFont(WritableFont.TIMES, 12 ,WritableFont.BOLD); 
            WritableCellFormat format1 = new  WritableCellFormat(font1); 
             //设置标题
            String[] titles = {"CaseName","TestImportance","ExecutionType","Keywords","Summary","Preconditions","step_number","StepActions","ExpectedResults","Execution"};
            	for(int i=0;i<titles.length;i++){
            		Label label_i = new Label(i,0,titles[i],format1);    
            		sheet.addCell(label_i);
            	}
            	//填充内容，两层循环
            	for(int i=1;i<input.length;i++){
            		int j=0;
            		int k = 1;
        			System.out.println("i="+i);
        			
        			for(Iterator it = input[i].iterator();it.hasNext();){
        				
        				
        				Element ele=(Element)it.next();
        				
        				System.out.println(ele.getText());
        				
        				j+=1;
        				 
        				System.out.println("j="+j);
        				
        				//处理特殊字符
        				String content1 = filterHtml(ele.getText());
        				String content2 = content1.replace("&ldquo;", "“");
        				String content3 = content2.replace("&rdquo;", "”");
        				String content4 = content3.replace("&nbsp;", " ");
        				
        		//对步骤的处理
        		if(i==6||i==7||i==8){		
        			Label label = new Label(i,j,content4);
        			sheet.addCell(label);
        			}
        		//通过步骤分割其他
        		else{
        			int ti[] = new ReadXml().getTime(strFileName);
        			Label label = new Label(i,k,content4);
        			k = k+ti[j];
        			sheet.addCell(label);
        			}
        		
        			
        			}
        		}
            	
            	
            	//获取递增步长
            	//获取name
            	List li = new ReadXml().getName(strFileName);
            	
            	int ti[] = new ReadXml().getTime(strFileName);
            	int k = 1;
            	
    				for(int s =0;s<li.size();s++){
    					System.out.println("00000"+li.get(s));
    					Label label = new Label(0,k,(String)li.get(s));			  				
    					sheet.addCell(label);
    					System.out.println("-----------"+li.get(s)); 
    					System.out.println("********"+ti[s]);
    					k = k+ti[s+1];
    					
    			}
    			
    			
    			//获取关键字
    			List lis = new ReadXml().getKey(strFileName);
            	
    			for(int s =0;s<lis.size();s++){
    				System.out.println("00000"+lis.get(s));
    				Label label = new Label(3,s+1,(String) lis.get(s));
    				sheet.addCell(label);
    			}
             //  写入数据并关闭文件
            book.write();
            book.close();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
