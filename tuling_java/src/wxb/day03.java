package wxb;

import java.util.Scanner;

public class day03 {

    public static void main(String args[]) {

        char ch = '人';

        byte by = -128;//-128~127
        short sh = 12345;//-32768~32767
        int in = 1234560000;//最常用
        long lo = 1234560000000000000L;

        float fl = 123.121F;
        double dl = 123.12122722222222222222222222222222222222222222222222D;

        System.out.println(fl);
        System.out.println(dl);

//        隐式转换、显式转换
//                  char --> int --> long --> float --> double
//        byte --> short --> int --> long --> float --> double

        int a = 600;
        byte b = (byte) a;
        System.out.println(b);

        long l = 1111111111111111111l;
        int i1 = (int) l;
        System.out.println(i1);

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        System.out.println(str + "好了");


    }








}
