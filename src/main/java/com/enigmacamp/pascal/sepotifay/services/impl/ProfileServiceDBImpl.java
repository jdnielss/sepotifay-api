package com.enigmacamp.pascal.sepotifay.services.impl;

import com.enigmacamp.pascal.sepotifay.entities.Profile;
import com.enigmacamp.pascal.sepotifay.services.ProfileService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class ProfileServiceDBImpl implements ProfileService {
  @Override
  public void create(Profile entity) {

  }

  @Override
  public void update(Profile entity) {

  }

  @Override
  public void delete(String id) {

  }

  @Override
  public Profile getById(String id) {
    return null;
  }

  @Override
  public Page<Profile> getAll(Pageable pager) {
    return null;
  }
}
