$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/orderiteminfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'orderitemid', name: 'orderitemid', index: 'OrderItemId', width: 50, key: true },
			{ label: '', name: 'productid', index: 'ProductId', width: 80 }, 			
			{ label: '', name: 'orderid', index: 'OrderId', width: 80 }, 			
			{ label: '', name: 'productnumber', index: 'ProductNumber', width: 80 }, 			
			{ label: '', name: 'producttotolprice', index: 'ProductTotolPrice', width: 80 }			
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
		orderiteminfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.orderiteminfo = {};
		},
		update: function (event) {
			var orderitemid = getSelectedRow();
			if(orderitemid == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(orderitemid)
		},
		saveOrUpdate: function (event) {
		    $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.orderiteminfo.orderitemid == null ? "sys/orderiteminfo/save" : "sys/orderiteminfo/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.orderiteminfo),
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
			var orderitemids = getSelectedRows();
			if(orderitemids == null){
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
                        url: baseURL + "sys/orderiteminfo/delete",
                        contentType: "application/json",
                        data: JSON.stringify(orderitemids),
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
		getInfo: function(orderitemid){
			$.get(baseURL + "sys/orderiteminfo/info/"+orderitemid, function(r){
                vm.orderiteminfo = r.orderiteminfo;
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