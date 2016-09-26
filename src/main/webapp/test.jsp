<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title></title>
    <script src="jquery-1.6.1.min.js" type="text/javascript"></script>
    <link rel="stylesheet" href="extra/demo.css" />
</head>
<body>
<style type="text/css">
.box_org_tree{width:800px;height:400px;overflow:hidden;}
.box_org_tree .org_td{vertical-align:top}
/*
.box_org_tree .org_node_c{width:60px; height:60px; margin:0 auto;padding:10px;border-radius:5px}
.box_org_tree .org_node_c .img_corg img{width:50px;height:50px;border:none}
.box_org_tree .org_node_c_inner{width:50px;height:50px;overflow:hidden;border:5px solid #eee;border-radius:5px}
*/

.box_org_tree .org_line_bm{background:url(img/m.png) no-repeat bottom center;-background-image:url(img/ie6/m.gif)}
.box_org_tree .org_line_tm{background:url(img/m.png) no-repeat top center;-background-image:url(img/ie6/m.gif)}
.box_org_tree .org_line_lr{background:url(img/lr.png) no-repeat top center;-background-image:url(img/ie6/lr.gif)}
.box_org_tree .org_line_l{background:url(img/l.png) no-repeat top center;-background-image:url(img/ie6/l.gif)}
.box_org_tree .org_line_r{background:url(img/r.png) no-repeat top center;-background-image:url(img/ie6/r.gif)}

.box_org_tree .org_table_col .org_node_c{margin:0}
.box_org_tree .org_table_col .org_line_bm{background:url(img/m.png) no-repeat 15px bottom;-background-image:url(img/ie6/m.gif)}
.box_org_tree .org_table_col .org_line_tm{background:url(img/b.png) no-repeat 15px -660px;-background-image:url(img/ie6/b.gif)}
.box_org_tree .org_table_col .org_line_lr{background:url(img/tb.png) no-repeat 15px -660px;-background-image:url(img/ie6/tb.gif)}
.box_org_tree .org_table_col .org_line_l{background:url(img/b.png) no-repeat 15px -660px;-background-image:url(img/ie6/b.gif)}
.box_org_tree .org_table_col .org_line_r{background:url(img/tb.png) no-repeat 15px -660px;-background-image:url(img/ie6/tb.gif)}
.box_org_tree .org_table_col .org_td_col{padding-left:15px;text-align:left}

