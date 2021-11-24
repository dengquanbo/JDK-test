package reflect;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class ParameterizedTypeTest<T> {
    private HashMap<String, List<Object>> a;
    private HashSet<String> b;
    private List<String> c;
    private List<T> d;
    private Class<?> e;
    private Map.Entry<T, T> f;

    @Test
    public void test1() {
        Field[] fields = ParameterizedTypeTest.class.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            System.out.println("变量:" + f.getName() + "是否是ParameterizedType：" +
                    (f.getGenericType() instanceof ParameterizedType));
            if ((f.getGenericType() instanceof ParameterizedType)) {
                ParameterizedType type = (ParameterizedType) f.getGenericType();
                System.out.println("实际类型 = " + type.getRawType());
                System.out.println("父类型 = " + type.getOwnerType());
                for (Type param : type.getActualTypeArguments()) {
                    //打印实际参数类型
                    System.out.println("泛型类型=" + param.toString());
                }
                System.out.println("");
            }
        }
    }
}
