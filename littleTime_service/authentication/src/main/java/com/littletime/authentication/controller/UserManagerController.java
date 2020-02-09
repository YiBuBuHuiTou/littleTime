package com.littletime.authentication.controller;

import com.cxd.littletime.common.util.StringUtils;
import com.littletime.authentication.Bean.User;
import com.littletime.authentication.common.AuthenticationUtils;
import com.littletime.authentication.common.I18nUtils;
import com.littletime.authentication.common.rabbitmq.RabbitCommonSender;
import com.littletime.authentication.common.result.CommonResultBean;
import com.littletime.authentication.common.result.FailResultBean;
import com.littletime.authentication.common.result.SuccessResultBean;
import com.littletime.authentication.service.IPTableService;
import com.littletime.authentication.service.UserBaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author YiBuBuHuiTou
 */
@RestController
public class UserManagerController {

    // request 请求
    private HttpServletRequest request;

    // rabbit mq 发送
    private RabbitCommonSender rabbitCommonSender;

    // 用户service
    private UserBaseService userBaseService;

    //黑白名单service
    private IPTableService ipTableService;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserManagerController.class);

    private static final long DEFAULT_TOKEN_TTL = 60;

    private static final long DEFAULT_REDIS_TTL = 60*24*15;

    @Autowired
    UserManagerController(RabbitCommonSender rabbitCommonSender, UserBaseService userBaseService,
                          HttpServletRequest request, IPTableService ipTableService) {
        this.rabbitCommonSender = rabbitCommonSender;
        this.userBaseService = userBaseService;
        this.request = request;
        this.ipTableService = ipTableService;
    }

    /**
     * 注册用户
     * @return
     */
    @RequestMapping(value = "/authentication/register", method = RequestMethod.POST)
    public String register(@RequestBody Map<String, String> body) throws IOException {
        StringBuilder errMsg = new StringBuilder();
        boolean resultFlg = false;
        CommonResultBean resultBean = null;
        User newUser = AuthenticationUtils.buildSimpleUser(body);
        if(newUser == null || newUser.getUserName() == null) {
//            result = "fail";
            errMsg.append("用户名为空。");
        } else {
            int tenantId = -1;
            String tenantIdStr = body.get("tenantId");
            if (tenantIdStr != null) {
                tenantIdStr = tenantIdStr.trim();
            }
            try {
                if (!StringUtils.isEmpty(tenantIdStr)) {
                    tenantId = Integer.parseInt(tenantIdStr);
                } else {
                    LOGGER.error("tenantId is null. operation[register] can not be succeed.");
                }
            } catch (NumberFormatException e) {
                LOGGER.error("tenantId is invalid. ", e);
            }
            if (userBaseService.checkUserExist(newUser.getUserName())) {
                errMsg.append("该用户名已存在。");
            } else {
                User user =  userBaseService.addUser(newUser, tenantId);
                if (user != null) {
                    resultFlg = true;
                }
            }
        }
        if (resultFlg) {
            resultBean = new SuccessResultBean();
            resultBean.setMessage(I18nUtils.getMessage("AUTHENTICATION_REGISTER_SUCCESS"));
        } else {
            resultBean = new FailResultBean();
            resultBean.setMessage(I18nUtils.getMessage("AUTHENTICATION_REGISTER_FAIL") + errMsg.toString());
        }
        return resultBean.toString();
    }

    /**
     * 登录用户
     * @return
     */
    @RequestMapping(value = "/authentication/signIn", method = RequestMethod.POST)
    public String signIn(@RequestBody Map<String, String> userInfo) {
        String userName = userInfo.get("userName");
        String password = userInfo.get("password");
        User user = AuthenticationUtils.buildSimpleUser(userInfo);
        User resultUser = null;
        CommonResultBean resultBean = null;
        if (!StringUtils.isEmpty(userName)) {
            resultUser = userBaseService.signIn(user);
        }
        if (resultUser != null) {
            long tokenTTL = DEFAULT_TOKEN_TTL;
            long redisTTL = DEFAULT_REDIS_TTL;
            try {
                tokenTTL = Long.parseLong(userInfo.get("tokenTTL").trim());
            } catch (Exception e) {
                // do nothing
            }
            try {
                tokenTTL = Long.parseLong(userInfo.get("redisTTL").trim());
            } catch (Exception e) {
                // do nothing
            }
            String token = userBaseService.generateToken(resultUser, tokenTTL, redisTTL);
            resultUser.setToken(token);
            userBaseService.updateUser(resultUser);
            resultBean = new SuccessResultBean();
            resultBean.setMessage(I18nUtils.getMessage("AUTHENTICATION_SIGN_IN_SUCCESS"));
            return resultBean.toString();
        } else {
            resultBean = new FailResultBean();
            resultBean.setMessage(I18nUtils.getMessage("AUTHENTICATION_SIGN_IN_FAIL"));
            return resultBean.toString();
        }
    }

    /**
     * 登出
     * @return
     */
    @RequestMapping(value = "/authentication/signOut", method = RequestMethod.POST)
    public String signOut(@RequestBody String credential) {
        //TODO 清除用户登录信息
        return "";
    }

    /**
     * 注销用户
     * @return
     */
    @RequestMapping(value = "/authentication/releaseUser", method = RequestMethod.POST)
    public String releaseUser(@RequestBody Map<String, String> credential) {
        //TODO 注销用户
        userBaseService.deleteUserByCredential(credential.get("credential"));
        return "";
    }

    /**
     * 追加子用户
     * @return
     */
    @RequestMapping(value = "/authentication/addChildUsers", method = RequestMethod.POST)
    public String addChildUsers(@RequestBody List<Map<String, String>> userInfoList) {
        //TODO
        return "";
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/authentication/checkUser", method = RequestMethod.POST)
    public String checkUser(@RequestBody Map<String, String> userInfo) {
        //TODO 判断 是否用户本人
        return "";
    }



    /**
     * 更新用户信息
     * @param userInfo
     * @return
     */
    @RequestMapping(value = "/authentication/updateUser", method = RequestMethod.POST)
    public String updateUser(@RequestBody Map<String, String> userInfo) {
        return "";
    }

    /**
     * 更新子用户信息
     * @param userInfoList
     * @return
     */
    @RequestMapping(value = "/authentication/updateChildUsers", method = RequestMethod.POST)
    public String updateChildUsers(@RequestBody List<Map<String, String>> userInfoList) {
        return "";
    }
}
