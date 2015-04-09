/**
 * constructor
 */
function Paymonth4distributor() {

}

/**
 * new Object
 */
var p4d = new Paymonth4distributor();

p4d.dataTable = undefined;

/**
 * method main
 */
Paymonth4distributor.prototype.main = function() {
	p4d.loadDataTable();
};

/**
 * load dataTable
 */
Paymonth4distributor.prototype.loadDataTable = function() {
	p4d.dataTable = $('#paymonth4distributorTable').DataTable({
		"processing" : true,
		"serverSide" : true,
		"scrollY" : "500px",
		"scrollCollapse" : true,
		"ordering" : false,
		"searching" : false,
		"dom" : 'l<"toolbar">rtip',
		"language" : util.dataTableLanguage(),
		"ajax" : {
			'url' : '../paymonth4cust/paymonth4custs.do',
			'dataType' : 'json',
			'type' : 'POST',
			'data' : function(d) {
				return $.extend(d, {
					'distributorId' : $('#distributorSelectedId').val() == '' ? undefined : $('#distributorSelectedId').val(),
					'custName' : $.trim($('#search').val()) == '' ? undefined : $.trim($('#search').val()),
					'month' : $('#month').val() == '' ? undefined : $('#month').val()
				});
			}
		},
		"columns" : [ {
			"data" : "month"
		}, {
			"data" : "cust.name"
		}, {
			"data" : "total"
		}, {
			"data" : "deduct"
		}, {
			"data" : "deduct"
		} ],

		"columnDefs" : [ {
			"targets" : [ 2, 3 ],
			"render" : function(data, type, full, meta) {
				return data.toFixed(2);
			}
		}, {
			"targets" : 4,
			"render" : function(data, type, full, meta) {

				console.log(data * full.total);

				return (data * full.total).toFixed(2);
			}
		} ],

		'drawCallback' : function(settings) {

		},
		'initComplete' : function() {
			p4d.addToolbar();
			p4d.clickSearch();
			p4d.zTree();
		}
	});
};

/**
 * add toolbar tools
 */
Paymonth4distributor.prototype.addToolbar = function() {
	var html = '';
	html += "<input type='search' id='distributorSelect' readonly placeholder='请选择分销商'/>";
	html += "<input type='search' style='margin-left: 10px; width: 140px' id='search' placeholder='请输入商户名称查询'/>";
	html += "<input type='month' id='month'style='margin-left: 10px;  width: 130px' />";
	html += "<button class='btn btn-primary' id='searchbtu' style='margin-bottom: 10px; margin-left: 10px;'>查询</button>"
	html += "<div><ul id='ztree' class='ztree'></ul></div>";
	html += "<input type='hidden' id='distributorSelectedId' />";
	$('.toolbar').html(html);
}

/**
 * search event;
 */
Paymonth4distributor.prototype.clickSearch = function() {
	$('#searchbtu').on('click', function() {
		p4d.dataTable.draw();
	});
}

/**
 * load zTree
 */
Paymonth4distributor.prototype.zTree = function() {

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
			onClick : p4d.clickDistributorNode
		}
	};

	$.ajax({
		url : '../distributor/getZtree4Loginer.do',
		async : false,
		success : function(data) {
			if (data) {
				$.fn.zTree.init($("#ztree"), setting, data);
				p4d.clickSelectDistributor();
			}
		}
	});
}

/**
 * click select distributor
 */
Paymonth4distributor.prototype.clickSelectDistributor = function() {
	$('#distributorSelect').on('focus', function() {
		$('#ztree').show();
	})
};

/**
 * select distributor
 */
Paymonth4distributor.prototype.clickDistributorNode = function(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("ztree");
	var node = zTree.getSelectedNodes();
	$('#distributorSelect').val(node[0].name);
	$('#distributorSelectedId').val(node[0].id);
	$('#ztree').hide();
}

/**
 * 
 */
$.ready(p4d.main());