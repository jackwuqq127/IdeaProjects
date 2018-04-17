package person.jack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AppTwoHello {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${spring.profiles.active}")
    private String active;

    @RequestMapping("/getAppOneMesg/{mesg}")
    public @ResponseBody String getAppOneMesg(@PathVariable String mesg){
        String strMesg=restTemplate.getForObject("http://APPONE/sayMesg/" + mesg, String.class);
        return "访问App2,从App1 收到信息：“"+strMesg+"”";
    }

    @RequestMapping("/info")
    public @ResponseBody String info(){
        return "这里是AppOne，端口："+active;
    }

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/discoveryClientList")
    public List discoveryClientList(){
        /*获取Eureka 中的服务名称列表*/
        List<String> services = discoveryClient.getServices();
        return services;
    }

    @GetMapping("/discoveryClientInstanceList")
    public List<Map> discoveryClientInstanceList(){
        /*获取客户端实例列表*/

        List<Map> list = new ArrayList<Map>();
        Map<String, Object> map = new HashMap<String, Object>();
        List<String> servicesNames = discoveryClient.getServices(); //获取名称
        for (String name : servicesNames) {
            map = new HashMap<String, Object>();
            List<ServiceInstance> instanceList = discoveryClient.getInstances(name);
            Map objs = new HashMap();
            for (ServiceInstance instance : instanceList) {
                objs.put("host", instance.getHost());
                objs.put("port", instance.getPort());
                objs.put("serviceId",instance.getServiceId());
                objs.put("uri", instance.getUri());
            }
            map.put(name, objs);
            list.add(map);
        }
        return list;
    }

    @GetMapping("/metaMap")
    public @ResponseBody List metaMap(){
        List<ServiceInstance> instanceList = discoveryClient.getInstances("AppTwo");
        List list = new ArrayList();
        for (ServiceInstance instance : instanceList) {
            Map map=instance.getMetadata();
            list.add(map);
        }
        return list;
    }
}
