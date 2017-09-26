package listener;
import entities.Shape;
/*
 * 图形下落完成之后的事件监听主要作用是，当图形自己定时下落之后，也需要重新显示。而此时，它并不能直接调用gamePanel的显示方法。
 * 故需要在定时下落完成后，触发一个事件，告知控制器，下落已经完成，可以调用gamePanel的显示方法。
 * 因此，事件源是图形，事件监听的实现是控制器。
 */

public interface ShapeListener {
	
	/*
	 * 图形下落完成之后的事件监听
	 */
	public abstract void shapeMoveDown(Shape shape);
	
	/*
	 * 图形自动下落时触发的是否可以下落的事件监听
	 */
	public abstract boolean shapeIsMoveDownable(Shape shape);
}
