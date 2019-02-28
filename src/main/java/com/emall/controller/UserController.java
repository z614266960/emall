package com.emall.controller;

import com.emall.error.BusinessException;
import com.emall.error.EmBusinessError;
import com.emall.response.CommonReturnType;
import com.emall.service.Model.UserModel;
import com.emall.service.UserService;
import com.emall.controller.viewObject.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller("user")
@RequestMapping("/user")
@CrossOrigin
public class UserController extends BaseController{

    @Autowired
    private UserService userService;

    @Autowired
    private HttpServletRequest httpServletRequest;

    //获取用户
    @RequestMapping("/get")
    @ResponseBody
    public CommonReturnType getUser(@RequestParam(name = "id")Integer id) throws BusinessException {
        UserModel userModel = userService.getUserById(id);
        if(userModel == null){
            throw new BusinessException(EmBusinessError.USER_NOT_EXIST);
        }
        UserVO userVO = convertFromModel(userModel);
        //返回通用的对象
        return CommonReturnType.create(userVO);
    }

    //opt验证码
    @RequestMapping(value = "/getopt",method = {RequestMethod.POST},consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType getOPT(@RequestParam(name="telphone")String tel){
        //随机数
        Random random = new Random();
        int randomInt = random.nextInt(9999);
        randomInt = randomInt+10000;
        String optCode = String.valueOf(randomInt);

        //optCode进Session
        httpServletRequest.getSession().setAttribute(tel,optCode);
        System.out.println("tel:"+tel+"   optCode:"+optCode);

        return CommonReturnType.create(null);
    }

    private UserVO convertFromModel(UserModel userModel){
        if(userModel == null){
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(userModel,userVO);
        return userVO;
    }


}
