package com.base;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
public class Display
{
    private Frame mainFrame;
    private Canvas canvas;
    private Graphics g;
    public static int width = 856, height = 480;
    BufferedImage localBufferedImage = new BufferedImage(width,height,1);
    int[] imageData = ((DataBufferInt) localBufferedImage.getRaster().getDataBuffer()).getData();
    private static Font monoFont = new Font("Monospaced", Font.PLAIN, 14);
    public Display()
    {
        mainFrame = new Frame("Display");
        mainFrame.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent windowEvent){
                    System.exit(0);
                }        
            });  
        canvas = new Canvas();
        canvas.setBounds(0, 0, width, height);
        mainFrame.add(canvas);
        mainFrame.pack();
        mainFrame.setVisible(true);
        prepare();
    }

    public void prepare()
    {
        if(canvas.getBufferStrategy() == null)
        {
            canvas.createBufferStrategy(2);
            g = canvas.getBufferStrategy().getDrawGraphics();
        }
    }
    
    public void draw() {
        g.dispose();
        canvas.getBufferStrategy().show();
        g = canvas.getBufferStrategy().getDrawGraphics();
    }
    
    public void clearCanvas()
    {
        g.setColor(Color.black);
        g.fillRect(0,0,width,height);
        g.setColor(new Color(255,255,255,128));
    }

    public void shiftImage(int a)
    {
        for(int i = 0; i < imageData.length/width;i++)
            for(int j = a; j < imageData.length/height; j++)
                imageData[i*width+j-a] = imageData[i*width+j];
        for(int k = 0; k < height; k++)
            for(int l = a; l > 0; l--)
                draw(width-l,k,0,0,0);
    }

    public void drawImage(BufferedImage img)
    {
        g.drawImage(img,0, 0, width, height,null);
    }

    public void draw(int x, int y,int color) {
        if(y*width+x<imageData.length&&y*width+x>=0 && x>=0 && y>=0)
            imageData[y*width+x] = color;
    }

    public void draw(int x, int y,int r, int g, int b)
    {
        draw(x,y,r<<16|g<<8|b);
    }

    public int getPixel(int x, int y) {
        return imageData[y*width+x];
    }

    public void drawText(String s,int x,int y)
    {
        if(g.getFont()==null)
            System.out.println("Font null");

        if(s!=null){
            g.drawString(s, x, y);
        }
    }

    public void drawCircle(int x,int y,int r)
    {
        g.fillOval(x, y, r*2, r*2);
    }
    
    public void setTitle(String s)
    {
        mainFrame.setTitle(s);
    }
}