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
    this.props.passParams({
      search : JSON.stringify(params),
      pageSize: 5,
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
          <FormItem label="合同编号：">
            <Input  {...getFieldProps('projectName') } />
          </FormItem>
          <FormItem label="还款状态：">
               <Select style={{ width: 150 }} {...getFieldProps('status', { initialValue: ''})}>
                 <Option value="0">演示1部</Option>
                 <Option value="1">演示2部</Option> 
               </Select>
          </FormItem>
          <FormItem label="客户姓名：">
            <Input  {...getFieldProps('customerName') } />
          </FormItem> 
          <FormItem label="所属机构：">
               <Select style={{ width: 150 }} {...getFieldProps('status', { initialValue: ''})}>
                 <Option value="0">演示1部</Option>
                 <Option value="1">演示2部</Option> 
               </Select>
          </FormItem>
          <FormItem label="借款期数：">
               <Select style={{ width: 150 }} {...getFieldProps('status', { initialValue: ''})}>
                 <Option value="0">演示1部</Option>
                 <Option value="1">演示2部</Option> 
               </Select>
          </FormItem>
           <FormItem><Button type="primary" onClick={this.handleQuery}>查询</Button></FormItem>
           <FormItem><Button type="reset" onClick={this.handleReset}>重置</Button></FormItem> 
        </Form>
    );
  }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;