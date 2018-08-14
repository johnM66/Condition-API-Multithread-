package concurrent;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Producer implements Runnable {
	
	private List<Integer> sharedQueue;
	private int max_size=2;
	Lock lock;
	Condition producerCondition;
	Condition consumerCondition;
	
	
	
	public Producer(List<Integer> sharedQueue, Lock lock, Condition producerCondition, Condition consumerCondition) {
		super();
		this.sharedQueue = sharedQueue;
		this.lock = lock;
		this.producerCondition = producerCondition;
		this.consumerCondition = consumerCondition;
	}



	@Override
	public void run() {
		
		for(int i=1;i<=10;i++) {
			try {
				produce(i);
			} catch (InterruptedException e) {
				
				e.printStackTrace();
			}
		}
	}



	private void produce(int i) throws InterruptedException {
		lock.lock();
		if(sharedQueue.size()==max_size) {
			producerCondition.await();
		}
		
		System.out.println("Produces"+i);
		sharedQueue.add(i);
		producerCondition.signal();
		lock.unlock();
		
	}

}
