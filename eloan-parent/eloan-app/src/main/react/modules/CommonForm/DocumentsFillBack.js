//返费签单 代收服务费
import React from 'react';
import {
    Button,
    Form,
    Input,
    Modal,
    Select,
    Row,
    Col, 
} from 'antd';
const createForm = Form.create;
const Option = Select.Option;
const FormItem = Form.Item; 
var HousingInformation = React.createClass({
    getInitialState() {
        return {
            
        }
    },
    changeSingleRate(e){
          //返费金额 = 屁贷金额×返费点位
        var returnFee = Number(e.target.value)*this.props.housBillBasicInfo.account;      
        this.props.form.setFieldsValue({returnFee})
        this.props.changeSingleRate(e);
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
        var returnRate='',returnFee='';
        var housBillBasicInfo = this.props.housBillBasicInfo;
        if(housBillBasicInfo){
            returnRate = housBillBasicInfo.singleRate - housBillBasicInfo.repaymentRate;
            returnFee =  returnRate*housBillBasicInfo.account;
        }
        return (
            <Form horizontal  form={this.props.form} style={{ marginTop: "20px" }}>
                <div id="s4">
                    <h2>返费签单</h2>
                    <Row>
                        <Col span="10">
                            <FormItem  {...formItemLayout} label="报单来源：">
                                <Select  disabled={!props.canEdit} {...getFieldProps('projectBelongs') } >
                                    <Option value="0">赚赚自有</Option>
                                    <Option value="1">报单机构报单个人</Option>
                                    <Option value="2">报单个人</Option>
                                </Select>
                            </FormItem>
                        </Col>
                        <Col span="10">
                            <FormItem  {...formItemLayout} label="金融顾问：">
                                <Input disabled={!props.canEdit}  {...getFieldProps('financialAdvisers') }  type="text" autoComplete="off" />
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="10">
                            <FormItem  {...formItemLayout} label="机构名称：">
                                <Select  disabled={!props.canEdit} {...getFieldProps('institutionName') } >
                                    <Option value="0"></Option>
                                    <Option value="1"></Option>
                                    <Option value="2"></Option>
                                </Select>
                            </FormItem>
                        </Col>
                        <Col span="10">
                            <FormItem  {...formItemLayout} label="业务员姓名：">
                                <Input disabled={!props.canEdit}  {...getFieldProps('salesman') }  type="text" autoComplete="off"/ >
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="10">
                            <FormItem  {...formItemLayout} label="返费点位：">
                                <Input disabled={!props.canEdit}  {...getFieldProps('returnRate',{initialValue:String(returnRate),onChange:this.changeSingleRate, rules: [{ required: true, message: '必填' },{pattern:/^\d+\.?\d{0,5}$/,message: '请输入小数'}]}) }  type="number" autoComplete="off" />
                            </FormItem>
                        </Col>
                        <Col span="10">
                            <FormItem  {...formItemLayout} label="返费金额：">
                                <Input disabled={true}  {...getFieldProps('returnFee', {initialValue:returnFee }) }   type="text" autoComplete="off" />
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="10">
                            <FormItem  {...formItemLayout} label="返费期限：">
                                <Input disabled={!props.canEdit}  {...getFieldProps('returnLimit') }  type="text" autoComplete="off" style={{ width: "100", height: "35px", border: "1px solid #d9d9d9" }}/>期
                            </FormItem>
                        </Col>
                        <Col span="10">
                            <FormItem  {...formItemLayout} label="返费卡号：">
                                <Input disabled={!props.canEdit}  {...getFieldProps('returnCard') }  type="text" autoComplete="off" />
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="10">
                            <FormItem  {...formItemLayout} label="开户行：">
                                <Input disabled={!props.canEdit}  {...getFieldProps('returnBank') }  type="text" autoComplete="off"/>
                            </FormItem>
                        </Col>
                    </Row>
                </div>
                <div id="s5">
                    <h2>代收服务费</h2>
                    <Row>
                        <Col span="10">
                            <FormItem  {...formItemLayout} label="服务费金额：">
                                <Input disabled={!props.canEdit}  {...getFieldProps('serviceFee') }  type="text" autoComplete="off" />
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="10">
                            <FormItem  {...formItemLayout} label="姓名：">
                                <Input disabled={!props.canEdit}  {...getFieldProps('serviceName') }  type="text" autoComplete="off" />
                            </FormItem>
                        </Col>
                        <Col span="10">
                            <FormItem  {...formItemLayout} label="放款卡号：">
                                <Input disabled={!props.canEdit}  {...getFieldProps('serviceCard') }  type="text" autoComplete="off" />
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="10">
                            <FormItem  {...formItemLayout} label="开户行：">
                                <Input disabled={!props.canEdit}  {...getFieldProps('serviceBank') }  type="text" autoComplete="off" />
                            </FormItem>
                        </Col>
                    </Row>
                </div>
            </Form>
        )
    }
});
HousingInformation = createForm()(HousingInformation);
export default HousingInformation; 