$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/roomorderinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'orderid', name: 'orderid', index: 'OrderId', width: 50, key: true },
			{ label: '', name: 'roomid', index: 'RoomId', width: 80 }, 			
			{ label: '', name: 'userid', index: 'UserID', width: 80 }, 			
			{ label: '', name: 'transactor', index: 'Transactor', width: 80 }, 			
			{ label: '', name: 'deposit', index: 'Deposit', width: 80 }, 			
			{ label: '', name: 'arrivetime', index: 'ArriveTime', width: 80 }, 			
			{ label: '', name: 'remarks', index: 'Remarks', width: 80 }			
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
		roomorderinfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.roomorderinfo = {};
		},
		update: function (event) {
			var orderid = getSelectedRow();
			if(orderid == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(orderid)
		},
		saveOrUpdate: function (event) {
		    $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.roomorderinfo.orderid == null ? "sys/roomorderinfo/save" : "sys/roomorderinfo/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.roomorderinfo),
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
			var orderids = getSelectedRows();
			if(orderids == null){
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
                        url: baseURL + "sys/roomorderinfo/delete",
                        contentType: "application/json",
                        data: JSON.stringify(orderids),
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
		getInfo: function(orderid){
			$.get(baseURL + "sys/roomorderinfo/info/"+orderid, function(r){
                vm.roomorderinfo = r.roomorderinfo;
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