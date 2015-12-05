import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
public class consoleWindow extends JFrame{
	private static final long serialVersionUID = 1L;
	public JPanel pp = new JPanel(new BorderLayout());
	private JButton ru=new JButton();
	private JButton ro=new JButton();
	private JButton cl=new JButton();
	private static JTextArea jt = new JTextArea();
	private JScrollPane sp = new JScrollPane(jt);
	public void CreateWindows(){}
	public consoleWindow(){
		URL urlcl = consoleWindow.class.getResource("cl.png");
		Icon iconcl = new ImageIcon(urlcl);
		cl.setIcon(iconcl);
		cl.setToolTipText("清除");
		cl.setOpaque(false);
		cl.setMargin(new Insets(0,0,0,0));
		cl.setEnabled(true);
		
		URL urlru = consoleWindow.class.getResource("run.png");
		Icon iconru = new ImageIcon(urlru);
		ru.setIcon(iconru);
		ru.setToolTipText("运行");
		ru.setOpaque(false);
		ru.setMargin(new Insets(0,0,0,0));
		ru.setEnabled(true);
		
		URL urlro = consoleWindow.class.getResource("runone.png");
		Icon iconro = new ImageIcon(urlro);
		ro.setIcon(iconro);
		ro.setToolTipText("运行一条");
		ro.setOpaque(false);
		ro.setMargin(new Insets(0,0,0,0));
		ro.setEnabled(true);
		
		jt.setEditable(false);
		JPanel p1 = new JPanel(new FlowLayout(0,1,0));
		JPanel p2 = new JPanel(new BorderLayout());
		p1.add(cl);
		p1.add(ru);
		p1.add(ro);
		p2.add(sp);
		pp.add(p1,BorderLayout.NORTH);
		pp.add(p2,BorderLayout.CENTER);
		cl.addActionListener(new clAction());
	}
	public static void show(int number){
		String str = jt.getText();
		str = str+number+"\n";
		jt.setText(str);
	}
	public static void show(String parameter){
		String str = jt.getText();
		str = str+parameter+"\n";
		jt.setText(str);
	}
	class clAction implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			jt.setText("");
		}
	}
	public static void main(String args[]){
		new consoleWindow().CreateWindows();
	}
}