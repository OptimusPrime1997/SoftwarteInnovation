package cn.edu.sjtu.ipads;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author Daniel
 * @since 2019/3/31 17:03
 */
public class Util {
    private static Random r = new Random(System.currentTimeMillis());
    private static SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

    /**
     * {timeSeconds}{type-hash}random
     *
     * @param type
     * @return
     */
    public static String idGenerate(String type) {
        String timeSeconds = System.currentTimeMillis() / 1000 + "";
        String typeHash = type.hashCode() % 1000 + "";
        String random = (r.nextInt(8999) + 1000) + "";
        return timeSeconds + typeHash + random;
    }

    public static String currentDateTime() {
        return DATETIME_FORMAT.format(new Date());
    }

}
