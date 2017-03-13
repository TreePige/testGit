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
      searchParams : JSON.stringify(params),
      pageSize: 10,
      currentPage: 1,
    });
  },
  handleReset() {
    this.props.form.resetFields();
  },
  componentDidMount() { 
       var me = this ;
       Utils.ajaxData({
        url: '/modules/system/getRoleList.htm',
        method: 'get', 
        type: 'json',
        callback: (result) => {
          var items  = result.data.map((item)=>{
              return (<Option key={item.id} value={String(item.id)}>{item.name}</Option>);
            });
           me.setState({roleList:items});
        }
      });
    },
  render() {
    const {
      getFieldProps
    } = this.props.form;
    return (
      <Form inline >
          <FormItem label="项目编号：">
            <Input  {...getFieldProps('chineseName', { initialValue: ''})} />
          </FormItem>
          <FormItem label="项目名称：">
            <Input  {...getFieldProps('englishName', { initialValue: ''})} />
          </FormItem>
          <FormItem label="客户名称：">
            <Input  {...getFieldProps('unit', { initialValue: ''})} />
          </FormItem>
          <FormItem label="客户经理：">
            <Input  {...getFieldProps('unit', { initialValue: ''})} />
          </FormItem>
          <FormItem><Button type="primary" onClick={this.handleQuery}>查询</Button></FormItem>
          <FormItem><Button type="reset" onClick={this.handleReset}>重置</Button></FormItem> 
        </Form>
    );
  }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;