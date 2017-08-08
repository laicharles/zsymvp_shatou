/**
 * Created by lijinxiong on 2014/8/5.
 */

$(function() {
    SideBar.init({
        shortenOnClickOutside: false
    });

    /*初始化日志列表*/
    $(".editable").dataTable({
        iDisplayLength: 25,/*默认显示数据量*/
        oLanguage: {
            sLengthMenu: ""
        },
        aoColumnDefs: [{
            bSortable: true,/*排序*/
            aTargets: [0]/*排序目标【数组】(当bSortable为false时启用)如：[0,1,2]*/
        }],
        fnInitComplete:function(){/*初始化：把搜索移动到信息块标题工具栏*/
            var $tools = $('.header .tools'),
                $DataTables = $('.DataTables_Table_0_wrapper');
            $filter = $('.DataTables_Table_0_filter');

            $tools.addClass('row-fluid span4');
            $('.btn-group',$tools).addClass('span3 text-right');
            $filter.prependTo($tools).addClass("span9").css({"line-height":"30px","height":"30px","white-space":"nowrap"});
            $(' .row-fluid:first',$DataTables).remove();
        }
    });
});
