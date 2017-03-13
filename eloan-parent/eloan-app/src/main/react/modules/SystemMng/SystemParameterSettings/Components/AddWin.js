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
var roleList = [];
Utils.ajaxData({
    url: '/modules/system/getRoleList.htm',
    method: 'get',
    type: 'json',
    callback: (result) => {
        roleList = result.data;
    }
});

var AddWin = React.createClass({
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
        console.log(record)
        var url = "/modules/system/SysConfigAction//saveOrUpdateSysConfig.htm";
        this.props.form.validateFields((errors, values) => {
            if (!!errors) {
                console.log('Errors in form!!!');
                return;
            }
            if (title == "新增") {
                var data = objectAssign({}, {
                    json: JSON.stringify(objectAssign({}, values, {

                    }, {
                            isDelete: values.isDelete
                        }))
                }, {
                        status: 'create'
                    });
            }
            else if (title == "修改") {
                var data = objectAssign({}, {
                    json: JSON.stringify(objectAssign({}, values, {

                    }, {
                            isDelete: values.isDelete
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
                    }
                }
            });
        })
    },
    getRoleList() {
        return roleList.map((item, index) => {
            return <Option key={item.id} >{item.name}</Option>
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
                    <Input  {...getFieldProps('id', { initialValue: '' }) } type="hidden"   />
                    <Row>
                        <Col span="12">
                            <FormItem  {...formItemLayout} label="参数编号：">
                                <Input disabled={!props.canEdit}  {...getFieldProps('code', { rules: [{ required: true, message: '必填' }] }) } type="text"  />
                            </FormItem>
                        </Col>
                        <Col span="12">
                            <FormItem  {...formItemLayout} label="参数名称：">
                                <Input disabled={!props.canEdit}  {...getFieldProps('name', { rules: [{ required: true, message: '必填' }] }) } type="text"  />
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="12">
                            <FormItem  {...formItemLayout} label="参数值：">
                                <Input disabled={!props.canEdit}  {...getFieldProps('value', { rules: [{ required: true, message: '必填' }] }) } type="text"  />
                            </FormItem>
                        </Col>
                        <Col span="12">
                            <FormItem  {...formItemLayout} label="备注：">
                                <Input disabled={!props.canEdit}  {...getFieldProps('remark') } type="text" />
                            </FormItem>
                        </Col>
                    </Row>
                </Form>
            </Modal>
        );
    }
});
AddWin = createForm()(AddWin);
export default AddWin;
