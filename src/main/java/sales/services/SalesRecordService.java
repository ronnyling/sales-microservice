package sales.services;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import sales.model.ResponseModel;
import sales.model.SalesRecord;
import sales.model.StaffB;
import sales.repositories.SalesRecordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.List;

import static sales.configurations.config.RESPONSE_CODE_1000;
import static sales.configurations.config.RESPONSE_CODE_1999;



@Service
@Slf4j
public class SalesRecordService {
    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    private SalesRecordRepository salesRecordRepository;

    HttpHeaders httpHeaders;


    public ResponseEntity fallbackMethod(StaffB location){
        ResponseModel res =new ResponseModel();
        res.setStatus(9999);
        res.setDataObj("You have reach a timeout on SalesRecordService");
        return new ResponseEntity<>(res,httpHeaders, HttpStatus.OK);
    }

    @HystrixCommand(fallbackMethod = "fallbackMethod", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "20000"),
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "10")
    })
    public ResponseEntity getSalesRec(StaffB staff){
        ResponseModel<List<SalesRecord>> res = new ResponseModel<>();
        res.setStatus(RESPONSE_CODE_1999);
        //System.out.println(staff);
        if(salesRecordRepository.checkExist(staff.getEID())!=null){
            log.info("User already exist.");
            return null;
        }
        res.setStatus(RESPONSE_CODE_1000);
        List<SalesRecord> salesRecords = salesRecordRepository.getSalesRecord(staff.getFName());
        res.setDataObj(salesRecords);
        System.out.println(new ResponseEntity<>(res,httpHeaders, HttpStatus.OK));
        return new ResponseEntity<>(res,httpHeaders, HttpStatus.OK);
    }
}