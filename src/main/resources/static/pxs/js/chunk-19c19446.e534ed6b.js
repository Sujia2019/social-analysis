(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-19c19446"],{"137b":function(t,e,a){},"47d7":function(t,e,a){"use strict";var o=a("b775"),s=[{name:"queryBoardMsg",url:"/board/showBoardMsg",type:"get"},{name:"addBoardMsg",url:"/board/addBoardMsg",type:"post"},{name:"showInfo",url:"/stu/showInfo",type:"get"},{name:"updateInfo",url:"/stu/update",type:"post"},{name:"showQuestion",url:"/question/show",type:"get"},{name:"sendAnswers",url:"/question/sendAnswers",type:"post"},{name:"isFinished",url:"/question/isFinished",type:"get"},{name:"getResult",url:"/question/getResult",type:"get"}],r=o["a"].createApiObj(s);e["a"]=r},d651:function(t,e,a){"use strict";a("137b")},fc23:function(t,e,a){"use strict";a.r(e);var o=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("el-card",{staticClass:"box-card"},[a("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[a("span",[t._v("我的留言板")])]),a("div",[a("el-table",{ref:"table",staticStyle:{width:"100%"},attrs:{data:t.tableData}},[a("el-table-column",{attrs:{type:"expand"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-form",{staticClass:"demo-table-expand",attrs:{"label-position":"left",inline:""}},[a("el-form-item",{attrs:{label:"留言用户"}},[a("span",[t._v(t._s(e.row.from_account))])]),a("el-form-item",{attrs:{label:"留言内容"}},[a("span",[t._v(t._s(e.row.board_text))])]),a("el-form-item",{attrs:{label:"留言时间"}},[a("span",[t._v(t._s(e.row.date_time))])])],1)]}}])}),a("el-table-column",{attrs:{label:"留言用户",prop:"from_account",width:"200"}}),a("el-table-column",{attrs:{label:"留言内容",prop:"board_text"}}),a("el-table-column",{attrs:{label:"留言时间",prop:"date_time"}}),a("el-table-column",{attrs:{label:"操作",prop:"desc"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{size:"mini",type:"primary"},on:{click:function(a){return t.handleEdit(e.$index,e.row)}}},[t._v("详情")])]}}])})],1)],1)]),a("el-dialog",{attrs:{title:"留言",visible:t.dialogBoardVisible},on:{"update:visible":function(e){t.dialogBoardVisible=e}}},[a("el-form",{attrs:{model:t.board}},[a("el-form-item",{attrs:{label:"账号","label-width":t.formLabelWidth}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:t.board.user_account,callback:function(e){t.$set(t.board,"user_account",e)},expression:"board.user_account"}})],1),a("el-form-item",{attrs:{label:"留言信息","label-width":t.formLabelWidth}},[a("el-input",{attrs:{autocomplete:"off"},model:{value:t.board.board_text,callback:function(e){t.$set(t.board,"board_text",e)},expression:"board.board_text"}})],1)],1),a("div",{staticClass:"dialog-footer",attrs:{slot:"footer"},slot:"footer"},[a("el-button",{on:{click:function(e){t.dialogBoardVisible=!1}}},[t._v("取 消")]),a("el-button",{attrs:{type:"primary"},on:{click:function(e){t.dialogBoardVisible=!1,t.addBoardMsg()}}},[t._v("确 定")])],1)],1)],1)},s=[],r=a("47d7"),l=a("ca00"),n={data:function(){return{dialogBoardVisible:!1,formLabelWidth:"120",tableData:[{id:"12987122",date:"2020-01-23 23:36:54",category:"江浙小吃、小吃零食",desc:"荷兰优质淡奶，奶香浓而不腻",address:"上海市普陀区真北路",shop:"王小虎夫妻店",shopId:"10333"},{id:"12987123",date:"2020-01-23 23:36:54",category:"江浙小吃、小吃零食",desc:"荷兰优质淡奶，奶香浓而不腻",address:"上海市普陀区真北路",shop:"王小虎夫妻店",shopId:"10333"},{id:"12987125",date:"2020-01-23 23:36:54",category:"江浙小吃、小吃零食",desc:"荷兰优质淡奶，奶香浓而不腻",address:"上海市普陀区真北路",shop:"王小虎夫妻店",shopId:"10333"},{id:"12987126",date:"2020-01-23 23:36:54",category:"江浙小吃、小吃零食",desc:"荷兰优质淡奶，奶香浓而不腻",address:"上海市普陀区真北路",shop:"王小虎夫妻店",shopId:"10333"}],board:{board_text:"",date_time:"",from_account:"",from_name:"",user_account:""},loginInfo:""}},mounted:function(){this.loginInfo=Object(l["b"])(),this.queryBoardMsg()},methods:{queryBoardMsg:function(){var t=this;r["a"].queryBoardMsg({account:this.loginInfo.user_account}).then((function(e){console.log(e),t.tableData=e.obj}))},addBoardMsg:function(){var t=this;this.board.from_account=this.loginInfo.user_account,r["a"].addBoardMsg(this.board).then((function(e){666===e.code?t.$message.success(e.message):t.$message.error(e.message)}))},handleEdit:function(t,e){this.$refs.table.toggleRowExpansion(e,!0)}}},d=n,i=(a("d651"),a("2877")),c=Object(i["a"])(d,o,s,!1,null,null,null);e["default"]=c.exports}}]);