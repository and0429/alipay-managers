/**
 * constructor
 */
function Paymonth() {

}

/**
 * new Object
 */
var pm = new Paymonth();

pm.dataTable = undefined;

/**
 * method main
 */
Paymonth.prototype.main = function() {
	pm.loadDataTable();
};

/**
 * load dataTable
 */
Paymonth.prototype.loadDataTable = function() {
	pm.dataTable = $('#PaymonthTable').DataTable({
		"processing" : true,
		"serverSide" : true,
		"scrollY" : "500px",
		"scrollCollapse" : true,
		"ordering" : false,
		"searching" : false,
		"dom" : 'l<"toolbar">rtip',
		"language" : util.dataTableLanguage(),
		"ajax" : {
			'url' : '../paymonth4distributor/paymonths.do',
			'dataType' : 'json',
			'type' : 'POST',
			'data' : function(d) {
				return $.extend(d, {
					'distributorId' : $('#distributorSelectedId').val() == '' ? undefined : $('#distributorSelectedId').val(),
					'month' : $('#month').val() == '' ? undefined : $('#month').val()
				});
			}
		},
		"columns" : [ {
			"data" : "distributor.name"
		}, {
			"data" : "month"
		}, {
			"data" : "total"
		}, {
			"data" : "deduct"
		}, {
			"data" : "deductMoney"
		} ],

		"columnDefs" : [ {
			"targets" : [ 2, 3, 4 ],
			"render" : function(data, type, full, meta) {
				return data.toFixed(2);
			}
		} ],

		'drawCallback' : function(settings) {

		},
		'initComplete' : function() {
			pm.addToolbar();
			pm.clickSearch();
			pm.zTree();
		}
	});
};

/**
 * add toolbar tools
 */
Paymonth.prototype.addToolbar = function() {
	var html = '';
	html += "<input type='search' id='distributorSelect' readonly placeholder='请选择分销商'/>";
	html += "<input type='month' id='month'style='margin-left: 10px;  width: 130px' />";
	html += "<button class='btn btn-primary' id='searchbtu' style='margin-bottom: 10px; margin-left: 10px;'>查询</button>"
	html += "<div><ul id='ztree' class='ztree'></ul></div>";
	html += "<input type='hidden' id='distributorSelectedId' />";
	$('.toolbar').html(html);
}

/**
 * search event;
 */
Paymonth.prototype.clickSearch = function() {
	$('#searchbtu').on('click', function() {
		pm.dataTable.draw();
	});
}

/**
 * load zTree
 */
Paymonth.prototype.zTree = function() {

	var setting = {
		view : {
			dblClickExpand : false,
			selectedMulti : false,
			showLine : false,
			showIcon : false
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onClick : pm.clickDistributorNode
		}
	};

	$.ajax({
		url : '../distributor/getZtree4Loginer.do',
		async : false,
		success : function(data) {
			if (data) {
				$.fn.zTree.init($("#ztree"), setting, data);
				pm.clickSelectDistributor();
			}
		}
	});
}

/**
 * click select distributor
 */
Paymonth.prototype.clickSelectDistributor = function() {
	$('#distributorSelect').on('focus', function() {
		$('#ztree').show();
	})
};

/**
 * select distributor
 */
Paymonth.prototype.clickDistributorNode = function(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("ztree");
	var node = zTree.getSelectedNodes();
	$('#distributorSelect').val(node[0].name);
	$('#distributorSelectedId').val(node[0].id);
	$('#ztree').hide();
}

/**
 * 
 */
$.ready(pm.main());