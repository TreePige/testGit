//审批历史
import React from 'react'
import {
  Table,
  Modal
} from 'antd';
var confirm = Modal.confirm;
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
    this.fetch({
      pageSize: pagination.pageSize,
      currentPage: pagination.current
    });
  },
  componentDidMount() {
    this.fetch(this.props.params);
  },
  fetch(params = { pageSize: 5, currentPage: 1 }) {
    this.setState({
      loading: true
    });
    params.projectId=this.props.record.projectId;
    Utils.ajaxData({
      url: '/modules/workflow/controller/ProcessHistoryController/getHistory.htm',
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
    this.fetch();
  },
  onRowClick(record) {
    this.setState({
      selectedRowKeys: [record.id]
    });
  },
  render() {
    console.log(this.props)
    var me = this;
    const {
      loading,
      selectedRowKeys
    } = this.state;
    const rowSelection = {
      //type: 'radio',
      selectedRowKeys,
      //onChange: this.onSelectChange,
    };
    const hasSelected = selectedRowKeys.length > 0;
    var columns = [{
        title: '状态',
        dataIndex: "processState"
      }, {
        title: '备注',
        dataIndex: 'remark'
      }, {
        title: '时间',  
        dataIndex: "createTime"
      }];
    var state = this.state;
    return (
      <div style={{height:'350px',overflowY: "scroll"}}>     
        <div className="block-panel">
          <Table columns={columns} rowKey={this.rowKey}  size="middle"  params ={this.props.params} 
            rowSelection={rowSelection}
            dataSource={this.state.data} 
            onRowClick={this.onRowClick}
            pagination={this.state.pagination}
            loading={this.state.loading}
            onChange={this.handleTableChange} bordered />
        </div>
      </div>
    );
  }
})

