package JavaOfStandard;

import java.lang.reflect.Field;
import java.util.ArrayList;

class Array1 {
    int[] arrInt;
    String[] arrString;
    int[] arrInt2;

    // ������: ���÷����� ����Ͽ� �迭 �ʵ带 �ʱ�ȭ
    Array1(int size) {
        Field[] fields = this.getClass().getDeclaredFields();

        for (Field field : fields) {
            if (field.getType().isArray()) {
                try {
                    Class<?> componentType = field.getType().getComponentType();
                    Object array = java.lang.reflect.Array.newInstance(componentType, size);
                    field.set(this, array);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // �ʵ忡 ���� ���Ϳ� ����
    public int[] getArrInt() {
        return arrInt;
    }

    public void setArrInt(int[] arrInt) {
        this.arrInt = arrInt;
    }

    public String[] getArrString() {
        return arrString;
    }

    public void setArrString(String[] arrString) {
        this.arrString = arrString;
    }

    public int[] getArrInt2() {
        return arrInt2;
    }

    public void setArrInt2(int[] arrInt2) {
        this.arrInt2 = arrInt2;
    }
}


	

public class arrayTest {
	public static void main (String args[]) {
		ArrayList <Array1> a = new ArrayList<>();

	}
	

}