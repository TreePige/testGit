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
    Col,
    DatePicker
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const objectAssign = require('object-assign');
const NavLine = require("./../../../utils/NavLine");
let treeData = {};
var AddDic = React.createClass({
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
    navLineData: {
        "基本信息": "#s1",
    },
    handleOk(e) {
        e.preventDefault();
        var me = this;
        var props = me.props;
        var record = props.record;
        var title = props.title;

        var url = "/modules/system/addOrUpdateDict.htm";
        this.props.form.validateFields((errors, values) => {
            if (!!errors) {
                console.log('Errors in form!!!');
                return;
            }
            console.log("values", values)
            var data = objectAssign({}, {
                form: JSON.stringify(values)
            }, {
                    status: 'create'
                });
            if (title == "修改") {
                var data = objectAssign({}, {
                    form: JSON.stringify(values)
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
            <div style={{ position: "relative" }}>
                        <Form horizontal  form={this.props.form} style={{ marginTop: "20px" }}>
                          <Input  {...getFieldProps('id', { initialValue: '' }) } type="hidden"   />
                          <div id="s1">
                            <Row>
                              <Col span="12">
                                <FormItem  {...formItemLayout} label="处置公司：">
                                  <Input disabled={!props.canEdit}  {...getFieldProps('projectCode') } type="text" autoComplete="off" />
                                </FormItem>
                              </Col>
                              <Col span="12">
                                <FormItem  {...formItemLayout} label="联系电话：">
                                  <Input disabled={!props.canEdit}  {...getFieldProps('idcard') } type="text" autoComplete="off" />
                                </FormItem>
                              </Col>
                            </Row>
                            <Row>
                              <Col span="12">
                                <FormItem  {...formItemLayout} label="处置金额：">
                                  <Input disabled={!props.canEdit}  {...getFieldProps('customerName') } type="text" autoComplete="off" />
                                </FormItem>
                              </Col>
                              <Col span="12">
                                <FormItem  {...formItemLayout} label="交易日期：">
                                    <DatePicker disabled={!props.canEdit}  {...getFieldProps('borrowingTime', { rules: [{ required: true, message: '必填', type: "date" }] }) }/>
                                </FormItem>
                              </Col>
                            </Row>
                            <Row>
                              <Col span="24">
                                <FormItem  labelCol={{span: 4}} wrapperCol={{span: 19}} label="备注：">
                                    <Input disabled={!props.canEdit} rows="3" type="textarea"  {...getFieldProps('remark')} autoComplete="off"/>
                                </FormItem>
                              </Col>
                            </Row>
                          </div>
                        </Form>
            </div>                
            </Modal>
        );
    }
});
AddDic = createForm()(AddDic);
export default AddDic;
