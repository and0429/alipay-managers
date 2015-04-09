/**
 * create namespace
 */
var loginer = new Object();

loginer.DataTable = undefined;
loginer.zTreeObj = undefined;
loginer.custOrDistributorId = undefined;
loginer.distributorHasChild = undefined;
loginer.role = undefined;

/**
 * main method
 */
loginer.main = function() {
	loginer.role = util.getRole();
	loginer.loadZtree();
	loginer.loadDataTable();
	loginer.clickSavebtn();
	loginer.clickUpdatebtn();
}

/*-------------------------------------------------------------------------------*/
/**
 * load zTree
 */
loginer.loadZtree = function() {

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
				loginer.custOrDistributorId = treeNode.id;
				loginer.distributorHasChild = treeNode.hasChild
				loginer.DataTable.draw();
				if (treeNode.isParent) {
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
		url : '../login/getZtree.do',
		dataType : 'json',
		success : function(data) {
			loginer.zTreeObj = $.fn.zTree.init($("#distributorTree"), setting, data);
			$('.scrollable').slimScroll({
				height : '604px',
				width : 'auto'
			});
		}
	});
};

/*------------------------------------------------------------------------------------------*/

/**
 * load tables
 */
loginer.loadDataTable = function() {

	loginer.DataTable = $('.table').DataTable(
			{
				"processing" : true,
				"serverSide" : true,
				"ordering" : false,
				"searching" : false,
				"scrollY" : "500px",
				"scrollCollapse" : true,
				"dom" : 'l<"toolbar">rtip',
				"language" : util.dataTableLanguage(),
				"ajax" : {
					'url' : '../login/loginers.do',
					'dataType' : 'json',
					'type' : 'POST',
					'data' : function(d) {
						return $.extend(d, {
							'username' : $.trim($('#search').val()),
							'custOrDistributorId' : loginer.custOrDistributorId
						});
					}
				},

				"columns" : [ {
					"data" : "custOrDistributorName"
				}, {
					"data" : "username"
				}, {
					"data" : "role"
				}, {
					"data" : "id"
				} ],

				"columnDefs" : [
						{
							"targets" : 2,
							"render" : function(data, type, full, meta) {
								var result = '无';
								if (data) {
									switch (data) {
									case 1:
										result = '代理商';
										break;
									case 2:
										result = '分销商';
										break;
									case 3:
										result = '商户';
										break;
									}
								}

								return result;
							}
						},
						{
							"targets" : 3,
							"render" : function(data, type, full, meta) {
								console.log(loginer.role);
								var operationHtml = "<div id='operation' style='display: none;'>";
								operationHtml += '<div class="icon-edit icon-blue-color updateBtn  margin-smallR3" title="修改" style="cursor:pointer" username=' + full.username
										+ ' loginId=' + data + '></div>';
								operationHtml += '<div class="icon-trash icon-blue-color margin-smallR3 deleteBtn" title="删除" style="cursor:pointer" loginId=' + data + '></div>';
								if (loginer.role === 1) {
									operationHtml += "<div class='icon-blue-color restpassword icon-trash icon-key' title='重置密码' style='cursor:pointer' loginId='" + data
											+ "'></div>";
								}
								operationHtml += '</div>';
								return operationHtml;
							}
						} ],

				'drawCallback' : function(settings) {
					loginer.showOrhideOperation();
					loginer.clickDelete();
					loginer.clickEdit();
					loginer.restPassword();
				},

				'initComplete' : function() {
					loginer.addButton();
					loginer.searchEvnet();
					loginer.clickAddBtn();
				}

			});
};

/**
 * show or hide edit and delete log
 */
loginer.showOrhideOperation = function() {
	$('.table tbody').on('mouseover', 'tr', function() {
		$(this).find('#operation').show();
	});
	$('.table tbody').on('mouseout', 'tr', function() {
		$(this).find('#operation').hide();
	});
}

/**
 * click search button
 */
loginer.searchEvnet = function() {
	$('#searchbtu').on('click', function() {
		loginer.DataTable.draw();
	});

	$('#search').on('keyup', function(event) {
		if (event.keyCode === 13) {
			loginer.DataTable.draw();
		}
	});
};

/**
 * add button
 */
