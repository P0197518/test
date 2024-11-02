package com.fujias.common.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fujias.common.base.BaseController;
import com.fujias.common.dao.MenuMapper;
import com.fujias.common.db.entity.MUsers;
import com.fujias.common.entity.LoginUser;
import com.fujias.common.entity.MenuEntity;
import com.fujias.common.entity.ResultInfo;
import com.fujias.common.form.MenuForm;
import com.fujias.common.service.CommonUserService;

/**
 * SYANAI001Controller
 * 
 * @author 陳強
 *
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController {
    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private CommonUserService commonUserService;

    /**
     * Index
     * 
     * @return ModelAndView
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        MenuForm formData = new MenuForm();
        LoginUser loginuser = this.getLoginUser();
        formData.setName(loginuser.getName());
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String time = sdf.format(new Date());
        formData.setNowdate(time);
        return this.backView("common/menu", formData);
    }

    /**
     * 获取菜单栏数据
     * 
     * @return 菜单栏数据
     */
    @RequestMapping(value = "/getMenuList", method = RequestMethod.GET)
    @ResponseBody
    public String getMenuList() {
        List<Map<String, String>> jsonList = new ArrayList<Map<String, String>>();
        List<MenuEntity> menuData = menuMapper.getMenuList(this.getLoginUser().getUsername());
        for (MenuEntity item : menuData) {
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("id", item.getResourceid());
            map.put("text", item.getResourcename());
            map.put("iconCls", item.getImgvalue());
            if (!StringUtils.isEmpty(item.getParentid())) {
                map.put("pid", item.getParentid());
            }
            map.put("url", item.getResourceurl());
            jsonList.add(map);
        }
        return this.backJsonString(jsonList);
    }

    /**
     * ローグインされない場合、notLogin画面へ遷移する。
     * 
     * @return View
     */
    @RequestMapping(value = "/info", method = RequestMethod.POST)
    @ResponseBody
    public ResultInfo getUserInfo() {
        String userId = this.getLoginUser().getUsername();
        MenuForm formData = new MenuForm();
        MUsers userInfo = commonUserService.getUserInfoById(userId);
        formData.setId(userInfo.getUsername());
        formData.setName(userInfo.getName());
        return this.backSingleData(formData);
    }

}
