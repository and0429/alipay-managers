var payOnlie = new Object();

payOnlie.dataTable = undefined;
payOnlie.outTradeNo = undefined;
payOnlie.payWay = undefined;

/**
 * main method
 */
payOnlie.main = function() {
	payOnlie.categorySelect();
	payOnlie.amountOnFocus();
	payOnlie.loadDataTables();
	payOnlie.save2Table();
	payOnlie.modalMethod();
}

/**
 * load goods category select
 */
payOnlie.categorySelect = function() {

	$.ajax({
		url : '../goodsCategory/all.do',
		type : 'GET',
		success : function(data) {
			var html = '';
			for (var int = 0; int < data.length; int++) {
				html += "<option value='" + data[int].name + "'>" + data[int].name + "</option>";
			}
			$('#categorySelect').html(html);
		}
	});
};

/**
 * load data table
 */
payOnlie.loadDataTables = function() {
	payOnlie.dataTable = $('.table').DataTable({
		"ordering" : false,
		"searching" : false,
		"scrollY" : "500px",
		"scrollCollapse" : true,
		"paging" : false,
		"dom" : '<"total"><"toolbar">t',
		"language" : util.dataTableLanguage(),

		"columns" : [ {
			"data" : "category"
		},/*
			 * { "data" : "paytype" },
			 */{
			"data" : "amount"
		} ],

		"drawCallback" : function(settings) {
			var api = this.api();
			payOnlie.total(api);
		},

		"initComplete" : function() {
			payOnlie.addButton();
			payOnlie.alipay();
			payOnlie.moneyAlipay();
			payOnlie.scnneralipay();
			payOnlie.deleteRow();
		}
	});
}

/**
 * delete a select row
 */
payOnlie.deleteRow = function() {

	$('.table tbody').on('click', 'tr', function() {
		if (window.confirm("您确定要删除此条记录吗？")) {
			payOnlie.dataTable.row($(this)).remove().draw();
		}
	});
}

/**
 * save to table
 */
payOnlie.save2Table = function() {
	$('#save2Table').on('click', function() {

		var patton = /^[0-9]+([.]{1}[0-9]{1,2}){0,1}$/;

		if (patton.test($('#amount').val())) {
			payOnlie.dataTable.row.add({
				"category" : $('#categorySelect').val(),
				// "paytype" : $('#payType').val(),
				"amount" : $('#amount').val()
			}).draw();
		} else {
			$('#amount')[0].focus();
		}

		$('#amount').val('');
	});
};

/**
 * 
 */
payOnlie.amountOnFocus = function() {
	$('#amount').on('focus', function() {
		this.value = '';
	});
}

/**
 * 计算总钱数
 */
payOnlie.total = function(api) {

	var total = 0;

	var totalArr = api.column(1).data().toArray();

	for (var int = 0; int < totalArr.length; int++) {
		total = parseFloat(total) + parseFloat(totalArr[int]);
	}
	$('#totalValue').html(total);
}

/**
 * js add add button
 */
payOnlie.addButton = function() {

	var toolbarHtml = "";
	toolbarHtml += "<button class='btn btn-primary btn-large' id='scnneralipay' style='margin-bottom: 10px;'>支付宝消费</button>";
	toolbarHtml += "<button class='btn btn-warning btn-large' id='alipay' style='margin-bottom: 10px; margin-left: 10px'>支付宝预支付</button>";
	toolbarHtml += "<button class='btn btn-success btn-large' id='moneyAlipay' style='margin-bottom: 10px; margin-left: 10px'>现金支付</button>";
	$('.toolbar').html(toolbarHtml);

	var totalHtml = "";
	totalHtml += "<span>总金额：</span><span id='totalValue'>0.00</span> 元"
	$('.total').html(totalHtml);
};

/**
 * alipay
 */
