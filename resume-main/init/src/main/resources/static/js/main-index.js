$(document).ready(function() {

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $(document).ajaxSend(function(e, xhr, options) { xhr.setRequestHeader(header, token); });

    if($('#detailModal').css('display') === 'none'){
        $("#userList").trigger("reloadGrid");
    }

    //설정 추가버튼 클릭시 다중 중첩 방지
    $("#option-add-btn").off('click').on('click',function(){
        $("#addSetting").appendTo("body").modal('show');
    });

    $('#optionModal').on('hidden.bs.modal', function () {
      location.reload();
    })
    

    var rowId;
    var data;
    var seldata;

    //jqGrid 부분
    $("#userList").jqGrid({
        url:'userList',
        datatype: 'json',
        editurl:"userEdit",
        mtype:'POST',
        colNames:['','ID','사진','이름','휴대폰','이메일','경력(만나이)','직종/직무','근무가능지역','자격증', '다운로드'],
        colModel:[
                { name: 'myac', width: 50, fixed:true, sortable : false, formatter:'actions', align:"center",
                formatoptions:{ editbutton : false, delbutton:true}},

                {name:'id', index:'id', sortable : false, width:20, hidden: true,
                    editrules:{  readonly:true,edithidden:true },editable:true, align:"center",
                    editoptions:{ readonly:true, size:"50", maxlength:"50", hidden:true}},

                {
                    name: 'imgname', index:'imgname',
                    width: 40,
                    fixed: true,
                    formatter: function (cellvalue, options, rowObject) {
                        if(cellvalue != null){
                        return "<img src='/img/"+cellvalue+"' class='img-fluid info-img' />";
                        }else{
                            return "사진이 없습니다.";
                        }
                    }
                },

                {name:'name', index:'name', sortable : false, width:50, align:"center",
                    editrules:{  required:true,edithidden:true },editable:true,
                    editoptions:{size:"50",maxlength:"50"}},
        
                {name:'phone', index:'phone', sortable : false, width:100, align:"center",
                    editrules:{  required:true,edithidden:true },editable:true,
                    editoptions:{size:"50",maxlength:"50"}},
        
                {name:'email',    index:'email',   sortable : false,  width:150, align:"center",
                    editrules:{  required:true,edithidden:true },editable:true,
                    editoptions:{size:"50",maxlength:"50"}},
        
                {name:'career_age',  index:'career_age', sortable : false,  width:100, align:"center",
                    editrules:{  required:true,edithidden:true },editable:true,
                    editoptions:{size:"50",maxlength:"50"}
                },

                {name:'occupation',  index:'occupation',sortable : false, width:200, align:"center",
                    editrules:{  required:true,edithidden:true },editable:true,
                    editoptions:{size:"50",maxlength:"50"}
                },

                {name:'area',  index:'area',sortable : false, width:200, align:"center",
                    editrules:{  required:true,edithidden:true },editable:true,
                    editoptions:{size:"50",maxlength:"50"}
                },

                {name:'qualifi',  index:'qualifi',sortable : false, width:200, align:"center",
                    editrules:{  required:true,edithidden:true },editable:true,
                    editoptions:{size:"50",maxlength:"50"}
                },

                {name : 'btn', index : 'btn', width : 70, fixed : true, align : 'center', formatter:Btn}

                ],

                pager: $('#userListpager'),
                pagination:true,
                rowNum:10,
                rowuserList:[10,20,30],
                viewrecords: true,
                caption: "유저리스트",
                autowidth : true,
                height : '440',

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
                    data = $('#userList').jqGrid('getRowData',rowId).id;
                    var href = "detail/"+data;
                    var info_name =$('#userList').jqGrid('getRowData',rowId).name;
                    $('#info_id').val(data);
                    $("#detailModalLabel").text(info_name);
                    $("#detailModal").appendTo("body").modal('show');
                    $('#detail').load(href);
                },
                onSelectRow: function() {
                    rowId = $('#userList').jqGrid('getGridParam', 'selrow');
                    seldata = $('#userList').jqGrid('getRowData', rowId);
                    data = $('#userList').jqGrid('getRowData',rowId).id;
                    $('#param').val(data);
                },
                onCellSelect: function(rowid, iCol, cellcontent, e){

                },
                loadComplete : function(xhr) {
                    
                },
                gridComplete: function() {

                },
                loadError: function(xhr,status,error) {
                alert(error);
                }
                });
                

                function Btn(cellvalue, options, rowObject){

                    var str = "<button type='button' class='btn btn-primary' id='pdf-btn"+options.rowId+"' onclick='pdfSave("+options.rowId+")'>PDF 저장</button>";
                    
                    return str;
                }

                $("#userList") .jqGrid({
                    pager : '#userListpager',
                    recordtext: "View {0} - {1} of {2}",
                    emptyrecords: "No records to view",
                    loadtext: "Loading...",
                    pgtext : "Page {0} of {1}"
                });
            

    //jqGrid 가로 길이 반응형
    $(window).on('resize.jqGrid', function() {
        $('#userList').jqGrid('setGridWidth', $(document.body).width());
    });

    $("#add-btn").click(function(){
        $("#addModal").appendTo("body").modal('show');
    });

    let today = new Date();   
    let year = today.getFullYear(); // 년도
    let month = today.getMonth() + 1;  // 월
    let date = today.getDate();  // 날짜

    $('#info_reg_date').val(year + '/' + month + '/' + date);

});


