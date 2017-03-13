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
const NavLine = require("../utils/NavLine");
const RadioGroup = Radio.Group;
const CheckboxGroup = Checkbox.Group;
var Region = require('../plugin/Region');

var PurposesList = [];
Utils.ajaxData({
    url: '/modules/common/action/ComboAction/queryDic.htm',
    method: 'get',
    data: {
        typeCode: "PLAN_USE"
    },
    type: 'json',
    callback: (result) => {
        PurposesList = result.data;
    }
});
var KeyDiskQueryList = [];
Utils.ajaxData({
    url: '/modules/common/action/ComboAction/queryDic.htm',
    method: 'get',
    data: {
        typeCode: "KEY_DISK_QUERY"
    },
    type: 'json',
    callback: (result) => {
        KeyDiskQueryList = result.data;
    }
});
var PropertyListedProofList = [];
Utils.ajaxData({
    url: '/modules/common/action/ComboAction/queryDic.htm',
    method: 'get',
    data: {
        typeCode: "HOUSE_MARKET_CERTIFY"
    },
    type: 'json',
    callback: (result) => {
        PropertyListedProofList = result.data;
    }
});
var PropertyPropertiesList = [];
Utils.ajaxData({
    url: '/modules/common/action/ComboAction/queryDic.htm',
    method: 'get',
    data: {
        typeCode: "PROPERTY_TYPE"
    },
    type: 'json',
    callback: (result) => {
        PropertyPropertiesList = result.data;
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
var AddlWin = React.createClass({
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
            propertyAddressId: '110101',
            formData: {}
        };
    },
    changeAreaId(name, areaId) {
        this.setState({
            [name]: areaId
        })
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
    getPropertyPropertiesList() {
        return PropertyPropertiesList.map((item, index) => {
            return <Option key={item.value} >{item.text}</Option>
        })
    },
    getPropertyListedProofList() {
        return PropertyListedProofList.map((item, index) => {
            return <Option key={item.value} >{item.text}</Option>
        })
    },
    getKeyDiskQueryList() {
        return KeyDiskQueryList.map((item, index) => {
            return <Option key={item.value} >{item.text}</Option>
        })
    },
    getPurposesList() {
        return PurposesList.map((item, index) => {
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
        var affor, purchase, property;
        var propertyProperties = this.props.form.getFieldsValue().propertyProperties;
        if (propertyProperties == 2) {
            affor = (
                <Col span="7">
                    <FormItem  {...formItemLayout} label="经适房房本时间：" >
                        <DatePicker  wrapperCol={{span:"7"}} {...getFieldProps('affordableRoomTime', { rules: [{ required: true, message: '必填', type: "date" }] }) }  disabled={!props.canEdit}/>
                    </FormItem>
                </Col>
            )
            purchase = (
                <Col span="7">
                    <FormItem  {...formItemLayout} label="经适房购房发票时间：">
                        <DatePicker  wrapperCol={{span:"7"}} {...getFieldProps('purchaseInvoicesTime', { rules: [{ required: true, message: '必填', type: "date" }] }) }  disabled={!props.canEdit}/>
                    </FormItem>
                </Col>
            )
        } else if (propertyProperties == 7) {
            property = (
                <Col span="7">
                    <FormItem  {...formItemLayout} label="房改房上市证明：">
                        <Select   {...getFieldProps('propertyListedProof', { rules: [{ required: true, message: '必填' }] }) }  disabled={!props.canEdit}>
                            {this.getPropertyListedProofList() }
                        </Select>
                    </FormItem>
                </Col>
            )
        }

        return (
            <Form horizontal  form={this.props.form} style={{ marginTop: "20px" }}>
                <Input  {...getFieldProps('id', { initialValue: '' }) } type="hidden"   />

                <Row>
                    <Col span="14">
                        <FormItem  labelCol={{ span: 4 }} wrapperCol={{ span: 19 }} label="房产地址：">
                            <Region {...getFieldProps('propertyAddressId', { initialValue: this.state.propertyAddressId, onChange: this.changeAreaId.bind(this, "propertyAddressId") }) }   disabled={!this.props.canEdit}/>
                        </FormItem>
                    </Col>
                    <Col span="4" >
                        <FormItem style={{ marginLeft: "-83px" }}>
                            <Input disabled={!props.canEdit} placeholder=""  {...getFieldProps('propertyAddress', { rules: [{ required: true, message: '必填' }] }) } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="房权证号：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('propertyCertificate', { rules: [{ required: true, message: '必填' }] }) } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout}  label="房产性质：">
                            <Select disabled={!props.canEdit} {...getFieldProps('propertyProperties', { rules: [{ required: true, message: '必填' }] }) } >
                                {this.getPropertyPropertiesList() }
                            </Select>
                        </FormItem>
                    </Col>
                    {property}
                </Row>
                <Row>
                    {affor}
                    {purchase}
                </Row>
                <Row>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="规划用途：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('planningPurposes', { rules: [{ required: true, message: '必填' }] }) } >
                                {this.getPurposesList() }
                            </Select>
                        </FormItem>
                    </Col>
                   { /*<Col span="7">
                        <FormItem  {...formItemLayout} label="钥匙盘查询：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('keyDiskQuery', { rules: [{ required: true, message: '必填' }] }) } >
                                {this.getKeyDiskQueryList() }
                            </Select>
                        </FormItem>
                    </Col>*/}
                </Row>
            </Form>
        );
    }
});
AddlWin = createForm()(AddlWin);
export default AddlWin;
