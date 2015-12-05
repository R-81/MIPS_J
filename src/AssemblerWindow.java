import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import javax.swing.event.*;
public class AssemblerWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	private static consoleWindow win = new consoleWindow();
	private static registerWindows rwin = new registerWindows();
	private static JFrame jf = new JFrame();
	private static dataWindows dwin = new dataWindows();
	public void CreateWindows(){
		final JTabbedPane tabbedPane = new JTabbedPane();
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.addTab("控制台",win.pp);
		tabbedPane.addTab("寄存器",rwin.pp);
		tabbedPane.addTab("内存",dwin.pp);
		tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				// 获得被选中选项卡的索引
				int selectedIndex = tabbedPane.getSelectedIndex();
				// 获得指定索引的选项卡标签
				if(selectedIndex==0){
					jf.setSize(400,375);
				}
				else if(selectedIndex==1){
					jf.setSize(400,645);
				}
				else if(selectedIndex==2){
					jf.setSize(850,375);
				}
			}
		});
		jf.setTitle("MIPS汇编器");
		jf.setLayout(new BorderLayout());
		JPanel p = new JPanel(new BorderLayout());
		p.add(tabbedPane,BorderLayout.CENTER);
		Container container = jf.getContentPane();
		container.add(BorderLayout.CENTER,p);
		container.setBackground(Color.white);
		jf.setVisible(true);
		jf.setSize(400,400);
		jf.setResizable(false);
		jf.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
	public static void show(int number){
		win.show(number);
	}
	public static void show(String parameter){
		win.show(parameter);
	}
	public static void rshow(){
		rwin.rshow();
	}
	public static void dshow(){
		dwin.dshow();
	}
	public static void main(String args[]){
		new AssemblerWindow().CreateWindows();
	}
}