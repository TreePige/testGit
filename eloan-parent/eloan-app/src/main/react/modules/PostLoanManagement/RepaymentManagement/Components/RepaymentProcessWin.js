import React from 'react';
import {
    Button,
    Form,
    Input,
    InputNumber,
    Modal,
    Select,
    Tree,
    TreeSelect,
    Row,
    Col,
    DatePicker
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const objectAssign = require('object-assign');
const NavLine = require("./../../../utils/NavLine");
let treeData = {};
var AddDic = React.createClass({
    getInitialState() {
        return {
            status: {},
            formData: {}
        };
    },
    handleCancel() {
        this.props.form.resetFields();
        this.props.hideModal();
    },
    navLineData: {
        "基本信息": "#s1",
        "还款信息": "#s2",
    },
    handleOk(e) {
        e.preventDefault();
        var me = this;
        var props = me.props;
        var record = props.record;
        var title = props.title;

        var url = "/modules/system/addOrUpdateDict.htm";
        this.props.form.validateFields((errors, values) => {
            if (!!errors) {
                console.log('Errors in form!!!');
                return;
            }
            console.log("values", values)
            var data = objectAssign({}, {
                form: JSON.stringify(values)
            }, {
                    status: 'create'
                });
            if (title == "修改") {
                var data = objectAssign({}, {
                    form: JSON.stringify(values)
                }, {
                        status: 'update'
                    });
            }
            Utils.ajaxData({
                url: url,
                data: data,
                callback: (result) => {
                    if (result.code == 200) {
                        Modal.success({
                            title: result.msg,
                            onOk: () => {
                                props.hideModal();
                            }
                        });
                    } else {
                        Modal.error({
                            title: result.msg,
                        });
                    }
                }
            });
        });
    },
    getRoleList() {
        return roleList.map((item, index) => {
            return <Option key={item.id} >{item.name}</Option>
        })
    },
    render() {
        const {
            getFieldProps
        } = this.props.form;
        var props = this.props;
        var state = this.state;
        var modalBtns = [
            <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
            <button key="button" className="ant-btn ant-btn-primary"  onClick={this.handleOk}>
                提 交
            </button>
        ];
        const formItemLayout = {
            labelCol: {
                span: 8
            },
            wrapperCol: {
                span: 15
            },
        };
        return (
            <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="1200"  footer={modalBtns} >
            <div style={{ position: "relative" }}>
                <div className="navLine-wrap" onScroll={NavLineUtils._handleSpy.bind(this, this.navLineData) }>
                  <div className="col-22 navLine-wrap-left" >
                        <Form horizontal  form={this.props.form} style={{ marginTop: "20px" }}>
                          <Input  {...getFieldProps('id', { initialValue: '' }) } type="hidden"   />
                          <div id="s1">
                            <h2>基本信息</h2>
                            <Row>
                              <Col span="12">
                                <FormItem  {...formItemLayout} label="项目编号：">
                                  <Input disabled={true}  {...getFieldProps('projectCode') } type="text" autoComplete="off" />
                                </FormItem>
                              </Col>
                              <Col span="12">
                                <FormItem  {...formItemLayout} label="借款名称：">
                                  <Input disabled={true}  {...getFieldProps('idcard') } type="text" autoComplete="off" />
                                </FormItem>
                              </Col>
                            </Row>
                            <Row>
                              <Col span="12">
                                <FormItem  {...formItemLayout} label="合同编号：">
                                  <Input disabled={true}  {...getFieldProps('customerName') } type="text" autoComplete="off" />
                                </FormItem>
                              </Col>
                              <Col span="12">
                                <FormItem  {...formItemLayout} label="签订日期：">
                                  <Input disabled={true}  {...getFieldProps('account') } type="text" autoComplete="off" />
                                </FormItem>
                              </Col>
                            </Row>
                            <Row>
                              <Col span="12">
                                <FormItem  {...formItemLayout} label="借款金额（小写）：">
                                    <Input disabled={true}  {...getFieldProps('account') } type="text" autoComplete="off" />
                                </FormItem>
                              </Col>
                              <Col span="12">
                                <FormItem  {...formItemLayout} label="借款金额（大写）:">
                                    <Input disabled={true}  {...getFieldProps('account') } type="text" autoComplete="off" />
                                </FormItem>
                              </Col>
                            </Row>
                            <Row>
                              <Col span="12">
                                <FormItem  {...formItemLayout} label="借款期限：">
                                    <Input disabled={true}  {...getFieldProps('account') } type="text" autoComplete="off" />
                                </FormItem>
                              </Col>
                              <Col span="12">
                                <FormItem  {...formItemLayout} label="还款方式：">
                                    <Input disabled={true}  {...getFieldProps('account') } type="text" autoComplete="off" />
                                </FormItem>
                              </Col>
                            </Row>
                            <Row>
                              <Col span="12">
                                <FormItem  {...formItemLayout} label="还款起始：">
                                  <Input disabled={true}  {...getFieldProps('customerName') } type="text" autoComplete="off" />
                                </FormItem>
                              </Col>
                              <Col span="12">
                                <FormItem  {...formItemLayout} label="还款结束：">
                                  <Input disabled={true}  {...getFieldProps('account') } type="text" autoComplete="off" />
                                </FormItem>
                              </Col>
                            </Row>
                            <Row>
                              <Col span="12">
                                <FormItem  {...formItemLayout} label="开户银行：">
                                  <Input disabled={true}  {...getFieldProps('customerName') } type="text" autoComplete="off" />
                                </FormItem>
                              </Col>
                              <Col span="12">
                                <FormItem  {...formItemLayout} label="银行账户：">
                                  <Input disabled={true}  {...getFieldProps('account') } type="text" autoComplete="off" />
                                </FormItem>
                              </Col>
                            </Row>
                            <Row>
                              <Col span="12">
                                <FormItem  {...formItemLayout} label="开户网点：">
                                  <Input disabled={true}  {...getFieldProps('customerName') } type="text" autoComplete="off" />
                                </FormItem>
                              </Col>
                            </Row>
                          </div>
                          <div id="s2">
                            <h2>还款信息</h2>
                            <Row>
                              <Col span="12">
                                <FormItem  {...formItemLayout} label="本期应还款日期：">
                                  <Input disabled={!props.canEdit}  {...getFieldProps('projectCode') } type="text" autoComplete="off" />
                                </FormItem>
                              </Col>
                              <Col span="12">
                                <FormItem  {...formItemLayout} label="还款期次：">
                                  <Input disabled={!props.canEdit}  {...getFieldProps('idcard') } type="text" autoComplete="off" />
                                </FormItem>
                              </Col>
                            </Row>
                            <Row>
                              <Col span="12">
                                <FormItem  {...formItemLayout} label="本期应还金额：">
                                  <Input disabled={!props.canEdit}  {...getFieldProps('customerName') } type="text" autoComplete="off" />
                                </FormItem>
                              </Col>
                              <Col span="12">
                                <FormItem  {...formItemLayout} label="逾期罚息：">
                                  <Input disabled={!props.canEdit}  {...getFieldProps('account') } type="text" autoComplete="off" />
                                </FormItem>
                              </Col>
                            </Row>
                            <Row>
                              <Col span="12">
                                <FormItem  {...formItemLayout} label="本期应还总额：">
                                    <Input disabled={!props.canEdit}  {...getFieldProps('account') } type="text" autoComplete="off" />
                                </FormItem>
                              </Col>
                              <Col span="12">
                                <FormItem  {...formItemLayout} label="本期实际还款:">
                                  <Select  disabled={!props.canEdit} {...getFieldProps('receiveType') } >
                                    <Option value="0">有</Option>
                                    <Option value="1">无</Option>
                                  </Select>
                                </FormItem>
                              </Col>
                            </Row>
                            <Row>
                              <Col span="12">
                                <FormItem  {...formItemLayout} label="实际还款日期：">
                                    <DatePicker disabled={!props.canEdit}  {...getFieldProps('borrowingTime', { rules: [{ required: true, message: '必填', type: "date" }] }) }/>
                                </FormItem>
                              </Col>
                              {props.title=="罚息减免"?<Col span="12">
                                <FormItem  {...formItemLayout} label="减免罚息：">
                                    <Input disabled={true}  {...getFieldProps('account') } type="text" autoComplete="off" />
                                </FormItem>
                              </Col>:null}
                            </Row>
                          </div>
                        </Form>
                    </div>
                    <div className="navLine-wrap-right" >
                        <NavLine navLineData={this.navLineData} activeItem="#s1" ref="NavLine"/>
                    </div>
                </div>
            </div>                
            </Modal>
        );
    }
});
AddDic = createForm()(AddDic);
export default AddDic;
