//审批信息
import React from 'react';
import {
    Button,
    Form,
    Input,
    Modal,
    Select,
    Row,
    Col,
    DatePicker
} from 'antd';
const createForm = Form.create;
const Option = Select.Option;
const FormItem = Form.Item;
var PropertySituationList = [];
Utils.ajaxData({
    url: '/modules/system/getUserInfo.htm',
    method: 'get',
    data: {
        roleName: "warrantStaff"
    },
    type: 'json',
    callback: (result) => {
        PropertySituationList = result.data;
    }
});
var SettleInfo = React.createClass({
    getInitialState() {
        return {
        }
    },

    componentDidMount() {

        var nextStepList = [];
        var me = this;
        var props = this.props;
        if (props.canEdit) {
            Utils.ajaxData({
                url: '/modules/workflow/controller/RepositoryController/queryButtonNameListByTaskId.htm',
                method: 'get',
                data: {
                    taskId: this.props.record.taskId
                },
                type: 'json',
                callback: (result) => {
                    var items = result.data.map((item) => {
                        return (<Option key={item.value} value={item.value}>{item.text}</Option>);
                    });
                    nextStepList = items;
                    me.setState({ nextStepList: nextStepList });
                }
            });

        } else {
            Utils.ajaxData({
                url: '/modules/common/action/ApprovalCommentsAction/getApprovalComment.htm',
                method: 'get',
                data: {
                    processInstanceId: this.props.record.processInstanceId,
                    taskId: this.props.record.taskId,
                },
                type: 'json',
                callback: (result) => {

                    this.props.form.setFieldsValue(result.data);
                }
            });
        }

    },
    render() {
        const {
            getFieldProps
        } = this.props.form;
        var props = this.props;
        var state = this.state;
        const formItemLayout = {
            labelCol: {
                span: 6
            },
            wrapperCol: {
                span: 15
            },
        };
        const formItemLayout2 = {
            labelCol: {
                span: 9
            },
            wrapperCol: {
                span: 15
            },
        };
        return (
            <Form horizontal  form={this.props.form} style={{ marginTop: "20px" }}>
                <Row>
                    <Col span="8">
                        <FormItem  {...formItemLayout2} label="处理人员：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('receiveType') } >
                                <Option value="0">线上收取</Option>
                                <Option value="1">线下收取</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="8">
                        <FormItem  {...formItemLayout2} label="是否还清：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('receiveType') } >
                                <Option value="0">线上收取</Option>
                                <Option value="1">线下收取</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="8">
                        <FormItem  {...formItemLayout2} label="是否逾期：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('receiveType') } >
                                <Option value="0">线上收取</Option>
                                <Option value="1">线下收取</Option>
                            </Select>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="审批意见：">
                            <Select disabled={!props.canEdit} {...getFieldProps('nextStep', { rules: [{ required: true, message: '必填' }] }) } >
                                {this.state.nextStepList}
                            </Select>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="备注：">
                            <textarea disabled={!props.canEdit}  {...getFieldProps('remarkComment') }  style={{ width: "550px", border: "1px solid #d9d9d9", borderRadius: "3px", paddingLeft: "5px" }} type="text" autoComplete="off" >
                            </textarea>
                        </FormItem>
                    </Col>
                </Row>
            </Form>
        )
    }
});
SettleInfo = createForm()(SettleInfo);
export default SettleInfo;
