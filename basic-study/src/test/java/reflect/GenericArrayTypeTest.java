package reflect;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.util.List;

public class GenericArrayTypeTest<T> {
    private T[] a;
    private List<String>[] b;
    private T[][] c;

    @Test
    public void test1() {
        Field[] fields = GenericArrayTypeTest.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            //输出当前变量是否为GenericArrayType类型
            System.out.println("变量: "
                    + field.getName()
                    + "是否是GenericArrayType："
                    + (field.getGenericType() instanceof GenericArrayType));
            if (field.getGenericType() instanceof GenericArrayType) {
                //如果是GenericArrayType，则输出当前泛型类型
                System.out.println("变量: "
                        + field.getName()
                        + "; getGenericComponentType()"
                        + ": "
                        + (((GenericArrayType) field.getGenericType()).getGenericComponentType()));
            }
        }
    }
}
