(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-b51f70f8"],{"10bb":function(e,t,a){"use strict";a.r(t);var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-card",{staticClass:"box-card"},[a("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[a("el-button",{attrs:{type:"primary",size:"small",plain:""},on:{click:e.findUsersByLikeQuery}},[e._v("添加好友")]),a("el-badge",{staticClass:"item",staticStyle:{"margin-left":"10px"},attrs:{value:e.addRequestNum,type:"primary"}},[a("el-button",{attrs:{type:"success",size:"small",plain:""},on:{click:function(t){e.dialogAddRequestVisible=!0}}},[e._v("好友申请")])],1),a("el-badge",{staticClass:"item",staticStyle:{"margin-left":"10px"},attrs:{value:e.myRequestNum,type:"primary"}},[a("el-button",{attrs:{type:"success",size:"small",plain:""},on:{click:function(t){e.dialogMyRequestVisible=!0}}},[e._v("我的申请")])],1)],1),a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData}},[a("el-table-column",{attrs:{prop:"user_account",label:"账号",width:"180"}}),a("el-table-column",{attrs:{prop:"sname",label:"姓名",width:"180"}}),a("el-table-column",{attrs:{prop:"gender",label:"性别",width:"180",formatter:e.formatGender}}),a("el-table-column",{attrs:{prop:"email",label:"邮箱"}}),a("el-table-column",{attrs:{prop:"phone",label:"手机"}}),a("el-table-column",{attrs:{label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{size:"mini",type:"primary"},on:{click:function(a){return e.handleEdit(t.$index,t.row)}}},[e._v("详情")]),a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(a){return e.handleDelete(t.$index,t.row)}}},[e._v("删除")])]}}])})],1)],1),a("el-drawer",{attrs:{title:"好友详情",visible:e.drawer,direction:e.direction,"before-close":e.handleClose},on:{"update:visible":function(t){e.drawer=t}}},[a("el-card",{staticClass:"box-card",staticStyle:{height:"calc(100vh - 77px)"}},[a("el-row",[a("el-col",{attrs:{span:12}},[a("el-tag",{attrs:{type:"success"}},[e._v("姓名")]),e._v(":"+e._s(e.drawerFriend.sname))],1),a("el-col",{attrs:{span:12}},[a("el-tag",{attrs:{type:"success"}},[e._v("性别")]),e._v(":"+e._s(0==e.drawerFriend.gender?"男":"女"))],1)],1),a("el-row",[a("el-col",{attrs:{span:12}},[a("el-tag",{attrs:{type:"success"}},[e._v("电话")]),e._v(":"+e._s(e.drawerFriend.phone))],1),a("el-col",{attrs:{span:12}},[a("el-tag",{attrs:{type:"success"}},[e._v("邮箱")]),e._v(":"+e._s(e.drawerFriend.email))],1)],1),a("el-row",[a("el-col",{attrs:{span:12}},[a("el-tag",{attrs:{type:"success"}},[e._v("大学")]),e._v(":"+e._s(e.drawerFriend.collage))],1),a("el-col",{attrs:{span:12}},[a("el-tag",{attrs:{type:"success"}},[e._v("专业")]),e._v(":"+e._s(e.drawerFriend.major))],1)],1),a("el-divider",[e._v("message board")]),a("div",{staticStyle:{height:"calc(100% - 224px)","overflow-y":"auto"}},e._l(e.drawerBoard,(function(t,n){return a("div",{key:n},[a("el-tag",{staticStyle:{"font-size":"16px"},attrs:{type:"warning",size:"small"}},[e._v(e._s(t.from_name))]),e._v(": "),a("span",{staticStyle:{"font-size":"16px","line-height":"12px"}},[e._v(e._s(t.board_text))]),a("div",{staticStyle:{display:"flex","justify-content":"flex-end","font-size":"14px"}},[e._v(e._s(t.date_time))]),a("el-divider")],1)})),0),a("div",[a("el-row",[a("el-col",{attrs:{span:20}},[a("el-input",{staticStyle:{"max-height":"54px"},attrs:{type:"textarea",placeholder:"请输入留言内容",maxlength:"100","show-word-limit":""},model:{value:e.boardInputInfo,callback:function(t){e.boardInputInfo=t},expression:"boardInputInfo"}})],1),a("el-col",{attrs:{span:4}},[a("el-button",{staticStyle:{height:"54px","margin-left":"5px"},attrs:{type:"primary"},on:{click:e.addBoardMsg}},[e._v("发送")])],1)],1)],1)],1)],1),a("el-dialog",{attrs:{title:"添加好友",visible:e.dialogAddFriendVisible},on:{"update:visible":function(t){e.dialogAddFriendVisible=t}}},[a("div",{staticStyle:{display:"flex","justify-content":"flex-start","margin-bottom":"20px"}},[a("el-input",{staticStyle:{width:"220px"},attrs:{size:"mini",placeholder:"账号"},model:{value:e.queryInfo.user_account,callback:function(t){e.$set(e.queryInfo,"user_account",t)},expression:"queryInfo.user_account"}}),a("el-input",{staticStyle:{width:"220px","margin-left":"10px"},attrs:{size:"mini",placeholder:"姓名"},model:{value:e.queryInfo.sname,callback:function(t){e.$set(e.queryInfo,"sname",t)},expression:"queryInfo.sname"}}),a("el-button",{staticStyle:{"margin-left":"10px"},attrs:{size:"mini",type:"primary"},on:{click:e.findUsersByLikeQuery}},[e._v("搜索")])],1),a("el-table",{attrs:{data:e.userListData,border:"",size:"mini"}},[a("el-table-column",{attrs:{prop:"user_account",label:"账号",width:"180"}}),a("el-table-column",{attrs:{prop:"sname",label:"姓名",width:"180"}}),a("el-table-column",{attrs:{prop:"major",label:"专业",width:"180"}}),a("el-table-column",{attrs:{label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{size:"mini",type:"primary",plain:""}},[e._v("查看详情")]),e.friendAccountArr.includes(t.row.user_account)?a("el-button",{attrs:{size:"mini",type:"success",disabled:"",plain:""}},[e._v("已添加")]):e.sendAccountArr.includes(t.row.user_account)?a("el-button",{attrs:{size:"mini",type:"primary",disabled:"",plain:""}},[e._v("已发送申请")]):a("el-button",{attrs:{size:"mini",type:"warning",plain:""},on:{click:function(a){return e.handleSend(t.$index,t.row)}}},[e._v("提交好友申请")])]}}])})],1)],1),a("el-dialog",{attrs:{title:"好友申请",visible:e.dialogAddRequestVisible},on:{"update:visible":function(t){e.dialogAddRequestVisible=t}}},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.requestData}},[a("el-table-column",{attrs:{prop:"account",label:"账号",width:"180"}}),a("el-table-column",{attrs:{prop:"detail",label:"申请信息",width:"450"}}),a("el-table-column",{attrs:{label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{size:"mini",type:"primary"},on:{click:function(a){return e.handleAgree(t.$index,t.row)}}},[e._v("同意")]),a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(a){return e.handleRefuse(t.$index,t.row)}}},[e._v("拒绝")])]}}])})],1)],1),a("el-dialog",{attrs:{title:"我的申请",visible:e.dialogMyRequestVisible},on:{"update:visible":function(t){e.dialogMyRequestVisible=t}}},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.myRequestData}},[a("el-table-column",{attrs:{prop:"account",label:"账号",width:"180"}}),a("el-table-column",{attrs:{prop:"detail",label:"申请信息",width:"450"}}),a("el-table-column",{attrs:{label:"操作"},scopedSlots:e._u([{key:"default",fn:function(t){return[a("el-button",{attrs:{size:"mini",type:"primary"},on:{click:function(a){return e.handleCancel(t.$index,t.row)}}},[e._v("取消")])]}}])})],1)],1)],1)},s=[],r=(a("4de4"),a("d81d"),a("b775")),i=[{name:"queryFriends",url:"/friend/getFriends",type:"get"},{name:"addFriend",url:"/friend/addRequest",type:"post"},{name:"updateRel",url:"/friend/updateRel",type:"post"},{name:"getRequest",url:"/friend/getRequest",type:"get"}],o=r["a"].createApiObj(i),l=o,u=a("96c3"),c=a("47d7"),d=a("ca00"),p={name:"Friend",data:function(){return{tableData:[],userListData:[],userSearchData:[],requestData:[{}],myRequestData:[{}],friendAccountArr:[],sendAccountArr:[],dialogAddFriendVisible:!1,dialogAddRequestVisible:!1,dialogMyRequestVisible:!1,friend:{account1:"",account2:"",addStatus:0,detail:""},queryInfo:{user_account:"",sname:""},formLabelWidth:"120px",loginInfo:"",addRequestNum:"",myRequestNum:"",drawer:!1,direction:"rtl",drawerFriend:{},drawerBoard:{},boardInputInfo:""}},mounted:function(){this.loginInfo=Object(d["b"])(),this.queryFriendList(),this.getRequest(1),this.getRequest(0)},methods:{queryFriendList:function(){var e=this;l.queryFriends({account:this.loginInfo.user_account}).then((function(t){console.log(t),666===t.code&&(e.tableData=t.obj)}))},getRequest:function(e){var t=this;l.getRequest({account:this.loginInfo.user_account,flag:e}).then((function(a){666===a.code?1===e?(t.addRequestNum=0==a.obj.length?"":a.obj.length,t.requestData=a.obj):(t.myRequestNum=0==a.obj.length?"":a.obj.length,t.myRequestData=a.obj):t.$message.error("加载好友申请信息失败")}))},queryAllUser:function(){var e=this;this.friendAccountArr=this.tableData.map((function(e){return e.user_account})),this.sendAccountArr=this.requestData.map((function(e){return e.account})),this.dialogAddFriendVisible=!0,u["a"].findAll({index:1,perPage:100}).then((function(t){666===t.code&&(t.obj=t.obj.filter((function(e){return e.user_account!=Object(d["b"])().user_account})),console.log(t.obj),e.userListData=t.obj)}))},addBoardMsg:function(){var e=this,t={};t.user_account=this.drawerFriend.user_account,t.from_account=this.loginInfo.user_account,t.board_text=this.boardInputInfo,t.from_name=this.drawerFriend.sname,c["a"].addBoardMsg(t).then((function(t){666===t.code?(e.$message.success(t.message),c["a"].queryBoardMsg({account:e.drawerFriend.user_account}).then((function(t){666===t.code&&(e.drawerBoard=t.obj)}))):e.$message.error(t.message)}))},findUsersByLikeQuery:function(){var e=this;this.friendAccountArr=this.tableData.map((function(e){return e.user_account})),this.sendAccountArr=this.requestData.map((function(e){return e.account})),this.dialogAddFriendVisible=!0,""===this.queryInfo.user_account&&delete this.queryInfo.user_account,""===this.queryInfo.sname&&delete this.queryInfo.sname,u["a"].findUsersByLikeQuery(this.queryInfo).then((function(t){666===t.code&&(t.obj=t.obj.filter((function(e){return e.user_account!=Object(d["b"])().user_account})),e.userListData=t.obj)}))},handleEdit:function(e,t){var a=this;console.log(e,t),this.drawer=!0,this.drawerFriend=t,c["a"].queryBoardMsg({account:t.user_account}).then((function(e){666===e.code&&(a.drawerBoard=e.obj)}))},handleSend:function(e,t){var a=this;console.log(e,t),this.$prompt("请输入申请信息","提示",{confirmButtonText:"确定",cancelButtonText:"取消"}).then((function(e){var n=e.value;a.friend.account1=Object(d["c"])().user_account,a.friend.account2=t.user_account,a.friend.detail=n,l.addFriend(a.friend).then((function(e){666===e.code&&(a.$message.success(e.message),a.findUsersByLikeQuery())}))})).catch((function(){a.$message({type:"info",message:"取消申请"})}))},handleDelete:function(e,t){var a=this;console.log(e,t),this.$confirm("删除好友".concat(t.sname,"是否继续?"),"提示",{confirmButtonText:"确定",cancelButtonText:"取消",type:"warning"}).then((function(){var e={account1:t.user_account,account2:a.loginInfo.user_account,addStatus:-1,detail:"删除"};l.updateRel(e).then((function(e){666===e.code?(a.$message({type:"success",message:"删除成功!"}),a.queryFriendList()):a.$message.error(e.message)}))})).catch((function(){a.$message({type:"info",message:"已取消删除"})}))},handleAgree:function(e,t){var a=this;console.log(e,t);var n={account1:t.account,account2:this.loginInfo.user_account,addStatus:1,detail:"你是谁"};l.updateRel(n).then((function(e){666===e.code&&(a.$message.success("添加成功"),a.getRequest(1),a.queryFriendList())}))},handleRefuse:function(e,t){var a=this;console.log(e,t);var n={account1:this.loginInfo.user_account,account2:t.user_account,addStatus:2,detail:"你是谁"};l.updateRel(n).then((function(e){666===e.code&&(a.$message.success("操作成功"),a.getRequest(0),a.queryFriendList())}))},handleClose:function(e){this.$confirm("确认关闭？").then((function(t){e()})).catch((function(e){}))},handleCancel:function(e,t){},formatGender:function(e,t,a,n){return 0===e.gender?"男":"女"}}},f=p,m=(a("eb84"),a("2877")),b=Object(m["a"])(f,n,s,!1,null,null,null);t["default"]=b.exports},"47d7":function(e,t,a){"use strict";var n=a("b775"),s=[{name:"queryBoardMsg",url:"/board/showBoardMsg",type:"get"},{name:"addBoardMsg",url:"/board/addBoardMsg",type:"post"},{name:"showInfo",url:"/stu/showInfo",type:"get"},{name:"updateInfo",url:"/stu/update",type:"post"},{name:"showQuestion",url:"/question/show",type:"get"},{name:"sendAnswers",url:"/question/sendAnswers",type:"post"},{name:"isFinished",url:"/question/isFinished",type:"get"},{name:"getResult",url:"/question/getResult",type:"get"}],r=n["a"].createApiObj(s);t["a"]=r},"96c3":function(e,t,a){"use strict";var n=a("b775"),s=[{name:"findAll",url:"/admin/findAll",type:"get"},{name:"delete",url:"/admin/delete",type:"delete"},{name:"findUsersByLikeQuery",url:"/admin/findUsersByLikeQuery?index=1&perPage=100",type:"post"}],r=n["a"].createApiObj(s);t["a"]=r},ae3a:function(e,t,a){},d81d:function(e,t,a){"use strict";var n=a("23e7"),s=a("b727").map,r=a("1dde"),i=a("ae40"),o=r("map"),l=i("map");n({target:"Array",proto:!0,forced:!o||!l},{map:function(e){return s(this,e,arguments.length>1?arguments[1]:void 0)}})},eb84:function(e,t,a){"use strict";a("ae3a")}}]);