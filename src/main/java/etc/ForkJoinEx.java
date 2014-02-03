package etc;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;



/**
 * $Revision: $
 * $Author:  $
 * $Date:  $
 */
public class ForkJoinEx {

    public static void main(String[] args) {
        Node root = new Node(1);

        Node node1 = new Node(2);
        root.getChildren().add(node1);


        new ForkJoinPool().invoke(new ValueSumCounter(root));
    }


}

class Node {

    private volatile long val;
    private CopyOnWriteArrayList<Node> nodes = new CopyOnWriteArrayList<Node>();

    Node(long val) {
        this.val = val;
    }

    public Collection<Node> getChildren() {
        return nodes;
    }

    public long getValue() {
        return val;
    }

    void setNodes(CopyOnWriteArrayList<Node> nodes) {
        this.nodes = nodes;
    }
}

class ValueSumCounter extends RecursiveTask<Long> {
    private final Node node;

    public ValueSumCounter(Node node) {
        this.node = node;
    }

    @Override
    protected Long compute() {
        long sum = node.getValue();
        List<ValueSumCounter> subTasks = new LinkedList<ValueSumCounter>();

        for (Node child : node.getChildren()) {
            ValueSumCounter task = new ValueSumCounter(child);
            task.fork(); // запустим асинхронно
            subTasks.add(task);
        }

        for (ValueSumCounter task : subTasks) {
            sum += task.join(); // дождёмся выполнения задачи и прибавим результат
        }

        System.out.println("sum= " + sum);
        return sum;
    }

}