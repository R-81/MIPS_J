import java.lang.Math;
public class SHOWCON{
	public static String showRegister(int parameter_1){
		String str = "";
		for(int i=31,j=3,number = 0;i>=0;i--){
			if(DM.register[parameter_1][i]==1){
				if(j==3){
					number += 8;
					j--;
				}
				else if(j==2){
					number += 4;
					j--;
				}
				else if(j==1){
					number += 2;
					j--;
				}
				else if(j==0){
					number += 1;
					j = 3;
					if(number==15){
						str = str+"f";
					}
					else if(number==14){
						str = str+"e";
					}
					else if(number==13){
						str = str+"d";
					}
					else if(number==12){
						str = str+"c";
					}
					else if(number==11){
						str = str+"b";
					}
					else if(number==10){
						str = str+"a";
					}
					else{
						str = str+number;
					}
					number = 0;
				}
			}
			else{
				if(j==0){
					j = 3;
					if(number==15){
						str = str+"f";
					}
					else if(number==14){
						str = str+"e";
					}
					else if(number==13){
						str = str+"d";
					}
					else if(number==12){
						str = str+"c";
					}
					else if(number==11){
						str = str+"b";
					}
					else if(number==10){
						str = str+"a";
					}
					else{
						str = str+number;
					}
					number = 0;
				}
				else{
					j--;
				}
			}
		}
		return str;
	}
	public static String showRAM(int parameter_1){
		String str = "";
		for(int i=31,j=3,number = 0;i>=0;i--){
			if(DM.RAM[parameter_1+i]==1){
				if(j==3){
					number += 8;
					j--;
				}
				else if(j==2){
					number += 4;
					j--;
				}
				else if(j==1){
					number += 2;
					j--;
				}
				else if(j==0){
					number += 1;
					j = 3;
					if(number==15){
						str = str+"f";
					}
					else if(number==14){
						str = str+"e";
					}
					else if(number==13){
						str = str+"d";
					}
					else if(number==12){
						str = str+"c";
					}
					else if(number==11){
						str = str+"b";
					}
					else if(number==10){
						str = str+"a";
					}
					else{
						str = str+number;
					}
					number = 0;
				}
			}
			else{
				if(j==0){
					j = 3;
					if(number==15){
						str = str+"f";
					}
					else if(number==14){
						str = str+"e";
					}
					else if(number==13){
						str = str+"d";
					}
					else if(number==12){
						str = str+"c";
					}
					else if(number==11){
						str = str+"b";
					}
					else if(number==10){
						str = str+"a";
					}
					else{
						str = str+number;
					}
					number = 0;
				}
				else{
					j--;
				}
			}
		}
		return str;
	}
}

