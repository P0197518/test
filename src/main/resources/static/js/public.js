/**
 * 表格高度
 */
let table_height = 620;

/**
 * 表格中的图片的宽度
 */
let table_image_width = 45;
let size = table_image_width;
let pageList = [10, 20, 50, 100];

/**
 * 文件上传按钮
 * @type {{handler: handler, text: string, iconCls: string}}
 */
let buttonUpload = buttonUploadHandler(function () {
    imageHandler();
});

function buttonUploadHandler(func) {
    return {
        iconCls: "icon-upload",
        text: "上传图片",
        handler: function() {
            func();
        }
    };
}

/**
 * 验证手机号格式
 * @param phone
 */
function phoneValidate(phone) {
    // 最新的手机号正则表达式
    let phone_number_reg = /^1[3-9]\d{9}$/;

    return phone_number_reg.test(phone);
}

/**
 * 验证邮箱格式
 * @param email
 */
function emailValidate(email) {
    // 邮箱正则表达式
    let email_reg = /^(([^()[\]\\.,;:\s@"]+(\.[^()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;

    return email_reg.test(email);
}