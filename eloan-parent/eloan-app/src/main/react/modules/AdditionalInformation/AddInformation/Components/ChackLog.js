import React from 'react';
import {
  Button,
  Form,
  Input,
  Modal,
  Icon,
  Tag,
  Select,
  Cascader,
  Tree,
  TreeSelect,
  Row,
  Table,
  Col
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const objectAssign = require('object-assign');
var UploadPhoto = require("../../../CommonForm/UploadPhoto");

var typeIdList = [];
Utils.ajaxData({
  url: '/modules/fel/FeltypeAction/SelectAll.htm',
  method: 'get',
  type: 'json',
  callback: (result) => {
    typeIdList = result.data;
  }
});

var tagOptions = [];
Utils.ajaxData({
  url: '/modules/fel/FelParamAction/formulaQuery.htm',
  method: 'get',
  type: 'json',
  callback: (result) => {
    tagOptions = result.data;
  }
});

var AddFormula = React.createClass({
  getInitialState() {
    return {
      tags: [],
      status: {},
      formData: {}
    };
  },
  handleCancel() {
    this.setState({
      tags: []
    });
    this.props.form.resetFields();
    this.props.hideModal();
  },
  onChange(value, selectedOptions) {
    var newValue = this.state.tags;
    var tag = selectedOptions[1];
    if (selectedOptions[0].label == '运算符' || selectedOptions[0].label == '数字') {
      tag.isOperator = 1;
    } else tag.isOperator = 0;
    newValue.push(tag);
    this.setState({
      tags: newValue,
    });
  },
  handleClose(key) {
    const tags = [...this.state.tags].filter((tag, index) => (index !== key) && tag);
    console.log(tags);
    this.setState({
      tags
    });
  },
  
  gettypeIdList() {
    return typeIdList.map((item, index) => {
      return <Option key={item.value} >{item.text}</Option>
    })
  },
 
  render() {
    
    const {
      getFieldProps
    } = this.props.form;
    var props = this.props;
    var state = this.state;
    var modalBtns = [
      <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
      <button key="button" className="ant-btn ant-btn-primary"  onClick={this.handleOk}>
          提 交
      </button>
    ];
    if (!props.canEdit) {
      modalBtns = [
        <button key="back" className="ant-btn" onClick={this.handleCancel}>关闭</button>
      ];
    }
    const formItemLayout = {
      labelCol: {
        span: 8
      },
      wrapperCol: {
        span: 15
      },
    };
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
    var columns = [{
        title: '处理人',
        dataIndex: "processState"
      }, {
        title: '流程状态',
        dataIndex: 'remark'
      }, {
        title: '处理时间',  
        dataIndex: "createTime"
      }, {
        title: '操作',  
        dataIndex: ""
      }];
    var state = this.state;
    return (
      <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="900"  footer={modalBtns} >
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
      </Modal>
    );
  }
});
AddFormula = createForm()(AddFormula);
export default AddFormula;