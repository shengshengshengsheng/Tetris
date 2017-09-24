package entities;
import java.util.Random;
import listener.ShapeListener;
import util.ConstantVarable;

/*
 * 图形工厂，用于生产出各种图形的类
 * 故一定需要有一个getShape方法
 * 由于其是产生图形的方法，每个图形需要注册一个监听器。
 */
public class ShapeFactory {
	//图形工厂是产生图形的类，故他中需要有图形的所有元素：种类和状态。故使用一个三维数组会比较合适
	//但由于这个图形的状态和形状都比较固定，所以，就把他拿出来放到其他包下了。
	
	public Shape getShape(ShapeListener shapeListener)
	{
		System.out.println("这是产生图形的方法");
		Shape shape = new Shape();
		int shapeKind = (int)(new Random().nextInt(ConstantVarable.BODIES.length));
		shape.setBody(ConstantVarable.BODIES[shapeKind]);
		shape.setStatus(0);
		shape.addshapeListener(shapeListener);
		return shape;
	}
}
