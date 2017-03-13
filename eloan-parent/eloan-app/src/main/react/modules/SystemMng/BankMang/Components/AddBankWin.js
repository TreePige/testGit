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
var AddBankWin = React.createClass({
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
        var url = "/modules/system/sysBankAction/saveOrUpdate.htm";
        this.props.form.validateFields((errors, values) => {
            if (!!errors) {
                console.log('Errors in form!!!');
                return;
            }
            console.log("values", values)
            var data = objectAssign({}, {
                json: JSON.stringify(values)
            }, {
                    status: 'create'
                });
            if (title == "修改") {
                var data = objectAssign({}, {
                    json: JSON.stringify(values)
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
            <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="800"  footer={modalBtns} >
                <Form horizontal  form={this.props.form}>
                    <Input  {...getFieldProps('id', { initialValue: '' }) } type="hidden"   />
                    <Row>
                        <Col span="12">
                            <FormItem  {...formItemLayout} label="开户行：">
                                <Input disabled={!props.canEdit}  {...getFieldProps('bankCode', { rules: [{ required: true, message: '必填' }] }) } type="text" autoComplete="off" />
                            </FormItem>
                        </Col>
                        <Col span="12">
                            <FormItem  {...formItemLayout} label="网点：">
                                <Input disabled={!props.canEdit}  {...getFieldProps('openNet', { rules: [{ required: true, message: '必填' }] }) } type="text" autoComplete="off" />
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="12">
                            <FormItem  {...formItemLayout} label="户名：">
                                <Input disabled={!props.canEdit}  {...getFieldProps('openName', { rules: [{ required: true, message: '必填' }] }) } type="text" autoComplete="off" />
                            </FormItem>
                        </Col>
                        <Col span="12">
                            <FormItem  {...formItemLayout} label="账号：">
                                <Input disabled={!props.canEdit}  {...getFieldProps('account', { rules: [{ pattern: /^(\d{16}|\d{19})$/, required: true, message: '请输入正确的银行卡号' }] }) } type="text" autoComplete="off" />
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="12">
                            <FormItem  {...formItemLayout} label="是否放款使用：">
                                <div  className="clearfix">
                                    <Select id="select" disabled={!props.canEdit} {...getFieldProps('isLoan') } >
                                        <Option  value={0}>启用</Option>
                                        <Option  value={1}>禁用</Option>
                                    </Select>
                                </div>
                            </FormItem>
                        </Col>
                        <Col span="12">
                            <FormItem  {...formItemLayout} label="是否回款使用：">
                                <div  className="clearfix">
                                    <Select id="select"  disabled={!props.canEdit} {...getFieldProps('isRepayment') } >
                                        <Option  value={0}>启用</Option>
                                        <Option  value={1}>禁用</Option>
                                    </Select>
                                </div>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="12">
                            <FormItem  {...formItemLayout} label="备注：">
                                <Input disabled={!props.canEdit}  {...getFieldProps('remark') } />
                            </FormItem>
                        </Col>
                    </Row>
                </Form>
            </Modal>
        );
    }
});
AddBankWin = createForm()(AddBankWin);
export default AddBankWin;
