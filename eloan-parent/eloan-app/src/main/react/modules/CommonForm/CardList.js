//新增打款
import React from 'react';
import {
    Button,
    Form,
    Input,
    Modal,
    Select,
    Upload,
    Table,
    message,
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
var CardList = React.createClass({
    getInitialState() {
        return {
            orgData: [],
            keys: [],
            showAddOrg: false
        }
    },
    remove(k) {
        const { form } = this.props;
        var id = form.getFieldValue(`${k}id`);//取到记录的id
        if (id) {
            //删除后台数据
            Utils.ajaxData({
                url: '/modules/audit/HousBillsAction/deleteById.htm',
                method: 'get',
                data: {
                    id: id//返回给后台的参数 
                },
                callback: (result) => {
                    console.log(result)
                }
            });
        }
        let keys = form.getFieldValue('keys');
        keys = keys.filter((key) => {
            return key !== k;
        });
        form.setFieldsValue({
            keys,
        });
    },
    add() {
        const { form } = this.props;
        let keys = form.getFieldValue('keys');
        keys = keys.concat(this.selectVlaue++);
        form.setFieldsValue({
            keys
        });
    },
    selectVlaue: 0,
    render() {
        const { getFieldProps, getFieldValue } = this.props.form;
        getFieldProps('keys', {
            initialValue: [],
        });

        const formItems = getFieldValue('keys').map((k) => {

            return (<tr key={k}>
                <td key={`k1`}>
                    <FormItem >
                        <Input {...getFieldProps(`${k}accountHolderName`, {
                            rules: [{
                                required: true, message: "  "
                            }]
                        }) } disabled={!this.props.canEdit}  type="text"/>
                    </FormItem>
                </td>
                <td key={`k2`}>
                    <FormItem >
                        <Input {...getFieldProps(`${k}cardid`, {
                            rules: [{
                                required: true, message: "  "
                            }]
                        }) } disabled={!this.props.canEdit}  type="text"/>
                    </FormItem>
                </td>
                <td key={`k3`}>
                    <FormItem >
                        <Input {...getFieldProps(`${k}account`, {
                            rules: [{
                                required: true, message: "  "
                            }]
                        }) } disabled={!this.props.canEdit}  type="text"/>
                    </FormItem>
                </td>
                <td key={`k4`}>
                    <FormItem >
                        <Input {...getFieldProps(`${k}bankFlag`, {
                            rules: [{
                                required: true, message: "  "
                            }]
                        }) } disabled={!this.props.canEdit}  type="text"/>
                    </FormItem>
                </td>
                <td key={`k5`}>
                    <Input {...getFieldProps(`${k}id`) } type="hidden"/>
                    <a onClick={() => this.remove(k) }>删除</a>
                </td>
            </tr >
            );
        });
        var sumAccount = '';
        var housBillBasicInfo = this.props.housBillBasicInfo;
        if (housBillBasicInfo) {
            sumAccount = housBillBasicInfo.account * housBillBasicInfo.repaymentRate;
        }
        const formItemLayout = {
            labelCol: {
                span: 3
            },
            wrapperCol: {
                span: 15
            },
        };
        return <Form  form={this.props.form} style={{ marginTop: 15 }}>

            <FormItem  {...formItemLayout} label="打款总额：">
                <Input disabled={true}  {...getFieldProps('sumAccount', { initialValue: sumAccount }) }   type="text" autoComplete="off"/>
            </FormItem>
            <Button  disabled={!this.props.canEdit} onClick={this.add} style={{ color: '#00aaee', borderColor: '#00aaee' }}>新增打款卡</Button>
            <table style={{ width: 800, marginLeft: 25 }} className="addTable" ref="addTable" >
                <tbody>
                    <tr>
                        <td>
                            开户人姓名
                        </td>
                        <td>
                            打款卡号
                        </td>
                        <td>
                            打款金额
                        </td>
                        <td>
                            开户行
                        </td>
                        <td >
                            操作
                        </td>
                    </tr>
                    {getFieldValue('keys').length ? formItems : null}
                </tbody>
            </table >
        </Form>
    }
});
CardList = createForm()(CardList);
export default CardList;