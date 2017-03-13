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
const Option = Select.Option;
const FormItem = Form.Item;
var Region = require('../plugin/Region');
var MarryStatuslist = [];
Utils.ajaxData({
  url: '/modules/common/action/ComboAction/queryDic.htm',
  method: 'get',
  data: {
    typeCode: "MARITAL_STATUS"
  },
  type: 'json',
  callback: (result) => {
    MarryStatuslist = result.data;
  }
});
var HousingInformation = React.createClass({
  getInitialState() {
    return {
      residentialAddressId: '110101',
    }
  },
  changeAreaId(name, areaId) {
    this.setState({
      [name]: areaId
    })
  },
  handleReset() {
    this.refs.validation.reset();
    this.setState(this.getInitialState());
  },
  getMarryStatuslist() {
    return MarryStatuslist.map((item, index) => {
      return <Option key={item.value} >{item.text}</Option>
    })
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
        <Row>
          <Col span="7">
            <FormItem  {...formItemLayout} label="贷款人姓名：">
              <Input disabled={!props.canEdit}  {...getFieldProps('name', { rules: [{ required: true, message: '必填' }] }) }  type="text" autoComplete="off" />
            </FormItem>
          </Col>
          <Col span="7">
            <FormItem  {...formItemLayout} label="贷款人身份证号：">
              <Input disabled={!props.canEdit}  {...getFieldProps('certNumber', { rules: [{ required: true, message: '必填',pattern:/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/ }] }) } type="text" autoComplete="off" />
            </FormItem>
          </Col>
          <Col span="7">
            <FormItem  {...formItemLayout} label="贷款人联系电话：">
              <Input disabled={!props.canEdit}  {...getFieldProps('phone') } type="text" autoComplete="off" />
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="7">
            <FormItem  {...formItemLayout}  label="婚姻状况：">
              <Select disabled={!props.canEdit} {...getFieldProps('marryStatus', { rules: [{ required: true, message: '必填' }] }) } >
                {this.getMarryStatuslist() }
              </Select>
            </FormItem>
          </Col>
          <Col span="7">
            <FormItem  {...formItemLayout} label="配偶姓名：">
              <Input disabled={!props.canEdit}  {...getFieldProps('spouseName') } type="text" autoComplete="off" />
            </FormItem>
          </Col>
          <Col span="7">
            <FormItem  {...formItemLayout} label="配偶身份证号：">
              <Input disabled={!props.canEdit}  {...getFieldProps('spouseCertNumber') } type="text" autoComplete="off" />
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="7">
            <FormItem  {...formItemLayout}  label="配偶联系电话：">
              <Input disabled={!props.canEdit}  {...getFieldProps('spousePhone') } type="text" autoComplete="off" />
            </FormItem>
          </Col>
          <Col span="7">
            <FormItem  {...formItemLayout} label="共借人：">
              <Input disabled={!props.canEdit}  {...getFieldProps('totalBorrowed') } type="text" autoComplete="off" />
            </FormItem>
          </Col>
          <Col span="7">
            <FormItem  {...formItemLayout} label="共借人身份证号：">
              <Input disabled={!props.canEdit}  {...getFieldProps('totalBorrowedCertNumber') } type="text" autoComplete="off" />
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="14">
            <FormItem  labelCol={{ span: 4 }} wrapperCol={{ span: 17 }} label="居住地(省)：">
              <Region {...getFieldProps('residentialAddressId', { initialValue: this.state.residentialAddressId, onChange: this.changeAreaId.bind(this, "residentialAddressId") }) }   disabled={!this.props.canEdit}/>
            </FormItem>
          </Col>
          <Col span="4" >
            <FormItem style ={{ marginLeft: "-84px" }}>
              <Input disabled={!props.canEdit} placeholder="详细地址"  {...getFieldProps('residentialAddress') } type="text" autoComplete="off" />
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="19">
            <FormItem  {...formItemLayout} label="备注："  labelCol={{ span: "3" }}>
              <Input disabled={!props.canEdit}  {...getFieldProps('remark') } type="textarea" autoComplete="off" />
            </FormItem>
          </Col>
        </Row>
      </Form >
    )
  }
});
HousingInformation = createForm()(HousingInformation);
export default HousingInformation;
