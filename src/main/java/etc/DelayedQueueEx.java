package etc;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * $Revision: $
 * $Author:  $
 * $Date:  $
 */
public class DelayedQueueEx {


    public static void main(String[] args) throws InterruptedException {
        DelayQueue queue = new DelayQueue();

        Delayed element1 = new DelayedElement(5000, "elem1");
        Delayed element2 = new DelayedElement(7000, "elem2");

        queue.put(element1);
        queue.put(element2);

        while (!queue.isEmpty())
            queue.take();

    }


}

class DelayedElement implements Delayed {

    private final long time;
    private final String elementName;

    DelayedElement(long delay, String name) {
        time = System.currentTimeMillis() + delay;
        elementName = name;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long t = time - System.currentTimeMillis();
        if (t % 1000 == 0)
            System.out.println(t + " name: " + elementName);
        return t;
    }

    @Override
    public int compareTo(Delayed o) {
        return 0;
    }
}
