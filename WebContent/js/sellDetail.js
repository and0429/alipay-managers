function SellDetail() {

}

var sellDetail = new SellDetail();

sellDetail.dataTable = undefined;

/**
 * main method
 */
SellDetail.prototype.main = function() {
	sellDetail.loadDataTable();
	sellDetail.submitRefund();

}

/**
 * load dataTables
 */
SellDetail.prototype.loadDataTable = function() {

	sellDetail.dataTable = $('#sellDetailTable').DataTable({
		"processing" : true,
		"serverSide" : true,
		"scrollY" : "500px",
		"scrollCollapse" : true,
		"ordering" : false,
		"searching" : false,
		"dom" : 'l<"toolbar">rtip',
		"language" : util.dataTableLanguage(),
		"ajax" : {
			'url' : '../pay/sellDetail.do',
			'dateType' : 'json',
			'type' : 'POST',
			'data' : function(d) {
				return $.extend(d, {
					'startDate' : $('#startDate').val() == '' ? undefined : $('#startDate').val(),
					'endDate' : $('#endDate').val() == '' ? undefined : $('#endDate').val() + ' 23:59:59',
				});
			}
		},
		"columns" : [ {
			"data" : "id"
		}, {
			"data" : "amount"
		}, {
			"data" : "payDate"
		}, {
			"data" : "payWay"
		}, {
			"data" : "status"
		}, {
			"data" : "loginer"
		}, {
			"data" : "refundTotal"
		}, {
			"data" : "refundTime"
		}, {
			"data" : "id"
		} ],

		"columnDefs" : [ {
			"targets" : 8,
			"render" : function(data, type, full, meta) {

				if ((full.status === 'TRADE_SUCCESS' && full.payWay === 0) || (full.status === 'TRADE_SUCCESS' && full.payWay === 2)) {
					var operationHtml = "<div id='operation' style='display: none;'>"
					operationHtml += '<div class="refund" style="cursor:pointer" amount=' + full.amount + ' payId=' + data + '>退款</div>';
					operationHtml += '</div>'
					return operationHtml;
				}
				return '';
			}
		}, {
			"targets" : 3,
			"render" : function(data, type, full, meta) {
				switch (data) {
				case 0:
					return '支付宝';
					break;
				case 2:
					return '扫码支付';
					break;
				case 1:
					return '现金';
					break;
				}
			}
		}, {
			"targets" : [ 2, 7 ],
			"render" : function(data, type, full, meta) {
				if (data) {
					return data.split('.')[0];
				}
				return '';
			}
		}, {
			"targets" : 0,
			"render" : function(data, type, full, meta) {
				return '订单号';
			}
		}, {
			"targets" : 4,
			"render" : function(data, type, full, meta) {
				if (data) {
					switch (data) {
					case 'TRADE_SUCCESS':
						return '付款成功';
						break;
					case 'REFUND_SUCCESS':
						return '退款成功';
						break;
					case 'WAIT_BUYER_PAY':
						return '等待付款';
						break;
					}
				}
				return '';
			}
		} ],

		'drawCallback' : function(settings) {
			sellDetail.showOrhideOperation();
			sellDetail.clickRefund();

		},
		'initComplete' : function() {
			sellDetail.addToobar();
			sellDetail.clickSearch();
		}

	});
}

/**
 * add toolbar
 */
SellDetail.prototype.addToobar = function() {
	var toolbarhtml = '';
	toolbarhtml += "<input type='date' id='startDate' placeholder='开始时间'/>";
	toolbarhtml += "<input type='date' id='endDate' placeholder='结束时间' style='margin-left: 10px;'/>";
	toolbarhtml += "<button class='btn btn-primary' id='searchbtu' style='margin-bottom: 10px; margin-left: 10px;'>查询</button>";
	$('.toolbar').html(toolbarhtml);
};

/**
 * show or hide edit and delete log
 */
SellDetail.prototype.showOrhideOperation = function() {
	$('.table tbody').on('mouseover', 'tr', function() {
		$(this).find('#operation').show();
	});
	$('.table tbody').on('mouseout', 'tr', function() {
		$(this).find('#operation').hide();
	});
}

/**
 * click refund
 */
SellDetail.prototype.clickRefund = function() {
	$('.refund').on('click', function() {
		$('#refundtotal').val('');
		$('#inputError').html('');
		$('#tradeNo').val($(this).attr('payId'));
		$('#amount').val($(this).attr('amount'));

		$('#refundModal').modal({
			'backdrop' : 'static',
			'show' : true
		});
	})
};

/**
 * 
 */
SellDetail.prototype.submitRefund = function() {

	$('#saveBtn').on('click', function() {

		var refundTotal = $('#refundtotal').val();
		var tradeNo = $('#tradeNo').val();
		var amount = $('#amount').val();

		var patton = /^[0-9]+([.]{1}[0-9]{1,2}){0,1}$/;

		$.ajax({
			url : '../pay/refund/' + tradeNo + '/' + refundTotal + '.do',
			beforeSend : function() {
				if (patton.test(refundTotal)) {

					if (parseFloat(refundTotal) > parseFloat(amount)) {
						$('#inputError').html('退款金额不能大于交易金额');
						return false;
					} else {
						return true;
					}

				} else {
					$('#inputError').html('请正确输入');
					return false;
				}
			},
			success : function(data) {
				if (data) {
					$('#refundModal').modal('hide');
					sellDetail.dataTable.draw(false);
				} else {
					$('#inputError').html('退款失败，请重试');
				}
			}
		});
	});
}

SellDetail.prototype.clickSearch = function() {
	$('#searchbtu').on('click', function() {
		sellDetail.dataTable.draw();
	});
}

/**
 * execute this method when the document is ready
 */
$(document).ready(sellDetail.main());