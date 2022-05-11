package com.pos.identity.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.pos.identity.service.config.FeignClientInternalConfiguration;

@FeignClient(name = "inventory-service", path = "/", configuration = FeignClientInternalConfiguration.class)
public interface PosServiceFeignClient {

	@GetMapping(value = "user/getUserByUsername")
	@ResponseBody
	ResponseEntity<Object> checkUserIsValidWithAgentCode(@RequestParam("username") String username);

	@GetMapping(value = "agentDetails/checkAgentWithAgentRegNumber")
	@ResponseBody
	ResponseEntity<Object> checkAgentIsValidWithAgentCode(@RequestParam("username") String username,
			@RequestParam("agentRegNumber") String agentRegNumber);

//	@PutMapping(value = "user/updateLockedUser")
//	@ResponseBody
//	ResponseEntity<Object> updateLockedUser(@RequestBody  UserDto userDto);

}
