import React from 'react';
import {
  Button,
  Form,
  Input,
  Modal,
  Select,
  TreeSelect,
  Row,
  Col
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const objectAssign = require('object-assign');
var Region = require('../../../plugin/Region');
var treeNodes = [];
var treeData = {};

var AddInstitution = React.createClass({
  getInitialState() {
    return {
      visible: false,
      areaId: '110101',
      formData: {}
    };
  },
  handleCancel() {
    this.props.form.resetFields();
    this.props.hideModal();
  },
  handleOk(e) {
    e.preventDefault();
    var me = this;
    var props = me.props;
    var record = props.record;
    var title = props.title;

    var url = "/modules/system/addoffice.htm";
    this.props.form.validateFields((errors, values) => {
      if (!!errors) {
        console.log('Errors in form!!!');
        return;
      }
      var data = values;
      var status = "create"
      if (title == "修改机构") {
        url = "/modules/system/addoffice.htm";
        status = "update"
        data = Object.assign({}, values, {

        });
      }
      Utils.ajaxData({
        url: url,
        data: {
          office: JSON.stringify(data),
          status: status
        },
        callback: (result) => {
          if (result.code == 200) {
            Modal.success({
              title: result.msg,
              onOk: () => {
                props.hideModal();
              }
            });
          }
        }
      });
    });
  },
  changeAreaId(areaId) {
    this.setState({
      areaId: areaId
    })
  },
  render() {
    const {
      getFieldProps
    } = this.props.form;
    var props = this.props;
    var state = this.state;
    treeData = this.props.officeData;
    var formData = state.formData;
    var modalBtns = [
      <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
      <button key="button" className="ant-btn ant-btn-primary"  onClick={this.handleOk}>
        确 定
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
    var Cols = 12;
    if (this.props.form.getFieldValue("parentId") == 0) {
      Cols = 0
    }
    return (
      <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="800"  footer={modalBtns} >
        <Form horizontal  form={this.props.form}>
          <Input  {...getFieldProps('id', { initialValue: '' }) } type="hidden"   />
          <Row>
            <Col span="12">
              <FormItem  {...formItemLayout} label="机构名称：">
                <Input disabled={!props.canEdit}  {...getFieldProps('name', { rules: [{ required: true, message: '必填' }] }) } type="text" />
              </FormItem>
            </Col>
            <Col span={Cols}>
              <FormItem  {...formItemLayout} label="上级：">
                <TreeSelect  dropdownStyle={{ maxHeight: 400, overflow: 'auto' }} treeData={treeData}
                  {...getFieldProps('parentId') }
                  treeDefaultExpandAll />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem  {...formItemLayout} label="排序：">
                <Input disabled={!props.canEdit}  {...getFieldProps('sort') } type="text"  />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem  {...formItemLayout} label="类型：">
                <Select  disabled={!props.canEdit} {...getFieldProps('type', { rules: [{ required: true, message: '必填', type: 'number' }] }) } >
                  <Option  value={0}>职能部门</Option>
                  <Option  value={1}>公司</Option>
                  <Option  value={2}>销售部门</Option>
                  <Option  value={3}>分公司</Option>
                </Select>
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem  {...formItemLayout} label="机构序号：">
                <Input disabled={!props.canEdit}  {...getFieldProps('officeNumber', { rules: [{ required: true, message: '必填' }] }) } type="text" />
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem  {...formItemLayout} label="机构代码证号：">
                <Input disabled={!props.canEdit}  {...getFieldProps('officeCard', { rules: [{ required: true, message: '必填' }] }) } type="text" />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="24">
              <FormItem   labelCol={{ span: 4 }} wrapperCol={{ span: 20 }}  label="机构地址：">
                <Region value={this.state.areaId} ref="region" onChange={this.changeAreaId} disabled={!props.canEdit}/>
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem  {...formItemLayout}  label="区号：">
                <Input disabled={!props.canEdit} {...getFieldProps('area', { rules: [{ required: true, message: '必填' }] }) } type="text"/>
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem  {...formItemLayout}  label="机构电话：">
                <Input disabled={!props.canEdit} {...getFieldProps('phone', { rules: [{ required: true, message: '必填' }] }) } type="text"/>
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem  {...formItemLayout}  label="银行：">
                <Input disabled={!props.canEdit} {...getFieldProps('officeBank', { rules: [{ required: true, message: '必填' }] }) } type="text"/>
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem  {...formItemLayout}  label="银行卡号：">
                <Input disabled={!props.canEdit} {...getFieldProps('officeBankCard', { rules: [{ pattern: /^(\d{16}|\d{19})$/, required: true, message: '必填' }] }) } type="text"/>
              </FormItem>
            </Col>
            <Col span="12">
              <FormItem  {...formItemLayout} label="状态：">
                <Select  disabled={!props.canEdit} {...getFieldProps('isDelete', { rules: [{ required: true, message: '必填',type:'number' }] }) } >
                  <Option value={0}>正常</Option>
                  <Option value={1}>禁用</Option>
                </Select>
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="12">
              <FormItem  {...formItemLayout} label="备注：">
                <Input disabled={!props.canEdit}  {...getFieldProps('remark') } />
              </FormItem>
            </Col>
          </Row>
        </Form>
      </Modal>
    );
  }
});
AddInstitution = createForm()(AddInstitution);
export default AddInstitution;
