import java.lang.Math;
public class J extends R{
	private static int MaxBit = 32;
	public static int start(int instruction,int parameter1,int parameter2){
		int returnNumber=0; 
		if(parameter1>31){
			parameter1 -= 32;
		}
		if(parameter2>31){
			parameter2 -= 32;
		}
		if(instruction==114){
			returnNumber = jalr(parameter1,parameter2);
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
		if(instruction==113){
			returnNumber = jal(parameter1);
		}
		else if(instruction==115){
			returnNumber = jr(parameter1);
		}
		else{
			returnNumber = 1;
		}
		return returnNumber;
	}
	public static int start(int instruction){
		int returnNumber=0; 
		if(instruction==112){
			returnNumber = j();
		}
		else if(instruction==113){
			returnNumber = jal();
		}
		else if(instruction==114){
			returnNumber = jalr();
		}
		else if(instruction==115){
			returnNumber = jr();
		}
		else{
			returnNumber = 1;
		}
		return returnNumber;
	}
	private	static int j(){
		return 2;
	}
	private static int jal(){
		return 2;
	}
	private static int jr(){
		return 2;
	}
	private static int jalr(){
		return 2;
	}
	private static int jal(int parameter1){
		return 2;
	}
	private static int jr(int parameter2){
		return 2;
	}
	private static int jalr(int parameter1,int parameter2){
		return 2;
	}
}

