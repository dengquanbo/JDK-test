import org.junit.Test;

public class ThreadPoolExcutorTest {
    int i = 0;

    @Test
    public void retry() {
        retry1:
        while (true) {
            i++;
            System.out.println(i);
            if (i == 10) {
                i = 0;
                continue retry1;
            }
        }

    }

}
