import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * Created by xx on 2017/5/31.
 */
public class ZooKeeperTest {

    private static final String HOST2 = "172.18.70.34:2181";
    private static final String HOST1 = "172.18.70.16:2181";
    private static final Integer TIMEOUT = 2000;

    public static void main(String[] args) {
        try {
            ZooKeeper zooKeeper1 = new ZooKeeper(HOST1, TIMEOUT, null);
            ZooKeeper zooKeeper2 = new ZooKeeper(HOST2, TIMEOUT, null);
            System.out.println("16 创建节点...");

            if (zooKeeper2.exists("/tests", false) == null) {
                zooKeeper2.create("/tests", "zNode1".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            }

            System.out.println("=========查看节点是否创建成功========");
            System.out.println(new String(zooKeeper1.getData("/tests", false, null)));



            System.out.println("========修改节点的数据========");
            System.out.println("16 修改节点...");
            String data = "zNode2";
            zooKeeper2.setData("/tests",data.getBytes(),-1);
            System.out.println("========查看修改的节点是否成功=========");
            System.out.println(new String(zooKeeper1.getData("/tests", false, null)));


            System.out.println("========删除节点========");
            System.out.println("16 删除节点...");
            zooKeeper2.delete("/tests",-1);
            System.out.println("========查看节点是否被删除=========");
            System.out.println("节点状态:" + zooKeeper2.exists("/tests",false));


            System.out.println("=========查看节点是否存在========");
            System.out.println("15 查看节点...");
            System.out.println(new String(zooKeeper1.getData("/tests", false, null)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (KeeperException e) {
            e.printStackTrace();
        }


    }
}
