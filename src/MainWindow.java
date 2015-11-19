import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileOutputStream;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;


public class MainWindow extends JFrame {
	public void Window() {
		Assembler ass = new Assembler();
		
		setTitle("MIPS-汇编器");//设置标题
		setLayout(null);//取消布局管理器
		setBounds(0,0,200,150);//设定窗体位置大小
		java.awt.Container c = getContentPane();//创建容器对象
		
		Icon icon_new = new ImageIcon("Image/btn_new.png");
		final JButton b_new = new JButton(icon_new);//创建按钮
		Icon icon_build = new ImageIcon("Image/btn_build.png");
		final JButton b_build = new JButton(icon_build);
		Icon icon_save = new ImageIcon("Image/btn_save.png");
		final JButton b_save = new JButton(icon_save);
		Icon icon_open = new ImageIcon("Image/btn_open.png");
		final JButton b_open = new JButton(icon_open);
		
		b_new.setBounds(2,2,33,33);
		b_build.setBounds(37,2,33,33);
		b_save.setBounds(72,2,33,33);
		b_open.setBounds(107,2,33,33);
		
		b_save.setEnabled(false);
		b_build.setEnabled(false);
		b_new.setToolTipText("新建一个文件");
		b_build.setToolTipText("编译文件");
		b_save.setToolTipText("保存文件");
		b_open.setToolTipText("打开文件");
		
		final JTextArea jt = new JTextArea("",8,6);
		jt.setLineWrap(true);
		
		jt.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(jt.getText().length() != 0){
					b_build.setEnabled(true);
					b_save.setEnabled(true);
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		b_new.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				jt.setText("");
				jt.requestFocus();
			}
		});
		
		
		b_open.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				char ch[] = new char[10000];
				ch = ass.ReadFile();
				String str = String.valueOf(ch);
				jt.setText(str);
			}
		});
		
		
		b_save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				CreatFile();
				String s = jt.getText().replaceAll("\n", "\r\n");
				try{
					File file = new File("filetest.txt");
					FileWriter out = new FileWriter(file);
					out.write(s);
					out.close();
				}
				catch(Exception e1){
					e1.printStackTrace();
				}
				b_save.setEnabled(false);
				b_build.setEnabled(true);
			}

			private void CreatFile() {
				File file = new File("filetest.txt");
				if(file.exists()){
					file.delete();
					System.out.println("文件已删除");
				}
				try{
						file.createNewFile();
						System.out.println("文件已创建");
				}
				catch(Exception e){
						e.printStackTrace();
				}
			}
		});
		
		
		b_build.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				File file = new File("filetest.txt");
				try{
					FileReader fileReader = new FileReader(file);
					BufferedReader bufferedReader = new BufferedReader(fileReader);
					char cha[] = new char[10000];
					int len = bufferedReader.read(cha);
					Assembler.assembler(cha);
					bufferedReader.close();
					fileReader.close();
				}
				catch(Exception e2){
					e2.printStackTrace();
				}
			}
		});
		
		
		
		jt.setBounds(2,37,500,500);
		c.add(b_new);
		c.add(b_build);
		c.add(b_save);
		c.add(b_open);
		c.add(jt);
		setVisible(true);//窗口可见
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
