/**
 * Created by lijinxiong on 2014/8/5.
 */

$(function() {
    SideBar.init({
        shortenOnClickOutside: false
    });

    /*表格checkbox全选*/
    $('table thead :checkbox:first').change(function(){
        var $this = $(this),
            checked = $this[0].checked,
            $tbody = $this.parents('table').find('tbody'),
            $checkbox = $(' :checkbox',$tbody);
        for(var i=0;i<$checkbox.length;i++){
            $checkbox[i].checked = checked;
        }
    });


    $(".more-toggle").click(function(){
        $($(this).attr("href")).slideToggle();
    });

    /*模态化窗口*/
    $(".menuButton[data-toggle=modal]").click(function(){
        $($(this).attr("href")).modal();
    });


    /*初始化管理员列表*/
    $(".editable").dataTable({
        iDisplayLength: 10,/*默认显示数据量*/
        oLanguage: {
            sLengthMenu: ""
        },
        aoColumnDefs: [{
            bSortable: false,/*排序*/
            aTargets: [0,6]/*排序目标【数组】(当bSortable为false时启用)如：[0,1,2]*/
        }],
        fnInitComplete:function(){/*初始化：把搜索移动到信息块标题工具栏*/
            var $tools = $('.header .tools'),
                $DataTables = $('.dataTables_wrapper');
            $filter = $('.dataTables_filter');

            $tools.addClass('row-fluid span4');
            $('.btn-group',$tools).addClass('span3 text-right');
            $filter.prependTo($tools).addClass("span9").css({"line-height":"30px","height":"30px","white-space":"nowrap"});
            $(' .row-fluid:first',$DataTables).remove();
        }
    });
    /*表格点击时显示选中状态*/
    /*$('.dataTable tbody td').click(function(){
        var $tr = $(this).parent(),
            $selCheckbox = $tr.find(':checkbox:first');

        $tr.toggleClass("active");
        $selCheckbox.checked = !$selCheckbox.checked;
     });*/
    /*账号排序*/
    $('.btnSort').click(function(){
        $(".editable").tableDnDUpdate();
        $(".editable").tableDnD({scrollAmount: 1});
        $('.btnSort_area').slideToggle().children(".btn").click(function(){
            location.reload();
        });
    });

    /*组织架构树 开始*/
    $.fn.zTree.init($("#depTree"),{
        view:{
            selectedMulti:false,
            showLine:false,
            showIcon:true,
            dblClickExpand:false
        },
        edit:{
            enable:false,
            showRemoveBtn:false,
            showRenameBtn:false,
            removeTitle:"删除",
            renameTitle:"重命名",
            drag: {
                isMove: false,
                isCopy: false
            }
        },
        callback:{
            onClick:function(event, treeId, treeNode){
                var zTree = $.fn.zTree.getZTreeObj("depTree");
                checkSort(treeNode);
                zTree.expandNode(treeNode);

            },
            onDblClick:function(event, treeId, treeNode){
                var treeObj = $.fn.zTree.getZTreeObj("depTree");
                treeObj.editName(treeNode);
            }/*,
            onRightClick:function(event, treeId, treeNode){
                $('#depTree a').contextmenu({
                    target: '#context-menu-org'
                });
                *//*var treeObj = $.fn.zTree.getZTreeObj("depTree");
                treeObj.selectNode(treeNode);*//*
            }*/
        },
        check:{enable:false},
        data:{simpleData:{enable:true}}
    } ,[
        {id:0,pId:-1,name:"佳米科技(110)",open:true,drag:false},
        {id:1,pId:0, name:"总裁办(4)"},
        {id:2,pId:0, name:"产品中心(59)",open:true},
        {id:20,pId:2, name:"产品研发部(40)",open:true},
        {id:201,pId:20, name:"IOS研发组(12)"},
        {id:202,pId:20, name:"Android研发组(18)"},
        {id:203,pId:20, name:"PC研发组(10)"},
        {id:21,pId:2, name:"产品规划部(4)"},
        {id:22,pId:2, name:"产品运营部(6)"},
        {id:23,pId:2, name:"用户体验部(9)"},
        {id:3,pId:0, name:"人力资源部(9)"},
        {id:31,pId:3, name:"人力资源部"},
        {id:32,pId:3, name:"人力资源部"},
        {id:33,pId:3, name:"人力资源部"},
        {id:34,pId:3, name:"人力资源部"},
        {id:35,pId:3, name:"人力资源部"},
        {id:36,pId:3, name:"人力资源部"},
        {id:37,pId:3, name:"人力资源部"},
        {id:38,pId:3, name:"人力资源部"},
        {id:39,pId:3, name:"人力资源部"},
        {id:391,pId:39, name:"人力资源部"},
        {id:392,pId:3, name:"人力资源部"},
        {id:393,pId:3, name:"人力资源部"},
        {id:394,pId:3, name:"人力资源部"}
    ] );
    /*$('#depTree a').attr({"data-target":"#context-menu-org","data-toggle":"context"});*//*取消右键*/
    /*组织架构树 结束*/

    /*移动账号 开始*/
    $.fn.zTree.init($("#depTree4move"),{
        view:{
            selectedMulti:false,
            showLine:true,
            showIcon:false
        },
        edit:{
            enable:true,
            showRemoveBtn:false,
            showRenameBtn:false,
            removeTitle:"删除",
            renameTitle:"重命名",
            drag: {
                isMove: true,
                isCopy: false
            }
        },
        callback:{
            onClick:function(event, treeId, treeNode){
                checkSort(treeNode);
            }
        },
        check:{enable:false},
        data:{simpleData:{enable:true}}
    } ,[
        {id:0,pId:-1,name:"佳米科技 (110)",open:true,drag:false},
        {id:1,pId:0, name:"总裁办(4)"},
        {id:2,pId:0, name:"产品中心(59)",open:true},
        {id:20,pId:2, name:"产品研发部(40)",open:true},
        {id:201,pId:20, name:"IOS研发组(12)"},
        {id:202,pId:20, name:"Android研发组(18)"},
        {id:203,pId:20, name:"PC研发组(10)"},
        {id:21,pId:2, name:"产品规划部(4)"},
        {id:22,pId:2, name:"产品运营部(6)"},
        {id:23,pId:2, name:"用户体验部(9)"},
        {id:3,pId:0, name:"人力资源部(9)"}
    ] );
    /*移动账号 结束*/

    /*部门排序 开始*/
    $.fn.zTree.init($("#depTree4Sort"),{
        view:{
            selectedMulti:false,
            showLine:true,
            showIcon:false
        },
        edit:{
            enable:true,
            showRemoveBtn:false,
            showRenameBtn:false,
            removeTitle:"删除",
            renameTitle:"重命名",
            drag: {
                isMove: true,
                isCopy: false
            }
        },
        callback:{
            onClick:function(event, treeId, treeNode){
                checkSort(treeNode);
            }
        },
        check:{enable:false},
        data:{simpleData:{enable:true}}
    } ,[
        {id:0,pId:-1,name:"佳米科技 (110)",open:true,drag:false},
        {id:1,pId:0, name:"总裁办(4)"},
        {id:2,pId:0, name:"产品中心(59)",open:true},
        {id:20,pId:2, name:"产品研发部(40)",open:true},
        {id:201,pId:20, name:"IOS研发组(12)"},
        {id:202,pId:20, name:"Android研发组(18)"},
        {id:203,pId:20, name:"PC研发组(10)"},
        {id:21,pId:2, name:"产品规划部(4)"},
        {id:22,pId:2, name:"产品运营部(6)"},
        {id:23,pId:2, name:"用户体验部(9)"},
        {id:3,pId:0, name:"人力资源部(9)"}
    ] );

    /*控制排序按钮活动*/
    function checkSort(node){
        var $btnMoveUpgrade = $('.btnMoveUpgrade'),
            $btnMoveUp = $('.btnMoveUp'),
            $btnMoveDown = $('.btnMoveDown'),
            $btnMoveDowngrade = $('.btnMoveDowngrade'),
            $nodeParent = node.getParentNode(),
            $nodePrev = node.getPreNode(),
            $nodeNext = node.getNextNode();
        $('.controlPanel a').addClass("disabled");
        if($nodeParent !== null){
            $btnMoveUpgrade.removeClass("disabled");
        }
        if($nodePrev !== null){
            $btnMoveUpgrade.removeClass("disabled");
            $btnMoveUp.removeClass("disabled");
            $btnMoveDowngrade.removeClass("disabled");
        }
        if($nodeNext !== null){
            $btnMoveUpgrade.removeClass("disabled");
            $btnMoveDown.removeClass("disabled");
        }
    }
    /*按钮方式排序部门:上移*/
    $('.btnMoveUp').click(function(){
        var zTree = $.fn.zTree.getZTreeObj("depTree4Sort"),
            treeNode = zTree.getSelectedNodes()[0];

        if (typeof(treeNode) !== 'undefined'){
            zTree.moveNode(treeNode,treeNode.getPreNode(),"next");
            checkSort(treeNode);
        }
    });
    /*按钮方式排序部门:下移*/
    $('.btnMoveDown').click(function(){
        var zTree = $.fn.zTree.getZTreeObj("depTree4Sort"),
            treeNode = zTree.getSelectedNodes()[0];

        if (typeof(treeNode) !== 'undefined'){
            zTree.moveNode(treeNode,treeNode.getNextNode(),"prev");
            checkSort(treeNode);
        }
    });
    /*部门排序 结束*/

    /*新增员工 部门选择 开始*/
    $.fn.zTree.init($("#treeDep"), {
        check: {
            enable: true,
            chkboxType: {"Y":"", "N":""}
        },
        view: {
            showIcon:false,
            dblClickExpand: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            beforeClick: beforeClick,
            onCheck: onCheck
        }
    }, [
        {id:0,pId:-1,name:"佳米科技",open:true,drag:false},
        {id:1,pId:0, name:"总裁办"},
        {id:2,pId:0, name:"产品中心",open:true},
        {id:20,pId:2, name:"产品研发部",open:true},
        {id:201,pId:20, name:"IOS研发组"},
        {id:202,pId:20, name:"Android研发组"},
        {id:203,pId:20, name:"PC研发组"},
        {id:21,pId:2, name:"产品规划部"},
        {id:22,pId:2, name:"产品运营部"},
        {id:23,pId:2, name:"用户体验部"},
        {id:3,pId:0, name:"人力资源部"}
    ]);
    /*新增员工 部门选择 结束*/

    /*上移员工 开始*/
    $('.btnMoveUp:not(:first)').click(function(){
        var $this = $(this),
            $trNode = $this.parents("tr"),
            $trPrevNode = $trNode.prev("tr");
        if($trPrevNode){
            $trPrevNode.before($trNode);
        }
    });
    /*上移员工 结束*/
    /*下移员工 开始*/
    $('.btnMoveDown:not(:last)').click(function(){
        var $this = $(this),
            $trNode = $this.parents("tr"),
            $trNextNode = $trNode.next("tr");
        if($trNextNode){
            $trNextNode.after($trNode);
        }
    });
    /*下移员工 结束*/
});

/*新增员工 部门选择 函数 开始*/
function beforeClick(treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("treeDep");
    zTree.checkNode(treeNode, !treeNode.checked, null, true);
    return false;
}
function onCheck(e, treeId, treeNode) {
    var zTree = $.fn.zTree.getZTreeObj("treeDep"),
        nodes = zTree.getCheckedNodes(true),
        v = "";
    for (var i=0, l=nodes.length; i<l; i++) {
        v += nodes[i].name + ",";
    }
    if (v.length > 0 ) v = v.substring(0, v.length-1);
    var cityObj = $("#DepSel");
    cityObj.attr("value", v);
}
function showMenu(event) {
    event = event? event: window.event;
    var $el = $(event.srcElement ? event.srcElement:event.target);
   /* var cityObj = $("#DepSel");
    var cityOffset = $("#DepSel").offset();
    $("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");*/
    $("#menuContent").css({left:$el.offset().left + "px", top:$el.offset().top + $el.outerHeight() + "px"}).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
    $("#menuContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "DepSel" || event.target.id == "DepSel2" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
        hideMenu();
    }
}
/*新增员工 部门选择 函数 结束*/