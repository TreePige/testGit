import React from 'react'
import {
  Table,
  Modal
} from 'antd';
import ReviewWin from '../ReviewWin';
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
    var ProjectBelongslist = [];
    Utils.ajaxData({
      url: '/modules/system/getProjectBelongInfo.htm',
      method: 'get',
      type: 'json',
      callback: (result) => {
        var data = result.data;
        console.log(data.projectBelongs)
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
    if (title == "查看") {
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
          // result.data.plBorrowRequirement.projectBelongs = String(result.data.plBorrowRequirement.projectBelongs);
          result.data.plBorrowRequirement.productId = String(result.data.plBorrowRequirement.productId);
          formData = this.transformeData(result.data.housAssessmentAgencies);
          this.refs.ReviewWin.refs.NewEvaOrg.setFieldsValue(formData);
          this.refs.ReviewWin.refs.HousingInformation.setFieldsValue(result.data.housPropertyInformation);
          this.refs.ReviewWin.refs.BasiInformation.setFieldsValue(result.data.housBorrowingBasics);
          this.refs.ReviewWin.refs.BorrowingNeeds.setFieldsValue(result.data.plBorrowRequirement);
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
      url: '/modules/workflow/ProcessTaskController/queryConnectionTasks.htm?isCompleted=true',
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
  //选择
  /*onSelectAll(selected, selectedRowKeys, selectedRows, changeRows) {
    if (selected) {
      this.setState({
        selectedRowKeys
      })
    } else {
      this.setState({
        selectedRowKeys: []
      })
    }
  },*/
  render() {
    var me = this;
    const {
      loading,
      selectedRowKeys
    } = this.state;
    const rowSelection = {
      selectedRowKeys
    };
    const hasSelected = selectedRowKeys.length > 0;
    var columns = [{
      title: '项目编号',
      dataIndex: 'projectCode'
    }, {
        title: '项目名称',
        dataIndex: 'projectName'
      }, {
        title: '客户名称',
        dataIndex: 'customerName'
      }, {
        title: '报单人',
        dataIndex: "customerManager"
      }, {
        title: '任务处理人',
        dataIndex: "executor"
      }, {
        title: '流程状态',
        dataIndex: "currentProcessStateCode"
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
          <button className="ant-btn" disabled={!hasSelected} onClick={this.showModal.bind(this, '查看', false) }>
            查看
          </button>
        </div>
        <Table columns={columns} rowKey={this.rowKey}  size="middle"  params ={this.props.params}
          rowSelection={rowSelection}
          onRowClick={this.onRowClick}
          dataSource={this.state.data}
          pagination={this.state.pagination}
          loading={this.state.loading}
          onChange={this.handleTableChange}  />
        <ReviewWin ref="ReviewWin"  visible={state.visible}  title={state.title} hideModal={me.hideModal} id={state.id} record={state.selectedrecord} canEdit={state.canEdit}/>
      </div>
    );
  }
})
