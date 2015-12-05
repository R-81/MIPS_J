import java.lang.Math;
public class other extends R{
	private static int MaxBit = 32;
	public static AssemblerWindow win;
	public other(AssemblerWindow win){
		this.win = win;
	}
	public static int start(int instruction){
		int returnNumber=0; 
		if(instruction==116){
			returnNumber = breakFunc();
		}
		else if(instruction==117){
			returnNumber = eret();
		}
		else if(instruction==118){
			returnNumber = syscall();
		}
		else{
			returnNumber = 1;
		}
		return returnNumber;
	}
	private static int thirthTwoToTen(int parameter1){
		int answer = 0;
		int tempNumber = 1;
		for(int i=0;i < 32;i++){
			if(register[parameter1][i]==1){
				answer += tempNumber;
			}
			tempNumber *= 2;
		}
		return answer;
	}
	private	static int breakFunc(){
		return 0;
	}
	private static int eret(){
		return 0;
	}
	private static int syscall(){
		int tempNumber = thirthTwoToTen(2);
		if(tempNumber==1){
			int answer = thirthTwoToTen(4);
			win.show(answer);
			return 0;
		}
		else if(tempNumber==4){
			;
		}
		else if(tempNumber==5){
			;
		}
		else if(tempNumber==8){
			;
		}
		else if(tempNumber==10){
			return 3;
		}
		else if(tempNumber==11){
			;
		}
		else if(tempNumber==12){
			;
		}
		return 0;
	}
}

