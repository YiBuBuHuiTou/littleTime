package com.littletime.authentication.interceptor;

import com.cxd.littletime.common.util.IpUtils;
import com.littletime.authentication.common.AuthenticationUtils;
import com.littletime.authentication.common.I18nUtils;
import com.littletime.authentication.config.CustomConfig;
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
    private CustomConfig customConfig;

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
        boolean isIntercept = true;
        List<String> whiteList = customConfig.getWhiteList();
        List<String> blackList = customConfig.getBlackList();
        //黑名单和白名单拦截
        String ip = AuthenticationUtils.getIpFromHttp(request);
        if (whiteList != null) {
            // 配置文件白名单
            boolean isInWhiteConf = isIpInIpList(ip, whiteList);
            // 数据白名单
            // TODO
            boolean isInWhiteDb = false;
            if (isInWhiteConf || isInWhiteDb) {
                isIntercept = false;
                LOGGER.info(I18nUtils.getMessage("AUTHENTICATION_IP_IN_WHITE_LIST"));
            } else {
                LOGGER.warn(I18nUtils.getMessage("AUTHENTICATION_IP_NOT_IN_WHITE_LIST", ip));
            }
        }

        if (blackList != null) {
            //配置文件黑名单
            boolean isInBlackConf = isIpInIpList(ip,blackList);
            //数据库黑名单
            // TODO
            boolean isInBlackDb = false;
            if (!isInBlackConf && !isInBlackDb) {
                isIntercept = false;
                LOGGER.info(I18nUtils.getMessage("AUTHENTICATION_IP_NOT_IN_BLACK_LIST"));
            } else {
                LOGGER.warn(I18nUtils.getMessage("AUTHENTICATION_IP_IN_BLACK_LIST", ip));
            }
        }


        return !isIntercept;
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

    /**
     * 判断IP是否在名单里
     * @param ip
     * @param iptables
     * @return
     */
    private boolean isIpInIpList(String ip, List<String> iptables) {
        boolean isIpInIpList = false;

        for (String ipStr : iptables) {
            if (IpUtils.isSingleIP(ipStr) && !IpUtils.isIP(ipStr)) {
                continue;
            } else if (IpUtils.isSingleIP(ipStr)) {
                if (ip.equals(ipStr)) {
                    isIpInIpList = true;
                    break;
                }
            } else {
                long ipNum = IpUtils.ip2Long(ip);
                String[] ips = ipStr.split("-");
                long start = IpUtils.ip2Long(ips[0]);
                long end = IpUtils.ip2Long(ips[1]);
                long currentIp = IpUtils.ip2Long(ip);
                if (start != 0L && end != 0L && currentIp >= start && currentIp <= end) {
                    isIpInIpList = true;
                    break;
                }
            }
        }
        return isIpInIpList;
    }
}
