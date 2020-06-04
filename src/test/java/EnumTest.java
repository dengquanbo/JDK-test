import org.junit.Test;

public class EnumTest {
    public enum MyEnum {
        A, B;
    }

    @Test
    public void test1() {
        Enum a = MyEnum.A;

        System.out.println(a.name());
        System.out.println(a.ordinal());
        Class aClass = a.getDeclaringClass();
        if (aClass.isEnum()) {
            //通过调用Class对象的getEnumConstants
            MyEnum[] codeEnums = (MyEnum[]) aClass.getEnumConstants();
            System.out.println(codeEnums);
        }
    }

    @Test
    public void test2() {
        int[] arr = {1, 2};
        int[] clone = arr.clone();

        arr[0] = 3;

        System.out.println(arr[0] + " " + arr[1]);
        System.out.println(clone[0] + " " + arr[1]);

    }

    @Test
    public void test3() {
        MyEnum[] myEnums = new MyEnum[]{MyEnum.A, MyEnum.B};
        MyEnum[] clone = myEnums.clone();



        System.out.println(myEnums == clone);
        System.out.println(myEnums[0] == clone[0]);
    }

}
