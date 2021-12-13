package com.example.apachecamelsql;

import com.github.javafaker.Faker;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

  @Autowired
  private CustomerRepository customerRepository;

  Faker faker = new Faker();


  @GetMapping(value = {"//customers/generate", "/customers/generate/{count}"})
  public List<Customer> getAllEmployees(@PathVariable(required = false) Integer count) {

    count = count == null ? 5 : count;

    Collection<Customer> newCustomerList = new ArrayList<>();
    for (int i = 0; i < count; i++) {

      Customer customer = new Customer();
      customer.setName(faker.name().name());
      customer.setEmail(faker.internet().emailAddress());
      customer.setAge(faker.random().nextInt(1, 100));

      newCustomerList.add(customer);
    }

    return customerRepository.saveAll(newCustomerList);

  }

}
