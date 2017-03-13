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
      endOpen: false,
      roleList: []
    }
  },

  onChange(field, value) {
    console.log(field, 'change', value);
    this.setState({
      [field]: value,
    });
  },
  
  handleStartToggle({ open }) {
    if (!open) {
      this.setState({ endOpen: true });
    }
  },
  handleEndToggle({ open }) {
    this.setState({ endOpen: open });
  },
  handleQuery() {
    var params = this.props.form.getFieldsValue();
    this.props.passParams({
      searchParams: JSON.stringify(params),
      pageSize: 10,
      currentPage: 1,
    });
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
        <FormItem label="项目编号：">
          <Input  {...getFieldProps('projectCode') } />
        </FormItem>
        <FormItem label="项目名称：">
          <Input  {...getFieldProps('projectName') } />
        </FormItem>
        <FormItem label="客户名称：">
          <Input  {...getFieldProps('customerName') } />
        </FormItem>
        <FormItem label="金融顾问/分销商名称：">
             <Select style={{ width: 150 }} {...getFieldProps('status', { initialValue: ''})}>
               <Option value="0">演示1部</Option>
               <Option value="1">演示2部</Option> 
             </Select>
        </FormItem> 
        <FormItem label="报单人：">
          <Input  {...getFieldProps('customerManager') } />
        </FormItem>  
        <FormItem><Button type="primary" onClick={this.handleQuery}>查询</Button></FormItem>
        <FormItem><Button type="reset" onClick={this.handleReset}>重置</Button></FormItem>
      </Form>
    );
  }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;
