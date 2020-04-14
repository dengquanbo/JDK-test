import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class JvmTest {
    @Test
    public void testss() throws IOException {
        Process process = Runtime.getRuntime().exec("jps -l -v");
        InputStream inputStream = process.getInputStream();
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        InputStreamReader reader = new InputStreamReader(bufferedInputStream);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }

        System.out.println();

    }
}
