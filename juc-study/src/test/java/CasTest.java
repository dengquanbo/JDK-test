import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.atomic.LongAdder;

public class CasTest {
    @Test
    public void tests() throws IOException, ClassNotFoundException {
        LongAdder longAdder = new LongAdder();
        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(new File("a.txt")));
        outputStream.writeObject(longAdder);

        ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(new File("a.txt")));
        Object object = inputStream.readObject();
        LongAdder longAdder1 = (LongAdder) object;
        System.out.println(longAdder1.sum());

    }
}
