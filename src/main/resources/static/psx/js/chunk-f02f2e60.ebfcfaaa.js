(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-f02f2e60"],{"03f8":function(s,t,e){},1627:function(s,t,e){"use strict";e.r(t);var i=function(){var s=this,t=s.$createElement,e=s._self._c||t;return e("el-card",{staticClass:"box-card"},[e("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[e("span",[s._v("结果分析")]),e("el-button",{staticStyle:{float:"right",padding:"3px 0"},attrs:{type:"text"}},[s._v("操作按钮")])],1),s.isFinished?e("div",[e("div",[s._v("您的：")]),e("div",{staticClass:"result_div"},[e("div",{staticClass:"test"},[s._v("聊天活跃度："),e("span",[s._v(s._s(s.result.active))]),s._v("。在所有人当中排名：第 "),e("span",[s._v(s._s(s.result.activeRank))]),s._v(" 名")]),e("div",[s._v("您有："),e("span",[s._v(s._s(s.result.friendCount))]),s._v(" 位好友，您看起来："),e("span",[s._v(s._s(s.result.friendDetail))])]),e("div",[s._v("您经常："),e("span",[s._v(s._s(s.result.boardDetail))])]),e("div",[s._v("通过您的日常交流，您是一位："),e("span",[s._v(s._s(s.result.dailyDetail)+" ")])]),e("div",[s._v("您所发表的偏正向言论："),e("span",[s._v(s._s(s.result.positive))]),s._v(" 条，负向言论："),e("span",[s._v(s._s(s.result.negative))]),s._v(" 条，总发言数："),e("span",[s._v(s._s(s.result.msgCount)+" ")]),s._v("条")]),e("div",{staticStyle:{display:"flex"}},[e("div",[s._v("综合分析：")]),e("div",[e("div",[s._v("评分："),e("span",[s._v(s._s(s.result.rate))])]),e("div",[s._v("得分："),e("span",[s._v(s._s(s.result.score))])]),e("div",[s._v("描述："),e("span",[s._v(s._s(s.result.detail))])])])])])]):e("div",[s._v(" 您还未完成问卷调查！ ")])])},n=[],a=e("47d7"),u=e("ca00"),o={data:function(){return{loginInfo:"",result:"",isFinished:""}},methods:{queryResult:function(){var s,t=this;a["a"].getResult({account:null===(s=this.loginInfo)||void 0===s?void 0:s.user_account}).then((function(s){console.log(s),t.result=s.obj}))},queryIsFinished:function(){var s=this;a["a"].isFinished({account:this.loginInfo.user_account}).then((function(t){s.isFinished=t.obj,s.isFinished&&s.queryResult()}))}},mounted:function(){this.loginInfo=Object(u["b"])(),this.queryIsFinished(),this.queryResult()}},r=o,l=(e("36f2"),e("2877")),d=Object(l["a"])(r,i,n,!1,null,null,null);t["default"]=d.exports},"36f2":function(s,t,e){"use strict";e("03f8")},"47d7":function(s,t,e){"use strict";var i=e("b775"),n=[{name:"queryBoardMsg",url:"/board/showBoardMsg",type:"get"},{name:"addBoardMsg",url:"/board/addBoardMsg",type:"post"},{name:"showInfo",url:"/stu/showInfo",type:"get"},{name:"updateInfo",url:"/stu/update",type:"post"},{name:"showQuestion",url:"/question/show",type:"get"},{name:"sendAnswers",url:"/question/sendAnswers",type:"post"},{name:"isFinished",url:"/question/isFinished",type:"get"},{name:"getResult",url:"/question/getResult",type:"get"}],a=i["a"].createApiObj(n);t["a"]=a}}]);