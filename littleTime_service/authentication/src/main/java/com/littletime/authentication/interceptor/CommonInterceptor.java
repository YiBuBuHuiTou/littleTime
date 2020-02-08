package com.littletime.authentication.interceptor;

import com.cxd.littletime.common.util.IpUtils;
import com.cxd.littletime.common.util.StringUtils;
import com.littletime.authentication.Bean.WhiteList;
import com.littletime.authentication.common.AuthenticationUtils;
import com.littletime.authentication.common.I18nUtils;
import com.littletime.authentication.config.CustomConfig;
import com.littletime.authentication.dao.BlackListDao;
import com.littletime.authentication.dao.WhiteListDao;
import com.littletime.authentication.service.IPTableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author YiBuBuHuiTou
 * 通用拦截器
 */
public class CommonInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private IPTableService ipTableService;

    //LOGGER
    private static Logger LOGGER = LoggerFactory.getLogger(CommonInterceptor.class);


    public CommonInterceptor() {
        super();
    }

    /**
     *  预处理回调方法，实现处理器的预处理（如登录检查），第三个参数为响应的处理器（如我们上一章的Controller实现）；
     *  返回值：true表示继续流程（如调用下一个拦截器或处理器）；
     *  false表示流程中断（如登录检查失败），不会继续调用其他的拦截器或处理器，此时我们需要通过response来产生响应；
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("interceptor start");
        String ip = AuthenticationUtils.getIpFromHttp(request);
        String isIntercept = ipTableService.checkIpIsValid(ip);
        if (isIntercept.equals("legal")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 后处理回调方法，实现处理器的后处理（但在渲染视图之前），此时我们可以通过modelAndView（模型和视图对象）对模型数据进行处理或对视图进行处理，
     * modelAndView也可能为null
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    /**
     * 整个请求处理完毕回调方法，即在视图渲染完毕时回调，如性能监控中我们可以在此记录结束时间并输出消耗时间，
     * 还可以进行一些资源清理，类似于try-catch-finally中的finally，但仅调用处理器执行链中preHandle返回true的拦截器的
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }


}