payOnlie.alipay = function() {

	$('#alipay').on('click', function() {

		if ($('#totalValue').html() == '0.00' || $('#totalValue').html() == '0.0' || $('#totalValue').html() == '0') {
			alert('请先添加商品！');
			return;
		}

		$('#total4paypage').html($('#totalValue').html());

		$.ajax({
			url : '../pay/prepay.do',
			type : "POST",
			data : {
				'amount' : $('#totalValue').html(),
				'payWay' : 0,
			},
			success : function(data) {

				if (data !== undefined && data.resultCode === 'SUCCESS') {
					$('#successMessage').html('<img style="padding-left: 130px;" id="QRCode" />');
					$('#QRCode').attr('src', data.smallPicUrl);
					$('#QRCodeModal').modal({
						'backdrop' : 'static',
						'show' : true
					});

					payOnlie.outTradeNo = data.outTradeNo;

					payOnlie.timeId = setInterval('payOnlie.getPayStatus()', 1000);
				} else {
					$('#successMessage').html("<span style='padding-left: 180px;'>获取二维码错误，请重试！</span>");
				}
			}
		});
	});
};

/**
 * 模态框的函数
 */
payOnlie.modalMethod = function() {
	$('#QRCodeModal').on('hidden.bs.modal', function(e) {
		window.clearInterval(payOnlie.timeId);
	})
	$('#QRCodeModal').on('shown.bs.modal', function(e) {
		$('#scannerNumber').focus();
		$('#affirmPay').on('click', function() {

			if ($('#scannerNumber').val() === '') {
				return;
			}

			$.ajax({
				url : '../pay/prepay.do',
				type : "POST",
				data : {
					'amount' : $('#totalValue').html(),
					'code' : $('#scannerNumber').val(),
					'payWay' : 2,
				},
				success : function(data) {

					if (data !== undefined && (data.resultCode === 'ORDER_SUCCESS_PAY_INPROCESS' || data.resultCode === 'ORDER_SUCCESS_PAY_SUCCESS')) {
						payOnlie.outTradeNo = data.outTradeNo;
						$('#successMessage').html("<span style='padding-left: 180px;'>正在支付，请稍等。。。</span>");
						payOnlie.timeId = setInterval('payOnlie.getPayStatus()', 1000);
					} else {
						$('#successMessage').html("<span style='padding-left: 180px;'>支付错误，请重试！</span>");
					}
				}
			});
		});
	})

}

/**
 * 扫码支付
 */
payOnlie.scnneralipay = function() {

	$('#scnneralipay').on('click', function() {

		if ($('#totalValue').html() == '0.00' || $('#totalValue').html() == '0.0' || $('#totalValue').html() == '0') {
			alert('请先添加商品！');
			return;
		}

		$('#total4paypage').html($('#totalValue').html());

		var successMessageHtml = '<span style="font-size: 25px; margin-right: 37px;">请扫码: </span><input type="text" autofocus="autofocus" name="number" id="scannerNumber"/>';
		successMessageHtml += "<button class='btn btn-primary' id='affirmPay' style='margin-bottom: 10px; margin-left: 30px;'>支付</button>";

		$('#successMessage').html(successMessageHtml);

		$('#QRCodeModal').modal({
			'backdrop' : 'static',
			'show' : true
		});
	});
}

/**
 * 现金支付
 */
payOnlie.moneyAlipay = function() {

	$('#moneyAlipay').on('click', function() {

		if ($('#totalValue').html() == '0.00' || $('#totalValue').html() == '0.0' || $('#totalValue').html() == '0') {
			alert('请先添加商品！');
			return;
		}

		$.ajax({
			url : '../pay/prepay.do',
			type : "POST",
			data : {
				'amount' : $('#totalValue').html(),
				'payWay' : 1,
			},
			success : function(data) {
				if (data) {
					alert('支付成功');
					payOnlie.dataTable.rows().remove().draw();
				}
			}
		});
	});

}

/**
 * get pay status;
 */
payOnlie.getPayStatus = function() {

	console.log(payOnlie.outTradeNo);

	$.ajax({
		url : '../pay/getStatus/' + payOnlie.outTradeNo + '.do',
		success : function(data) {
			if (data.status === 'TRADE_SUCCESS') {
				window.clearInterval(payOnlie.timeId);
				$('#successMessage').html("<span style='padding-left: 180px;'>支付成功, 正在跳转。。。</span>");
				window.setTimeout(function() {
					$('#QRCodeModal').modal('hide');
					payOnlie.dataTable.rows().remove().draw();
				}, 5000);
			}

		}
	})

}

/**
 * 
 */
$(document).ready(payOnlie.main());
