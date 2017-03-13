import React from 'react'
import {
  Table,
  Modal
} from 'antd';
import AddFormula from './AddFormula'
var confirm = Modal.confirm;
const objectAssign = require('object-assign');
export default React.createClass({
  getInitialState() {
    return {
      selectedRowKeys: [], // 这里配置默认勾选列
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
  showModal(title, record, canEdit) {
    var record = record;
    if (title == '编辑'||title == '查看') {
      var record = record;
      record.typeId=String(record.typeId);
      this.refs.AddFormula.setFieldsValue(record);
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
      url: '/modules/fel/FelFormulaAction/Select.htm',
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
  delete(record) {console.log(record)
    var me = this;   
    confirm({
      title: '是否确认要删除这项内容',
      onOk: function() {
        Utils.ajaxData({
          url:  "/modules/fel/FelFormulaAction/Delete.htm",
          data: {
            id: record.id, 
          },
          method: 'post',
          callback: (result) => {
            if (result.code == 200) {
            Modal.success({
              title: result.msg,
            });
          }else {
            Modal.error({
                  title: result.msg,
              });
          }
            me.refreshList();
          }
        });
      },
      onCancel: function() {}
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
  render() {
    var me = this;
    const {
      loading 
    } = this.state;
   
    var columns = [{
      title: '主键',
      dataIndex: 'id' 
      },{
        title: '字段名称',
        dataIndex: 'chineseName' 
      },
      {
        title: '参数名',
        dataIndex: 'englishName' 
      },{
        title: '单位',
        dataIndex: "unit"
      },{
        title: '数据来源',
        dataIndex: "dataSource",
        render: function (value) {
         if(value==1){
            return "系统计算"
          } 
          else if(value==2){
            return "人工录入"
          }
          else return <span></span>;
        }
      },{
        title: '公式',
        dataIndex: "formulaChinese",
      },{
        title: '备注',
        dataIndex: "note",
      },{
        title: '操作',
        dataIndex: "",
        render(text, record) {
          return <div style={{textAlign: "left"}}>
          <a href="#" onClick={me.showModal.bind(me,'编辑',record,true)}>编辑 </a>
           <span className="ant-divider"></span>
             <a href="#" onClick={me.delete.bind(null,record)}>删除</a>
           <span className="ant-divider"></span>
          <a href="#" onClick={me.showModal.bind(me,'查看',record,false)}>查看 </a></div>
        }
      }];
    var state = this.state;
    return (
      <div className="block-panel">
           <div className="actionBtns" style={{ marginBottom: 16 }}>
             <div className="actionBtns" style={{ marginBottom: 16 }}>
               <button className="ant-btn" onClick={this.showModal.bind(this,'新增公式',null,true)}>
                 新增公式
               </button>
             </div>  
           </div>
           <Table columns={columns} rowKey={this.rowKey}  size="middle"  params ={this.props.params}  
             dataSource={this.state.data}
             pagination={this.state.pagination}
             loading={this.state.loading}
             onChange={this.handleTableChange}  />
            <AddFormula ref="AddFormula"  visible={state.visible}    title={state.title} hideModal={me.hideModal} record={state.record}
             canEdit={state.canEdit}/> 
         </div>
    );
  } 
}) 
