import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
import javax.swing.table.JTableHeader.*;
public class dataWindows extends JFrame{
	public JPanel pp = new JPanel(new BorderLayout());
	private static final long serialVersionUID = 1L;
	private JButton le=new JButton();
	private JButton ri=new JButton();
	private static String[] columnNames = {"Address","Value(+0)","Value(+4)","Value(+8)",
	"Value(+c)","Value(+10)","Value(+14)","Value(+18)","Value(+1c)"};
	private static String[][] tableValues = new String[16][9];
	private JPanel p1 = new JPanel(new BorderLayout());
	private JPanel p2 = new JPanel(new FlowLayout(1,1,0));
	private static JTable table = new MyTable(tableValues,columnNames);
	private JScrollPane sp = new JScrollPane(table);
	private static  int startCount = 0;
	private static String[] blockNumber = {"0x0000000","0x000000","0x00000","0x0000","0x000",
	"0x00","0x0","0x"};
	private static String tenToSixteen(int number){
		String str = Integer.toHexString(number);
		int len = str.length();
		str = blockNumber[len-1]+str;
		return str;
	}
	public void CreateWindows(){}
	public dataWindows(){
		URL urlle = dataWindows.class.getResource("left.png");
		Icon iconle = new ImageIcon(urlle);
		le.setIcon(iconle);
		le.setToolTipText("◊Û“∆º‡ ”øÈ");
		le.setOpaque(false);
		le.setMargin(new Insets(0,0,0,0));
		le.setEnabled(false);
		
		URL urlri = dataWindows.class.getResource("right.png");
		Icon iconri = new ImageIcon(urlri);
		ri.setIcon(iconri);
		ri.setToolTipText("”““∆º‡ ”øÈ");
		ri.setOpaque(false);
		ri.setMargin(new Insets(0,0,0,0));
		//000000
		//ffffff
		dshow();
		table.setFont(new Font("SimSun-ExtB", Font.PLAIN, 16));
		table.setSelectionMode(0);//SINGLE_SELECTION
		p1.add(sp,BorderLayout.CENTER);
		p2.add(le);
		p2.add(ri);
		le.addActionListener(new leAction());
		ri.addActionListener(new riAction());
		pp.add(p1,BorderLayout.CENTER);
		pp.add(p2,BorderLayout.SOUTH);
	}
	public static void dshow(){
		SHOWCON showR = new SHOWCON();
		for(int i=0;i<16;i++){
			for(int j = 0;j < 9;j++){
				String str = "";
				if(j==0){
					tableValues[i][0] = tenToSixteen(startCount+32*i);
					str = tableValues[i][0];
				}
				else{
					String answer = showR.showRAM(startCount+32*(8*i+j-1));
					str = "0x";
					str = str+answer;
					tableValues[i][j] = str;
				}
				table.setValueAt(str,i,j);
			}
		}
	}
	class riAction implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			le.setEnabled(true);
			startCount += 256;
			if(startCount==15872){
				ri.setEnabled(false);
			}
			dshow();
		}
	}
	class leAction implements ActionListener{
		public void actionPerformed(ActionEvent arg0){
			ri.setEnabled(true);
			startCount -= 256;
			if(startCount==0){
				le.setEnabled(false);
			}
			dshow();
		}
	}
	public static void main(String args[]){
		new registerWindows().CreateWindows();
	}
}