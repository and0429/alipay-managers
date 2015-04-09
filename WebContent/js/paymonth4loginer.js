function PayMonth4Loginer() {

};

var payMonth4Loginer = new PayMonth4Loginer();
payMonth4Loginer.dataTable;

/**
 * main method
 */
PayMonth4Loginer.prototype.main = function() {
	payMonth4Loginer.loadDataTable();

}

/**
 * load table
 */
PayMonth4Loginer.prototype.loadDataTable = function() {

	payMonth4Loginer.dataTable = $('#payMonthTable').DataTable({
		"processing" : true,
		"serverSide" : true,
		"ordering" : false,
		"scrollY" : "500px",
		"scrollCollapse" : true,
		"searching" : false,
		"dom" : 'l<"toolbar">rtip',
		"language" : util.dataTableLanguage(),
		"ajax" : {
			'url' : "../payMonth4Loginer/payMonths.do",
			'dateType' : 'json',
			'type' : 'POST',
			'data' : function(d) {
				return $.extend(d, {
					'month' : $('#month').val() == '' ? undefined : $('#month').val()
				});
			}
		},
		"columns" : [ {
			"data" : "month"
		}, {
			"data" : "loginer.username"
		}, {
			"data" : "total"
		} ],

		"columnDefs" : [ {
			"targets" : 2,
			"render" : function(data, type, full, meta) {
				return data;
			}
		} ],

		'drawCallback' : function(settings) {
			// sellDetail.showOrhideOperation();
			// sellDetail.clickRefund();

		},
		'initComplete' : function() {
			payMonth4Loginer.addToobar();
			payMonth4Loginer.clickSearch();
		}

	});
};

/**
 * add toolbar
 */
PayMonth4Loginer.prototype.addToobar = function() {
	var toolbarhtml = '';
	toolbarhtml += "<input type='month' id='month' placeholder='结束时间' style='margin-left: 10px;'/>";
	toolbarhtml += "<button class='btn btn-primary' id='searchbtu' style='margin-bottom: 10px; margin-left: 10px;'>查询</button>";
	$('.toolbar').html(toolbarhtml);
};

/**
 * click search button
 */
PayMonth4Loginer.prototype.clickSearch = function() {
	$('#searchbtu').on('click', function() {
		payMonth4Loginer.dataTable.draw();
	});
};

/**
 * execute this method when zhe document is ready
 */
$.ready(payMonth4Loginer.main());