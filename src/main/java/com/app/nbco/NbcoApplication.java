package com.app.nbco;

import com.app.nbco.role.entity.Role;
import com.app.nbco.role.repository.RoleRepository;
import com.app.nbco.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableTransactionManagement(proxyTargetClass = true)
public class NbcoApplication {

    public static void main(String[] args) {
        SpringApplication.run(NbcoApplication.class, args);
    }

}
