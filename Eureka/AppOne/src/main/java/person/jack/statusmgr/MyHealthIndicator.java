package person.jack.statusmgr;

import com.netflix.appinfo.HealthCheckHandler;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.cloud.netflix.eureka.EurekaHealthCheckHandler;
import org.springframework.stereotype.Component;
import person.jack.controller.HelloController;

@Component
public class MyHealthIndicator implements HealthIndicator {
    public Health health() {
        if(HelloController.isHealth){ //节点是否健康的判断依据，UP:健康
            return new Health.Builder(Status.UP).build();
        }
        return new Health.Builder(Status.DOWN).build(); //DOWN: 不健康
    }
}

@Component
class MyHealthHander implements HealthCheckHandler{

    @Autowired
    MyHealthIndicator myHealthIndicator;
    public InstanceInfo.InstanceStatus getStatus(InstanceInfo.InstanceStatus instanceStatus) {
        Status status = myHealthIndicator.health().getStatus();
        if (status.equals(Status.UP)){
            return InstanceInfo.InstanceStatus.UP;
        }else if(status.equals(Status.DOWN)){
            return InstanceInfo.InstanceStatus.DOWN;
        }
        return InstanceInfo.InstanceStatus.UNKNOWN;
    }
}
