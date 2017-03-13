import React from 'react';
import {
    Button,
    Form,
    Input,
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
let treeData = [];
Utils.ajaxData({
  url: '/modules/system/checkboxoffices.htm',
  method: 'get',
  type: 'json',
  callback: (result) => {
    let data = result.data;
    treeData = data;
  }
});
var ProductTypeList = [];
Utils.ajaxData({
    url: '/modules/paramconfig/ProductTypeAction/getProductTypeCombo.htm',
    method: 'get',
    type: 'json',
    callback: (result) => {
        ProductTypeList = result.data;
    }
});
var RepaymentTypeList = [];
Utils.ajaxData({
    url: '/getDicts.htm?type=REPAYMENT_TYPE',
    method: 'get',
    type: 'json',
    callback: (result) => {
        RepaymentTypeList = result.data;
    }
});
var AddParaModal = React.createClass({
    getInitialState() {
        return {
            status: {},
            formData: {},
            needParam: []
        };
    },
    handleCancel(){
        this.props.form.resetFields();
        this.props.hideModal();
    },
    handleOk(e) {
        e.preventDefault();
        var me = this;
        var props = me.props;
        var record = props.record;
        var title = props.title;

        var url = "/modules/paramconfig/ProductParamAction/saveOrUpdateProductParam.htm";
        this.props.form.validateFields((errors, values) => {
            if (!!errors) {
                console.log('Errors in form!!!');
                return;
            }
            console.log("values", values)
            if(values.officeIds){
                var officeIds=values.officeIds.join(',')
            } else {
                var officeIds=""
            }
            var data = objectAssign({}, {
                json: JSON.stringify(objectAssign({}, values, {
                officeIds: officeIds
                }))
            }, {
                status: 'create'
            });
            if (title == "编辑") {
                var data = objectAssign({}, {
                json: JSON.stringify(objectAssign({}, values, {
                    officeIds: officeIds
                }))
                }, {
                status: 'update'
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
    getProductTypeList() {
        return ProductTypeList.map((item, index) => {
            return <Option key={item.value} >{item.text}</Option>
        })
    },
    getRepaymentTypeList() {
        return RepaymentTypeList.map((item, index) => {
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
        if (!props.canEdit) {
        modalBtns = [
            <button key="back" className="ant-btn" onClick={this.handleCancel}>关闭</button>
            ];
        }
        const formItemLayout = {
            labelCol: {
                span: 8
            },
            wrapperCol: {
                span: 15
            },
        };
        return (
        <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="1000"  footer={modalBtns} >
            <Form horizontal  form={this.props.form}>
                <Input  {...getFieldProps('id',  {initialValue:''})} type="hidden"   />
                    <Row>
                        <Col span="12">
                            <FormItem  {...formItemLayout} label="产品类型：">
                                <Select id="select" disabled={!props.canEdit} {...getFieldProps('productType',{ rules: [{required:true,message:'必填'}]})} >
                                        {this.getProductTypeList()}
                                </Select> 
                            </FormItem> 
                        </Col>
                        <Col span="12">
                            <FormItem  {...formItemLayout} label="产品名称：">
                                <Input disabled={!props.canEdit}  {...getFieldProps('name', { rules: [{required:true,message:'必填'}]})} type="text" autoComplete="off" />
                            </FormItem> 
                        </Col>
                    </Row>
                    <Row>
                        <Col span="12">
                            <FormItem  {...formItemLayout} label="底点利率：">
                                <Input disabled={!props.canEdit}  {...getFieldProps('bottomMonthRate', { rules: [{required:true,message:'必填'}]})} type="text" autoComplete="off" />
                            </FormItem> 
                        </Col>
                        <Col span="12">
                            <FormItem  {...formItemLayout} label="还款方式：">
                                <Select id="select" disabled={!props.canEdit} {...getFieldProps('repaymentType',{ rules: [{required:true,message:'必填'}]})} >
                                        {this.getRepaymentTypeList()}
                                </Select> 
                            </FormItem> 
                        </Col>
                    </Row>
                    <Row>
                        <Col span="12">
                            <FormItem  {...formItemLayout} label="逾期罚息费率：">
                                <Input disabled={!props.canEdit}  {...getFieldProps('overdueDayRate', { rules: [{required:true,message:'必填'}]})} type="text" autoComplete="off" />
                            </FormItem> 
                        </Col>
                    </Row>
                    <Row>
                        <Col span="12">
                            <FormItem  {...formItemLayout} label="适用机构：">
                                <TreeSelect disabled={!props.canEdit} dropdownStyle={{ maxHeight: 400, overflow: 'auto' }} treeData={treeData} 
                                {...getFieldProps('officeIds')}
                                placeholder="请选择" treeDefaultExpandAll multiple={true}  treeCheckable={true}/>
                            </FormItem>
                        </Col>
                        <Col span="12">
                            <FormItem  {...formItemLayout} label="提前还款罚息费率：">
                                <Input disabled={!props.canEdit}  {...getFieldProps('aheadRepayRate', { rules: [{required:true,message:'必填'}]})} type="text" autoComplete="off" />
                            </FormItem> 
                        </Col>
                    </Row>
                    <Row>
                        <Col span="12">
                            <FormItem  {...formItemLayout} label="备注：">
                                <Input disabled={!props.canEdit}  {...getFieldProps('remark', { rules: [{required:true,message:'必填'}]})} type="text" autoComplete="off" />
                            </FormItem> 
                        </Col>
                    </Row>
            </Form>
        </Modal>
        );
    }
});
AddParaModal = createForm()(AddParaModal);
export default AddParaModal;
