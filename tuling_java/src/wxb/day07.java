package wxb;

//类
public class day07 {

//    int a = 1000;
    /**
     * 主方法
     * main:主要的
     * @param args
     */
    public static void main(String[] args) {
//        int y1 = fun1();
//        调用函数fun1，3、4：实际参数
        System.out.println("加法：" + add(5, 4));
        System.out.println("减法：" + minus(10, 5));
        System.out.println("乘法：" + chengfa(2, 8));
        System.out.println("除法：" + chufa(0, 3));
        System.out.println("余数：" + yushu(7, 3));
    }

//    四则运算符
//    加法：+
//    减法：-
//    乘法：*
//    除法：/  只取运算结果的整数  7/3  2
//    取余：%（取余数）  5%3  2

//    加法
    public static int add(int x1, int x2) {

        return x1 + x2;
    }

    public static int minus(int x1, int x2) {

        return x1 - x2;
    }

    public static int chengfa(int x1, int x2) {

        return x1 * x2;
    }

    public static int chufa(int x1, int x2) {

        return x1 / x2;
    }

    public static int yushu(int x1, int x2) {

        return x1 % x2;
    }

}
