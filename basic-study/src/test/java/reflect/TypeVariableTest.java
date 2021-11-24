package reflect;

import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.List;

public class TypeVariableTest<K, T> {

    K a;

    T b;


    @Test
    public void main() {
        // 获得类上泛型定义
        Type[] types = TypeVariableTest.class.getTypeParameters();

        Field[] fields = TypeVariableTest.class.getDeclaredFields();
        for (Field f : fields) {
            f.setAccessible(true);
            System.out.println("变量:" + f.getName() + "是否是TypeVariable：" +
                    (f.getGenericType() instanceof TypeVariable));
            if (f.getGenericType() instanceof TypeVariable) {
                TypeVariable type = (TypeVariable) f.getGenericType();
                int index = type.getBounds().length - 1;
                //输出上边界
                System.out.println("上边界getBounds()：" + type.getBounds()[index]);
                //输出名称
                System.out.println("名称getName()：" + type.getName());
                //输出所在的类的类型
                System.out.println("类型getGenericDeclaration()：" + type.getGenericDeclaration());
            }
        }
    }
}
