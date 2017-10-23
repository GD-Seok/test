package com.board.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.board.domain.BoardCommand;

public class BoardValidator implements Validator {

	//1.유효성 검사를 할 클래스명을 지정해준다.
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return BoardCommand.class.isAssignableFrom(clazz);
	}

	//WriterController에서 호출할 것임
	//유효성 검사(1. 입력받을 대상자 (DTO객체(=command객체), 2.에러정보를 담을 객체명)
	public void validate(Object target, Errors errors) { //필수 입력 체크
		// TODO Auto-generated method stub
		
		//target에서 문제가 발생했을시
		//(에러 객체명, 적용시킬 필드명, 적용시킬 에러코드명)
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", "required");
		
		/*required.pwd=암호는 필수입력입니다.
		 * 라고 properties에 있을시
		 * 찾는 순서
		 * required.command.pwd
		 * required.pwd
		 * required.java.lang.String
		 * required
		 * */
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "writer", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "required");
		//errors.rejectValue("적용필드명","에러코드명"); 도 가능하다.
		//필드에 에러코드를 추가한다.
		
		
	}

}
