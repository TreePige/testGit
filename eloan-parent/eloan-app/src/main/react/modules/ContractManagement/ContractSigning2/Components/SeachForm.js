import React from 'react';
import {
  Button,
  Form,
  Input,
  Select,
  DatePicker
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;

let SeachForm = React.createClass({
  getInitialState() {
        return {
            roleList: []
        }
    },
  handleQuery() {
    var params = this.props.form.getFieldsValue();
    params.custSignedTimeBegintime = params.custSignedTimeBegintime&&DateFormat.formatDate(params.custSignedTimeBegintime);
    params.custSignedTimeEndtime = params.custSignedTimeEndtime&&DateFormat.formatDate(params.custSignedTimeEndtime);
    params.contractCifceTimeBegintime = params.contractCifceTimeBegintime&&DateFormat.formatDate(params.contractCifceTimeBegintime);
    params.contractCifceTimeEndtime = params.contractCifceTimeEndtime&&DateFormat.formatDate(params.contractCifceTimeEndtime);
    this.props.passParams({
      searchParams : JSON.stringify(params),
      pageSize: 10,
      currentPage: 1,
    });
  },
  disabledStartDate(contractCifceTimeBegintime) {
    if (!contractCifceTimeBegintime || !this.state.contractCifceTimeEndtime) {
      return false;
    }
    return contractCifceTimeBegintime.getTime() >= this.state.contractCifceTimeEndtime.getTime();
  },
  disabledEndDate(contractCifceTimeEndtime) {
    if (!contractCifceTimeEndtime || !this.state.contractCifceTimeBegintime) {
      return false;
    }
    return contractCifceTimeEndtime.getTime() <= this.state.contractCifceTimeBegintime.getTime();
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
  onStartChange(value) {
    this.onChange('contractCifceTimeBegintime', value);
  },
  onEndChange(value) {
    this.onChange('contractCifceTimeEndtime', value);
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
  handleReset() {
    this.props.form.resetFields();
  },
  render() {
    const {
      getFieldProps
    } = this.props.form;
    return (
      <Form inline >
        <FormItem label="合同编号：">
          <Input  {...getFieldProps('contractCode', { initialValue: ''})} />
        </FormItem>
        <FormItem label="合同名称：">
          <Input  {...getFieldProps('contractName', { initialValue: ''})} />
        </FormItem>
        <FormItem label="合同生成时间：">
        <DatePicker disabledDate={this.disabledStartDate} {...getFieldProps('contractCifceTimeBegintime',{onChange:this.onStartChange} )} />
        </FormItem>
        <FormItem label="至">
          <DatePicker disabledDate={this.disabledEndDate} {...getFieldProps('contractCifceTimeEndtime',{onChange:this.onEndChange} )} />
        </FormItem>
        <FormItem label="资产渠道：">
          <Select style={{ width: 100 }} {...getFieldProps('channelName', { initialValue: ''})}>
            <Option value="1">51人品</Option>
          </Select>
        </FormItem> 
        <FormItem label="签约方式：">
          <Select style={{ width: 100 }} {...getFieldProps('signedWay', { initialValue: ''})}>
            <Option value="1">E签宝</Option>
            <Option value="2">法大大</Option> 
          </Select>
        </FormItem> 
        <FormItem label="合同签订时间：">
        <DatePicker disabledDate={this.disabledStartDate2} {...getFieldProps('custSignedTimeBegintime',{onChange:this.onStartChange2} )} />
        </FormItem>
        <FormItem label="至">
          <DatePicker disabledDate={this.disabledEndDate2} {...getFieldProps('custSignedTimeEndtime',{onChange:this.onEndChange2} )} />
        </FormItem>
        <FormItem><Button type="primary" onClick={this.handleQuery}>查询</Button></FormItem>
        <FormItem><Button type="reset" onClick={this.handleReset}>重置</Button></FormItem> 
      </Form>
    );
  }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;
