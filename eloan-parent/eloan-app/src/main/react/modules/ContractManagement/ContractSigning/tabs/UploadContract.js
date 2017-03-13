//基本信息
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
const FormItem = Form.Item;
const Option = Select.Option;
const objectAssign = require('object-assign');
var Reflux = require('reflux');
var UploadPhoto = require("../../../CommonForm/UploadPhoto");
var FormDataStore = require('../../../CommonForm/stores/FormDataStore');
var typeIdList = [];
Utils.ajaxData({
    url: '/getDicts.htm?type=BANK_TYPE',
    method: 'get',
    type: 'json',
    callback: (result) => {
        typeIdList = result.data;
    }
});
var ShowuploadWin = React.createClass({
    mixins: [
        Reflux.listenTo(FormDataStore, "onMatch")
    ],
    onMatch(data) {
        console.log(data)
        if (data.formData) {
            this.props.form.setFieldsValue(data.formData);
        } else this.props.form.resetFields();
    },
    getInitialState() {
        return {
            status: {
                automaticWithholdingTime: ""
            },
            formData: {}
        };
    },
    handleReset() {
        this.refs.validation.reset();
        this.setState(this.getInitialState());
    },
    handleCancel() {
        this.props.form.resetFields();
        this.props.hideModal();
    },
    onChange(field, value) {
        console.log(field, 'change', value);
        this.setState({
            [field]: value,
        });
    },
    onStartChange(value) {
        this.onChange('automaticWithholdingTime', value);
    },
    handleStartToggle({ open }) {
        if (!open) {
            this.setState({ endOpen: true });
        }
    },
    componentDidMount() {
        var record = {};
        if (this.props.record) {
            var record = this.props.record;
        }
        this.props.form.setFieldsValue(record);
    },
    gettypeIdList() {
        return typeIdList.map((item, index) => {
            return <Option key={item.value} >{item.text}</Option>
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
        return (
            <div style={{ position: "relative" }} >
                <Form horizontal  form={this.props.form} style={{ marginTop: "20px" }}>
                    <Input  {...getFieldProps('id', { initialValue: '' }) } type="hidden"   />
                    <Row>
                        <Col span="10">
                            <FormItem  {...formItemLayout} label="客户姓名：">
                                <Input disabled={true}  {...getFieldProps('customerName') } type="text" autoComplete="off" />
                            </FormItem>
                        </Col>
                        <Col span="10">
                            <FormItem  {...formItemLayout} label="借款金额：">
                                <Input disabled={true}  {...getFieldProps('account') } type="text" autoComplete="off" />
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="10">
                            <FormItem  {...formItemLayout} label="借款期限：">
                                <Input disabled={true}  {...getFieldProps('timeLimit') } type="text" autoComplete="off" />
                            </FormItem>
                        </Col>
                        <Col span="10">
                            <FormItem  {...formItemLayout} label="出借人：">
                                <Input disabled={!props.canEdit}  {...getFieldProps('lender') } type="text" autoComplete="off" />
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="10">
                            <FormItem  {...formItemLayout} label="受托人：">
                                <Input disabled={!props.canEdit}  {...getFieldProps('trustee') } type="text" autoComplete="off" />
                            </FormItem>
                        </Col>
                        <Col span="10">
                            <FormItem  {...formItemLayout} label="出借人的受托人：">
                                <Input disabled={!props.canEdit}  {...getFieldProps('trusteeOfLender') } type="text" autoComplete="off" />
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="7" style={{ marginTop: "10px" }}>
                            <UploadPhoto style={{ marginLeft: "100px" }} title="公正回执单" btype="HUIZHIDAN" canEdit={props.canEdit} selectRecord={props.record}/>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="19">
                            <FormItem  {...formItemLayout}  label="备注："  labelCol={{ span: "4" }} wrapperCol={{span:"18"}}>
                                <Input disabled={!props.canEdit}   {...getFieldProps('remark', { rules: [{ required: true, message: '必填' }] }) } type="textarea" autoComplete="off" />
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>

                    </Row>
                </Form>
            </div>
        );
    }
});
ShowuploadWin = createForm()(ShowuploadWin);
export default ShowuploadWin;
