//借款需求
import React from 'react';
import {
    Button,
    Form,
    Input,
    Modal,
    Select,
    Row,
    Col,
    InputNumber,
    DatePicker
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const objectAssign = require('object-assign');
const NavLine = require("../utils/NavLine");
var roleId = window.roleId;
var ProductIdList = [];
Utils.ajaxData({
    url: '/modules/common/action/ComboAction/getAllProductSimpleInfos.htm',
    method: 'get',
    type: 'json',
    callback: (result) => {
        ProductIdList = result.data;
    }
});
var repaymentTypelist = [];
Utils.ajaxData({
    url: '/modules/common/action/ComboAction/queryDic.htm',
    method: 'get',
    data: {
        typeCode: "REPAYMENT_TYPE"
    },
    type: 'json',
    callback: (result) => {
        repaymentTypelist = result.data;
    }
});

var BorrowingNeeds = React.createClass({
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
    onChange(name, value) {
        console.log(value)
        console.log(name)
        if (name == "productId") {
            Utils.ajaxData({
                url: '/modules/common/action/ComboAction/queryProductAbout.htm',
                method: 'post',
                data: {
                    productId: value
                },
                type: 'json',
                callback: (result) => {
                    this.props.form.setFieldsValue(result.data)
                }
            });
        }
    },
    getProductIdList() {
        return ProductIdList.map((item, index) => {
            return <Option key={item.value} >{item.text}</Option>
        })
    },
    getrepaymentTypelist() {
        return repaymentTypelist.map((item, index) => {
            return <Option key={item.value} >{item.text}</Option>
        })
    },
    getProjectBelongslist() {
        return ProjectBelongslist.map((item, index) => {
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

        var finan;
        var instit;
        var projectBelongs = this.props.form.getFieldsValue().projectBelongs;
        if (projectBelongs == 2) {
            instit = (
                <Row>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="机构名称：">
                            <Input disabled={true}  {...getFieldProps('institutionName') } type="text" autoComplete="off"  />
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="业务员：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('salesman') } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="联系电话(报单机构)：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('mobile') } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                </Row>
            )
        } else if (projectBelongs == 3) {
            finan = (
                <Row>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="金融顾问：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('financialAdvisers') } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="联系电话(报单人)：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('mobile') } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                </Row>
            )
        }
        return (
            <Form horizontal  form={this.props.form} style={{ marginTop: "20px" }}>
                <Input {...getFieldProps('id') } type="hidden" autoComplete="off" />
                <Row>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="联系电话：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('phoneNumber') } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="申请额度(万)：">
                            <InputNumber disabled={!props.canEdit}  {...getFieldProps('account', { rules: [{ required: true, message: '必填', type: 'number' }] }) }  autoComplete="off" />
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="借款日期：">
                            <DatePicker disabled={!props.canEdit}  {...getFieldProps('borrowingTime', { rules: [{ required: true, message: '必填', type: "date" }] }) } style={{ width: "193px" }}/>
                        </FormItem>
                    </Col>

                </Row>
                <Row>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="产品名称：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('productId', { rules: [{ required: true, message: '必填' }], onChange: this.onChange.bind(this, "productId") }) } >
                                {this.getProductIdList() }
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="还款方式：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('repaymentType', { rules: [{ required: true, message: '必填' }] }) } >
                                {this.getrepaymentTypelist() }
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="借款期限(月)：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('timeLimit') } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>

                </Row>
                <Row>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="底点利率(%)：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('repaymentRate') } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="成单利率(%)：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('singleRate') } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="项目所属：">
                            <Select  disabled={true} {...getFieldProps('projectBelongs') } >
                                <Option  value={1}>赚赚自有</Option>
                                <Option  value={2}>报单机构</Option>
                                <Option  value={3}>报单个人</Option>
                            </Select>
                        </FormItem>
                    </Col>
                </Row>
                {finan}
                {instit}
                <Row>
                    <Col span="7">
                        <FormItem  {...formItemLayout}  label="借款用途：" style={{ width: "800px" }} labelCol={{ span: "3" }}>
                            <Input disabled={!props.canEdit}   {...getFieldProps('borrowUse', { rules: [{ required: true, message: '必填' }] }) } type="textarea" autoComplete="off" />
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="还款来源：" style={{ width: "800px" }} labelCol={{ span: "3" }}>
                            <Input disabled={!props.canEdit}  {...getFieldProps('repaymentSource', { rules: [{ required: true, message: '必填' }] }) } type="textarea" autoComplete="off" />
                        </FormItem>
                    </Col>
                </Row>
            </Form>
        );
    }
});
BorrowingNeeds = createForm()(BorrowingNeeds);
export default BorrowingNeeds;