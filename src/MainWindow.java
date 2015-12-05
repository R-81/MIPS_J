
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import javax.swing.tree.DefaultTreeCellEditor.EditorContainer;

import com.oracle.webservices.internal.api.EnvelopeStyle.Style;


public class MainWindow extends JFrame {
	Assembler ass = new Assembler();
	String filepath = new String();
	
	private static Thread t;
	
	
	public void Window() {
		
		setTitle("MIPS-汇编器");//设置标题
		setLayout(null);//取消布局管理器
		setBounds(0,0,500,550);//设定窗体位置大小
		java.awt.Container c = getContentPane();//创建容器对象
		
		Icon icon_new = new ImageIcon("Image/btn_new.png");
		final JButton b_new = new JButton(icon_new);//创建按钮
		Icon icon_build = new ImageIcon("Image/btn_build.png");
		final JButton b_build = new JButton(icon_build);
		Icon icon_save = new ImageIcon("Image/btn_savec.png");
		final JButton b_save = new JButton(icon_save);
		Icon icon_open = new ImageIcon("Image/btn_open.png");
		final JButton b_open = new JButton(icon_open);
		Icon icon_savec = new ImageIcon("Image/btn_save.png");
		final JButton b_savec = new JButton(icon_savec);
		
		b_new.setBounds(2,2,33,33);
		b_build.setBounds(142,2,33,33);
		b_save.setBounds(107,2,33,33);
		b_savec.setBounds(72,2,33,33);
		b_open.setBounds(37,2,33,33);
		
		b_save.setEnabled(false);
		b_build.setEnabled(false);
		b_savec.setEnabled(false);
		
		b_new.setToolTipText("新建一个文件");
		b_build.setToolTipText("编译文件");
		b_savec.setToolTipText("保存当前文件");
		b_save.setToolTipText("保存文件为");
		b_open.setToolTipText("打开文件");
		
		
		final JTextPane jt = new JTextPane();
		
		
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
			public void actionPerformed(ActionEvent e) {
				JFileChooser open_frame = new JFileChooser();
				open_frame.setFileSelectionMode(JFileChooser.FILES_ONLY);
				open_frame.setMultiSelectionEnabled(true);
				FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT", "txt");
				open_frame.setFileFilter(filter);
				int value = open_frame.showOpenDialog(null);
				String temp = "";
				if(value == JFileChooser.APPROVE_OPTION){
					jt.setText("");
					File file = open_frame.getSelectedFile();
					try {
						FileReader in = new FileReader(file);
						BufferedReader bufferedReader = new BufferedReader(in);
						String num = new String();
						while((num = bufferedReader.readLine())!=null){
							temp = temp+num+'\n';
						}
						bufferedReader.close();
						}
					catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					finally{
						//System.out.println(temp);
						jt.setText(temp);
						jt.requestFocus();
						b_save.setEnabled(false);
						b_build.setEnabled(true);
						b_savec.setEnabled(true);
						filepath = file.getAbsolutePath();
					}
				}
			}
		});
		
		b_savec.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = jt.getText().replaceAll("\n", "\r\n");
				File file = new File(filepath);
				FileWriter out;
				try {
					out = new FileWriter(file);
					out.write(s);
					out.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		b_save.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser save_frame = new JFileChooser();
				save_frame.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES); 
				FileNameExtensionFilter filter = new FileNameExtensionFilter("TXT","txt");
				save_frame.setFileFilter(filter);
				int value = save_frame.showSaveDialog(save_frame);
				if(value == JFileChooser.APPROVE_OPTION){
					b_savec.setEnabled(true);
					File getPath = save_frame.getSelectedFile();
					if(getPath.exists()){
						int copy = JOptionPane.showConfirmDialog(null,"是否覆盖当前文件？","save",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
						if(copy == JOptionPane.YES_OPTION){
							save_frame.approveSelection();
							String f = getPath.getAbsolutePath();
							System.out.println("save"+f);
							String s = jt.getText().replaceAll("\n", "\r\n");
							try{
								File file = new File(f);
								FileWriter out = new FileWriter(file);
								out.write(s);
								out.close();
							}
							catch(Exception e1){
								e1.printStackTrace();
							}
							b_save.setEnabled(false);
							b_build.setEnabled(true);
							filepath = getPath.getAbsolutePath();
						}
					}
					else{
						try{
							getPath.createNewFile();
							String s = jt.getText().replaceAll("\n", "\r\n");
							FileWriter out = new FileWriter(getPath);
							out.write(s);
							out.close();
							filepath = getPath.getAbsolutePath();
						} catch (Exception e2) {
						e2.printStackTrace();
						}
				}
				}
			}
		});
		
		
		b_savec.registerKeyboardAction(new ActionListener() { 

            @Override 
            public void actionPerformed(ActionEvent e) { 
            	String s = jt.getText().replaceAll("\n", "\r\n");
				File file = new File(filepath);
				FileWriter out;
				try {
					out = new FileWriter(file);
					out.write(s);
					out.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
            } 
        }, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK), JComponent.WHEN_IN_FOCUSED_WINDOW); 
		
		
		
		b_build.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				char ins[] = new char[1000];
				ins = ass.ReadFile(filepath);
				ass.assembler(ins);
				}
			});
		
		
		t = new Thread(new Runnable() {
			
			@Override
			public void run() {
				jt.getDocument().addDocumentListener(new SyntaxHighlighter(jt));
			}
			class SyntaxHighlighter implements DocumentListener {
				private Set<String> keywords;
				private Set<String> inswords;
				private javax.swing.text.Style keywordStyle;
				private javax.swing.text.Style normalStyle;
				private javax.swing.text.Style insStyle;
				
				public SyntaxHighlighter(JTextPane jt){
					keywordStyle = ((StyledDocument)jt.getDocument()).addStyle("Keyword_Style",null);
					normalStyle = ((StyledDocument) jt.getDocument()).addStyle("Keyword_Style", null);
					insStyle = ((StyledDocument) jt.getDocument()).addStyle("Keyword_Style", null);
					
					StyleConstants.setForeground(keywordStyle, Color.RED);
					StyleConstants.setForeground(normalStyle, Color.BLACK);
					StyleConstants.setForeground(insStyle, Color.BLUE);
					
					keywords = new HashSet<String>();
					keywords.add("$zero");
					keywords.add("$at");
					keywords.add("$v0");
					keywords.add("$v1");
					keywords.add("$a0");
					keywords.add("$a1");
					keywords.add("$a2");
					keywords.add("$a3");
					keywords.add("$t0");	
					keywords.add("$t1");
					keywords.add("$t2");
					keywords.add("$t3");
					keywords.add("$t4");
					keywords.add("$t5");
					keywords.add("$t6");
					keywords.add("$t7");
					keywords.add("$s0");
					keywords.add("$s1");
					keywords.add("$s2");
					keywords.add("$s3");
					keywords.add("$s4");
					keywords.add("$s5");
					keywords.add("$s6");
					keywords.add("$s7");
					keywords.add("$t8");
					keywords.add("$t9");
					keywords.add("$k0");
					keywords.add("$k1");
					keywords.add("$gp");
					keywords.add("$sp");
					keywords.add("$fp");
					keywords.add("$ra");
					keywords.add("$0");
					keywords.add("$1");
					keywords.add("$2");
					keywords.add("$3");
					keywords.add("$4");
					keywords.add("$5");
					keywords.add("$6");
					keywords.add("$7");
					keywords.add("$8");
					keywords.add("$9");
					keywords.add("$10");
					keywords.add("$11");
					keywords.add("$12");
					keywords.add("$13");
					keywords.add("$14");
					keywords.add("$15");
					keywords.add("$16");
					keywords.add("$17");
					keywords.add("$18");
					keywords.add("$19");
					keywords.add("$20");
					keywords.add("$21");
					keywords.add("$22");
					keywords.add("$23");
					keywords.add("$24");
					keywords.add("$25");
					keywords.add("$26");
					keywords.add("$27");
					keywords.add("$28");
					keywords.add("$29");
					keywords.add("$30");
					keywords.add("$31");
					
					
					inswords = new HashSet<String>();
					inswords.add("mfhi");
					inswords.add("mflo");
					inswords.add("mthi");
					inswords.add("mtlo");
					inswords.add("mfc0");
					inswords.add("mtc0");
					inswords.add("div");
					inswords.add("divu");
					inswords.add("mult");
					inswords.add("multu");
					inswords.add("add");
					inswords.add("addu");
					inswords.add("sub");
					inswords.add("subu");
					inswords.add("and");
					inswords.add("nor");
					inswords.add("xor");
					inswords.add("sllv");
					inswords.add("srlv");
					inswords.add("srav");
					inswords.add("slt");
					inswords.add("sltu");
					inswords.add("sll");
					inswords.add("srl");
					inswords.add("sra");
					inswords.add("addi");
					inswords.add("addiu");
					inswords.add("andi");
					inswords.add("ori");
					inswords.add("xori");
					inswords.add("slti");
					inswords.add("sltiu");
					inswords.add("beq");
					inswords.add("bgez");
					inswords.add("bgtz");
					inswords.add("blez");
					inswords.add("bltz");
					inswords.add("bne");
					inswords.add("lb");
					inswords.add("lbu");
					inswords.add("lh");
					inswords.add("lhu");
					inswords.add("lui");
					inswords.add("lw");
					inswords.add("sb");
					inswords.add("sh");
					inswords.add("sw");
					inswords.add("j");
					inswords.add("jal");
					inswords.add("jalr");
					inswords.add("jr");
					inswords.add("break");
					inswords.add("eret");
					inswords.add("syscall");
					inswords.add("sleu");
					inswords.add("sle");
										
					
				}
				
				public void coloring(StyledDocument doc,int pos,int len) throws BadLocationException{
					int start = indexOfWordStart(doc,pos);
					int end = indexOfWordEnd(doc,pos+len);
					char ch;
					while(start<end){
						ch = getChatAt(doc,start);
						if(Character.isLetter(ch)||Character.isDigit(ch)||(int)ch == 36){
							start = coloringWord(doc, start);
						}
						else{
							SwingUtilities.invokeLater(new ColoringTask(doc,start,1,normalStyle));
							++start;
						}
					}
				}
				
				public int coloringWord(StyledDocument doc,int pos)throws BadLocationException{
					int wordEnd = indexOfWordEnd(doc,pos);
					String word = doc.getText(pos, wordEnd - pos);
					//System.out.println(word);
					//System.out.println(pos);
					//System.out.println(wordEnd);
					
					if(keywords.contains(word)){
						SwingUtilities.invokeLater(new ColoringTask(doc,pos,wordEnd-pos,keywordStyle));
					}
					else if(inswords.contains(word)){
						SwingUtilities.invokeLater(new ColoringTask(doc,pos,wordEnd-pos,insStyle));
					}
					else{
						SwingUtilities.invokeLater(new ColoringTask(doc,pos,wordEnd-pos,normalStyle));
					}
					return wordEnd;
				}
				
				public char getChatAt(Document doc,int pos)throws BadLocationException{
					return doc.getText(pos, 1).charAt(0);
				}
				
				public int indexOfWordStart(Document doc,int pos) throws BadLocationException{
					for(;pos>0 && isWordCharacter(doc,pos-1);--pos);
					return pos;
				}
				
				public int indexOfWordEnd(Document doc,int pos)throws BadLocationException{
					for(;isWordCharacter(doc, pos);++pos);
					//System.out.println(pos);
					return pos;
				}
				
				public boolean isWordCharacter(Document doc,int pos) throws BadLocationException{
					char ch = getChatAt(doc, pos);
					if(Character.isLetter(ch)||Character.isDigit(ch)||(int)ch == 36){
						//System.out.println(ch);
						//System.out.println((int)'$');
						return true;
					}
					return false;
				}

				@Override
				public void insertUpdate(DocumentEvent e) {
					// TODO Auto-generated method stub
					try{
						coloring((StyledDocument)e.getDocument(),e.getOffset(),e.getLength());
					}catch(BadLocationException e1){
						//e1.printStackTrace();
					}
				}

				@Override
				public void removeUpdate(DocumentEvent e) {
					// TODO Auto-generated method stub
					try{
						coloring((StyledDocument)e.getDocument(), e.getOffset(), 0);
					}catch(BadLocationException e1){
						//e1.printStackTrace();
					}
				}

				@Override
				public void changedUpdate(DocumentEvent e) {
					// TODO Auto-generated method stub
					
				}
				class ColoringTask implements Runnable{
					private StyledDocument doc;
					private javax.swing.text.Style style;
					private int pos;
					private int len;
					
					public ColoringTask(StyledDocument doc,int pos,int len,javax.swing.text.Style normalStyle){
						this.doc = doc;
						this.pos = pos;
						this.len = len;
						this.style = normalStyle;
					}
					public void run(){
						try{
							doc.setCharacterAttributes(pos, len, style, true);
						}catch(Exception e){}
					}
				}
			}
		});
		
		
		
		t.start();
		jt.setBounds(2,37,500,500);
		c.add(b_new);
		c.add(b_build);
		c.add(b_save);
		c.add(b_savec);
		c.add(b_open);
		c.add(jt);
		setVisible(true);//窗口可见
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}
}
