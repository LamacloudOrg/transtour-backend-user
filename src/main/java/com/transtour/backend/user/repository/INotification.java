package com.transtour.backend.user.repository;

import com.transtour.backend.user.dto.ActivationAccountDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Qualifier("NotificationClient")
@FeignClient(name = "SPRING-CLOUD-NOTIFICATION-API")
public interface INotification {

    @RequestMapping(method = RequestMethod.POST, value = "/v1/notification/activation/code")
    Void sendActivationCode(@RequestBody ActivationAccountDTO activationAccountDTO);
}