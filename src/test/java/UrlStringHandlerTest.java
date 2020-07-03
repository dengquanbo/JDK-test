import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URL;

import urlhandler.EchoURLConnection;
import urlhandler.EchoURLStreamHandlerFactory;

public class UrlStringHandlerTest {
    @Test
    public void test1() throws IOException {
        URL url = new URL("httpx://www.apptest.com:8080/test/ios.php");
        url.openConnection();
    }

    @Test
    public void test2() throws IOException {
        URL.setURLStreamHandlerFactory(new EchoURLStreamHandlerFactory());
        // URLConnection.setContentHandlerFactory(new EchoContentHandlerFactory());
        URL url = new URL("oschina://localhost:8080/test/ios.php");
        EchoURLConnection connection = (EchoURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()));
        pw.write("name=zhangsan&password=123456");
        pw.flush();
        InputStream stream = connection.getInputStream();
        int len = -1;
        byte[] buf = new byte[256];
        while ((len = stream.read(buf, 0, 256)) > -1) {
            String line = new String(buf, 0, len);
            if (line.endsWith("\r\n0\r\n\r\n") && len < 256) {
                //服务器返回的是Transfer-chunked编码,\r\n0\r\n\r\n表示读取结束了，chunked编码解析：http://dbscx.iteye.com/blog/830644  line
                // = line.substring(0, line.length()-"\r\n0\r\n\r\n".length());
                System.out.println(line);
                break;
            } else {
                System.out.println(line);
            }
        }
        pw.close();
        stream.close();
    }
}
