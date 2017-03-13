import React from 'react'
import {
  Table,
  Modal
} from 'antd';
import AddlWin from '../../AddlWin'
import Actions from '../Actions'
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
  //查看弹窗
  showModal(title, canEdit) {
    var selectedrecord = this.state.selectedrecord
    var result = {
          "code": 200,
          "data": {
              "housBillBasicInfo": {
                  "account": 100000,
                  "collectionForm": "线下收取",
                  "customerName": "1",
                  "projectBelongs": 2,
                  "projectCode": "110000058",
                  "receivableAccount": 1231231,
                  "repaymentRate": 0.04,
                  "singleRate": 2,
                  "timeLimit": 3,
                  "firstInterest": 2
              },
              "housLoanfees": {
                  "financialAdvisers": 1,
                  "institutionName": "2",
                  "projectBelongs": 2,
                  "returnBank": "测试内容v9ao",
                  "returnCard": "测试内容i1h8",
                  "returnFee": 87535,
                  "returnLimit": 1,
                  "returnRate": 10502,
                  "salesman": "测试内容53v7",
                  "serviceBank": "测试内容o8ee",
                  "serviceCard": "测试内容45ln",
                  "serviceFee": 33470,
                  "serviceName": "测试内容2k6s"
              },
              "lend":
              {

                  "account": "111132222"              

              },
              "loans":
              {
                  "account": 55033,
                  "accountHolderName": "测试内容r8yu",
                  "bankFlag": 48833,
                  "cardid": "测试内容c4jn"
              }

          },
          "msg": "操作成功"
      };
    // if (title == '放款单填写') {
    //   Utils.ajaxData({
    //     url: '/modules/audit/HousBillsAction/getHousBillBasicInfo.htm',
    //     data: {
    //       // processInstanceId: selectedrecord.processInstanceId
    //     },
    //     callback: (result) => {
    //       // result.data.housPropertyInformation.propertyProperties = String(result.data.housPropertyInformation.propertyProperties);
    //       // result.data.housPropertyInformation.propertySituation = String(result.data.housPropertyInformation.propertySituation);
    //       // result.data.housPropertyInformation.whetherOneContact = String(result.data.housPropertyInformation.whetherOneContact);
    //       // result.data.housPropertyInformation.whetherTwoContact = String(result.data.housPropertyInformation.whetherTwoContact);

    //       // result.data.housBorrowingBasics.marryStatus = String(result.data.housBorrowingBasics.marryStatus);

    //       // result.data.plBorrowRequirement.repaymentType = String(result.data.plBorrowRequirement.repaymentType);
    //       // result.data.plBorrowRequirement.projectBelongs = String(result.data.plBorrowRequirement.projectBelongs);
    //       // result.data.plBorrowRequirement.productId = String(result.data.plBorrowRequirement.productId);

    //       // formData = this.transformeData(result.data.housAssessmentAgencies);
    //       // this.refs.ReviewWin.refs.NewEvaOrg.setFieldsValue(formData);
    //       // this.refs.ReviewWin.refs.HousingInformation.setFieldsValue(result.data.housPropertyInformation);
    //       // this.refs.ReviewWin.refs.BasiInformation.setFieldsValue(result.data.housBorrowingBasics);
    //       // this.refs.ReviewWin.refs.BorrowingNeeds.setFieldsValue(result.data.plBorrowRequirement);
    //     }
    //   });
    // }
    this.setState({
      canEdit: canEdit,
      visible: true,
      title: title,
    },()=>{
           var housBillBasicInfo = result.data.housBillBasicInfo; 
           console.log("housBillBasicInfo", housBillBasicInfo)
           this.refs.AddlWin.refs.DocumentsFill.setFieldsValue(housBillBasicInfo);
    });
  },
  rowKey(record) {
    return record.taskId;
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
      url: '/modules/workflow/ProcessTaskController/queryAuditTasks.htm?type=usertask-writeLoanInfo&&isCompleted=false',
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
    var id = record.taskId;
    this.setState({
      selectedRowKeys: [id],
      selectedrecord: record
    });
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
        title: '项目名称',
        dataIndex: "projectName"
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
        title: '任务处理人',
        dataIndex: "assignee"
      }, {
        title: '任务开始时间',
        dataIndex: "createTime"
      }
    ];
    var state = this.state;
    return (
      <div className="block-panel">
        <div className="actionBtns" style={{ marginBottom: 16 }}>
          <button className="ant-btn"   disabled={false} onClick={this.showModal.bind(this, '放款单填写', false) }>
            审核
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
