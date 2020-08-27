package com.martin.thinkinmyjava.demo;

/**
 * @author Martin
 * @description
 * @date 2020/4/16
 */
public class IntegerTest {

    public static void main(String[] args) {
        Byte aByte = new Byte("1");
        Byte bByte = 1;
        Byte cByte = 1;
        System.out.println(aByte);
        System.out.println(bByte);
        System.out.println(aByte == bByte);
        System.out.println(bByte == cByte);

        Integer a = 1;
        Integer b = 1;
        System.out.println(a == b);
        System.out.println(a.equals(b));

        Integer c = 1000000000;
        Integer d = 1000000000;
        System.out.println(c == d);
        System.out.println(c.equals(d));

        Integer e = 127;
        Integer f = 127;
        System.out.println(e == f);
        System.out.println(e.equals(f));

        Integer g = 128;
        Integer h = 128;
        System.out.println(g == h);
        System.out.println(g.equals(h));

        Byte aa = 1;
        Byte bb = 1;
        System.out.println(aa == bb);
        System.out.println(aa.equals(bb));

        Byte cc = 127;
        Byte dd = 127;
        System.out.println(cc == dd);
        System.out.println(cc.equals(dd));
    }


}
