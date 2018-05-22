package yuzhaoLiu.project.algorithms;

import org.junit.Test;

import java.util.Random;

public class testAlgorithm {

    /*
    * 两个机器人，机器人A设置数值，机器人B猜数值。
    * B每猜出一个数值，A负责告知B数值比正确值高了还是低了。
    * 最终B根据二分法得到正确数值
    * 数值范围0~999,数据类型为int
    * */
    @Test
    public void testDichotomy(){
        final int range = 1000;
        int robotA , robotB;
        int times = 0;
        robotA = new Random().nextInt(range);//随机生成0~999的int类型数值
        robotB = new Random().nextInt(range);
        dichotomy(robotA , robotB , 1000 , 0 , times);
    }

    public int dichotomy(int robotA , int robotB , int max , int min , int times){
        int middleValue = 0;
        if(robotA>robotB){
            System.out.println("robotA:"+"robotB猜的数小了，哈哈哈");
            middleValue = (max+robotB)/2;//取B的值与最大范围的中间数
            times+=1;
            dichotomy(robotA , middleValue , max , robotB , times);//同时更改最小范围为B的值
        }
        else if(robotA<robotB){
            System.out.println("robotA:"+"robotB猜的数大了，哈哈哈");
            middleValue = (robotB-min)/2;//取B的值与最小范围的中间数
            times+=1;
            dichotomy(robotA , middleValue , robotB , min , times);//同时更改最大范围为B的值
        }
        else if(robotA==robotB){
            System.out.println("robotA:"+robotA);
            System.out.println("robotB:"+robotB);
            System.out.println("robotB:"+"真相只有一个:"+robotB);
            System.out.println("robotA:"+"可是你都猜了"+times+"次了");
            return robotB;
        }
        return 0;
    }

}
