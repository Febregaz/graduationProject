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
    public void testEnter(){
        int A[]={4,-3,5,-2,-1,2,6,-2};
        System.out.println(MaxSubSum(A,0,7));
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

    public int MaxSubSum(int A[] , int Left , int Right){
        int MaxLeftSum,MaxRightSum;
        int MaxLeftBorderSum,MaxRightBorderSum;
        int LeftBorderSum,RightBorderSum;
        int Center,i;
        if(Left == Right){
            if(A[Left] > 0){
                return A[Left];
            }
            else {
                return 0;
            }
        }
        Center = (Left + Right)/2;
        MaxLeftSum = MaxSubSum(A , Left , Center);
        MaxRightSum = MaxSubSum(A , Center+1 , Right);
        MaxLeftBorderSum = 0; LeftBorderSum = 0;
        for(i = Center ; i>=Left ; i--){
            LeftBorderSum+=A[i];
            if(LeftBorderSum > MaxLeftBorderSum){
                MaxLeftBorderSum = LeftBorderSum;
            }
        }
        MaxRightBorderSum = 0; RightBorderSum = 0;
        for(i = Center+1 ; i<=Right ; i++){
            RightBorderSum+=A[i];
            if(RightBorderSum>MaxRightBorderSum){
                MaxRightBorderSum = RightBorderSum;
            }
        }
        if(MaxLeftSum>=MaxRightSum&&MaxLeftSum>=(MaxLeftBorderSum+MaxRightBorderSum)){
            return MaxLeftSum;
        }
        else if(MaxRightSum>=MaxLeftSum&&MaxRightSum>=(MaxLeftBorderSum+MaxRightBorderSum)){
            return MaxRightSum;
        }
        else if((MaxLeftBorderSum+MaxRightBorderSum)>=MaxLeftSum&&(MaxLeftBorderSum+MaxRightBorderSum)>=MaxRightSum){
            return MaxLeftBorderSum+MaxRightBorderSum;
        }
        return -1;
    }

}
