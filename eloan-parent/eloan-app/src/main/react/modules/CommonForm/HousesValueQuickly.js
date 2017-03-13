//权证下户--房屋快出值信息
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
var HousesValueQuickly = React.createClass({
    getInitialState() {
        return {

        }
    },
    handleReset() {
        this.refs.validation.reset();
        this.setState(this.getInitialState());
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
                        <FormItem  {...formItemLayout} label="学校：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('school') } >
                                <Option value={0}>有</Option>
                                <Option value={1}>无</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="医院：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('hospital') } >
                                <Option value={0}>有</Option>
                                <Option value={1}>无</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="购物等配套情况：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('shopping') } >
                                <Option value={0}>有</Option>
                                <Option value={1}>无</Option>
                            </Select>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="银行可否代为提前还款：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('bankPrepayment') } >
                                <Option value={0}>是</Option>
                                <Option value={1}>否</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="银行可否代为领取解押材料：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('bankPrepayment') } >
                                <Option value={0}>是</Option>
                                <Option value={1}>否</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="房屋快出值：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('housingValueFaster') } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="房产交易产生的税费：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('propertyTaxes') } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="19">
                        <FormItem  labelCol={{ span: 3 }} wrapperCol={{ span: 18 }}  label="备注：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('remarkComment') } type="textarea" autoComplete="off" />
                        </FormItem>
                    </Col>
                </Row>

            </Form>
        )
    }
});
HousesValueQuickly = createForm()(HousesValueQuickly);
export default HousesValueQuickly;
