$(document).ajaxError(function (event, xhr, options, exc) {
    if (xhr.status == 400) {
        alert("参数校验失败");
    } else if (xhr.status == 401) {
        alert("⽤⼾未登录, 即将跳转到登录⻚!");
//已经被拦截器拦截了, 未登录
        location.href = "blog_login.html";
    }
});
$(document).ajaxSend(function (e, xhr, opt) {
    var user_token = localStorage.getItem("user_token");
    xhr.setRequestHeader("user_token", user_token);
});
