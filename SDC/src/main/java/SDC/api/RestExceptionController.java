package SDC.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(basePackages = "SDC.api")
public class RestExceptionController {

	private static Logger logger = LoggerFactory.getLogger(RestExceptionController.class);

	@ExceptionHandler({ MethodArgumentNotValidException.class })
	public Map<String, String> error1(MethodArgumentNotValidException e) {
		logger.error(e.getMessage());

		Map<String, String> map = new HashMap<String, String>();

		List<ObjectError> errors = e.getBindingResult().getAllErrors();

		for (ObjectError error : errors) {
			String fieldName = ((FieldError) error).getField();
			map.put(fieldName, error.getDefaultMessage());
		}

		return map;
	}
//	@ExceptionHandler({ NullPointerException.class })
//	public String eror1(Exception e) {
//	logger.error(e.getMessage());
//	return "errorNull";
//	}
//	@ExceptionHandler({ Exception.class })
//	public String eror(Exception e) {
//		logger.error(e.getMessage());
//		return "error";
//	}
}
