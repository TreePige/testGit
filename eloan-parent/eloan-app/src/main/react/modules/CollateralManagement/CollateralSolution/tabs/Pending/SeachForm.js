import React from 'react';
import {
  Button,
  Form,
  Input,
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;

let SeachForm = React.createClass({

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
          <Input  {...getFieldProps('projectCode', { initialValue: '' }) } />
        </FormItem>
        <FormItem label="项目名称：">
          <Input  {...getFieldProps('projectName', { initialValue: '' }) } />
        </FormItem>
        <FormItem label="客户名称：">
          <Input  {...getFieldProps('customerName', { initialValue: '' }) } />
        </FormItem>
        <FormItem label="客户经理：">
          <Input  {...getFieldProps('projectCode', { initialValue: '' }) } />
        </FormItem>
        <FormItem><Button type="primary" onClick={this.handleQuery}>查询</Button></FormItem>
        <FormItem><Button type="reset" onClick={this.handleReset}>重置</Button></FormItem>
      </Form>
    );
  }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;
