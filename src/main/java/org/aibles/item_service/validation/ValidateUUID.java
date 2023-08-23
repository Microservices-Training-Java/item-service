package org.aibles.item_service.validation;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = ValidateUUID.IdValidator.class)
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE, ElementType.FIELD})
public @interface ValidateUUID {

  String message() default "Invalid UUID";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

  class IdValidator implements ConstraintValidator<ValidateUUID, String> {

    @Override
    public void initialize(ValidateUUID constraintAnnotation) {}

    @Override
    public boolean isValid(
      String id, ConstraintValidatorContext constraintValidatorContext) {
      var regexId = "[a-fA-F0-9]{8}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{4}-[a-fA-F0-9]{12}";
      return id.matches(regexId);
    }
  }
}
