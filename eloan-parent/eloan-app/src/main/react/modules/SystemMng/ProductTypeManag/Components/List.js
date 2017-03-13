import React from 'react'
import {
  Table,
  Modal
} from 'antd';
import ItemList from './ItemList'
import AddTypeWin from './AddTypeWin'
import AddProduct from './AddProduct'
var confirm = Modal.confirm;
const objectAssign = require('object-assign');
export default React.createClass({
  getInitialState() {
    return {
      selectedRowKeys: [], // 这里配置默认勾选列
      loading: false,
      data: [],
      pagination: {
              pageSize: 5,
              currentPage: 1},
      canEdit: true,
      visible: false,
      assignVisible:false
    };
  },
  componentWillReceiveProps(nextProps, nextState) {
    this.fetch(nextProps.params);
  },
  hideModal() {
    this.setState({
      visible: false,
      assignVisible:false
    });
    var pagination = this.state.pagination;
    this.refreshList();
  },
  //新增跟编辑弹窗
  showModal(title, record, canEdit) {
    var record = record;

    if (title == '修改') {
      var record = record;

      this.refs.AddProduct.setFieldsValue(record);
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
  //打开分配弹窗
  showTypeModal(title,canEdit) {
    this.setState({ 
      assignVisible: true,
      title: title,
      record: this.state.selectedRow
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
    const pager = this.state.pagination;
    pager.current = pagination.current;
    this.setState({
      pagination: pager,
    });
    this.refreshList();
  },
  componentDidMount() {
    this.fetch(this.props.params);
  },
  fetch(params={pageSize:5,currentPage:1}) {
    this.setState({
      loading: true
    }); 
    Utils.ajaxData({
      url: '/modules/paramconfig/ProductTypeAction/getProductTypeList.htm',
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
    var pagination = this.state.pagination;
    var params = objectAssign({}, this.props.params, {
      currentPage: pagination.current,
      pageSize: pagination.pageSize
    });
    this.fetch(params);
  },
  delete(record) {
    console.log(record)
    var me = this;
    var json={};
    json.id=record.id;
    json.deleteState=1;
    confirm({
      title: '是否确认要删除这项内容',
      onOk: function() {
        Utils.ajaxData({
          url: "/modules/paramconfig/ProductTypeAction/saveOrUpdateProductType.htm",
          data: {
            json:JSON.stringify(json),
            act:'update'
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
      onCancel: function() {}
    });
  },
  onRowClick(record) {
    this.setState({
      selectedRowKeys: [record.id],
      selectedRow:record
    });
  },
  render() {
    var me = this;
    const {
      loading,
      selectedRowKeys
    } = this.state;
    const rowSelection = {
      type: 'radio',
      selectedRowKeys,
      //onChange: this.onSelectChange,
    };
    const hasSelected = selectedRowKeys.length > 0;
    var columns = [{
       title: '产品类别名称',
        dataIndex: 'productType' 
      },{
        title: '贷款类型',
        dataIndex: 'ptype',
        render:function(value){
          return {
                    1:'信贷',
                    2:'车贷',
                    3:"房贷"
                 }[value];
        } 
      },{
        title: '是否启用',
        dataIndex: "state",
        render: function (value) {
          if(value==0){
            return "是"
          } 
          else if(value==1){
            return "否"
          }
          else return <span></span>;
        } 
      }, {
        title: '创建时间',
        dataIndex: 'createDate'
      }, {
      title: '操作',
      dataIndex: "",
      render(text, record) {
        return <div style={{textAlign: "left"}}><a href="#" onClick={me.showModal.bind(me,'修改',record,true)}>修改 </a><a href="#" onClick={me.delete.bind(me,record)}>删除 </a></div>
      }
    }];
    var state = this.state;
    console.log(state)
    return (
      <div className="block-panel">
           <div className="actionBtns" style={{ marginBottom: 16 }}>
             <div className="actionBtns" style={{ marginBottom: 16 }}>
               <button className="ant-btn" onClick={this.showModal.bind(this,'新增',true)}>
                 新增
               </button>
               <button className="ant-btn" disabled={!hasSelected} onClick={this.showTypeModal.bind(this,'批量添加',true)}>
                 批量添加
               </button>
             </div>  
           </div>
           <Table columns={columns} rowKey={this.rowKey}  size="middle"  params ={this.props.params} rowSelection={rowSelection} 
             dataSource={this.state.data} onRowClick={this.onRowClick} pagination={this.state.pagination} loading={this.state.loading}
             onChange={this.handleTableChange}  />
            <ItemList formulaId={state.selectedRow}/>
            <AddProduct ref="AddProduct"  visible={state.visible}    title={state.title} hideModal={me.hideModal} record={state.record} canEdit={state.canEdit}/> 
            <AddTypeWin ref="AddTypeWin"  visible={state.assignVisible}    title={state.title} hideModal={me.hideModal} selectRecord={state.record} canEdit={state.canEdit}/> 
         </div>
    );
  }
})
