package cn.sxt.game;


import javax.swing.*;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//可以把Frame换成JFrame解决闪烁问题，但是不理想
public class MyGameFrame extends Frame {
    //Image ball=GameUtil.getImage(("images/ball.png"));
    Image planeImg=GameUtil.getImage(("images/plane.png"));
    Image bg=GameUtil.getImage(("images/bg.jpg"));


    Plane plane=new Plane(planeImg,250,250);
    Shell shells[]=new Shell[100];
    Explode explode;


    //paint（）自动被调用，g相当于一支画笔
    public void paint(Graphics g){
        //注意背景和飞机的顺序（如果飞机写在在背景前面就会被背景覆盖）
        g.drawImage(bg,0,0,null);

        plane.drawSelf(g); //画飞机
        //画一堆炮弹
        for(int i=0;i<100;i++){
            shells[i].draw(g);
            //判断炮弹是否和飞机相撞
            boolean collision=shells[i].getRect().intersects(plane.getRect());
            if(collision){
                plane.live=false;
                //下面这个爆炸类有问题
                if(explode==null) {
                    explode=new Explode(plane.x,plane.y);
                    //System.out.println("baozha"+plane.x+plane.y);
                }
                explode.draw(g);

            }
        }
    }

    //内部类（可以直接使用外部类的属性和方法）,Thread:线程
    //反复的重画窗口
    class PaintThread extends  Thread{
        public void run()
        {
            while(true){
                //System.out.println("窗口");
                repaint();//重画窗口

                try {
                    Thread.sleep(40); //暂停40毫秒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    //内部类
    //定义键盘监听的内部类（不同的操作对应不同的数字）
    class KeyMonitor extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            plane.addDirection(e);

        }

        public void keyReleased(KeyEvent e){
            plane.minusDirection(e);
            super.keyReleased(e);
        }
    }

    //初始化窗口
    public void launchFrame() {
        this.setTitle("PlanGame-");
        this.setVisible(true);
        //设置窗口大小
        this.setSize(Constant.GAME_WIDTH, Constant.GAME_HEIGHT);
        //设置窗口位置
        this.setLocation(300, 300);
        //窗口关闭的同时把程序结束
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        //启动重画窗口的线程
        new PaintThread().start();
        //给窗口增加键盘的监听
        addKeyListener(new KeyMonitor());
        //初始化100个炮弹
        for (int i = 0; i < shells.length; i++){
            shells[i]=new Shell();
        }
    }
    public  static void main(String[] args){
        MyGameFrame f=new MyGameFrame();
        f.launchFrame();
    }

    //双缓冲解决闪烁问题
    private Image offScreenImage=null;
    public void update(Graphics g){
        if(offScreenImage==null)
            offScreenImage=this.createImage(500,500);//游戏窗口的宽度和高度

        Graphics gOff=offScreenImage.getGraphics();
        paint(gOff);
        g.drawImage(offScreenImage,0,0,null);

    }

}
