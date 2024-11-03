/**
 * 禁止输入空格
 */
function preventSpace() {
    let event = window.event;

    if(event.keyCode === 32) {
        event.returnValue = false;
    }
}

/**
 * 登录
 */
function login() {
    let username = $("#username").val();
    let password = $("#password").val();

    if (!username) {
        alert("请输入用户名！");

        $("#username").focus();
    } else if (!password) {
        alert("请输入密码！");

        $("#password").focus();
    } else {
        ajaxPost("/user/login", {
            username: username,
            password: password
        }, function() {
            location.href = "/index.html";
        }, function (res) {
            if (res && res.responseJSON) {
                let response = res.responseJSON;

                if (res.status && res.status === 404) {
                    let message;

                    if(response.path) {
                        message = "路径" + response.path + "不存在。";
                    } else {
                        message = response.message;
                    }

                    alert(message);
                } else {
                    alert(response.message);
                }
            }
        });
    }
}

$(function () {
    $("#username").keydown(function() {
        preventSpace();
    }).attr("placeholder", "请输入账号");

    /**
     * 给密码输入框绑定回车登录事件
     */
    $("#password").keydown(function() {
        let event = window.event;

        if(event.keyCode === 13) {
            login();
        }

        preventSpace();
    }).attr("placeholder", "请输入密码");

    // 点击登录按钮
    $("#login").on("click", function () {
        login();
    });

});