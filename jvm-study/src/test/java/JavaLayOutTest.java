import cn.dqb.jvm.Student;
import org.junit.Test;
import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.info.GraphLayout;
import org.openjdk.jol.vm.VM;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cn.hutool.core.text.UnicodeUtil;

public class JavaLayOutTest {

    @Test
    public void test1() {
        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }


    @Test
    public void test2() {
        List o = new ArrayList<Integer>();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }

    // -XX:-UseCompressedOops -XX:-UseCompressedClassPointers

    @Test
    public void test3() {
        Student student = new Student();
        System.out.println(ClassLayout.parseInstance(student).toPrintable());
    }


    @Test
    public void test4() {
        Integer student = 1;
        System.out.println(ClassLayout.parseInstance(student).toPrintable());
    }

    @Test
    public void test5() {
        System.out.println(VM.current().details());
    }

    @Test
    public void test6() {
        System.out.println(ClassLayout.parseClass(Integer.class).toPrintable());


        System.out.println(ClassLayout.parseInstance(new Integer(1)).toPrintable());

    }

    @Test
    public void test7() {
        System.out.println(ClassLayout.parseClass(HashMap.class).toPrintable());

        HashMap hashMap= new HashMap();
        hashMap.put("flydean","www.flydean.com");
        System.out.println(ClassLayout.parseInstance(hashMap).toPrintable());



        System.out.println(GraphLayout.parseInstance(hashMap).toPrintable());

    }


    @Test
    public void test8() throws UnsupportedEncodingException {
        String s = UnicodeUtil.toUnicode("☺", false);

        System.out.println(s);

        int length = "😁".length();
        System.out.println("length " + length);//2

        System.out.println("☺".length());
        System.out.println("☺️".length());
        System.out.println("☺️".getBytes(StandardCharsets.UTF_8).length);


        //s = "👦👩";
        //System.out.println("2. string length =" + s.length());
        //System.out.println("2. string bytes length =" + s.getBytes().length);
        //System.out.println("2. string char length =" + s.toCharArray().length);
        //System.out.println();


        System.out.println(new String("初心V☺".getBytes(StandardCharsets.UTF_8),"gbk"));
    }

    @Test
    public void test9() throws UnsupportedEncodingException {
        String driverClassName = "com.mysql.jdbc.Driver";	//启动驱动
        String url = "jdbc:mysql://localhost:3307/test?useUnicode=true&characterEncoding=gbk";	//设置连接路径
        String username = "root";	//数据库用户名
        String password = "123456";	//数据库连接密码
        Connection con = null;		//连接
        PreparedStatement pstmt = null;	//使用预编译语句
        ResultSet rs = null;	//获取的结果集
        try {
            Class.forName(driverClassName); //执行驱动
            con = DriverManager.getConnection(url, username, password); //获取连接


            String sql = "INSERT INTO emoji_test(nick_name) VALUES(?)"; //设置的预编译语句格式
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1,"初心V☺");
            pstmt.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            //关闭资源,倒关
            try {
                if(rs != null) rs.close();
                if(pstmt != null) pstmt.close();
                if(con != null) con.close();  //必须要关
            } catch (Exception e) {
            }
        }
    }
}
