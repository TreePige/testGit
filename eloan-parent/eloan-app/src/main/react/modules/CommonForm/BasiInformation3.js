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
var typeIdList = [];
Utils.ajaxData({
  url: '/getDicts.htm?type=BANK_TYPE',
  method: 'get',
  type: 'json',
  callback: (result) => {
    typeIdList = result.data;
  }
});
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
        automaticWithholdingTime:""
      },
      formData: {}
    };
  },
  handleCancel() {
    this.props.form.resetFields();
    this.props.hideModal();
  },
  onChange(field, value) {
    console.log(field, 'change', value);
    this.setState({
      [field]: value,
    });
  },
  onStartChange(value) {
    this.onChange('automaticWithholdingTime', value);
  },
  handleStartToggle({ open }) {
    if (!open) {
      this.setState({ endOpen: true });
    }
  }, 
  componentDidMount() {
    var record = {};
    if (this.props.record) {
      var record = this.props.record;
    }
    this.props.form.setFieldsValue(record);
  },
  gettypeIdList() {
    return typeIdList.map((item, index) => {
      return <Option key={item.value} >{item.text}</Option>
    })
  },
  navLineData: {
    "房产信息": "#s1",
    "基本信息": "#s2",
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
      <div style={{ position: "relative" }}>
        <div className="navLine-wrap" onScroll={NavLineUtils._handleSpy.bind(this,this.navLineData)}>
            <div className="col-22 navLine-wrap-left" >
              <Form horizontal  form={this.props.form} style={{ marginTop: "20px" }}>
                <Input  {...getFieldProps('id', { initialValue: '' }) } type="hidden"   />
                  <div id="s1">
                    <h2>房产信息</h2>
                      <Row>
                        <Col span="8">
                          <FormItem  {...formItemLayout} label="房产地址：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('name') } type="text" autoComplete="off" />
                          </FormItem>
                        </Col>
                        <Col span="8">
                          <FormItem  {...formItemLayout} label="面积：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('idcard') } type="text" autoComplete="off" />
                          </FormItem>
                        </Col>
                      </Row>
                      <Row>
                        <Col span="8">
                          <FormItem  {...formItemLayout} label="房产性质：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('email') } type="text" autoComplete="off" />
                          </FormItem>
                        </Col>
                        <Col span="8">
                          <FormItem  {...formItemLayout} label="房屋现状：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('city') } type="text" autoComplete="off" />
                          </FormItem>
                        </Col>
                      </Row>
                      <Row>
                        <Col span="8">
                          <FormItem  {...formItemLayout} label="是否一抵：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('creditItgaHgstLine') } type="text" autoComplete="off" />
                          </FormItem>
                        </Col>
                        <Col span="8">
                          <FormItem  {...formItemLayout} label="抵押权人：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('creditcardCnt') } type="text" autoComplete="off" />
                          </FormItem>
                        </Col>
                        <Col span="8">
                          <FormItem  {...formItemLayout} label="金额：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('n3mEnchashCnt') } type="text" autoComplete="off" />
                          </FormItem>
                        </Col>
                      </Row>
                      <Row>
                        <Col span="8">
                          <FormItem  {...formItemLayout} label="是否二抵：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('n3mPaymentRate') } type="text" autoComplete="off" />
                          </FormItem>
                        </Col>
                        <Col span="8">
                          <FormItem  {...formItemLayout} label="抵押权人：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('n3mUtilizValue') } type="text" autoComplete="off" />
                          </FormItem>
                        </Col>
                        <Col span="8">
                          <FormItem  {...formItemLayout} label="金额：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('n3mMaxUtiliz') } type="text" autoComplete="off" />
                          </FormItem>
                        </Col>
                      </Row>
                  </div>
                  <div id="s2">
                    <h2>基本信息</h2>
                      <Row>
                        <Col span="8">
                          <FormItem  {...formItemLayout} label="贷款人姓名：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('n3mPaymentRate') } type="text" autoComplete="off" />
                          </FormItem>
                        </Col>
                        <Col span="8">
                          <FormItem  {...formItemLayout} label="贷款人身份证号：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('n3mUtilizValue') } type="text" autoComplete="off" />
                          </FormItem>
                        </Col>
                        <Col span="8">
                          <FormItem  {...formItemLayout} label="贷款人联系电话：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('n3mMaxUtiliz') } type="text" autoComplete="off" />
                          </FormItem>
                        </Col>
                        <Col span="8">
                          <FormItem  {...formItemLayout} label="婚姻状况：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('n3mMaxUtiliz') } type="text" autoComplete="off" />
                          </FormItem>
                        </Col>
                      </Row>
                      <Row>
                        <Col span="8">
                          <FormItem  {...formItemLayout} label="配偶姓名：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('n3mPaymentRate') } type="text" autoComplete="off" />
                          </FormItem>
                        </Col>
                        <Col span="8">
                          <FormItem  {...formItemLayout} label="配偶身份证号：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('n3mUtilizValue') } type="text" autoComplete="off" />
                          </FormItem>
                        </Col>
                        <Col span="8">
                          <FormItem  {...formItemLayout} label="配偶联系电话：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('n3mMaxUtiliz') } type="text" autoComplete="off" />
                          </FormItem>
                        </Col>
                        <Col span="8">
                          <FormItem  {...formItemLayout} label="共借人：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('n3mMaxUtiliz') } type="text" autoComplete="off" />
                          </FormItem>
                        </Col>
                      </Row>
                      <Row>
                        <Col span="8">
                          <FormItem  {...formItemLayout} label="共借人身份证号：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('n3mPaymentRate') } type="text" autoComplete="off" />
                          </FormItem>
                        </Col>
                        <Col span="8">
                          <FormItem  {...formItemLayout} label="居住地(省）：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('n3mUtilizValue') } type="text" autoComplete="off" />
                          </FormItem>
                        </Col>
                      </Row>
                  </div>
              </Form>
            </div>
            <div className="navLine-wrap-right" >
                <NavLine navLineData={this.navLineData} activeItem="#s1" ref="NavLine"/>
            </div>
        </div>
      </div>
    );
  }
});
AddInsuWin = createForm()(AddInsuWin);
export default AddInsuWin;
