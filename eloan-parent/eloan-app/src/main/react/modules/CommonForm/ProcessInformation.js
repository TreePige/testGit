//审批信息
import React from 'react';
import {
    Button,
    Form,
    Input,
    Modal,
    Select,
    Row,
    InputNumber,
    Col, 
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
var ProcessInformation = React.createClass({
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
    getPropertySituationList() {
        return PropertySituationList.map((item, index) => {
            return <Option key={item.id} value={String(item.userName) }>{item.name}</Option>
        })
    },
    render() {
        const {
            getFieldProps
        } = this.props.form;
        var props = this.props;
        var state = this.state;
        const formItemLayout = {
            labelCol: {
                span: 8
            },
            wrapperCol: {
                span: 15
            },
        };
        var amount
        var receiveType = this.props.form.getFieldsValue().receiveType;
        if (receiveType == 0) {
            amount = (
                <Col span="10">
                    <FormItem  {...formItemLayout} label="下户费金额：">
                        <Input disabled={!props.canEdit}  {...getFieldProps('amountComment', { rules: [{ required: true, message: '必填' }] }) } type="text" autoComplete="off" />
                    </FormItem>
                </Col>
            )
        }
        var item;
        if (this.props.record.processStateCode == "usertask-customerServiceStaffConfirm") {

            item = (
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="下户费收取形式：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('receiveType') } >
                                <Option value={0}>线上收取</Option>
                                <Option value={1}>线下收取</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    {amount}
                </Row>
            )
        } else {

            item = "";
        }
        var ccc;
        if (this.props.record.processStateCode == "usertask-householdTaskAssign"||this.props.record.processStateCode =="usertask-collateralTaskAssign") {
            ccc = (
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout}  label="任务分配：">
                            <Select disabled={!props.canEdit} {...getFieldProps('manualAssignee', { rules: [{ required: true, message: '必填' }] }) } >
                                {this.getPropertySituationList() }
                            </Select>
                        </FormItem>
                    </Col>
                </Row>
            )
        } else {

            ccc = "";
        }
        var quickly;
        if (this.props.record.processStateCode == "usertask-householdInvestigate") {
            quickly = (
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="房屋快出值：">
                            <InputNumber disabled={!props.canEdit} style={{width:"150px"}} {...getFieldProps('housingValueFaster', { rules: [{ required: true, message: '必填',type:"number" }] }) } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                </Row>
            )
        } else {

            quickly = "";
        }
        var service;
        if (this.props.record.processStateCode == "usertask-householdConfirm") {
            service = (
                <Row>
                    <Col span="12">
                        <FormItem  {...formItemLayout} label="房屋快出值：">
                            <InputNumber disabled={!props.canEdit} style={{width:"150px"}} {...getFieldProps('amountComment', { rules: [{ required: true, message: '必填' ,type:"number"}] }) } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>

                </Row>
            )
        } else {

            service = "";
        }
        return (
            <Form horizontal  form={this.props.form} style={{ marginTop: "20px" }}>
                {item}
                {ccc}
                {quickly}
                {service}
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
                    
                    <Col span="19">
                        <FormItem  labelCol={{ span: 5 }} wrapperCol={{ span: 18 }}  label="备注：">
                            <Input disabled={!props.canEdit} rows="2" {...getFieldProps('remarkComment', { rules: [{ required: true, message: '必填' }] }) } type="textarea" autoComplete="off" />
                        </FormItem>
                    </Col>
                </Row>
            </Form>
        )
    }
});
ProcessInformation = createForm()(ProcessInformation);
export default ProcessInformation;
