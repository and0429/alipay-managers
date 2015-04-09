var goodsCategory = new Object();

goodsCategory.dataTable = undefined;
goodsCategory.role = undefined;

/**
 * main method
 */
goodsCategory.main = function() {
	goodsCategory.getLoginer();
	goodsCategory.loadDateTables();
	goodsCategory.clickSaveBtn();
}

/**
 * get loginer
 */
goodsCategory.getLoginer = function() {
	$.ajax({
		url : '../getloginer.do',
		dataType : 'json',
		success : function(data) {
			if (data) {
				if (data.role === 3) {
					goodsCategory.role = true;
				}
			}
		}
	});
};

goodsCategory.loadDateTables = function() {

	goodsCategory.dataTable = $('#goodsCategoryTable').DataTable({
		"processing" : true,
		"serverSide" : true,
		"scrollY" : "500px",
		"scrollCollapse" : true,
		"ordering" : false,
		"searching" : false,
		"dom" : 'l<"toolbar">rtip',
		"language" : util.dataTableLanguage(),
		"ajax" : {
			'url' : '../goodsCategory/goodsCategorys.do',
			'dataType' : 'json',
		},
		"columns" : [ {
			"data" : "name"
		}, {
			"data" : "id"
		} ],

		"columnDefs" : [ {
			"targets" : 1,
			"render" : function(data, type, full, meta) {
				var operationHtml = "<div id='operation' style='display: none;'>"
				operationHtml += '<div class="icon-trash icon-blue-color deleteBtn" title="删除" style="cursor:pointer" goodsCategory=' + data + '></div>';
				operationHtml += '</div>'
				return operationHtml;
			}
		} ],

		'drawCallback' : function(settings) {
			goodsCategory.showOrhideOperation();
			goodsCategory.clickDelete();
		},
		'initComplete' : function() {
			goodsCategory.addButton();
			goodsCategory.clickAddBtn();
		}

	});
}

/**
 * show or hide edit and delete log
 */
goodsCategory.showOrhideOperation = function() {
	$('.table tbody').on('mouseover', 'tr', function() {
		$(this).find('#operation').show();
	});
	$('.table tbody').on('mouseout', 'tr', function() {
		$(this).find('#operation').hide();
	});
}

/**
 * js add add button
 */
goodsCategory.addButton = function() {

	if (goodsCategory.role) {
		var html = "";
		html += "<button class='btn btn-warning' id='addbtn' style='margin-bottom: 10px; margin-left: 10px;'>新增</button>";
		$('.toolbar').html(html);
	}
};

/**
 * click add button event
 */
goodsCategory.clickAddBtn = function() {

	$('#addbtn').on('click', function() {
		$('#goodsCategoryForm').clearForm();
		$('#inputError').html('');

		$('#goodsCategory').modal({
			'backdrop' : 'static',
			'show' : true
		});
	})
};

/**
 * click save button
 */
goodsCategory.clickSaveBtn = function() {

	$('#saveBtn').on('click', function() {
		$('#goodsCategoryForm').ajaxSubmit({
			'dataType' : 'json',
			'beforeSubmit' : function(arr, jquery) {

				if (arr[0].value === '') {
					$('#name')[0].focus();
					$('#inputError').html('请填写名称');
					return false;
				}
				return true;
			},
			'success' : function(data) {
				if (data.code) {
					$('#goodsCategory').modal('hide');
					goodsCategory.dataTable.draw();
				} else {
					$('#inputError').html(data.msg);
					$('#name')[0].focus();
				}
			}
		});
	});
}

/**
 * delete a record
 */
goodsCategory.clickDelete = function() {

	$('.deleteBtn').on('click', function() {

		var id = $(this).attr('goodsCategory');

		if (window.confirm("您确定要删除这个商品类别吗？")) {

			$.ajax({
				url : '../goodsCategory/delete/' + id + '.do',
				dataType : 'json',
				success : function(data) {
					if (data.code) {
						goodsCategory.dataTable.draw(false);
					}
				}
			})
		}
	});
};

/**
 * execute the method when the document is ready
 */
$(document).ready(goodsCategory.main());