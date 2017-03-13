import React from 'react'
import {
  Table,
  Modal
} from 'antd';
import AddlWin from '../AddlWin'
var confirm = Modal.confirm;
const objectAssign = require('object-assign');
var data = [
  {
    "projectCode": 123,
    "customerName": "123",
    "customerManager": "123",
    "createTime": "123",
    "assignee": "123",
    "aa": "123",

  }

]
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
      visible: false
    });
    this.refreshList();
  },
  //新增跟编辑弹窗
  showModal(title, canEdit) {
    var selectedrecord = this.state.selectedrecord
    var me = this;
    var formData = {};
    var formData2 = {};
    // if (title == "退费申请") {
    //   Utils.ajaxData({
    //     url: '/modules/finance/HousLowerCostAction/getHousLowerCostInfo.htm',
    //     data: {
    //       processInstanceId: selectedrecord.processInstanceId,
    //       projectId: selectedrecord.projectId
    //     },
    //     callback: (result) => {
    //       this.refs.AddlWin.refs.Charge_xh.setFieldsValue(result.data.housLowerCostBasicInfo);
    //       this.refs.AddlWin.refs.Charge_xh.setFieldsValue(result.data.housLowerCostInfo);
    //     }
    //   });
    // }
    var result = {
      "code": 200,
      "data": {
        "tuifei": {
          "projectCode": 123,
          "projectName": "123",
          "customerName": "123",
          "account": 123,
          "timeLimit": 2,
          "aa": "123",
          "financeSpecialist": "hh",
          "actualFee": 123456,
          "receiveType": "现金"
        }
      }

    }
    this.setState({
      visible: true,
      title: title,
      canEdit: canEdit,
      formData: formData2
    }, () => {
      //result得到一个=对象housBillBasicInfo的数据data 
      var tuifei = result.data.tuifei;
      //在控制台显示 
      console.log("tuifei", tuifei)
      //AddlWin弹窗获得对象housBillBasicInfo里面的数据。
      this.refs.AddlWin.refs.Refund.setFieldsValue(tuifei);
    });
  },
  rowKey(record) {
    return record.taskId;
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
      url: '/modules/workflow/ProcessTaskController/queryhousLowerCost.htm?isCompleted=false',
      data: params,
      callback: (result) => {
        const pagination = this.state.pagination;
        pagination.current = params.currentPage;
        pagination.pageSize = params.pageSize;
        pagination.total = result.total;
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
    var id = record.taskId;
    this.setState({
      selectedRowKeys: [id],
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
      onChange: this.onSelectChange,
    };
    const hasSelected = selectedRowKeys.length > 0;
    var columns = [{
      title: '项目编号',
      dataIndex: 'projectCode'
    }, {
        title: '客户名称',
        dataIndex: 'customerName'
      }, {
        title: '报单人',
        dataIndex: "customerManager"
      }, {
        title: '任务处理人',
        dataIndex: "assignee"
      }, {
        title: '任务开始时间',
        dataIndex: "createTime"
      }, {
        title: '是否发起',
        dataIndex: "aa"
      },];
    var state = this.state;
    return (
      <div className="block-panel">
        <div className="actionBtns" style={{ marginBottom: 16 }}>
          <button className="ant-btn"  disabled={!hasSelected} onClick={this.showModal.bind(this, '退费申请', true) }>
            退费申请
          </button>
        </div>
        <Table columns={columns} rowKey={this.rowKey}  size="middle"  params ={this.props.params}
          rowSelection={rowSelection}
          onRowClick={this.onRowClick}
          dataSource={data}
          pagination={this.state.pagination}
          loading={this.state.loading}
          onChange={this.handleTableChange}  />
        <AddlWin ref="AddlWin"  visible={state.visible} formData={state.formData}   showModal={this.showModal.bind(this, '收费', true) }
          title={state.title} hideModal={me.hideModal} record={state.selectedrecord} canEdit={state.canEdit}/>
      </div>
    );
  }
})
