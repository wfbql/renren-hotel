$(function () {
    $("#jqGrid").jqGrid({
        url: baseURL + 'sys/userinfo/list',
        datatype: "json",
        colModel: [			
			{ label: 'userid', name: 'userid', index: 'UserID', width: 50, key: true },
			{ label: '', name: 'usertype', index: 'UserType', width: 80 }, 			
			{ label: '', name: 'username', index: 'UserName', width: 80 }, 			
			{ label: '', name: 'userpassword', index: 'UserPassword', width: 80 }, 			
			{ label: '', name: 'userpicaddress', index: 'UserPicAddress', width: 80 }, 			
			{ label: '', name: 'useremail', index: 'UserEmail', width: 80 }, 			
			{ label: '', name: 'userphone', index: 'UserPhone', width: 80 }, 			
			{ label: '', name: 'usercarddate', index: 'UserCardDate', width: 80 }, 			
			{ label: '', name: 'useridcard', index: 'UserIdCard', width: 80 }			
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
		userinfo: {}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.userinfo = {};
		},
		update: function (event) {
			var userid = getSelectedRow();
			if(userid == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getInfo(userid)
		},
		saveOrUpdate: function (event) {
		    $('#btnSaveOrUpdate').button('loading').delay(1000).queue(function() {
                var url = vm.userinfo.userid == null ? "sys/userinfo/save" : "sys/userinfo/update";
                $.ajax({
                    type: "POST",
                    url: baseURL + url,
                    contentType: "application/json",
                    data: JSON.stringify(vm.userinfo),
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
			var userids = getSelectedRows();
			if(userids == null){
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
                        url: baseURL + "sys/userinfo/delete",
                        contentType: "application/json",
                        data: JSON.stringify(userids),
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
		getInfo: function(userid){
			$.get(baseURL + "sys/userinfo/info/"+userid, function(r){
                vm.userinfo = r.userinfo;
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