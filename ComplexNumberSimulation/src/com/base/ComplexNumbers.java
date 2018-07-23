package com.base;

public class ComplexNumbers 
{
    public static ComplexNumber add(ComplexNumber n1, ComplexNumber n2)
    {
        return new ComplexNumber(n1.r+n2.r,n1.i+n2.i);
    }

    public static ComplexNumber multiply(ComplexNumber n1, ComplexNumber n2)
    {
        return new ComplexNumber(n1.i * n2.i * -1 + n1.r * n2.r, n1.r * n2.i + n2.r * n1.i);
    }
    
    public static ComplexNumber multiply(ComplexNumber n1, double scalar)
    {
        return new ComplexNumber(n1.r*scalar,n1.i*scalar);
    }
    
    public static ComplexNumber divide(ComplexNumber n1, double scalar)
    {
        return new ComplexNumber(n1.r/scalar,n1.i/scalar);
    }
    
    public static ComplexNumber divide(ComplexNumber n1, ComplexNumber n2)
    {
        ComplexNumber m = new ComplexNumber(n2.r,n2.i*-1);
        ComplexNumber n = multiply(m,n1);
        ComplexNumber o = multiply(m,n2);
        return divide(n,o.r);
    }
}
