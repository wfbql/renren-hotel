$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/workerinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'workerid', name: 'workerid', index: 'WorkerId', width: 50, key: true },
			{ label: '', name: 'workername', index: 'WorkerName', width: 80 }, 			
			{ label: '', name: 'worksex', index: 'WorkSex', width: 80 }, 			
			{ label: '', name: 'workeridcard', index: 'WorkerIdCard', width: 80 }, 			
			{ label: '', name: 'workerpassword', index: 'WorkerPassword', width: 80 }			
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
    });
});

var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		workerinfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.workerinfo = {};
		},
		update: function (event) {
			var workerid = getSelectedRow();
			if(workerid == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(workerid)
		},
		saveOrUpdate: function (event) {
		    $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.workerinfo.workerid == null ? "sys/workerinfo/save" : "sys/workerinfo/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.workerinfo),
                    success: function(r){
                        if(r.code === 0){
                             layer.msg("操作成功", {icon: 1});
                             vm.reload();
                             $('#btnSaveOrUpdate').button('reset');
                             $('#btnSaveOrUpdate').dequeue();
                        }else{
                            layer.alert(r.msg);
                            $('#btnSaveOrUpdate').button('reset');
                            $('#btnSaveOrUpdate').dequeue();
                        }
                    }
                });
			});
		},
		del: function (event) {
			var workerids = getSelectedRows();
			if(workerids == null){
				return ;
			}
			var lock = false;
            layer.confirm('确定要删除选中的记录？', {
                btn: ['确定','取消'] //按钮
            }, function(){
               if(!lock) {
                    lock = true;
		            $.ajax({
                        type: "POST",
                        url: baseURL + "sys/workerinfo/delete",
                        contentType: "application/json",
                        data: JSON.stringify(workerids),
                        success: function(r){
                            if(r.code == 0){
                                layer.msg("操作成功", {icon: 1});
                                $("#jqGrid").trigger("reloadGrid");
                            }else{
                                layer.alert(r.msg);
                            }
                        }
				    });
			    }
             }, function(){
             });
		},
		getInfo: function(workerid){
			$.get(baseURL + "sys/workerinfo/info/"+workerid, function(r){
                vm.workerinfo = r.workerinfo;
            });
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});