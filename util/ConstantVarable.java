package util;

public class ConstantVarable {
	
	//设置每一个单元格的宽高
	public static final int CELL_WIDTH = 20;
	public static final int CELL_HEIGHT = 20;
	
	public static final int CELL_WIDTH_NUMS = 20;
	public static final int CELL_HEIGHT_NUMS = 20;
	//设置游戏面板的大小
	public static final int PANEL_WIDTH = CELL_WIDTH_NUMS*CELL_WIDTH;
	public static final int PANEL_HEIGHT = CELL_HEIGHT_NUMS*CELL_HEIGHT;
	
	//三维数组的第一维，表示有多少种图形。而后面两维则共同表示某种图形所对应的状态
	public static final int[][][] BODIES = new int[][][]{
		{
			{
				1,0,0,0,
				1,1,1,0,
				0,0,0,0,
				0,0,0,0
			},
			{
				1,1,0,0,
				1,0,0,0,
				1,0,0,0,
				0,0,0,0
			},
			{
				1,1,1,0,
				0,0,1,0,
				0,0,0,0,
				0,0,0,0
			},
			{
				0,1,0,0,
				0,1,0,0,
				1,1,0,0,
				0,0,0,0
			}
		},
	};
}
