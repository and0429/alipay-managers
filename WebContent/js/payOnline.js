var payOnlie = new Object();

payOnlie.dataTable = undefined;

/**
 * main method
 */
payOnlie.main = function() {
	payOnlie.categorySelect();
	payOnlie.amountOnFocus();
	payOnlie.loadDataTables();
	payOnlie.save2Table();
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
		}, {
			"data" : "paytype"
		}, {
			"data" : "amount"
		} ],

		"drawCallback" : function(settings) {
			var api = this.api();
			payOnlie.total(api);
		},

		"initComplete" : function() {
			payOnlie.addButton();
			payOnlie.alipay();
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
				"paytype" : $('#payType').val(),
				"amount" : $('#amount').val(),
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

	var totalArr = api.column(2).data().toArray();

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
	toolbarHtml += "<button class='btn btn-warning' id='alipay' style='margin-bottom: 10px;'>确认支付</button>";
	$('.toolbar').html(toolbarHtml);

	var totalHtml = "";
	totalHtml += "<span>总金额：</span><span id='totalValue'></span> 元"
	$('.total').html(totalHtml);
};

/**
 * alipay
 */
payOnlie.alipay = function() {

	$('#alipay').on('click', function() {

		$('#QRCode').attr('src', '');

		$.ajax({
			url : '../pay/prepay.do',
			type : "POST",
			data : {
				'total' : $('.total').html()
			},
			success : function(data) {
				$('#QRCode').attr('src', data.smallPicUrl);
				$('#QRCodeModal').modal({
					'backdrop' : 'static',
					'show' : true
				});
			}
		});
	});
};

/**
 * 
 */
$(document).ready(payOnlie.main());