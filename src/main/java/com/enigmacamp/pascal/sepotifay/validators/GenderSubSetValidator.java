package com.enigmacamp.pascal.sepotifay.validators;

import com.enigmacamp.pascal.sepotifay.enums.Gender;
import com.enigmacamp.pascal.sepotifay.validators.constraints.ValidGender;

public class GenderSubSetValidator extends EnumSubSetValidator<ValidGender, Gender> {
  @Override
  public void initialize(ValidGender constraint) {
    super.initialize(constraint.anyOf());
  }
}
