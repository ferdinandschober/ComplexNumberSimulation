package com.base;

public class Engine implements Runnable
{
    Display display;
    ComplexNumber v,w,x,y,z;
    ComplexNumber q,r,s,t,u;
    Thread thread;
    Stats stats;

    public Engine()
    {
        display = new Display();
        stats = new Stats();

        v = new ComplexNumber(100,0);
        w = new ComplexNumber(-100,0);
        x = new ComplexNumber(40,0);
        y = new ComplexNumber(40,0);
        z = new ComplexNumber(70,0);
        q = new ComplexNumber(Math.cos(Math.PI/180),Math.sin(Math.PI/180));
        r = new ComplexNumber(Math.cos(Math.PI/360),Math.sin(Math.PI/360));
        s = new ComplexNumber(Math.cos(Math.PI/360),-Math.sin(Math.PI/360));
        t = new ComplexNumber(Math.cos(Math.PI/45),-Math.sin(Math.PI/45));
        u = new ComplexNumber(Math.cos(Math.PI/45),Math.sin(Math.PI/45));
        thread = new Thread(this);
        thread.start();
    }

    public void run()
    {
        for(;;)
        {
            update();
            draw();
            try{
				Thread.sleep(5);
			}
            catch(Exception e){}
			stats.update();
            stats.lastTime = System.nanoTime();
        }
    }

    public void update()
    {
        v = ComplexNumbers.multiply(v,r);
        w = ComplexNumbers.multiply(w,s);
        x = ComplexNumbers.multiply(x,t);
        y = ComplexNumbers.multiply(y,u);
        z = ComplexNumbers.multiply(z,q);
    }

    public void draw()
    {
        display.clearCanvas();
        int r1 = 4;
        int r2 = 20;
        int r3 = 5;
        int r4 = 40;
        display.drawCircle(display.width/2+(int)z.r-r4,display.height/2+(int)z.i-r2,r4);
        display.drawCircle(display.width/2+(int)z.r+(int)v.r-r2,display.height/2+(int)+z.i+(int)v.i-r2,r2);
        display.drawCircle(display.width/2+(int)z.r+(int)w.r-r2,display.height/2+(int)z.i+(int)w.i-r2,r2);
        display.drawCircle(display.width/2+(int)z.r+(int)v.r+(int)x.r-r3,display.height/2+(int)z.i+(int)v.i+(int)x.i-r3,r3);
        display.drawCircle(display.width/2+(int)z.r+(int)w.r+(int)y.r-r3,display.height/2+(int)z.i+(int)w.i+(int)y.i-r3,r3);
        display.drawText(stats.stats,0,10);
        //display.setTitle(stats.fps_+" fps; "+stats.frameTime_+"ms");
        display.draw();
    }
}   
 