//房产信息
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
var PropertyPropertiesList = [];
Utils.ajaxData({
  url: '/modules/common/action/ComboAction/queryDic.htm',
  method: 'get',
  data:{
      typeCode:"PROPERTY_TYPE"
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
  data:{
      typeCode:"HOUSING_STATUS"
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
    componentWillReceiveProps(nextProps, nextState) {console.log("componentWillReceiveProps")
        //nextProps.record&&this.props.form.setFieldsValue(nextProps.record);
    },
    componentDidMount(){
        this.props.record&&this.props.form.setFieldsValue(this.props.record);         
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
        return (
            <Form horizontal  form={this.props.form} style={{ marginTop: 20 }}>
                <div id="s1">
                    <Row>
                        <Col span="7">
                            <FormItem  {...formItemLayout} label="处理意见 ：">
                                <Select  disabled={!props.canEdit} {...getFieldProps('whetherOneContact', { rules: [{ required: true, message: '必填' }] }) } >
                                    <Option value="0">登记完成</Option>
                                    <Option value="1">驳回</Option>
                                    <Option value="2">客户放弃</Option>
                                    <Option value="3">拒绝贷款</Option>
                                </Select>
                            </FormItem>
                        </Col>
                    </Row>
                    <Row>
                        <Col span="7">
                           <FormItem  {...formItemLayout} label="备注：">
                               <textarea disabled={!props.canEdit}  {...getFieldProps('remarkComment') }  style={{ width: "550px", border: "1px solid #d9d9d9", borderRadius: "3px", paddingLeft: "5px" }} type="text" autoComplete="off" >
                               </textarea>
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
