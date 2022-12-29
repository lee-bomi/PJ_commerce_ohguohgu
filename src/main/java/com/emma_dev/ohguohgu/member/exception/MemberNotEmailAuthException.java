package com.emma_dev.ohguohgu.member.exception;

public class MemberNotEmailAuthException extends RuntimeException{

    public MemberNotEmailAuthException(String error) {
        super(error);
    }
}
