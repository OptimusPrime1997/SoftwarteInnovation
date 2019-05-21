package cn.edu.sjtu.ipads.layer1.services;

import java.util.Map;

/**
 * @author Daniel
 * @since 2019/3/31 15:31
 */
public class URLHelper {
    public static String getServiceURL(String serviceName, String path, Map<String, String> paras) {
        String tmp = "http://" + serviceName + "/" + path;
        if (paras != null && !paras.isEmpty()) {
            tmp += "?";
            for (String key : paras.keySet()) {
                tmp += key + "=" + paras.get(key) + "&";
            }
        }
        return tmp;
    }
}
