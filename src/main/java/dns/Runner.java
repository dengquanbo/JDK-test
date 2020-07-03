package dns;

import java.net.*;
import java.io.*;
import java.util.*;

public class Runner implements Runnable {

    private String getPage(String page) {
        InputStreamReader input = null;
        StringWriter output = new StringWriter();
        try {
            URL url = new URL(page);
            URLConnection u = url.openConnection();
            u.connect();
            input = new InputStreamReader(u.getInputStream());
            char[] streamBuffer = new char[256];
            while (true) {
                int bytesRead = input.read(streamBuffer);
                if (bytesRead == -1) break;
                output.write(streamBuffer, 0, bytesRead);
            } // while
        } catch (IOException e) {
            System.out.println("error opening page");
        } finally {
            if (input != null)
                try {
                    input.close();
                } catch (IOException e) {
                } // try
        } // try
        return output.toString();
    } // getPage

    @Override
    public void run() {
        getPage("http://paces:9000/session/Login.po");
    } // run

} 