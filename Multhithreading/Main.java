// package Multhithreading;
class MyThread extends Thread{
    @Override 
    public void run(){
        for (int i = 0; i < 5; i++) {
           System.out.println(Thread.currentThread().getName() +" : "+i);
        }
    }
}

class MyThread2 implements Runnable{
    @Override
    public void run(){
        for (int i = 0; i < 5; i++) {
           System.out.println(Thread.currentThread().getName() +" : "+i);
        }  
    }
}
public class Main {
    public static void main(String[] args) {
        MyThread t1 = new MyThread();//Object create hua h yha, thread nhi--Implementation via extending Thread class 
        MyThread2 t2 = new MyThread2();//Iske pas khudka start method nhi hai to Thread ka object create
        //krkr usme t2 ka object pas kra jisse run method ki implementation chali jae or Thread object ke pas
        // start method bhi hai to vha se thread.start() kr lenge.
        Thread thread = new Thread(t2);//Implementation via runnable interface
        t1.start();//Thread yha create hua hai
        thread.start();
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() +" : "+i);
        }
    }
}
