
import React from 'react'
import {
  Table,
  Modal
} from 'antd';
import AddlWin from '../AddlWin'
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
      visible: false,
    });
    this.refreshList();
  },
  //任务分配查看弹窗
  showModal(title, canEdit) {
    var selectedrecord = this.state.selectedrecord;

    console.log(selectedrecord);
    var record = record;
    //从后台拿到数据
    //debugger;
    if (title == '查看详情') {
      var HousBillBasicInfo = {}
      Utils.ajaxData({
        url: ' /modules/audit/HousBillsAction/getHousBillBasicInfo.htm',
        data: {
          processInstanceId: selectedrecord.processInstanceId
        },
        callback: (result) => {
          var housBillBasicInfo = result.data.housBillBasicInfo;
          // var lend=result.data.lend;

          // if(!housBillBasicInfo.hasOwnProperty("singleRate"))
          //housBillBasicInfo.singleRate=1;
          this.setState({
            canEdit: canEdit,
            visible: true,
            title: title,
            housBillBasicInfo,
          }, () => {
            var refs = this.refs.AddlWin.refs;
            refs.DocumentsFill.setFieldsValue(housBillBasicInfo);
            //refs.DocumentsFillLend.setFieldsValue(lend);
            var lend = result.data.lend;
            var loans = result.data.loans;
            var housLoanfees = result.data.housLoanfees;

            housLoanfees && refs.DocumentsFillBack.setFieldsValue(housLoanfees);

            lend && refs.DocumentsFillLend.setFieldsValue(lend[0]);
            loans && refs.CardList.setFieldsValue(this.transFormCardData(loans));//获取打款卡中的数据
          });
        }
      });
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
  transFormCardData(data) {
    var formData = {};
    var keys = [];
    data.forEach(item => {
      let k = item.id;//id值 需要更改
      keys.push(k);
      formData[k + 'accountHolderName'] = item.accountHolderName;
      formData[k + 'cardid'] = item.cardid;
      formData[k + 'account'] = item.account;
      formData[k + 'bankFlag'] = item.bankFlag;
      formData[k + 'id'] = item.id;
    })
    formData["keys"] = keys;
    return formData;
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
  fetch(params = {}) {
    this.setState({
      loading: true
    });
    if (!params.pageSize) {
      var params = {};
      params = {

        pageSize: 10,
        currentPage: 1
      }
    }
    Utils.ajaxData({
      url: '/modules/workflow/ProcessTaskController/queryAuditTasks.htm?type=usertask-writeLoanInfo&isCompleted=true',
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
          pagination
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
    var id = record.id;
    var selectedRowKeys = this.state.selectedRowKeys;
    if (selectedRowKeys.indexOf(id) < 0) {
      selectedRowKeys.push(id);
    } else {
      selectedRowKeys.remove(id)
    }
    this.setState({
      selectedRowKeys: selectedRowKeys,
      selectedrecord: record
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
      onSelectAll: this.onSelectAll,
    };
    const hasSelected = selectedRowKeys.length > 0;
    var columns = [
      {
        title: '项目编号',
        dataIndex: 'projectCode'
      }, {
        title: '客户名称',
        dataIndex: 'customerName'
      }, {
        title: '贷款金额',
        dataIndex: "account"
      }, {
        title: '贷款期限',
        dataIndex: "timeLimit"
      }, {
        title: '报单人',
        dataIndex: "customerManager"
      }, {
        title: '流程状态',
        dataIndex: "currentProcessStateCode"
      }, {
        title: '任务处理人',
        dataIndex: "assignee"
      }, {
        title: '任务开始时间',
        dataIndex: "startTime"
      }, {
        title: '任务结束时间',
        dataIndex: "endTime"
      }
    ];
    var state = this.state;
    return (
      <div className="block-panel">
        <div className="actionBtns" style={{ marginBottom: 16 }}>
          <button className="ant-btn" disabled={!hasSelected} onClick={this.showModal.bind(this, '查看详情', false) }>
            查看详情
          </button>
        </div>
        <Table columns={columns} rowKey={this.rowKey}  size="middle"  params ={this.props.params}
          rowSelection={rowSelection}
          onRowClick={this.onRowClick}
          dataSource={this.state.data}
          pagination={this.state.pagination}
          loading={this.state.loading}
          onChange={this.handleTableChange}  />
        <AddlWin ref="AddlWin"  visible={state.visible}    title={state.title} hideModal={me.hideModal} record={state.selectedrecord}
          canEdit={state.canEdit}/>
      </div>
    );
  }
})
