import React from 'react'
import {
  Table,
  Modal
} from 'antd';
import AddFormula from './AddFormula'
import ChackLog from './ChackLog'
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
    this.clearSelectedList();
    this.fetch(nextProps.params);
  },
  hideModal() {
    this.setState({
      visible1: false,
      visible2: false,
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
      visible1: true,
      title: title,
      record: record
    });
  },
   //查看编辑弹窗
  showModal2(title, record, canEdit) {
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
      visible2: true,
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
      url: '/modules/supplymaterial/SupplyMaterialAction/getSupplyMaterialPage.htm',
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
   clearSelectedList() {
    this.setState({
      selectedRowKeys: [],
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
  onRowClick(record) {
    var id = record.taskId;
    this.setState({
      selectedRowKeys:[id],
      selectedrecord:record
    });
  },
  //选择
  onSelectAll(selected, selectedRowKeys, selectedRows, changeRows) {
    if (selected) {
      this.setState({
        selectedRowKeys
      })
    } else {
      this.setState({
        selectedRowKeys: []
      })
    }
  },
  render() {
    var me = this;
    const {
      loading,
      selectedRowKeys
    } = this.state;
    const rowSelection = {
      // type: 'checkbox',
      selectedRowKeys,
      onChange: this.onSelectChange,
    };
     const hasSelected = selectedRowKeys.length > 0;
    var columns = [{
      title: '项目编号',
      dataIndex: 'projectCode' 
      },{
        title: '客户名称',
        dataIndex: 'customerName' 
      },{
        title: '客户金额',
        dataIndex: 'amount' 
      },{
        title: '贷款期限',
        dataIndex: 'timeLimit' 
      },{
        title: '金融顾问/分销商名称',
        dataIndex: 'financialAdvisers' 
      },{
        title: '任务处理人',
        dataIndex: '' 
      },{
        title: '任务开始时间',
        dataIndex: 'executor' 
      }];
    var state = this.state;
    return (
      <div className="block-panel">
           <div className="actionBtns" style={{ marginBottom: 16 }}>
             <div className="actionBtns" style={{ marginBottom: 16 }}>
               <button className="ant-btn" onClick={this.showModal.bind(this,'贷后补充资料',null,true)}>
                 补充资料
               </button>
               <button className="ant-btn"  disabled={!hasSelected} onClick={this.showModal2.bind(this,'查看登陆日志',null,true)}>
                 查看登陆日志
               </button>
             </div>  
           </div>
           <Table columns={columns} rowKey={this.rowKey}  size="middle"  params ={this.props.params}  
             rowSelection={rowSelection}
             onRowClick={this.onRowClick}
             dataSource={this.state.data}
             pagination={this.state.pagination}
             loading={this.state.loading}
             onChange={this.handleTableChange}  />
            <AddFormula ref="AddFormula"  visible={state.visible1}    title={state.title} hideModal={me.hideModal} record={state.record}
             canEdit={state.canEdit}/> 
            <ChackLog ref="ChackLog"  visible={state.visible2}    title={state.title} hideModal={me.hideModal} record={state.record}
             canEdit={state.canEdit}/> 
         </div>
    );
  } 
}) 
