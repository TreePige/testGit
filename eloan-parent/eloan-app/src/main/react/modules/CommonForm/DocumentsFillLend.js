//放宽单据填写-放款单
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
        var account='';
        if(this.props.housBillBasicInfo){
            account = this.props.housBillBasicInfo.account;
        }

        return (
            <Form horizontal  form={this.props.form} style={{ marginTop: "20px" }}>  
                    <Row>
                         <Col span="10">
                           <FormItem  {...formItemLayout} label="开户人姓名：">
                               <Input disabled={!props.canEdit}  {...getFieldProps('accountHolderName',{rules: [{ required: true, message: '必填' }]}) }  type="text" autoComplete="off" />                           
                           </FormItem>
                        </Col>
                        <Col span="10">
                           <FormItem  {...formItemLayout} label="放款卡号：">
                               <Input disabled={!props.canEdit}  {...getFieldProps('cardid',{rules: [{ required: true, message: '必填' }]}) }   type="text" autoComplete="off" />
                            </FormItem>
                        </Col>                       
                    </Row>
                     <Row>
                         <Col span="10">
                           <FormItem  {...formItemLayout} label="开户行：">
                               <Input disabled={!props.canEdit}  {...getFieldProps('bankFlag',{rules: [{ required: true, message: '必填' }]}) }  type="text" autoComplete="off" />
                              
                           </FormItem>
                        </Col>
                        <Col span="10">
                           <FormItem  {...formItemLayout} label="放款金额：">
                               <Input disabled={true}  {...getFieldProps('account',{initialValue:account}) } type="text" autoComplete="off" />
                              
                           </FormItem>
                        </Col>                       
                    </Row>
                     <Input {...getFieldProps('id') } type="hidden"/>
            </Form>
        )
    }
});
HousingInformation = createForm()(HousingInformation);
export default HousingInformation;
