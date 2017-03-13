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
    return (
      <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="900"  footer={modalBtns} >
          <Form horizontal  form={this.props.form}>
                <Input  {...getFieldProps('id',  {initialValue:''})} type="hidden"   />
                <Input  {...getFieldProps('paramId',  {initialValue:''})} type="hidden"   />
            <Row>
              <Col span="12">
                <FormItem  {...formItemLayout} label="客户姓名：">
                  <Input disabled={!props.canEdit}  {...getFieldProps('id')} type="text" autoComplete="off" />
                </FormItem> 
              </Col>
               <Col span="12">
                <FormItem  {...formItemLayout} label="借款金额：">
                  <Input disabled={!props.canEdit} {...getFieldProps('chineseName')} type="text"/>
                </FormItem> 
              </Col>
            </Row>
            <Row>
              <Col span="12">
                <FormItem  {...formItemLayout} label="借款期限：">
                  <Input disabled={!props.canEdit} {...getFieldProps('chineseName')} type="text"/>
                </FormItem> 
              </Col>
            </Row>
            <Row>
              <Col span="12">
                <FormItem  {...formItemLayout} label="他项权利证明："> 
                  <Input disabled={!props.canEdit} {...getFieldProps('englishName')} type="text"/>
                  
                </FormItem>
              </Col>
              <Col span="12">
                 <UploadPhoto ref="RoomThisPhoto" title="房本照片" btype="HOUSE" canEdit={false} selectRecord={props.record}/>
              </Col>
            </Row>
            <Row>
             <Col span="12">
                <FormItem  {...formItemLayout} label="公证材料："> 
                   <UploadPhoto ref="RoomThisPhoto" title="房本照片" btype="HOUSE" canEdit={false} selectRecord={props.record}/>
                </FormItem>
              </Col>
            </Row>
            <Row>
            </Row>
            <Row>
              <Col span="12">
                <FormItem  {...formItemLayout} label="备注：">
                  <Input disabled={!props.canEdit}  {...getFieldProps('note')} style={{width:"700px",height:"100px"}}/>
                </FormItem> 
              </Col>
            </Row>
          </Form>
      </Modal>
    );
  }
});
AddFormula = createForm()(AddFormula);
export default AddFormula;