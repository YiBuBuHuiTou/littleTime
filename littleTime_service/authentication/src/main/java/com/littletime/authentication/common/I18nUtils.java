package com.littletime.authentication.common;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.i18n.LocaleContext;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author YiBuBuHuiTou
 */
@Component
public class I18nUtils {

    private static MessageSource messageSource;

    /**
     * 构造函数
     * @param messageSource
     */
    public I18nUtils(MessageSource messageSource) {
        I18nUtils.messageSource = messageSource;
    }

    /**
     * 国际化message获取
     * @param key
     * @return
     */
    public static String getMessage(String key) {
        try{
            return messageSource.getMessage(key,null, LocaleContextHolder.getLocale());
        }catch (NoSuchMessageException e) {
           // e.printStackTrace();

            return "";
        }
    }

}
