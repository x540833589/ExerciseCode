/**
 * 修改数量按钮点击事件
 */
function minus() {
	var amount = document.getElementById("amount").value;
	if (amount > 1)
		document.getElementById("amount").value = amount - 1;
	return false;
}

function plus() {
	var amount = document.getElementById("amount");
	amount.value = parseInt(amount.value) + 1;
	return false;
}