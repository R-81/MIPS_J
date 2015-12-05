
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.sun.org.apache.xalan.internal.xsltc.trax.SAX2StAXStreamWriter;

import jdk.management.cmm.SystemResourcePressureMXBean;





public class Assembler {
	private static int Maxpos = 120;
	private static int r_1 = 64;
	private static int r_2 = 68;
	private static int r_3 = 74;
	private static int i5 = 87;
	private static int i16 = 90;
	private static int i16i = 97;
	private static int lands = 103;
	private static int j_branch = 112;
	private static int register[] = new int[32];
	private static String registerwords[] = new String[Maxpos+1];
	static AssemblerWindow win = new AssemblerWindow();
	static R rr = new R();
	static I ii = new I();
	static J jj = new J();
	other o = new other(win);
	private static void Initial(){
		
		registerwords[0] = "$zero";
		registerwords[1] = "$at";
		registerwords[2] = "$v0";
		registerwords[3] = "$v1";
		registerwords[4] = "$a0";
		registerwords[5] = "$a1";
		registerwords[6] = "$a2";
		registerwords[7] = "$a3";
		registerwords[8] = "$t0";
		registerwords[9] = "$t1";
		registerwords[10] = "$t2";
		registerwords[11] = "$t3";
		registerwords[12] = "$t4";
		registerwords[13] = "$t5";
		registerwords[14] = "$t6";
		registerwords[15] = "$t7";
		registerwords[16] = "$s0";
		registerwords[17] = "$s1";
		registerwords[18] = "$s2";
		registerwords[19] = "$s3";
		registerwords[20] = "$s4";
		registerwords[21] = "$s5";
		registerwords[22] = "$s6";
		registerwords[23] = "$s7";
		registerwords[24] = "$t8";
		registerwords[25] = "$t9";
		registerwords[26] = "$k0";
		registerwords[27] = "$k1";
		registerwords[28] = "$gp";
		registerwords[29] = "$sp";
		registerwords[30] = "$fp";
		registerwords[31] = "$ra";
		registerwords[32] = "$0";
		registerwords[33] = "$1";
		registerwords[34] = "$2";
		registerwords[35] = "$3";
		registerwords[36] = "$4";
		registerwords[37] = "$5";
		registerwords[38] = "$6";
		registerwords[39] = "$7";
		registerwords[40] = "$8";
		registerwords[41] = "$9";
		registerwords[42] = "$10";
		registerwords[43] = "$11";
		registerwords[44] = "$12";
		registerwords[45] = "$13";
		registerwords[46] = "$14";
		registerwords[47] = "$15";
		registerwords[48] = "$16";
		registerwords[49] = "$17";
		registerwords[50] = "$18";
		registerwords[51] = "$19";
		registerwords[52] = "$20";
		registerwords[53] = "$21";
		registerwords[54] = "$22";
		registerwords[55] = "$23";
		registerwords[56] = "$24";
		registerwords[57] = "$25";
		registerwords[58] = "$26";
		registerwords[59] = "$27";
		registerwords[60] = "$28";
		registerwords[61] = "$29";
		registerwords[62] = "$30";
		registerwords[63] = "$31";
		//R
		//one register
		registerwords[64] = "mfhi";
		registerwords[65] = "mflo";
		registerwords[66] = "mthi";
		registerwords[67] = "mtlo";
		//two register
		registerwords[68] = "mfc0";
		registerwords[69] = "mtc0";
		registerwords[70] = "div";
		registerwords[71] = "divu";
		registerwords[72] = "mult";
		registerwords[73] = "multu";
		//three register
		registerwords[74] = "add";
		registerwords[75] = "addu";
		registerwords[76] = "sub";
		registerwords[77] = "subu";
		registerwords[78] = "and";
		registerwords[79] = "nor";
		registerwords[80] = "or";
		registerwords[81] = "xor";
		registerwords[82] = "sllv";
		registerwords[83] = "srlv";
		registerwords[84] = "srav";
		registerwords[85] = "slt";
		registerwords[86] = "sltu";
		//I
		//5 immediate
		registerwords[87] = "sll";
		registerwords[88] = "srl";
		registerwords[89] = "sra";
		//16 immediate
		registerwords[90] = "addi";
		registerwords[91] = "addiu";
		registerwords[92] = "andi";
		registerwords[93] = "ori";
		registerwords[94] = "xori";
		registerwords[95] = "slti";
		registerwords[96] = "sltiu";
		//16 offset
		registerwords[97] = "beq";
		//传2$，2   完成~
		registerwords[98] = "bgez";
		registerwords[99] = "bgtz";
		registerwords[100] = "bltz";
		registerwords[101] = "blez";
		registerwords[102] = "bne";
		//传$$imm 16进制、10进制,完成~
		registerwords[103] = "lb";
		registerwords[104] = "lbu";
		registerwords[105] = "lh";
		registerwords[106] = "lhu";
		registerwords[107] = "lui";
		registerwords[108] = "lw";
		registerwords[109] = "sb";
		registerwords[110] = "sh";
		registerwords[111] = "sw";
		//J
		//26 index
		registerwords[112] = "j";
		//two register
		registerwords[113] = "jal";
		//下一行第一个字符位置
		//one register
		registerwords[114] = "jalr";
		registerwords[115] = "jr";
		//
		//BREAK
		registerwords[116] = "break";
		//ERET
		registerwords[117] = "eret";
		//SYSCALL
		registerwords[118] = "syscall";
		//R add three register
		registerwords[119] = "sleu";
		registerwords[120] = "sle";
	}
	
	
	
