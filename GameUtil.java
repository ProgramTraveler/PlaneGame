package cn.sxt.game;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
//import java.net.URI;
import java.net.URL;

public class GameUtil {
    //工具类最好将构造器私有
    private GameUtil(){

    }
    //指定路径文件的图片对象
    public static Image getImage(String path) {
        BufferedImage bi = null;
        try {
            URL u = GameUtil.class.getClassLoader().getResource(path);
            bi = ImageIO.read(u);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bi;
    }
}
