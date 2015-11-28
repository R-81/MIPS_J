import java.awt.event.KeyListener;

import javax.naming.InitialContext;

import jdk.nashorn.internal.runtime.regexp.joni.SearchAlgorithm;

import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;





public class Assembler extends DM{
	private static int Maxpos = 118;
	private static int register[] = new int[32];
	private static String registerwords[] = new String[64];
	private static String inswords[] = new String[Maxpos+1];
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
		registerwords[64] = "mfhi";//move 64 to 68
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
		registerwords[98] = "bgez";
		registerwords[99] = "bgtz";
		registerwords[100] = "bltz";
		registerwords[101] = "blez";
		registerwords[102] = "bne";
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
		//one register
		registerwords[114] = "jalr";
		registerwords[115] = "jr";
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
	public char[] ReadFile(){
		File file = new File("filetest.txt");
		char ch[] = new char[10000];
		try{
			FileReader in = new FileReader(file);
			in.read(ch); 
			in.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return ch;
	}
	public void start(){
		MainWindow win = new MainWindow();
		win.Window();
	}
	public static void assembler(char ins[]) {
		Initial();
		//存折所有读入
		char line[] = new char[30];//
		//存指令，例如add
		char label[] = new char[10];
		//beq等跳转指令用标签
		int i = 0;
		for(int j = 0;i<ins.length;i++){
			if(ins[i]!='\n'){
				line[j++] = ins[i];
			}
			else if (ins[i] == '\n') {
				int len = j;
				int k;
				int mark = 0;
				char op[] = new char[5];
				for(j = 0,k = 0;j<len;j++){
					if(line[j] == ' '&& op.length!=0){
						String ops = "";
						int oppos;
						for(int l = 0;l<5;l++)
							if(op[l]>='a'&& op[l]<='z')
								ops = ops+op[l];
						for(oppos = 0;oppos<=******;oppos++)
							if(registerwords[oppos].equals(ops))
								break;
					}
					else
						op[k++] = line[j];
				}
				
			}
		}
	}

}
