import java.lang.Math;
public class R extends DM{
	private static int MaxBit = 32;
	public static int start(int instruction,int parameter1,int parameter2,int parameter3){
		int returnNumber=0; 
		if(parameter1>31){
			parameter1 -= 32;
		}
		if(parameter2>31){
			parameter2 -= 32;
		}
		if(parameter3>31){
			parameter3 -= 32;
		}
		if(instruction==74){
			returnNumber = add(parameter1,parameter2,parameter3,0);
		}
		else if(instruction==75){
			returnNumber = addu(parameter1,parameter2,parameter3,0);
		}
		else if(instruction==76){
			returnNumber = sub(parameter1,parameter2,parameter3);
		}
		else if(instruction==77){
			returnNumber = subu(parameter1,parameter2,parameter3);
		}
		else if(instruction==78){
			returnNumber = and(parameter1,parameter2,parameter3);
		}
		else if(instruction==79){
			returnNumber = nor(parameter1,parameter2,parameter3);
		}
		else if(instruction==80){
			returnNumber = or(parameter1,parameter2,parameter3);
		}
		else if(instruction==81){
			returnNumber = xor(parameter1,parameter2,parameter3);
		}
		else if(instruction==82){
			returnNumber = sllv(parameter1,parameter2,parameter3);
		}
		else if(instruction==83){
			returnNumber = srlv(parameter1,parameter2,parameter3);
		}
		else if(instruction==84){
			returnNumber = srav(parameter1,parameter2,parameter3);
		}
		else if(instruction==85){
			returnNumber = slt(parameter1,parameter2,parameter3);
		}
		else if(instruction==86){
			returnNumber = sltu(parameter1,parameter2,parameter3);
		}
		else{
			returnNumber = 1;
		}
		return returnNumber;
	}
	public static int start(int instruction,int parameter1,int parameter2){
		int returnNumber=0; 
		if(parameter1>31){
			parameter1 -= 32;
		}
		if(parameter2>31){
			parameter2 -= 32;
		}
		if(instruction==69){
			returnNumber = mtc0(parameter1,parameter2);
		}
		else if(instruction==70){
			returnNumber = div(parameter1,parameter2);
		}
		else if(instruction==71){
			returnNumber = divu(parameter1,parameter2);
		}
		else if(instruction==72){
			returnNumber = mult(parameter1,parameter2);
		}
		else if(instruction==73){
			returnNumber = multu(parameter1,parameter2);
		}
		else{
			returnNumber = 1;
		}
		return returnNumber;
	}
	public static int start(int instruction,int parameter1){
		int returnNumber=0; 
		if(parameter1>31){
			parameter1 -= 32;
		}
		else{
			returnNumber = 1;
		}
		return returnNumber;
	}
	private static int add(int parameter1,int parameter2,int parameter3,int tempNumber){
		int temp30 = 0;
		int calculatorNumber = 0;
		for(int i=0;i<MaxBit;i++){
			calculatorNumber=0;
			if(tempNumber==1){
				calculatorNumber++;
			}
			if(register[parameter2][i]==1){
				calculatorNumber++;
			}
			if(register[parameter3][i]==1){
				calculatorNumber++;
			}
			if(calculatorNumber==3){
				register[parameter1][i]=1;
				tempNumber=1;
			}
			else if(calculatorNumber==2){
				register[parameter1][i]=0;
				tempNumber=1;
			}
			else if(calculatorNumber==1){
				register[parameter1][i]=1;
				tempNumber=0;
			}
			else if(calculatorNumber==0){
				register[parameter1][i]=0;
				tempNumber=0;
			}
			if(i==30){
				temp30=tempNumber;
			}
			else if(i==31){
				Overflow = temp30^tempNumber;//XOR
			}
		}
		return 0;
	}
	private static int addu(int parameter1,int parameter2,int parameter3,int tempNumber){ 
		int calculatorNumber = 0;
		for(int i=0;i<MaxBit;i++){
			calculatorNumber=0;
			if(tempNumber==1){
				calculatorNumber++;
			}
			if(register[parameter2][i]==1){
				calculatorNumber++;
			}
			if(register[parameter3][i]==1){
				calculatorNumber++;
			}
			if(calculatorNumber==3){
				register[parameter1][i]=1;
				tempNumber=1;
			}
			else if(calculatorNumber==2){
				register[parameter1][i]=0;
				tempNumber=1;
			}
			else if(calculatorNumber==1){
				register[parameter1][i]=1;
				tempNumber=0;
			}
			else if(calculatorNumber==0){
				register[parameter1][i]=0;
				tempNumber=0;
			}
		}
		return 0;
	}
	private static int sub(int parameter1,int parameter2,int parameter3){
		if(register[parameter2][31]==0){
			for(int i=0;i<32;i++){
				register[36][i]=register[parameter2][i];
			}
		}
		else{
			register[36][31]=1;
			for(int i=0;i<31;i++){
				register[36][i]=1^register[parameter2][i];
			}
			addu(36,36,0,1);
		}
		if(register[parameter3][31]==1){
			register[35][31]=0;
			for(int i=0;i<31;i++){
				register[35][i]=register[parameter3][i];
			}
		}
		else{
			register[35][31]=1;
			for(int i=0;i<31;i++){
				register[35][i]=1^register[parameter3][i];
			}
			addu(35,35,0,1);
		}
		add(37,36,35,0);
		if(register[37][31]==1){
			for(int i=0;i<31;i++){
				register[37][i]=1^register[37][i];
			}
			addu(37,37,0,1);
		}
		for(int i=0;i < 32;i++){
			register[parameter1][i] = register[37][i];
		}
		return 0;
	}
	private static int subu(int parameter1,int parameter2,int parameter3){
		if(register[parameter2][31]==0){
			for(int i=0;i<32;i++){
				register[36][i]=register[parameter2][i];
			}
		}
		else{
			register[36][31]=1;
			for(int i=0;i<31;i++){
				register[36][i]=1^register[parameter2][i];
			}
			addu(36,36,0,1);
		}
		if(register[parameter3][31]==1){
			register[35][31]=0;
			for(int i=0;i<31;i++){
				register[35][i]=register[parameter3][i];
			}
		}
		else{
			register[35][31]=1;
			for(int i=0;i<31;i++){
				register[35][i]=1^register[parameter3][i];
			}
			addu(35,35,0,1);
		}
		addu(37,35,36,0);
		if(register[37][31]==1){
			for(int i=0;i<31;i++){
				register[37][i]=1^register[37][i];
			}
			addu(parameter1,37,0,1);
		}
		return 0;
	}
	private static int and(int parameter1,int parameter2,int parameter3){
		for(int i = 0;i < 32;i++){
			register[parameter1][i] = register[parameter2][i]&register[parameter3][i];
		}
		return 0;
	}
	private static int nor(int parameter1,int parameter2,int parameter3){
		for(int i = 0;i < 32;i++){
			register[parameter1][i] = 1^(register[parameter2][i]|register[parameter3][i]);
		}
		return 0;
	}
	private static int or(int parameter1,int parameter2,int parameter3){
		for(int i = 0;i < 32;i++){
			register[parameter1][i] = register[parameter2][i]|register[parameter3][i];
		}
		return 0;
	}
	private static int xor(int parameter1,int parameter2,int parameter3){
		for(int i = 0;i < 32;i++){
			register[parameter1][i] = register[parameter2][i]^register[parameter3][i];
		}
		return 0;
	}
	private static int sllv(int parameter1,int parameter2,int parameter3){
		int tempNumber = 0;
		for(int i = 0;i<5;i++){
			tempNumber += register[parameter3][i]*Math.pow(2,i);
		}
		for(int i = 0;i<32-tempNumber;i++){
			register[parameter1][i+tempNumber]=register[parameter2][i];
		}
		for(int i = 0;i<tempNumber;i++){
			register[parameter1][i]=0;
		}
		return 0;
	}
	private static int srlv(int parameter1,int parameter2,int parameter3){
		int tempNumber = 0;
		for(int i = 0;i<5;i++){
			tempNumber += register[parameter3][i]*Math.pow(2,i);
		}
		for(int i = 0;i < 32-tempNumber;i++){
			register[parameter1][i] = register[parameter2][i+tempNumber];
		}
		for(int i = 32-tempNumber;i < 32;i++){
			register[parameter1][i] = 0;
		}
		return 0;
	}
	private static int srav(int parameter1,int parameter2,int parameter3){
		int tempNumber = 0;
		for(int i = 0;i<5;i++){
			tempNumber += register[parameter3][i]*Math.pow(2,i);
		}
		for(int i = 0;i < 32-tempNumber;i++){
			register[parameter1][i] = register[parameter2][i+tempNumber];
		}
		for(int i = 32-tempNumber;i < 32;i++){
			register[parameter1][i] = register[parameter2][31];
		}
		return 0;
	}
	private static int slt(int parameter1,int parameter2,int parameter3){
		int tempNumber = 0;
		if(register[parameter2][31]>register[parameter3][31]){
			tempNumber = 1;
		}
		else if((register[parameter1][31] == 1)&&(register[parameter1][31]==1)){
			for(int i = 30;i>=0;i--){
				if(register[parameter2][i]>register[parameter3][i]){
					tempNumber = 1;
					break;
				}
				else if(register[parameter2][i]<register[parameter3][i]){
					tempNumber = 0;
					break;
				}
			}
		}
		else if((register[parameter1][31] == 0)&&(register[parameter1][31]==0)){
			for(int i = 30;i>=0;i--){
				if(register[parameter2][i]<register[parameter3][i]){
					tempNumber = 1;
					break;
				}
				else if(register[parameter2][i]>register[parameter3][i]){
					tempNumber = 0;
					break;
				}
			}
		}
		if(tempNumber==1){
			for(int i =1;i < 32;i++){
				register[parameter1][i] = 0;
			}
			register[parameter1][0] = 1;
		}
		else{
			for(int i =0;i < 32;i++){
				register[parameter1][i] = 0;
			}
		}
		return 0;
	}
	private static int sltu(int parameter1,int parameter2,int parameter3){
		int tempNumber = 0;
		for(int i = 30;i>=0;i--){
			if(register[parameter2][i]<register[parameter3][i]){
				tempNumber = 1;
				break;
			}
			else if(register[parameter2][i]>register[parameter3][i]){
				tempNumber = 0;
				break;
			}
		}
		if(tempNumber==1){
			for(int i =1;i < 32;i++){
				register[parameter1][i] = 0;
			}
			register[parameter1][0] = 1;
		}
		else{
			for(int i =0;i < 32;i++){
				register[parameter1][i] = 0;
			}
		}
		return 0;
	}
	private static int mtc0(int parameter1,int parameter2){
		for(int i = 0;i < 32;i++){
			CoregisterCP0[parameter2][i] = register[parameter1][i];
		}
		return 0;
	}
	private static int div(int parameter1,int parameter2){
		int tempNumber=0;
		if(register[parameter2][31]==0){
			for(int i=0;i<32;i++){
				register[38][i]=register[parameter2][i];
			}
		}
		else{
			register[38][31]=1;
			for(int i=0;i<31;i++){
				register[38][i]=1^register[parameter2][i];
			}
			addu(38,38,0,1);
		}
		if(register[parameter1][31]==0){
			for(int i=0;i<32;i++){
				register[33][i]=register[parameter1][i];
			}
		}
		else{
			register[33][31]=1;
			for(int i=0;i<31;i++){
				register[33][i]=1^register[parameter1][i];
			}
			addu(33,33,0,1);
		}
		for(int i = 30;i>=0;i--){
			if(register[38][i]==1){
				tempNumber = i;
				break;
			}
		}
		for(int k = 30;k>30-tempNumber;k--){
			register[34][k]=0;
		}
		register[34][31]=register[33][31]^register[38][31];
		for(int i = 30;i>= 30 - tempNumber;i--){
			register[38][i] = register[38][i-30+tempNumber];
		}
		for(int i = 29-tempNumber;i>=0;i--){
			register[38][i] = 0;
		}
		for(int i = 0,j = 30-tempNumber;i<31-tempNumber;i++,j--){
			for(int k = 0;k <= tempNumber;k++){
				register[35][k]=register[38][30-tempNumber+k-i];
				register[36][k]=register[33][30-tempNumber+k-i];
			}
			for(int k = tempNumber+1;k<32;k++){
				register[35][k]=0;
				register[36][k]=0;
			}
			sleu(1,35,36);
			if(register[1][0]==1){
				register[34][j]=1;
				subu(37,36,35);
				for(int k = 0;k <= tempNumber;k++){
					register[33][30-tempNumber+k-i]=register[37][k];
				}
			}
			else{
				register[34][j]=0;
			}
			for(int k = 0;k < 30;k++){
				register[38][k] = register[38][k+1];
			}
			register[38][30]=0;
		}
		if(register[33][31]==1){
			for(int i=0;i<31;i++){
				register[33][i]=1^register[33][i];
			}
			addu(33,33,0,1);
		}
		if(register[34][31]==1){
			for(int i=0;i<31;i++){
				register[34][i]=1^register[34][i];
			}
			addu(34,34,0,1);
		}
		return 0;
	}
	private static int divu(int parameter1,int parameter2){
		int tempNumber=0;
		for(int i=0;i<32;i++){
			register[38][i]=register[parameter2][i];
		}
		for(int i=0;i<32;i++){
			register[33][i]=register[parameter1][i];
		}
		for(int i = 31;i>=0;i--){
			if(register[38][i]==1){
				tempNumber = i;
				break;
			}
		}
		for(int k = 31;k>31-tempNumber;k--){
			register[34][k]=0;
		}
		for(int i = 31;i>= 31 - tempNumber;i--){
			register[38][i] = register[38][i-31+tempNumber];
		}
		for(int i = 31-tempNumber;i>=0;i--){
			register[38][i] = 0;
		}
		for(int i = 0,j = 31-tempNumber;i<32-tempNumber;i++,j--){
			for(int k = 0;k <= tempNumber;k++){
				register[35][k]=register[38][31-tempNumber+k-i];
				register[36][k]=register[33][31-tempNumber+k-i];
			}
			for(int k = tempNumber+1;k<32;k++){
				register[35][k]=0;
				register[36][k]=0;
			}
			register[1][0]=1;
			for(int k = 31;k>=0;k--){
				if(register[35][k]<register[36][k]){
					register[1][0]=1;
					break;
				}
				else if(register[35][k]>register[36][k]){
					register[1][0]=0;
					break;
				}
			}
			if(register[1][0]==1){
				register[34][j]=1;	
				for(int k=0;k<32;k++){
					register[35][k]=1^register[35][k];
				}
				addu(35,35,0,1);
				addu(37,35,36,0);
				for(int k = 0;k <= tempNumber;k++){
					register[33][31-tempNumber+k-i]=register[37][k];
				}
			}
			else{
				register[34][j]=0;
			}
			for(int k = 0;k < 31;k++){
				register[38][k] = register[38][k+1];
			}
			register[38][31]=0;
		}
		return 0;
	}
	private static int mult(int parameter1,int parameter2){
		return 0;
	}
	private static int multu(int parameter1,int parameter2){
		return 0;
	}
	private static int sleu(int parameter1,int parameter2,int parameter3){
		int tempNumber = 1;
		for(int i = 30;i>=0;i--){
			if(register[parameter2][i]<register[parameter3][i]){
				tempNumber = 1;
				break;
			}
			else if(register[parameter2][i]>register[parameter3][i]){
				tempNumber = 0;
				break;
			}
		}
		if(tempNumber==1){
			for(int i =1;i < 32;i++){
				register[parameter1][i] = 0;
			}
			register[parameter1][0] = 1;
		}
		else{
			for(int i =0;i < 32;i++){
				register[parameter1][i] = 0;
			}
		}
		return 0;
	}
	private static int sle(int parameter1,int parameter2,int parameter3){
		int tempNumber = 1;
		if(register[parameter2][31]>register[parameter3][31]){
			tempNumber = 1;
		}
		else if((register[parameter1][31] == 1)&&(register[parameter1][31]==1)){
			for(int i = 30;i>=0;i--){
				if(register[parameter2][i]>register[parameter3][i]){
					tempNumber = 1;
					break;
				}
				else if(register[parameter2][i]<register[parameter3][i]){
					tempNumber = 0;
					break;
				}
			}
		}
		else if((register[parameter1][31] == 0)&&(register[parameter1][31]==0)){
			for(int i = 30;i>=0;i--){
				if(register[parameter2][i]<register[parameter3][i]){
					tempNumber = 1;
					break;
				}
				else if(register[parameter2][i]>register[parameter3][i]){
					tempNumber = 0;
					break;
				}
			}
		}
		if(tempNumber==1){
			for(int i =1;i < 32;i++){
				register[parameter1][i] = 0;
			}
			register[parameter1][0] = 1;
		}
		else{
			for(int i =0;i < 32;i++){
				register[parameter1][i] = 0;
			}
		}
		return 0;
	}
}
