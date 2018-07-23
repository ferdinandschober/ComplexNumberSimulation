package com.base;

public class Stats
{
    double targetfps = 60.0;
    long targetFrameTime = (long)(1000000000/targetfps);
    public double avgfps,avgframeTime,avgframeTimems;
    public double fps,frameTime,deltaTime;
    public String avgfps_, avgframeTimems_, stats;
    public long lastUpdateTime = System.nanoTime(),lastTime = System.nanoTime();
    public int updateRate = 1000;
    public int counter = 0;
    public void update()
    {
        counter++;
        frameTime = System.nanoTime()-lastTime;
        fps = (double)(1000000000/frameTime);
        deltaTime = targetFrameTime - frameTime;
        
        if(counter == updateRate)
        {
            avgframeTime = (System.nanoTime()-lastUpdateTime)/updateRate;
            avgframeTimems = avgframeTime/1000000;
            avgfps = (double)(1000000000/avgframeTime);

            avgfps_ = Double.toString(avgfps);
            if(avgfps < 10000.0 && avgfps >= 1000.0)
            {}
            else if (avgfps < 1000.0 && avgfps >= 100.0)
                avgfps_ = " " + avgfps_;
            else if (avgfps < 100.0 && avgfps >= 10.0)
                avgfps_ = "  " + avgfps_;
            else if (avgfps < 10.0 && avgfps > -10.0)
                avgfps_ = "   " + avgfps_;
                
            avgframeTimems_ = Double.toString(avgframeTimems);
            if(avgframeTimems < 10000.0 && avgframeTimems >= 1000.0)
            {}
            else if (avgframeTimems < 1000.0 && avgframeTimems >= 100.0)
                avgframeTimems_ = " " + avgframeTimems_;
            else if (avgframeTimems < 100.0 && avgframeTimems >= 10.0)
                avgframeTimems_ = "  " + avgframeTimems_;
            else if (avgframeTimems < 10.0 && avgframeTimems > -10.0)
                avgframeTimems_ = "   " + avgframeTimems_;
                
            avgfps_ += "             ";
            avgframeTimems_ += "                ";
            avgfps_ = avgfps_.substring(0,6);
            avgframeTimems_ = avgframeTimems_.substring(0,7);
            stats = avgfps_+" avgfps; "+avgframeTimems_+" ms";
            lastUpdateTime = System.nanoTime();
            counter = 0;
        }
        
    }
}