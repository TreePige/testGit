//基本信息
import React from 'react';
import {
  Button,
  Form,
  Input,
  Modal,
  Select,
  Row,
  Col,
  DatePicker
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const objectAssign = require('object-assign');
var Reflux = require('reflux');
var FormDataStore = require('./stores/FormDataStore');
const NavLine = require("../utils/NavLine");
//var UploadPhoto = require("./UploadPhoto");
/*var typeIdList = [];
Utils.ajaxData({
  url: '/getDicts.htm?type=BANK_TYPE',
  method: 'get',
  type: 'json',
  callback: (result) => {
    typeIdList = result.data;
  }
});*/
var AddInsuWin = React.createClass({
  mixins: [
    Reflux.listenTo(FormDataStore, "onMatch")
  ],
  onMatch(data) {
    console.log(data)
    if (data.formData) {
      this.props.form.setFieldsValue(data.formData);
    } else this.props.form.resetFields();
  },
  getInitialState() {
    return {
      status: {
        automaticWithholdingTime: ""
      },
      formData: {}
    };
  },
  handleReset() {
    this.refs.validation.reset();
    this.setState({
      status:{
        automaticWithholdingTime:""
      }
    })
  },
  componentDidMount() {
    var record = {};
    if (this.props.record) {
      var record = this.props.record;
    }
    this.props.form.setFieldsValue(record);
  },
  render() {
    const {
      getFieldProps
    } = this.props.form;
    var props = this.props;
    var state = this.state;
    const formItemLayout = {
      labelCol: {
        span: 8
      },
      wrapperCol: {
        span: 15
      },
    };
    return (
          <Form horizontal  form={this.props.form} style={{ marginTop: "20px" }}>
            <Input  {...getFieldProps('id', { initialValue: '' }) } type="hidden"   />
            <div id="s1">
              <Row>
                <Col span="12">
                  <FormItem  {...formItemLayout} label="押品编号：">
                    <Input disabled={!props.canEdit}  {...getFieldProps('chargeNumber') } type="text" autoComplete="off" />
                  </FormItem>
                </Col>
                <Col span="12">
                  <FormItem  {...formItemLayout} label="他项权利证名称：">
                    <Input disabled={!props.canEdit}  {...getFieldProps('hisRightCertificate') } type="text" autoComplete="off" />
                  </FormItem>
                </Col>
              </Row>
              <Row>
                <Col span="12">
                  <FormItem  {...formItemLayout} label="抵押权人：">
                    <Input disabled={!props.canEdit}  {...getFieldProps('mortgageRight') } type="text" autoComplete="off" />
                  </FormItem>
                </Col>
                <Col span="12">
                  <FormItem  {...formItemLayout} label="委托人：">
                    <Input disabled={!props.canEdit}  {...getFieldProps('client') } type="text" autoComplete="off" />
                  </FormItem>
                </Col>
              </Row>
              <Row>
                <Col span="12">
                  <FormItem  {...formItemLayout} label="领取时间：">
                      <DatePicker disabled={!props.canEdit}  {...getFieldProps('collectionTime', { rules: [{ required: true, message: '必填', type: "date" }] }) } style={{ width: "193px" }}/>
                  </FormItem>
                </Col>
                <Col span="12">
                  <FormItem  {...formItemLayout} label="登记人：">
                    <Select  disabled={!props.canEdit} {...getFieldProps('registeredPerson') } >
                      <Option value="0">有</Option>
                      <Option value="1">无</Option>
                    </Select>
                  </FormItem>
                </Col>
              </Row>
              <Row>
                <Col span="12">
                  <FormItem  {...formItemLayout} label="押品出库时间：">
                      <DatePicker disabled={!props.canEdit}  {...getFieldProps('borrowingTime', { rules: [{ required: true, message: '必填', type: "date" }] }) } style={{ width: "193px" }}/>
                  </FormItem>
                </Col>
                <Col span="12">
                  <FormItem  {...formItemLayout} label="出库人：">
                    <Input disabled={!props.canEdit}  {...getFieldProps('account') } type="text" autoComplete="off" />
                  </FormItem>
                </Col>
              </Row>
              <Row>
                <Col span="24">
                  <FormItem  labelCol={{span: 4}} wrapperCol={{span: 19}} label="备注：">
                      <Input disabled={!props.canEdit} rows="3" type="textarea"  {...getFieldProps('remark')} autoComplete="off"/>
                  </FormItem>
                </Col>
              </Row>
            </div>
          </Form>
    );
  }
});
AddInsuWin = createForm()(AddInsuWin);
export default AddInsuWin;
