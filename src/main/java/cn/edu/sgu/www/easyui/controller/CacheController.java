package cn.edu.sgu.www.easyui.controller;

import cn.edu.sgu.www.easyui.consts.MimeType;
import cn.edu.sgu.www.easyui.redis.RedisUtils;
import cn.edu.sgu.www.easyui.restful.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author heyunlin
 * @version 1.0
 */
@RestController
@Api(tags = "缓存管理")
@RequestMapping(path = "/cache", produces = MimeType.APPLICATION_JSON_CHARSET_UTF_8)
public class CacheController {

    /**
     * 应用名
     */
    @Value("${spring.application.name}")
    private String service;

    private final RedisUtils redisUtils;

    @Autowired
    public CacheController(RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    @ApiOperation("清理应用的缓存")
    @RequestMapping(value = "/clean", method = RequestMethod.POST)
    public JsonResult<Void> location() {
        redisUtils.deleteByPattern(service + "*");

        return JsonResult.success("缓存清理成功~");
    }

}