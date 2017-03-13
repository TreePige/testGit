import React from 'react'
import {
  Table,
  Modal
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
    if (roleId == "declarationStaff" || roleId == "customerServiceStaffB" || roleId == "system") {
      var ProjectBelongslist = [];
      Utils.ajaxData({
        url: '/modules/system/getProjectBelongInfo.htm',
        method: 'get',
        type: 'json',
        callback: (result) => {
          var data = result.data;
          if (data.projectBelongs == 1) {
            this.refs.ReviewWin.refs.BorrowingNeeds.setFieldsValue({ projectBelongs: data.projectBelongs });
          }
          if (data.projectBelongs == 2) {
            this.refs.ReviewWin.refs.BorrowingNeeds.setFieldsValue({ projectBelongs: data.projectBelongs });
            this.refs.ReviewWin.refs.BorrowingNeeds.setFieldsValue({ institutionName: data.institutionName });
          }
          if (data.projectBelongs == 3) {
            this.refs.ReviewWin.refs.BorrowingNeeds.setFieldsValue({ projectBelongs: data.projectBelongs });
          }
        }
      });
    }
    var formData = {};
    if (title == "申请进件") {
      Utils.ajaxData({
        url: '/modules/common/action/plConsultAction/getConsultById.htm',
        data: {
          processInstanceId: selectedrecord.processInstanceId
        },
        callback: (result) => {
          var id = result.data.housPropertyInformation.id
          result.data.housPropertyInformation.propertyProperties = String(result.data.housPropertyInformation.propertyProperties);
          result.data.housPropertyInformation.propertySituation = String(result.data.housPropertyInformation.propertySituation);
          result.data.housPropertyInformation.whetherOneContact = String(result.data.housPropertyInformation.whetherOneContact);
          result.data.housPropertyInformation.whetherTwoContact = String(result.data.housPropertyInformation.whetherTwoContact);
          result.data.housBorrowingBasics.marryStatus = String(result.data.housBorrowingBasics.marryStatus);
          result.data.plBorrowRequirement.repaymentType = String(result.data.plBorrowRequirement.repaymentType);
          result.data.plBorrowRequirement.projectBelongs = result.data.plBorrowRequirement.projectBelongs == undefined ? '' : result.data.plBorrowRequirement.projectBelongs;
          result.data.plBorrowRequirement.projectBelongs = String(result.data.plBorrowRequirement.projectBelongs);
          result.data.plBorrowRequirement.productId = String(result.data.plBorrowRequirement.productId);
          formData = this.transformeData(result.data.housAssessmentAgencies);
          this.refs.ReviewWin.refs.NewEvaOrg.setFieldsValue(formData);
          this.refs.ReviewWin.refs.HousingInformation.setFieldsValue(result.data.housPropertyInformation);
          if (roleId == "declarationStaff" || roleId == "customerServiceStaffB" || roleId == "system") {
            this.refs.ReviewWin.refs.BasiInformation.setFieldsValue(result.data.housBorrowingBasics);
            this.refs.ReviewWin.refs.BorrowingNeeds.setFieldsValue(result.data.plBorrowRequirement);
          }
          this.setState({
            id: id,
          });
        }
      });
    }
    this.setState({
      visible: true,
      title: title,
      canEdit: canEdit,
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
    if (roleId == "declarationStaff" || roleId == "customerServiceStaffB") {
      Utils.ajaxData({
        url: '/modules/workflow/ProcessTaskController/queryInstanceInfoTasks.htm?isCompleted=false',
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
    }
    if (roleId == "customerServiceStaffA") {
      Utils.ajaxData({
        url: '/modules/workflow/ProcessTaskController/queryInstanceTasks.htm?isCompleted=false',
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
    }
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
    console.log(window.roleId)
    var roleId = window.roleId;
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
        title: '来源',
        dataIndex: "businessOrigin"
      }, {
        title: '报单人',
        dataIndex: "customerManager"
      }, {
        title: '任务处理人',
        dataIndex: "executor"
      }, {
        title: '任务开始时间',
        dataIndex: "startTime"
      }];
    var state = this.state;

    var buttons = (
      <button className="ant-btn"  disabled={!hasSelected} onClick={this.showModal.bind(this, '申请进件', true) }>
        申请进件
      </button>
    );
    var buttonsq = (
      <button className="ant-btn"  onClick={this.showModal.bind(this, '新增申请', true) }>
        新增申请
      </button>
    );
    if (roleId == "customerServiceStaffA") {
      buttonsq = ""
    }
    if (roleId == "declarationStaff" || roleId == "customerServiceStaffB") {
      buttons = ""
    }
    return (
      <div className="block-panel">
        <div className="actionBtns" style={{ marginBottom: 16 }}>
          {buttonsq}
          {buttons}
        </div>
        <Table columns={columns} rowKey={this.rowKey}  size="middle"  params ={this.props.params}
          rowSelection={rowSelection}
          onRowClick={this.onRowClick}
          dataSource={this.state.data}
          pagination={this.state.pagination}
          loading={this.state.loading}
          onChange={this.handleTableChange}  />
        <ReviewWin ref="ReviewWin"  visible={state.visible}    title={state.title} hideModal={me.hideModal} record={state.selectedrecord} id={state.id} canEdit={state.canEdit}/>
      </div>
    );
  }
})
