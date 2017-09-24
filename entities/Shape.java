package entities;
import java.awt.Color;
import java.awt.Graphics;

import listener.ShapeListener;
import util.ConstantVarable;

/*
 * ͼ���࣬Ӧ�þ������䣬���ƣ����ơ���ת�ķ���
 * ͬ��ҲҪ��ͼ����ʾ�ķ���
 * ͼ�λ�Ӧ�þ��ж�ʱ�������ػ�Ĺ��ܣ��˹���ͨ���¼���������ʵ�֡�
 * ��Ϊ��������¼�������������Ҫ���һ��ע��ʱ��������ķ���
 */

public class Shape {
	
	private ShapeListener shapeListener;
	
	private int body[][];//���ڴ�ŵ�ǰͼ����ʲôͼ��
	private int status; //���ڴ�ŵ�ǰͼ�ε�״̬
	
	private int top;//���ڱ�ʾͼ�ξ����ϱ߽�ľ���
	private int left;//���ڱ�ʾͼ�ξ�����߽�ľ���
	
	//Ϊ�˱�֤�ж��Ƿ��ƶ���shape����Ҫ�����ṩ��ȡtop��left�ķ���
	public int getTop() {
		return top;
	}

	public int getLeft() {
		return left;
	}

	public Shape(){
		//��ͼ��ʱ����Ӧ���������Ķ�ʱ����Ĺ���
		new Thread(new shapeDriver()).start();
	}

	//����ͼ����ͼ�ι��������ģ�����Ҫ�����ṩset��������ͼ�ι����������ʵ�ͼ�Ρ�
	public void setBody(int[][] body) {
		this.body = body;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void moveDown()
	{
		System.out.println("����ͼ������ķ���");
		top++;
	}
	
	public void moveRight()
	{
		System.out.println("����ͼ�����Ƶķ���");
		left++;
	}
	
	public void moveLeft()
	{
		System.out.println("����ͼ�����Ƶķ���");
		left--;
	}
	
	public void rotate()
	{
		System.out.println("����ͼ����ת�ķ���");
		status = (status+1)%body.length;
	}
	
	public void drawMe(Graphics g)
	{
		System.out.println("����ͼ����ʾ�ķ���");
		
		//���ڵ���ͼ����������������4X4�ľ�������ʾ��ͼ�Σ���ֻ�е�Ϊ1ʱ���Ż�����Ӧ�ĸ���
		//Ҫ�ø�����ʾ�������ͨ��Graphic���������
		g.setColor(Color.BLUE);//����ͼ�ε���ɫ
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
