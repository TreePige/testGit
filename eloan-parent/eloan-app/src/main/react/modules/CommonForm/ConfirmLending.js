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
            <Form horizontal  form={this.props.form} style={{ marginTop: "20px" }}>
                <div id="s1">
                    <Row>
                         <Col span="10">
                           <FormItem  {...formItemLayout} label="项目编号：">
                               <Input disabled={!props.canEdit}  {...getFieldProps('remarkComment') }  type="text" autoComplete="off" >
                               </Input>
                           </FormItem>
                        </Col>
                        <Col span="10">
                           <FormItem  {...formItemLayout} label="客户姓名：">
                               <Input disabled={!props.canEdit}  {...getFieldProps('remarkComment') }  type="text" autoComplete="off" >
                               </Input>
                           </FormItem>
                        </Col>                       
                    </Row>
                     <Row>
                         <Col span="10">
                           <FormItem  {...formItemLayout} label="批贷金额：">
                               <Input disabled={!props.canEdit}  {...getFieldProps('remarkComment') }  type="text" autoComplete="off" >
                               </Input>
                           </FormItem>
                        </Col>
                        <Col span="10">
                           <FormItem  {...formItemLayout} label="贷款限制：">
                               <Input disabled={!props.canEdit}  {...getFieldProps('remarkComment') }  type="text" autoComplete="off" >
                               </Input>
                           </FormItem>
                        </Col>                       
                    </Row>
                     <Row>
                         <Col span="10">
                           <FormItem  {...formItemLayout} label="月息：">
                               <Input disabled={!props.canEdit}  {...getFieldProps('remarkComment') }  type="text" autoComplete="off" >
                               </Input>
                           </FormItem>
                        </Col>
                        <Col span="10">
                           <FormItem  {...formItemLayout} label="成单利率：">
                               <Input disabled={!props.canEdit}  {...getFieldProps('remarkComment') }  type="text" autoComplete="off" >
                               </Input>
                           </FormItem>
                        </Col>                       
                    </Row>
                     <Row>
                         <Col span="10">
                           <FormItem  {...formItemLayout} label="首期利息：">
                               <Input disabled={!props.canEdit}  {...getFieldProps('remarkComment') }  type="text" autoComplete="off" >
                               </Input>
                           </FormItem>
                        </Col>           
                    </Row>
                     <Row>
                         <Col span="10">
                           <FormItem  {...formItemLayout} label="下户费收取形式：">
                               <Select  disabled={!props.canEdit} {...getFieldProps('state') } >
                                 <Option value="0">线下收取</Option>
                                 <Option value="1">放款时扣除</Option>
                               </Select>
                           </FormItem>
                        </Col>
                        <Col span="10">
                           <FormItem  {...formItemLayout} label="下户费金额：">
                               <Input disabled={!props.canEdit}  {...getFieldProps('remarkComment') }  type="text" autoComplete="off" >
                               </Input>
                           </FormItem>
                        </Col>                       
                    </Row>
                    <Row>
                        <Col span="10">
                           <FormItem  {...formItemLayout} label="备注：">
                               <textarea disabled={!props.canEdit}  {...getFieldProps('remarkComment') }  style={{ width: "550px", border: "1px solid #d9d9d9", borderRadius: "3px", paddingLeft: "5px" }} type="text" autoComplete="off" >
                               </textarea>
                           </FormItem>
                        </Col>
                    </Row>
                </div>
                <div id="s2">
                <h2>放款单</h2>         
                    <Row>
                         <Col span="10">
                           <FormItem  {...formItemLayout} label="姓名：">
                               <Input disabled={!props.canEdit}  {...getFieldProps('remarkComment') }  type="text" autoComplete="off" >
                               </Input>
                           </FormItem>
                        </Col>
                        <Col span="10">
                           <FormItem  {...formItemLayout} label="放款卡号：">
                               <Input disabled={!props.canEdit}  {...getFieldProps('remarkComment') }  type="text" autoComplete="off" >
                               </Input>
                           </FormItem>
                        </Col>                       
                    </Row>
                     <Row>
                         <Col span="10">
                           <FormItem  {...formItemLayout} label="开户行：">
                               <Input disabled={!props.canEdit}  {...getFieldProps('remarkComment') }  type="text" autoComplete="off" >
                               </Input>
                           </FormItem>
                        </Col>           
                    </Row>
                    <Row>
                          <Col span="10">
                           <FormItem  {...formItemLayout} label="&nbsp;">
                               <Button disabled={!props.canEdit}  {...getFieldProps('remarkComment') }  type="text" autoComplete="off" style={{width:"100",height:"35px",border: "1px solid #d9d9d9"}}>
                               增加打款卡
                               </Button>
                           </FormItem>
                        </Col>            
                    </Row>
                    <Row>
                         <Col span="10">
                           <FormItem  {...formItemLayout} label="开户人姓名：">
                               <Input disabled={!props.canEdit}  {...getFieldProps('remarkComment') }  type="text" autoComplete="off" >
                               </Input>
                           </FormItem>
                        </Col>
                        <Col span="10">
                           <FormItem  {...formItemLayout} label="打款卡号：">
                               <Input disabled={!props.canEdit}  {...getFieldProps('remarkComment') }  type="text" autoComplete="off" >
                               </Input>
                           </FormItem>
                        </Col>                       
                    </Row>
                     <Row>
                         <Col span="10">
                           <FormItem  {...formItemLayout} label="开户网点：">
                               <Input disabled={!props.canEdit}  {...getFieldProps('remarkComment') }  type="text" autoComplete="off" >
                               </Input>
                           </FormItem>
                        </Col>
                        <Col span="10">
                           <FormItem  {...formItemLayout} label="打款金额：">
                               <Input disabled={!props.canEdit}  {...getFieldProps('remarkComment') }  type="text" autoComplete="off" >
                               </Input>
                           </FormItem>
                        </Col>                       
                    </Row>
                </div>
                 <div id="s3">
                <h2>返费签单</h2>         
                    <Row>
                        <Col span="10">
                           <FormItem  {...formItemLayout} label="报单来源：">
                               <Select  disabled={!props.canEdit} {...getFieldProps('state') } >
                                 <Option value="0">赚赚自有</Option>
                                 <Option value="1">报单机构报单个人</Option>
                                 <Option value="2">报单个人</Option>
                               </Select>
                           </FormItem>
                        </Col>  
                        <Col span="10">
                           <FormItem  {...formItemLayout} label="金融顾问：">
                               <Input disabled={!props.canEdit}  {...getFieldProps('remarkComment') }  type="text" autoComplete="off" >
                               </Input>
                           </FormItem>
                        </Col>
                    </Row>
                     <Row>
                        <Col span="10">
                           <FormItem  {...formItemLayout} label="机构名称：">
                               <Select  disabled={!props.canEdit} {...getFieldProps('state') } >
                                 <Option value="0"></Option>
                                 <Option value="1"></Option>
                                 <Option value="2"></Option>
                               </Select>
                           </FormItem>
                        </Col>  
                        <Col span="10">
                           <FormItem  {...formItemLayout} label="业务员姓名：">
                               <Input disabled={!props.canEdit}  {...getFieldProps('remarkComment') }  type="text" autoComplete="off" >
                               </Input>
                           </FormItem>
                        </Col>
                    </Row>
                    <Row>
                         <Col span="10">
                           <FormItem  {...formItemLayout} label="返费点位：">
                               <Input disabled={!props.canEdit}  {...getFieldProps('remarkComment') }  type="text" autoComplete="off" >
                               </Input>
                           </FormItem>
                        </Col>
                        <Col span="10">
                           <FormItem  {...formItemLayout} label="返费金额：">
                               <Input disabled={!props.canEdit}  {...getFieldProps('remarkComment') }  type="text" autoComplete="off" >
                               </Input>
                           </FormItem>
                        </Col>                       
                    </Row>
                     <Row>
                         <Col span="10">
                           <FormItem  {...formItemLayout} label="返费期限：">
                               <Input disabled={!props.canEdit}  {...getFieldProps('remarkComment') }  type="text" autoComplete="off" style={{width:"100",height:"35px",border: "1px solid #d9d9d9"}}>
                               期
                               </Input>
                           </FormItem>
                        </Col>
                        <Col span="10">
                           <FormItem  {...formItemLayout} label="返费卡号：">
                               <Input disabled={!props.canEdit}  {...getFieldProps('remarkComment') }  type="text" autoComplete="off" >
                               </Input>
                           </FormItem>
                        </Col>                       
                    </Row>
                    <Row>
                         <Col span="10">
                           <FormItem  {...formItemLayout} label="开户行：">
                               <Input disabled={!props.canEdit}  {...getFieldProps('remarkComment') }  type="text" autoComplete="off">
                               </Input>
                           </FormItem>
                        </Col>      
                    </Row>
                </div>
                 <div id="s4">
                <h2>代收服务费</h2>         
                    <Row>
                        <Col span="10">
                           <FormItem  {...formItemLayout} label="服务费金额：">
                               <Input disabled={!props.canEdit}  {...getFieldProps('remarkComment') }  type="text" autoComplete="off" >
                               </Input>
                           </FormItem>
                        </Col>
                    </Row>
                     <Row>
                        <Col span="10">
                           <FormItem  {...formItemLayout} label="姓名：">
                                <Input disabled={!props.canEdit}  {...getFieldProps('remarkComment') }  type="text" autoComplete="off" >
                               </Input>
                           </FormItem>
                        </Col>  
                        <Col span="10">
                           <FormItem  {...formItemLayout} label="放款卡号：">
                               <Input disabled={!props.canEdit}  {...getFieldProps('remarkComment') }  type="text" autoComplete="off" >
                               </Input>
                           </FormItem>
                        </Col>
                    </Row>
                    <Row>
                         <Col span="10">
                           <FormItem  {...formItemLayout} label="开户行：">
                               <Input disabled={!props.canEdit}  {...getFieldProps('remarkComment') }  type="text" autoComplete="off" >
                               </Input>
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
