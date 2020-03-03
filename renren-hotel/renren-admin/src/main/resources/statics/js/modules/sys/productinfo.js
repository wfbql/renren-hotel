$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/productinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'productid', name: 'productid', index: 'ProductId', width: 50, key: true },
			{ label: '', name: 'producttype', index: 'ProductType', width: 80 }, 			
			{ label: '', name: 'productname', index: 'ProductName', width: 80 }, 			
			{ label: '', name: 'productprice', index: 'ProductPrice', width: 80 }, 			
			{ label: '', name: 'productnumber', index: 'ProductNumber', width: 80 }, 			
			{ label: '', name: 'productimagepath', index: 'ProductImagePath', width: 80 }, 			
			{ label: '', name: 'productsalenumber', index: 'ProductSaleNumber', width: 80 }, 			
			{ label: '', name: 'consumepoint', index: 'ConsumePoint', width: 80 }			
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
		productinfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.productinfo = {};
		},
		update: function (event) {
			var productid = getSelectedRow();
			if(productid == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(productid)
		},
		saveOrUpdate: function (event) {
		    $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.productinfo.productid == null ? "sys/productinfo/save" : "sys/productinfo/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.productinfo),
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
			var productids = getSelectedRows();
			if(productids == null){
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
                        url: baseURL + "sys/productinfo/delete",
                        contentType: "application/json",
                        data: JSON.stringify(productids),
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
		getInfo: function(productid){
			$.get(baseURL + "sys/productinfo/info/"+productid, function(r){
                vm.productinfo = r.productinfo;
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