//房产信息
import React from 'react';
import {
    Button,
    Form,
    InputNumber,
    Input,
    Modal,
    Select,
    Row,
    Col
} from 'antd';
const createForm = Form.create;
const Option = Select.Option;
const FormItem = Form.Item;
var Region = require('../plugin/Region');
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
var PropertySituationList = [];
Utils.ajaxData({
    url: '/modules/common/action/ComboAction/queryDic.htm',
    method: 'get',
    data: {
        typeCode: "HOUSING_STATUS"
    },
    type: 'json',
    callback: (result) => {
        PropertySituationList = result.data;
    }
});
var HousingInformation = React.createClass({
    getInitialState() {
        return {
            propertyAddressId: '110101'
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
    componentDidMount() {
        this.props.record && this.props.form.setFieldsValue(this.props.record);
    },
    getPropertyPropertiesList() {
        return PropertyPropertiesList.map((item, index) => {
            return <Option key={item.value} >{item.text}</Option>
        })
    },
    getPropertySituationList() {
        return PropertySituationList.map((item, index) => {
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
        var aWorth, secondWorth;
        var mortgageSituation = this.props.form.getFieldsValue().mortgageSituation;
        if (mortgageSituation == 0) {
            aWorth = (
                <Row>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="是否一抵 ：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('whetherOneContact', { rules: [{ required: true, message: '必填' }] }) } >
                                <Option value="0">是</Option>
                                <Option value="1">否</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="抵押权人：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('againstOneMortgagee', { rules: [{ required: true, message: '必填' }] }) } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="金额：">
                            <InputNumber disabled={!props.canEdit}  {...getFieldProps('againstOneAmount', { rules: [{ required: true, message: '必填',type:"number" }] }) }  autoComplete="off" />
                        </FormItem>
                    </Col>
                </Row>
            )
        }
        if (mortgageSituation == 1) {
            secondWorth = (
                <Row>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="是否二抵 ：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('whetherTwoContact', { rules: [{ required: true, message: '必填' }] }) } >
                                <Option value="0">是</Option>
                                <Option value="1">否</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="抵押权人：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('againstTwoMortgagee', { rules: [{ required: true, message: '必填' }] }) } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="金额：">
                            <InputNumber disabled={!props.canEdit}  {...getFieldProps('againstTwoAmount', { rules: [{ required: true, message: '必填' ,type:"number" }] }) } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                </Row>
            )
        }
        return (
            <Form horizontal  form={this.props.form} style={{ marginTop: "20px" }}>
                <Input  {...getFieldProps('id', { initialValue: '' }) } type="hidden"   />
                <Row>
                    <Col span="14">
                        <FormItem  labelCol={{ span: 4 }} wrapperCol={{ span: 17 }} label="房产地址：">
                            <Region {...getFieldProps('propertyAddressId', { initialValue: this.state.propertyAddressId, onChange: this.changeAreaId.bind(this, "propertyAddressId") }) }   disabled={!this.props.canEdit}/>
                        </FormItem>
                    </Col>
                    <Col span="4" >
                        <FormItem style ={{ marginLeft: "-84px" }}>
                            <Input disabled={!props.canEdit} placeholder="详细地址"  {...getFieldProps('propertyAddress', { rules: [{ required: true, message: '必填' }] }) } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="面积(平方)：">
                            <InputNumber disabled={!props.canEdit}  {...getFieldProps('propertyArea', { rules: [{ required: true, message: '必填' ,type:"number"}] }) }  autoComplete="off" />
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout}  label="房产性质：">
                            <Select disabled={!props.canEdit} {...getFieldProps('propertyProperties', { rules: [{ required: true, message: '必填' }] }) } >
                                {this.getPropertyPropertiesList() }
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout}  label="房屋现状：">
                            <Select disabled={!props.canEdit} {...getFieldProps('propertySituation', { rules: [{ required: true, message: '必填' }] }) } >
                                {this.getPropertySituationList() }
                            </Select>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="房产证号：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('houseNumber') } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="房屋抵押情况：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('mortgageSituation', { rules: [{ required: true, message: '必填' ,type:"number"}] }) } >
                                <Option value={0}>一抵</Option>
                                <Option value={1}>二抵</Option>
                            </Select>
                        </FormItem>
                    </Col>
                </Row>
                {aWorth}
                {secondWorth}
            </Form>
        )
    }
});
HousingInformation = createForm()(HousingInformation);
export default HousingInformation;
