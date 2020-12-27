package com.gri.alex.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.gri.alex.exceptions.OrderNotFoundException;
import com.gri.alex.orderentity.Order;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@EnableResourceServer
@EnableWebSecurity
@RestController
@RequestMapping("/orders")
public class OrderProcessingController extends WebSecurityConfigurerAdapter {

    private Map<String, Order> orders = new HashMap<>();

    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        log.info("Received Order For {} Items", order.getItems().size());
        order.getItems().forEach(lineItem ->
                        log.info("Item: {} Quantity: {}", lineItem.getItemCode(), lineItem.getQuantity()));

        String orderId = UUID.randomUUID().toString();
        order.setOrderId(orderId);
        orders.put(orderId, order);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable String id) throws OrderNotFoundException {
        if(orders.containsKey(id)){
            return new ResponseEntity<>(orders.get(id), HttpStatus.OK);
        } else {
            throw new OrderNotFoundException();
        }
    }

    @Bean
    public ResourceServerTokenServices tokenServices() {
        RemoteTokenServices tokenServices = new RemoteTokenServices();
        tokenServices.setClientId("OrderProcessingService");
        tokenServices.setClientSecret("OrderProcessingServiceSecret");
        tokenServices.setCheckTokenEndpointUrl("http://localhost:8085/oauth/check_token");

        return tokenServices;
    }

    @Override
    public AuthenticationManager authenticationManagerBean() {
        OAuth2AuthenticationManager authenticationManager = new OAuth2AuthenticationManager();
        authenticationManager.setTokenServices(tokenServices());

        return authenticationManager;
    }

}
