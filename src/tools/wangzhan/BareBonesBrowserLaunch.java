/**
 * 疯神冒险岛论坛bbs.13mxd.com
 */
package tools.wangzhan;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class BareBonesBrowserLaunch {

    /*打开指定网站的方法.*/
    
    public static void OpenWeb1() {

      /*  String cmdStr = "cmd /c start iexplore http://bbs.13mxd.com";
        try {
            Runtime.getRuntime().exec(cmdStr);
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        try {
            URI uri = new URI("http://www.baidu.com");
            Desktop.getDesktop().browse(uri);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
