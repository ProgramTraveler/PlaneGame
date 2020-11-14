package cn.sxt.game;

import java.awt.*;

//炮弹类
public class Shell extends GameObject{
    double degree;

    public Shell(){
        x=200;
        y=200;
        width=10;
        speed=2;
        height=10;

        degree=Math.random()*Math.PI*2;//弧度(0到2π之间)

    }

    public void draw(Graphics g){
        //Color c=g.getColor();
        g.setColor(Color.YELLOW);

        g.fillOval((int)x,(int)y,width,height); //填充炮弹
        //沿着任意角度去飞
        x+=speed*Math.cos(degree);
        y+=speed*Math.sin(degree);

        if(x<0 || x>Constant.GAME_WIDTH-width){
            degree=Math.PI-degree;
        }
        if(y<30 || y>Constant.GAME_HEIGHT-height){
            degree=-degree;
        }


    }

}
