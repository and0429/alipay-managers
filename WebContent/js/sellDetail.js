function SellDetail() {

}

var sellDetail = new SellDetail();

sellDetail.dataTable = undefined;

/**
 * main method
 */
SellDetail.prototype.main = function() {
	sellDetail.loadDataTable();

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
			"data" : "category"
		}, {
			"data" : "amount"
		}, {
			"data" : "payDate"
		}, {
			"data" : "payWay"
		}, {
			"data" : "loginer"
		}, {
			"data" : "id"
		} ],

		"columnDefs" : [ {
			"targets" : 5,
			"render" : function(data, type, full, meta) {
				var operationHtml = "<div id='operation' style='display: none;'>"
				operationHtml += '<div class="refund" style="cursor:pointer" payId=' + data + '>退款</div>';
				operationHtml += '</div>'
				return operationHtml;
			}
		}, {
			"targets" : 3,
			"render" : function(data, type, full, meta) {
				switch (data) {
				case 0:
					return '支付宝';
					break;

				case 1:
					return '现金';
					break;
				}
			}
		}, {
			"targets" : 2,
			"render" : function(data, type, full, meta) {
				return data.split('.')[0];
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
		console.log($(this).attr('payId'))
	})
};

SellDetail.prototype.clickSearch = function() {
	$('#searchbtu').on('click', function() {
		sellDetail.dataTable.draw();
	});
}

/**
 * execute this method when the document is ready
 */
$(document).ready(sellDetail.main());