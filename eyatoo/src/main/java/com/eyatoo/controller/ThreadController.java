package com.eyatoo.controller;

import com.eyatoo.pojo.User;
import com.eyatoo.service.UserService;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class ThreadController implements Runnable{
    private static List list = new ArrayList();

    private static volatile Integer flag = 0;

    @Resource
    private static UserService userService;

    @Override
    public void run() {
        if(list.size() <= 10){
            pop();
            System.out.println("------线程"+Thread.currentThread().getName()+"正在运行");
            System.out.println("------线程"+Thread.currentThread().getName()+"flag=="+flag);
        }
    }

    public synchronized  Object pop() {
        while (flag == 0){
            System.out.println("------线程"+Thread.currentThread().getName()+"执行等待方法");
            put(1);
            put(2);
            try {
                this.wait();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        if(list.size() > 0){
            System.out.println("------线程"+Thread.currentThread().getName()+"执行删除方法");
            flag = 2;
            this.notify();
            return  list.remove(0);
        }else {
            System.out.println("------线程"+Thread.currentThread().getName()+"无");
            return null;
        }
    }

    public  synchronized  Object put(Object object){
        System.out.println("------线程"+Thread.currentThread().getName()+"执行添加方法");
        flag = 1;
        list.add(object);
//        try {
//            this.wait();
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
        return 1;
    }

    public static void main(String [] arge) throws Exception{

            List<User> userList = new ArrayList<>();
            Thread thread1 = new Thread("1"){
                public void run(){
                   User user = new User();
                   user.setId(1);
                   user.setName("giaoge");
                   user.setIsAgent(1);
                   synchronized (userList){
                       userList.add(user);
                       System.out.println(Thread.currentThread().getName()+"已处理数据并添加进数组了");
                       try {
                           userList.wait();
                       }catch (Exception e) {
                           e.printStackTrace();
                       }
                   }
                }
            };
            Thread thread2 = new Thread("2"){

                public void run(){
                    synchronized (userList){
//                        Integer isOK = this.
//                        System.out.println(Thread.currentThread().getName()+"从数据库取出的数据为：");
                    }
                }
            };
            thread1.start();
            thread2.start();
//          ThreadController threadController = new ThreadController();
//            Thread t1=new Thread(threadController,"t1");
//            Thread t2=new Thread(threadController,"t2");
//            t1.start();
//            t2.start();
//            t1.join();
//            t2.join();
//            System.out.println("listSize=="+list.size());
//            System.out.println("finallayFlag=="+flag);
//        //写两个线程 1.图片下载
//        Object obj=new Object();
//        Thread download= new Thread(){
//            public void run() {
//                System.out.println("开始下载图片");
//                for (int i = 0; i < 101; i+=10) {
//                    System.out.println("down"+i+"%");
//                    try {
//                        Thread.sleep(50);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                System.out.println("图片下载成功");
//                synchronized (obj) {
//                    obj.notify();//唤起
//                    try {
//                        obj.wait();//阻塞当前
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                System.out.println("开始下载附件");
//                for (int i = 0; i < 101; i+=10) {
//                    System.out.println("附件下载"+i+"%");
//
//                    try {
//                        Thread.sleep(50);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                System.out.println("附件下载成功");
//            }
//        };
//        //2.图片展示
//        Thread show=new Thread(){
//            public void run(){
//                synchronized (obj) {
//                    try {
//                        obj.wait();//阻塞当前
//                        obj.notify();
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                System.out.println("show:开始展示图片");
//                System.out.println("图片展示完毕");
//            }
//        };
//
//        download.start();
//        show.start();

    }
}
