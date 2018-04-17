package person.jack.ribbon.client;

import com.netflix.client.ClientFactory;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.config.ConfigurationManager;
import com.netflix.loadbalancer.*;
import com.netflix.niws.client.http.RestClient;
import org.junit.jupiter.api.Test;
import person.jack.ribbon.rule.MyRule;

import java.util.ArrayList;
import java.util.List;

public class LBTest {

    @Test
    public void testBalancer() {
        //负载均衡器对象
        ILoadBalancer loadBalancer = new BaseLoadBalancer();

        //准备服务器列表
        List<Server> serverList = new ArrayList<Server>();
        serverList.add(new Server("localhost", 8080));
        serverList.add(new Server("localhost", 8081));

        //将服务器列表加载到负载均衡器
        loadBalancer.addServers(serverList);
        for (int i = 0; i < 10; i++) {
            Server server = loadBalancer.chooseServer(null);
            System.out.println(server);
        }
    }

    @Test
    public void testRule() {
        //负载均衡器对象
        BaseLoadBalancer lb = new BaseLoadBalancer();
        MyRule rule = new MyRule();
        rule.setLoadBalancer(lb);
        lb.setRule(rule);

        //准备服务器列表
        List<Server> serverList = new ArrayList<Server>();
        serverList.add(new Server("localhost", 8080));
        serverList.add(new Server("localhost", 8081));

        //将服务器列表加载到负载均衡器
        lb.addServers(serverList);
        for (int i = 0; i < 10; i++) {
            Server server = lb.chooseServer("key");
            System.out.println(server);
        }
    }

    @Test
    public void testMyRuleBalancer() throws Exception {
        ConfigurationManager.getConfigInstance().setProperty(
                "myClient.ribbon.listOfServers", "localhost:8080,localhost:8081");
        ConfigurationManager.getConfigInstance().setProperty(
                "myClient.ribbon.NFLoadBalancerRuleClassName",
                MyRule.class.getName());
        RestClient client = (RestClient) ClientFactory.getNamedClient("myClient");
        HttpRequest request = HttpRequest.newBuilder().uri("/info").build();
        for (int i = 0; i < 10; i++) {
            HttpResponse response = client.executeWithLoadBalancer(request);
            String json = response.getEntity(String.class);
            System.out.println(json);
        }
    }
}
