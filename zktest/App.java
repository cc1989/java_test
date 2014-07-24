package Test.zktest;

import java.io.FileNotFoundException;

import org.apache.log4j.Logger;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.util.Log4jConfigurer;

/**
 * 
 * @ClassName: App 
 * @Description: zookeeper�ͻ��˱�д
 * @author huangchen.hc@alibaba-inc.com
 * @date 2014��7��12�� ����5:54:53 
 *
 */
public class App 
{
    public static void main( String[] args ) throws Exception
    {
    	try {
			Log4jConfigurer.initLogging("src/main/resources/log4j.properties", 10);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	//����һ��Zookeeperʵ������һ������ΪĿ���������ַ�Ͷ˿ڣ��ڶ�������ΪSession��ʱʱ�䣬������Ϊ�ڵ�仯ʱ�Ļص�����
    	 ZooKeeper zk = new ZooKeeper("10.73.145.135:2181", 
    		        50000, new Watcher() { 
    		            // ������б��������¼�
    		            public void process(WatchedEvent event) { 
    		                System.out.println("�Ѿ�������" + event.getPath() + "�¼���"); 
    		            } 
    		        }); 
    	//����һ���ڵ�root��������mydata,������ACLȨ�޿��ƣ��ڵ�Ϊ�����Ե�(���ͻ���shutdown��Ҳ������ʧ)
    	zk.create("/root1", "mydata".getBytes(),Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

    	//��root���洴��һ��childone znode,����Ϊchildone,������ACLȨ�޿��ƣ��ڵ�Ϊ�����Ե�
    	zk.create("/root/childone","childone".getBytes(), Ids.OPEN_ACL_UNSAFE,CreateMode.PERSISTENT);

    	//ȡ��/root�ڵ��µ��ӽڵ�����,����List<String>
    	zk.getChildren("/root",true);

    	//ȡ��/root/childone�ڵ��µ�����,����byte[]
    	zk.getData("/root/childone", true, null);

    	//�޸Ľڵ�/root/childone�µ����ݣ�����������Ϊ�汾�������-1���ǻ����ӱ��޸ĵ����ݰ汾��ֱ�Ӹĵ�
    	zk.setData("/root/childone","childonemodify".getBytes(), -1);

    	//ɾ��/root/childone����ڵ㣬�ڶ�������Ϊ�汾����1�Ļ�ֱ��ɾ�������Ӱ汾
    	//zk.delete("/root/childone", -1);
    	      
    	//�ر�session
    	zk.close();
    	
    	Thread.sleep(10000);
    }
}
