package cn.cuit.interceptor;


import cn.cuit.entity.User;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import java.util.Set;

public class ValidationUtil {

	public static String valdate(@Valid User user) {
		Set<ConstraintViolation<@Valid User>> violations = Validation.buildDefaultValidatorFactory()
				.getValidator()
				.validate(user);
		// 如果有错误，则返回错误信息
		if (!violations.isEmpty()) {
			StringBuilder stringBuilder = new StringBuilder();
			for (ConstraintViolation<User> violation : violations) {
				stringBuilder.append(violation.getMessage()).append("\n");
			}
			System.out.println(stringBuilder.toString());
			return stringBuilder.toString();
		}
		return "success";
	}
}
