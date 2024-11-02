function BusinessMessage() {
    this.init;
    this.messages;
};
BusinessMessage.prototype = {
    get: function(id, args) {
        if (!this.init) {
            this.messages = new Array();
            // 这里可以使用Java代码的方法读取服务端已经缓存好的消息提示语
            // 或者可以静态设置一些提示语
            this.messages['I00001'] = "保存成功"; // 添加和更新成功情况下使用
            this.messages['I00002'] = "删除成功"; // 删除成功情况下使用
            this.messages['I00003'] = "恢复成功"; // 恢复成功情况下使用
            this.messages['I00004'] = "强制出库成功"; // 強制出庫成功情况下使用
            this.messages['I00005'] = "强制入库成功"; // 強制入庫成功情况下使用



            this.messages['E00001'] = "处理失败，请再次尝试。"; // 后台更新失败情况下使用
            this.messages['E00002'] = "请选择要处理的数据。"; // 需要选择画面的一条数据，但是没有选择时使用
            this.messages['E00003'] = "该数据已更新到其他终端，请获取最新数据，再次尝试。";
            this.messages['E00004'] = "该数据不存在。";
            this.messages['E00005'] = "相应的{0}已经存在。";
            this.messages['E00006'] = "输入的数据不正确。请参照提示修改数据。"; //输入的数据不正确，请参照提示修改数据
            this.messages['E00009'] = "请根据提示修改（未翻译）";


            this.messages['Q00001'] = "是否删除数据？"; //是否删除数据？
            this.messages['Q00002'] = "是否保存数据？"; //是否保存数据？
            this.messages['Q00003'] = "是否恢复数据？"; //是否恢复数据？
            this.messages['Q00004'] = "该数据含有子数据，是否全部删除？";


            this.messages['EC0001'] = "请输入用户名或密码。";
            this.messages['EC0002'] = "用户名和密码不正确。";
            this.messages['EC0003'] = "数据验证发生错误。";
            this.messages['EC0004'] = "{0}请在{1}字符以内输入。";
            this.messages['EC0005'] = "{0}请在{1}位数内输入。";
            this.messages['EC0006'] = "{{0}请输入半角数字(0-9)。";
            this.messages['EC0008'] = "请选择{0}。";
            this.messages['EC0009'] = "{0}请输入全角字符。";
            this.messages['EC0010'] = "{0}的大小关系有错误。";
            this.messages['EC0011'] = "请在{0}中输入正确的日期。";
            this.messages['EC0012'] = "请输入{0}。";
            this.messages['EC0013'] = "{0}请输入{1}字符。";
            this.messages['EC0014'] = "{0}请输入{1}之后。";
            this.messages['EC0015'] = "上传文件失败。";
            this.messages['EC0016'] = "该用户不存在。";
            this.messages['EC0017'] = "导入的数据不存在。";
            this.messages['EC0018'] = "JAN代码不存在。";
            this.messages['EC0019'] = "请输入正确的值。";
            this.messages['EC0090'] = "发生了系统错误，请咨询系统管理。";
            this.messages['EC0091'] = "没有访问权限，请咨询系统管理。";
            this.init = true;
        }
        var message = this.messages[id];
        if (!message && message !== "") {
            return id;
        }
        if (args) {
            if (typeof args == "object" && args.length) {
                for (var i = 0; i < args.length; i++) {
                    var pattern = new RegExp("\\{" + i + "\\}", "g");
                    message = message.replace(pattern, args[i]);
                }
            } else {
                message = message.replace(/\{0\}/g, args);
            }
        }
        return message;
    },
    alert: function(messageId, messageArgs, okFunction, cancleFunction) {
        if (messageId.indexOf("I") == 0) {
            mini.alert(businessMessage.get(messageId, messageArgs), "信息",
                function(action) {
                    if (action == "ok") {
                        if (okFunction != undefined && okFunction != null) {
                            okFunction();
                        }
                    }
                });
        } else if (messageId.indexOf("W") == 0 || messageId.indexOf("Q") == 0) {
            mini.confirm(businessMessage.get(messageId, messageArgs), "询问",
                function(action) {
                    if (action == "ok") {
                        if (okFunction != undefined && okFunction != null) {
                            okFunction();
                        }
                    } else if (cancleFunction != undefined &&
                        cancleFunction != null) {
                        cancleFunction();
                    }
                });
        } else {
            mini.alert(businessMessage.get(messageId, messageArgs), "错误",
                function(action) {
                    if (action == "ok") {
                        if (okFunction != undefined && okFunction != null) {
                            okFunction();
                        }
                    }
                });
        }
    }
};
var businessMessage = new BusinessMessage();