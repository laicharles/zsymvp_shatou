/**
 * Created by lijinxiong on 2014/8/6.
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

    /*模态化窗口*/
    $(".menuButton[data-toggle=modal]").click(function(){
        $($(this).attr("href")).modal();
    });

    /*表格点击时显示选中状态*/
    /*$('.table td').click(function(){
     *//*$(this).toggleClass("active");*//*
     var elCheckbox = $(" :first :checkbox",this);
     if(elCheckbox.length > 0){
     elCheckbox[0].checked = !elCheckbox[0].checked;
     }
     });*/

    /*初始化管理员列表*/
    $(".editable").dataTable({
        iDisplayLength: 25,/*默认显示数据量*/
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

    /*选择管理员 开始*/
    var availableTags = ["admin(梁志军)","lei-li(李雷)","hua-li(李花)","liang-li(李良)"];
    function split( val ) {return val.split( /,\s*/ );}
    function extractLast( term ) {return split( term ).pop();}

    $( "#txtManager_add" )
        .bind( "keydown", function( event ) {
            if ( event.keyCode === $.ui.keyCode.TAB &&
                $( this ).autocomplete( "instance" ).menu.active ) {
                event.preventDefault();
            }
        })
        .autocomplete({
            minLength: 0,
            source: function( request, response ) {
                response( $.ui.autocomplete.filter(
                    availableTags, extractLast( request.term ) ) );
            },
            focus: function() {
                return false;
            },
            select: function( event, ui ) {
                var terms = split( this.value );
                terms.pop();
                terms.push( ui.item.value );
                terms.push( "" );
                this.value = terms.join( ", " );
                return false;
            }
        });
    /*选择管理员 结束*/

    /*显示更多广播范围 开始*/
    $("[data-toggle='popover']").popover({
        container: ".wraper",
        trigger: "hover"
    });
    /*显示更多广播范围 结束*/

    /*管理员选择 开始*/
    $.fn.zTree.init($("#treeDepSelMng"), {
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
            onClick:function(){
                $('#selAccount').empty().append("<option>梁妙</option><option>李飞</option><option>关为兰</option><option>赵三</option>");
            }
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
    $('#selAccount option').dblclick(function(){
        $(this).clone().prependTo($('#selAccount_selected'));
    });
    $('#selAccount_selected option').dblclick(function(){
        $(this).remove();
    });
    /*管理员选择 结束*/

    /*选择广播范围 部门选择 开始*/
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
    /*选择广播范围 部门选择 结束*/
});
/*选择广播范围 部门选择 函数 开始*/
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
    var cityObj = $("#DepSel_add:visible,#DepSel_edit:visible");
    cityObj.attr("value", v);
}
function showMenu() {
    var cityObj = $(event.target);
    var cityOffset = cityObj.offset();
    $("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

    $("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
    $("#menuContent").fadeOut("fast");
    $("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
    if (!(event.target.id == "menuBtn" || event.target.id == "DepSel_add" || event.target.id == "DepSel_edit" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
        hideMenu();
    }
}
/*选择广播范围 部门选择 函数 结束*/