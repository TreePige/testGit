import React from 'react';
import {
    Button,
    Form,
    Input,
    InputNumber,
    Modal,
    Select,
    Tree,
    TreeSelect,
    Row,
    Col
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const objectAssign = require('object-assign');

var ptypeList = [];
    Utils.ajaxData({
        url: '/getDicts.htm?type=ELOAN_TYPE',
        method: 'get',
        type: 'json',
        callback: (result) => {
            ptypeList = result.data;
        }
    });
    
var AddProduct = React.createClass({
    getInitialState() {
        return {
            status: {},
            formData: {}
        };
    },
    handleCancel() {
        this.props.form.resetFields();
        this.props.hideModal();
    },
    handleOk(e) {
        e.preventDefault();
        var me = this;
        var props = me.props;
        var record = props.record;
        var title = props.title;

        var url = "/modules/paramconfig/ProductTypeAction/saveOrUpdateProductType.htm";
        this.props.form.validateFields((errors, values) => {
            if (!!errors) {
                console.log('Errors in form!!!');
                return;
            }
            console.log("values", values)
            var data = objectAssign({}, {
                json: JSON.stringify(values)
            }, {
                act: 'create'
            });
            if (title == "修改") {
                var data = objectAssign({}, {
                    json: JSON.stringify(values)
                }, {
                    act: 'update'
                });
            }
            Utils.ajaxData({
                url: url,
                data: data,
                callback: (result) => {
                    if (result.code == 200) {
                        Modal.success({
                            title: result.msg,
                            onOk: () => {
                                props.hideModal();
                            }
                        });
                    } else {
                        Modal.error({
                            title: result.msg,
                        });
                    }
                }
            });
        });
    },
    getptypeList() {
        return ptypeList.map((item, index) => {
            return <Option key={item.value} >{item.text}</Option>
        })
    },
    render() {
        const {
            getFieldProps
        } = this.props.form;
        var props = this.props;
        var state = this.state;
        var modalBtns = [
            <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
            <button key="button" className="ant-btn ant-btn-primary"  onClick={this.handleOk}>
          提 交
      </button>
        ];
        const formItemLayout = {
            labelCol: {
                span: 8
            },
            wrapperCol: {
                span: 15
            },
        };
        return (
            <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="900"  footer={modalBtns} >
          <Form horizontal  form={this.props.form}>
                <Input  {...getFieldProps('id',  {initialValue:''})} type="hidden"   />
              <Col span="12">
                <FormItem  {...formItemLayout} label="类别名称：">
                  <Input disabled={!props.canEdit}  {...getFieldProps('productType', { rules: [{required:true,message:'必填'}]})} type="text" autoComplete="off" />
                </FormItem> 
              </Col>
              <Col span="12">
                <FormItem  {...formItemLayout}  label="贷款类型：">
                  <Select  disabled={!props.canEdit} {...getFieldProps('ptype',{ rules: [{required:true,message:'必填'}]})} >
                        {this.getptypeList()}
                  </Select>
                </FormItem>
              </Col> 
              <Col span="12">
                <FormItem  {...formItemLayout}  label="是否启用：">
                  <Select  disabled={!props.canEdit} {...getFieldProps('state',{ rules: [{required:true,message:'必填',type:'number'}]})} >
                        <Option value={1}>是</Option> 
                        <Option value={0}>否</Option>
                  </Select>
                </FormItem>
              </Col> 
               <Col span="12">
              <FormItem  {...formItemLayout} label="备注：">
                  <Input disabled={!props.canEdit}  {...getFieldProps('note', { rules: [{required:true,message:'必填'}]})} type="text" autoComplete="off" />
                </FormItem> 
              </Col>             
          </Form>
      </Modal>
        );
    }
});
AddProduct = createForm()(AddProduct);
export default AddProduct;
