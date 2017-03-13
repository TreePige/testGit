//主表
import React from 'react'
import {
  Table,
  Modal
} from 'antd';
import RepayListUnder from './RepayListUnder';
import RepaymentProcessWin from './RepaymentProcessWin';
import HousingDisposalWin from './HousingDisposalWin';
var confirm = Modal.confirm;
const objectAssign = require('object-assign');
export default React.createClass({
  getInitialState() {
    return {
      selectedRowKeys: [], // 这里配置默认勾选列
      loading: false,
      data: [],
      pagination: {
        pageSize: 5,
        currentPage: 1
      },
      canEdit: true,
      visible: false,
      visible2: false,
    };
  },
  componentWillReceiveProps(nextProps, nextState) {
    this.fetch(nextProps.params);
  },
  hideModal() {
    this.setState({
      visible: false,
      visible2: false,
    });
    this.refreshList();
    var pagination = this.state.pagination;

  },
  //新增跟编辑弹窗
  showModal(title, record, canEdit) {
    var record = record;

    if (title == '修改') {
      var record = record;
      this.refs.RepaymentProcessWin.setFieldsValue(record);
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
  showModal2(title, record, canEdit) {
    var record = record;
    this.refs.RepaymentProcessWin.setFieldsValue(record);
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
  //选择
  onSelectChange(selectedRowKeys) {
    this.setState({
      selectedRowKeys
    });
  },
  //分页
  handleTableChange(pagination, filters, sorter) {
    console.log(pagination)
    const pager = this.state.pagination;
    pager.current = pagination.current;
    this.setState({
      pagination: pager,
    });
    this.refreshList();
  },
  componentDidMount() {
    this.fetch(this.props.params);
  },
  fetch(params = { pageSize: 5, currentPage: 1 }) {
    this.setState({
      loading: true
    });
    Utils.ajaxData({
      url: '/modules/system/dictList.htm',
      data: params,
      callback: (result) => {
        const pagination = this.state.pagination;
        pagination.current = params.currentPage;
        pagination.total = result.totalCount;
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
    var pagination = this.state.pagination;
    var params = objectAssign({}, this.props.params, {
      currentPage: pagination.current,
      pageSize: pagination.pageSize
    });
    this.fetch(params);
  },
  delete(record) {
    console.log(record)
    var me = this;
    confirm({
      title: '是否确认要删除这项内容',
      onOk: function () {
        Utils.ajaxData({
          url: "/modules/system/deleteDict.htm",
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
      onCancel: function () { }
    });
  },
  onRowClick(record) {
    this.setState({
      selectedRowKeys: [record.id]
    });
  },
  CancelOverdue(title) {
    var me = this;
    var id = me.state.selectedRowKeys[0];
      confirm({
        title: '取消逾期将不计算逾期罚息，确定取消该笔贷款逾期？',
        onOk: function() {
          Utils.ajaxData({
            url: "/modules/system/userEditPage.html",
            data: {
              id: id,
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
      loading,
      selectedRowKeys
    } = this.state;
    const rowSelection = {
      type: 'radio',
      selectedRowKeys,
      //onChange: this.onSelectChange,
    };
    const hasSelected = selectedRowKeys.length > 0;
    var columns = [{
      title: '项目编号',
      dataIndex: 'name'
    }, {
        title: '合同编号',
        dataIndex: "code"
      }, {
        title: '产品类型',
        dataIndex: 'remark'
      }, {
        title: '客户姓名',
        dataIndex: "sort"
      }, {
        title: '所属机构',
        dataIndex: "sort11"
      }, {
        title: '借款期数',
        dataIndex: "sort9"
      }, {
        title: '贷款金额',
        dataIndex: "sort8"
      }, {
        title: '已还利息',
        dataIndex: "sort7"
      }, {
        title: '已还本金',
        dataIndex: "sort6"
      }, {
        title: '剩余本金',
        dataIndex: "sort5"
      }, {
        title: '下户费',
        dataIndex: "sort4"
      },{
        title: '返费',
        dataIndex: "sort3"
      },{
        title: '还款状态',
        dataIndex: "sort2"
      },{
        title: '还款处理',
        dataIndex: "sort1"
      }];
    var state = this.state;
    return (
      <div className="block-panel">
        <div className="actionBtns" style={{ marginBottom: 16 }}>
          <button className="ant-btn" disabled={!hasSelected} onClick={this.showModal.bind(this, '还款处理', null, true) }>
            还款处理
          </button>
          <button className="ant-btn" disabled={!hasSelected} onClick={this.showModal2.bind(this, '房屋处置', null, true) }>
            房屋处置
          </button>
          <button className="ant-btn" disabled={!hasSelected} onClick={this.showModal.bind(this, '提前还款', null, true) }>
            提前还款
          </button>
          <button className="ant-btn" disabled={!hasSelected} onClick={this.showModal.bind(this, '罚息减免', null, true) }>
            罚息减免
          </button>
          <button className="ant-btn" disabled={!hasSelected} onClick={this.showModal.bind(this, '导出表格', null, true) }>
            导出表格
          </button>
          <button className="ant-btn" disabled={!hasSelected} onClick={this.CancelOverdue.bind(this,'取消逾期')}>
            取消逾期
          </button>
        </div>
        <Table columns={columns} rowKey={this.rowKey}  size="middle"  params ={this.props.params} rowSelection={rowSelection}
          dataSource={this.state.data} onRowClick={this.onRowClick}
          pagination={this.state.pagination}
          loading={this.state.loading}
          onChange={this.handleTableChange}  />
        <RepayListUnder parentId={selectedRowKeys[0]}/>
        <RepaymentProcessWin ref="RepaymentProcessWin"  visible={state.visible}    title={state.title} hideModal={me.hideModal} record={state.record}
          canEdit={state.canEdit}/>
        <HousingDisposalWin ref="HousingDisposalWin"  visible={state.visible2}    title={state.title} hideModal={me.hideModal} record={state.record}
          canEdit={state.canEdit}/>  
      </div>
    );
  }
})
