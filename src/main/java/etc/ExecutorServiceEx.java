package etc;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.*;

/**
 * $Revision: $
 * $Author:  $
 * $Date:  $
 */
public class ExecutorServiceEx {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        executorService.execute(new Runnable() {
            public void run() {
                System.out.println("Asynchronous task");
                try {
                    TimeUnit.SECONDS.sleep(5);
                } catch (InterruptedException ignore) {
                    ignore.printStackTrace();
                }
            }
        });

        System.out.println(executorService.awaitTermination(2, TimeUnit.SECONDS));
        executorService.shutdown();
        System.out.println("down1");


        System.out.println("    ==============  nextExecutor    ============");


        ExecutorService executorService2 = Executors.newSingleThreadExecutor();

        Set<Callable<String>> callables = new HashSet<Callable<String>>();

        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Task 1";
            }
        });
        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Task 2";
            }
        });
        callables.add(new Callable<String>() {
            public String call() throws Exception {
                return "Task 3";
            }
        });

        List<Future<String>> futures = executorService2.invokeAll(callables);

        for (Future<String> future : futures) {
            System.out.println("future.get = " + future.get());
        }

        executorService2.shutdown();
        System.out.println("down2");

        System.out.println("    ==============  nextExecutor    ============");


        ScheduledExecutorService executorService3 = Executors.newScheduledThreadPool(10);

        ScheduledFuture<String> result = executorService3.schedule(
                new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        return "Op A";
                    }
                }
                , 2
                , TimeUnit.SECONDS);

        System.out.println( result.get());
        executorService3.shutdown();
        System.out.println("down3");

    }


}
