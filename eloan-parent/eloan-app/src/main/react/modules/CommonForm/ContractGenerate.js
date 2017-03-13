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
import ShowuploadWin from '../ContractManagement/ContractSigning/tabs/ShowuploadWin'
var MarryStatuslist = [];
var HousingInformation = React.createClass({
  getInitialState() {

    return {
      residentialAddressId: '110101',
      visibleUp: false,
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
    hideModal() {
    this.setState({
      visibleUp: false
    });
  },
  upload() {

  },
  showupload(title, canEdit) {
    this.setState({
      visibleUp: true,
      title: title,
      canEdit: canEdit,
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
        <Button type="primary" style={{ marginRight: 10 }} onClick={this.props.showTab}>合同生成</Button>
        <Button type="primary" style={{ marginRight: 10 }} >下载合同</Button>
        <Button type="primary" style={{ marginRight: 10 }}   onClick={this.showupload.bind(this, '上传合同', true) }>上传合同</Button>
        <ShowuploadWin   visible={state.visibleUp}  hideModal={this.hideModal}  title={state.title}
          canEdit={state.canEdit}/>
        <h2 style={{ marginTop: "10px" }}>基本信息</h2>
        <Row>
          <Col span="6">
            <FormItem  {...formItemLayout} label="协议编号：">
              <Input disabled={true}  {...getFieldProps('name', { rules: [{ required: true, message: '必填' }] }) }  type="text" autoComplete="off" />
            </FormItem>
          </Col>
          <Col span="6">
            <FormItem  {...formItemLayout} label="签署日期：">
              <DatePicker disabled={props.canEdit}  {...getFieldProps('borrowingTime', { rules: [{ required: true, message: '必填', type: "date" }] }) } style={{ width: 193 }}/>
            </FormItem>
          </Col>
          <Col span="6">
            <FormItem  {...formItemLayout} label="签署地址：">
              <Input disabled={props.canEdit}   {...getFieldProps('name', { rules: [{ required: true, message: '必填' }] }) }  type="text" autoComplete="off" />
            </FormItem>
          </Col>
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
        <Row>
          <Col span="6">
            <FormItem  {...formItemLayout} label="贷款期限（月）：">
              <Input disabled={true}   {...getFieldProps('spouseCertNumber') } type="text" autoComplete="off" />
            </FormItem>
          </Col>
          <Col span="6">
            <FormItem  {...formItemLayout} label="还款方式：">
              <Input    disabled={true} {...getFieldProps('spouseCertNumber') }  type="text" autoComplete="off" />
            </FormItem>
          </Col>
          <Col span="6">
            <FormItem  {...formItemLayout} label="还款起始日：">
              <DatePicker disabledDate={this.disabledStartDate2} {...getFieldProps('custSignedTimeBegintime', { onChange: this.onStartChange2 }) } />
            </FormItem>
          </Col>
          <Col span="6">
            <FormItem  {...formItemLayout} label="借款结束日：">
              <DatePicker disabledDate={this.disabledEndDate2} {...getFieldProps('custSignedTimeEndtime', { onChange: this.onEndChange2 }) } />
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="6">
            <FormItem  {...formItemLayout} label="借款利率：">
              <Input disabled={true}   {...getFieldProps('spouseCertNumber') } type="text" autoComplete="off" />
            </FormItem>
          </Col>
          <Col span="6">
            <FormItem  {...formItemLayout} label="服务费：">
              <Input disabled={true}   {...getFieldProps('name', { rules: [{ required: true, message: '必填' }] }) }  type="text" autoComplete="off" />
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="6">
            <FormItem  {...formItemLayout}  label="借款用途：">
              <Input disabled={props.canEdit}   {...getFieldProps('spousePhone') } type="text" autoComplete="off" labelCol={{ span: 4 }} />
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="6">
            <FormItem  {...formItemLayout} label="开户名：">
              <Input disabled={props.canEdit}   {...getFieldProps('name', { rules: [{ required: true, message: '必填' }] }) }  type="text" autoComplete="off" />
            </FormItem>
          </Col>
          <Col span="6">
            <FormItem  {...formItemLayout} label="账号：">
              <Input disabled={props.canEdit}   {...getFieldProps('name', { rules: [{ required: true, message: '必填' }] }) }  type="text" autoComplete="off" />
            </FormItem>
          </Col>
          <Col span="6">
            <FormItem  {...formItemLayout}  label="开户行：">
              <Select>
                {this.getMarryStatuslist() }
              </Select>
            </FormItem>
          </Col>
        </Row>
        <Row>
          <Col span="6">
            <FormItem  {...formItemLayout}  label="开户区：">
              <Input disabled={true}   {...getFieldProps('name', { rules: [{ required: true, message: '必填' }] }) } type="text" autoComplete="off" labelCol={{ span: 4 }} />
            </FormItem>
          </Col>
        </Row>

      </Form>
    )

  }

});

HousingInformation = createForm()(HousingInformation);
export default HousingInformation;