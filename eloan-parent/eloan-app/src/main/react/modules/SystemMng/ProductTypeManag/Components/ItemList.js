import React from 'react'
import {
  Table,
  Modal
} from 'antd';
import ItemWin from './ItemWin'
var confirm = Modal.confirm;
const objectAssign = require('object-assign');
var pager = {
  pageSize: 10,
  currentPage: 1
};
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
    console.log(nextProps)
    var formulaId = nextProps.formulaId;
    var params ;
    if(formulaId){
       params = objectAssign({},  
      {
        searchParams : JSON.stringify({id:formulaId.formulaId})
      }
     , pager)
    }
    else params=pager;
    if(formulaId&&formulaId.formulaId){
      this.fetch(params);
    }
  },
  hideModal() {
    this.setState({
      visible: false,
    }); 
    var params=  objectAssign({},{
      searchParams : JSON.stringify({id:this.props.formulaId.formulaId})},
      pager
    ); 
    this.fetch(params);
  },
  //新增跟编辑弹窗
  showModal(title, record, canEdit) {
    var record = record;
    if (title == '编辑'||title == '查看') {
      var record = record;
      record.typeId=String(record.typeId);
      this.refs.ItemWin.setFieldsValue(record);
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
    console.log(pagination)
    const pager = this.state.pagination;
    pager.current = pagination.current;
    this.setState({
      pagination: pager,
    });
    this.fetch({
      pageSize: pagination.pageSize,
      currentPage: pagination.current
    });
  },
  fetch(params) {
    this.setState({
      loading: true
    });
    if (params == null) {
      params = pager;
    }
    Utils.ajaxData({
      url: '/modules/fel/FelFormulaAction/Select.htm',
      data: params,
      callback: (result) => {
        const pagination = this.state.pagination;
        pagination.current = params.currentPage;
        pagination.total = result.total;
        if (!pagination.current) {
          pagination.current = 1
        };
        this.setState({
          loading: false,
          data: result.data,
          pagination,
        });
        this.clearList();
      }
    });
  },
  clearList() {
    this.setState({
      selectedRowKeys: [],
    });
  },
  refreshList() {
    this.fetch();
  },
  render() {
    var me = this;
    const {
      loading,
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
          <a href="#" onClick={me.showModal.bind(me,'查看',record,false)}>查看 </a></div>
        }
      }];
    var state = this.state;
    return (
      <div className="block-panel">
           <Table columns={columns} rowKey={this.rowKey}  size="middle"  params ={this.props.params}  
             dataSource={this.state.data}
             pagination={this.state.pagination}
             loading={this.state.loading}
             onChange={this.handleTableChange}  />
            <ItemWin ref="ItemWin"  visible={state.visible}    title={state.title} hideModal={me.hideModal} record={state.record}
             canEdit={state.canEdit}/> 
         </div>
    );
  }
})