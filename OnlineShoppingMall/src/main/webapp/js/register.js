/**
 * 修改数量按钮点击事件
 */
function Validate() {
	var ps_1 = document.getElementById("password_1").value;
	var ps_2 = document.getElementById("password_2").value;
	if (ps_1 != ps_2) {
		alert("两次密码不一致!")
		return false;
	} else
		return true;
}

function validatePasswordLength(str) {
	if (str.length >= 7) {
		document.getElementById("passwordStrength").innerHTML = "密码强度:强";
		return;
	}
	if (str.length >= 4) {
		document.getElementById("passwordStrength").innerHTML = "密码强度:中";
		return;
	}
	if (str.length > 0) {
		document.getElementById("passwordStrength").innerHTML = "密码强度:弱";
		return;
	}
	if (str.length == 0) {
		document.getElementById("passwordStrength").innerHTML = "";
		return;
	}
}

function resett() {
	var allValue = document.getElementsByTagName("input");
	for(var i = 1 ; i < allValue.length ; i++)
		allValue[i].value = "";
	return false;
}