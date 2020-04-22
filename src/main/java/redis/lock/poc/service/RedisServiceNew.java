package redis.lock.poc.service;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.lock.poc.entity.OtaData;

/**
 * Created by aykumar on 4/12/2018.
 */
@Service
public class RedisServiceNew {

    private static final Logger logger = LoggerFactory.getLogger(RedisServiceNew.class);
    @Autowired
    RedissonClient redissonClient;

    public String isLockAvaliableGetLock(OtaData otaData) {
        // get all request in SET
        RLock ibxLock = redissonClient.getFairLock(otaData.getSubnet());
        RLock lagLock = redissonClient.getFairLock(otaData.getLagGrpId());
        RLock[] locks = null;
        logger.info("Fair lock created for both ibxLock: {} and lagLock: {}", ibxLock.toString(), lagLock.toString());
        if (!(ibxLock.isLocked() || lagLock.isLocked())) {
            locks = new RLock[2];
            locks[0] = ibxLock;
            locks[1] = lagLock;
            locks[0].lock();
            locks[1].lock();
            doOTATask(otaData);
            locks[0].unlock();
            locks[1].unlock();
            return "success";

        }
        return "fail";
    }

    private String doOTATask(OtaData otaData) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // otaLockMap.remove(emp);
        return "Task executed succesfully doOTATask***";
    }
}
