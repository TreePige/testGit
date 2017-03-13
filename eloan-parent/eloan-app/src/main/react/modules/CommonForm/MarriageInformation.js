import React from 'react';
import {
    Button,
    Form,
    Input,
    Modal,
    Select,
    Row,
    Col,
    Radio,
    Checkbox,
    DatePicker
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const objectAssign = require('object-assign');
var Reflux = require('reflux');
var FormDataStore = require('./stores/FormDataStore');
const RadioGroup = Radio.Group;
const CheckboxGroup = Checkbox.Group;
var MarryStatuslist = [];
Utils.ajaxData({
    url: '/modules/common/action/ComboAction/queryDic.htm',
    method: 'get',
    data: {
        typeCode: "MARITAL_STATUS"
    },
    type: 'json',
    callback: (result) => {
        MarryStatuslist = result.data;
    }
});
var typeIdList = [];
Utils.ajaxData({
    url: '/getDicts.htm?type=BANK_TYPE',
    method: 'get',
    type: 'json',
    callback: (result) => {
        typeIdList = result.data;
    }
});
var MarriageInformation = React.createClass({
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
                automaticWithholdingTime: "",
            },
            formData: {}
        };
    },
    handleCancel() {
        this.props.form.resetFields();
        this.props.hideModal();
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
    onChangea(checkedValues) {
        console.log('checked = ', checkedValues);
    },
    getMarryStatuslist() {
        return MarryStatuslist.map((item, index) => {
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
        var married, divorced, remarried;
        var maritalStatus = this.props.form.getFieldsValue().maritalStatus;
        if (maritalStatus == 2) {
            married = (
                <Col span="7">
                    <FormItem  {...formItemLayout} label="证件日期：">

                        <DatePicker disabled={!props.canEdit} wrapperCol={{span:"7"}}  {...getFieldProps('documentsTime', { rules: [{ required: true, message: '必填', type: "date" }] }) } />
                    </FormItem>
                </Col>
            )
        } else if (maritalStatus == 3) {
            divorced = (
                <Row>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="证件日期：">
                            <DatePicker disabled={!props.canEdit} wrapperCol={{span:"7"}}  {...getFieldProps('documentsTime', { rules: [{ required: true, message: '必填', type: "date" }] }) } />
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="离婚协议：">
                            <Select   {...getFieldProps('divorceAgreement', { rules: [{ required: true, message: '必填', type: "number" }] }) }  disabled={!props.canEdit}>
                                <Option value={0}>有</Option>
                                <Option value={1}>无</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="法院判决：">
                            <Select   {...getFieldProps('courtVerdict', { rules: [{ required: true, message: '必填', type: "number" }] }) }  disabled={!props.canEdit}>
                                <Option value={0}>是</Option>
                                <Option value={1}>否</Option>
                            </Select>
                        </FormItem>
                    </Col>
                </Row>
            )
        } else if (maritalStatus == 5) {
            remarried = (
                <Row>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="证件日期：">
                            <DatePicker disabled={!props.canEdit} wrapperCol={{span:"7"}} {...getFieldProps('documentsTime', { rules: [{ required: true, message: '必填', type: "date" }] }) } />
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="房本日期：">
                            <DatePicker disabled={!props.canEdit} wrapperCol={{span:"7"}} {...getFieldProps('roomTime', { rules: [{ required: true, message: '必填', type: "date" }] }) } />
                        </FormItem>
                    </Col>
                </Row>
            )
        }
        return (
            <Form horizontal  form={this.props.form} style={{ marginTop: "20px" }}>
                <Input  {...getFieldProps('id', { initialValue: '' }) } type="hidden"   />
                <Row>
                    <Col span="7">
                        <FormItem  {...formItemLayout}  label="婚姻状况：">
                            <Select disabled={!props.canEdit} {...getFieldProps('maritalStatus', { rules: [{ required: true, message: '必填' }] }) } >
                                {this.getMarryStatuslist() }
                            </Select>
                        </FormItem>
                    </Col>
                    {married}
                </Row>
                {divorced}
                {remarried}

            </Form>
        );
    }
});
MarriageInformation = createForm()(MarriageInformation);
export default MarriageInformation;
