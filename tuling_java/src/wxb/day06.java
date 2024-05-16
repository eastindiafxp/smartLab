package wxb;

public class day06 {

//    主方法    main：主要的  function:函数
    public static void main(String[] args) {

//        实际参数
        double y1 = add2(5, 2, 7);//临时
        double y2 = add2(4, 3, 5);

        double y3 = square(100);
        System.out.println(y3);

    }

//    return : 返回
//    public static 修饰符
//    int, double等等: 规定返回值的类型
//    add：方法（函数）名，可以任意起名（字母、下划线_、数字组成）
//    形式参数
//    return：返回计算结果
    public static double add2(int x1, int x2, int x3) {
        double a = x1 + x2 + x3 + 0.5;
        return a;
    }

//    square:平方；广场；直角
//    +：加法   -：减法      *：乘法    /：除法
    public static int square(int x) {

        int a = x * x * x;
        return a;
    }





}
