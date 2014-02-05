package etc;

import java.util.concurrent.LinkedTransferQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TransferQueue;

/**
 * $Revision: $
 * $Author:  $
 * $Date:  $
 */
public class TransferQueueEx {
    public static void main(String[] args) throws InterruptedException {


        final TransferQueue transferQueue = new LinkedTransferQueue();

        final String payLoad1 = "payLoad";
        final String payLoad2 = "payLoad2";

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("==transferQueue.hasWaitingConsumer(): " +
                            transferQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }).start();

        TimeUnit.SECONDS.sleep(1);


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    System.out.println("==transferQueue.hasWaitingConsumer(): " +
                            transferQueue.hasWaitingConsumer());

                    transferQueue.transfer(payLoad1);
                    transferQueue.transfer(payLoad2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


            }
        }).start();


        TimeUnit.SECONDS.sleep(1);


        System.out.println("==transferQueue.hasWaitingConsumer(): " +
                transferQueue.remove());


    }
}