$(document).on("keydown", "#info_phone", function() { 
    $(this).val( $(this).val().replace(/[^0-9]/g, "").replace(/(^02|^0505|^1[0-9]{3}|^0[0-9]{2})([0-9]+)?([0-9]{4})$/,"$1-$2-$3").replace("--", "-") ); 
  });



  function telValidator(args) {
    const msg = '유효하지 않는 전화번호입니다.';
    // IE 브라우저에서는 당연히 var msg로 변경
    
    if (/^[0-9]{2,3}-[0-9]{3,4}-[0-9]{4}/.test(args)) {
        return true;
    }
    // alert(msg);
    return false;
  }
  function emailValidator(args) {
    var msg = '유효하지 않는 이메일입니다.';
    if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(args)) {
        return true;
    }
    // alert(msg);
    return false;
  }

  function Reset(str){
    $("#addDetials."+str+" input").val("");
    $("#addDetials select").val("");
  }

  function SettingReset(str){
    $("#addSetting."+str+" input").val("");
  }

  //검색창
  function searchForm(){

    if($('.searchTable').css('display') == 'none'){
          $('.searchTable').show();
          $('#search').text("검색창 ▲")
      }else{
          $('.searchTable').hide();
          $('#search').text("검색창 ▼")
      }
  }

   //검색
  function searchUser(){
    //이름
    var name = $("input[name=nameSearch]").val();
    //지역
    var area_array = Array();
    var area_cnt = 0;
    var areachkbox = $(".areaCheckBox");

    for(i=0;i<areachkbox.length;i++) {
        if (areachkbox[i].checked == true){
            area_array[area_cnt] = areachkbox[i].value;
            area_cnt++;
        }
    }
    //경력
    var career = $("input[name=careerRadio]:checked").val();
    //자격증
    var qualifi_array = Array();
    var qualifi_cnt = 0;
    var qualifichkbox = $(".qualifiCheckBox");

    for(i=0;i<qualifichkbox.length;i++) {
        if (qualifichkbox[i].checked == true){
            qualifi_array[qualifi_cnt] = qualifichkbox[i].value;
            qualifi_cnt++;
        }
    }
    //고객사
    var client_array = Array();
    var client_cnt = 0;
    var clientchkbox = $(".clientCheckBox");

    for(i=0;i<clientchkbox.length;i++) {
        if (clientchkbox[i].checked == true){
            client_array[client_cnt] = clientchkbox[i].value;
            client_cnt++;
        }
    }
    //업무
    var task_array = Array();
    var task_cnt = 0;
    var taskchkbox = $(".taskCheckBox");

    for(i=0;i<taskchkbox.length;i++) {
        if (taskchkbox[i].checked == true){
            task_array[task_cnt] = taskchkbox[i].value;
            task_cnt++;
        }
    }

    var rows = $("#userList").getGridParam("rowNum");
    var postData  = {'name' : name, 'career' : career, 'area' : area_array.toString(), 'qualifi' : qualifi_array.toString(), 'client_mc' : client_array.toString(), 'task_mc' : task_array.toString(), 'rows' :  rows,'search_yn' : 'Y' }
    console.log(postData);  
    $("#userList").jqGrid("clearGridData", true);
          $("#userList").setGridParam({
            datatype	: "json",
            postData	: postData,
            loadComplete	: function(data) {
            }
          }).trigger("reloadGrid");
      
  } 
  //검색결과 초기화 
  function searchReset(){
    var postData  = {'name' : '', 'career' : '', 'area' : '', 'qualifi' : '', 'client_mc' : '', 'task_mc' : '', 'rows' :  '', 'search_yn' : 'N' }
    $("#userList").setGridParam({
      datatype	: "json",
      postData	: postData,
      loadComplete	: function(data) {
      }
    }).trigger("reloadGrid");
  }
  
  // 유저추가
  function userAdd(){
      var param = $("form[name=addForm]").serialize();

      $.ajax({  
        type: "POST", 
        url: "/userAdd",
        data: param,
        success:function(data){
          $("#addModal").modal("hide");
          $("#userList").trigger("reloadGrid");
        },
        error:function(data){
          alert("error");
        }
    });
  } 

  // 유저 정보 변경
  function userUpdate(){
      var myForm = document.getElementById('UpdateForm');
      var id = $("#info_id").val();
      const formData = new FormData(myForm);
      for (var pair of formData.entries()) { 
        console.log(pair[0]+ ', ' + pair[1]); 
      }
      $.ajax({  
          type: "POST",
          url: "/userUpdate",
          contentType : false,
          processData: false,
          data: formData,
          success:function(data){
            var info_name = $("#name").val();
            $("#detailModalLabel").text(info_name);
            $("#attachImg").val("");
            $("#attachFile").val("");
            alert("수정되었습니다.");
            $.ajax({  
              type: "POST", 
              url: "detail/"+id,
              contentType : false,
              processData: false,
              success:function(data){
                $("#userList").trigger("reloadGrid");
                $('#detail').load("detail/"+id);
              },
              error:function(data){
                alert("error");
              }
            });
          },
          error:function(data){
            alert("error");
          }
      });
  }

  // 근무경력추가
  function workAdd(){
      var workparam = $("form[name=workForm]").serialize();
      var workparams = $("form[name=workForm]").serializeArray();
      console.log(workparams);

      $.ajax({  
        type: "POST" ,
        url: "/detail/WorkAdd",
        data: workparam,
        success:function(data){
          Reset("work");
          $("#addDetials").modal("hide");
          $("#workList").trigger("reloadGrid");
        },
        error:function(data){
          alert("error");
        }
    });
  }
  //기술경력추가 
  function careerAdd(){

      var careerparam = $("form[name=careerForm]").serialize();

      $.ajax({  
        type: "POST", 
        url: "/detail/CareerAdd",
        data: careerparam,
        success:function(data){
          Reset("career");
          $("#addDetials").modal("hide");
          $("#careerList").trigger("reloadGrid");
        },
        error:function(data){
          alert("error");
        }
    });
  }

  // 학력추가
  function educationAdd(){

      var educationparam = $("form[name=educationForm]").serialize();

      $.ajax({  
        type: "POST" ,
        url: "/detail/EducationAdd",
        data: educationparam,
        success:function(data){
          Reset("education");
          $("#addDetials").modal("hide");
          $("#educationList").trigger("reloadGrid");
        },
        error:function(data){
          alert("error");
        }
    });
  }

  // 교육사항추가
  function edumatterAdd(){
      var edumatterparam = $("form[name=edumatterForm]").serialize();

      $.ajax({  
        type: "POST" ,
        url: "/detail/EduMatterAdd",
        data: edumatterparam,
        success:function(data){
          Reset("edumatter");
          $("#addDetials").modal("hide");
          $("#edumatterList").trigger("reloadGrid");
        },
        error:function(data){
          alert("error");
        }
    });
  }

  // 자격증추가
  function qualificationsAdd(){
      var qulifiparam = $("form[name=qualificationsForm]").serialize();

      $.ajax({  
        type: "POST", 
        url: "/detail/QualificationsAdd",
        data: qulifiparam,
        success:function(data){
          Reset("qualifications");
          $("#addDetials").modal("hide");
          $("#qualificationsList").trigger("reloadGrid");
        },
        error:function(data){
          alert("error");
        }
    });
  }

  // 상훈추가
  function prizeAdd(){
      var prizeparam = $("form[name=prizeForm]").serialize();

      $.ajax({  
        type: "POST" ,
        url: "/detail/PrizeAdd",
        data: prizeparam,
        success:function(data){
          Reset("prize");
          $("#addDetials").modal("hide");
          $("#prizeList").trigger("reloadGrid");
        },
        error:function(data){
          alert("error");
        }
    });
  }

  // 고객사추가
  function clientAdd(){
      var clientparam = $("form[name=clientForm]").serialize();

      $.ajax({  
        type: "POST" ,
        url: "/ClientAdd",
        data: clientparam,
        success:function(data){
          SettingReset("client");
          $("#addSetting").modal("hide");
          $("#clientList").trigger("reloadGrid");
        },
        error:function(data){
          alert("error");
        }
    });
  }

  // 직종추가
  function occupationAdd(){
    var occupationparam = $("form[name=occupationForm]").serialize();

    $.ajax({  
      type: "POST" ,
      url: "/OccupationAdd",
      data: occupationparam,
      success:function(data){
        SettingReset("task");
        $("#addSetting").modal("hide");
        $("#occupationList").trigger("reloadGrid");
      },
      error:function(data){
        alert("error");
      }
  });
}

  // 직무추가
  function taskAdd(){
      var taskparam = $("form[name=taskForm]").serialize();

      $.ajax({  
        type: "POST" ,
        url: "/TaskAdd",
        data: taskparam,
        success:function(data){
          SettingReset("task");
          $("#addSetting").modal("hide");
          $("#taskList").trigger("reloadGrid");
        },
        error:function(data){
          alert("error");
        }
    });
  }

  // 학원추가
  function instituteAdd(){
      var instituteparam = $("form[name=instituteForm]").serialize();

      $.ajax({  
        type: "POST" ,
        url: "/InstituteAdd",
        data: instituteparam,
        success:function(data){
          SettingReset("institute");
          $("#addSetting").modal("hide");
          $("#instituteList").trigger("reloadGrid");
        },
        error:function(data){
          alert("error");
        }
    });
  }

  // 직무추가
  function spotAdd(){
      var spotparam = $("form[name=spotForm]").serialize();

      $.ajax({  
        type: "POST" ,
        url: "/SpotAdd",
        data: spotparam,
        success:function(data){
          SettingReset("spot");
          $("#addSetting").modal("hide");
          $("#spotList").trigger("reloadGrid");
        },
        error:function(data){
          alert("error");
        }
    });
  }
  // 지역추가
  function areaAdd(){
    var spotparam = $("form[name=areaForm]").serialize();

    $.ajax({  
      type: "POST" ,
      url: "/AreaAdd",
      data: spotparam,
      success:function(data){
        SettingReset("area");
        $("#addSetting").modal("hide");
        $("#areaList").trigger("reloadGrid");
      },
      error:function(data){
        alert("error");
      }
    });
  }
  // 자격증추가
  function qualifiAdd(){
    var spotparam = $("form[name=qualifiForm]").serialize();

    $.ajax({  
      type: "POST" ,
      url: "/qualificatonCodeAdd",
      data: spotparam,
      success:function(data){
        SettingReset("qualifi");
        $("#addSetting").modal("hide");
        $("#qualifiList").trigger("reloadGrid");
      },
      error:function(data){
        alert("error");
      }
  });
  }


  // 파일 삭제
  function fileDelete(id){
      
      $.ajax({  
          type: "POST" ,
          url: "/fileDelete/"+ id,
          data: id,
          success:function(data){
              alert('삭제되었습니다.');
              $("#file_list"+id).remove();
          },
          error:function(data){
          alert("error");
          }
      });
  }

  // 근무경력 수정
  function workUpdate(){
      var workUpdateForm = $("form[name=workupdateForm]").serialize();

      $.ajax({  
          type: "POST" ,
          url: "/detail/WorkUpdate",
          data: workUpdateForm,
          success:function(data){
            $("#updateDetials").modal("hide");
            $("#workList").trigger("reloadGrid");
              alert('수정되었습니다.');
          },
          error:function(data){
          alert("error");
          }
      });
  }

  // 기술경력 수정
  function careerUpdate(){
      var careerUpdateForm = $("form[name=careerupdateForm]").serialize();

      $.ajax({  
          type: "POST" ,
          url: "/detail/CareerUpdate",
          data: careerUpdateForm,
          success:function(data){
            $("#updateDetials").modal("hide");
            $("#careerList").trigger("reloadGrid");
              alert('수정되었습니다.');
          },
          error:function(data){
          alert("error");
          }
      });
  }

  // 학력 수정
  function educationUpdate(){
      var educationUpdateForm = $("form[name=educationupdateForm]").serialize();

      $.ajax({  
          type: "POST" ,
          url: "/detail/EducationUpdate",
          data: educationUpdateForm,
          success:function(data){
            $("#updateDetials").modal("hide");
            $("#educationList").trigger("reloadGrid");
              alert('수정되었습니다.');
          },
          error:function(data){
          alert("error");
          }
      });
  }

  // 교육사항 수정
  function edumatterUpdate(){
      var edumatterUpdateForm = $("form[name=edumatterupdateForm]").serialize();

      $.ajax({  
          type: "POST" ,
          url: "/detail/EduMatterUpdate",
          data: edumatterUpdateForm,
          success:function(data){
            $("#updateDetials").modal("hide");
            $("#edumatterList").trigger("reloadGrid");
              alert('수정되었습니다.');
          },
          error:function(data){
          alert("error");
          }
      });
  }

  // 자격증 수정
  function qualificationsUpdate(){
      var qualificationsupdateForm = $("form[name=qualificationsupdateForm]").serialize();

      $.ajax({  
          type: "POST" ,
          url: "/detail/QualificationsUpdate",
          data: qualificationsupdateForm,
          success:function(data){
            $("#updateDetials").modal("hide");
            $("#qualificationsList").trigger("reloadGrid");
              alert('수정되었습니다.');
          },
          error:function(data){
          alert("error");
          }
      });
  }

  // 상훈 수정
  function prizeUpdate(){
      var prizeupdateForm = $("form[name=prizeupdateForm]").serialize();

      $.ajax({  
          type: "POST" ,
          url: "/detail/PrizeUpdate",
          data: prizeupdateForm,
          success:function(data){
            $("#updateDetials").modal("hide");
            $("#prizeList").trigger("reloadGrid");
              alert('수정되었습니다.');
          },
          error:function(data){
          alert("error");
          }
      });
  }

  // 고객사 수정
  function clientUpdate(){
      var clientUpdateForm = $("form[name=clientUpdateForm]").serialize();

      $.ajax({  
          type: "POST" ,
          url: "/ClientUpdate",
          data: clientUpdateForm,
          success:function(data){
            $("#updateSetting").modal("hide");
            $("#clientList").trigger("reloadGrid");
              alert('수정되었습니다.');
          },
          error:function(data){
          alert("error");
          }
      });
  }

  // 직종 수정
  function occupationUpdate(){
    var occupationUpdateForm = $("form[name=occupationUpdateForm]").serialize();

    $.ajax({  
        type: "POST" ,
        url: "/OccupationUpdate",
        data: occupationUpdateForm,
        success:function(data){
          $("#updateSetting").modal("hide");
          $("#occupationList").trigger("reloadGrid");
            alert('수정되었습니다.');
        },
        error:function(data){
        alert("error");
        }
    });
}

  // 직무 수정
  function taskUpdate(){
      var taskUpdateForm = $("form[name=taskUpdateForm]").serialize();

      $.ajax({  
          type: "POST" ,
          url: "/TaskUpdate",
          data: taskUpdateForm,
          success:function(data){
            $("#updateSetting").modal("hide");
            $("#taskList").trigger("reloadGrid");
              alert('수정되었습니다.');
          },
          error:function(data){
          alert("error");
          }
      });
  }

  // 학원 수정
  function instituteUpdate(){
      var instituteUpdateForm = $("form[name=instituteUpdateForm]").serialize();

      $.ajax({  
          type: "POST" ,
          url: "/InstituteUpdate",
          data: instituteUpdateForm,
          success:function(data){
            $("#updateSetting").modal("hide");
            $("#instituteList").trigger("reloadGrid");
              alert('수정되었습니다.');
          },
          error:function(data){
          alert("error");
          }
      });
  }

  // 직무 수정
  function spotUpdate(){
      var spotUpdateForm = $("form[name=spotUpdateForm]").serialize();

      $.ajax({  
          type: "POST" ,
          url: "/SpotUpdate",
          data: spotUpdateForm,
          success:function(data){
            $("#updateSetting").modal("hide");
            $("#spotList").trigger("reloadGrid");
              alert('수정되었습니다.');
          },
          error:function(data){
          alert("error");
          }
      });
  }
  // 지역 수정
  function areaUpdate(){
    var areaUpdateForm = $("form[name=areaUpdateForm]").serialize();

    $.ajax({  
        type: "POST" ,
        url: "/AreaUpdate",
        data: areaUpdateForm,
        success:function(data){
          $("#updateSetting").modal("hide");
          $("#areaList").trigger("reloadGrid");
            alert('수정되었습니다.');
        },
        error:function(data){
        alert("error");
        }
    });
  }
  // 자격증 수정
  function qualifiUpdate(){
    var qualifiUpdateForm = $("form[name=qualifiUpdateForm]").serialize();

    $.ajax({  
        type: "POST" ,
        url: "/qualificatonCodeUpdate",
        data: qualifiUpdateForm,
        success:function(data){
          $("#updateSetting").modal("hide");
          $("#qualifiList").trigger("reloadGrid");
            alert('수정되었습니다.');
        },
        error:function(data){
        alert("error");
        }
    });
  }

  //pdf 저장
  function pdfSave(id){
    location.href="/download-pdf/"+id;
  }

  //설정 열기
  function setting_open(){
    var href = "setting";
    $("#optionModal").appendTo("body").modal('show');
  }