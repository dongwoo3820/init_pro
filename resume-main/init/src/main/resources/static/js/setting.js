$(document).ready(function() {

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) { xhr.setRequestHeader(header, token); });




        $(".client").show();
        $(".institute").hide();
        $(".spot").hide();
        $(".occupation").hide();
        $(".task").hide();
        $(".area").hide();
        $(".qualifi").hide();
        $("#option-save-btn").removeAttr("onclick");
        $("#option-save-btn").attr("onclick", "clientAdd()");
        $("#option-update-btn").removeAttr("onclick");
        $("#option-update-btn").attr("onclick", "clientUpdate()");

        
        $(".nav-item1").click(function() {
            idx = $(this).index();

            // 근무경력
            if(idx == '0'){
                $(".client").show();
                $(".institute").hide();
                $(".spot").hide();
                $(".occupation").hide();
                $(".task").hide();
                $(".area").hide();
                $(".qualifi").hide();
                $("#option-save-btn").removeAttr("onclick");
                $("#option-save-btn").attr("onclick", "clientAdd()");
                $("#option-update-btn").removeAttr("onclick");
                $("#option-update-btn").attr("onclick", "clientUpdate()");
            }
            else if(idx == '1'){
                $(".client").hide();
                $(".institute").show();
                $(".spot").hide();
                $(".occupation").hide();
                $(".task").hide();
                $(".area").hide();
                $(".qualifi").hide();
                $("#option-save-btn").removeAttr("onclick");
                $("#option-save-btn").attr("onclick", "instituteAdd()");
                $("#option-update-btn").removeAttr("onclick");
                $("#option-update-btn").attr("onclick", "instituteUpdate()");
            }
            else if(idx == '2'){
                $(".client").hide();
                $(".institute").hide();
                $(".spot").show();
                $(".occupation").hide();
                $(".task").hide();
                $(".area").hide();
                $(".qualifi").hide();
                $("#option-save-btn").removeAttr("onclick");
                $("#option-save-btn").attr("onclick", "spotAdd()");
                $("#option-update-btn").removeAttr("onclick");
                $("#option-update-btn").attr("onclick", "spotUpdate()");
            }
            else if(idx == '3'){
                $(".client").hide();
                $(".institute").hide();
                $(".spot").hide();
                $(".occupation").show();
                $(".task").hide();
                $(".area").hide();
                $(".qualifi").hide();
                $("#option-save-btn").removeAttr("onclick");
                $("#option-save-btn").attr("onclick", "occupationAdd()");
                $("#option-update-btn").removeAttr("onclick");
                $("#option-update-btn").attr("onclick", "occupationUpdate()");
            }
            else if(idx == '4'){
                $(".client").hide();
                $(".institute").hide();
                $(".spot").hide();
                $(".occupation").hide();
                $(".task").show();
                $(".area").hide();
                $(".qualifi").hide();
                $("#option-save-btn").removeAttr("onclick");
                $("#option-save-btn").attr("onclick", "taskAdd()");
                $("#option-update-btn").removeAttr("onclick");
                $("#option-update-btn").attr("onclick", "taskUpdate()");
            }
            else if(idx == '5'){
                $(".client").hide();
                $(".institute").hide();
                $(".spot").hide();
                $(".occupation").hide();
                $(".task").hide();
                $(".area").show();
                $(".qualifi").hide();
                $("#option-save-btn").removeAttr("onclick");
                $("#option-save-btn").attr("onclick", "areaAdd()");
                $("#option-update-btn").removeAttr("onclick");
                $("#option-update-btn").attr("onclick", "areaUpdate()");
            }
            else if(idx == '6'){
                $(".client").hide();
                $(".institute").hide();
                $(".spot").hide();
                $(".occupation").hide();
                $(".task").hide();
                $(".area").hide();
                $(".qualifi").show();
                $("#option-save-btn").removeAttr("onclick");
                $("#option-save-btn").attr("onclick", "qualifiAdd()");
                $("#option-update-btn").removeAttr("onclick");
                $("#option-update-btn").attr("onclick", "qualifiUpdate()");
            }
        });
    var rowId;
    var data;
    var seldata;

    //고객사
    jQuery("#clientList").jqGrid({
        url:'ClientList',
        datatype: 'json',
        mtype:'POST',
        editurl: 'ClientEdit',
        colNames:['','MC','이름'],
        colModel:[
            { name: 'myac', width: 50, fixed:true, sortable : false, formatter:'actions', align:"center",
                formatoptions:{ editbutton : false, delbutton:true}},

            {name:'client_mc', index:'client_mc',  width:50,align:"center",
                editrules:{  readonly:true,edithidden:true },editable:true,
                editoptions:{ readonly:true,size:"50",maxlength:"50"}},

            {name:'name', index:'name',  width:100,align:"center",
                editrules:{  required:true,edithidden:true },editable:true,
                editoptions:{size:"50",maxlength:"50"}}
                ],

                pager: jQuery('#clientListPager'),
                pagination:true,
                rowNum:10,
                rowList:[10,20,30],
                sortname: 'client_mc',
                viewrecords: true,
                sortorder: "desc",
                caption: "고객사",
                width : 900,
                height :'auto',
        
                jsonReader: {
                    root: "rows",
                    page: "page",
                    total: "total",
                    records: "records",
                    repeatitems: false,
                    cell: "cell",
                    id: "client_mc"
                },
                ondblClickRow: function(){
                    rowId = $('#clientList').jqGrid('getGridParam', 'selrow');
                    var client_mc = $('#clientList').jqGrid('getRowData',rowId).client_mc;
                    var name = $('#clientList').jqGrid('getRowData',rowId).name;
                    $("#update_client_name").val(name);
                    $("#update_client_mc").val(client_mc);
                    $("#updateSetting").appendTo("body").modal('show');
                },
                onSelectRow: function() {
                    
                },
                loadComplete : function(xhr) {

                },
                gridComplete: function() {

                },
                loadError: function(xhr,status,error) {
                alert(error);
                }
                });

                jQuery("#clientList") .jqGrid({
                    pager : '#clientListPager',
                    recordtext: "View {0} - {1} of {2}",
                    emptyrecords: "No records to view",
                    loadtext: "Loading...",
                    pgtext : "Page {0} of {1}"
                });  
        

    //직무
    jQuery("#taskList").jqGrid({
        url:'TaskList',
        datatype: 'json',
        mtype:'POST',
        editurl: "TaskEdit",
        colNames:['','nm','이름', '상위코드'],
        colModel:[
            { name: 'myac', width: 50, fixed:true, sortable : false, formatter:'actions', align:"center",
                formatoptions:{ editbutton : false, delbutton:true}},

            {name:'task_nm', index:'task_nm',  width:50,align:"center",
                editrules:{  readonly:true,edithidden:true },editable:true,
                editoptions:{ readonly:true,size:"50",maxlength:"50"}},

            {name:'name', index:'name',  width:100,align:"center",
                editrules:{  required:true,edithidden:true },editable:true,
                editoptions:{size:"50",maxlength:"50"}},

            {name:'occupation', index:'occupation',  width:100,align:"center", hidden: true,
                editrules:{  required:true,edithidden:true },editable:true,
                editoptions:{size:"50",maxlength:"50"}}
                ],

                pager: jQuery('#taskListPager'),
                pagination:true,
                rowNum:10,
                rowList:[10,20,30],
                sortname: 'task_nm',
                viewrecords: true,
                sortorder: "desc",
                caption: "업무",
                width : 900,
                height :'auto',
        
                jsonReader: {
                    root: "rows",
                    page: "page",
                    total: "total",
                    records: "records",
                    repeatitems: false,
                    cell: "cell",
                    id: "task_nm"
                },
                ondblClickRow: function(){
                    rowId = $('#taskList').jqGrid('getGridParam', 'selrow');
                    var occupation = $('#taskList').jqGrid('getRowData',rowId).occupation;
                    var task_mc = $('#taskList').jqGrid('getRowData',rowId).task_mc;
                    var name = $('#taskList').jqGrid('getRowData',rowId).name;
                    console.log(upper_task_mc);
                    $("#update_occupation option").filter(function(){return this.value == occupation;}).attr('selected',true);
                    $("#update_task_name").val(name);
                    $("#update_task_nm").val(task_mc);
                    $("#updateSetting").appendTo("body").modal('show');
                },
                onSelectRow: function() {
                    
                },
                loadComplete : function(xhr) {

                },
                gridComplete: function() {

                },
                loadError: function(xhr,status,error) {
                alert(error);
                }
                });

                jQuery("#taskList") .jqGrid({
                    pager : '#taskListPager',
                    recordtext: "View {0} - {1} of {2}",
                    emptyrecords: "No records to view",
                    loadtext: "Loading...",
                    pgtext : "Page {0} of {1}"
                });  
        
    

    //학원
    jQuery("#instituteList").jqGrid({
        url:'InstituteList',
        datatype: 'json',
        mtype:'POST',
        editurl: 'InstituteEdit',
        colNames:['','ID','이름'],
        colModel:[
            { name: 'myac', width: 50, fixed:true, sortable : false, formatter:'actions', align:"center",
                formatoptions:{ editbutton : false, delbutton:true}},

            {name:'institute_mc', index:'institute_mc',  width:50,align:"center",
                editrules:{  readonly:true,edithidden:true },editable:true,
                editoptions:{ readonly:true,size:"50",maxlength:"50"}},

            {name:'name', index:'name',  width:100,align:"center",
                editrules:{  required:true,edithidden:true },editable:true,
                editoptions:{size:"50",maxlength:"50"}}
            ],

                pager: jQuery('#instituteListPager'),
                pagination:true,
                rowNum:10,
                rowList:[10,20,30],
                sortname: 'institute_mc',
                viewrecords: true,
                sortorder: "desc",
                caption: "학원",
                width : 900,
                height :'auto',
        
                jsonReader: {
                    root: "rows",
                    page: "page",
                    total: "total",
                    records: "records",
                    repeatitems: false,
                    cell: "cell",
                    id: "institute_mc"
                },
                ondblClickRow: function(){
                    rowId = $('#instituteList').jqGrid('getGridParam', 'selrow');
                    var institute_mc = $('#instituteList').jqGrid('getRowData',rowId).institute_mc;
                    var name = $('#instituteList').jqGrid('getRowData',rowId).name;
                    $("#update_institute_name").val(name);
                    $("#update_institute_mc").val(institute_mc);
                    $("#updateSetting").appendTo("body").modal('show');
                },
                onSelectRow: function() {

                },
                loadComplete : function(xhr) {

                },
                gridComplete: function() {

                },
                loadError: function(xhr,status,error) {
                alert(error);
                }
                });

                jQuery("#instituteList") .jqGrid({
                    pager : '#instituteListPager',
                    recordtext: "View {0} - {1} of {2}",
                    emptyrecords: "No records to view",
                    loadtext: "Loading...",
                    pgtext : "Page {0} of {1}"
                });  

    //직종
    jQuery("#occupationList").jqGrid({
        url:'OccupationList',
        datatype: 'json',
        mtype:'POST',
        editurl: "OccupationEdit",
        colNames:['','nm','이름'],
        colModel:[
            { name: 'myac', width: 50, fixed:true, sortable : false, formatter:'actions', align:"center",
                formatoptions:{ editbutton : false, delbutton:true}},

            {name:'occupation_nm', index:'occupation_nm',  width:50,align:"center",
                editrules:{  readonly:true,edithidden:true },editable:true,
                editoptions:{ readonly:true,size:"50",maxlength:"50"}},

            {name:'name', index:'name',  width:100,align:"center",
                editrules:{  required:true,edithidden:true },editable:true,
                editoptions:{size:"50",maxlength:"50"}}
                ],

                pager: jQuery('#occupationListPager'),
                pagination:true,
                rowNum:10,
                rowList:[10,20,30],
                sortname: 'occupation_nm',
                viewrecords: true,
                sortorder: "desc",
                caption: "업무",
                width : 900,
                height :'auto',
        
                jsonReader: {
                    root: "rows",
                    page: "page",
                    total: "total",
                    records: "records",
                    repeatitems: false,
                    cell: "cell",
                    id: "occupation_nm"
                },
                ondblClickRow: function(){
                    rowId = $('#occupationList').jqGrid('getGridParam', 'selrow');
                    var occupation_nm = $('#occupationList').jqGrid('getRowData',rowId).occupation_nm;
                    var name = $('#occupationList').jqGrid('getRowData',rowId).name;
                    $("#update_occupation_name").val(name);
                    $("#update_occupation_nm").val(occupation_nm);
                    $("#updateSetting").appendTo("body").modal('show');
                },
                onSelectRow: function() {
                    
                },
                loadComplete : function(xhr) {

                },
                gridComplete: function() {

                },
                loadError: function(xhr,status,error) {
                alert(error);
                }
                });

                jQuery("#occupationList") .jqGrid({
                    pager : '#occupationListPager',
                    recordtext: "View {0} - {1} of {2}",
                    emptyrecords: "No records to view",
                    loadtext: "Loading...",
                    pgtext : "Page {0} of {1}"
                });  


    //직위
    jQuery("#spotList").jqGrid({
        url:'SpotList',
        datatype: 'json',
        mtype:'POST',
        editurl: 'SpotEdit',
        colNames:['','ID','이름'],
        colModel:[
            { name: 'myac', width: 50, fixed:true, sortable : false, formatter:'actions', align:"center",
                formatoptions:{ editbutton : false, delbutton:true}},

            {name:'spot_mc', index:'spot_mc',  width:50,align:"center",
                editrules:{  readonly:true,edithidden:true },editable:true,
                editoptions:{ readonly:true,size:"50",maxlength:"50"}},

            {name:'name', index:'name',  width:100,align:"center",
                editrules:{  required:true,edithidden:true },editable:true,
                editoptions:{size:"50",maxlength:"50"}}
            ],

                pager: jQuery('#spotListPager'),
                pagination:true,
                rowNum:10,
                rowList:[10,20,30],
                sortname: 'spot_mc',
                viewrecords: true,
                sortorder: "desc",
                caption: "직무",
                width : 900,
                height :'auto',
        
                jsonReader: {
                    root: "rows",
                    page: "page",
                    total: "total",
                    records: "records",
                    repeatitems: false,
                    cell: "cell",
                    id: "spot_mc"
                },
                ondblClickRow: function(){
                    rowId = $('#spotList').jqGrid('getGridParam', 'selrow');
                    var spot_mc = $('#spotList').jqGrid('getRowData',rowId).spot_mc;
                    var name = $('#spotList').jqGrid('getRowData',rowId).name;
                    $("#update_spot_name").val(name);
                    $("#update_spot_mc").val(spot_mc);
                    $("#updateSetting").appendTo("body").modal('show');
                },
                onSelectRow: function() {

                },
                loadComplete : function(xhr) {

                },
                gridComplete: function() {

                },
                loadError: function(xhr,status,error) {
                alert(error);
                }
                });

                jQuery("#spotList") .jqGrid({
                    pager : '#spotListPager',
                    recordtext: "View {0} - {1} of {2}",
                    emptyrecords: "No records to view",
                    loadtext: "Loading...",
                    pgtext : "Page {0} of {1}"
                });


    //지역
    jQuery("#areaList").jqGrid({
        url:'AreaList',
        datatype: 'json',
        mtype:'POST',
        editurl: 'AreaEdit',
        colNames:['','ID','이름'],
        colModel:[
            { name: 'myac', width: 50, fixed:true, sortable : false, formatter:'actions', align:"center",
                formatoptions:{ editbutton : false, delbutton:true}},

            {name:'id', index:'id',  width:50,align:"center",
                editrules:{  readonly:true,edithidden:true },editable:true,
                editoptions:{ readonly:true,size:"50",maxlength:"50"}},

            {name:'name', index:'name',  width:100,align:"center",
                editrules:{  required:true,edithidden:true },editable:true,
                editoptions:{size:"50",maxlength:"50"}}
            ],

                pager: jQuery('#areaListPager'),
                pagination:true,
                rowNum:10,
                rowList:[10,20,30],
                sortname: 'id',
                viewrecords: true,
                sortorder: "desc",
                caption: "지역",
                width : 900,
                height :'auto',
        
                jsonReader: {
                    root: "rows",
                    page: "page",
                    total: "total",
                    records: "records",
                    repeatitems: false,
                    cell: "cell",
                    id: "id"
                },
                ondblClickRow: function(){
                    rowId = $('#areaList').jqGrid('getGridParam', 'selrow');
                    var id = $('#areaList').jqGrid('getRowData',rowId).id;
                    var name = $('#areaList').jqGrid('getRowData',rowId).name;
                    $("#update_area_name").val(name);
                    $("#update_id").val(id);
                    $("#updateSetting").appendTo("body").modal('show');
                },
                onSelectRow: function() {

                },
                loadComplete : function(xhr) {

                },
                gridComplete: function() {

                },
                loadError: function(xhr,status,error) {
                alert(error);
                }
                });

                jQuery("#areaList") .jqGrid({
                    pager : '#arealistpager',
                    recordtext: "View {0} - {1} of {2}",
                    emptyrecords: "No records to view",
                    loadtext: "Loading...",
                    pgtext : "Page {0} of {1}"
                });
        
    //자격증
    jQuery("#qualifiList").jqGrid({
        url:'qualificatonCodeList',
        datatype: 'json',
        mtype:'POST',
        editurl: 'qualificatonCodeEdit',
        colNames:['','ID','이름','발급기관'],
        colModel:[
            { name: 'myac', width: 50, fixed:true, sortable : false, formatter:'actions', align:"center",
                formatoptions:{ editbutton : false, delbutton:true}},

            {name:'qualifi_mc', index:'qualifi_mc',  width:50,align:"center",
                editrules:{  readonly:true,edithidden:true },editable:true,
                editoptions:{ readonly:true,size:"50",maxlength:"50"}},

            {name:'qualifi_name', index:'qualifi_name',  width:100,align:"center",
                editrules:{  required:true,edithidden:true },editable:true,
                editoptions:{size:"50",maxlength:"50"}},

            {name:'issuer', index:'issuer',  width:100,align:"center",
                editrules:{  required:true,edithidden:true },editable:true,
                editoptions:{size:"50",maxlength:"50"}}
            ],

                pager: jQuery('#qualifiListPager'),
                pagination:true,
                rowNum:10,
                rowList:[10,20,30],
                sortname: 'qualifi_mc',
                viewrecords: true,
                sortorder: "desc",
                caption: "지역",
                width : 900,
                height :'auto',
        
                jsonReader: {
                    root: "rows",
                    page: "page",
                    total: "total",
                    records: "records",
                    repeatitems: false,
                    cell: "cell",
                    id: "qualifi_mc"
                },
                ondblClickRow: function(){
                    rowId = $('#qualifiList').jqGrid('getGridParam', 'selrow');
                    var qualifi_mc = $('#qualifiList').jqGrid('getRowData',rowId).qualifi_mc;
                    var qualifi_name = $('#qualifiList').jqGrid('getRowData',rowId).qualifi_name;
                    var issuer = $('#qualifiList').jqGrid('getRowData',rowId).issuer;
                    $("#update_qualifi_name").val(qualifi_name);
                    $("#update_qualifi_mc").val(qualifi_mc);
                    $("#update_qualifi_issuer").val(issuer);
                    $("#updateSetting").appendTo("body").modal('show');
                },
                onSelectRow: function() {

                },
                loadComplete : function(xhr) {

                },
                gridComplete: function() {

                },
                loadError: function(xhr,status,error) {
                alert(error);
                }
                });

                jQuery("#qualifiList") .jqGrid({
                    pager : '#qualifiListPager',
                    recordtext: "View {0} - {1} of {2}",
                    emptyrecords: "No records to view",
                    loadtext: "Loading...",
                    pgtext : "Page {0} of {1}"
                });
});
