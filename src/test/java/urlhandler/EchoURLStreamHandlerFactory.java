package urlhandler;

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

public class EchoURLStreamHandlerFactory implements URLStreamHandlerFactory {
    public URLStreamHandler createURLStreamHandler(String protocol) {
        //通过这里的分流处理不同的schema请求，当然脑残人士认为这里才是核心代码，URL是一套协议处理框架，如果if-else就是核心，是不是oracle要倒闭
        if (protocol.equals("echo") || protocol.equals("oschina")) {
            return new EchoURLStreamHandler(); //实例化协议处理Handler
        }
        return null;
    }
}