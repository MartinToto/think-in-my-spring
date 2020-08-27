import util.ZooKeeperSession;

/**
 * @author Martin
 * @description
 * @date 2020/5/6
 */
public class ZooKeeperSessionTest {
    public static void main(String[] args) {
        ZooKeeperSession zooKeeperSession = new ZooKeeperSession();
        Long productId = 123L;
        Boolean aBoolean = zooKeeperSession.acquireDistributedLock(productId);
        System.out.println(aBoolean);
        System.out.println("hello word");
//        zooKeeperSession.releaseDistributedLock(productId);

    }
}
