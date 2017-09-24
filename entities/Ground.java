package entities;
import java.awt.Color;
import java.awt.Graphics;

import myEnum.ShapeAction;
import util.ConstantVarable;

/*
 * 障碍物类，用于存放落下的图形形成的障碍物。
 * 必须要有两个方法：drawMe和accept方法。
 * drawMe方法，是绘制障碍物图形的方法
 * accept方法，则是接收下落图形，形成新的障碍物的方法
 */

public class Ground {
	
	private int[][] obstacle = new int[ConstantVarable.CELL_WIDTH_NUMS][ConstantVarable.CELL_HEIGHT_NUMS];
	
	/*
	 * 绘制障碍物图形
	 */
	public void drawMe(Graphics g)
	{
		System.out.println("这是障碍物显示的方法");
		for(int x = 0;x<ConstantVarable.CELL_WIDTH_NUMS;x++){
			for(int y = 0;y<ConstantVarable.CELL_HEIGHT_NUMS;y++){
				if(obstacle[x][y]==1){
					g.setColor(Color.BLUE);
					g.fill3DRect(x*ConstantVarable.CELL_WIDTH, 
							y*ConstantVarable.CELL_HEIGHT, 
							ConstantVarable.CELL_WIDTH, 
							ConstantVarable.CELL_HEIGHT, 
							true);
				}
					
			}
		}
	}
	
	/*
	 * 将新下落的图形融合成自己的图形
	 */
	public void accept(Shape shape)
	{
		System.out.println("这是接收图形，并形成自己的障碍物的方法");
		for(int x = 0;x<4;x++){
			for(int y = 0;y<4;y++){
				if(shape.isMember(x, y, false)){
					obstacle[shape.getLeft()+x][shape.getTop()+y] = 1;
				}
			}
		}
		deleteFullLine();
	}
	
	private void deleteFullLine() {
		for(int y = ConstantVarable.CELL_HEIGHT_NUMS-1;y>=0;y--){
			boolean isFullFlage = true;
			for(int x = 0;x<ConstantVarable.CELL_WIDTH_NUMS;x++){
				if(obstacle[x][y] == 0){
					isFullFlage = false;
				}
			}
			
			if(isFullFlage)
				deleteLines(y);
		}
	}

	private void deleteLines(int lineNum) {
		for(int y =lineNum;y>0;y--){
			for(int x = 0;x<ConstantVarable.CELL_WIDTH_NUMS;x++){
				obstacle[x][y] = obstacle[x][y-1];
			}
		}
		
		for(int x = 0;x<ConstantVarable.CELL_WIDTH_NUMS;x++){
			obstacle[x][0] = 0;
		}
	}

	/*
	 * 此方法是判断图形是否能够做下一个动作的方法。
	 * 所谓的做下一个动作，即图形的有效区域不能达到左边界、右边界或者其他障碍物。
	 * 因此，我们需要定义游戏面板的边界。并且要根据下一个动作来判断是否可以移动
	 */
	public boolean isMoveable(Shape shape,ShapeAction action)
	{
		int left = shape.getLeft();
		int top = shape.getTop();
		
		switch(action){
		case LEFT:
			left--;
			break;
		case RIGHT:
			left++;
			break;
		case DOWN:
			top++;
			break;
		default:
			break;
		}
		
		//依次取出图形的点，判断是否超出显示区域。
		for(int x =0;x<4;x++){
			for(int y = 0;y<4;y++){
				if(shape.isMember(x,y,action == ShapeAction.ROTATE)){
					if((top+y>=ConstantVarable.CELL_HEIGHT_NUMS)
							||(left+x<0)
							||(left+x>=ConstantVarable.CELL_WIDTH_NUMS)
							||obstacle[left+x][top+y]==1)
						return false;
				}	
			}
		}
		
		return true;
		
	}
	
	/*
	 * 判断游戏是否结束。
	 * 目前的实现里面，只有判断第一行是否有障碍物。如果有的话，则不应产生新的图形。进而游戏结束
	 */
	public boolean isFull()
	{
		for(int x = 0;x<ConstantVarable.CELL_WIDTH_NUMS;x++)
		{
			if(obstacle[x][0] == 1)
				return true;
		}
		return false;
	}

}
