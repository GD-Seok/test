package com.board.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.board.domain.BoardCommand;

public class BoardValidator implements Validator {

	//1.��ȿ�� �˻縦 �� Ŭ�������� �������ش�.
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return BoardCommand.class.isAssignableFrom(clazz);
	}

	//WriterController���� ȣ���� ����
	//��ȿ�� �˻�(1. �Է¹��� ����� (DTO��ü(=command��ü), 2.���������� ���� ��ü��)
	public void validate(Object target, Errors errors) { //�ʼ� �Է� üũ
		// TODO Auto-generated method stub
		
		//target���� ������ �߻�������
		//(���� ��ü��, �����ų �ʵ��, �����ų �����ڵ��)
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "pwd", "required");
		
		/*required.pwd=��ȣ�� �ʼ��Է��Դϴ�.
		 * ��� properties�� ������
		 * ã�� ����
		 * required.command.pwd
		 * required.pwd
		 * required.java.lang.String
		 * required
		 * */
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "writer", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "content", "required");
		//errors.rejectValue("�����ʵ��","�����ڵ��"); �� �����ϴ�.
		//�ʵ忡 �����ڵ带 �߰��Ѵ�.
		
		
	}

}
