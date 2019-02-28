package com.emall.error;

import javax.lang.model.type.ErrorType;

//包装器业务异常类实现
public class BusinessException extends Exception implements CommonError {

    private CommonError commonError;

    //直接接受传参
    public BusinessException(CommonError commonError){
        super();
        this.commonError = commonError;
    }

    //自定义errMsg
    public BusinessException(CommonError commonError,String errMsg){
        super();
        this.commonError = commonError;
        this.commonError.setErrMsg(errMsg);
    }


    @Override
    public int getErrCode() {
        return this.commonError.getErrCode();
    }

    @Override
    public String getErrMsg() {
        return this.commonError.getErrMsg();
    }

    @Override
    public CommonError setErrMsg(String errMsg) {
        this.commonError.setErrMsg(errMsg);
        return this;
    }
}
