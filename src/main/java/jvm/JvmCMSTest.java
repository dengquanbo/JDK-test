package jvm;

/**
 * 验证 CMS 收集器
 * <p>
 * -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseConcMarkSweepGC
 *
 * eden:8M S0=S1=1M
 */
public class JvmCMSTest {
    public static void main(String[] args) {
        int size = 1024 * 1024;

        byte[] myAlloc1 = new byte[4 * size];

        System.out.println("11111111");

        byte[] myAlloc2 = new byte[4 * size];
        System.out.println("22222");

        byte[] myAlloc3 = new byte[4 * size];
        System.out.println("3333");

        byte[] myAlloc4 = new byte[2 * size];
        System.out.println("44444");
    }
}
