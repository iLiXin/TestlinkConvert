import java.awt.Container;
import java.awt.Dialog;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.dom4j.DocumentException;

/**
 * 
 * 
 * @author 
 */
public class GUI extends JFrame{
	
	
	// �ļ�·��
	protected JTextField filename;

	// С����
	private JLabel jl1;
	private JLabel jl2;
	private JLabel jl3;

	// С��ť
	private JButton bu1;
	private JButton bu2;
	
	//��ѡ��ť
	private JRadioButton jb1;
	private JRadioButton jb2;
	private ButtonGroup bg;
	/*
	 * ���췽��
	 */
	public GUI() {
		// ���ô��ڱ���
		this.setTitle("TestlinkConvert");
		// ���������ʼ��
		init();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// ���ò��ַ�ʽΪ���Զ�λ
		this.setLayout(null);
		
		this.setBounds(0, 0, 355, 150);
		// ���ô���ı���ͼ��
		Image image = new ImageIcon("F:/logo.jpg").getImage();
		this.setIconImage(image);
		
		// �����С���ܸı�
		this.setResizable(false);
		
		// ������ʾ
		this.setLocationRelativeTo(null);

		// ����ɼ�
		this.setVisible(true);
	}

	/*
	 * ��ʼ������
	 */
	public void init() {
		// ����һ������
		final Container con = this.getContentPane();
		jl1 = new JLabel("File");
		jl1.setBounds(20, 40, 70, 20);
		
		jl2 = new JLabel("Choose file to Convert:");
		jl2.setBounds(15, 5, 300, 20);
		
		jl3 = new JLabel("Done !");
		jl3.setBounds(210, 40, 80, 20);
		jl3.setVisible(false);
		
		// �ļ�·�������
		filename = new JTextField();
		filename.setBounds(50, 40, 150, 20);
		
		//��ѡ��ť
		jb1 = new JRadioButton("xml->xls",true);
		jb1.setBounds(40, 75, 80, 20);
		jb2 = new JRadioButton("xls->xml");
		jb2.setBounds(120, 75, 80, 20);
		bg = new ButtonGroup();
		bg.add(jb1);
		bg.add(jb2);

		// ��ť�趨
		bu1 = new JButton("ѡ��");
		bu1.setBounds(250, 40, 80, 20);
		bu2 = new JButton("ת��");
		bu2.setBounds(250, 75, 80, 20);
		// ����ť����¼�

		change();
		
		// �������������װ��
		con.add(jb1);
		con.add(jb2);
		con.add(jl1);
		con.add(jl2);
		con.add(jl3);
		con.add(bu2);
		con.add(bu1);
		con.add(filename);

	}

	public static void main(String[] args) {
		// ʵ��������
		GUI qq = new GUI();
	}

	
	
	public void change(){
	
		bu1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				System.out.println("���ѡ��");
				JFileChooser chooser = new JFileChooser();
				if(jb1.isSelected()){
				FileNameExtensionFilter filter = new FileNameExtensionFilter("xml", "xml");
				chooser.setFileFilter(filter);
				}
				else
				{FileNameExtensionFilter filter = new FileNameExtensionFilter("xls", "xls");
				chooser.setFileFilter(filter);}
				
				int returnVal = chooser.showOpenDialog(new JPanel());
					if(returnVal == JFileChooser.APPROVE_OPTION){
						System.out.println("��򿪵��ļ��ǣ�"+chooser.getSelectedFile().getAbsolutePath());
						filename.setText(chooser.getSelectedFile().getAbsolutePath());
					}
			}
		});
		
		
		
		// ����ť����¼�
		bu2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(jb1.isSelected()){
				String path = filename.getText();
				try {
					new CreatExcel().creat(new ReadXml().Read(path), path);
					System.out.println("succeed");
					
					jl3.setVisible(true);
					JOptionPane.showMessageDialog(null, "��ϲ����ת���ɹ�");
					
				} catch (RowsExceededException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (WriteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (DocumentException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
			}
				else{
					String path = filename.getText();
					
					new CreatXml().create(path);
					System.out.println("succeed");
					
					jl3.setVisible(true);
					JOptionPane.showMessageDialog(null, "��ϲ����ת���ɹ�");
				}	
			}
		});
		}
		
	}


	
	

	