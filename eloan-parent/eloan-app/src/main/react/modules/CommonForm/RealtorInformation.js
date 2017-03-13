//权证下户--房屋中介信息
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
var RealtorInformation = React.createClass({
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
                        <FormItem  {...formItemLayout} label="门名名称：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('doorName') } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="负责人姓名：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('principalName') } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="负责人联系方式：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('principalPhone') } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                </Row>
                <Row>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="税款详情：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('taxDetails') } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="正常价格(净得)/万：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('normalPrice') } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="快速成交价格(净得)/万：">
                            <Input disabled={!props.canEdit}  {...getFieldProps('fastTransactionPrice') } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                </Row>

            </Form>
        )
    }
});
RealtorInformation = createForm()(RealtorInformation);
export default RealtorInformation;
