import React from 'react';
import {
    Button,
    Form,
    Input,
    Modal,
    Select,
    Row,
    Col,
    Radio,
    Checkbox,
    DatePicker
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const objectAssign = require('object-assign');
var Reflux = require('reflux');
var FormDataStore = require('./stores/FormDataStore');
const NavLine = require("../utils/NavLine");
const RadioGroup = Radio.Group;
const CheckboxGroup = Checkbox.Group;
var Region = require('../plugin/Region');
var typeIdList = [];
Utils.ajaxData({
    url: '/getDicts.htm?type=BANK_TYPE',
    method: 'get',
    type: 'json',
    callback: (result) => {
        typeIdList = result.data;
    }
});
var AddlWin = React.createClass({
    mixins: [
        Reflux.listenTo(FormDataStore, "onMatch")
    ],
    onMatch(data) {
        console.log(data)
        if (data.formData) {
            this.props.form.setFieldsValue(data.formData);
        } else this.props.form.resetFields();
    },
    getInitialState() {
        return {
            status: {
                automaticWithholdingTime: "",
            },
            areaId1: '110101',
            areaId2: '110101',
            formData: {}
        };
    },
    changeAreaId(name, areaId) {
        this.setState({
            [name]: areaId
        })
    },
    handleCancel() {
        this.props.form.resetFields();
        this.props.hideModal();
    },
    componentDidMount() {
        var record = {};
        if (this.props.record) {
            var record = this.props.record;
        }
        this.props.form.setFieldsValue(record);
    },
    gettypeIdList() {
        return typeIdList.map((item, index) => {
            return <Option key={item.value} >{item.text}</Option>
        })
    },
    onChangea(checkedValues) {
        console.log('checked = ', checkedValues);
    },
    navLineData: {
        "房产信息": "#s1",
        "婚姻信息": "#s2",
        "征信信息": "#s3",
        "风控信息": "#s4",
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
        var disabled1, disabled2, disabled3, disabled4, disabled5, disabled6, disabled7, danbaodk, daizje1, daizbs, yuqi;
        console.log(this.props.form.getFieldsValue())
        var property = this.props.form.getFieldsValue().property;
        var ccc = this.props.form.getFieldsValue().ccc;
        var disabled1 = disabled2 = property == "3" ? false : true;
        var disabled3 = property == "2" ? false : true;
        var yuqijine = this.props.form.getFieldsValue().yuqijine;
        var yuqi = yuqijine == true ? false : true;
        var danbao = this.props.form.getFieldsValue().danbao;
        var danbaodk = danbao == true ? false : true;
        var daiz = this.props.form.getFieldsValue().daiz;
        if (daiz == true) {
            daizbs = false,
                daizje1 = false
        } else {
            daizbs = true,
                daizje1 = true
        }
        if (ccc == 0) {
            disabled4 = false,
                disabled5 = true,
                disabled6 = true,
                disabled7 = true
        } else if (ccc == 1) {
            disabled4 = true,
                disabled5 = true,
                disabled6 = true,
                disabled7 = true
        } else if (ccc == 2) {
            disabled4 = false,
                disabled5 = false,
                disabled6 = false,
                disabled7 = true
        } else if (ccc == 3) {
            disabled4 = false,
                disabled5 = true,
                disabled6 = true,
                disabled7 = false
        } else if (ccc == 4 || ccc == null) {
            disabled4 = true,
                disabled5 = true,
                disabled6 = true,
                disabled7 = true
        }
        return (
            <div style={{ position: "relative" }}>
                <div className="navLine-wrap" onScroll={NavLineUtils._handleSpy.bind(this, this.navLineData) }>
                    <div className=" navLine-wrap-left" span="22">
                        <Form horizontal  form={this.props.form} style={{ marginTop: "20px" }}>
                            <Input  {...getFieldProps('id', { initialValue: '' }) } type="hidden"   />
                            <div id="s1">
                                <h2>房产信息</h2>
                                <Row>
                                    <Col span="14">
                                        <FormItem  labelCol={{ span: 4 }} wrapperCol={{ span: 19 }} label="房产地址：">
                                            <Region areaId={this.state.areaId1} ref="region1" onAreaChange={this.changeAreaId.bind(this, "areaId1") } disabled={!this.props.canEdit}/>
                                        </FormItem>
                                    </Col>
                                    <Col span="4" >
                                        <FormItem style={{ marginLeft: "-130px" }}>
                                            <Input disabled={!props.canEdit} placeholder="详细地址"  {...getFieldProps('n3mOverdardueCnt') } type="text" autoComplete="off" />
                                        </FormItem>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="房权证号：">
                                            <Input disabled={!props.canEdit}  {...getFieldProps('creditItgaHgstLine') } type="text" autoComplete="off" />
                                        </FormItem>
                                    </Col>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout}  label="房产性质：">
                                            <Select disabled={!props.canEdit}  {...getFieldProps('property', { rules: [{ required: true, message: '必填' }] }) } >
                                                <Option value="0">商品房</Option>
                                                <Option value="1">按经济适用房管理</Option>
                                                <Option value="2">房改房</Option>
                                                <Option value="3">经济适用房</Option>
                                            </Select>
                                        </FormItem>
                                    </Col>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="经适房房本时间：">
                                            <DatePicker  {...getFieldProps('capitalSupplyTime') } style={{ width: "210px" }} disabled={disabled1}/>
                                        </FormItem>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="经适房购房发票时间：">
                                            <DatePicker   {...getFieldProps('capitalSupplyTime') } style={{ width: "210px" }} disabled={disabled2}/>
                                        </FormItem>
                                    </Col>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="房改房上市证明：">
                                            <Select   {...getFieldProps('state') }  disabled={disabled3}>
                                                <Option value="0">是</Option>
                                                <Option value="1">否</Option>
                                            </Select>
                                        </FormItem>
                                    </Col>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="规划用途：">
                                            <Select  disabled={!props.canEdit} {...getFieldProps('state', { rules: [{ required: true, message: '必填' }] }) } >
                                                <Option value="0">是</Option>
                                                <Option value="1">否</Option>
                                            </Select>
                                        </FormItem>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="钥匙盘查询：">
                                            <Select  disabled={!props.canEdit} {...getFieldProps('state', { rules: [{ required: true, message: '必填' }] }) } >
                                                <Option value="0">是</Option>
                                                <Option value="1">否</Option>
                                            </Select>
                                        </FormItem>
                                    </Col>
                                </Row>
                            </div>
                            <div id="s2">
                                <h2>婚姻信息</h2>
                                <Row>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="婚姻状况：">
                                            <Select  disabled={!props.canEdit} {...getFieldProps('ccc', { rules: [{ required: true, message: '必填' }] }) } >
                                                <Option value="0">已婚</Option>
                                                <Option value="1">未婚</Option>
                                                <Option value="2">离异</Option>
                                                <Option value="3">再婚</Option>
                                                <Option value="4">丧偶</Option>
                                            </Select>
                                        </FormItem>
                                    </Col>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="证件日期：">
                                            <DatePicker   {...getFieldProps('capitalSupplyTime') }  style={{ width: "210px" }} disabled={disabled4}/>
                                        </FormItem>
                                    </Col>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="离婚协议：">
                                            <Select   {...getFieldProps('state', { rules: [{ required: true, message: '必填' }] }) }  disabled={disabled5}>
                                                <Option value="0">是</Option>
                                                <Option value="1">否</Option>
                                            </Select>
                                        </FormItem>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="法院判决：">
                                            <Select   {...getFieldProps('state', { rules: [{ required: true, message: '必填' }] }) }  disabled={disabled6}>
                                                <Option value="0">是</Option>
                                                <Option value="1">否</Option>
                                            </Select>
                                        </FormItem>
                                    </Col>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="房本日期：">
                                            <DatePicker   {...getFieldProps('capitalSupplyTime') } style={{ width: "210px" }} disabled={disabled7}/>
                                        </FormItem>
                                    </Col>
                                </Row>
                            </div>
                            <div id="s3">
                                <h2>征信信息</h2>
                                <Row>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout}  style={{ marginLeft: "30px", marginTop: "15px" }}>
                                            <Checkbox className="ant-checkbox-inline" value="0" {...getFieldProps('sleepy', { valuePropName: 'checked' }) }>征信良好</Checkbox>
                                            <Checkbox className="ant-checkbox-inline" value="1" {...getFieldProps('yuqijine', { valuePropName: 'checked' }) }>当前逾期</Checkbox>
                                        </FormItem>
                                    </Col>
                                    <Col span="4" >
                                        <FormItem  {...formItemLayout} label="逾期金额："  style={{ marginLeft: "-140px", marginTop: "15px"}}>
                                            <Input disabled={yuqi} {...getFieldProps('yuqi') } style={{width:"210px" }} type="text" autoComplete="off" />
                                        </FormItem>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col span="4">
                                        <FormItem  {...formItemLayout}  style={{ marginLeft: "30px" }}>
                                            <Checkbox className="ant-checkbox-inline" value="0" {...getFieldProps('danbao', { valuePropName: 'checked' }) }>担保性贷款</Checkbox>
                                        </FormItem>
                                    </Col>
                                    <Col span="8">
                                        <FormItem  {...formItemLayout} label="担保性贷款金额：" style={{ marginLeft: "-20px" }}>
                                            <Input disabled={danbaodk} style={{ width: "210px" }} {...getFieldProps('creditItgaHgstLine') } type="text" autoComplete="off" />
                                        </FormItem>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col span="4">
                                        <FormItem  {...formItemLayout}  style={{ marginLeft: "30px" }}>
                                            <Checkbox className="ant-checkbox-inline" value="0" {...getFieldProps('daiz', { valuePropName: 'checked' }) }>呆账</Checkbox>
                                        </FormItem>
                                    </Col>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="呆账笔数：" >
                                            <Input disabled={daizbs} {...getFieldProps('creditItgaHgstLine') } type="text" autoComplete="off" />
                                        </FormItem>
                                    </Col>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="呆账金额：">
                                            <Input disabled={daizje1} {...getFieldProps('creditItgaHgstLine') } type="text" autoComplete="off" />
                                        </FormItem>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col span="4">
                                        <FormItem  {...formItemLayout}  style={{ marginLeft: "30px" }}>
                                            <Checkbox className="ant-checkbox-inline" value="0" {...getFieldProps('eeee', { valuePropName: 'checked' }) }>近2年连3累6</Checkbox>
                                        </FormItem>
                                    </Col>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="备注：" >
                                            <Input disabled={!props.canEdit} {...getFieldProps('creditItgaHgstLine') } type="text" autoComplete="off" />
                                        </FormItem>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col span="10">
                                        <FormItem label="证件照片相符：" {...formItemLayout} labelCol={{ span: 6 }} wrapperCol={{ span: 18 }} >
                                            <Checkbox className="ant-checkbox-inline"  {...getFieldProps('sleep1', { valuePropName: 'checked' }) }>身份证</Checkbox>
                                            <Checkbox className="ant-checkbox-inline"  {...getFieldProps('sleep2', { valuePropName: 'checked' }) }>结婚证</Checkbox>
                                            <Checkbox className="ant-checkbox-inline"  {...getFieldProps('sleep3', { valuePropName: 'checked' }) }>离婚证</Checkbox>
                                        </FormItem>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col span="10">
                                        <FormItem label="证件信息一致：" {...formItemLayout} labelCol={{ span: 6 }} wrapperCol={{ span: 18 }}  >
                                            <Checkbox className="ant-checkbox-inline" {...getFieldProps('sleep4', { valuePropName: 'checked' }) }>身份证</Checkbox>
                                            <Checkbox className="ant-checkbox-inline" {...getFieldProps('sleep5', { valuePropName: 'checked' }) }>结婚证</Checkbox>
                                            <Checkbox className="ant-checkbox-inline" {...getFieldProps('sleep6', { valuePropName: 'checked' }) }>离婚证</Checkbox>
                                            <Checkbox className="ant-checkbox-inline" {...getFieldProps('sleep7', { valuePropName: 'checked' }) }>户口本</Checkbox>
                                        </FormItem>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="户口与房屋所在地是否一致：">
                                            <Select  disabled={!props.canEdit} {...getFieldProps('state') } >
                                                <Option value="0">是</Option>
                                                <Option value="1">否</Option>
                                            </Select>
                                        </FormItem>
                                    </Col>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="抵贷是否一致：">
                                            <Select  disabled={!props.canEdit} {...getFieldProps('state') } >
                                                <Option value="0">是</Option>
                                                <Option value="1">否</Option>
                                            </Select>
                                        </FormItem>
                                    </Col>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="抵贷实际用款人：">
                                            <Input disabled={!props.canEdit} {...getFieldProps('creditItgaHgstLine') } type="text" autoComplete="off" />
                                        </FormItem>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="是否共借：">
                                            <Select  disabled={!props.canEdit} {...getFieldProps('state') } >
                                                <Option value="0">是</Option>
                                                <Option value="1">否</Option>
                                            </Select>
                                        </FormItem>
                                    </Col>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="是否有保证人：">
                                            <Select  disabled={!props.canEdit} {...getFieldProps('state') } >
                                                <Option value="0">是</Option>
                                                <Option value="1">否</Option>
                                            </Select>
                                        </FormItem>
                                    </Col>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="是否家庭名下唯一房产：">
                                            <Select  disabled={!props.canEdit} {...getFieldProps('state') } >
                                                <Option value="0">是</Option>
                                                <Option value="1">否</Option>
                                            </Select>
                                        </FormItem>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="房本是否满五年：">
                                            <Select  disabled={!props.canEdit} {...getFieldProps('state') } >
                                                <Option value="0">是</Option>
                                                <Option value="1">否</Option>
                                            </Select>
                                        </FormItem>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col span="14">
                                        <FormItem  labelCol={{ span: 4 }} wrapperCol={{ span: 19 }} label="备用房屋地址：">
                                            <Region areaId={this.state.areaId2} ref="region2" onAreaChange={this.changeAreaId.bind(this, "areaId2") } disabled={!this.props.canEdit}/>
                                        </FormItem>
                                    </Col>
                                    <Col span="4" >
                                        <FormItem style={{ marginLeft: "-130px" }}>
                                            <Input disabled={!props.canEdit} placeholder="详细地址"  {...getFieldProps('n3mOverdardueCnt') } type="text" autoComplete="off" />
                                        </FormItem>
                                    </Col>
                                </Row>
                            </div>
                            <div id="s4">
                                <h2>风控信息</h2>
                                <Row>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="姓名身份证是否一致：">
                                            <Select  disabled={!props.canEdit} {...getFieldProps('state') } >
                                                <Option value="0">是</Option>
                                                <Option value="1">否</Option>
                                            </Select>
                                        </FormItem>
                                    </Col>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="关联企业数量：">
                                            <Input disabled={!props.canEdit} {...getFieldProps('creditItgaHgstLine', { rules: [{ required: true, message: '必填' }] }) } type="text" autoComplete="off" />
                                        </FormItem>
                                    </Col>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="关注类企业类别：">
                                            <Select  disabled={!props.canEdit} {...getFieldProps('state') } >
                                                <Option value="0">小贷</Option>
                                                <Option value="1">资管</Option>
                                                <Option value="2">担保</Option>
                                            </Select>
                                        </FormItem>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="是否有被执行：">
                                            <Select  disabled={!props.canEdit} {...getFieldProps('state') } >
                                                <Option value="0">是</Option>
                                                <Option value="1">否</Option>
                                            </Select>
                                        </FormItem>
                                    </Col>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="笔数：">
                                            <Input disabled={!props.canEdit} {...getFieldProps('creditItgaHgstLine') } type="text" autoComplete="off" />
                                        </FormItem>
                                    </Col>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="金额：">
                                            <Input disabled={!props.canEdit} {...getFieldProps('creditItgaHgstLine') } type="text" autoComplete="off" />
                                        </FormItem>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="已被占通道：">
                                            <Select  disabled={!props.canEdit} {...getFieldProps('state') } >
                                                <Option value="0">是</Option>
                                                <Option value="1">否</Option>
                                            </Select>
                                        </FormItem>
                                    </Col>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="已上最高额抵押：">
                                            <Select  disabled={!props.canEdit} {...getFieldProps('state') } >
                                                <Option value="0">是</Option>
                                                <Option value="1">否</Option>
                                            </Select>
                                        </FormItem>
                                    </Col>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="司法查询/行政限制：">
                                            <Select  disabled={!props.canEdit} {...getFieldProps('state') } >
                                                <Option value="0">是</Option>
                                                <Option value="1">否</Option>
                                            </Select>
                                        </FormItem>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="一抵性质：">
                                            <Select  disabled={!props.canEdit} {...getFieldProps('state') } >
                                                <Option value="0">银行</Option>
                                                <Option value="1">非银行</Option>
                                            </Select>
                                        </FormItem>
                                    </Col>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="一抵银行名称：">
                                            <Input disabled={!props.canEdit} {...getFieldProps('creditItgaHgstLine') } type="text" autoComplete="off" />
                                        </FormItem>
                                    </Col>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="一抵金额：">
                                            <Input disabled={!props.canEdit} {...getFieldProps('creditItgaHgstLine') } type="text" autoComplete="off" />
                                        </FormItem>
                                    </Col>
                                </Row>
                                <Row>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="一抵利率：">
                                            <Input disabled={!props.canEdit} {...getFieldProps('creditItgaHgstLine') } type="text" autoComplete="off" />
                                        </FormItem>
                                    </Col>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="评估值：">
                                            <Input disabled={!props.canEdit} {...getFieldProps('creditItgaHgstLine') } type="text" autoComplete="off" />
                                        </FormItem>
                                    </Col>
                                    <Col span="7">
                                        <FormItem  {...formItemLayout} label="快出价：">
                                            <Input disabled={!props.canEdit} {...getFieldProps('creditItgaHgstLine') } type="text" autoComplete="off" />
                                        </FormItem>
                                    </Col>
                                </Row>
                            </div>
                        </Form>
                    </div>
                    <div className="navLine-wrap-right" >
                        <NavLine navLineData={this.navLineData} activeItem="#s1" ref="NavLine"/>
                    </div>
                </div>
            </div>
        );
    }
});
AddlWin = createForm()(AddlWin);
export default AddlWin;
