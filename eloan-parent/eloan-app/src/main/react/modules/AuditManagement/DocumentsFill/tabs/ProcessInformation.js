//审批信息
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
var PropertySituationList = [];
 
var ProcessInformation = React.createClass({
    getInitialState() {
        return {
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
                    
                    <Col span="19">
                        <FormItem  labelCol={{ span: 5 }} wrapperCol={{ span: 18 }}  label="备注：">
                            <Input disabled={!props.canEdit} rows="2" {...getFieldProps('remarkComment', { rules: [{ required: true, message: '必填' }] }) } type="textarea" autoComplete="off" />
                        </FormItem>
                    </Col>
                </Row>
            </Form>
        )
    }
});
ProcessInformation = createForm()(ProcessInformation);
export default ProcessInformation;
