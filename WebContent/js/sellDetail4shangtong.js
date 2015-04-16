/**
 * constructor
 */
function SellDetail4shangtong() {

};

/**
 * prototype override
 */
SellDetail4shangtong.prototype = {

	constructor : SellDetail4shangtong,

	main : function() {
		sd4st.loadDataTable();
	},

	loadDataTable : function() {

		sd4st.dataTable = $('.table').DataTable({

			"processing" : true,
			"serverSide" : true,
			"ordering" : false,
			"scrollY" : "500px",
			"scrollCollapse" : true,
			"searching" : false,
			"dom" : 'l<"toolbar">rtip',
			"language" : util.dataTableLanguage(),
			"ajax" : {
				'url' : '../sellDetail4Distributor/getSellDetail4Distributors.do',
				'dataType' : 'json',
				'type' : 'POST',
				'data' : function(d) {
					return $.extend(d, {
						'distributorId' : $('#distributorSelectedId').val() == '' ? undefined : $('#distributorSelectedId').val(),
						'custName' : $.trim($('#search').val()) == '' ? undefined : $.trim($('#search').val()),
						'payDate' : $('#month').val() == '' ? undefined : $('#month').val()
					});
				}
			},
			"columns" : [ {
				"data" : "amount"
			}, {
				"data" : "payDate"
			}, {
				"data" : "custName"
			}, {
				"data" : "refundTotal"
			}, {
				"data" : "refundTime"
			} ],

			"columnDefs" : [ {
				"targets" : [ 0, 3 ],
				"render" : function(data, type, full, meta) {
					if (data !== undefined) {
						return data;
					}
					return '';
				}
			}, {
				"targets" : 1,
				"render" : function(data, type, full, meta) {
					if (data) {
						return data.split('.')[0];
					}
					return '';

				}
			} ],

			'drawCallback' : function(settings) {

			},
			'initComplete' : function() {
				sd4st.addToolbar();
				sd4st.clickSearch();
				sd4st.zTree();
			}

		});
	},

	addToolbar : function() {
		var html = '';
		html += "<input type='search' id='distributorSelect' readonly placeholder='请选择分销商'/>";
		html += "<input type='search' style='margin-left: 10px; width: 140px' id='search' placeholder='请输入商户名称查询'/>";
		html += "<input type='month' id='month'style='margin-left: 10px;  width: 130px' />";
		html += "<button class='btn btn-primary' id='searchbtu' style='margin-bottom: 10px; margin-left: 10px;'>查询</button>"
		html += "<div><ul id='ztree' class='ztree'></ul></div>";
		html += "<input type='hidden' id='distributorSelectedId' />";
		$('.toolbar').html(html);
	},

	clickSearch : function() {
		$('#searchbtu').on('click', function() {
			sd4st.dataTable.draw();
		});
	},

	zTree : function() {

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
				onClick : sd4st.clickDistributorNode
			}
		};

		$.ajax({
			url : '../distributor/getZtree4Loginer.do',
			async : false,
			success : function(data) {
				if (data) {
					$.fn.zTree.init($("#ztree"), setting, data);
					sd4st.clickSelectDistributor();
				}
			}
		});
	},

	clickSelectDistributor : function() {
		$('#distributorSelect').on('focus', function() {
			$('#ztree').show();
		})
	},

	clickDistributorNode : function(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("ztree");
		var node = zTree.getSelectedNodes();
		$('#distributorSelect').val(node[0].name);
		$('#distributorSelectedId').val(node[0].id);
		$('#ztree').hide();
	}
};

/**
 * create new Object
 */
var sd4st = new SellDetail4shangtong();

sd4st.dataTable = undefined;

/**
 * execute the method when zhe document is ready
 */
$.ready(sd4st.main());