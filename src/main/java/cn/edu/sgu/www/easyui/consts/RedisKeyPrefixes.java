package cn.edu.sgu.www.easyui.consts;

/**
 * @author heyunlin
 * @version 1.0
 */
public interface RedisKeyPrefixes {

    String PREFIX_BASE = "easyui-admin:";

    /**
     * 用户的侧栏菜单
     */
    String PREFIX_USER_LEFT_MENUS = PREFIX_BASE + "user_left_menus:";

    /**
     * 角色的菜单树
     */
    String PREFIX_ROLE_MENU_TREE = PREFIX_BASE + "role_menu_tree:";

    /**
     * 用户登录的次数
     */
    String PREFIX_USER_LOGIN_TIMES = PREFIX_BASE + "user_login_times:";
}