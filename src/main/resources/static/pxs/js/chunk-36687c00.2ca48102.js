(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-36687c00"],{"1ad7":function(e,t,a){"use strict";a.r(t);var i=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-card",[a("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:e.queryAllQuestions}},[e._v("添加问卷")])],1),a("div",[a("div",{staticStyle:{"margin-bottom":"10px"}},[a("el-select",{attrs:{size:"mini",placeholder:"问卷"},on:{change:e.handleChange},model:{value:e.selectedPageId,callback:function(t){e.selectedPageId=t},expression:"selectedPageId"}},e._l(e.option,(function(e,t){return a("el-option",{key:t,attrs:{label:e.pageName,value:e.id}})})),1)],1),a("div",e._l(e.questionsData,(function(t,i){return a("div",{key:i,staticClass:"text item"},[a("div",[e._v(e._s(i+1)+"."+e._s(t.title))]),a("el-radio-group",{staticClass:"answers-group"},[a("div",[a("el-radio",{staticClass:"answer-list-div",attrs:{label:t.answer1}},[e._v(e._s(t.answer1.split("//")[0]))])],1),a("div",[a("el-radio",{staticClass:"answer-list-div",attrs:{label:t.answer2}},[e._v(e._s(t.answer2.split("//")[0]))])],1),a("div",[a("el-radio",{staticClass:"answer-list-div",attrs:{label:t.answer3}},[e._v(e._s(t.answer3.split("//")[0]))])],1)])],1)})),0)])]),a("el-dialog",{attrs:{title:"请选择20道题目",visible:e.dialogAddVisible,width:"70%"},on:{"update:visible":function(t){e.dialogAddVisible=t}}},[a("div",{class:20===e.num?"greenClass":"redClass"},[e._v("已选中题目数量:"+e._s(e.num))]),a("el-table",{staticStyle:{width:"100%",height:"60vh","overflow-y":"auto"},attrs:{data:e.tableData},on:{"selection-change":e.handleSelectionChange}},[a("el-table-column",{attrs:{type:"selection",width:"55"}}),a("el-table-column",{attrs:{prop:"title",label:"问题",width:"320"}}),a("el-table-column",{attrs:{prop:"answer1",label:"答案1"}}),a("el-table-column",{attrs:{prop:"answer2",label:"答案2"}}),a("el-table-column",{attrs:{prop:"answer3",label:"答案3"}})],1),a("span",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{attrs:{size:"mini"},on:{click:function(t){e.dialogAddVisible=!1}}},[e._v("取 消")]),a("el-button",{attrs:{type:"primary",size:"mini"},on:{click:e.addPage}},[e._v("确 定")])],1)],1)],1)},s=[],n=(a("d81d"),a("d3b7"),a("25f0"),a("96c3")),l={data:function(){return{curPage:1,pageSize:1e3,totalPage:0,tableData:[],questionsData:[],dialogAddVisible:!1,form:{},formLabelWidth:"120",option:[],selectedPageId:"",multipleSelection:[],num:"0"}},mounted:function(){this.getAvailableQuestions()},methods:{getAvailableQuestions:function(){var e=this;n["a"].getAvailableQuestions().then((function(t){666==t.code?e.option=t.obj:e.$message.warning(t.message)}))},queryAllQuestions:function(){var e=this;this.dialogAddVisible=!0,n["a"].getAllQuestions({index:this.curPage,perPage:this.pageSize}).then((function(t){console.log(t),666==t.code?(e.tableData=t.obj,e.curPage=t.pageNum,e.pageSize=t.pageSize,e.totalPage=t.totalSize):e.$message.warning(t.message)}))},handleChange:function(e){var t=this;console.log(e),n["a"].getQuestionsDetail({qId:e}).then((function(e){console.log(e),666===e.code?t.questionsData=e.obj:t.$message.success(e.message)}))},handleSelectionChange:function(e){this.multipleSelection=e,this.num=this.multipleSelection.length},addPage:function(){var e=this;if(20==this.num){var t=this.multipleSelection.map((function(e){return e.id}));console.log(t.toString()),this.$prompt("请输入问卷名称","提示",{confirmButtonText:"确定",cancelButtonText:"取消"}).then((function(a){var i=a.value;n["a"].addPage({available:!0,pageName:i,questionList:"["+t.toString()+"]"}).then((function(t){console.log(t),666===t.code?(e.$message.success("添加成功"),e.dialogAddVisible=!1,e.getAvailableQuestions()):e.$message.error(t.message)}))})).catch((function(){e.$message({type:"info",message:"取消输入"})}))}else this.$message.error("请选择20道题目！")}}},o=l,r=(a("487e"),a("2877")),d=Object(r["a"])(o,i,s,!1,null,null,null);t["default"]=d.exports},"487e":function(e,t,a){"use strict";a("8d82")},"8d82":function(e,t,a){},"96c3":function(e,t,a){"use strict";var i=a("b775"),s=[{name:"findAll",url:"/admin/findAll",type:"get"},{name:"delete",url:"/admin/delete",type:"post"},{name:"findUsersByLikeQuery",url:"/admin/findUsersByLikeQuery?index=1&perPage=100",type:"post"},{name:"getChatLogsName",url:"/admin/getChatLogsName",type:"get"},{name:"getChatLog",url:"/admin/getChatLog",type:"get"},{name:"getAllQuestions",url:"/admin/getAllQuestions",type:"get"},{name:"addQuestion",url:"/admin/addQuestion",type:"post"},{name:"getAvailableQuestions",url:"/admin/getAvailableQuestions",type:"get"},{name:"getQuestionsDetail",url:"/admin/getQuestionsDetail",type:"get"},{name:"addPage",url:"/admin/addQuestionPage",type:"post"},{name:"finishedPercent",url:"/admin/finishedPercent",type:"get"}],n=i["a"].createApiObj(s);t["a"]=n},d81d:function(e,t,a){"use strict";var i=a("23e7"),s=a("b727").map,n=a("1dde"),l=a("ae40"),o=n("map"),r=l("map");i({target:"Array",proto:!0,forced:!o||!r},{map:function(e){return s(this,e,arguments.length>1?arguments[1]:void 0)}})}}]);