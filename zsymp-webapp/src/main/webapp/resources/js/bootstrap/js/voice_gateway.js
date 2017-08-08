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
});