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

	public DistributedLock(ZooKeeper zooKeeper){
		this.zooKeeper = zooKeeper;
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
		lock = new WriteLock(zooKeeper, lockPath, null);
		try {
			while (true) {
				if (lock.lock()) {
					return true;
				}

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
			Watcher wh=new Watcher(){
				public void process(org.apache.zookeeper.WatchedEvent event)
				{
					System.out.println(event.toString());
				}
			};

			ZooKeeper zooKeeper = new ZooKeeper("10.73.145.135:2181", 20000, wh);
			final DistributedLock distributedLock = new DistributedLock(zooKeeper);
			
			
			for(int i = 0; i < 100 ; i ++){
				Thread thread = new Thread(new Runnable(){
					public void run() {
						if(distributedLock.lock()){
							System.out.println("获得锁---------------");
						
						}
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						distributedLock.unlock();
						
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
