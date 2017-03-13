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
            </Form>
        )
    }
});
HousingInformation = createForm()(HousingInformation);
export default HousingInformation;
