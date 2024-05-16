package wxb;

public class day08 {

    public static void main(String args[]) {

        System.out.println(velocity(100.5, 10));

        System.out.println(pool(5, 10));

    }


    /**
     * 计算速度  velocity:速度
     * @param distance 距离，单位是米(m)
     * @param time 时间，单位是秒(s)
     * @return
     */
    public static double velocity(double distance, double time) {

        double vel = distance / time;
        return vel;
    }

    /**
     * 水池放水时间问题
     * @param time1 只开进水龙头放满水的时间，小时(h)
     * @param time2 只开排水龙头排完水的时间，小时(h)
     * @return
     */
    public static double pool(double time1, double time2) {

//        1、假设水池总容量是：V（volumn:容积、体积、音量）
        double V = 100;
//        进水龙头进水的速度就是：
        double v1 = V / time1;
//        排水龙头排水的速度：
        double v2 = V / time2;
//      进水、排水一起开时，进水的速度
        double v3 = v1 - v2;
//      总时间
        double time = V / v3;
        return time;
    }

}
