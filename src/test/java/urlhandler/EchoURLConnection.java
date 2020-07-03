package urlhandler;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.URLConnection;

public class EchoURLConnection extends URLConnection {
    private Socket connection = null;
    public final static int DEFAULT_PORT = 80;

    public EchoURLConnection(URL url) {
        super(url);
    }

    public synchronized InputStream getInputStream() throws IOException {
        if (!connected) {
            connect();
        }
        return connection.getInputStream();
    }

    public synchronized OutputStream getOutputStream() throws IOException {
        if (!connected) {
            connect();
        }
        return connection.getOutputStream();
    }

    public String getContentType() {
        return "text/plain";
    }

    public synchronized void connect() throws IOException {
        if (!connected) {
            int port = url.getPort();
            if (port < 0 || port > 65535) port = DEFAULT_PORT;
            this.connection = new Socket(url.getHost(), port);
// true表示关闭Socket的缓冲,立即发送数据..其默认值为false// 
            //           若Socket的底层实现不支持TCP_NODELAY选项, 则会抛出SocketException
            this.connection.setTcpNoDelay(true);
// 表示是否允许重用Socket所绑定的本地地址
            this.connection.setReuseAddress(true);
// 表示接收数据时的等待超时时间,单位毫秒..其默认值为0,表示会无限等待,永远不会超时
// 当通过Socket的输入流读数据时,如果还没有数据,就会等待
// 超时后会抛出SocketTimeoutException,且抛出该异常后Socket仍然是连接的,可以尝试再次读数据
            this.connection.setSoTimeout(30000);
// 表示当执行Socket.close()时,是否立即关闭底层的Socket
// 这里设置为当Socket关闭后,底层Socket延迟5秒后再关闭,而5秒后所有未发送完的剩余数据也会被丢弃
// 默认情况下,执行Socket.close()方法,该方法会立即返回,但底层的Socket实际上并不立即关闭
// 它会延迟一段时间,直到发送完所有剩余的数据,才会真正关闭Socket,断开连接
// Tips:当程序通过输出流写数据时,仅仅表示程序向网络提交了一批数据,由网络负责输送到接收方
// Tips:当程序关闭Socket,有可能这批数据还在网络上传输,还未到达���收方
// Tips:这里所说的"未发送完的剩余数据"就是指这种还在网络上传输,未被接收方接收的数据
            this.connection.setSoLinger(true, 5);
// 表示发送数据的缓冲区的大小
            this.connection.setSendBufferSize(1024);
// 表示接收数据的缓冲区的大小
            this.connection.setReceiveBufferSize(1024);
// 表示对于长时间处于空闲状态(连接的两端没有互相传送数据)的Socket,是否要自动把它关闭,true为是
// 其默认值为false,表示TCP不会监视连接是否有效,不活动的客户端可能会永久存在下去,而不会注意到服务器已经崩溃
            this.connection.setKeepAlive(true);
// 表示是否支持发送一个字节的TCP紧急数据,socket.sendUrgentData(data)用于发送一个字节的TCP紧急数据
// 其默认为false,即接收方收到紧急数据时不作任何处理,直接将其丢弃..若用户希望发送紧急数据,则应设其为true
// 设为true后,接收方会把收到的紧急数据与普通数据放在同样的队列中
            this.connection.setOOBInline(true);
// 该方法用于设置服务类型,以下代码请求高可靠性和最小延迟传输服务(把0x04与0x10进行位或运算)
// Socket类用4个整数表示服务类型// 0x02:低成本(二进制的倒数第二位为1)
// 0x04:高可靠性(二进制的倒数第三位为1)// 0x08:最高吞吐量(二进制的倒数第四位为1)
// 0x10:最小延迟(二进制的倒数第五位为1)
//
            this.connection.setTrafficClass(0x04 | 0x10);
// 该方法用于设定连接时间,延迟,带宽的相对重要性(该方法的三个参数表示网络传输数据的3项指标)
// connectionTime--该参数表示用最少时间建立连接
// latency---------该参数表示最小延迟
// bandwidth-------该参数表示最高带宽// 可以为这些参数赋予任意整数值,这些整数之间的相对大小就决定了相应参数的相对重要性/
// 如这里设置的就是---最高带宽最重要,其次是最小连接时间,最后是最小延迟
            this.connection.setPerformancePreferences(2, 1, 3);
            this.connected = true;
            StringBuilder sb = new StringBuilder();
            sb.append("POST " + url.getPath() + " HTTP/1.1 \r\n");
            if (url.getPort() < 0 || url.getPort() > 65536) {
                sb.append("Host:").append(url.getHost()).append("\r\n");
            } else {
                sb.append
                        ("Host:").append(url.getHost()).append(":").append(url.getPort()).append("\r\n");
            }
            sb.append
                    ("Connection:keep-alive\r\n");
            sb.append("Date:Fri, 22 Apr 2016 13:17:35 GMT\r\n");
            sb.append
                    ("Vary:Accept-Encoding\r\n");
            sb.append("Content-Type: application/x-www-form-urlencoded,charset=utf-8\r\n");
            sb
                    .append("Content-Length: ").append("name=zhangsan&password=123456".getBytes("UTF-8").length).append("\r\n");
            sb
                    .append("\r\n");
            this.connection.getOutputStream().write(sb.toString().getBytes("UTF-8"));
        }
    }

    public synchronized void disconnect() throws IOException {
        if (connected) {
            this.connection.close();
            this.connected = false;
        }
    }
}