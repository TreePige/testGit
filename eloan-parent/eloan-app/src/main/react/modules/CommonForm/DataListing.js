//权证下户--资料清单
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
var Region = require('../plugin/Region');
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
var DataListing = React.createClass({
    getInitialState() {
        return {
            residentialAddressId: '110101',
        }
    },
    changeAreaId(name, areaId) {
        this.setState({
            [name]: areaId
        })
    },
    handleReset() {
        this.refs.validation.reset();
        this.setState(this.getInitialState());
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
        return (
            <Form horizontal  form={this.props.form} style={{ marginTop: "20px" }}>
                <Input {...getFieldProps('id') } type="hidden" autoComplete="off" />
                <Row>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="户口本：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('accountBook') } >
                                <Option value={0}>有</Option>
                                <Option value={1}>无</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="结婚证：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('marriageCertificate') } >
                                 <Option value={0}>有</Option>
                                <Option value={1}>无</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="离婚证：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('divorceCertificate') } >
                                 <Option value={0}>有</Option>
                                <Option value={1}>无</Option>
                            </Select>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="离婚协议：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('divorceAgreement') } >
                                 <Option value={0}>有</Option>
                                <Option value={1}>无</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="民事调解书：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('civilMediation') } >
                                 <Option value={0}>有</Option>
                                <Option value={1}>无</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="法院判决书：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('courtVerdict') } >
                                <Option value={0}>有</Option>
                                <Option value={1}>无</Option>
                            </Select>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="是否唯一住房：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('onlyHousing') } >
                                 <Option value={0}>有</Option>
                                <Option value={1}>无</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="房产证：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('deed') } >
                                <Option value={0}>有</Option>
                                <Option value={1}>无</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="购房合同：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('purchaseContract') } >
                                 <Option value={0}>有</Option>
                                <Option value={1}>无</Option>
                            </Select>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="租赁合同：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('leaseContract') } >
                                 <Option value={0}>有</Option>
                                <Option value={1}>无</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="抵押贷款合同：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('mortgageLoanContract') } >
                                 <Option value={0}>有</Option>
                                <Option value={1}>无</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="贷款卡：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('loanCard') } >
                                 <Option value={0}>有</Option>
                                <Option value={1}>无</Option>
                            </Select>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="征信报告：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('creditReport') } >
                                <Option value={0}>有</Option>
                                <Option value={1}>无</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="满二或满五：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('twoOrFive') } >
                                <Option value={0}>满二</Option>
                                <Option value={1}>满五</Option>
                            </Select>
                        </FormItem>
                    </Col>
                </Row>

            </Form>
        )
    }
});
DataListing = createForm()(DataListing);
export default DataListing;