.clearfix:after{content:".";height:0;display:block;visibility:hidden;clear:both}
.clearfix{zoom:1}
.box_org_three{width:800px;overflow:hidden}
.box_org_three .org_node_c{width:60px; height:60px;padding:10px;margin:0;border-radius:5px}
.box_org_three .org_node_c .img_corg img{width:50px;height:50px;border:none}
.box_org_three .org_node_c_inner{width:50px;height:50px;overflow:hidden;border:5px solid #eee;border-radius:5px}
.org_table_col .org_line_bm{background:url(img/m.png) no-repeat 39px bottom;-background-image:url(img/ie6/m.gif)}
.org_table_col .org_line_tm{background:url(img/b.png) no-repeat 39px -660px;-background-image:url(img/ie6/b.gif)}
.org_table_col .org_line_lr{background:url(img/tb.png) no-repeat 39px -660px;-background-image:url(img/ie6/tb.gif)}
.org_table_col .org_line_l{background:url(img/b.png) no-repeat 39px -660px;-background-image:url(img/ie6/b.gif)}
.org_table_col .org_line_r{background:url(img/tb.png) no-repeat 39px -660px;-background-image:url(img/ie6/tb.gif)}

.org_td_col{padding-left:39px}
</style>

<div id="box_org_tree" class="box_org_tree"></div>

<div id="box_org_three" class="box_org_three"></div>

<br/>
<hr/>
<input onclick="drag(this)" type="button" value="开始全屏拖动操作"/>
<hr/>
<style type="text/css">
.opsboard input{width:50px}
.opsboard input[type^=radio]{width:auto}
.opsboard button{margin-top:5px}
</style>
<div class="opsboard" style="position:fixed;right:0;top:0;padding:10px;border:2px solid #666;background:#fff;-position:absolute;right:0;top:0;-width:330px; height:500px;overflow-y:auto;overflow-x:hidden;font:14px/150% Verdana,'微软雅黑','宋体',sans-serif;color:#333">

<div>
<label><input type="radio" name="orgtype" value="1" onclick="chgOrg(1)" checked="checked"/>树形结构</label>
&nbsp;&nbsp;&nbsp;
<label><input type="radio" name="orgtype" value="2" onclick="chgOrg(2)"/>列式结构</label>
</div>
<br/>
<hr/>

导航图
<div id="map" class="map" style="width:300px;height:100px;border:1px solid #64C0F1">
    <div id="mapcont" style="position:relative;width:100%;height:100px;background:#d5d5d5;">
        <div id="maphandle" style="position:absolute;left:0;top:0;width:50px;height:25px;border:1px dashed #000;opacity:0.3;background:#999;cursor:move;z-index:2;filter:alpha(opacity=30);"></div>
        <div id="cloneorg" style="width:100%;height:100%;z-index:-1"></div>
    </div>
</div>
<br/>
<hr/>
<div id="ops7">
<button onclick="chg('column')">节点为列式结构2</button>id <input id="chg" value="3" />
</div>
<div id="ops8">
<button onclick="chg('')">节点为树形结构</button>id <input id="chg" value="3" />
</div>
<br/>
<div id="ops1">
<button onclick="refresh()">刷新页面</button>
</div>
<div id="ops2">
<button onclick='$("#box_org_tree,#box_org_three").html("")'>清空节点</button>
</div>
<div id="ops3">
<button onclick="recreate()">重建节点</button>
</div>
<div id="ops4">
<button onclick="del()">删除节点</button>
id <input id="del" value="16" />
</div>
<div id="ops5">
<button onclick="append()">移动节点</button>
from <input id="appendfrom" value="22" />to <input id="appendto" value="7" />
</div>
<div id="ops9">
<button onclick='pos()'>定位节点</button>
id <input id="pos" value="16" />
</div>
<div id="ops6">
<button onclick="add()">添加节点</button>以下节点数组
<br/>
<textarea id="add" style="width:250px;height:250px">
[
{"id":50, "pid":24, "logo":"logo/7.jpg", "name":"新成员50"},
{"id":51, "pid":24, "logo":"logo/8.jpg", "name":"新成员51"},
{"id":52, "pid":50, "logo":"logo/9.jpg", "name":"新成员52"}, 
{"id":53, "pid":50, "logo":"logo/an.gif", "name":"新成员53"}, 
{"id":60, "pid":33, "logo":"logo/an.gif", "name":"新成员60"}, 
{"id":61, "pid":100, "logo":"logo/an.gif", "name":"新成员61"}, 
{"id":54, "pid":52, "logo":"logo/4.jpg", "name":"新成员54"}
]
</textarea>
</div>
<br/>
<pre style="font:14px/150% Verdana,'微软雅黑','宋体',sans-serif;color:#333">
一、本组件实现了纵向树形结构组织架构图。
二、可实现增加、删除、改变节点结构的功能。
三、可以拖动节点，改变节点结构。
四、可以配置扩展节点形态和样式。
五、通过鼠标滚轮可自由放大缩小。
六、拖动节点需要确保document上的mouse事件有效。
</pre>
</div>

<p style="font:14px/150% Verdana,'微软雅黑','宋体',sans-serif;color:#2889A8">
组织架构图Demo，怎么玩？<br/>
一、右侧功能随便点。<br/>
二、鼠标在机构图通过滚轮能放大缩小（Demo缩放范围作了限制），同时因大小能展现不一样的视图。<br/>
三、放大到最大视图时还可以实现不同操作。<br/>
四、通过节点还可能拖动改变节点关系。<br/>
五、可以全屏拖动，也可以通过右侧“导航图”查看，还可以通过右侧“定位节点”。<br/>
六、<b>全浏览器兼容(包括ie6)。</b><br/>
</p>

<script src="bs.orgchart-1.0.js" type="text/javascript"></script>
<script src="extra/demo.js" type="text/javascript"></script>
<script type="text/javascript">
function del(){
    CO.NodeObject.deleteNodeWithData($("#del").val());
}
function add(){
    var wrap = CO.conf.wrap;
    if(wrap.find("table.org_table[type!=tree]").length)return;
    
    var data = eval($("#add").val());
    CO.NodeObject.addNodeWithData(data);
}
function recreate(){
    CO.NodeObject.addNodeWithData(CO.DataObject.source);
}
function refresh(){
    location.reload();
}
function append(){
    var wrap = CO.conf.wrap;
    if(wrap.find("table.org_table[type!=tree]").length)return;
    var wrap = $("#org_td_"+$("#appendto").val()).hide();
    CO.NodeObject.appendNodeWithData($("#org_td_"+$("#appendfrom").val()), $("#org_td_"+$("#appendto").val()));
    wrap.fadeIn(1000);
}
function chgOrg(type){
    $("#box_org_tree,#box_org_three").html("");
    switch(type){
        case 1:{
            $("#ops1, #ops2, #ops3, #ops4, #ops5, #ops6, #ops7, #ops8").show();
            window.CO.conf = {
                "data":data,        
                "wrap":$("#box_org_tree")
            }
        }break;
        case 2:{
            $("#ops4, #ops5, #ops6, #ops7, #ops8").hide();
            window.CO.conf = {
                "data":data2,       
                "orgType":"column",
                "wrap":$("#box_org_three")
            }
        }break;
    }
    window.CO.render();
}
function chg(type){
    var pid = parseInt($("#chg").val());
    var schData = window.CO.DataObject.redefine().searchDataGroup(pid);
    var source = window.CO.DataObject.redefine().formatForRank(schData);//格式化数据

    if($("#org_group_" + pid).length){
        $("#org_group_" + pid).find("table.org_table:first").remove();
    }
    source.shift();
    window.CO.BaseObject.createByRank(source, type);//建树
    
    if(type !== "column")return;
    var td = $("#org_td_" + pid);
    td.find("table.org_table:first").css({
        "border" : "1px dashed #666",
        "border-radius" : "5px"
    });
}

function drag(e){
    if(window.Demo.isWrapDraggable){
        window.Demo.isWrapDraggable = false;
        e.value = "开始全屏拖动操作";
    }else {
        window.Demo.isWrapDraggable = true;
        e.value = "取消全屏拖动操作";
    }
}
function map(){
    var map = $("#map");
    var wrap = $("#box_org_tree");
    var mapcont = $("#mapcont");
    var moveNode = $("#maphandle");
    var target, cloneOrg;
    var mouseStartX, 
        mlStartX, 
        pointX, 
        mx, 
        mouseStartY, 
        mlStartY, 
        pointY, 
        my, 
        startMove;
    var tW, tH, wrapW, wrapH, hW, hH, cW, cH, trateX, trateY, centerX, centerY, tml, tmt, mcenterX, mcenterY;
    map.mouseenter(function(){
        //重新计算地图大小，与架构图成比例，宽不变
        target = wrap.find("table.org_table:first");
        tW = target.width();
        tH = target.height();
        wrapW = wrap.width();
        wrapH = wrap.height();
        hW = moveNode.outerWidth();
        hH = moveNode.outerHeight();
        cW = mapcont.width();
        cH = tH / tW * cW;
        mapcont.height(cH);
        map.height(cH);

        mlStartX = parseFloat(target.css("margin-left"));
        mlStartX = isNaN(mlStartX) ? 0 : mlStartX;
        mlStartY = parseFloat(target.css("margin-top"));
        mlStartY = isNaN(mlStartY) ? 0 : mlStartY;
        
        mcenterX = mlStartX - wrapW / 2;
        mcenterY = mlStartY - wrapH / 2;
        
        trateX = cW / tW;
        trateY = cH / tH;
        centerX = trateX * mcenterX;
        centerY = trateY * mcenterY;
        tml = -centerX - hW / 2;
        tmt = -centerY - hH / 2;
        
        moveNode.css("left", tml);
        moveNode.css("top", tmt);

        $("#cloneorg").html(wrap.html());
        cloneOrg = $("#cloneorg").children("table.org_table");
        cloneOrg.find("div.org_node_c").replaceWith("<div style='margin:0 auto;width:2px;height:2px;background:#000;border:1px solid #fff;-font-size:0'></div>");
        cloneOrg.attr({
            "width": "100%", 
            "height": "100%"
        }).css({
            "margin": "0"
        }).removeClass();
        cloneOrg.find("table").attr("width", "100%").removeClass();
        cloneOrg.find("td, div.org_group").css({
            "margin": "0"
        }).attr("id", "").removeClass();
    });
    moveNode.mousedown(function(e){
        e.preventDefault();
        startMove = true;
        mouseStartX = e.pageX;
        mouseStartY = e.pageY;
        mlStartX = parseFloat(moveNode.css("left"));
        mlStartX = isNaN(mlStartX) ? 0 : mlStartX;
        mlStartY = parseFloat(moveNode.css("top"));
        mlStartY = isNaN(mlStartY) ? 0 : mlStartY;          
    }).mouseup(function(e){
        startMove = false;
    }).mouseout(function(e){
        startMove = false;  
    });
    mapcont.mousemove(function(e){
        e.preventDefault();
        if(!startMove)return;
        pointX = e.pageX;
        pointY = e.pageY;
        mx = pointX - mouseStartX;
        my = pointY - mouseStartY;
        moveNode.css("left", mlStartX + mx + "px");
        moveNode.css("top", mlStartY + my + "px");  
        moveTarget();
    });
    
    //采用以地图放大镜的中心来定位结构图
    function moveTarget(){
        tW = target.width();
        tH = target.height();
        wrapW = wrap.width();
        wrapH = wrap.height();  
        cW = mapcont.width();
        cH = mapcont.height();
        hW = moveNode.outerWidth();
        hH = moveNode.outerHeight();
        
        mcenterX = parseFloat(moveNode.css("left")) + hW / 2;
        mcenterY = parseFloat(moveNode.css("top")) + hH / 2;
        trateX = tW / cW;
        trateY = tH / cH;
        centerX = trateX * mcenterX;
        centerY = trateY * mcenterY;
        tml = centerX - wrapW / 2;
        tmt = centerY - wrapH / 2;
        
        //console.log(target.length);
        target.css("margin-left", -tml);
        target.css("margin-top", -tmt);
    }
}
//定位节点
function pos(){
    var nid = parseInt($("#pos").val());
    var node = $("#org_node_c_" + nid);
    var wrap = $("#box_org_tree");
    var view = wrap.find("table.org_table:first");
    var inner = node.find("div.org_node_c_inner:first");
    
    var wrapXY = wrap.offset();
    var wrapW = wrap.width();
    var wrapH = wrap.height();  
    var nodeXY = node.offset();
    var nodeW = node.outerWidth();
    var nodeH = node.outerHeight();

    var mlX = parseFloat(view.css("margin-left"));
    var mlX = isNaN(mlX) ? 0 : mlX;
    var mlY = parseFloat(view.css("margin-top"));
    var mlY = isNaN(mlY) ? 0 : mlY; 
    
    var tml = mlX - (nodeXY.left - wrapXY.left - wrapW / 2) - nodeW / 2;
    var tmt = mlY - (nodeXY.top - wrapXY.top - wrapH / 2) - nodeH / 2;  
    view.stop().animate({
        "margin-left" : tml,
        "margin-top" : tmt
    }, 500, function(){
        inner.addClass("org_flashborder");
        setTimeout(function(){inner.removeClass("org_flashborder")}, 2000);
    });
}
var data = [
    {"id":12, "pid":3, "logo":"", "name":"张湛忻"},
    {"id":13, "pid":3, "logo":"logo/8.jpg", "name":"张和栩"},
    {"id":7, "pid":12, "logo":"logo/9.jpg", "name":"张妤嫒"}, 
    {"id":10, "pid":5, "logo":"logo/an.gif", "name":"查姆金"}, 
    {"id":5, "pid":2, "logo":"logo/4.jpg", "name":"张梦琪"}, 
    {"id":9, "pid":2, "logo":"logo/6.jpg", "name":"张三"},        
    {"id":1, "pid":0, "logo":"logo/1.jpg", "name":"张璟熙"},
    {"id":2, "pid":1, "logo":"logo/2.jpg", "name":"张乔斯"}, 
    {"id":8, "pid":5, "logo":"logo/3.jpg", "name":"张紫萱"},       
    {"id":3, "pid":1, "logo":"logo/3.jpg", "name":"相札"},
    {"id":6, "pid":2, "logo":"logo/5.jpg", "name":"马延行"},   
    {"id":11, "pid":6, "logo":"logo/5.jpg", "name":"张三"},
    {"id":14, "pid":13, "logo":"logo/5.jpg", "name":"马*泽"},
    {"id":15, "pid":13, "logo":"logo/5.jpg", "name":"马证强"},
    {"id":16, "pid":1, "logo":"logo/13.jpg", "name":"杨伟雪"},
    {"id":17, "pid":1, "logo":"logo/3.jpg", "name":"杨 蓉倚"},
    {"id":18, "pid":22, "logo":"logo/3.jpg", "name":"张三"},
    {"id":19, "pid":21, "logo":"logo/3.jpg", "name":"杨凯瞎"},
    {"id":20, "pid":22, "logo":"logo/3.jpg", "name":"张三"},
    {"id":21, "pid":16, "logo":"logo/3.jpg", "name":"张三"},
    {"id":22, "pid":17, "logo":"logo/10.jpg", "name":"杨亮哲"},
    {"id":23, "pid":18, "logo":"logo/3.jpg", "name":"张三"},
    {"id":24, "pid":1, "logo":"logo/3.jpg", "name":"马苏拉"},
    {"id":25, "pid":1, "logo":"logo/3.jpg", "name":"杨蓉倚"},
    {"id":26, "pid":1, "logo":"logo/3.jpg", "name":"张三"},
    {"id":27, "pid":1, "logo":"logo/3.jpg", "name":"杨税当"},
    {"id":28, "pid":1, "logo":"logo/3.jpg", "name":"张三"}
];


$(function(){

    window.Demo = new OrgchartDemo();
    
    var adapter = {
        "id": "id",
        "pid": "pid",
        "logo": "logo",
        "name": "name"
    };
    var DemoOption = {
        "adapter": adapter,
        "htmlContent" : Demo.htmlContent,
        "onCreateAllTreeCallback" : Demo.onCreateAllTreeCallback,
        "addEventToNode" : Demo.addEventToNode,
        "createHtmlContent" : Demo.createHtmlContent        
    };

    window.CO = new CreateOrgchartBS($.extend({
        "orgType":"",
        "data":data,        
        "wrap":$("#box_org_tree")
    }, DemoOption));
    window.CO.init();

    map();
});

</script>

</body>
</html>
