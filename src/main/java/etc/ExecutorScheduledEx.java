package etc;

import java.util.Random;
import java.util.concurrent.*;

/**
 * $Revision: $
 * $Author:  $
 * $Date:  $
 */
public class ExecutorScheduledEx {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        ScheduledExecutorService es = Executors.newScheduledThreadPool(
                Runtime.getRuntime().availableProcessors());


        runnableScheduler(es);


        fixedScheduler(es);

        es.shutdown();
    }

    private static void runnableScheduler(ScheduledExecutorService es) throws InterruptedException {



        ScheduledFuture<?> res1 =  es.scheduleAtFixedRate(new RunnableScheduledFuture<String>() {
            @Override
            public boolean isPeriodic() {
                return true;
            }

            @Override
            public long getDelay(TimeUnit unit) {
                return 1000;
            }

            @Override
            public int compareTo(Delayed o) {
                return 0;
            }

            @Override
            public void run() {

                System.out.println("run");
                System.out.println(new Random().nextInt());

            }

            @Override
            public boolean cancel(boolean mayInterruptIfRunning) {
                return true;
            }

            @Override
            public boolean isCancelled() {
                return false;
            }

            @Override
            public boolean isDone() {
                return false;
            }

            @Override
            public String get() throws InterruptedException, ExecutionException {
                return null;
            }

            @Override
            public String get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
                return "Done.";
            }
        }, 1000, 1000, TimeUnit.MILLISECONDS);


        waitAndCancel(res1);

    }

    private static void fixedScheduler(ScheduledExecutorService es) throws InterruptedException {
        ScheduledFuture<?> r = es.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                System.out.println(new Random().nextInt());

            }
        }, 1000, 1000, TimeUnit.MILLISECONDS);

        waitAndCancel(r);
    }

    private static void waitAndCancel(ScheduledFuture<?> r) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);


        System.out.println("cancel " + r.cancel(true));
    }
}
