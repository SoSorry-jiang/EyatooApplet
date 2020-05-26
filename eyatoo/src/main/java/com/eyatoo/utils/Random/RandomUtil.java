package com.eyatoo.utils.Random;

import java.util.Random;

/**
 * 公用随机数类
 */
public class RandomUtil {

    //随机纯数字
    public static String number(){
        Random dom = new Random();

        String number = dom.nextInt(100) + String.valueOf(System.currentTimeMillis());
        return number;
    }

    //随机纯数字，短
    public static Integer lessNumber(){
        Random random = new Random();
        Integer number = random.nextInt(1000000000);
        return number;
    }


    //有限制长度的数字和字母的随机数
    public static String LetterNumber(){
        //限制总长度(10-15)
        Random dom = new Random();
        Random dom2 = new Random();
        int num =  dom.nextInt(10) + dom2.nextInt(10);
        while (num<=10){
            num = dom.nextInt(10) + dom2.nextInt(10);
            if (num>15){
                num = 15;
            }
        }
        char[] Lnumber = new char[num--];
        int i = 0;
        while (i < num) {
            Lnumber[i] = (char) ('0' + Math.random() * 10);
            i++;
            if (i>num){
                break;
            }
            Lnumber[i] = (char) ('a' + Math.random() * 26);
            i++;
            if (i>num){
                break;
            }
            Lnumber[i] = (char) ('A' + Math.random() * 26);
            i++;
            if (i>num){
                break;
            }
        }
        return String.valueOf(Lnumber);
    }

}