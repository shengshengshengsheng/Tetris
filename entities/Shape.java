package entities;
import java.awt.Color;
import java.awt.Graphics;

import listener.ShapeListener;
import util.ConstantVarable;
//the shape class used to change the map
/*
 * 图形类，应该具有下落，左移，右移、旋转的方法
 * 同样也要有图形显示的方法
 * 图形还应该具有定时下落且重绘的功能，此功能通过事件监听器来实现。
 * 因为该类带有事件监听器，故需要添加一个注册时间监听器的方法
 */

public class Shape {
	
	private ShapeListener shapeListener;
	
	private int body[][];//用于存放当前图形是什么图形
	private int status; //用于存放当前图形的状态
	
	private int top;//用于表示图形距离上边界的距离
	private int left;//用于表示图形距离左边界的距离
	
	//为了保证判断是否移动，shape类需要对外提供获取top和left的方法
	public int getTop() {
		return top;
	}

	public int getLeft() {
		return left;
	}

	public Shape(){
		//有图形时，就应该启动它的定时下落的功能
		new Thread(new shapeDriver()).start();
	}

	//由于图形是图形工厂产生的，故需要对外提供set方法，供图形工厂产生合适的图形。
	public void setBody(int[][] body) {
		this.body = body;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void moveDown()
	{
		System.out.println("这是图形下落的方法");
		top++;
	}
	
	public void moveRight()
	{
		System.out.println("这是图形右移的方法");
		left++;
	}
	
	public void moveLeft()
	{
		System.out.println("这是图形左移的方法");
		left--;
	}
	
	public void rotate()
	{
		System.out.println("这是图形旋转的方法");
		status = (status+1)%body.length;
	}
	
	public void drawMe(Graphics g)
	{
		System.out.println("这是图形显示的方法");
		
		//对于单个图形来讲，我们是以4X4的矩阵来表示该图形，故只有当为1时，才会填充对应的格子
		//要让格子显示，则必须通过Graphic参数来完成
		g.setColor(Color.BLUE);//填充该图形的颜色
		for(int x = 0;x<4;x++){
			for(int y = 0;y<4;y++){
				if(getFlagByPos(x,y)){
					g.fill3DRect((left+x)*ConstantVarable.CELL_WIDTH, (top+y)*ConstantVarable.CELL_HEIGHT, 
							ConstantVarable.CELL_WIDTH, ConstantVarable.CELL_HEIGHT,true);
				}
			}
		}
				
	}
	
	private boolean getFlagByPos(int x, int y) {
		return body[status][y*4+x] == 1;
	}
	
	public boolean isMember(int x,int y,boolean rotate)
	{
		int tempStatus = status;
		if(rotate)
			tempStatus = (status+1)%body.length;
		return body[tempStatus][y*4+x] == 1;
	}

	private class shapeDriver implements Runnable
	{

		@Override
		public void run() {
			while(shapeListener.shapeIsMoveDownable(Shape.this))
			{
				moveDown();
				shapeListener.shapeMoveDown(Shape.this);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}
	
	public void addshapeListener(ShapeListener sl)
	{
		if(sl != null)
			this.shapeListener = sl;
	}
}
