package cn.cuit.exception;

import cn.cuit.resultAPI.Result;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 捕获异常并返回
 */
@ControllerAdvice
@ResponseBody
public class ExceptionsHandler {

	@ExceptionHandler(UnauthorizedException.class)
	public Result<String> handleUnauthorizedException(UnauthorizedException e) {
		return Result.failed("没有权限访问该资源");
	}
}