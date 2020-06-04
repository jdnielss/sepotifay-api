package com.enigmacamp.pascal.sepotifay.validators.constraints;

import com.enigmacamp.pascal.sepotifay.enums.Gender;

import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
public @interface ValidGender {
  /**
   * @return subset of Gender enum
   */
  Gender[] anyOf();

  /**
   * @return the error message template
   */
  String message() default "Value must be any of {anyOf}";
  /**
   * @return the groups the constraint belongs to
   */
  Class<?>[] groups() default {};
  /**
   * @return the payload associated to the constraint
   */
  Class<? extends Payload>[] payload() default {};
}
