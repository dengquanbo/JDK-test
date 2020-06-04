package reflect;

import org.junit.Test;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.WildcardType;
import java.util.List;
import java.util.Map;

public class WildcardTypeTest {
    private List<? extends Map> a;
    private List<? super Map> b;

    @Test
    public void test1() {
        Field[] fields = WildcardTypeTest.class.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            Type type = field.getGenericType();
            System.out.println("变量：" + field.getName() + "是否是ParameterizedType类型：" + (type instanceof ParameterizedType));
            if (type instanceof ParameterizedType) {
                for (Type param : ((ParameterizedType) type).getActualTypeArguments()) {
                    System.out.println("变量：" + param.getTypeName() + "是否是WildcardType类型：" + (param instanceof WildcardType));
                    if ((param instanceof WildcardType)) {
                        int lowIndex = ((WildcardType) param).getLowerBounds().length - 1;
                        int upperIndex = ((WildcardType) param).getUpperBounds().length - 1;
                        if (lowIndex >= 0) {
                            System.out.println("getLowerBounds(): " + ((WildcardType) param).getLowerBounds()[lowIndex]);
                        }
                        if (upperIndex >= 0) {
                            System.out.println("getUpperBounds(): " + ((WildcardType) param).getUpperBounds()[upperIndex]);
                        }
                    }
                }
            }
        }
    }
}
