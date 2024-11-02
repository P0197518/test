package com.fujias.common.config;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fujias.common.serializer.CustomBigDecimalDeserializer;
import com.fujias.common.serializer.UtcDateDeserializer;
import com.fujias.common.util.DateUtil;

/**
 * SpringMVCのコンフィグクラスです。
 * 
 * @version 1.0
 * @author 陳強
 */
@SpringBootConfiguration
@EnableAsync
public class SpringMvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // コントローラー処理前に、何がしたいものがあれば、ControllerInterceptorに実装してください
        // registry.addInterceptor(new ControllerInterceptor());
        LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
        // lang表示切换语言的参数名
        localeInterceptor.setParamName("lang");
        localeInterceptor.setIgnoreInvalidLocale(false);
        registry.addInterceptor(localeInterceptor);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    }

    /**
     * jacksonOnjectMapperを初期化する。
     * 
     * @param builder builder
     * @return jacksonOnjectMapper
     */
    @Bean("objectMapper")
    @Primary
    @ConditionalOnMissingBean
    public ObjectMapper jacksonOnjectMapper(Jackson2ObjectMapperBuilder builder) {
        ObjectMapper mapper = builder.createXmlMapper(false).build();

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        // yyyy/MM/dd'T'HH:mm:ss.SSSZ のフォーマットを禁止する
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

        // デフォルトデータフォーマットを定義する
        DateFormat dateFormat = new SimpleDateFormat(DateUtil.DATE_FORMAT19);
        mapper.setDateFormat(dateFormat);

        // 時間変換を複数フォーマット処理可能に設定する。
        UtcDateDeserializer utcDateDeserializer = new UtcDateDeserializer();
        CustomBigDecimalDeserializer bigDecimalDeserializer = new CustomBigDecimalDeserializer();
        SimpleModule newModule = new SimpleModule("UTCDateDeserializer", PackageVersion.VERSION);
        newModule.addDeserializer(Date.class, utcDateDeserializer);
        newModule.addDeserializer(BigDecimal.class, bigDecimalDeserializer);
        mapper.registerModule(newModule);

        return mapper;
    }

    /**
     * @return locale
     */
    @Bean
    public LocaleResolver localeResolver() {
        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
        // 设置默认语言环境,设置默认.不指定具体语言
        localeResolver.setDefaultLocale(Locale.CHINA);
        return localeResolver;
    }

}
