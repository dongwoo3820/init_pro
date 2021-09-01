$(document).ready(function() {
    

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) { xhr.setRequestHeader(header, token); });

    if($('#detailModal').css('display') === 'none'){
        $("#userList").trigger("reloadGrid");
        $("#pdf-btn").show();
        $("#update-btn").show();
        $("#detail-add-btn").hide();
    }

    //상세페이지 내에서 추가버튼 클릭시 중복 생성 방지
    $("#detail-add-btn").off('click').on('click',function(){
        $("#addDetials").appendTo("body").modal('show');
    });

    $(document).on("keyup", "#phone", function() { 
        $(this).val( $(this).val().replace(/[^0-9]/g, "").replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/,"$1-$2-$3").replace("--", "-") ); 
    });
    

    //버튼 클릭시 검색창 변경
    $(".nav-item").click(function() {
        var idx = $(this).index();
        if(idx == '0'){
            $("#pdf-btn").show();
            $("#update-btn").show();
            $("#detail-add-btn").hide();
        }else{
            $("#pdf-btn").hide();
            $("#update-btn").hide();
            $("#detail-add-btn").show();
        }

        // 근무경력
        if(idx == '1'){
            // var link_id =$('#info_id').val();
            // $("#workList").jqGrid('setGridParam',{
            //     url:'/detail/'+ link_id +'/WorkList',
            //     datatype: "json"
            // });
            $("#workList").trigger("reloadGrid");
            $(".work").show();
            $(".career").hide();
            $(".education").hide();
            $(".edumatter").hide();
            $(".qualifications").hide();
            $(".prize").hide();
            $("#detail-save-btn").removeAttr("onclick");
            $("#detail-save-btn").attr("onclick", "workAdd()");
            $("#detail-update-btn").removeAttr("onclick");
            $("#detail-update-btn").attr("onclick", "workUpdate()");
        }
        // 기술경력
        else if(idx == '2'){
            // var link_id =$('#info_id').val();
            // $("#careerList").jqGrid('setGridParam',{
            //     url:'/detail/'+ link_id +'/CareerList',
            //     datatype: "json"
            // });
            $("#careerList").trigger("reloadGrid");
            $(".work").hide();
            $(".career").show();
            $(".education").hide();
            $(".edumatter").hide();
            $(".qualifications").hide();
            $(".prize").hide();
            $("#detail-save-btn").removeAttr("onclick");
            $("#detail-save-btn").attr("onclick", "careerAdd();");
            $("#detail-update-btn").removeAttr("onclick");
            $("#detail-update-btn").attr("onclick", "careerUpdate()");
        }
        // 학력사항
        else if(idx == '3'){
            // var link_id =$('#info_id').val();
            // $("#educationList").jqGrid('setGridParam',{
            //     url:'/detail/'+ link_id +'/EducationList',
            //     datatype: "json"
            // });
            $("#educationList").trigger("reloadGrid");
            $(".work").hide();
            $(".career").hide();
            $(".education").show();
            $(".edumatter").hide();
            $(".qualifications").hide();
            $(".prize").hide();
            $("#detail-save-btn").removeAttr("onclick");
            $("#detail-save-btn").attr("onclick", "educationAdd ();");
            $("#detail-update-btn").removeAttr("onclick");
            $("#detail-update-btn").attr("onclick", "educationUpdate()");
        }
        //교육사항
        else if(idx == '4'){
            // var link_id =$('#info_id').val();
            // $("#edumatterList").jqGrid('setGridParam',{
            //     url:'/detail/'+ link_id +'/EduMatterList',
            //     datatype: "json"
            // });
            $("#edumatterList").trigger("reloadGrid");
            $(".work").hide();
            $(".career").hide();
            $(".education").hide();
            $(".edumatter").show();
            $(".qualifications").hide();
            $(".prize").hide();
            $("#detail-save-btn").removeAttr("onclick");
            $("#detail-save-btn").attr("onclick", "edumatterAdd();");
            $("#detail-update-btn").removeAttr("onclick");
            $("#detail-update-btn").attr("onclick", "edumatterUpdate()");
        }
        //자격증
        else if(idx == '5'){
            // var link_id =$('#info_id').val();
            // $("#qualificationsList").jqGrid('setGridParam',{
            //     url:'/detail/'+ link_id +'/QualificationsList',
            //     datatype: "json"
            // });
            $("#qualificationsList").trigger("reloadGrid");
            $(".work").hide();
            $(".career").hide();
            $(".education").hide();
            $(".edumatter").hide();
            $(".qualifications").show();
            $(".prize").hide();
            $("#detail-save-btn").removeAttr("onclick");
            $("#detail-save-btn").attr("onclick", "qualificationsAdd();");
            $("#detail-update-btn").removeAttr("onclick");
            $("#detail-update-btn").attr("onclick", "qualificationsUpdate()");
        }
        //상훈
        else if(idx == '6'){
            // var link_id =$('#info_id').val();
            // $("#prizeList").jqGrid('setGridParam',{
            //     url:'/detail/'+ link_id +'/PrizeList',
            //     datatype: "json"
            // });
            $("#prizeList").trigger("reloadGrid");
            $(".work").hide();
            $(".career").hide();
            $(".education").hide();
            $(".edumatter").hide();
            $(".qualifications").hide();
            $(".prize").show();
            $("#detail-save-btn").removeAttr("onclick");
            $("#detail-save-btn").attr("onclick", "prizeAdd();");
            $("#detail-update-btn").removeAttr("onclick");
            $("#detail-update-btn").attr("onclick", "prizeUpdate()");
        }

    })

    // 기술경력 수정
    

    
});

