package wxb;

/**
 * 方法（函数）
 */
public class day05 {

//    主方法
    public static void main(String args[]) {

        int a = 4;//整数
        String b = "adrgt";//String：字符串

        //调用函数 f()
        int y = f();
        System.out.println(y);

    }

//    方法（函数）：一个数（多个数）:x，经过一系列运算，最终得到另一个数:y
//    那么 y 就叫做 x 的函数，x叫做自变量
    public static int f() {
        int a = 5;
        int b = 3;
        return a + b;//return : 返回
    }

}
