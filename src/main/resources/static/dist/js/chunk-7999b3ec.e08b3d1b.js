(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-7999b3ec"],{"137b":function(t,e,a){},d651:function(t,e,a){"use strict";a("137b")},fc23:function(t,e,a){"use strict";a.r(e);var s=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("el-card",{staticClass:"box-card"},[a("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[a("span",[t._v("我的留言板")]),a("el-button",{staticStyle:{float:"right",padding:"3px 0"},attrs:{type:"text"}},[t._v("操作按钮")])],1),a("div",[a("el-table",{staticStyle:{width:"100%"},attrs:{data:t.tableData}},[a("el-table-column",{attrs:{type:"expand"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-form",{staticClass:"demo-table-expand",attrs:{"label-position":"left",inline:""}},[a("el-form-item",{attrs:{label:"商品名称"}},[a("span",[t._v(t._s(e.row.name))])]),a("el-form-item",{attrs:{label:"所属店铺"}},[a("span",[t._v(t._s(e.row.shop))])]),a("el-form-item",{attrs:{label:"商品 ID"}},[a("span",[t._v(t._s(e.row.id))])]),a("el-form-item",{attrs:{label:"店铺 ID"}},[a("span",[t._v(t._s(e.row.shopId))])]),a("el-form-item",{attrs:{label:"商品分类"}},[a("span",[t._v(t._s(e.row.category))])]),a("el-form-item",{attrs:{label:"店铺地址"}},[a("span",[t._v(t._s(e.row.address))])]),a("el-form-item",{attrs:{label:"商品描述"}},[a("span",[t._v(t._s(e.row.desc))])])],1)]}}])}),a("el-table-column",{attrs:{label:"留言主题",prop:"id"}}),a("el-table-column",{attrs:{label:"时间",prop:"date"}}),a("el-table-column",{attrs:{label:"操作",prop:"desc"},scopedSlots:t._u([{key:"default",fn:function(e){return[a("el-button",{attrs:{size:"mini",type:"primary"},on:{click:function(a){return t.handleEdit(e.$index,e.row)}}},[t._v("详情")]),a("el-button",{attrs:{size:"mini",type:"danger"},on:{click:function(a){return t.handleDelete(e.$index,e.row)}}},[t._v("删除")])]}}])})],1)],1)])],1)},r=[],o=a("b775"),n=[{name:"queryBoardMsg",url:"/board/showBoardMsg",type:"get"},{name:"addBoardMsg",url:"/board/addBoardMsg",type:"post"},{name:"showInfo",url:"/stu/showInfo",type:"get"}],l=o["a"].createApiObj(n),d=l,i={data:function(){return{tableData:[{id:"12987122",date:"2020-01-23 23:36:54",category:"江浙小吃、小吃零食",desc:"荷兰优质淡奶，奶香浓而不腻",address:"上海市普陀区真北路",shop:"王小虎夫妻店",shopId:"10333"},{id:"12987123",date:"2020-01-23 23:36:54",category:"江浙小吃、小吃零食",desc:"荷兰优质淡奶，奶香浓而不腻",address:"上海市普陀区真北路",shop:"王小虎夫妻店",shopId:"10333"},{id:"12987125",date:"2020-01-23 23:36:54",category:"江浙小吃、小吃零食",desc:"荷兰优质淡奶，奶香浓而不腻",address:"上海市普陀区真北路",shop:"王小虎夫妻店",shopId:"10333"},{id:"12987126",date:"2020-01-23 23:36:54",category:"江浙小吃、小吃零食",desc:"荷兰优质淡奶，奶香浓而不腻",address:"上海市普陀区真北路",shop:"王小虎夫妻店",shopId:"10333"}],board:{board_text:"string",date_time:"string",from_account:"string",from_name:"string",user_account:"test"}}},mounted:function(){this.queryBoardMsg()},methods:{queryBoardMsg:function(){d.queryBoardMsg({account:"test"}).then((function(t){console.log(t)}))}}},c=i,u=(a("d651"),a("2877")),p=Object(u["a"])(c,s,r,!1,null,null,null);e["default"]=p.exports}}]);