//기술경력
$(document).ready(function() {

    var link_id =$('#info_id').val();


    //jqGrid 부분
    $("#careerList").jqGrid({
        url:'/detail/'+link_id+'/CareerList',
        datatype: 'json',
        mtype:'POST',
        editurl: '/detail/CareerEdit',
        colNames:['','','ID','사업명','사업기간','고객사','업무','상세업무'],
        colModel:[
                { name: 'myac', width: 30, fixed:true, sortable : false, formatter:'actions',align:"center", 
                formatoptions:{ editbutton : false, delbutton:true}},

                {name:'id',  index:'id', sortable : false, width:20, align:"center", hidden: true,
                editrules:{  readonly:true, edithidden:true },editable:true,
                editoptions:{ readonly:true,size:"50",maxlength:"50"}},

                {name:'info_id', index:'info_id', sortable : false, width:20, align:"center", hidden: true,
                editrules:{  readonly:true,edithidden:true },editable:true,
                editoptions:{ readonly:true,size:"50",maxlength:"50",defaultValue:link_id}},

                {name:'project_name', index:'project_name', sortable : false, width:100, align:"center",
                    editrules:{  required:true,edithidden:true },editable:true,
                    editoptions:{size:"50",maxlength:"50"}},

                {name:'date', index:'date', sortable : false, width:110, align:"center",
                    editrules:{  required:true,edithidden:true },editable:true,
                    editoptions:{size:"50",maxlength:"50"}},


                {name:'client_name', index:'client_name', sortable : false, width:100, align:"center",
                    editrules:{  required:true,edithidden:true },editable:true,
                    editoptions:{size:"50",maxlength:"50"}},

                {name:'task', index:'task', sortable : false, width:80, align:"center",
                    editrules:{  required:true,edithidden:true },editable:true,
                    editoptions:{size:"50",maxlength:"50"}},

                {name:'task_detail', index:'task_detail', sortable : false, width:150, align:"center",
                    editrules:{  required:true,edithidden:true },editable:true,
                    editoptions:{size:"50",maxlength:"50"}}
                ],

                pager: $('#careerListPager'),
                pagination:true,
                sortname: 'id',
                viewrecords: true,
                sortorder: "desc",
                caption: "기술경력",
                width :"900",
                height :'221',
                shrinkToFit : false,
                scroll : true,
        
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
                    var rowId = $('#careerList').jqGrid('getGridParam', 'selrow');
                    var id = $('#careerList').jqGrid('getRowData',rowId).id;
                    var project_name = $('#careerList').jqGrid('getRowData',rowId).project_name;
                    var date = $('#careerList').jqGrid('getRowData',rowId).date.split('~');
                    var client_name = $('#careerList').jqGrid('getRowData',rowId).client_name;
                    var task = $('#careerList').jqGrid('getRowData',rowId).task;
                    var task_detail = $('#careerList').jqGrid('getRowData',rowId).task_detail;
                    $("#update_career_id").val(id);
                    $("#update_career_Project_name").val(project_name);
                    $("#update_career_Start_date").val(date[0].trim());
                    $("#update_career_End_date").val(date[1].trim());
                    $("#update_career_Client_name option").filter(function(){return this.text == client_name;}).attr('selected',true);
                    $("#update_career_Task option").filter(function(){return this.text == task;}).attr('selected',true);
                    $("#update_career_Task_detail").val(task_detail);
                    $("#updateDetials").appendTo("body").modal('show');
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

                $("#careerList") .jqGrid({
                    pager : '#careerListPager',
                    recordtext: "View {0} - {1} of {2}",
                    emptyrecords: "No records to view",
                    loadtext: "Loading...",
                    pgtext : "Page {0} of {1}"
                });  

            });
    
    //근무경력
    $(document).ready(function() {

        var link_id =$('#info_id').val();

    //jqGrid 부분
    $("#workList").jqGrid({
        url:'/detail/'+link_id+'/WorkList',
        datatype: 'json',
        mtype:'POST',
        editurl:'/detail/WorkEdit',
        colNames:['','work_id','info_ID','회사명','부서','업무','직위','근무기간'],
        colModel:[
                { name: 'myac', width: 30, fixed:true, sortable : false, formatter:'actions',align:"center", 
                    formatoptions:{ editbutton : false, delbutton:true}},

                {name:'id',  index:'id', sortable : false, width:20, align:"center",  hidden: true,
                    editrules:{  readonly:true,edithidden:true },editable:true,
                    editoptions:{ readonly:true,size:"50",maxlength:"50"}},

                {name:'info_id', index:'info_id', sortable : false, width:20,align:"center",hidden: true,
                    editrules:{  readonly:true,edithidden:true },editable:true,
                    editoptions:{ readonly:true,size:"50",maxlength:"50",defaultValue:link_id}},

                {name:'company_name', index:'company_name', sortable : false, width:100,align:"center",
                    editrules:{  required:true,edithidden:true },editable:true,
                    editoptions:{size:"50",maxlength:"50"}},

                {name:'department', index:'department', sortable : false, width:100,align:"center",
                    editrules:{  required:true,edithidden:true },editable:true,
                    editoptions:{size:"50",maxlength:"50"}},

                {name:'task', index:'task', sortable : false, width:100,align:"center",
                    editrules:{  required:true,edithidden:true },editable:true,
                    editoptions:{size:"50",maxlength:"50"}},

                {name:'spot', index:'spot', sortable : false, width:100,align:"center",
                    editrules:{  required:true,edithidden:true },editable:true,
                    editoptions:{size:"50",maxlength:"50"}},

                {name:'date', index:'date', sortable : false, width:100, align:"center",
                    editrules:{  required:true,edithidden:true },editable:true,
                    editoptions:{size:"50",maxlength:"50"}}
                ],

                pager: $('#workListPager'),
                pagination:true,
                shrinkToFit : false,
                scroll : true,
                sortname: 'id',
                viewrecords: true,
                sortorder: "desc",
                caption: "근무경력",
                width : '900',
                height :'221',
        
                jsonReader: {
                    root: "rows",
                    page: "page",
                    total: "total",
                    records: "records",
                    repeatitems: false,
                    cell: "cell",
                    id: "id"
                },ondblClickRow: function(){
                    var rowId = $('#workList').jqGrid('getGridParam', 'selrow');
                    var id = $('#workList').jqGrid('getRowData',rowId).id;
                    var company_name = $('#workList').jqGrid('getRowData',rowId).company_name;
                    var Department = $('#workList').jqGrid('getRowData',rowId).department;
                    var date = $('#workList').jqGrid('getRowData',rowId).date.split('~');
                    var Spot = $('#workList').jqGrid('getRowData',rowId).spot;
                    var Task = $('#workList').jqGrid('getRowData',rowId).task;
                    $("#update_work_id").val(id);
                    $("#update_work_Company_Name").val(company_name);
                    $("#update_work_Start_date").val(date[0].trim());
                    $("#update_work_End_date").val(date[1].trim());
                    $("#update_work_Department").val(Department);
                    $("#update_work_Spot option").filter(function(){return this.text == Spot;}).attr('selected',true);
                    $("#update_work_Task option").filter(function(){return this.text == Task;}).attr('selected',true);
                    $("#updateDetials").appendTo("body").modal('show');
                },
                onSelectRow: function() {
                    rowId = $('#workList').jqGrid('getGridParam', 'selrow');
                    seldata = $('#workList').jqGrid('getRowData', rowId);
                    data = $('#workList').jqGrid('getRowData',rowId).id;
                    $('#param2').val(data);
                },
                loadComplete : function(xhr) {

                },
                gridComplete: function() {

                },
                loadError: function(xhr,status,error) {
                alert(error);
                }
                });

                $("#workList") .jqGrid({
                    pager : '#workListPager',
                    recordtext: "View {0} - {1} of {2}",
                    emptyrecords: "No records to view",
                    loadtext: "Loading...",
                    pgtext : "Page {0} of {1}"
                });  
            });
            
    //학력
    $(document).ready(function() {

        var link_id = $('#info_id').val();

    //jqGrid 부분
    $("#educationList").jqGrid({
        url:'/detail/'+link_id+'/EducationList',
        datatype: 'json',
        mtype:'POST',
        editurl:'/detail/EducationEdit',
        colNames:['', '','ID','학교명','학과(전공)','학위','수학기간'],
        colModel:[
                { name: 'myac', width: 30, fixed:true, sortable : false, formatter:'actions', align:"center",
                    formatoptions:{ editbutton : false, delbutton:true}},

                {name:'id',  index:'id', sortable : false, width:20, align:"center", hidden: true,
                    editrules:{  required:true,edithidden:true },editable:false,
                    editoptions:{size:"50",maxlength:"50"}},

                {name:'info_id', index:'info_id', sortable : false,  width:20, align:"center",hidden: true,
                editrules:{  readonly:true,edithidden:true },editable:true,
                editoptions:{ readonly:true,size:"50",maxlength:"50",defaultValue: link_id}},

                {name:'name', index:'name', sortable : false, width:100, align:"center",
                    editrules:{  required:true,edithidden:true },editable:true,
                    editoptions:{size:"50",maxlength:"50"}},

                {name:'department', index:'department', sortable : false, width:100, align:"center",
                    editrules:{  required:true,edithidden:true },editable:true,
                    editoptions:{size:"50",maxlength:"50"}},

                {name:'degree', index:'degree', sortable : false, width:100, align:"center",
                    editrules:{  required:true,edithidden:true },editable:true,
                    editoptions:{size:"50",maxlength:"50"}},

                {name:'date', index:'date', sortable : false, width:100,  align:"center",
                    editrules:{  required:true,edithidden:true },editable:true,
                    editoptions:{size:"50",maxlength:"50"}}
                ],

                pager: $('#educationListPager'),
                pagination:true,
                shrinkToFit : false,
                scroll : true,
                sortname: 'id',
                viewrecords: true,
                sortorder: "desc",
                caption: "학력",
                width : 900,
                height :'221',
        
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
                    var rowId = $('#educationList').jqGrid('getGridParam', 'selrow');
                    var id = $('#educationList').jqGrid('getRowData',rowId).id;
                    var Name = $('#educationList').jqGrid('getRowData',rowId).name;
                    var Department = $('#educationList').jqGrid('getRowData',rowId).department;
                    var date = $('#educationList').jqGrid('getRowData',rowId).date.split('~');
                    var degree = $('#educationList').jqGrid('getRowData',rowId).degree;
                    $("#update_edu_id").val(id);
                    $("#update_education_name").val(Name);
                    $("#update_education_start_date").val(date[0].trim());
                    $("#update_education_end_date").val(date[1].trim());
                    $("#update_education_department").val(Department);
                    $("#update_education_degree").val(degree);
                    $("#updateDetials").appendTo("body").modal('show');
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

                $("#educationList") .jqGrid({
                    pager : '#educationListPager',
                    recordtext: "View {0} - {1} of {2}",
                    emptyrecords: "No records to view",
                    loadtext: "Loading...",
                    pgtext : "Page {0} of {1}"
                });  
            });
    
    //교육사항
    $(document).ready(function() {

        var link_id =$('#info_id').val();

    //jqGrid 부분
    $("#edumatterList").jqGrid({
        url:'/detail/'+link_id+'/EduMatterList',
        datatype: 'json',
        mtype:'POST',
        editurl:'/detail/EduMatterEdit',
        colNames:['', '','ID','과정명','교육기관','수료번호','기간'],
        colModel:[
                { name: 'myac', width: 50, fixed:true, sortable : false, formatter:'actions', align:"center",
                    formatoptions:{ editbutton : false, delbutton:true}},

                {name:'id',  index:'id', sortable : false, width:50, align:"center",hidden: true,
                editrules:{  readonly:true,edithidden:true },editable:true,
                editoptions:{ readonly:true,size:"50",maxlength:"50"}},   

                {name:'info_id', index:'info_id', sortable : false, width:50, align:"center",hidden: true,
                editrules:{  readonly:true,edithidden:true },editable:true,
                editoptions:{ readonly:true,size:"50",maxlength:"50", defaultValue:link_id}},

                {name:'course_name', index:'course_name', sortable : false, width:100, align:"center",
                    editrules:{  required:true,edithidden:true },editable:true,
                    editoptions:{size:"50",maxlength:"50"}},

                {name:'institation', index:'institation', sortable : false, width:100, align:"center",
                    editrules:{  required:true,edithidden:true },editable:true,
                    editoptions:{size:"50",maxlength:"50"}},

                {name:'completion_number', index:'completion_number', sortable : false,  width:100, align:"center",
                    editrules:{  required:true,edithidden:true },editable:true,
                    editoptions:{size:"50",maxlength:"50"}},

                {name:'date', index:'date', sortable : false, width:100, align:"center",
                    editrules:{  required:true,edithidden:true },editable:true,
                    editoptions:{size:"50",maxlength:"50"}}
                ],

                pager: $('#edumatterListPager'),
                pagination:true,
                shrinkToFit : false,
                scroll : true,
                sortname: 'id',
                viewrecords: true,
                sortorder: "desc",
                caption: "교육사항",
                width : 900,
                height :'221',
        
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
                    var rowId = $('#edumatterList').jqGrid('getGridParam', 'selrow');
                    var id = $('#edumatterList').jqGrid('getRowData',rowId).id;
                    var Course_Name = $('#edumatterList').jqGrid('getRowData',rowId).course_name;
                    var completion_number = $('#edumatterList').jqGrid('getRowData',rowId).completion_number;
                    var institation = $('#edumatterList').jqGrid('getRowData',rowId).institation;
                    var date = $('#edumatterList').jqGrid('getRowData',rowId).date.split('~');
                    var degree = $('#edumatterList').jqGrid('getRowData',rowId).degree;
                    $("#update_matter_id").val(id);
                    $("#update_edumatter_course_name").val(Course_Name);
                    $("#update_edumatter_completion_number").val(completion_number);
                    $("#update_edumatter_start_date").val(date[0].trim());
                    $("#update_edumatter_end_date").val(date[1].trim());
                    $("#update_edumatter_institaion_mc option").filter(function(){return this.text == institation;}).attr('selected',true);
                    $("#updateDetials").appendTo("body").modal('show');
                },
                onSelectRow: function() {

                },
                loadComplete : function(data) {

                },
                gridComplete: function() {

                },
                loadError: function(xhr,status,error) {
                console.log(error);
                }
                });

                $("#edumatterList") .jqGrid({
                    pager : '#edumatterListPager',
                    recordtext: "View {0} - {1} of {2}",
                    emptyrecords: "No records to view",
                    loadtext: "Loading...",
                    pgtext : "Page {0} of {1}"
                });  
    });


    //자격증
    $(document).ready(function() {

        var link_id =$('#info_id').val();

        //jqGrid 부분
        $("#qualificationsList").jqGrid({
            url:'/detail/'+ link_id +'/QualificationsList',
            datatype: 'json',
            mtype:'POST',
            editurl: '/detail/QualificationsEdit',
            colNames:['', '','ID','자격증명','발급번호','취득일','발급기관'],
            colModel:[
                    { name: 'myac', width: 50, fixed:true, sortable : false, formatter:'actions', align:"center",
                        formatoptions:{ editbutton : false, delbutton:true}},

                    {name:'id',  index:'id', sortable : false,  width:50,   align:"center", hidden: true,
                    editrules:{  readonly:true,edithidden:true },editable:true,
                    editoptions:{ readonly:true,size:"50",maxlength:"50"}},

                    {name:'info_id', index:'info_id', sortable : false,  width:50,align:"center",hidden: true,
                    editrules:{  readonly:true,edithidden:true },editable:true,
                    editoptions:{ readonly:true,size:"50",maxlength:"50", defaultValue: link_id}},

                    {name:'name', index:'name', sortable : false, width:100,align:"center",
                        editrules:{  required:true,edithidden:true },editable:true,
                        editoptions:{size:"50",maxlength:"50"}},

                    {name:'reg_num', index:'reg_num', sortable : false, width:100,align:"center",
                        editrules:{  required:true,edithidden:true },editable:true,
                        editoptions:{size:"50",maxlength:"50"}},

                    {name:'reg_date', index:'reg_date', sortable : false, width:100,align:"center",
                        editrules:{  required:true,edithidden:true },editable:true,
                        editoptions:{size:"50",maxlength:"50"}},

                    {name:'issuer', index:'issuer', sortable : false, width:100,align:"center",
                        editrules:{  required:true,edithidden:true },editable:true,
                        editoptions:{size:"50",maxlength:"50"}}
                    ],

                    pager: $('#qualificationsListPager'),
                    pagination:true,
                    shrinkToFit : false,
                    scroll : true,
                    sortname: 'rnum',
                    viewrecords: true,
                    caption: "자격증",
                    width : '900',
                    height :'221',
            
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
                        var rowId = $('#qualificationsList').jqGrid('getGridParam', 'selrow');
                        var id = $('#qualificationsList').jqGrid('getRowData',rowId).id;
                        var qualname = $('#qualificationsList').jqGrid('getRowData',rowId).name;
                        var reg_num = $('#qualificationsList').jqGrid('getRowData',rowId).reg_num;
                        var reg_date = $('#qualificationsList').jqGrid('getRowData',rowId).reg_date;
                        $("#update_qualifi_ID").val(id);
                        $("#update_qualifi_mc option").filter(function(){return this.text == qualname;}).attr('selected',true);
                        $("#update_qualifi_reg_num").val(reg_num);
                        $("#update_qualfi_reg_date").val(reg_date);
                        $("#updateDetials").appendTo("body").modal('show');
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

                    $("#qualificationsList") .jqGrid({
                        
                        pager : '#qualificationsListPager',
                        recordtext: "View {0} - {1} of {2}",
                        emptyrecords: "No records to view",
                        loadtext: "Loading...",
                        pgtext : "Page {0} of {1}"
                        
                    });  
                });

    //상훈
    $(document).ready(function() {

        var link_id =$('#info_id').val();

    //jqGrid 부분
    $("#prizeList").jqGrid({
        url:'/detail/'+link_id+'/PrizeList',
        datatype: 'json',
        mtype:'POST',
        editurl:'/detail/PrizeEdit',
        colNames:['', 'prize_id','info_id','상훈명','상훈기관','연 월 일', '근거'],
        colModel:[
                { name: 'myac', width: 50, fixed:true, sortable : false, formatter:'actions', align:"center",
                    formatoptions:{ editbutton : false, delbutton:true}},

                {name:'id',  index:'id', sortable : false, width:50,   align:"center",hidden: true,
                editrules:{  readonly:true,edithidden:true },editable:true,
                editoptions:{ readonly:true,size:"50",maxlength:"50"}},

                {name:'info_id', index:'info_id', sortable : false, width:50,align:"center",hidden: true,
                editrules:{  readonly:true,edithidden:true },editable:true,
                editoptions:{ readonly:true,size:"50",maxlength:"50",defaultValue: link_id}},

                {name:'name', index:'name', sortable : false, width:100,align:"center",
                    editrules:{  required:true,edithidden:true },editable:true,
                    editoptions:{size:"50",maxlength:"50"}},
                
                {name:'agency', index:'agency', sortable : false, width:100,align:"center",
                    editrules:{  required:true,edithidden:true },editable:true,
                    editoptions:{size:"50",maxlength:"50"}},

                {name:'reg_date', index:'reg_date', sortable : false, width:100,align:"center",
                    editrules:{  required:true,edithidden:true },editable:true,
                    editoptions:{size:"50",maxlength:"50"}},

                {name:'evidence', index:'evidence', sortable : false, width:100,align:"center",
                    editrules:{  required:true,edithidden:true },editable:true,
                    editoptions:{size:"50",maxlength:"50"}},
                ],

                pager: $('#prizeListPager'),
                pagination:true,
                shrinkToFit : false,
                scroll : true,
                viewrecords: true,
                caption: "상훈",
                width : '900',
                height :'221',
        
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
                    var rowId = $('#prizeList').jqGrid('getGridParam', 'selrow');
                    var id = $('#prizeList').jqGrid('getRowData',rowId).id;
                    var name = $('#prizeList').jqGrid('getRowData',rowId).name;
                    var agency = $('#prizeList').jqGrid('getRowData',rowId).agency;
                    var evidence = $('#prizeList').jqGrid('getRowData',rowId).evidence;
                    var reg_date = $('#prizeList').jqGrid('getRowData',rowId).reg_date;
                    $("#update_prize_id").val(id);
                    $("#update_prize_name").val(name);
                    $("#update_prize_agency").val(agency);
                    $("#update_prize_evidence").val(evidence);
                    $("#update_prize_reg_date").val(reg_date);
                    $("#updateDetials").appendTo("body").modal('show');
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

                $("#prizeList") .jqGrid({
                    pager : '#prizeListPager',
                    recordtext: "View {0} - {1} of {2}",
                    emptyrecords: "No records to view",
                    loadtext: "Loading...",
                    pgtext : "Page {0} of {1}"
                });  
            });