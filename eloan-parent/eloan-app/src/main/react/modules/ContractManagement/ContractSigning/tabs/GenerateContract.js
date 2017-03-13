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
var MarryStatuslist = [];
var HousingInformation = React.createClass({
  getInitialState() {
    return {
      residentialAddressId: '110101',
    }
  },
  getMarryStatuslist() {
    return MarryStatuslist.map((item, index) => {
      return <Option key={item.value} >{item.text}</Option>
    })
  },
  disabledStartDate2(custSignedTimeBegintime) {
    if (!custSignedTimeBegintime || !this.state.custSignedTimeEndtime) {
      return false;
    }
    return custSignedTimeBegintime.getTime() >= this.state.custSignedTimeEndtime.getTime();
  },
  disabledEndDate2(custSignedTimeEndtime) {
    if (!custSignedTimeEndtime || !this.state.custSignedTimeBegintime) {
      return false;
    }
    return custSignedTimeEndtime.getTime() <= this.state.custSignedTimeBegintime.getTime();
  },
  onChange(field, value) {
    console.log(field, 'change', value);
    this.setState({
      [field]: value,
    });
  },
  onStartChange2(value) {
    this.onChange('custSignedTimeBegintime', value);
  },
  onEndChange2(value) {
    this.onChange('custSignedTimeEndtime', value);
  },
  handleStartToggle({ open }) {
    if (!open) {
      this.setState({ endOpen: true });
    }
  },
  handleEndToggle({ open }) {
    this.setState({ endOpen: open });
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
        <h2 style={{ marginTop: "10px" }}>基本信息</h2>
        <Row>
          <Col span="6">
            <FormItem  {...formItemLayout} label="甲方姓名：">
              <Input disabled={true}   {...getFieldProps('phone') } type="text" autoComplete="off" />
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="6">
            <FormItem  {...formItemLayout}  label="甲方身份证：">
             <Input disabled={true}   {...getFieldProps('phone') } type="text" autoComplete="off" />
            </FormItem>
          </Col>
          <Col span="6">
            <FormItem  {...formItemLayout} label="借款金额（元）：">
              <Input disabled={true}   {...getFieldProps('spouseName') } type="text" autoComplete="off" />
            </FormItem>
          </Col>
          <Col span="6">
            <FormItem  {...formItemLayout} label="评估金额（元）：">
              <Input disabled={true}   {...getFieldProps('spouseCertNumber') } type="text" autoComplete="off" />
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="6">
            <FormItem  {...formItemLayout}  label="居住地址：">
              <Input disabled={true}   {...getFieldProps('spousePhone') } type="text" autoComplete="off" labelCol={{ span: 4 }}/>
            </FormItem>
          </Col>
        </Row>

      </Form>
    )
  }
});
HousingInformation = createForm()(HousingInformation);
export default HousingInformation;