loginer.addButton = function() {
	var html = "";
	html += "<input type='search' id='search' placeholder='请输入用户名查询'/>";
	html += "<button class='btn btn-primary' id='searchbtu' style='margin-bottom: 10px; margin-left: 10px;'>查询</button>"
	html += "<button class='btn btn-warning' id='addbtn' disabled='disabled' style='margin-bottom: 10px; margin-left: 10px;'>新增</button>";
	$('.toolbar').html(html);
}

/**
 * click add button
 */
loginer.clickAddBtn = function() {
	$('#addbtn').on(
			'click',
			function() {
				$('#loginForm').clearForm();
				$('#role').val('1');
				$('#inputError').html('');
				$('#myModalLabel').html('新增用户');
				$('#loginForm').attr('action', '../loginer/add.do');
				$('#distributor').html(
						util.getLoginerSelect('loginSelect', 'custOrDistributorId', 'myselect', (loginer.custOrDistributorId ? loginer.custOrDistributorId : 0),
								loginer.distributorHasChild));
				$('#myModal').modal({
					'backdrop' : 'static',
					'show' : true
				});
			});
};

/**
 * click save button
 */
loginer.clickSavebtn = function() {
	$('#saveBtn').on('click', loginer.submitForm);
}

/**
 * click save button
 */
loginer.clickUpdatebtn = function() {
	$('#updateBtn').on('click', loginer.submitUpdateForm);
}

/**
 * submit form and validation
 */
loginer.submitForm = function() {

	$('#loginForm').ajaxSubmit({
		'dataType' : 'json',
		'beforeSubmit' : function(arr, jquery) {

			if (arr[2].value === '') {
				$('#name')[0].focus();
				$('#inputError').html('请填写用户名');
				return false;
			}

			return true;
		},
		'success' : function(data) {
			if (data.code) {
				$('#myModal').modal('hide');
				loginer.DataTable.draw();
			} else {
				$('#inputError').html(data.msg);
			}
		}
	});
};

/**
 * update submit form and validation
 */
loginer.submitUpdateForm = function() {

	$('#updateForm').ajaxSubmit({
		'dataType' : 'json',
		'url' : '../loginer/update.do',
		'beforeSubmit' : function(arr, jquery) {

			if (arr[1].value === '') {
				$('#updatePassword')[0].focus();
				$('#inputError2').html('请填写旧密码');
				return false;
			}

			if (arr[2].value === '') {
				$('#newpassword')[0].focus();
				$('#inputError2').html('请填写新密码');
				return false;
			}
			if (arr[3].value === '') {
				$('#newpassword2')[0].focus();
				$('#inputError2').html('请填写确认密码');
				return false;
			}

			if (arr[2].value !== arr[3].value) {
				$('#newpassword')[0].focus();
				$('#inputError2').html('两次密码不一致');
				return false;
			}

			return true;
		},
		'success' : function(data) {
			if (data.code) {
				$('#updateModal').modal('hide');
				loginer.DataTable.draw(false);
			} else {
				$('#inputError2').html(data.msg);
				$('#updatePassword')[0].focus();
			}
		}
	});
};

/**
 * delete a entity
 */
loginer.clickDelete = function() {

	$('.deleteBtn').on('click', function() {
		var id = $(this).attr('loginId');
		if (window.confirm('确定要删除此条记录吗？')) {
			$.ajax({
				'url' : '../loginer/delete/' + id + '.do',
				'success' : function(data) {
					if (data.code) {
						loginer.DataTable.draw(false);
					}
				}
			});
		}
	});
};

/**
 * click edit button
 */
loginer.clickEdit = function() {

	$('.updateBtn').on('click', function() {

		var id = $(this).attr('loginId');
		var username = $(this).attr('username');

		$('#updateForm').clearForm();
		$('#inputError2').val('');
		$('#updateId').val(id);
		$('#updateUsername').val(username);

		$('#updateModal').modal({
			'backdrop' : 'static',
			'show' : true
		});
	})
}

/**
 * rest password is 111111;
 */
loginer.restPassword = function() {

	$('.restpassword').on('click', function() {

		var id = $(this).attr('loginId');

		if (window.confirm("您确定要将密码设置为“111111”吗？")) {

			$.ajax({
				'url' : '../loginer/restPassword/' + id + '.do',
				'dataType' : 'json',
				'success' : function(data) {
					if (data.code) {
						window.alert('密码重置成功！')
					}
				}
			});
		}

	});
}

$(document).ready(loginer.main());