	public char[] ReadFile(String filepath){
		File file = new File(filepath);
		char ch[] = new char[10000];
		try{
			FileReader in = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(in);
			bufferedReader.read(ch); 
			bufferedReader.close();
			in.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		String in = "";
		for(int i = 0;i<=1000;i++)
			if((ch[i]>='0'&&ch[i]<='9')||(ch[i]>='a'&&ch[i]<='z')||ch[i] == ','||ch[i] == '\n'||ch[i]==' '||ch[i]=='$'||ch[i] == ':'||ch[i]=='('||ch[i]==')')
				in = in+ch[i];
		in = in+'\n';
		char ins2[] = in.toCharArray();
		return ins2;
	}
	
	
	
	
	public void start(){
		MainWindow win = new MainWindow();
		win.Window();
	}
	
	public static void assembler(char ins[]) {
		win.show("-- operation completed successfully --\n");
		
		//System.out.println(ins);
		Initial();
		//存折所有读入
		char line[] = new char[40];//
		//存指令，例如add
		char label[][] = new char[10][10];
		int label_pos[] = new int[10];
		int lab_num =0;
		//beq等跳转指令用标签
		int i = 0;
		int j = 0;
		
		for(j = 0;i<ins.length;i++){
			//System.out.println(ins[i]);
			if(ins[i]!='\n'){
				line[j++] = ins[i];
			}
			else if (ins[i] == '\n') {
				//System.out.println(line);
				line[j++] = ins[i];
				int len = j;
				int k;
				int mark = 0;
				
				int oppos = 0;
				int r1pos = 0;
				int r2pos = 0;
				int r3pos = 0;
				
				char op[] = new char[5];
				char r1[] = new char[5];
				char r2[] = new char[5];
				char r3[] = new char[5];
				
				int imm = 0;
				
				int error = 0;

				for(j = 0,k = 0;j<len;j++){
					//System.out.println(i);
					//System.out.println(j);
					if(line[j] == ':' && op.length != 0){
						char kc = (char) (k+'0');
						label[lab_num][k+1] = kc;
						for(;k>=0;k--){
							label[lab_num][k] = op[k];
							label_pos[lab_num] = i-len+1;
						}
						k++;
						j++;
						lab_num += 1;
						op = new char[5];
						continue;
					}
					else if(line[j] == ' '&& k!=0){
						j++;
						String ops = "";
						for(int l = 0;l<5;l++){
							if((op[l]>='a'&& op[l]<='z')||(op[l]>='0'&&op[l]<='9'))
								ops = ops+op[l];
							else {
								break;
							}}
						for(oppos = 0;oppos<=Maxpos;oppos++)
							if(registerwords[oppos].equals(ops))
								break;
						break;
					}
					else{
						op[k++] = line[j];
					}
				}
				
				
				//找到指令对应数
				//
				
				
				
				if(oppos>=r_1&&oppos<r_2)
				{
					for(k = 0;j<len;j++){
						if(line[j]!='\n')
							r1[k++]=line[j];
						else{
							String r_1 = "";
							for(int l = 0;l<5;l++){
								if((r1[l]>='a'&& r1[l]<='z')||(r1[l]>='0'&&r1[l]<='9')||r1[l]=='$')
									r_1 = r_1+r1[l];
								else {
									break;
								}}
							for(r1pos = 0;r1pos<=Maxpos;r1pos++)
								if(registerwords[r1pos].equals(r_1))
									break;	
							error = rr.start(oppos,r1pos);
							if(error == 1)
							//报错
							break;
						}
					}
					j = 0;
					line = new char[30];
					continue;
				}
				else if(oppos>=r_2&&oppos<r_3)
				{
					for(k = 0;j<len;j++){
						if(line[j]!=',')
							r1[k++]=line[j];
						else{
							j++;
							String r_1 = "";
							for(int l = 0;l<5;l++){
								if((r1[l]>='a'&& r1[l]<='z')||(r1[l]>='0'&&r1[l]<='9')||r1[l]=='$')
									r_1 = r_1+r1[l];
								else {
									break;
								}}
							for(r1pos = 0;r1pos<=Maxpos;r1pos++)
								if(registerwords[r1pos].equals(r_1))
									break;
							break;
						}
					}
					for(k = 0;j<len;j++){
						if(line[j]!='\n')
							r2[k++]=line[j];
						else{
							String r_2 = "";
							for(int l = 0;l<5;l++){
								if((r2[l]>='a'&& r2[l]<='z')||(r2[l]>='0'&&r2[l]<='9')||r2[l]=='$')
									r_2 = r_2+r2[l];
								else {
									break;
								}}
							for(r2pos = 0;r2pos<=Maxpos;r2pos++)
								if(registerwords[r2pos].equals(r_2))
									break;
							break;
						}
					}
					//System.out.println(oppos);
					error = rr.start(oppos,r1pos,r2pos);
					if(error == 1)
					//报错
					j = 0;
					line = new char[30];
					continue;
				}
				else if(oppos>=r_3&&oppos<i5)
				{
					for(k = 0;j<len;j++){
						if(line[j]!=','){
							r1[k++]=line[j];
						}
						else{
							j++;
							String r_1 = "";
							for(int l = 0;l<5;l++){
								if((r1[l]>='a'&& r1[l]<='z')||(r1[l]>='0'&&r1[l]<='9')||r1[l]=='$')
									r_1 = r_1+r1[l];
								else {
									break;
								}}
							for(r1pos = 0;r1pos<=Maxpos;r1pos++)
								if(registerwords[r1pos].equals(r_1))
									break;
							break;
						}
					}
					for(k = 0;j<len;j++){
						if(line[j]!=',')
							r2[k++]=line[j];
						else{
							j++;
							String r_2 = "";
							for(int l = 0;l<5;l++){
								if((r2[l]>='a'&& r2[l]<='z')||(r2[l]>='0'&&r2[l]<='9')||r2[l]=='$')
									r_2 = r_2+r2[l];
								else {
									break;
								}}
							for(r2pos = 0;r2pos<=Maxpos;r2pos++)
								if(registerwords[r2pos].equals(r_2))
									break;
							break;
						}
					}
					for(k = 0;j<len;j++){
						if(line[j]!='\n')
							r3[k++]=line[j];
						else{
							String r_3 = "";
							for(int l = 0;l<5;l++){
								if((r3[l]>='a'&& r3[l]<='z')||(r3[l]>='0'&&r3[l]<='9')||r3[l]=='$')
									r_3 = r_3+r3[l];
								else {
									break;
								}}
							for(r3pos = 0;r3pos<=Maxpos;r3pos++)
								if(registerwords[r3pos].equals(r_3))
									break;
							break;
						}
					}
					//System.out.println(oppos);
					error = rr.start(oppos,r1pos,r2pos,r3pos);
					if(error == 1);
					//报错
					j = 0;
					line = new char[30];
					continue;
				}
				else if(oppos>=i5&&oppos<i16)
				{
					for(k = 0;j<len;j++){
						if(line[j]!=','){
							r1[k++]=line[j];
						}
						else{
							j++;
							String r_1 = "";
							for(int l = 0;l<5;l++){
								if((r1[l]>='a'&& r1[l]<='z')||(r1[l]>='0'&&r1[l]<='9')||r1[l]=='$')
									r_1 = r_1+r1[l];
								else {
									break;
								}}
							for(r1pos = 0;r1pos<=Maxpos;r1pos++)
								if(registerwords[r1pos].equals(r_1))
									break;
							break;
						}
					}
					for(k = 0;j<len;j++){
						if(line[j]!=',')
							r2[k++]=line[j];
						else{
							j++;
							String r_2 = "";
							for(int l = 0;l<5;l++){
								if((r2[l]>='a'&& r2[l]<='z')||(r2[l]>='0'&&r2[l]<='9')||r2[l]=='$')
									r_2 = r_2+r2[l];
								else {
									break;
								}}
							for(r2pos = 0;r2pos<=Maxpos;r2pos++)
								if(registerwords[r2pos].equals(r_2))
									break;
							break;
						}
					}
					for(k = 0;j<len;j++){
						if(line[j]!='\n'){
							imm = imm*10+line[j]-'0';
						}
						else {
							j++;
							break;
						}
					}
					//System.out.println(oppos);
					error = ii.start(oppos,r1pos,r2pos,imm);
					j = 0;
					line = new char[30];
					continue;
				}
				else if(oppos>=i16&&oppos<i16i)
				{
					for(k = 0;j<len;j++){
						if(line[j]!=','){
							r1[k++]=line[j];
						}
						else{
							j++;
							String r_1 = "";
							for(int l = 0;l<5;l++){
								if((r1[l]>='a'&& r1[l]<='z')||(r1[l]>='0'&&r1[l]<='9')||r1[l]=='$')
									r_1 = r_1+r1[l];
								else {
									break;
								}}
							for(r1pos = 0;r1pos<=Maxpos;r1pos++)
								if(registerwords[r1pos].equals(r_1))
									break;
							break;
						}
					}
					for(k = 0;j<len;j++){
						if(line[j]!=',')
							r2[k++]=line[j];
						else{
							j++;
							String r_2 = "";
							for(int l = 0;l<5;l++){
								if((r2[l]>='a'&& r2[l]<='z')||(r2[l]>='0'&&r2[l]<='9')||r2[l]=='$')
									r_2 = r_2+r2[l];
								else {
									break;
								}}
							for(r2pos = 0;r2pos<=Maxpos;r2pos++)
								if(registerwords[r2pos].equals(r_2))
									break;
							break;
						}
					}
					for(k = 0;j<len;j++){
						if(line[j]!='\n'){
							imm = imm*10+line[j]-'0';
						}
						else {
							j++;
							break;
						}
					}
					//System.out.println(oppos);
					error = ii.start(oppos,r1pos,r2pos,imm);
					j = 0;
					line = new char[30];
					continue;
				}
				else if(oppos>=i16i&&oppos<lands)
				{
					//System.out.println(oppos);
					for(k = 0;j<len;j++){
						if(line[j]!=','){
							r1[k++]=line[j];
						}
						else{
							j++;
							String r_1 = "";
							for(int l = 0;l<5;l++){
								if((r1[l]>='a'&& r1[l]<='z')||(r1[l]>='0'&&r1[l]<='9')||r1[l]=='$')
									r_1 = r_1+r1[l];
								else {
									break;
								}}
							for(r1pos = 0;r1pos<=Maxpos;r1pos++)
								if(registerwords[r1pos].equals(r_1))
									break;
							break;
						}
					}
					for(k = 0;j<len;j++){
						if(line[j]!=',')
							r2[k++]=line[j];
						else{
							j++;
							String r_2 = "";
							for(int l = 0;l<5;l++){
								if((r2[l]>='a'&& r2[l]<='z')||(r2[l]>='0'&&r2[l]<='9')||r2[l]=='$')
									r_2 = r_2+r2[l];
								else {
									break;
								}}
							for(r2pos = 0;r2pos<=Maxpos;r2pos++)
								if(registerwords[r2pos].equals(r_2))
									break;
							break;
						}
					}
					
					
					
					char[] lab = new char[10];
					int o = 0;
					int m = 0;
					int jump_pos = 0;
					for(k = 0;j<len;j++){
						if(line[j]!='\n'){
							lab[k++] = line[j];
						}
						else {
							//System.out.println(lab);
							//System.out.println(label[0]);
							//System.out.println(label_pos[0]);
							j++;
							for(m = 0;m<lab_num;m++){
								//System.out.println("1");
								for( o = 0;o<=k;o++){
									if(lab[o]==label[m][o]){
										continue;
									}
									else
										break;
								}
								//System.out.println("2");
								if(o == k+1 && k == (label[m][o]-'0')){
									jump_pos = label_pos[m];
								}
							}
							break;
						}
					}
					
					System.out.println(jump_pos);
					//I i_I = new I;
					error = jj.start(oppos,r1pos,r2pos,jump_pos);
					if(error == 2){
						i = jump_pos;
					}
					j = 0;
					line = new char[30];
					continue;
				}
				else if(oppos>=lands && oppos<j_branch){
					//System.out.println(line);
					for(k = 0;j<len;j++){
						if(line[j]!=','){
							r1[k++]=line[j];
						}
						else{
							j++;
							String r_1 = "";
							for(int l = 0;l<5;l++){
								if((r1[l]>='a'&& r1[l]<='z')||(r1[l]>='0'&&r1[l]<='9')||r1[l]=='$')
									r_1 = r_1+r1[l];
								else {
									break;
								}}
							for(r1pos = 0;r1pos<=Maxpos;r1pos++)
								if(registerwords[r1pos].equals(r_1))
									break;
							break;
						}
					}
					for(k = 0;j<len;j++){
						if(line[j]!='(' && line[j]>='0' && line[j]<='9'){
							imm = imm*10+line[j]-'0';
						}
						else
							break;
					}
					j++;
					for(k = 0;j<len;j++){
						System.out.println(line[j]);
						if(line[j]!=')')
							r2[k++]=line[j];
						else{
							j++;
							String r_2 = "";
							for(int l = 0;l<5;l++){
								if((r2[l]>='a'&& r2[l]<='z')||(r2[l]>='0'&&r2[l]<='9')||r2[l]=='$')
									r_2 = r_2+r2[l];
								else {
									break;
								}}
							//System.out.println(r_2);
							for(r2pos = 0;r2pos<=Maxpos;r2pos++)
								if(registerwords[r2pos].equals(r_2))
									break;
							break;
						}
					}
					//
					error = ii.start(oppos, r1pos,r2pos,imm);
					if(error == 1)
					//
					j = 0;
					line = new char[30];
					continue;
				}
				else if(oppos == 112)
				{
					//j
					char[] lab = new char[10];
					int o = 0;
					int m = 0;
					int jump_pos = 0;
					for(k = 0;j<len;j++){
						if(line[j]!='\n'){
							lab[k++] = line[j];
						}
						else {
							//System.out.println(lab);
							//System.out.println(label[0]);
							//System.out.println(label_pos[0]);
							j++;
							for(m = 0;m<lab_num;m++){
								//System.out.println("1");
								for( o = 0;o<=k;o++){
									if(lab[o]==label[m][o]){
										continue;
									}
									else
										break;
								}
								//System.out.println(o);
								//System.out.println(k);
								//System.out.println(label[m][o]);
								if(o == k+1 && k == (label[m][o]-'0')){
									jump_pos = label_pos[m];
									//System.out.println(label_pos[0]);
								}
							}
							break;
						}
					}
					i = jump_pos;
					//^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
					j = 0;
					line = new char[30];
					continue;
					//System.out.println(jump_pos);
				}
				else if(oppos == 113){
					//jal
					char[] lab = new char[10];
					int o = 0;
					int m = 0;
					int jump_pos = 0;
					for(k = 0;j<len;j++){
						if(line[j]!='\n'){
							lab[k++] = line[j];
						}
						else {
							//System.out.println(lab);
							//System.out.println(label[0]);
							//System.out.println(label_pos[0]);
							j++;
							for(m = 0;m<lab_num;m++){
								//System.out.println("1");
								for( o = 0;o<=k;o++){
									if(lab[o]==label[m][o]){
										continue;
									}
									else
										break;
								}
								//System.out.println(o);
								//System.out.println(k);
								//System.out.println(label[m][o]);
								if(o == k+1 && k == (label[m][o]-'0')){
									jump_pos = label_pos[m];
									//System.out.println(label_pos[0]);
								}
							}
							break;
						}
					}
					j = 0;
					line = new char[30];
					//System.out.println(jump_pos);
					//System.out.println(ins[i+1]);
					//System.out.println("1");
					continue;
				}
				else if(oppos == 114){
					//jalr
					for(k = 0;j<len;j++){
						if(line[j]!=',')
							r1[k++]=line[j];
						else{
							j++;
							String r_1 = "";
							for(int l = 0;l<5;l++){
								if((r1[l]>='a'&& r1[l]<='z')||(r1[l]>='0'&&r1[l]<='9')||r1[l]=='$')
									r_1 = r_1+r1[l];
								else {
									break;
								}}
							for(r1pos = 0;r1pos<=Maxpos;r1pos++)
								if(registerwords[r1pos].equals(r_1))
									break;
							break;
						}
					}
					for(k = 0;j<len;j++){
						if(line[j]!='\n')
							r2[k++]=line[j];
						else{
							String r_2 = "";
							for(int l = 0;l<5;l++){
								if((r2[l]>='a'&& r2[l]<='z')||(r2[l]>='0'&&r2[l]<='9')||r2[l]=='$')
									r_2 = r_2+r2[l];
								else {
									break;
								}}
							for(r2pos = 0;r2pos<=Maxpos;r2pos++)
								if(registerwords[r2pos].equals(r_2))
									break;
							break;
						}
					}
					//jalr^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
					j = 0;
					line = new char[30];
					continue;
				}
				else if(oppos == 115){
					//jr
					for(k = 0;j<len;j++){
						if(line[j]!='\n')
							r1[k++]=line[j];
						else{
							String r_1 = "";
							for(int l = 0;l<5;l++){
								if((r1[l]>='a'&& r1[l]<='z')||(r1[l]>='0'&&r1[l]<='9')||r1[l]=='$')
									r_1 = r_1+r1[l];
								else {
									break;
								}}
							for(r1pos = 0;r1pos<=Maxpos;r1pos++)
								if(registerwords[r1pos].equals(r_1))
									break;	
							//R r = new R();
							//error = R.start(oppos,r1pos);
							//if(error == 1)
							//报错
							break;
						}
					}
					// i == ~~~~~~~~~^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
					j = 0;
					line = new char[30];
					continue;
				}
			}
		}
		win.rshow();
		win.dshow();
		win.show("\n-- program is finished running (dropped off bottom) --");
	}

}
