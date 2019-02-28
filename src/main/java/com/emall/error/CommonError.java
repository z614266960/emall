package com.emall.error;

import com.emall.response.CommonReturnType;

public interface CommonError {
    public int getErrCode();
    public String getErrMsg();
    public CommonError setErrMsg(String errMsg);
}
