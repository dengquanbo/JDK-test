import java.util.Date;

public class OSRTest {
    public static void main(String[] args) {
        long sum = 0L;
        Integer.valueOf(10000);
        int range = 8000;
        for (int i = 0; i < 10; i++) {
            long beginTime = new Date().getTime();
            for (int j = 0; j < 10000; j++) {
                for (int k = 0; k < 8000; k++) {
                    Integer integer = k;
                }
            }
            long endTime = new Date().getTime();
            sum = sum + (endTime - beginTime);
        }
        System.out.println(sum);
    }


    public static void main1(String[] args) {
        long sum = 0L ;
        Integer.valueOf(10000);
        int range = 8000;
        for(int i = 0 ;i < 10;i++){
            long beginTime = new Date().getTime();
            for(int j  = 0 ;j < 10000; j++){
                for(int k = 0 ;k < range; k ++){
                    Integer integer  = k ;
                }
            }
            long endTime = new Date().getTime();
            sum = sum + (endTime - beginTime);
        }
        System.out.println(sum);
    }
}
