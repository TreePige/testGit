//放款单据填写基本信息
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
var HousingInformation = React.createClass({
    getInitialState() {
        return {
            propertyAddressId: '110101'
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
                span: 8
            },
            wrapperCol: {
                span: 15
            },
        };
        return (
            <Form horizontal  form={this.props.form} style={{ marginTop: "20px" }}>             
                    <Row>
                        <Col span="10">
                            <FormItem  {...formItemLayout} label="项目编号：">
                                <Input disabled={true}  {...getFieldProps('projectCode') }  type="text" autoComplete="off" />
                            </FormItem>
                        </Col>
                        <Col span="10">
                            <FormItem  {...formItemLayout} label="客户姓名：">
                                <Input  disabled={true}  {...getFieldProps('customerName') }  type="text" autoComplete="off" />
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="10">
                            <FormItem  {...formItemLayout} label="批贷金额：">
                                <Input  disabled={true} {...getFieldProps('account') }  type="text" autoComplete="off" />
                            </FormItem>
                        </Col>
                        <Col span="10">
                            <FormItem  {...formItemLayout} label="借款限制：">
                                <Input  disabled={true} {...getFieldProps('timeLimit') }  type="text" autoComplete="off" />
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="10">
                            <FormItem  {...formItemLayout} label="底点利率：">
                                <Input  disabled={true}  {...getFieldProps('repaymentRate') }  type="text" autoComplete="off" />
                            </FormItem>
                        </Col>
                        <Col span="10">
                            <FormItem  {...formItemLayout} label="成单利率：">
                                <Input disabled={true}  {...getFieldProps('singleRate') }  type="text" autoComplete="off"/ >
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="10">
                            <FormItem  {...formItemLayout} label="首期利息：">
                                <Input disabled={true} {...getFieldProps('firstInterest') } type="text" autoComplete="off" />
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="10">
                            <FormItem  {...formItemLayout} label="下户费收取形式：">
                                <Select  disabled={true} {...getFieldProps('collectionForm') } >
                                    <Option value="0">线下收取</Option>
                                    <Option value="1">放款时扣除</Option>
                                </Select>
                            </FormItem>
                        </Col>
                        <Col span="10">
                            <FormItem  {...formItemLayout} label="下户费金额：">
                                <Input disabled={true}  {...getFieldProps('receivableAccount') }  type="text" autoComplete="off" />
                            </FormItem>
                        </Col>
                    </Row> 
            </Form>
        )
    }
});
HousingInformation = createForm()(HousingInformation);
export default HousingInformation;
