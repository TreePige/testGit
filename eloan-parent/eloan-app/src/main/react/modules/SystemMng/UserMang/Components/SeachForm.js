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
      user : JSON.stringify(params),
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
          <FormItem label="员工编号：">
            <Input  {...getFieldProps('number', { initialValue: ''})} />
          </FormItem>
          <FormItem label="员工姓名：">
            <Input  {...getFieldProps('name', { initialValue: ''})} />
          </FormItem>
          <FormItem label="用户状态：">
             <Select style={{ width: 100 }} {...getFieldProps('status', { initialValue: ''})}>
               <Option value="0">正常</Option>
               <Option value="1">锁定</Option> 
             </Select>
           </FormItem> 
           <FormItem label="用户角色：">
             <Select style={{ width: 100 }} placeholder="请选择角色"  {...getFieldProps('roleId', { initialValue: ''})}>
                {this.state.roleList}    
            </Select> 
           </FormItem>
           <FormItem label="所属部门：">
            <Input  {...getFieldProps('officeName', { initialValue: ''})} />
           </FormItem>
           <FormItem><Button type="primary" onClick={this.handleQuery}>查询</Button></FormItem>
           <FormItem><Button type="reset" onClick={this.handleReset}>重置</Button></FormItem> 
        </Form>
    );
  }
});

SeachForm = createForm()(SeachForm);
export default SeachForm;