<%@ page  contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>
<%@ include  file="../base.jsp"  %>

<div class="navbar-inner">
            <div class="container-fluid">
            <ul class="nav visible-desktop">
                <li class="dropdown visible-desktop "  style="display:none">
                <a href="#layouts" class="dropdown-toggle" data-toggle="dropdown">Layouts<b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="#" target=&quot;_blank&quot;>Blank Page</a></li>
                    <li><a href="#" target=&quot;_blank&quot;>Full Width Page</a></li>
                </ul>
            </li>
            </ul>
            <ul class="nav pull-right nav-indicators">
                <li class="dropdown nav-notifications"  style="display:none">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><span class="badge">9</span><i class="icon-warning-sign"></i></a>
                    <ul class="dropdown-menu">
                        <li class="nav-notifications-header">
                            <a tabindex="-1" href="#">
                                You have
                                <strong>
                                    9
                                </strong>
                                new notifications
                            </a>
                        </li>
                        <li class="nav-notification-body text-info">
                            <a href="#">
                                <i class="icon-user">
                                </i>
                                New User
                                <small class="pull-right">
                                    Just Now
                                </small>
                            </a>
                        </li>
                        <li class="nav-notification-body text-error">
                            <a href="#">
                                <i class="icon-user">
                                </i>
                                User Deleted
                                <small class="pull-right">
                                    Just Now
                                </small>
                            </a>
                        </li>
                    </ul>
                </li>
                <li class="divider-vertical"></li>
                <li class="dropdown">
                	<a href="javascript:document.getElementById('logOutForm').submit();"><i class="icon-off"></i>退出</a>
                    <!--<a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="icon-caret-down"></i></a>
                    <ul class="dropdown-menu">
                         <li><a href="#"><i class="icon-cogs"></i>Settings</a></li> 
                        <li><a href="javascript:document.getElementById('logOutForm').submit();"><i class="icon-off"></i>退出</a></li>
	                         <li class="divider"></li>
	                         <li><a href="#"><i class="icon-info-sign"></i>Help</a></li> 
                    </ul>-->
                </li>
                 <form:form id="logOutForm" action="${pageContext.request.contextPath}/cus/j_spring_security_logout.do" method="post">
                </form:form>
            </ul>
            </div>
            </div>