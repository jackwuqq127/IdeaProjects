package com.zit.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND,reason = "参数中没有找到用户名！")
public class NoUserName extends RuntimeException {}
