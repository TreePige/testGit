import React from 'react'; 
import { Table,Tree,Modal,Form,Input,Button } from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const TreeNode = Tree.TreeNode; 
const objectAssign = require('object-assign');
var columns = [{
        title: '序号',
        dataIndex: 'id' 
      }, {
        title: '公式名称',
        dataIndex: 'chineseName' 
      }, {
        title: '参数名',
        dataIndex: 'englishName'
      }, {
        title: '公式',
        dataIndex: 'formulaChinese' 
      }, {
        title: '备注',
        dataIndex: 'note' 
      } ];
var AddTypeWin = React.createClass({
  getInitialState() {
    return {
      loading: false,
      data: [],
      pagination: {
        pageSize: 9999999,
        currentPage: 1},
      visible: false,
      params: {
        pageSize: 9999999,
        currentPage: 1
      },
      selectedRowKeys:[]
    };
  }, 
  rowKey(record) {
    return record.id;
  },
  //分页
  handleTableChange(pagination, filters, sorter) {
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
  componentWillReceiveProps(nextProp){
    if(nextProp.selectRecord){
      if (nextProp.selectRecord.formulaId) {
        var selectedRowKeys = nextProp.selectRecord.formulaId.split(',')
      } else{
        var selectedRowKeys=[]
      }
    }
    this.setState({
        selectedRowKeys:selectedRowKeys
      });
  }, 
  componentDidMount(){
    this.fetch();
  },
  fetch(params={pageSize:9999999,currentPage:1}) {
    this.setState({
      loading: true
    }); 
    Utils.ajaxData({
      url: '/modules/fel/FelFormulaAction/Select.htm',
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
      }
    });
  }, 
  passParams(params) {
    this.setState({
      params: params
    });
  },
  handleQuery() {
    var params = this.props.form.getFieldsValue();
    this.fetch({
      searchParams : JSON.stringify(params),
      pageSize: 9999999,
      currentPage: 1,
    })
  },
  handleCancel(){
      this.props.form.resetFields();
      this.props.hideModal();
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
  handleOk(e) {
        e.preventDefault();
        var me = this;
        var props = me.props;
        var record = props.record;
        var title = props.title;
        var selectRecord= this.props.selectRecord;
        var id = selectRecord.id;
        var formulaId=this.state.selectedRowKeys;
        var json={};
        json.id=id;
        json.formulaId=formulaId.join(',');
         Utils.ajaxData({
                url: '/modules/paramconfig/ProductTypeAction/saveOrUpdateProductType.htm',
                method: 'get', 
                type: 'json',
                data:{
                  json:JSON.stringify(json),
                  act: 'update'
                },
                callback: (result) => { 
                   if(result.msg=="操作成功")
                   {
                     Modal.success({
                       title: result.msg
                     });  
                     me.props.hideModal();
                   }
                   else  
                   {
                     Modal.error({
                       title: result.msg
                     });  
                   }
                }
              });  
  },
  render() {  
    const {
      getFieldProps
    } = this.props.form;
    const {
      loading,
      selectedRowKeys
    } = this.state;
    var state = this. state;  
    var status = state.status;
    var props = this.props;
    const rowSelection = {
      type: 'checkbox',
      selectedRowKeys,
      onSelectAll: this.onSelectAll,
    };
    return(
    <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="800"
      footer={[
          <button key="submit" className="ant-btn ant-btn-primary" loading={this.state.loading} onClick={this.handleOk}>
            确 定
          </button>,
          <button key="back" className="ant-btn" onClick={this.handleCancel}>取 消</button> 
        ]} >  
        <Form inline >
          <FormItem label="公式名称：">
            <Input  {...getFieldProps('chineseName', { initialValue: ''})} />
          </FormItem>
          <FormItem label="参数名称：">
            <Input  {...getFieldProps('englishName', { initialValue: ''})} />
          </FormItem>          
           <FormItem><Button type="primary" onClick={this.handleQuery}>查询</Button></FormItem>
           <FormItem><Button type="reset" onClick={this.handleReset}>重置</Button></FormItem> 
        </Form>
        <div style={{height:'450px'}} >
            <Table columns={columns} rowKey={this.rowKey}  size="middle"  params ={this.state.params} rowSelection={rowSelection} 
             dataSource={this.state.data} onRowClick={this.onRowClick} pagination={this.state.pagination} loading={this.state.loading} />
        </div> 
    </Modal> 
    )
  }
});
AddTypeWin = createForm()(AddTypeWin);
export default AddTypeWin;