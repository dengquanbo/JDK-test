import org.junit.Test;

public class IntegerTest {
    @Test
    public void printBit() {
        System.out.println(Integer.toBinaryString(-1));
    }

    @Test
    public void test1() {
        System.out.println((int) (char) (byte) -1);
        System.out.println((byte) -1);
    }

    public static void main(String... args) {
        String str = " ";
        System.out.println(str.length());
        char b = '蠮';
        char b1 = '\uffff';
        char b2 = '\uff1e' + '1';
        char b3 = 'a' + 'b';
        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);
        System.out.println('a' + 'b');
        System.out.println('\u4e00');
        System.out.println('\u8c9f');
    }

    @Test
    public void test2() {
        int count = 1; //记录汉字的个数

        for (char c = '\u4e00'; c <= '\u8c9f'; c++) {
            System.out.print(c + "\t");
            count++;
            if (count % 10 == 0) {
                System.out.println("");
            }
        }
        System.out.println(count);
    }
}
