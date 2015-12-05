import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import javax.swing.table.JTableHeader.*;
public class registerWindows extends JFrame{
	public JPanel pp = new JPanel(new BorderLayout());
	private static final long serialVersionUID = 1L;
	private static String[] columnNames = {"Name","Number","Value"};
	private static String[][] tableValues = new String[35][3];
	private JPanel p = new JPanel(new BorderLayout());
	private static JTable table = new MyTable(tableValues,columnNames);
	private JScrollPane sp = new JScrollPane(table);
	private String[] name = {"$zero","$at","$v0","$v1","$a0","$a1","$a2","$a3",
	"$t0","$t1","$t2","$t3","$t4","$t5","$t6","$t7","$s0","$s1","$s2","$s3",
	"$s4","$s5","$s6","$s7","$t8","$t9","$k1","$k2","$gp","$sp","$fp","$ra",
	"pc","hi","lo"};
	public void CreateWindows(){}
	public registerWindows(){
		for(int i = 0;i < 35;i++){
			tableValues[i][0] = name[i];
			tableValues[i][1] = ""+i;
		}
		rshow();
		table.setFont(new Font("SimSun-ExtB", Font.PLAIN,14));
		table.setSelectionMode(0);//SINGLE_SELECTION
		p.add(sp,BorderLayout.CENTER);
		pp.add(p);
	}
	public static void rshow(){
		SHOWCON showR = new SHOWCON();
		for(int i=0;i<35;i++){
			String answer = showR.showRegister(i);
			String str = "0x";
			str = str+answer;
			tableValues[i][2] = str;
			table.setValueAt(str,i,2);
		}
	}
	public static void main(String args[]){
		new registerWindows().CreateWindows();
	}
}