package com.fujias.business.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import com.fujias.business.forms.TestForm;
import com.fujias.business.service.TestService;
import com.fujias.common.base.BaseController;
import com.fujias.common.constants.StateTypes;
import com.fujias.common.entity.ResultInfo;
import com.fujias.common.entity.SelectItem;
import com.fujias.common.security.springsecurity.access.RoleResourceCache;

/**
 * SYANAI001Controller
 * 
 * @author 陳強
 *
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseController {

    @Autowired
    private TestService testService;

    @Autowired
    private RequestMappingHandlerMapping handlerMapping;

    /**
     * 测试画面的初期化
     * 
     * @return 测试画面
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        TestForm form = new TestForm();
        return this.backView("common/test", form);

    }

    /**
     * 获取所有的请求列表
     * 
     * @param formData formData
     * @return 请求列表
     * @throws Exception Exception
     */
    @RequestMapping(value = "/getAllMapping", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getAllMapping(@RequestBody TestForm formData) throws Exception {

        List<SelectItem> urlmaps = new ArrayList<SelectItem>();
        Map<?, HandlerMethod> map = this.handlerMapping.getHandlerMethods();
        Iterator<?> iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            @SuppressWarnings("rawtypes")
            Map.Entry entry = (Map.Entry) iterator.next();
            urlmaps.add(new SelectItem(entry.getKey().toString(), entry.getValue().toString()));
        }

        return this.backListData(urlmaps);
    }

    /**
     * 重新加载所有权限列表
     * 
     * @param formData formData
     * @return 请求列表
     * @throws Exception Exception
     */
    @RequestMapping(value = "/reloadAllRoleCache", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo reloadAllRoleCache(@RequestBody TestForm formData) throws Exception {

        RoleResourceCache.reloadAll();
        RoleResourceCache.reloadAllWhite();

        return this.backUpdateState(StateTypes.SUCCESS);
    }

    /**
     * 重新加载所有权限列表
     * 
     * @param formData formData
     * @return 请求列表
     * @throws Exception Exception
     */
    @RequestMapping(value = "/addAllResourceToAdmin", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo addAllResourceToAdmin(@RequestBody TestForm formData) throws Exception {

        testService.addAllResourceToAdmin();

        RoleResourceCache.reloadAll();

        return this.backUpdateState(StateTypes.SUCCESS);
    }

}
