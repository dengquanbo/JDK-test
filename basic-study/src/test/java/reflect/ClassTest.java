package reflect;

import org.junit.Test;

import java.lang.reflect.Field;

public class ClassTest {
    private int a;
    private Integer b;
    private int[] c;
    private Integer d;
    private ClassTest e;
    private ClassTest[] f;

    @Test
    public void test1() {
        Field[] fields = ClassTest.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            System.out.println("变量" + field.getName() + "属于class类型 = " + (field.getGenericType() instanceof Class));
        }
    }
}
