(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-df8cd360"],{3180:function(e,t,s){"use strict";s("e07d")},"6ab7":function(e,t,s){e.exports=s.p+"img/cute.8f1966bb.png"},d7d14:function(e,t,s){"use strict";s.r(t);var r=function(){var e=this,t=e.$createElement,s=e._self._c||t;return s("div",{staticClass:"register-div"},[s("div",{staticClass:"login-div"},[e._m(0),s("el-form",{ref:"ruleForm",staticClass:"demo-ruleForm",attrs:{"label-position":"top","label-width":"80px",model:e.reset,rules:e.rules}},[e.verifyCodeFlag?s("div",[s("el-form-item",{attrs:{label:"新密码",prop:"user_pwd"}},[s("el-input",{attrs:{placeholder:"Password",size:"mini","show-password":""},model:{value:e.reset.user_pwd,callback:function(t){e.$set(e.reset,"user_pwd",t)},expression:"reset.user_pwd"}},[s("template",{slot:"prepend"},[s("i",{staticClass:"el-icon-lock"})])],2)],1),s("el-form-item",{attrs:{label:"确认密码",prop:"user_pwd1"}},[s("el-input",{attrs:{placeholder:"Password",size:"mini","show-password":""},model:{value:e.reset.user_pwd1,callback:function(t){e.$set(e.reset,"user_pwd1",t)},expression:"reset.user_pwd1"}},[s("template",{slot:"prepend"},[s("i",{staticClass:"el-icon-lock"})])],2)],1)],1):s("div",[s("el-form-item",{attrs:{label:"用户名",prop:"user_account"}},[s("el-input",{attrs:{placeholder:"Account/Phone/Email",size:"mini"},model:{value:e.reset.user_account,callback:function(t){e.$set(e.reset,"user_account",t)},expression:"reset.user_account"}},[s("template",{slot:"prepend"},[s("i",{staticClass:"el-icon-user"})])],2)],1),s("el-form-item",{attrs:{label:"手机",prop:"phone"}},[s("el-input",{attrs:{placeholder:"Phone",size:"mini"},model:{value:e.reset.phone,callback:function(t){e.$set(e.reset,"phone",t)},expression:"reset.phone"}},[s("template",{slot:"prepend"},[s("i",{staticClass:"el-icon-mobile-phone"})])],2)],1),s("el-form-item",{attrs:{label:"验证码",prop:"verify"}},[s("el-input",{staticStyle:{width:"65% !important"},attrs:{placeholder:"Verify",size:"mini"},model:{value:e.reset.verify,callback:function(t){e.$set(e.reset,"verify",t)},expression:"reset.verify"}},[s("template",{slot:"prepend"},[s("i",{staticClass:"el-icon-s-claim"})])],2),s("el-button",{staticStyle:{float:"right","min-width":"25%"},attrs:{type:"primary",size:"mini",disabled:e.sendButtonDisabled},on:{click:e.sendVeriftyCode}},[e._v(e._s(e.sendButtonLabel))])],1)],1),s("el-form-item",{staticClass:"button-div"},[e.verifyCodeFlag?s("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(t){return e.updatePwd()}}},[e._v("保存")]):s("el-button",{attrs:{type:"primary",size:"mini"},on:{click:function(t){return e.submitVerify()}}},[e._v("提交验证码")]),s("el-button",{attrs:{type:"",size:"mini"},on:{click:function(t){return e.cancel()}}},[e._v("取消")])],1)],1)],1)])},i=[function(){var e=this,t=e.$createElement,r=e._self._c||t;return r("div",{staticClass:"img-div"},[r("img",{attrs:{src:s("6ab7")}})])}],n=s("7ded"),o={data:function(){var e=this,t=/^(((13[0-9]{1})|(15[0-9]{1})|(16[0-9]{1})|(17[3-8]{1})|(18[0-9]{1})|(19[0-9]{1})|(14[5-7]{1}))+\d{8})$/,s=function(t,s,r){""===s?r(new Error("请输入密码")):(""!==e.register.user_pwd&&e.$refs.ruleForm.validateField("user_pwd"),r())},r=function(t,s,r){""===s?r(new Error("请再次输入密码")):s!==e.register.user_pwd?r(new Error("两次输入密码不一致!")):r()},i=function(e,s,r){""===s?r(new Error("请输入手机号")):t.test(s)||r(new Error("请输入正确的手机号"))};return{reset:{user_account:"",user_pwd:"",user_pwd1:"",phone:"",email:""},sendButtonLabel:"获取验证码",sendButtonDisabled:!1,verifyCodeFlag:!1,rules:{user_account:[{required:!0,message:"请输入用户名",trigger:"blur"}],user_pwd:[{required:!0,message:"请输入密码",trigger:"blur"},{validator:s,trigger:"blur"}],user_pwd1:[{required:!0,message:"请输入确认密码",trigger:"blur"},{validator:r,trigger:"blur"}],email:[{required:!0,message:"请输入邮箱",trigger:"blur"},{type:"email",message:"请输入正确的邮箱地址",trigger:["blur","change"]}],phone:[{required:!0,message:"请输入手机号",trigger:"blur"},{validator:i,trigger:["blur","change"]}]}}},methods:{doRegister:function(){var e=this;this.$refs.ruleForm.validate((function(t){if(!t)return e.$message.error("验证未通过，请检查后重试"),!1;n["a"].doRegister(e.register).then((function(t){console.log(t),666===t.code?(e.$message.success(t.message),e.$router.push("/login")):e.$message.error(t.message)}))}))},sendVeriftyCode:function(){var e=this;this.sendButtonDisabled=!0;var t=60;this.sendButtonLabel=t+"s重试",n["a"].sendCode({phone:this.reset.phone}).then((function(t){666===t.code?e.$message.success(t.message):e.$message.error(t.message)})),this.interval=setInterval((function(){t--,e.sendButtonLabel=t+"s重试",t<=0&&(clearInterval(e.interval),e.sendButtonLabel="发送",e.sendButtonDisabled=!1)}),1e3)},cancel:function(){this.$router.push("/login")},submitVerify:function(){var e=this;n["a"].verify({phone:this.reset.phone,code:this.reset.verify}).then((function(t){666===t.code?(e.verifyCodeFlag=!0,e.$message.success("验证通过！")):e.$message.error(t.message)}))},updatePwd:function(){var e=this,t={auth:"false"};t.user_account=this.reset.user_account,t.user_pwd=this.reset.user_pwd,t.phone=this.reset.phone,n["a"].changePwd(t).then((function(t){console.log(t),666===t.code?(e.$message.success("密码重置成功"),e.$router.push("/login")):e.$message.error(t.message)}))}}},a=o,l=(s("3180"),s("2877")),u=Object(l["a"])(a,r,i,!1,null,null,null);t["default"]=u.exports},e07d:function(e,t,s){}}]);