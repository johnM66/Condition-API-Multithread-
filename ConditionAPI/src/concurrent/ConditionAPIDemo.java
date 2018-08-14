package concurrent;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionAPIDemo {
  public static void main(String[] args) {
	   List<Integer> list =new LinkedList<Integer>();
	   Lock lock=new ReentrantLock();
	   Condition producer=lock.newCondition();
	   Condition consumer=lock.newCondition();
	   Producer pr=new Producer(list, lock, producer, consumer);
	   Consumer con=new Consumer(list, lock, producer, consumer);
	   Thread t1=new Thread(pr,"ProducerThread");
	   Thread t2=new Thread(con,"ConsumerThread");
	   t1.start();
	   t2.start();
}
}
