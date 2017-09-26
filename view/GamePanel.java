package view;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import entities.Ground;
import entities.Shape;
import util.ConstantVarable;

/*
 * GamePanel类，用于显示整个游戏界面,且需要重绘图形，故需要继承一个Jpanel类
 */

public class GamePanel extends JPanel{
	
	private Ground ground;
	private Shape shape;
	
	public GamePanel(){
		this.setSize(ConstantVarable.PANEL_WIDTH,ConstantVarable.PANEL_HEIGHT);
	}
	
	public void display(Ground ground,Shape shape)
	{
		System.out.println("这是游戏面板显示的方法");
		this.ground = ground;
		this.shape = shape;
		this.repaint();
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		//重新显示
		/*
		 * 需要重新刷新显示区域。
		 */
		g.setColor(new Color(0xcfcfcf));
		g.fillRect(0, 0, ConstantVarable.PANEL_WIDTH, ConstantVarable.PANEL_HEIGHT);
		if(ground != null && shape != null)
		shape.drawMe(g);
		ground.drawMe(g);
	}
}
