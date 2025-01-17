package com.green.gittest.user.exception;

import com.green.gittest.common.GlobalExceptionHandler;
import com.green.gittest.common.model.ResultDto;
import com.green.gittest.common.myexception.SignUpException;
import com.green.gittest.common.myexception.UserNotFoundException;
import com.green.gittest.common.myexception.WrongValue;
import org.springframework.core.annotation.Order;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@Order(1)
@RestControllerAdvice(basePackages = "com.green.gittest.user")
public class UserException{// 1,2,3,4,5,8
    //1.널|2.유저낫|3.워링벨류|4.런타임|5.데이터인터걸|8.사인업핸들러|

    //1.널
    @ExceptionHandler(NullPointerException.class)
    public ResultDto<String> handleNullPointerException(NullPointerException ex) {
        return ResultDto.resultDto(HttpStatus.BAD_REQUEST, "정보를 입력해주세요.");
    }
    //2.유저낫
    @ExceptionHandler(UserNotFoundException.class)
    public ResultDto<String> handleUserNotFoundException(UserNotFoundException ex) {
        return ResultDto.resultDto(HttpStatus.BAD_REQUEST, "존재하지 않는 아이디입니다.");
    }
    //3.워링벨류
    @ExceptionHandler(WrongValue.class)
    public ResultDto<String> handleWrongValueException(WrongValue ex) {
        return ResultDto.resultDto(HttpStatus.BAD_REQUEST, "비밀번호가 틀렸습니다.");
    }
    //4.런타임
    @ExceptionHandler(RuntimeException.class)
    public ResultDto<String> handleRuntimeException(RuntimeException ex) {
        ex.printStackTrace();
        return ResultDto.resultDto(HttpStatus.INTERNAL_SERVER_ERROR, "처리할 수 없는 요청입니다.");
    }
    //5.데이터인터걸
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResultDto<String> handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        ex.printStackTrace();
        String errorMessage = "이미 존재하고 있는 아이디입니다." ;
        //+ ex.getRootCause().getMessage() +" (데이터 무결성 위반)";
        // 외래 키 제약 조건 위반 시 특정 메시지 제공
        if (ex.getMessage().contains("FOREIGN KEY")) {
            errorMessage = "(참조하는 키가 존재하지 않습니다.)";
        }
        return ResultDto.resultDto(HttpStatus.BAD_REQUEST, errorMessage);
    }
    //8.사인업핸들러
    @ExceptionHandler(SignUpException.class)
    public ResultDto<String> handleSignUpException(SignUpException ex) {
        ex.printStackTrace();
        return ResultDto.resultDto(HttpStatus.INTERNAL_SERVER_ERROR, "회원가입 오류임");
    }
}
