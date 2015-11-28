import java.lang.Math;
public class I extends R{
	private static int MaxBit = 32;
	public static int start(int instruction,int parameter1,int parameter2,int parameter3){
		int returnNumber=0; 
		if(parameter1>31){
			parameter1 -= 32;
		}
		if(parameter2>31){
			parameter2 -= 32;
		}
		if(instruction==87){
			returnNumber = sll(parameter1,parameter2,parameter3);
		}
		else if(instruction==88){
			returnNumber = srl(parameter1,parameter2,parameter3);
		}
		else if(instruction==89){
			returnNumber = sra(parameter1,parameter2,parameter3);
		}
		else if(instruction==90){
			returnNumber = addi(parameter1,parameter2,parameter3);
		}
		else if(instruction==91){
			returnNumber = addiu(parameter1,parameter2,parameter3);
		}
		else if(instruction==92){
			returnNumber = andi(parameter1,parameter2,parameter3);
		}
		else if(instruction==93){
			returnNumber = ori(parameter1,parameter2,parameter3);
		}
		else if(instruction==94){
			returnNumber = xori(parameter1,parameter2,parameter3);
		}
		else if(instruction==95){
			returnNumber = slti(parameter1,parameter2,parameter3);
		}
		else if(instruction==96){
			returnNumber = sltiu(parameter1,parameter2,parameter3);
		}
		else if(instruction==97){
			returnNumber = beq(parameter1,parameter2,parameter3);
		}
		else if(instruction==98){
			returnNumber = bgez(parameter1,parameter2,parameter3);
		}
		else if(instruction==99){
			returnNumber = bgtz(parameter1,parameter2,parameter3);
		}
		else if(instruction==100){
			returnNumber = bltz(parameter1,parameter2,parameter3);
		}
		else if(instruction==101){
			returnNumber = blez(parameter1,parameter2,parameter3);
		}
		else if(instruction==102){
			returnNumber = bne(parameter1,parameter2,parameter3);
		}
		else if(instruction==103){
			returnNumber = lb(parameter1,parameter2,parameter3);
		}
		else if(instruction==104){
			returnNumber = lbu(parameter1,parameter2,parameter3);
		}
		else if(instruction==105){
			returnNumber = lh(parameter1,parameter2,parameter3);
		}
		else if(instruction==106){
			returnNumber = lhu(parameter1,parameter2,parameter3);
		}
		else if(instruction==107){
			returnNumber = lui(parameter1,parameter2,parameter3);
		}
		else if(instruction==108){
			returnNumber = lw(parameter1,parameter2,parameter3);
		}
		else if(instruction==109){
			returnNumber = sb(parameter1,parameter2,parameter3);
		}
		else if(instruction==110){
			returnNumber = sh(parameter1,parameter2,parameter3);
		}
		else if(instruction==111){
			returnNumber = sw(parameter1,parameter2,parameter3);
		}
		else{
			returnNumber = 1;
		}
		return returnNumber;
	}
	public static int start(int instruction,int parameter1,int parameter2,String parameter3){
		int returnNumber=0; 
		if(parameter1>31){
			parameter1 -= 32;
		}
		if(parameter2>31){
			parameter2 -= 32;
		}
		if(instruction==87){
			returnNumber = sll(parameter1,parameter2,parameter3);
		}
		else if(instruction==88){
			returnNumber = srl(parameter1,parameter2,parameter3);
		}
		else if(instruction==89){
			returnNumber = sra(parameter1,parameter2,parameter3);
		}
		else if(instruction==90){
			returnNumber = addi(parameter1,parameter2,parameter3);
		}
		else if(instruction==91){
			returnNumber = addiu(parameter1,parameter2,parameter3);
		}
		else if(instruction==92){
			returnNumber = andi(parameter1,parameter2,parameter3);
		}
		else if(instruction==93){
			returnNumber = ori(parameter1,parameter2,parameter3);
		}
		else if(instruction==94){
			returnNumber = xori(parameter1,parameter2,parameter3);
		}
		else if(instruction==95){
			returnNumber = slti(parameter1,parameter2,parameter3);
		}
		else if(instruction==96){
			returnNumber = sltiu(parameter1,parameter2,parameter3);
		}
		else if(instruction==97){
			returnNumber = beq(parameter1,parameter2,parameter3);
		}
		else if(instruction==98){
			returnNumber = bgez(parameter1,parameter2,parameter3);
		}
		else if(instruction==99){
			returnNumber = bgtz(parameter1,parameter2,parameter3);
		}
		else if(instruction==100){
			returnNumber = bltz(parameter1,parameter2,parameter3);
		}
		else if(instruction==101){
			returnNumber = blez(parameter1,parameter2,parameter3);
		}
		else if(instruction==102){
			returnNumber = bne(parameter1,parameter2,parameter3);
		}
		else if(instruction==103){
			returnNumber = lb(parameter1,parameter2,parameter3);
		}
		else if(instruction==104){
			returnNumber = lbu(parameter1,parameter2,parameter3);
		}
		else if(instruction==105){
			returnNumber = lh(parameter1,parameter2,parameter3);
		}
		else if(instruction==106){
			returnNumber = lhu(parameter1,parameter2,parameter3);
		}
		else if(instruction==107){
			returnNumber = lui(parameter1,parameter2,parameter3);
		}
		else if(instruction==108){
			returnNumber = lw(parameter1,parameter2,parameter3);
		}
		else if(instruction==109){
			returnNumber = sb(parameter1,parameter2,parameter3);
		}
		else if(instruction==110){
			returnNumber = sh(parameter1,parameter2,parameter3);
		}
		else if(instruction==111){
			returnNumber = sw(parameter1,parameter2,parameter3);
		}
		else{
			returnNumber = 1;
		}
		return returnNumber;
	}
	private static void tenToThirtyTwo(int parameter1,int parameter2){
		for(int i=0;i<MaxBit;i++){
			register[parameter1][i] = 0;
		}
		if(parameter2<0){
			register[parameter1][31] = 1;
			parameter2 *= -1;
		}
		else{
			register[parameter1][31] = 0;
		}
		for(int i = 0;i < MaxBit-1;i++){
			if(parameter2!=0){
				register[parameter1][i] = parameter2%2;
				parameter2 /= 2;
			}
		}
		if(register[parameter1][31]==1){
			for(int i=0;i<MaxBit-1;i++){
				register[parameter1][i]=1^register[parameter1][i];
			}
			addu(parameter1,parameter1,0,1);
		}
	}
	private static void sixTeenToThirtyTwo(int parameter1,String parameter2){
		char[] temp = parameter2.toCharArray();
		String temp2 = new String(temp,2,temp.length);
		tenToThirtyTwo(parameter1,Integer.valueOf(temp2,16));
	}
	
//////////////////////////////////////////////////////////////////////////////////////
	private static int sll(int parameter1,int parameter2,int parameter3){
		for(int i=0;i < MaxBit-parameter3;i++){
			register[parameter1][i+parameter3] = register[parameter2][i];
		}
		for(int i=0;i < parameter3;i++){
			register[parameter1][i] = 0;
		}
		return 0;
	}
	private static int srl(int parameter1,int parameter2,int parameter3){
		for(int i=0;i < MaxBit-parameter3;i++){
			register[parameter1][i] = register[parameter2][i+parameter3];
		}
		for(int i=MaxBit-parameter3;i < MaxBit;i++){
			register[parameter1][i] = 0;
		}
		return 0;
	}
	private static int sra(int parameter1,int parameter2,int parameter3){
		for(int i=0;i < MaxBit-parameter3;i++){
			register[parameter1][i] = register[parameter2][i+parameter3];
		}
		for(int i=MaxBit-parameter3;i < MaxBit;i++){
			register[parameter1][i] = register[parameter2][31];
		}
		return 0;
	}
	private static int addi(int parameter1,int parameter2,int parameter3){
		tenToThirtyTwo(parameter1,parameter3);
		return add(parameter1,parameter1,parameter2,0);
	}
	private static int addiu(int parameter1,int parameter2,int parameter3){
		tenToThirtyTwo(parameter1,parameter3);
		return addu(parameter1,parameter1,parameter2,0);
	}
	private static int andi(int parameter1,int parameter2,int parameter3){
		tenToThirtyTwo(parameter1,parameter3);
		return and(parameter1,parameter1,parameter2);
	}
	private static int ori(int parameter1,int parameter2,int parameter3){
		tenToThirtyTwo(parameter1,parameter3);
		return or(parameter1,parameter1,parameter2);
	}
	private static int xori(int parameter1,int parameter2,int parameter3){
		tenToThirtyTwo(parameter1,parameter3);
		return xor(parameter1,parameter1,parameter2);
	}
	private static int slti(int parameter1,int parameter2,int parameter3){
		tenToThirtyTwo(parameter1,parameter3);
		return slt(parameter1,parameter2,parameter1);
	}
	private static int sltiu(int parameter1,int parameter2,int parameter3){
		tenToThirtyTwo(parameter1,parameter3);
		return sltu(parameter1,parameter2,parameter1);
	}
	private static int beq(int parameter1,int parameter2,int parameter3){
		
		return 0;
	}
	private static int bgez(int parameter1,int parameter2,int parameter3){
		return 0;
	}
	private static int bgtz(int parameter1,int parameter2,int parameter3){
		return 0;
	}
	private static int bltz(int parameter1,int parameter2,int parameter3){
		return 0;
	}
	private static int blez(int parameter1,int parameter2,int parameter3){
		return 0;
	}
	private static int bne(int parameter1,int parameter2,int parameter3){
		return 0;
	}
	private static int lb(int parameter1,int parameter2,int parameter3){
		return 0;
	}
	private static int lbu(int parameter1,int parameter2,int parameter3){
		return 0;
	}
	private static int lh(int parameter1,int parameter2,int parameter3){
		return 0;
	}
	private static int lhu(int parameter1,int parameter2,int parameter3){
		return 0;
	}
	private static int lui(int parameter1,int parameter2,int parameter3){
		return 0;
	}
	private static int lw(int parameter1,int parameter2,int parameter3){
		return 0;
	}
	private static int sb(int parameter1,int parameter2,int parameter3){
		return 0;
	}
	private static int sh(int parameter1,int parameter2,int parameter3){
		return 0;
	}
	private static int sw(int parameter1,int parameter2,int parameter3){
		return 0;
	}
/////////////////////////////////////////////////////////////////////////////////////
	private static int sixTeenToTen(String parameter1){
		char[] temp = parameter1.toCharArray();
		String temp2 = new String(temp,2,temp.length);
		System.out.printf("%d\n",Integer.valueOf(temp2,16));
		return Integer.valueOf(temp2,16);
	}
	private static int sll(int parameter1,int parameter2,String parameter3){
		int parameter4 = sixTeenToTen(parameter3);
		for(int i=0;i < MaxBit-parameter4;i++){
			register[parameter1][i+parameter4] = register[parameter2][i];
		}
		for(int i=0;i < parameter4;i++){
			register[parameter1][i] = 0;
		}
		return 0;
	}
	private static int srl(int parameter1,int parameter2,String parameter3){
		int parameter4 = sixTeenToTen(parameter3);
		for(int i=0;i < MaxBit-parameter4;i++){
			register[parameter1][i] = register[parameter2][i+parameter4];
		}
		for(int i=MaxBit-parameter4;i < MaxBit;i++){
			register[parameter1][i] = 0;
		}
		return 0;
	}
	private static int sra(int parameter1,int parameter2,String parameter3){
		int parameter4 = sixTeenToTen(parameter3);
		for(int i=0;i < MaxBit-parameter4;i++){
			register[parameter4][i] = register[parameter2][i+parameter4];
		}
		for(int i=MaxBit-parameter4;i < MaxBit;i++){
			register[parameter1][i] = register[parameter2][31];
		}
		return 0;
	}
	private static int addi(int parameter1,int parameter2,String parameter3){
		sixTeenToThirtyTwo(parameter1,parameter3);
		return add(parameter1,parameter1,parameter2,0);
	}
	private static int addiu(int parameter1,int parameter2,String parameter3){
		sixTeenToThirtyTwo(parameter1,parameter3);
		return addu(parameter1,parameter1,parameter2,0);
	}
	private static int andi(int parameter1,int parameter2,String parameter3){
		sixTeenToThirtyTwo(parameter1,parameter3);
		return and(parameter1,parameter1,parameter2);
	}
	private static int ori(int parameter1,int parameter2,String parameter3){
		sixTeenToThirtyTwo(parameter1,parameter3);
		return or(parameter1,parameter1,parameter2);
	}
	private static int xori(int parameter1,int parameter2,String parameter3){
		sixTeenToThirtyTwo(parameter1,parameter3);
		return xor(parameter1,parameter1,parameter2);
	}
	private static int slti(int parameter1,int parameter2,String parameter3){
		sixTeenToThirtyTwo(parameter1,parameter3);
		return slt(parameter1,parameter2,parameter1);
	}
	private static int sltiu(int parameter1,int parameter2,String parameter3){
		sixTeenToThirtyTwo(parameter1,parameter3);
		return sltu(parameter1,parameter2,parameter1);
	}
	private static int beq(int parameter1,int parameter2,String parameter3){
		
		return 0;
	}
	private static int bgez(int parameter1,int parameter2,String parameter3){
		return 0;
	}
	private static int bgtz(int parameter1,int parameter2,String parameter3){
		return 0;
	}
	private static int bltz(int parameter1,int parameter2,String parameter3){
		return 0;
	}
	private static int blez(int parameter1,int parameter2,String parameter3){
		return 0;
	}
	private static int bne(int parameter1,int parameter2,String parameter3){
		return 0;
	}
	private static int lb(int parameter1,int parameter2,String parameter3){
		return 0;
	}
	private static int lbu(int parameter1,int parameter2,String parameter3){
		return 0;
	}
	private static int lh(int parameter1,int parameter2,String parameter3){
		return 0;
	}
	private static int lhu(int parameter1,int parameter2,String parameter3){
		return 0;
	}
	private static int lui(int parameter1,int parameter2,String parameter3){
		return 0;
	}
	private static int lw(int parameter1,int parameter2,String parameter3){
		return 0;
	}
	private static int sb(int parameter1,int parameter2,String parameter3){
		return 0;
	}
	private static int sh(int parameter1,int parameter2,String parameter3){
		return 0;
	}
	private static int sw(int parameter1,int parameter2,String parameter3){
		return 0;
	}
}

