import React from 'react'
import {
  Table,
  Modal
} from 'antd';
import PaymentHistoryWin from './PaymentHistoryWin'
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
    var parentId = nextProps.parentId;
    var params;
    if (parentId) {
      params = objectAssign({},
        { id: parentId }
        , pager)
    }
    else params = pager;

    this.fetch(params);
  },
  hideModal() {
    this.setState({
      visible: false,
    });
    var params = objectAssign({}, {
      id: this.props.parentId
    },
      pager
    );
    this.fetch(params);
  },
  //新增跟编辑弹窗
  showModal(title, record, canEdit) {
    var newRecord = {};
    if (title == '修改') {
      newRecord = record;
      newRecord.parentId = this.props.parentId;
    } else if (title == '新增字项') {
      newRecord.parentId = this.props.parentId;
    }
    this.refs.PaymentHistoryWin.setFieldsValue(newRecord);
    this.setState({
      canEdit: canEdit,
      visible: true,
      title: title,
      record: newRecord
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
      url: '/modules/system/getDictDetail.htm',
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
  delete(record) {
    console.log(record)
    var me = this;
    confirm({
      title: '是否确认要删除这项内容',
      onOk: function () {
        Utils.ajaxData({
          url: "/modules/system/deleteDictDetail.htm",
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
  render() {
    var me = this;
    const {
      loading,
    } = this.state;
    var columns = [{
      title: '期次',
      dataIndex: 'parentId'
    }, {
        title: '还款日期',
        dataIndex: 'itemValue'
      }, {
        title: '本期应还金额',
        dataIndex: 'itemCode'
      }, {
        title: '已还本期利息',
        dataIndex: 'itemCode8'
      }, {
        title: '已还本期本金',
        dataIndex: 'itemCode7'
      }, {
        title: '实还金额',
        dataIndex: 'itemCode6'
      }, {
        title: '剩余待还本金',
        dataIndex: 'itemCode5'
      }, {
        title: '罚息金额',
        dataIndex: 'itemCode4'
      }, {
        title: '实际罚息金额',
        dataIndex: 'itemCode3'
      }, {
        title: '实际还款日期',
        dataIndex: 'itemCode2'
      }, {
        title: '还款状态',
        dataIndex: 'itemCode1'
      }, {
        title: '操作',
        dataIndex: "",
        render(text, record) {
          return <div style={{ textAlign: "left" }}><a href="#" onClick={me.showModal.bind(me, '还款记录', record, true) }>还款记录 </a></div>
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
        <PaymentHistoryWin ref="PaymentHistoryWin"  visible={state.visible}    title={state.title} hideModal={me.hideModal} record={state.record}
          canEdit={state.canEdit}/>  
      </div>
    );
  }
})