public class DM{
	//int only have 0 and 1
	public static int register[][] = new int[40][32];
	//register[32]=PC;register[33]=HI;register[34]=LO;
	//register[35],register[36],register[37],register[38],register[39]
	//are temporary registers;
	//low bit 0;high bit 31;
	public static int CoregisterCP0[][] = new int[32][32];
	//CP0
	//special registers
	//协处理器一词通常用来表示处理器的一个可选部件，负责处理指令集的某个扩展，
	//拥有与处理器相独立的寄存器。MIPS32架构提供了最多4个协处理器，分别是CP0-CP3。
	//协处理器CP0用作系统控制，CP1、CP3用作浮点处理单元，而CP2被保留用于特定实现。
	//除CP0外的协处理器都是可选的。
	//协处理器CP0的具体作用有：配置CPU工作状态、高速缓存控制、异常控制、
	//存储管理单元控制等。CP0通过配置内部的一系列寄存器来完成上述工作。
	public static int Overflow = 0;
	public static int RAM[] = new int[10000];
	public DM(){
		for(int i = 0;i < 40;i++){
			for(int j = 0;j < 32;j++){
				register[i][j] = 0;
			}
		}
		for(int i = 0;i < 32;i++){
			for(int j = 0;j < 32;j++){
				CoregisterCP0[i][j] = 0;
			}
		}
	}
}