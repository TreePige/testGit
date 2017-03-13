//权证下户--下户信息
import React from 'react';
import {
    Button,
    Form,
    Input,
    InputNumber,
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
var PropertyPropertiesList = [];
Utils.ajaxData({
    url: '/getDicts.htm?type=INMATE',
    method: 'get',
    data: {
        // typeCode: "PROPERTY_TYPE"
    },
    type: 'json',
    callback: (result) => {
        PropertyPropertiesList = result.data;
    }
});
var HousingOrientationList = [];
Utils.ajaxData({
    url: '/getDicts.htm?type=HOUSE_ORIENTATION',
    method: 'get',
    type: 'json',
    callback: (result) => {
        HousingOrientationList = result.data;

    }
});
var LivingConditionsList = [];
Utils.ajaxData({
    url: '/getDicts.htm?type=LIVE_STATE',
    method: 'get',
    type: 'json',
    callback: (result) => {
        LivingConditionsList = result.data;

    }
});
var NextDoorInformation = React.createClass({
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
    getPropertyPropertiesList() {
        return PropertyPropertiesList.map((item, index) => {
            return <Option key={item.value} >{item.text}</Option>
        })
    },
    getHousingOrientationList() {
        return HousingOrientationList.map((item, index) => {
            return <Option key={item.value} >{item.text}</Option>
        })
    },
    getLivingConditionsList() {
        return LivingConditionsList.map((item, index) => {
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
                    <Col span="14">
                        <FormItem  labelCol={{ span: 4 }} wrapperCol={{ span: 17 }} label="房产地址：">
                            <Region {...getFieldProps('propertyAddressId', { initialValue: this.state.propertyAddressId, onChange: this.changeAreaId.bind(this, "propertyAddressId") }) }   disabled={!this.props.canEdit}/>
                        </FormItem>
                    </Col>
                    <Col span="4" >
                        <FormItem style ={{ marginLeft: "-84px" }}>
                            <Input disabled={!props.canEdit} placeholder=""  {...getFieldProps('propertyAddress', { rules: [{ required: true, message: '必填'}] }) } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="小区名称：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('communityName', { rules: [{ required: true, message: '必填'}] }) } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="楼牌号是否与房本一致 ：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('buildingBrands', { rules: [{ required: true, message: '必填',type:"number"}] }) } >
                                <Option value={0}>是</Option>
                                <Option value={1}>否</Option>
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout}  label="居住人含：">
                            <Select disabled={!props.canEdit} {...getFieldProps('livingPeople') } >
                                {this.getPropertyPropertiesList() }
                            </Select>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="装修状况：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('furnishingStatus', { rules: [{ required: true, message: '必填',type:"number"}] }) } >
                                <Option value={0}>精装</Option>
                                <Option value={1}>简装</Option>
                            </Select>
                        </FormItem> 
                    </Col>
                    <Col span="6">
                        <FormItem  {...formItemLayout} label="房室户型 (室)：" labelCol={{ span: "10" }} wrapperCol={{ span: "11" }}>
                            <InputNumber disabled={!props.canEdit}  {...getFieldProps('room', { rules: [{ required: true, message: '必填', type: 'number'}] }) }  autoComplete="off" />
                        </FormItem>

                    </Col>
                    <Col span="5" style={{ marginLeft: "-20px" }}>
                        <FormItem  {...formItemLayout} label="(厅)" labelCol={{ span: "5" }} wrapperCol={{ span: "11" }}>
                            <InputNumber disabled={!props.canEdit}  {...getFieldProps('hall', { rules: [{ required: true, message: '必填', type: 'number'}] }) }  autoComplete="off" />
                        </FormItem>
                    </Col>
                    <Col span="5" style={{ marginLeft: "-30px" }}>
                        <FormItem  {...formItemLayout} label="(卫)" labelCol={{ span: "5" }} wrapperCol={{ span: "11" }}>
                            <InputNumber disabled={!props.canEdit}  {...getFieldProps('guard', { rules: [{ required: true, message: '必填', type: 'number'}] }) }  autoComplete="off" />
                        </FormItem>
                    </Col>

                </Row>
                <Row>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="所在楼层：">
                            <InputNumber disabled={!props.canEdit}  {...getFieldProps('floor', { rules: [{ required: true, message: '必填', type: 'number'}] }) } autoComplete="off" />
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="总共层数：">
                            <InputNumber disabled={!props.canEdit}  {...getFieldProps('totalFloors', { rules: [{ required: true, message: '必填', type: 'number'}] }) }  autoComplete="off" />
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout}  label="房屋朝向：">
                            <Select disabled={!props.canEdit} {...getFieldProps('housingOrientation') } >
                                {this.getHousingOrientationList() }
                            </Select>
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="7">
                        <FormItem  {...formItemLayout}  label="居住情况：">
                            <Select disabled={!props.canEdit} {...getFieldProps('livingConditions') } >
                                {this.getLivingConditionsList() }
                            </Select>
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="租户签署放弃优先购买权：">
                            <Select  disabled={!props.canEdit} {...getFieldProps('purchasingPower', { rules: [{ required: true, message: '必填',type:"number"}] }) } >
                                <Option value={0}>是</Option>
                                <Option value={1}>否</Option>
                            </Select>
                        </FormItem>
                    </Col>
                </Row>


            </Form>
        )
    }
});
NextDoorInformation = createForm()(NextDoorInformation);
export default NextDoorInformation;
