import React from 'react'
import {
  Table,
  Modal
} from 'antd';
import AddParaModal from './AddParaModal'
var confirm = Modal.confirm;
const objectAssign = require('object-assign');

let officeIdsObj = {};
Utils.ajaxData({
        url: '/modules/paramconfig/ProductParamAction/getOfficeCombo.htm',
        method: 'get', 
        type: 'json',
        callback: (result) => {
          var items  = result.data.map((item)=>{
             officeIdsObj[item.value] = item.text;
            });
        }
      });

export default React.createClass({
  getInitialState() {
    return { 
      loading: false,
      data: [],
      pagination: {},
      canEdit: true,
      visible: false,
    };
  },
  componentWillReceiveProps(nextProps, nextState) {  
    this.fetch(nextProps.params);
  },
  hideModal() {
    this.setState({
      visible: false,
    });
    var pagination = this.state.pagination;
    this.refreshList();
  },
  //新增跟编辑弹窗
  showModal(title, record2, canEdit) {
    var record = record2;
    if (title == '查看'||title == '编辑') {
      record.productType=String(record.productType);
      record.repaymentType=String(record.repaymentType);
      record.officeIds=String(record.officeIds);
      record.bottomMonthRate=String(record.bottomMonthRate);
      record.overdueDayRate=String(record.overdueDayRate);
      record.aheadRepayRate=String(record.aheadRepayRate);
      if(record.officeIds){
        record.officeIds = record.officeIds.split(',');
      }
      this.refs.AddParaModal.setFieldsValue(record);
    } else if (title == '新增') {
      record = null
    }
    this.setState({
      canEdit: canEdit,
      visible: true,
      title: title,
      record: record
    });
  },
  rowKey(record) {
    return record.id;
  }, 
  //分页
 handleTableChange(pagination, filters, sorter) { 
    const pager = this.state.pagination;
    pager.current = pagination.current;
    this.setState({
      pagination: pager,
    });
    this.refreshList();
  },
  fetch(params={}) {
    this.setState({
      loading: true
    });
    if(!params.pageSize){
      var params={};
      params = {
        pageSize: 10,
        currentPage: 1
      }
    }
    Utils.ajaxData({
      url: '/modules/paramconfig/ProductParamAction/getProductParamList.htm',
      data: params,
      callback: (result) => {
        const pagination = this.state.pagination;
        pagination.current = params.currentPage;
        pagination.pageSize = params.pageSize;
        pagination.total = result.totalCount;
        if (!pagination.current) {
          pagination.current = 1
        };
        this.setState({
          loading: false,
          data: result.data,
          pagination,
        }); 
      }
    });
  }, 
  refreshList() {
    var pagination = this.state.pagination;
    var params = objectAssign({}, this.props.params, {
      currentPage: pagination.current,
      pageSize: pagination.pageSize
    });
    this.fetch(params);
  },
  componentDidMount() {
    this.fetch();
  }, 
  delete(record) {
    console.log(record)
    var me = this;
    confirm({
      title: '是否确认要删除这项内容',
      onOk: function() {
        Utils.ajaxData({
          url: "/modules/paramconfig/ProductParamAction/deleteProductParam.htm  ",
          data: {
            id: record.id,
          },
          method: 'post',
          callback: (result) => {
            Modal.success({
              title: result.msg,
            });
            me.refreshList();
          }
        });
      },
      onCancel: function() {}
    });
  }, 
  render() {
    var me = this;
    const {
      loading 
    } = this.state; 
    var columns = [{
        title: '产品编号',
        dataIndex: 'id' 
      },{
        title: '产品名称',
        dataIndex: 'name' 
      },{
        title: '所属机构',
        dataIndex: "officeIds",
        render(value,record) {console.log(value)
          var arr=value;
          if(typeof value=='string'){
             arr= value&&value.split(',')
          } 
         var newArr =arr.map((item)=>{
            return officeIdsObj[item]
          });
          return newArr.join(',')
        }
      },{
        title: '创建日期',
        dataIndex: "createDate",
      },{
        title: '创建人',
        dataIndex: "creator",
      },{
        title: '是否已有项目使用',
        dataIndex: "isRun",
        render(value) {
         if (value == 0) {
            return <span>是</span>
          } else if (value == 1) {
            return <span>否</span>
          } else {
            return <span></span>
          }
        }
      },{
        title: '操作',
        dataIndex: "",
        render(text, record) {
          var record =record;
           return <div style={{textAlign: "left"}}>
          <a href="#" onClick={()=>{me.showModal('编辑',record,true)}}>编辑</a>
           <span className="ant-divider"></span>
             <a href="#" onClick={me.delete.bind(null,record)}>删除</a>
           <span className="ant-divider"></span>
          <a href="#" onClick={me.showModal.bind(null,'查看',record,false)}>查看</a></div>
        }
      }];
    var state = this.state;console.log(this.state.data)
    return (
      <div className="block-panel">
           <div className="actionBtns" style={{ marginBottom: 16 }}>
             <div className="actionBtns" style={{ marginBottom: 16 }}>
               <button className="ant-btn" onClick={this.showModal.bind(this,'新增',null,true)}>
                 新增
               </button>
             </div>  
           </div>
           <Table columns={columns} rowKey={this.rowKey}  size="middle"  
             dataSource={this.state.data}
             pagination={this.state.pagination}
             loading={this.state.loading}
             onChange={this.handleTableChange}  />
            <AddParaModal ref="AddParaModal"  visible={state.visible}    title={state.title} hideModal={me.hideModal} record={state.record}
             canEdit={state.canEdit}/> 
         </div>
    );
  } 
})  
