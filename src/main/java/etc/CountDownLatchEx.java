package etc;

import java.util.concurrent.CountDownLatch;

/**
 * $Revision: $
 * $Author:  $
 * $Date:  $
 */
public class CountDownLatchEx {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(3);

        Waiter      waiter1      = new Waiter(latch);
        Waiter      waiter2      = new Waiter(latch);
        Waiter      waiter3      = new Waiter(latch);
        Decrementer decrementer = new Decrementer(latch);

        new Thread(waiter1)     .start();
        new Thread(waiter2)     .start();
        new Thread(waiter3)     .start();

        new Thread(decrementer).start();

        Thread.sleep(4000);
    }
}

class Waiter implements Runnable{

    CountDownLatch latch = null;

    public Waiter(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Waiter Released: " + Thread.currentThread().getName());
    }
}

class Decrementer implements Runnable {

    CountDownLatch latch = null;

    public Decrementer(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {

        try {
            Thread.sleep(1000);
            this.latch.countDown();

            Thread.sleep(1000);
            this.latch.countDown();

            Thread.sleep(1000);
            this.latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

