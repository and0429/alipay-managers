var cust = new Object();

cust.dataTable = undefined;
cust.addOrEditFlag = undefined;
cust.zTreeObj = undefined;
cust.distributorId = undefined;

/**
 * main method
 */
cust.main = function() {
	cust.loadZtree();
	cust.loaddataTable();
	cust.clickSavebtn();
};

/**
 * Load dataTable
 */
cust.loaddataTable = function() {
	cust.dataTable = $('.table').DataTable({
		"processing" : true,
		"serverSide" : true,
		"ordering" : false,
		"searching" : false,
		"scrollY" : "500px",
		"scrollCollapse" : true,
		"dom" : 'l<"toolbar">rtip',
		"language" : util.dataTableLanguage(),
		"ajax" : {
			'url' : '../cust/custs.do',
			'dataType' : 'json',
			'type' : 'POST',
			'data' : function(d) {
				return $.extend(d, {
					'name' : $.trim($('#search').val()),
					'distributorId' : cust.distributorId
				});
			}
		},
		"columns" : [ {
			"data" : "distributor.name"
		}, {
			"data" : "name"
		}, {
			"data" : "addr"
		}, {
			"data" : "manager"
		}, {
			"data" : "tel"
		}, {
			"data" : "id"
		} ],

		"columnDefs" : [ {
			"targets" : 5,
			"render" : function(data, type, full, meta) {
				var operationHtml = "<div id='operation' style='display: none;'>"
				operationHtml += '<div class="icon-edit icon-blue-color updateBtn  margin-smallR3" title="修改" style="cursor:pointer" custId=' + data + '></div>';
				operationHtml += '<div class="icon-trash icon-blue-color deleteBtn" title="删除" style="cursor:pointer" custId=' + data + '></div>';
				operationHtml += '</div>'
				return operationHtml;
			}
		} ],

		'drawCallback' : function(settings) {
			cust.showOrhideOperation();
			cust.clickDelete();
			cust.clickEdit();
		},
		'initComplete' : function() {
			cust.addButton();
			cust.searchEvnet();
			cust.clickAddBtn();
		}

	});
};

/**
 * js add add button
 */
cust.addButton = function() {

	var html = "";
	html += "<input type='search' id='search' placeholder='请输入名称查询'/>";
	html += "<button class='btn btn-primary' id='searchbtu' style='margin-bottom: 10px; margin-left: 10px;'>查询</button>"
	html += "<button class='btn btn-warning' id='addbtn' disabled='disabled' style='margin-bottom: 10px; margin-left: 10px;'>新增</button>";
	$('.toolbar').html(html);
};

/**
 * click search button
 */
cust.searchEvnet = function() {
	$('#searchbtu').on('click', function() {
		cust.dataTable.draw();
	});

	$('#search').on('keyup', function(event) {
		if (event.keyCode === 13) {
			cust.dataTable.draw();
		}
	});
};

/**
 * show or hide edit and delete log
 */
cust.showOrhideOperation = function() {
	$('.table tbody').on('mouseover', 'tr', function() {
		$(this).find('#operation').show();
	});
	$('.table tbody').on('mouseout', 'tr', function() {
		$(this).find('#operation').hide();
	});
}

/**
 * click add button
 */
cust.clickAddBtn = function() {
	$('#addbtn').on('click', function() {
		$('#custForm').clearForm();
		$('#inputError').html('');
		$('#myModalLabel').html('新增商户');
		$('#custForm').attr('action', '../cust/add.do');
		$('#distributor').html(util.getAllDistributors('distributorSelect', 'distributorId', 'myselect'));
		$('#distributorSelect').val(cust.distributorId);
		$('#distributorSelect').attr('disabled', 'disabled');
		$('#myModal').modal({
			'backdrop' : 'static',
			'show' : true
		});
	});
};

/**
 * click save button
 */
cust.clickSavebtn = function() {

	$('#saveBtn').on('click', cust.submitForm);
}

/**
 * submit form and validation
 */
cust.submitForm = function() {

	$('#custForm').ajaxSubmit({
		'dataType' : 'json',
		'data' : {
			'distributorId' : $('#distributorSelect').val()
		},
		'beforeSubmit' : function(arr, jquery) {

			if (arr[0].value === '') {
				$('#name')[0].focus();
				$('#inputError').html('请填写名称');
				return false;
			}
			if (arr[1].value === '') {
				$('#manager')[0].focus();
				$('#inputError').html('请填写商户负责人');
				return false;
			}
			if (arr[2].value === '') {
				$('#tel')[0].focus();
				$('#inputError').html('请填写联系电话');
				return false;
			}
			if (arr[3].value === '') {
				$('#addr')[0].focus();
				$('#inputError').html('请填写联系地址');
				return false;
			}
			return true;
		},
		'success' : function(data) {
			if (data.code) {
				$('#myModal').modal('hide');
				if (cust.addOrEditFlag) {
					cust.dataTable.draw(false);
				} else {
					cust.dataTable.draw();
				}

				cust.addOrEditFlag = undefined;
			}
		},
		'resetForm' : true,
	});
};

/**
 * 点击修改
 */
cust.clickEdit = function() {
	$('.updateBtn').on('click', function() {
		cust.addOrEditFlag = true;
		$('#custForm').clearForm();
		$('#inputError').html('');
		$('#myModalLabel').html('修改商户');
		$('#custForm').attr('action', '../cust/update.do');
		$('#distributor').html(util.getAllDistributors('distributorSelect', 'distributorId', 'myselect'));
		var id = $(this).attr('custId');
		$.ajax({
			'url' : '../cust/getById/' + id + '.do',
			'success' : function(data) {
				if (data) {
					$('#distributorSelect').val((data.distributor.id) ? data.distributor.id : '-1');
					$('#distributorSelect').attr('disabled', 'disabled');
					$('#name').val(data.name);
					$('#id').val(id);
					$('#tel').val(data.tel);
					$('#manager').val(data.manager);
					$('#addr').val(data.addr);
					$('#myModal').modal({
						'backdrop' : 'static',
						'show' : true
					});
				}
			}
		});
	});
}

/**
 * delete a entity
 */
cust.clickDelete = function() {

	$('.deleteBtn').on('click', function() {
		var id = $(this).attr('custId');
		if (window.confirm('确定要删除此条记录吗？')) {
			$.ajax({
				'url' : '../cust/delete/' + id + '.do',
				'success' : function(data) {
					if (data.code) {
						cust.dataTable.draw(false);
					}
				}
			});
		}
	});
};

/*-------------------------------------- load tree ----------------------------------*/

/**
 * load zTree
 */
cust.loadZtree = function() {

	var setting = {
		view : {
			selectedMulti : false,
			showTitle : false,
			dblClickExpand : false,
			showLine : false
		},
		edit : {
			enable : false
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			beforeExpand : false,
			beforeClick : function(treeId, treeNode) {
				return true;
			},
			onClick : function(event, treeId, treeNode) {
				cust.distributorId = treeNode.id
				cust.dataTable.draw();
				if (!treeNode.isParent) {
					$('#addbtn').removeAttr('disabled');
				} else {
					$('#addbtn').attr('disabled', 'disabled');
				}
			}
		}
	};

	/**
	 * get data;
	 */
	$.ajax({
		url : '../distributor/getZtree4Loginer.do',
		dataType : 'json',
		success : function(data) {
			cust.zTreeObj = $.fn.zTree.init($("#distributorTree"), setting, data);
			$('.scrollable').slimScroll({
				height : '604px',
				width : 'auto'
			});
		}
	});
}

/**
 * excute method
 */
cust.main();