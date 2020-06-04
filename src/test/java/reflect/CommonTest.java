package reflect;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.List;

public class CommonTest<K> {
    List<? extends K> c;

    @Test
    public void test1() {
        Field[] fields = CommonTest.class.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            if (f.getGenericType() instanceof ParameterizedType) {
                System.out.println("变量：" + f.getName() + "是ParameterizedType类型");
                Type[] actualTypeArguments = ((ParameterizedType) f.getGenericType()).getActualTypeArguments();
                for (Type type : actualTypeArguments) {
                    if (type instanceof WildcardType) {
                        System.out.println("变量：" + f.getName() + "的实际类型：" + type.getTypeName() + "，是WildcardType类型");
                        Type[] type1 = ((WildcardType) type).getUpperBounds();
                        if (type1[0] instanceof TypeVariable) {
                            System.out.println("变量：" + f.getName() + "的实际类型：" + type.getTypeName() + "，的上界：" + type1[0].getTypeName() +
                                    "，是TypeVariable类型");
                        }
                    }
                }
            }
        }
    }
}
