package cn.sxt.game;

import java.awt.*;

//游戏物体的父类
public class GameObject {
     Image img;
     double x,y;
     int speed;
     int width,height;

    public void drawSelf(Graphics g){

        g.drawImage(img,(int)x,(int)y,null);
    }

    public GameObject(Image img, double x, double y, int speed, int width, int height){
        super();
        this.img=img;
        this.x=x;
        this.y=y;
        this.speed=speed;
        this.width=width;
        this.height=height;
    }

    public GameObject(Image img, double x, double y){
        super();
        this.x=x;
        this.y=y;
        this.img=img;
    }
    //空构造器
    public GameObject(){
        
    }

    //返回物体所在矩形，便于后序的碰撞检测
    public Rectangle getRect(){
       return new Rectangle((int)x,(int)y,width,height);
    }


}
