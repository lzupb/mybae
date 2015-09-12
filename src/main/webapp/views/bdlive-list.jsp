<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/core" prefix="c"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <title>直播列表</title>
        <link rel="stylesheet" href="${ctx}/static/css/flexigrid/flexigrid.pack.css" type="text/css" />        
        <script src="${ctx}/static/js/jquery-1.10.2.js" type="text/javascript"></script>
        <script src="${ctx}/static/js/flexigrid.pack.js" type="text/javascript"></script>
        
    </head>
    <body>     

        <h2>直播列表</h2>   
        <table class="flexme4" style="display: none"></table>   
        <script type="text/javascript">
        $(".flexme4").flexigrid({
            //url : 'example4.php',
            url : '${ctx}/liveList',
            dataType : 'json',
            method : 'GET',
            colModel : [ {
                display : 'roomID',
                name : 'roomID',
                width : 90,
                sortable : true,
                align : 'center'
                }, {
                    display : 'Name',
                    name : 'name',
                    width : 120,
                    sortable : true,
                    align : 'left'
                }, {
                    display : 'mediaKey',
                    name : 'mediaKey',
                    width : 120,
                    sortable : true,
                    align : 'left'
                }, {
                    display : '开始时间',
                    name : 'startTime',
                    width : 80,
                    sortable : true,
                    align : 'left',
                    hide : true
                }, {
                    display : '结束时间',
                    name : 'endTime',
                    width : 80,
                    sortable : true,
                    align : 'right'
            } ],
            buttons : [ {
                name : 'Add',
                bclass : 'add',
                onpress : Example4
                }
                ,
                {
                    name : 'Edit',
                    bclass : 'edit',
                    onpress : Example4
                }
                ,
                {
                    name : 'Delete',
                    bclass : 'delete',
                    onpress : Example4
                }
                ,
                {
                    separator : true
                } 
            ],
            searchitems : [ {
                display : 'roomID',
                name : 'id'
                }, {
                    display : 'Name',
                    name : 'name',
                    isdefault : true
            } ],
            sortname : "iso",
            sortorder : "asc",
            usepager : true,
            title : 'live-list',
            useRp : true,
            rp : 15,
            showTableToggleBtn : true,
            width : 750,
            height : 200
        });

        function Example4(com, grid) {
            if (com == 'Delete') {
                var conf = confirm('Delete ' + $('.trSelected', grid).length + ' items?')
                if(conf){
                    $.each($('.trSelected', grid),
                        function(key, value){
                            $.get('example4.php', { Delete: value.firstChild.innerText}
                                , function(){
                                    // when ajax returns (callback), update the grid to refresh the data
                                    $(".flexme4").flexReload();
                            });
                    });    
                }
            }
            else if (com == 'Edit') {
                var conf = confirm('Edit ' + $('.trSelected', grid).length + ' items?')
                if(conf){
                    $.each($('.trSelected', grid),
                        function(key, value){
                            // collect the data
                            var OrgEmpID = value.children[0].innerText; // in case we're changing the key
                            var EmpID = prompt("Please enter the New Employee ID",value.children[0].innerText);
                            var Name = prompt("Please enter the Employee Name",value.children[1].innerText);
                            var PrimaryLanguage = prompt("Please enter the Employee's Primary Language",value.children[2].innerText);
                            var FavoriteColor = prompt("Please enter the Employee's Favorite Color",value.children[3].innerText);
                            var FavoriteAnimal = prompt("Please enter the Employee's Favorite Animal",value.children[4].innerText);

                            // call the ajax to save the data to the session
                            $.get('example4.php', 
                                { Edit: true
                                    , OrgEmpID: OrgEmpID
                                    , EmpID: EmpID
                                    , Name: Name
                                    , PrimaryLanguage: PrimaryLanguage
                                    , FavoriteColor: FavoriteColor
                                    , FavoritePet: FavoriteAnimal  }
                                , function(){
                                    // when ajax returns (callback), update the grid to refresh the data
                                    $(".flexme4").flexReload();
                            });
                    });    
                }
            }
            else if (com == 'Add') {
                // collect the data
                var EmpID = prompt("Please enter the Employee ID","5");
                var Name = prompt("Please enter the Employee Name","Mark");
                var PrimaryLanguage = prompt("Please enter the Employee's Primary Language","php");
                var FavoriteColor = prompt("Please enter the Employee's Favorite Color","Tan");
                var FavoriteAnimal = prompt("Please enter the Employee's Favorite Animal","Dog");

                // call the ajax to save the data to the session
                $.get('example4.php', { Add: true, EmpID: EmpID, Name: Name, PrimaryLanguage: PrimaryLanguage, FavoriteColor: FavoriteColor, FavoritePet: FavoriteAnimal  }
                    , function(){
                        // when ajax returns (callback), update the grid to refresh the data
                        $(".flexme4").flexReload();
                });
            }
        }       
        </script>
    </body>
</html>
