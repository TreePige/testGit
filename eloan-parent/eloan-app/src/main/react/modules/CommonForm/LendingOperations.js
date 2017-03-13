//基本信息
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
var MarryStatuslist = [];
Utils.ajaxData({
  url: '/modules/common/action/ComboAction/queryDic.htm',
  method: 'get',
  data:{
      typeCode:"MARITAL_STATUS"
  },
  type: 'json',
  callback: (result) => {
    MarryStatuslist = result.data;
  }
});
var HousingInformation = React.createClass({
  getInitialState() {
    return {
      residentialAddressId: '110101',
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
  getMarryStatuslist() {
        return MarryStatuslist.map((item, index) => {
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
 var disableds=true;
    console.log(props.title)
    if(props.title=="放款操作"){
      disableds=props.canEdit;
    }
    return (
      <Form horizontal  form={this.props.form} style={{ marginTop: "20px" }}>
        <div id="s1">
         <h2>放款信息</h2>
          <Row>
            <Col span="10">
              <FormItem  {...formItemLayout} label="放款信息：">
                <Input disabled={!props.canEdit}  {...getFieldProps('certNumber') }  type="text" autoComplete="off" />
              </FormItem>
            </Col>
            <Col span="10">
              <FormItem  {...formItemLayout} label="借款名称：">
                <Input disabled={!props.canEdit}  {...getFieldProps('certNumber') } type="text" autoComplete="off" />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="10">
              <FormItem  {...formItemLayout}  label="客户名称：">
                <Select disabled={!props.canEdit}  {...getFieldProps('certNumber') } >
                  {this.getMarryStatuslist()}
                </Select>
              </FormItem>
            </Col>
            <Col span="10">
              <FormItem  {...formItemLayout} label="批贷金额（大写）：">
                <Input disabled={!props.canEdit}  {...getFieldProps('spouseName') } type="text" autoComplete="off" />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="10">
              <FormItem  {...formItemLayout}  label="批贷金额（小写）：">
                <Input disabled={!props.canEdit}  {...getFieldProps('spousePhone') } type="text" autoComplete="off" />
              </FormItem>
            </Col>
            <Col span="10">
              <FormItem  {...formItemLayout} label="开户银行：">
                <Input disabled={!props.canEdit}  {...getFieldProps('totalBorrowed') } type="text" autoComplete="off" />
              </FormItem>
            </Col>
            
          </Row>
          <Row>
            <Col span="10">
              <FormItem  {...formItemLayout} label="银行账户：">
                <Input disabled={!props.canEdit}  {...getFieldProps('totalBorrowedCertNumber') } type="text" autoComplete="off" />
              </FormItem>
            </Col>
            <Col span="10">
              <FormItem  {...formItemLayout} label="开户网点：">
                <Input disabled={!props.canEdit}  {...getFieldProps('totalBorrowedCertNumber') } type="text" autoComplete="off" />
              </FormItem>
            </Col>
          </Row>
        </div>
         <div id="s2">
         <h2>打款信息</h2>
          <Row>
            <Col span="10">
              <FormItem  {...formItemLayout} label="放款信息：">
                <Input disabled={!props.canEdit}  {...getFieldProps('certNumber') }  type="text" autoComplete="off" />
              </FormItem>
            </Col>
            <Col span="10">
              <FormItem  {...formItemLayout} label="借款名称：">
                <Input disabled={!props.canEdit}  {...getFieldProps('certNumber') } type="text" autoComplete="off" />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="10">
              <FormItem  {...formItemLayout}  label="客户名称：">
                <Select disabled={!props.canEdit}  {...getFieldProps('certNumber') } >
                  {this.getMarryStatuslist()}
                </Select>
              </FormItem>
            </Col>
            <Col span="10">
              <FormItem  {...formItemLayout} label="批贷金额（大写）：">
                <Input disabled={!props.canEdit}  {...getFieldProps('spouseName') } type="text" autoComplete="off" />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="10">
              <FormItem  {...formItemLayout}  label="批贷金额（小写）：">
                <Input disabled={!props.canEdit}  {...getFieldProps('spousePhone') } type="text" autoComplete="off" />
              </FormItem>
            </Col>
            <Col span="10">
              <FormItem  {...formItemLayout} label="开户银行：">
                <Input disabled={!props.canEdit}  {...getFieldProps('totalBorrowed') } type="text" autoComplete="off" />
              </FormItem>
            </Col>
            
          </Row>
          <Row>
            <Col span="10">
              <FormItem  {...formItemLayout} label="银行账户：">
                <Input disabled={!props.canEdit}  {...getFieldProps('totalBorrowedCertNumber') } type="text" autoComplete="off" />
              </FormItem>
            </Col>
            <Col span="10">
              <FormItem  {...formItemLayout} label="开户网点：">
                <Input disabled={!props.canEdit}  {...getFieldProps('totalBorrowedCertNumber') } type="text" autoComplete="off" />
              </FormItem>
            </Col>
          </Row>
        </div>
        <div id="s3">
         <h2>表单信息</h2>
          <Row>
            <Col span="10">
              <FormItem  {...formItemLayout} label="财务专员：">
                <Input disabled={!props.canEdit}  {...getFieldProps('certNumber') }  type="text" autoComplete="off" />
              </FormItem>
            </Col>
            <Col span="10">
              <FormItem  {...formItemLayout} label="首期息费：">
                <Input disabled={!props.canEdit}  {...getFieldProps('certNumber') } type="text" autoComplete="off" />
              </FormItem>
            </Col>
          </Row>
          <Row>
          <Col span="10">
                           <FormItem  {...formItemLayout} label="下户费收取形式：">
                               <Select  disabled={props.canEdit} {...getFieldProps('state') } >
                                 <Option value="0"></Option>
                                 <Option value="1"></Option>
                                 <Option value="2"></Option>
                               </Select>
                           </FormItem>
                        </Col>  
            <Col span="10">
            </Col>
            <Col span="10">
              <FormItem  {...formItemLayout} label="下户费金额：">
                <Input disabled={!props.canEdit}  {...getFieldProps('spouseName') } type="text" autoComplete="off" />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="10">
              <FormItem  {...formItemLayout}  label="放款银行：">
                <Input disabled={props.canEdit}  {...getFieldProps('spousePhone') } type="text" autoComplete="off" />
              </FormItem>
            </Col>
            <Col span="10">
              <FormItem  {...formItemLayout} label="银行账号：">
                <Input disabled={props.canEdit}  {...getFieldProps('totalBorrowed') } type="text" autoComplete="off" />
              </FormItem>
            </Col>
            
          </Row>
          <Row>
            <Col span="10">
              <FormItem  {...formItemLayout} label="处理意见：">
                <Input disabled={props.canEdit}  {...getFieldProps('totalBorrowedCertNumber') } type="text" autoComplete="off" />
              </FormItem>
            </Col>
          </Row>
          <Row>
            <Col span="10">
              <FormItem  {...formItemLayout} label="备注：">
                <Input disabled={props.canEdit}  {...getFieldProps('totalBorrowedCertNumber') } type="text" autoComplete="off" />
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
