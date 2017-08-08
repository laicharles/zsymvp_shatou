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

    /*客服人员选择 开始*/
    $.fn.zTree.init($("#treeDepSel"), {
        view: {
            showIcon:false,
            dblClickExpand: false
        },
        data: {
            simpleData: {
                enable: true
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
    /*客服人员选择 结束*/

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
            aTargets: [0,4]/*排序目标【数组】(当bSortable为false时启用)如：[0,1,2]*/
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

});