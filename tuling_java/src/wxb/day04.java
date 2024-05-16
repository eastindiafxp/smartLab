package wxb;

public class day04 {

    public static void main(String args[]) {

//        八种基本数据类型
//        1、char：字符型（一次只能写入一个字符）
//        2、short：短整型(-32768~32767)
//        3、int：整型（整数）integer   最常用
//        4、long：长整型
//        5、byte：字节型(-128~127)
//        6、float：浮点型/单精度（小数）
//        7、double：双精度（小数）
//        8、boolean：布尔型（true，false）


        char ch1 = '我';
        char ch = 22469;
        short sh = 12345;
        int i = 1;
        long l = 13333333333L;
        byte b = -128;
        float fl = 123.5F;
        double d = 123.56789D;
        boolean bl = false;

//        数据类型之间的相互转换：显示转换、隐式转换
//                  char --> int --> long --> float --> double
//        byte --> short --> int --> long --> float --> double
//        隐式转换
        char ch2 = 7777;//6万多
        int int2 = ch2;//21亿多
//        显式转换(强制转换)
        int int3 = 88888;
        char ch3 = (char) int3;



        System.out.println(bl);

//        打印字符串到控制台
//        System.out.print("hello world!你好，世界！");

    }











}
