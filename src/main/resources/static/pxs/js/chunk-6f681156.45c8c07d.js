(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-6f681156"],{"02aa":function(s,e,t){"use strict";t.r(e);var n=function(){var s=this,e=s.$createElement,t=s._self._c||e;return t("el-card",{staticClass:"box-card"},[t("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[t("span",[s._v("问卷调查")])]),s.isFinished?t("div",[s._v(" 您已完成问卷调查，暂无新问卷！ ")]):t("div",[s._l(s.questions,(function(e,n){return t("div",{key:n,staticClass:"text item"},[t("div",[s._v(s._s(n+1)+"."+s._s(e.title))]),t("el-radio-group",{staticClass:"answers-group",model:{value:s.answer[n],callback:function(e){s.$set(s.answer,n,e)},expression:"answer[index]"}},[t("div",[t("el-radio",{staticClass:"answer-list-div",attrs:{label:e.answer1}},[s._v(s._s(e.answer1.split("//")[0]))])],1),t("div",[t("el-radio",{staticClass:"answer-list-div",attrs:{label:e.answer2}},[s._v(s._s(e.answer2.split("//")[0]))])],1),t("div",[t("el-radio",{staticClass:"answer-list-div",attrs:{label:e.answer3}},[s._v(s._s(e.answer3.split("//")[0]))])],1)])],1)})),t("div",[t("el-button",{attrs:{type:"primary",size:"mini"},on:{click:s.submitAnswers}},[s._v("提交")])],1)],2)])},i=[],a=(t("4160"),t("ac1f"),t("1276"),t("159b"),t("47d7")),o=t("ca00"),r={data:function(){return{questions:[],answer:[],isFinished:!1,loginInfo:""}},computed:{},watch:{answer:function(s,e){}},mounted:function(){this.loginInfo=Object(o["b"])(),this.queryIsFinished(),this.showQuestion()},methods:{showQuestion:function(){var s=this;a["a"].showQuestion({curr:1,pageSize:10}).then((function(e){console.log(e),s.questions=e.obj}))},queryIsFinished:function(){var s=this;a["a"].isFinished({account:this.loginInfo.user_account}).then((function(e){console.log(e),s.isFinished=e.obj}))},submitAnswers:function(){var s=this,e=0,t=!0;console.log(this.answer),t=20==this.answer.length,this.answer.forEach((function(s,n){console.log(s),s?(e+=parseInt(s.split("//")[1]),console.log(e)):t=!1})),t?a["a"].sendAnswers(e).then((function(e){console.log(e),666===e.code?s.$message.success(e.message):s.$message.error(e.message)})):this.$message.error("请完成全部题目后提交")}}},l=r,u=(t("2911"),t("2877")),c=Object(u["a"])(l,n,i,!1,null,null,null);e["default"]=c.exports},2911:function(s,e,t){"use strict";t("7662")},"47d7":function(s,e,t){"use strict";var n=t("b775"),i=[{name:"queryBoardMsg",url:"/board/showBoardMsg",type:"get"},{name:"addBoardMsg",url:"/board/addBoardMsg",type:"post"},{name:"showInfo",url:"/stu/showInfo",type:"get"},{name:"updateInfo",url:"/stu/update",type:"post"},{name:"showQuestion",url:"/question/show",type:"get"},{name:"sendAnswers",url:"/question/sendAnswers",type:"post"},{name:"isFinished",url:"/question/isFinished",type:"get"},{name:"getResult",url:"/question/getResult",type:"get"}],a=n["a"].createApiObj(i);e["a"]=a},7662:function(s,e,t){}}]);