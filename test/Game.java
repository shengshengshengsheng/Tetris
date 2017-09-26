package test;

import javax.swing.JFrame;

import control.Controller;
import entities.Ground;
import entities.ShapeFactory;
import view.GamePanel;

public class Game {

	public static void main(String[] args) {
		ShapeFactory shapeFactory = new ShapeFactory();
		Ground ground = new Ground();
		GamePanel gamePanel = new GamePanel();
		
		Controller controller = new Controller(
				ground,shapeFactory,gamePanel);
		
		//GamePanel由于是继承JPanel，故需要一个Frame才可以显示出来。
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(gamePanel.getSize().width+10, gamePanel.getSize().height+35);
		frame.add(gamePanel);
		gamePanel.addKeyListener(controller);
		frame.addKeyListener(controller);
		frame.setVisible(true);
		controller.newGame();
	}

}
