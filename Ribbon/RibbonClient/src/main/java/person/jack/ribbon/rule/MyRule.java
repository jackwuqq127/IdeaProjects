package person.jack.ribbon.rule;

import com.netflix.loadbalancer.BaseLoadBalancer;
import com.netflix.loadbalancer.ILoadBalancer;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.Server;

import java.util.List;
import java.util.Random;

public class MyRule implements IRule {
    private ILoadBalancer lb;

    //负载均衡器选择服务器
    @Override
    public Server choose(Object key) {
        int n = new Random().nextInt(10) + 1;
        if (n < 2) {
            return getServerByPort(8080);
        }
        return getServerByPort(8081);
    }

    //工具方法，根据端口获取服务器
    private Server getServerByPort(int port) {
        List<Server> serverList = lb.getAllServers();
        for (Server server : serverList) {
            if (server.getPort() == port) {
                return server;
            }
        }
        return null;
    }

    //设置负载均衡器
    @Override
    public void setLoadBalancer(ILoadBalancer lb) {
        this.lb = lb;
    }

    //获取负载均衡器
    @Override
    public ILoadBalancer getLoadBalancer() {
        return this.lb;
    }
}
