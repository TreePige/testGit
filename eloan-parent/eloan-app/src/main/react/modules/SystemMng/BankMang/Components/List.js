import React from 'react'
import {
  Table,
  Modal
} from 'antd';
import AddBankWin from './AddBankWin'
var confirm = Modal.confirm;
export default React.createClass({
  getInitialState() {
    return {
      selectedRowKeys: [], // 这里配置默认勾选列
      loading: false,
      data: [],
      pagination: {
        pageSize: 10,
        currentPage: 1
      },
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
  //新增跟修改弹窗
  showModal(title, record, canEdit) {
    var record = record;
    if (title == '修改') {
      var record = record;
      record.accountType = String(record.accountType);
      this.refs.AddBankWin.setFieldsValue(record);
      console.log(record);
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
  fetch(params = {
    pageSize: 10,
    currentPage: 1
  }) {
    this.setState({
      loading: true
    });
    Utils.ajaxData({
      url: '/modules/system/sysBankAction/query.htm',
      data: params,
      callback: (result) => {
        const pagination = this.state.pagination;
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
    var params = {
      currentPage: pagination.current,
      pageSize: pagination.pageSize
    };
    this.fetch(params);
  },
  delete(record) {
    console.log(record)
    var me = this;
    confirm({
      title: '是否确认要删除这项内容',
      onOk: function () {
        Utils.ajaxData({
          url: "/modules/system/sysBankAction/deleteById.htm",
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
  componentDidMount() {
    this.fetch();
  },
  render() {
    var me = this;
    const {
      loading,
      selectedRowKeys
    } = this.state;
    const rowSelection = {
      selectedRowKeys,
      onChange: this.onSelectChange,
    };
    const hasSelected = selectedRowKeys.length > 0;
    var columns = [{
      title: '开户行',
      dataIndex: 'bankCode'
    }, {
        title: '户名',
        dataIndex: "openName"
      }, {
        title: '账户',
        dataIndex: 'account'
      }, {
        title: '开户网点',
        dataIndex: "openNet"
      }, {
        title: '是否放款使用',
        dataIndex: "isLoan",
        render: function (value) {
          if (value == 0)
            return "是";
          else return "否";
        }
      }, {
        title: '是否回款使用',
        dataIndex: "isRepayment",
        render: function (value) {
          if (value == 0)
            return "是";
          else return "否";
        }
      }, {
        title: '创建者',
        dataIndex: "creator"
      },
      {
        title: '创建时间',
        dataIndex: 'createTime',
      }, {
        title: '操作',
        dataIndex: "",
        render(text, record) {
          return <div style={{ textAlign: "left" }}><a href="#" onClick={me.showModal.bind(me, '修改', record, true) }>修改 </a><a href="#" onClick={me.delete.bind(me, record) }>删除 </a></div>
        }
      }];
    var state = this.state;
    return (
      <div className="block-panel">
        <div className="actionBtns" style={{ marginBottom: 16 }}>
          <button className="ant-btn" onClick={this.showModal.bind(this, '新增', null, true) }>
            新增
          </button>
        </div>
        <Table columns={columns} rowKey={this.rowKey}  size="middle"  params ={this.props.params} rowSelection={rowSelection}
          dataSource={this.state.data}
          pagination={this.state.pagination}
          loading={this.state.loading}
          onChange={this.handleTableChange}  />
        <AddBankWin ref="AddBankWin"  visible={state.visible}    title={state.title} hideModal={me.hideModal} record={state.record}
          canEdit={state.canEdit}/>
      </div>
    );
  }
}) 
