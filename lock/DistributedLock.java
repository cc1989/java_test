package Test.locktest;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.util.Log4jConfigurer;

import Test.lock.WriteLock;

public class DistributedLock {

	private WriteLock lock;
	private String lockPath = "/lock";
	private ZooKeeper zooKeeper ;

	public DistributedLock(String url) throws IOException{
		Watcher wh=new Watcher(){
			public void process(org.apache.zookeeper.WatchedEvent event)
			{
				System.out.println(Thread.currentThread().getName() + " : " + event.toString());
			}
		};
		zooKeeper = new ZooKeeper(url, 20000, wh);
		lock = new WriteLock(zooKeeper, lockPath, null); 
	}

	/**
	 * 获得锁
	 * 
	 * Author:  chenkangxian
	 *
	 * Last Modification Time: 2012-4-6
	 *
	 * @return 获得锁是否成功
	 */
	public boolean lock(){
		
		try {
			while (true) {
				if (lock.lock()) {
					return true;
				}
				Thread.sleep(1000);
			}
		} catch (KeeperException e) {
			e.printStackTrace();
			return false;
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * 解锁
	 * 
	 * Author:  chenkangxian
	 *
	 * Last Modification Time: 2012-4-6
	 *
	 */
	public void unlock(){
		lock.unlock();
	}
	
	public static void main(String args[]) throws FileNotFoundException{
		Log4jConfigurer.initLogging("src/main/resources/log4j.properties", 10);
		try {
					
			for(int i = 0; i < 5 ; i ++){
				Thread thread = new Thread(new Runnable(){
					DistributedLock lock = new DistributedLock("10.73.145.135:2181");
					public void run() {
						if(lock.lock()){
							System.out.println(Thread.currentThread().getName() + " : 获得锁---------------");
						
						}
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						lock.unlock();
						System.out.println(Thread.currentThread().getName() + " : 释放锁---------------");
					}
					
				});
				thread.start();
			}

			Thread.sleep(2000*1000);

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
