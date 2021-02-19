
// 更换验证码
function change(img) {
	img.src = "getcode?" + new Date().getTime(); // 加时间或随机数
}

// 点击输入框时，清空后面的error类
function FocusItem(obj) { 
	if ($(obj).attr('name') == 'code') {
		$(obj).next().next().html('').removeClass('error');
	} else {
		$(obj).next('span').html('').removeClass('error');
	}
}

// 检查某一input标签
var flag = true;//标记位
function CheckItem(obj) {
	var msgBox = $(obj).next('span');
	switch ($(obj).attr('name')) {
		case "userName":
			if (obj.value == "") {
				msgBox.html('用户名不能为空');
				msgBox.addClass('error');
				flag = false;
			} else {
				// 使用Ajax请求
				var url = "usernamecheck?name=" + encodeURI($(obj).val()) + "&" + new Date().getTime();
				$.get(url, function (data) {
					if (data == "false") {
						msgBox.html('用户名已存在!');
						msgBox.addClass('error');
						flag = false;
					} else {
						msgBox.html().removeClass('error');
						flag = true;
					}
				});
			}
			break;
		case "passWord":
			if (obj.value == "") {
				msgBox.html('密码不能为空');
				msgBox.addClass('error');
				flag = false;
			} else {
				flag = true;
			}
			break;
		case "rePassWord":
			if (obj.value == "") {
				msgBox.html('确认密码不能为空');
				msgBox.addClass('error');
				flag = false;
			} else if ($(obj).val() != $('input[name="passWord"]').val()) {
				msgBox.html('密码不一致');
				msgBox.addClass('error');
				flag = false;
			} else {
				flag = true;
			}
			break;

		case "code":
			var numshow = $(obj).next().next();
			if (obj.value == "") {
				numshow.html('验证码不能为空');
				numshow.addClass('error');
				flag = false
			} else {
				var url = "checkusernum?num=" + encodeURI($(obj).val()) + "&" + new Date().getTime();
				$.get(url, function (data) {
					if (data == "false") {
						numshow.html('验证码输入有误');
						numshow.addClass('error');
						flag = false;
					} else {
						numshow.html().removeClass('error');
						flag = true;
					}
				})
			}
			break;
	}
}

// 检查所有input标签
function checkForm(frm) {
	var els = frm.getElementsByTagName('input');
	//onblur 这个属性才需要验证
	for (var i = 0; i < els.length; i++) {
		if (els[i] != null) {
			if (els[i].getAttribute("onblur")) { // 如果有onblur属性则检查
				CheckItem(els[i]);
			}
		}
	}
	return flag;
}