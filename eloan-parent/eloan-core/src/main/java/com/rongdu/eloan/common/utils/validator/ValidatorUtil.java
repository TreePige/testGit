package com.rongdu.eloan.common.utils.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.rongdu.eloan.common.exception.ParamValideException;

public class ValidatorUtil {

	private static Validator validator = Validation
			.buildDefaultValidatorFactory().getValidator();

	public static <T> void validateDomain(T obj) throws ParamValideException {

		Set<ConstraintViolation<T>> constraintViolations = validator
				.validate(obj);
		List<String> erros = new ArrayList<String>();

		for (ConstraintViolation<T> constraintViolation : constraintViolations) {
			erros.add(constraintViolation.getMessage());
		}
		if (erros.size() > 0) {
			throw new ParamValideException(erros.toString());
		}

	}

	public static <T> void validateProperty(T obj, String propertyName)
			throws ParamValideException {

		Set<ConstraintViolation<T>> set = validator.validateProperty(obj,
				propertyName);

		List<String> erros = new ArrayList<String>();
		for (ConstraintViolation<T> constraintViolation : set) {
			erros.add(constraintViolation.getMessage()+constraintViolation.getPropertyPath());
		}

		if (erros.size() > 0) {
			throw new ParamValideException(erros.toString());
		}

	}
}
