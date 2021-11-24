import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.concurrent.atomic.LongAdder;

public class FloatTest {

    @Test
    public void test1() {
        System.out.println(0.1 + 0.1 + 0.1);
        System.out.println(String.format("%.17f", 0.2f));
        System.out.println(String.format("%.17f", 0.3f));
        showFloatBits(0.2f);

        System.out.println("------Complementation------");
        System.out.println(7 % 4);
        System.out.println((-7) % 4);
        System.out.println(7 % (-4));
        System.out.println((-7) % (-4));

        System.out.println();
        System.out.println("------Modulo Operation------");
        System.out.println(Math.floorMod(7, 4));
        System.out.println(Math.floorMod(-7, 4));
        System.out.println(Math.floorMod(7, -4));
        System.out.println(Math.floorMod(-7, -4));

        System.out.println(-1 & 1);
        System.out.println(Integer.toBinaryString(-1));
        System.out.println(Integer.toBinaryString(1));
    }

    @Test
    public void test22(){
        float v = 1.0f / 0.0f;
        showFloatBits(1.0f / 0.0f);
    }

    @Test
    public void test112() {
        double a = 0.1 + 0.1 + 0.1;
        System.out.println(a);
        showFloatBits(-4.444f);
        showFloatBits(1.25f);
        showFloatBits(1000.00001f);
        showFloatBits(8.25f);

        float b = -4.444f;
        System.out.println(b + 01.f);
        System.out.println(01.f);
        System.out.println(0.1 + 0.1);
        System.out.printf("%.20f", 0.1 + 0.1 + 0.1);
        System.out.printf("%.20f", 0.1 + 0.1);
        showFloatBits(0.2f);
    }


    @Test
    public void showFloatBits() {
        BigDecimal bigDecimal1 = new BigDecimal("0.3").add(new BigDecimal("0.000003"));
        BigDecimal bigDecimal2 = new BigDecimal(0.3);
        System.out.println(bigDecimal1);
        System.out.println(bigDecimal2);
    }

    public void showFloatBits(float floatNumber) {
        int FLOAT_BYTE_SIZE = 4;
        int BYTE_BIT_SIZE = 8;
        int FLOAT_SYMBOL_POSITION = 1;
        int FLOAT_MANTISSA_POSITION = 10;

        ByteBuffer byteBuffer = ByteBuffer.allocate(FLOAT_BYTE_SIZE);
        byteBuffer.putFloat(floatNumber);
        byte[] numberByteArray = byteBuffer.array();

        String[] buffer = new String[FLOAT_BYTE_SIZE * BYTE_BIT_SIZE + 2];

        int bitPosition = 0;
        for (int i = 0; i < FLOAT_BYTE_SIZE; i++) {
            for (int j = 0; j < BYTE_BIT_SIZE; j++) {
                buffer[bitPosition] = String.valueOf(numberByteArray[i] >> (BYTE_BIT_SIZE - j - 1) & 0B1);
                bitPosition++;

                // 位置标识分开符号、指数和尾数
                if (bitPosition == FLOAT_SYMBOL_POSITION || bitPosition == FLOAT_MANTISSA_POSITION) {
                    buffer[bitPosition] = "-";
                    bitPosition++;
                }
            }
        }

        Arrays.stream(buffer).forEach(System.out::print);
        System.out.println();
    }
}
