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
var Investigators = React.createClass({
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
                        <FormItem  {...formItemLayout} label="调查员：">
                            <Input disabled={true}  {...getFieldProps('investigator') } type="text" autoComplete="off" />
                        </FormItem>
                    </Col>
                    <Col span="7">
                        <FormItem  {...formItemLayout} label="调查日期：">
                            <DatePicker disabled={!props.canEdit}  {...getFieldProps('surveyTime', { rules: [{ required: true, message: '必填', type: "date" }] }) } />
                        </FormItem>
                    </Col>
                </Row>

            </Form>
        )
    }
});
Investigators = createForm()(Investigators);
export default Investigators;
