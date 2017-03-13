import React from 'react';
import {
  Button,
  Form,
  Input,
  Select,
  Tree,
  TreeSelect,
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
      searchParam : JSON.stringify(params),
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
        url: '/modules/paramconfig/ProductParamAction/getOfficeCombo.htm',
        method: 'get', 
        type: 'json',
        callback: (result) => {
          var items  = result.data.map((item)=>{
              return (<Option key={item.value} value={item.value}>{item.text}</Option>);
            });
           me.setState({officeIdsList:items});
        }
      });
      Utils.ajaxData({
        url: '/getDicts.htm?type=GUARANTEE_TYPE',
        method: 'get', 
        type: 'json',
        callback: (result) => {
          var items  = result.data.map((item)=>{
              return (<Option key={item.value} value={item.value}>{item.text}</Option>);
            });
           me.setState({ptypeList:items});
        }
      });
  },
  render() {
    const {
      getFieldProps
    } = this.props.form;
    var props = this.props;
    var state = this.state;
    return (
      <Form inline >
          <FormItem label="产品编号：">
            <Input  {...getFieldProps('id', { initialValue: ''})} />
          </FormItem>
          <FormItem label="产品名称：">
            <Input  {...getFieldProps('name', { initialValue: ''})} />
          </FormItem> 
          <FormItem label="所属机构：">
            <Select style={{ width: 250 }} placeholder="请选择"  {...getFieldProps('officeIds', { initialValue: ''})}>
              {this.state.officeIdsList}    
            </Select> 
          </FormItem> 
          <FormItem label="担保类型：">
            <Select style={{ width: 100 }} placeholder="请选择"  {...getFieldProps('ptype', { initialValue: ''})}>
              {this.state.ptypeList}    
            </Select>
          </FormItem>   
          <FormItem label="是否启用：">
            <Select style={{ width: 100 }}  placeholder="请选择"  {...getFieldProps('isRun', { initialValue: ''})}>
                <Option  value={0}>是</Option>
                <Option  value={1}>否</Option>
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