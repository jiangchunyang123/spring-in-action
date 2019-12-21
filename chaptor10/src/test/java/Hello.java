import org.junit.Test;

public class Hello {
   public static volatile int race =0;
   public static void increase(){
       race++;
   }

    public static void main(String[] args) {
        Thread[] threads = new Thread[20];
        for(int i=0;i<threads.length;i++){
            threads[i] = new Thread(){
                @Override
                public void run(){
                    for(int i =0;i< 10000;i++){
                        increase();
                    }
                }
            };
        threads[i].start();
        }
        while (Thread.activeCount()>1){
            Thread.yield();
        }
        System.out.println(race);
    }
    public void test(){

    }
}
