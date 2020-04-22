package redis.lock.poc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import redis.lock.poc.entity.OtaData;
import redis.lock.poc.service.RedisServiceNew;

import java.util.HashMap;

/**
 * Created by aykumar on 3/5/2018.
 */
@Controller

public class RedisController {
    @Autowired
    RedisServiceNew redisService;

    @RequestMapping(value = "/testLock", method = RequestMethod.POST)
    public ResponseEntity<?> testLock(@RequestBody OtaData otaData) {
        String status = "";

//        RMap<String,OtaLock> otaMap =redisConfig.getClient().getMap("otaAppMap");
//        otaMap.clear();
//        String status =redisService.doSomeTask(employee,otaMap);


        try {
            status = redisService.isLockAvaliableGetLock(otaData);


        } catch (Exception e) {
            e.printStackTrace();
        }
        ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(status, HttpStatus.OK);
        return responseEntity;
    }

    @GetMapping("/testl")
    public ResponseEntity testLock(){
        OtaData otaData= new OtaData();
        otaData.setLagGrpId("1");
        otaData.setSubnet("255");
       String status = redisService.isLockAvaliableGetLock(otaData);
       return (status=="success")?new ResponseEntity(HttpStatus.OK)
               : new ResponseEntity(HttpStatus.BAD_REQUEST);
    }
}
