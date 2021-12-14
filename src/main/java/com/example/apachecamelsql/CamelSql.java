package com.example.apachecamelsql;

import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CamelSql extends RouteBuilder {

  Logger logger = LoggerFactory.getLogger(CamelSql.class);


  @Autowired
  private LastIdService lastIdService;

  @Override
  public void configure() throws Exception {

    from("sql:SELECT TOP 10 * FROM tmp.customers where id > :#${bean:lastIdService.getId} order by id ASC ?outputType=StreamList&outputClass=com.example.apachecamelsql.Customer")
        .id("sql-route")
        .to("log:stream")
        .split(body()).streaming()
        .process(exchange -> {
          logger.info("Processing {}", exchange.getIn().getBody());
          lastIdService.setId(((Customer) exchange.getIn().getBody()).getId());
        })
        .end();
  }
}
