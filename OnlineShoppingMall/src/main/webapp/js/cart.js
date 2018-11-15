/**
 * 修改数量按钮点击事件
 */
function minus(id) {
	var sum = 0.0;
	var amount = document.getElementById(id);
	if(amount.value > 1)
		amount.value = parseInt(amount.value) - 1;
	var price = parseFloat(document.getElementById("price" + id).innerHTML);
	document.getElementById("totalPrice" + id).value = price * amount.value + "元";
	var items = document.getElementsByName("totalPrice");
	for(var i = 0 ; i < items.length ; i++) {
		sum = sum + parseFloat(items[i].value);
	}
	document.getElementById("summary").value = sum + "元";
	document.getElementById("zj").innerHTML = sum;
}

function plus(id) {
	var sum = 0.0;
	var amount = document.getElementById(id);
	amount.value = parseInt(amount.value) + 1;
	var price = parseFloat(document.getElementById("price" + id).innerHTML);
	document.getElementById("totalPrice" + id).value = price * amount.value + "元";
	var items = document.getElementsByName("totalPrice");
	for(var i = 0 ; i < items.length ; i++) {
		sum = sum + parseFloat(items[i].value);
	}
	document.getElementById("summary").value = sum + "元";
	document.getElementById("zj").innerHTML = sum;
}

function total(id , amount) {
	var sum = 0.0;
	if(document.getElementById(id).value == "")
		amount = "0";
	var amountValue = parseInt(amount);
	var price = document.getElementById("price" + id);
	var priceValue = parseFloat(price.innerHTML);
	document.getElementById("totalPrice" + id).value = amountValue * priceValue + "元";
	var items = document.getElementsByName("totalPrice");
	for(var i = 0 ; i < items.length ; i++) {
		sum = sum + parseFloat(items[i].value);
	}
	document.getElementById("summary").value = sum + "元";
	document.getElementById("zj").innerHTML = sum;
}

function handlePlus (id){
	var goodsId = document.getElementById("goodsId" + id).innerHTML;
	var amount = parseInt(document.getElementById(id).value) + 1;
	$.ajax({
		url : '/handle',
		type : 'POST',
		data : {
			'goodsId': goodsId,
			'amount' : amount
		},
		success : function(data){
			if(data.success)
				plus(id);
		}
	});
}

function handleMinus (id){
	var goodsId = document.getElementById("goodsId" + id).innerHTML;
	var amount = parseInt(document.getElementById(id).value) - 1;
	$.ajax({
		url : '/handle',
		type : 'POST',
		data : {
			'goodsId': goodsId,
			'amount' : amount
		},
		success : function(data){
			if(data.success)
				minus(id);
		}
	});
}

function handleInput (id , amount){
	$.ajax({
		url : '/handle',
		type : 'POST',
		data : {
			'goodsId': id,
			'amount' : amount
		},
		success : function(data){
			if(data.success)
				total(id , amount);
		}
	});
}