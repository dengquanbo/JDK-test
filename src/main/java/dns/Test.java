package dns;

public class Test {

    public static void main(String[] argv) {
        for (int i = 0; i < 10; i++) {
            new Thread(new Runner()).start();
        }
    } // main

}

