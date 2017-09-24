package entities;
import java.util.Random;
import listener.ShapeListener;
import util.ConstantVarable;

/*
 * ͼ�ι�������������������ͼ�ε���
 * ��һ����Ҫ��һ��getShape����
 * �������ǲ���ͼ�εķ�����ÿ��ͼ����Ҫע��һ����������
 */
public class ShapeFactory {
	//ͼ�ι����ǲ���ͼ�ε��࣬��������Ҫ��ͼ�ε�����Ԫ�أ������״̬����ʹ��һ����ά�����ȽϺ���
	//���������ͼ�ε�״̬����״���ȽϹ̶������ԣ��Ͱ����ó����ŵ����������ˡ�
	
	public Shape getShape(ShapeListener shapeListener)
	{
		System.out.println("���ǲ���ͼ�εķ���");
		Shape shape = new Shape();
		int shapeKind = (int)(new Random().nextInt(ConstantVarable.BODIES.length));
		shape.setBody(ConstantVarable.BODIES[shapeKind]);
		shape.setStatus(0);
		shape.addshapeListener(shapeListener);
		return shape;
	}
}
