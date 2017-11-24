package sales.controllers;

import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Processor;
import org.springframework.messaging.handler.annotation.SendTo;
import sales.model.StaffB;
import sales.services.SalesRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.InterfaceAddress;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@EnableBinding(Processor.class)
@Controller    // This means that this class is a Controller
@RequestMapping(path="/sales") // This means URL's start with /demo (after Application path)
@Slf4j
@EnableDiscoveryClient
@EnableEurekaClient
@EnableFeignClients
public class MainController {

	@Autowired
	private SalesRecordService salesRecordService;
	RestTemplate restTemplate = new RestTemplate();


	/*
	@PostMapping(path="/getSales")
	public @ResponseBody SalesRecord getSalesRecord(@RequestBody StaffB staffB)  {
		try{
			log.info(staffB.toString());
            //staffB= mapper.readValue((JsonParser) httpEntity.getBody(),StaffB.class);
			//log.info(staffB.toString());
			SalesRecord salesRec = salesRecordRepository.getSalesRecord(staffB.getFName());
			return (salesRec);
		}catch(NullPointerException e){
			System.out.println(e.getStackTrace());
			return null;
		}
				//
	}
	@Autowired
	private SalesRecordRepository salesRecordRepository;
	@PostMapping(path="/getSalestest")
	public @ResponseBody String getSalesRecordtest(@RequestBody StaffB staffB)  {
		try{
			log.info(staffB.toString());
			//staffB= mapper.readValue((JsonParser) httpEntity.getBody(),StaffB.class);
			//log.info(staffB.toString());
			List some = salesRecordRepository.getSalesRecord(staffB.getFName());
			return some.toString();
		}catch(NullPointerException e){
			System.out.println(e.getStackTrace());
			return null;
		}
		//
	}*/

	//@PostMapping(path="/getSales",produces = "application/json; charset=UTF-8")
	//public ResponseEntity getSalesRecord (@RequestBody StaffB staffB) {
	//	return salesRecordService.getSalesRec(staffB);
	//}
	@PostMapping(path="/getSales")
	public ResponseEntity getSalesRecord (@RequestBody StaffB staffB) {
		return salesRecordService.getSalesRec(staffB);
	}


	@PostMapping(path="/getSalesTest")
	public @ResponseBody String getSalesRecordTest (){
		return "Testing on requestmapping and postmapping";
	}

	/*
	@StreamListener(value=Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	public String ReplyToProducer(final String message){
		if (message.equals("Sending this string to Topic")){
			return "I got the message from Producer";
		}
		return (message +"Didn't get the correct message");
	}*/
	@StreamListener(value=Processor.INPUT)
	@SendTo(Processor.OUTPUT)
	public StaffB ReplyToProducer(StaffB message){
		if (message.getFName().equals("ronny")){
			return message;
		}
		message.setFName("Not working!");
		return (message);
	}



}
