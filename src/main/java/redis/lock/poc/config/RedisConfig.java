package redis.lock.poc.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by aykumar on 3/5/2018.
 */
@Configuration
public class RedisConfig {
    @Value("${redis.host}")
    String host;

    @Value("${redis.port}")
    String port;


    /**
     * Redis server is running on my VM gdc-java64-26 @return
     */
    @Bean
    public RedissonClient getRedissionClient() {
        Config config = new Config();
        SingleServerConfig singleServerConfig = config.useSingleServer();

        singleServerConfig
                .setAddress("redis://" + host + ":" + port);
        // 10000 mili sec.. i.e 10 sec
        singleServerConfig.setDnsMonitoringInterval(3000);
        singleServerConfig.setKeepAlive(true);
        singleServerConfig.setDnsMonitoring(true);
        return Redisson.create(config);
    }
}
