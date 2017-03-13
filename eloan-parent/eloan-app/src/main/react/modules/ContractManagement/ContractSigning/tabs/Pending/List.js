import React from 'react'
import {
  Table,
  Modal,
} from 'antd';
import ReviewWin from '../ReviewWin'
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
      visible: false
    });
    this.refreshList();
  },
  transformeData(data) {
    var formData = {};
    var keys = [];
    data.forEach(item => {
      let k = item.assessmentAgencies;
      keys.push(k);
      formData[k + 'assessedValue'] = item.assessedValue;
      formData[k + 'id'] = item.id;
    })
    formData["keys"] = keys;
    return formData;
  },
  //新增跟编辑弹窗
  showModal(title, canEdit) {
    var selectedrecord = this.state.selectedrecord
    var me = this;
    var formData = {};
    /* if (title == "申请进件") {
       Utils.ajaxData({
         url: '/modules/common/action/plConsultAction/getConsultById.htm',
         data: {
           processInstanceId: selectedrecord.processInstanceId
         },
         callback: (result) => {
           result.data.housPropertyInformation.propertyProperties = String(result.data.housPropertyInformation.propertyProperties);
           result.data.housPropertyInformation.propertySituation = String(result.data.housPropertyInformation.propertySituation);
           result.data.housPropertyInformation.whetherOneContact = String(result.data.housPropertyInformation.whetherOneContact);
           result.data.housPropertyInformation.whetherTwoContact = String(result.data.housPropertyInformation.whetherTwoContact);
 
           result.data.housBorrowingBasics.marryStatus = String(result.data.housBorrowingBasics.marryStatus);
           if(result.data.plBorrowRequirement){
              result.data.plBorrowRequirement.repaymentType = String(result.data.plBorrowRequirement.repaymentType);
              result.data.plBorrowRequirement.projectBelongs = String(result.data.plBorrowRequirement.projectBelongs);
              result.data.plBorrowRequirement.productId = String(result.data.plBorrowRequirement.productId);
           }
           
 
           formData = this.transformeData(result.data.housAssessmentAgencies);
           this.refs.ReviewWin.refs.NewEvaOrg.setFieldsValue(formData);
           this.refs.ReviewWin.refs.HousingInformation.setFieldsValue(result.data.housPropertyInformation);
           this.refs.ReviewWin.refs.BasiInformation.setFieldsValue(result.data.housBorrowingBasics);
           this.refs.ReviewWin.refs.BorrowingNeeds.setFieldsValue(result.data.plBorrowRequirement);
         }
       });
     }*/
    var result = {
      "code": 23311,
      "data": {
        "test": {
          "account": 33730,
          "name": "测试内容3o1m",
          "spouseName": "测试内容152n",
          "endTime": "测试内容oy4o",
          "executor": "测试内容1r3v",
          "projectCode": "测试内容s118",
          "projectName": "测试内容ti77",
          "startTime": "测试内容okfo",
          "timeLimit": 64403

        },

      },
      "msg": "操作成功"

    }
    this.setState({
      visible: true,
      title: title,
      canEdit: canEdit,
    }, () => {
      var test = result.data.test;
      this.refs.ReviewWin.refs.ContractGenerate.setFieldsValue(test);
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
      url: '/modules/workflow/ProcessTaskController/queryContractTasks.htm?isCompleted=false',
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
  newContract() {
    var selectedrecord = this.state.selectedrecord;
    var me = this;
    Utils.ajaxData({
      url: '/modules/workflow/controller/ProcessTaskController/completeTask.htm',
      data: {
        taskId: selectedrecord.taskId,
        serviceVariables: JSON.stringify({
          "remarkComment": "00000",
          "consultId": selectedrecord.consultId,
          "nextStep": "pass",
          "processStateCode": selectedrecord.processStateCode,
          "projectId": selectedrecord.projectId,
          "processInstanceId": selectedrecord.processInstanceId
        }),
        processVariables: JSON.stringify({ "comment": "pass" })
      },
      callback: (result) => {
        if (result.code == 200) {
          Modal.info({
            title: '合同生成成功',
            onOk() {
              me.refreshList();
            }
          })
        }
      }
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
      onChange: this.onSelectChange,
    };
    var selectRecord = this.state.selectRecord;
    const hasSelected = selectedRowKeys.length > 0;
    var columns = [{
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
        title: '金融顾问/分销商名称',
        dataIndex: "customerManager"
      }, {
        title: '任务处理人',
        dataIndex: "executor"
      }, {
        title: '任务开始时间',
        dataIndex: "startTime"
      }
    ];
    var state = this.state;

    return (
      <div className="block-panel">
        <div className="actionBtns" style={{ marginBottom: 16 }}>
          {
            //   <button className="ant-btn" disabled={!hasSelected} onClick={this.showModal.bind(this, '合同签订', null, true) }>
            //   合同签订
            // </button>
          }
          <button className="ant-btn" disabled={!hasSelected} onClick={this.newContract}>
            处理
          </button>
          <a className="ant-btn" disabled={true} href={this.downLoad} style={{padding:'5px 17px'}}>
            下载
          </a>
        </div>
        <Table columns={columns} rowKey={this.rowKey}  size="middle"  params ={this.props.params}
          onRowClick={this.onRowClick}
          dataSource={this.state.data}
          pagination={this.state.pagination}
          loading={this.state.loading}
          onChange={this.handleTableChange}  />
        <ReviewWin ref="ReviewWin"  visible={state.visible}    title={state.title} hideModal={me.hideModal} record={state.record}
          canEdit={state.canEdit}/>
      </div>
    );
  }
})
