package com.enigmacamp.pascal.sepotifay.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface Service<E> {
  void create(E entity);
  void update(E entity);
  void delete(String id);
  E getById(String id);
  Page<E> getAll(Pageable pager);
}
