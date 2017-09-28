package control;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import entities.Ground;
import entities.Shape;
import entities.ShapeFactory;
import listener.ShapeListener;
import myEnum.ShapeAction;
import view.GamePanel;
//controller,used to control the event
/*
 * 控制器，2个用途：
 * 1，用于处理用户按键事件的处理
 * 2，实现游戏的处理逻辑
 * 
 * 图形定时下落时，会产生一个事件，控制器要利用这个事件来调用gamePanel中的显示方法，
 * 从而完成图形定时下落后，可以重新显示的功能
 */
public class Controller extends KeyAdapter implements ShapeListener {
	private GamePanel gamePanel;
	private Shape shape;
	private ShapeFactory shapeFactory;
	private Ground ground;
	
	public Controller(){}
	
	public Controller(Ground ground,ShapeFactory shapeFactory,GamePanel gamePanel){
		this.gamePanel = gamePanel;
		this.shapeFactory = shapeFactory;
		this.ground = ground;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()){
		case KeyEvent.VK_UP:
			if(ground.isMoveable(shape, ShapeAction.ROTATE))
				shape.rotate();
			break;
		case KeyEvent.VK_DOWN:
			if(shapeIsMoveDownable(shape))
				shape.moveDown();
			break;
		case KeyEvent.VK_LEFT:
			if(ground.isMoveable(shape, ShapeAction.LEFT))
				shape.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			if(ground.isMoveable(shape, ShapeAction.RIGHT))
				shape.moveRight();
			break;
		}
		//每次响应完按键事件后，需要调用gamePanel方法来重新显示图形和障碍物。
		gamePanel.display(ground, shape);
	}
	
	public void newGame()
	{
		shape = shapeFactory.getShape(this);
	}

	@Override
	public void shapeMoveDown(Shape shape) {
		gamePanel.display(ground, shape);
	}

	@Override
	public synchronized boolean shapeIsMoveDownable(Shape shape) {
		if( this.shape != shape){
			return false;
		}
		if(ground.isMoveable(shape, ShapeAction.DOWN))
			return true;
		ground.accept(this.shape);
		if(!ground.isFull())
			this.shape = shapeFactory.getShape(this);
		return false;
	}
}
