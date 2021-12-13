package com.example.apachecamelsql;

import org.springframework.stereotype.Service;

@Service
public class LastIdService {

  private Long id = 1L;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
