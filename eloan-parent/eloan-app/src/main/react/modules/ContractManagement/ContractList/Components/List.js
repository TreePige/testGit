
import React from 'react'
import {
  Table,
  Modal
} from 'antd';
import ContractWin from './ContractWin'
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
  //新增跟编辑弹窗
  showModal(title, record, canEdit) {
    var record = record;
        Actions.setFormData(record)
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
      url: '/modules/channel/FarcLoanContractAction/getFarcLoanContractList.htm',
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
      selectedRowKeys: selectedRowKeys
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
      type: 'checkbox',
      selectedRowKeys,
      onChange: this.onSelectChange,
    };
    const hasSelected = selectedRowKeys.length > 0;
    var columns = [{
        title: '项目编号',
        dataIndex: 'loanAgreementNumber'
      }, {
        title: '项目名称',
        dataIndex: "channelName"
      }, {
        title: '客户名称',
        dataIndex: 'contractCode'
      }, {
        title: '贷款金额',
        dataIndex: "contractName"
      }, {
        title: '贷款期限',
        dataIndex: "signedWay" 
      }, {
        title: '金融顾问/分销商名称',
        dataIndex: "electronSignMark"
      }, {
        title: '任务处理人',
        dataIndex: "custSignedTime"
      }, {
        title: '任务开始时间',
        dataIndex: "contractCifceTime"
      }, {
        title: '操作',
        dataIndex: "",
        render(text, record) {
          return <div style={{textAlign: "left"}}><a href="#" onClick={me.showModal.bind(me,'查看',record,false)}>查看 </a></div>
        }
      }
    ];
    var state = this.state;
    return (
      <div className="block-panel">
           <div className="actionBtns" style={{ marginBottom: 16 }}>
            <button className="ant-btn" onClick={this.showModal.bind(this, '合同下载', null, true) }>
              合同下载
            </button>
            <button className="ant-btn" onClick={this.showModal.bind(this, '合同查看', null, true) }>
              合同查看
            </button>
           </div>
           <Table columns={columns} rowKey={this.rowKey}  size="middle"  params ={this.props.params} 
             onRowClick={this.onRowClick}
             dataSource={this.state.data}
             pagination={this.state.pagination}
             loading={this.state.loading}
             onChange={this.handleTableChange}  />
            <ContractWin ref="ContractWin"  visible={state.visible}    title={state.title} hideModal={me.hideModal} record={state.record}
             canEdit={state.canEdit}/> 
         </div>
    );
  }
})
