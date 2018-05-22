package project.jinshang.common.utils;

import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

public class ExpressUtils {

    private static final String KEY = "ed1c59e68f863810";

    public static String searchkuaiDiInfo(String com, String nu) {
        String content = "";
        try {
            URL url = new URL("http://www.kuaidi100.com/applyurl?key=" + KEY + "&com=" + com
                    + "&nu=" + nu);
            URLConnection con = url.openConnection();
            con.setAllowUserInteraction(false);
            InputStream urlStream = url.openStream();
            byte b[] = new byte[10000];
            int numRead = urlStream.read(b);
            content = new String(b, 0, numRead);
            while (numRead != -1) {
                numRead = urlStream.read(b);
                if (numRead != -1) {
                    // String newContent = new String(b, 0, numRead);
                    String newContent = new String(b, 0, numRead, "UTF-8");
                    content += newContent;
                }
            }
            urlStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("快递查询错误");
        }
        return content;
    }

    public static String searchLogisticsInfo(String com, String nu) {
        String content = "";
        try {
//            URL url = new URL("http://www.kuaidi100.com/query?id=" + KEY + "&type=" + com + "&postid=" + nu + "&valicode=&temp=0.12476588338756689");
            URL url = new URL("http://api.kuaidi100.com/api?id=" + KEY + "&com=" + com + "&nu=" + nu + "&show=0&muti=1&order=desc");
            URLConnection con = url.openConnection();
            con.setAllowUserInteraction(false);
            InputStream urlStream = url.openStream();
            byte b[] = new byte[10000];
            int numRead = urlStream.read(b);
            content = new String(b, 0, numRead);
            while (numRead != -1) {
                numRead = urlStream.read(b);
                if (numRead != -1) {
                    // String newContent = new String(b, 0, numRead);
                    String newContent = new String(b, 0, numRead, "UTF-8");
                    content += newContent;
                }
            }
            urlStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("快递查询错误");
        }
        return content;
    }
}
