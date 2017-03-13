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
    InputNumber,
    Checkbox,
    DatePicker
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const objectAssign = require('object-assign');
var Reflux = require('reflux');
var FormDataStore = require('./stores/FormDataStore');
var UploadPhoto = require("../CommonForm/UploadPhoto");
const RadioGroup = Radio.Group;
const CheckboxGroup = Checkbox.Group;
var EnterprisesCategorylist = [];
Utils.ajaxData({
    url: '/modules/common/action/ComboAction/queryDic.htm',
    method: 'get',
    data: {
        typeCode: "ATTENTION_ENTERPRISE_TYPE"
    },
    type: 'json',
    callback: (result) => {
        EnterprisesCategorylist = result.data;
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
    getEnterprisesCategorylist() {
        return EnterprisesCategorylist.map((item, index) => {
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
            <Form horizontal  form={this.props.form} style={{ marginTop: "20px" }}>
                <Input  {...getFieldProps('id', { initialValue: '' }) } type="hidden"   />

                <Row>
                    <Col span="7" style={{ marginTop: "10px" }}>
                        <UploadPhoto style={{ marginLeft: "45px" }} title="安融征信" btype="ANRONGCRE" canEdit={props.canEdit} selectRecord={props.record}/>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="姓名身份证是否一致：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('nameIdentificationConsistent') } >
                                <Option value={0}>是</Option>
                                <Option value={1}>否</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="关联企业数量：">
                            <Input disabled={!props.canEdit} {...getFieldProps('affiliatesNumber') } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>

                </Row>
                <Row>
                    <Col span="7" style={{ marginLeft: "308px" }}>
                        <FormItem  {...formItemLayout} label="关注类企业类别：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('followEnterprisesCategory') } >
                                {this.getEnterprisesCategorylist() }
                            </Select>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="7" style={{ marginTop: "10px" }}>
                        <UploadPhoto style={{ marginLeft: "45px" }} title="人法查询" btype="RENFACHAXUN" canEdit={props.canEdit} selectRecord={props.record}/>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="是否有被执行：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('isExecuted') } >
                                <Option value={0}>是</Option>
                                <Option value={1}>否</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="笔数：">
                            <Input disabled={!props.canEdit} {...getFieldProps('items') } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>

                </Row>
                <Row>
                    <Col span="7" style={{ marginLeft: "308px" }}>
                        <FormItem  {...formItemLayout} label="金额：">
                            <Input disabled={!props.canEdit} {...getFieldProps('money') } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="结案：">
                            <Input disabled={!props.canEdit} {...getFieldProps('concluded') } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="7" style={{ marginTop: "10px" }}>
                        <UploadPhoto style={{ marginLeft: "45px" }} title="建委查询" btype="JIANWEICHAXUN" canEdit={props.canEdit} selectRecord={props.record}/>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="已被占通道：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('occupiedChannel') } >
                                <Option value={0}>是</Option>
                                <Option value={1}>否</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="已上最高额抵押：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('maximumMortgage') } >
                                <Option value={0}>是</Option>
                                <Option value={1}>否</Option>
                            </Select>
                        </FormItem>
                    </Col>

                </Row>
                <Row>
                    <Col span="7" style={{ marginLeft: "308px" }}>
                        <FormItem  {...formItemLayout} label="司法查询/行政限制：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('judicialInquiry') } >
                                <Option value={0}>是</Option>
                                <Option value={1}>否</Option>
                            </Select>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="一抵性质：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('arrivedNature') } >
                                <Option value={0}>银行</Option>
                                <Option value={1}>非银行</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="一抵银行名称：">
                            <Input disabled={!props.canEdit} {...getFieldProps('againstBankName') } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="一抵金额：">
                            <Input disabled={!props.canEdit} {...getFieldProps('arrivedAmount') } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="一抵利率：">
                            <Input disabled={!props.canEdit} {...getFieldProps('arrivedRates') } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="评估值：">
                            <InputNumber disabled={!props.canEdit} {...getFieldProps('assessedValue', { rules: [{ required: true, message: '必填',type:"number" }] }) } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="快出价：">
                            <InputNumber disabled={!props.canEdit} {...getFieldProps('fastBid', { rules: [{ required: true, message: '必填',type:"number" }] }) } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                </Row>

            </Form>
        );
    }
});
AddlWin = createForm()(AddlWin);
export default AddlWin;
