package com.fujias.business.common.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fujias.business.constants.MailTypes;
import com.fujias.common.component.MailComponent;
import com.fujias.common.component.SpringUtil;
import com.fujias.common.db.dao.MUsersMapper;
import com.fujias.common.db.entity.MUsers;
import com.fujias.common.util.StringUtil;

/**
 * 邮件发送工具类
 * 
 * @author chenqiang
 *
 */
@Service
public class MailService {

    @Autowired
    private MailComponent mailComponent;
    @Autowired
    private MUsersMapper t0020UsersMapper;

    /**
     * 获取收件人列表
     * 
     * @param mailType 邮件类型
     * @return 收件人列表
     */
    public List<String> getSendList(MailTypes mailType) {
        List<String> sendList = new ArrayList<String>();
        if (MailTypes.订单创建 == mailType || MailTypes.订单修改 == mailType || MailTypes.订单取消 == mailType) {
            sendList.add("admin");
        }
        return sendList;
    }

    /**
     * 文件导入处理
     * 
     * @param mailType mailType
     * @param no no
     * @return 导出数据
     */
    public boolean sendMail(MailTypes mailType, String no) {
        return sendMail(mailType, SpringUtil.getLoginUserId(), this.getSendList(mailType), no);
    }

    /**
     * 文件导入处理
     * 
     * @param mailType mailType
     * @param sendUser sendUser
     * @param no no
     * @return 导出数据
     */
    public boolean sendMail(MailTypes mailType, String sendUser, String no) {
        return sendMail(mailType, sendUser, this.getSendList(mailType), no);
    }

    /**
     * 文件导入处理
     * 
     * @param mailType mailType
     * @param sendUser sendUser
     * @param receiverUser receiverUser
     * @param no no
     * @return 导出数据
     */
    public boolean sendMail(MailTypes mailType, String sendUser, String receiverUser, String no) {
        List<String> receiverUserArr = new ArrayList<String>();
        receiverUserArr.add(receiverUser);
        return sendMail(mailType, sendUser, receiverUserArr, no);
    }

    /**
     * 文件导入处理
     * 
     * @param mailType mailType
     * @param sendUser sendUser
     * @param receiverUser receiverUser
     * @param no no
     * @return 导出数据
     */
    public boolean sendMail(MailTypes mailType, String sendUser, List<String> receiverUser, String no) {

        MUsers sendUserInfo = t0020UsersMapper.selectByPrimaryKey(sendUser);

        List<String> receiverList = new ArrayList<String>();
        for (String item : receiverUser) {
            MUsers t0020Users = t0020UsersMapper.selectByPrimaryKey(item);
            if (t0020Users != null && !StringUtil.isEmpty(t0020Users.getEmail())) {
                receiverList.add(t0020Users.getEmail());
            }
        }

        if (receiverList.isEmpty()) {
            return true;
        }

        String subject = "";
        String content = "";
        // 修改邮件的内容
        if (MailTypes.订单创建 == mailType) {
            subject = "【生产管理系统通知】订单创建通知";
            content = sendUserInfo.getName() + "创建了【" + no + "】的订单，请确认。";
        } else if (MailTypes.订单修改 == mailType) {
            subject = "【生产管理系统通知】订单修改通知";
            content = sendUserInfo.getName() + "修改了【" + no + "】的订单，请确认。";
        } else if (MailTypes.订单取消 == mailType) {
            subject = "【生产管理系统通知】订单取消通知";
            content = sendUserInfo.getName() + "取消了【" + no + "】的订单，请确认。";
        }

        return mailComponent.sendHtmlEmail(receiverList, null, subject, content);
    }
}
