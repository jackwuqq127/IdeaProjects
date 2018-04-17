package person.jack.ribbon.client;

import com.netflix.client.ClientFactory;
import com.netflix.client.http.HttpRequest;
import com.netflix.client.http.HttpResponse;
import com.netflix.config.ConfigurationManager;
import com.netflix.niws.client.http.RestClient;
import org.junit.jupiter.api.Test;

public class RibbonClientTest {

    @Test
    public void testBalancer() throws Exception {
        ConfigurationManager.getConfigInstance().setProperty(
                "myClient.ribbon.listOfServers", "localhost:8080,localhost:8081");
        RestClient client = (RestClient) ClientFactory.getNamedClient("myClient");
        HttpRequest request = HttpRequest.newBuilder().uri("/info").build();
        for(int i = 0; i < 10; i++) {
            HttpResponse response = client.executeWithLoadBalancer(request);
            String json = response.getEntity(String.class);
            System.out.println(json);
        }
    }
}
