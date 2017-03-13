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
var Reflux = require('reflux');
var UploadPhoto = require("./UploadPhoto");

var AddInsuWin = React.createClass({

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
                  <FormItem  {...formItemLayout} label="客户姓名：">
                    <Input disabled={true}  {...getFieldProps('customerName') } type="text" autoComplete="off" />
                  </FormItem>
                </Col>
                <Col span="12">
                  <FormItem  {...formItemLayout} label="借款金额：">
                    <Input disabled={true}  {...getFieldProps('account') } type="text" autoComplete="off" />
                  </FormItem>
                </Col>
              </Row>
              <Row>
                <Col span="12">
                  <FormItem  {...formItemLayout} label="借款期限：">
                    <Input disabled={true}  {...getFieldProps('timeLimit') } type="text" autoComplete="off" />
                  </FormItem>
                </Col>
                <Col span="12">
                  <FormItem  {...formItemLayout} label="有无抵押：">
                    <Select  disabled={!props.canEdit} {...getFieldProps('mortgage') } >
                      <Option value={1}>有</Option>
                      <Option value={0}>无</Option>
                    </Select>
                  </FormItem>
                </Col>
              </Row>
              <Row>
                <Col span="12">
                  <FormItem  {...formItemLayout} label="有无查封:">
                    <Select  disabled={!props.canEdit} {...getFieldProps('seizure') } >
                      <Option value={1}>有</Option>
                      <Option value={0}>无</Option>
                    </Select>
                  </FormItem>
                </Col>
                <Col span="12">
                  <FormItem  {...formItemLayout} label="有无业务占用:">
                    <Select  disabled={!props.canEdit} {...getFieldProps('businessOccupancy') } >
                      <Option value={1}>有</Option>
                      <Option value={0}>无</Option>
                    </Select>
                  </FormItem>
                </Col>
              </Row>
              <Row>
                <Col span="12">
                  <FormItem  {...formItemLayout} label="有无网签占用:">
                    <Select  disabled={!props.canEdit} {...getFieldProps('netSignedOccupancy') } >
                      <Option value={1}>有</Option>
                      <Option value={0}>无</Option>
                    </Select>
                  </FormItem>
                </Col>
              </Row>
              <Row>
                <Col span="12">
                  <FormItem  {...formItemLayout} label="卡号:">
                    <Input disabled={!props.canEdit}  {...getFieldProps('creditCardNumber') } type="text" autoComplete="off" />
                  </FormItem>
                </Col>
                <Col span="12">
                  <UploadPhoto ref="RoomThisPhoto" title="" btype="CardNumber" canEdit={props.canEdit} selectRecord={props.record}/>
                </Col>
              </Row>
              <Row>
                <Col span="12">
                  <FormItem  {...formItemLayout} label="开户行:">
                    <Input disabled={!props.canEdit}  {...getFieldProps('bankAccount') } type="text" autoComplete="off" />
                  </FormItem>
                </Col>
                <Col span="12">
                  <UploadPhoto ref="RoomThisPhoto" title="" btype="BankAccount" canEdit={props.canEdit} selectRecord={props.record}/>
                </Col>
              </Row>
              <Row>
                <Col span="12">
                  <FormItem  {...formItemLayout} label="开户网点:">
                    <Input disabled={!props.canEdit}  {...getFieldProps('accountOpening') } type="text" autoComplete="off" />
                  </FormItem>
                </Col>
              </Row>
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
                    <DatePicker disabled={!props.canEdit} {...getFieldProps('custSignedTimeEndtime') } />
                  </FormItem>
                </Col>
                <Col span="12">
                  <FormItem  {...formItemLayout} label="登记人：">
                    <Input disabled={!props.canEdit}  {...getFieldProps('registeredPerson') } type="text" autoComplete="off" />
                  </FormItem>
                </Col>
              </Row>
              <Row>
                <Col span="12">
                  <UploadPhoto ref="RoomThisPhoto" title="抵押回执单" btype="MortgageReceipt" canEdit={props.canEdit} selectRecord={props.record}/>
                </Col>
                <Col span="12">
                  <UploadPhoto ref="RoomThisPhoto" title="定位截屏" btype="PositioningScreenshot" canEdit={props.canEdit} selectRecord={props.record}/>
                </Col>
              </Row>
            </div>
          </Form>
    );
  }
});
AddInsuWin = createForm()(AddInsuWin);
export default AddInsuWin;
