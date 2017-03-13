import React from 'react'
import {
  Table,
  Modal
} from 'antd';
import AddInstitution from './AddInstitution'
var confirm = Modal.confirm;
export default React.createClass({
  getInitialState() {
    return {
      selectedRows: [], // 这里配置默认勾选列
      loading: false,
      data: [],
      expandedRowKeys: [],
      canEdit: true,
      visible: false,
    };
  },
  componentDidMount() {
    this.fetch();
  },
  rowKey(record) {
    return record.value;
  },
  fetch(params = {
    pageSize: 10,
    currentPage: 1
  }) {
    this.setState({
      loading: true
    });
    Utils.ajaxData({
      url: '/modules/system/fetchAllOffice.htm',
      data: {
        node: 0
      },
      callback: (result) => {
        var data = result.data;
        this.setState({
          loading: false,
          data: data,
        });
        this.clearList();
      }
    });
  },
  refreshList() {
    this.fetch();
  },
  showModal(title, canEdit) {
    var record = this.state.selectedRows[0];
    Utils.ajaxData({
      url: '/modules/system/getOfficeInfoById.htm',
      data: {
        id: record.value
      },
      callback: (result) => {
        if (title == '修改机构') {
          this.refs.AddInstitution.setFieldsValue(result.data);
        } else if (title == '增加机构') {
          this.refs.AddInstitution.setFieldsValue({
            parentId: record.value
          });
        }
        this.setState({
          canEdit: canEdit,
          visible: true,
          title: title
        });
      }
    });
  },
  hideModal() {
    this.setState({
      visible: false
    });

    this.fetch();
  },
  getExpandeRowKeys(data) {
    var me = this;
    var expandedRowKeys = [];

    data.length && data.forEach(function (record, i) {
      if (record.children) {
        expandedRowKeys.push(me.rowKey(record));
        expandedRowKeys = expandedRowKeys.concat(me.getExpandeRowKeys(record.children));
      }
    });

    return expandedRowKeys;
  },

  expandedAll() {
    var data = this.state.data;
    var expandedRowKeys = this.getExpandeRowKeys(data);

    function sortNumber(a, b) {
      return a - b
    }
    this.setState({
      expandedRowKeys: expandedRowKeys.sort(sortNumber).reverse()
    });
    console.log(expandedRowKeys.sort(sortNumber).reverse());
  },
  collapseAll() {
    this.setState({
      expandedRowKeys: []
    });
  },
  onExpandedRowsChange(expandedRowKeys) {
    this.setState({
      expandedRowKeys: expandedRowKeys
    });
  },
  clearList() {
    this.setState({
      selectedRowKeys: [],
    });
  },
  delete(record) {
    var me = this;
    var id = record.value;

    confirm({
      title: '是否缺认要删除这项内容',
      onOk: function () {
        Utils.ajaxData({
          url: "/modules/system/general/officeDelete.htm",
          data: {
            id: id
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
  onRowClick(record) { 
    this.setState({
      selectedRowKeys: [record.value],
      selectedRows:[record]
    });
  },
  render() {
    var me = this;
    var state = this.state;
    const {
      selectedRows,
      selectedRowKeys
    } = this.state;
    const hasSelected = selectedRows.length > 0;
    const rowSelection = {
      selectedRowKeys,
    };
    let openEdit = true;
    if (hasSelected && selectedRowKeys.indexOf("0") === -1) {
      openEdit = false;
    }
    var columns = [{
      title: '机构名称',
      dataIndex: 'label'
    }, {
        title: '备注',
        dataIndex: 'remark'
      }, {
        title: '排序',
        dataIndex: "sort"
      }, {
        title: '操作',
        dataIndex: "",
        render(text, record) {
          return <div style={{ textAlign: "left" }}><a href="#" onClick={me.delete.bind(me, record) }>删除 </a></div>
        }
      }];
    return (
      <div className="block-panel">
        <div className="actionBtns" style={{ marginBottom: 16 }}>
          <button className="ant-btn" onClick={this.expandedAll.bind(this, '展开所有', false) }>
            展开所有
          </button>
          <button className="ant-btn" onClick={this.collapseAll.bind(this, '收缩所有', false) }>
            收缩所有
          </button>
          <button className="ant-btn" onClick={this.refreshList}>
            刷新
          </button>
          <button className="ant-btn" disabled={openEdit} onClick={this.showModal.bind(this, '修改机构', true) }>
            修改机构
          </button>
          <button className="ant-btn" disabled={!hasSelected} onClick={this.showModal.bind(this, '增加机构', true) }>
            增加机构
          </button>
        </div>
        <Table columns={columns} rowKey={this.rowKey} rowSelection={rowSelection} size="middle"  ref="table" pagination={false}
          onRowClick={this.onRowClick}
          dataSource={this.state.data}
          expandedRowKeys={this.state.expandedRowKeys}
          onExpandedRowsChange={this.onExpandedRowsChange}
          loading={this.state.loading} />
        <AddInstitution ref="AddInstitution"  visible={state.visible}  officeData={this.state.data}  title={state.title} hideModal={me.hideModal} record={state.record}
          canEdit={state.canEdit}/>
      </div>
    );
  }
})