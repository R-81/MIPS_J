public class text extends DM{
	public static void main(String args[]){
		R r = new R();
		I ii = new I();
		int a = ii.start(87,5,6,0x0005);
		System.out.printf("%d\n",a);
		for(int i = 0;i < 32;i++){
			System.out.printf("%d",register[5][i]);
		}
		System.out.printf("\n");
		for(int i = 0;i < 32;i++){
			System.out.printf("%d",register[6][i]);
		}
	}
}