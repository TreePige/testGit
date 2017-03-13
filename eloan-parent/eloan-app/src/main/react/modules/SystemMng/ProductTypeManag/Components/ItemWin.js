import React from 'react';
import {
    Button,
    Form,
    Input,
    Modal,
    Icon,
    Tag,
    Select,
    Cascader,
    Tree,
    TreeSelect,
    Row,
    Col
} from 'antd';
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
const objectAssign = require('object-assign');


var typeIdList = [];
    Utils.ajaxData({
        url: '/modules/fel/FeltypeAction/SelectAll.htm',
        method: 'get',
        type: 'json',
        callback: (result) => {
            typeIdList = result.data;
        }
    });

var tagOptions = []; 
Utils.ajaxData({
        url: '/modules/fel/FelParamAction/formulaQuery.htm',
        method: 'get', 
        type: 'json',
        callback: (result) => {
           tagOptions = result.data;
        }
      });

var ItemWin = React.createClass({
    getInitialState() {
        return {
            tags: [],  
            status: {},
            formData: {}
        };
    },
    handleCancel(){
        this.setState({tags:[]});
        this.props.form.resetFields();
        this.props.hideModal();
    },  
    handleOk(e) {
        e.preventDefault();
        var me = this;
        var props = me.props;
        var record = props.record;
        var title = props.title;
        var url = "/modules/fel/FelFormulaAction/Insert.htm";
        this.props.form.validateFields((errors, values) => {
            if (!!errors) {
                console.log('Errors in form!!!');
                return;
            }
            console.log("values", values)
            var data = objectAssign({}, {
                json: JSON.stringify(values)
            }, {
                tags:JSON.stringify(me.state.tags)
            });
            if (title == "编辑") {
                url = "/modules/fel/FelFormulaAction/Update.htm";
                var data = objectAssign({}, {
                json: JSON.stringify(values)
            }, {
                tags:JSON.stringify(me.state.tags)
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
    gettypeIdList() {
        return typeIdList.map((item, index) => {
            return <Option key={item.value} >{item.text}</Option>
        })
    },
    componentWillReceiveProps(nextProp){ 
    var selectRecord = nextProp.record;
      if(selectRecord){console.log(JSON.parse(selectRecord.formulaJson));
        this.setState({
          tags:selectRecord.formulaJson&&nextProp.title!='新增公式'?JSON.parse(selectRecord.formulaJson):[]
        });
      }
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
        if (!props.canEdit) {
          modalBtns = [
            <button key="back" className="ant-btn" onClick={this.handleCancel}>关闭</button>
          ];
        }
        const formItemLayout = {
            labelCol: {
                span: 8
            },
            wrapperCol: {
                span: 15
            },
        };
        return (
            <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel} width="900"  footer={modalBtns} >
          <Form horizontal  form={this.props.form}>
                <Input  {...getFieldProps('id',  {initialValue:''})} type="hidden"   />
              <Col span="12">
                <FormItem  {...formItemLayout} label="序号：">
                  <Input disabled={true}  {...getFieldProps('id')} type="text" autoComplete="off" />
                </FormItem> 
              </Col>
              <Col span="12">
                <FormItem  {...formItemLayout} label="是否启用：">
                  <Select  disabled={!props.canEdit} {...getFieldProps('state',{ rules: [{required:true,message:'必填',type:'number'}]})} >
                        <Option value={1}>是</Option> 
                        <Option value={0}>否</Option>
                  </Select> 
                </FormItem> 
               </Col>

              <Col span="12">
                <FormItem  {...formItemLayout} label="公式名称：">
                  <Input disabled={!props.canEdit} {...getFieldProps('chineseName')} type="text"/>
                </FormItem> 
              </Col>
              <Col span="12">
                <FormItem  {...formItemLayout} label="计算单位：">
                  <Input disabled={!props.canEdit} {...getFieldProps('unit')} type="text"/>
                </FormItem> 
              </Col>

              <Col span="12">
                <FormItem  {...formItemLayout} label="参数名称："> 
                  <Input disabled={!props.canEdit} {...getFieldProps('englishName')} type="text"/>
                </FormItem>
              </Col>
              <Col span="12">
                <FormItem  {...formItemLayout} label="数据来源：">
                  <Select  disabled={!props.canEdit} {...getFieldProps('dataSource',{ rules: [{required:true,message:'必填',type:'number'}]})} >
                        <Option value={1}>系统计算</Option> 
                        <Option value={2}>人工录入</Option>
                  </Select> 
                </FormItem> 
              </Col> 

              <Col span="12">
                <FormItem  {...formItemLayout}  label="是否内嵌公式：">
                  <Select  disabled={!props.canEdit} {...getFieldProps('nestedState',{ rules: [{required:true,message:'必填',type:'number'}]})} >
                        <Option value={1}>是</Option> 
                        <Option value={0}>否</Option>
                  </Select>
                </FormItem>
              </Col> 
              <Col span="12">
                <FormItem  {...formItemLayout}  label="类别：">
                  <Select disabled={!props.canEdit} {...getFieldProps('typeId', { rules: [{required:true,message:'必填'}]})} > 
                    {this.gettypeIdList()}
                  </Select> 
                </FormItem>
              </Col> 
              <Col span="12">
                <FormItem  {...formItemLayout}  label="计算公式：">
                  <span style={{paddingBottom:'7px',display:'block'}} className="myTag">  
                    {this.state.tags.length?this.state.tags.map((tag,index) =>
                              <Tag key={index+1} closable={false}  >{tag.label}</Tag>
                            ):null}
                     
                  </span>
                </FormItem>
              </Col> 
              <Col span="12">
                <FormItem  {...formItemLayout} label="备注：">
                  <Input disabled={!props.canEdit}  {...getFieldProps('note')} />
                </FormItem> 
              </Col>
          </Form>
      </Modal>
        );
    }
});
ItemWin = createForm()(ItemWin);
export default ItemWin;
