package urlhandler;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

public class EchoURLStreamHandler extends URLStreamHandler {
    public URLConnection openConnection(URL u) throws IOException {
        //在这里我们也可以进行相应的分流}
        return new EchoURLConnection(u);
    }
}