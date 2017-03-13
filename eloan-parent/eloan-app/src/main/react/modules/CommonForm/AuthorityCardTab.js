//权证下户
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
const FormItem = Form.Item;
const Option = Select.Option;
const objectAssign = require('object-assign');
var Reflux = require('reflux');
var FormDataStore = require('./stores/FormDataStore');
const NavLine = require("../utils/NavLine");
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
        automaticWithholdingTime: ""
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
  onChange(field, value) {
    console.log(field, 'change', value);
    this.setState({
      [field]: value,
    });
  },
  onStartChange(value) {
    this.onChange('automaticWithholdingTime', value);
  },
  handleStartToggle({ open }) {
    if (!open) {
      this.setState({ endOpen: true });
    }
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
  navLineData: {
    "下户信息": "#s1",
    "资料清单": "#s2",
    "房屋中介信息": "#s3",
    "房屋快出值信息": "#s4",
    "调查员": "#s5",
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
      <div style={{ position: "relative" }}>
        <div className="navLine-wrap" onScroll={NavLineUtils._handleSpy.bind(this, this.navLineData) }>
          <div className=" navLine-wrap-left" span="22">
            <Form horizontal  form={this.props.form} style={{ marginTop: "20px" }}>
              <Input  {...getFieldProps('id', { initialValue: '' }) } type="hidden"   />
              <div id="s1">
                <h2>下户信息</h2>
                <Row>
                  <Col span="14">
                    <FormItem  labelCol={{ span: 4 }} wrapperCol={{ span: 19 }} label="房产地址：">
                      <Region areaId={this.state.areaId1} ref="region1" onAreaChange={this.changeAreaId.bind(this, "propertyAddressId") } disabled={!this.props.canEdit}/>
                    </FormItem>
                  </Col>
                  <Col span="4" >
                    <FormItem style={{ marginLeft: "-130px" }}>
                      <Input disabled={!props.canEdit} placeholder="详细地址"  {...getFieldProps('propertyAddress') } type="text" autoComplete="off" />
                    </FormItem>
                  </Col>
                </Row>
                <Row>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="小区名称：">
                      <Input disabled={!props.canEdit}  {...getFieldProps('communityName') } type="text" autoComplete="off" />
                    </FormItem>
                  </Col>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="楼牌号是否与房本一致 ：">
                      <Select  disabled={!props.canEdit} {...getFieldProps('buildingBrands', { rules: [{ required: true, message: '必填' }] }) } >
                        <Option value="0">是</Option>
                        <Option value="1">否</Option>
                      </Select>
                    </FormItem>
                  </Col>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="居住人含：">
                      <Select  disabled={!props.canEdit} {...getFieldProps('livingPeople', { rules: [{ required: true, message: '必填' }] }) } >
                        <Option value="0">无</Option>
                        <Option value="1">高龄老人</Option>
                        <Option value="2">学龄前儿童</Option>
                      </Select>
                    </FormItem>
                  </Col>
                </Row>
                <Row>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="装修状况：">
                      <Select  disabled={!props.canEdit} {...getFieldProps('furnishingStatus', { rules: [{ required: true, message: '必填' }] }) } >
                        <Option value="0">精装</Option>
                        <Option value="1">简装</Option>
                      </Select>
                    </FormItem>
                  </Col>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="房室户型：">
                      <Input disabled={!props.canEdit}  {...getFieldProps('n3mUtilizValue') } type="text" autoComplete="off" />
                    </FormItem>
                  </Col>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="所在楼层：">
                      <Input disabled={!props.canEdit}  {...getFieldProps('floor') } type="text" autoComplete="off" />
                    </FormItem>
                  </Col>
                </Row>
                <Row>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="总共层数：">
                      <Input disabled={!props.canEdit}  {...getFieldProps('totalFloors') } type="text" autoComplete="off" />
                    </FormItem>
                  </Col>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="房屋朝向：">
                      <Select  disabled={!props.canEdit} {...getFieldProps('housingOrientation', { rules: [{ required: true, message: '必填' }] }) } >
                        <Option value="0">坐北朝南</Option>
                        <Option value="1">坐南朝北</Option>
                        <Option value="2">坐西朝东</Option>
                        <Option value="3">坐东朝西</Option>
                      </Select>
                    </FormItem>
                  </Col>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="居住情况：">
                      <Select  disabled={!props.canEdit} {...getFieldProps('livingConditions', { rules: [{ required: true, message: '必填' }] }) } >
                        <Option value="0">自住</Option>
                        <Option value="1">出租</Option>
                        <Option value="2">空置</Option>
                      </Select>
                    </FormItem>
                  </Col>
                </Row>
                <Row>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="租户签署放弃优先购买权：">
                      <Select  disabled={!props.canEdit} {...getFieldProps('purchasingPower', { rules: [{ required: true, message: '必填' }] }) } >
                        <Option value="0">是</Option>
                        <Option value="1">否</Option>
                      </Select>
                    </FormItem>
                  </Col>
                </Row>
              </div>
              <div id="s2">
                <h2>资料清单</h2>
                <Row>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="户口本：">
                      <Select  disabled={!props.canEdit} {...getFieldProps('accountBook', { rules: [{ required: true, message: '必填' }] }) } >
                        <Option value="0">有</Option>
                        <Option value="1">无</Option>
                      </Select>
                    </FormItem>
                  </Col>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="结婚证：">
                      <Select  disabled={!props.canEdit} {...getFieldProps('marriageCertificate', { rules: [{ required: true, message: '必填' }] }) } >
                        <Option value="0">有</Option>
                        <Option value="1">无</Option>
                      </Select>
                    </FormItem>
                  </Col>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="离婚证：">
                      <Select  disabled={!props.canEdit} {...getFieldProps('divorceCertificate', { rules: [{ required: true, message: '必填' }] }) } >
                        <Option value="0">有</Option>
                        <Option value="1">无</Option>
                      </Select>
                    </FormItem>
                  </Col>
                </Row>
                <Row>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="离婚协议：">
                      <Select  disabled={!props.canEdit} {...getFieldProps('divorceAgreement', { rules: [{ required: true, message: '必填' }] }) } >
                        <Option value="0">有</Option>
                        <Option value="1">无</Option>
                      </Select>
                    </FormItem>
                  </Col>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="民事调解书：">
                      <Select  disabled={!props.canEdit} {...getFieldProps('civilMediation', { rules: [{ required: true, message: '必填' }] }) } >
                        <Option value="0">有</Option>
                        <Option value="1">无</Option>
                      </Select>
                    </FormItem>
                  </Col>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="法院判决书：">
                      <Select  disabled={!props.canEdit} {...getFieldProps('courtVerdict', { rules: [{ required: true, message: '必填' }] }) } >
                        <Option value="0">有</Option>
                        <Option value="1">无</Option>
                      </Select>
                    </FormItem>
                  </Col>
                </Row>
                <Row>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="是否唯一住房：">
                      <Select  disabled={!props.canEdit} {...getFieldProps('onlyHousing', { rules: [{ required: true, message: '必填' }] }) } >
                        <Option value="0">有</Option>
                        <Option value="1">无</Option>
                      </Select>
                    </FormItem>
                  </Col>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="房产证：">
                      <Select  disabled={!props.canEdit} {...getFieldProps('deed', { rules: [{ required: true, message: '必填' }] }) } >
                        <Option value="0">有</Option>
                        <Option value="1">无</Option>
                      </Select>
                    </FormItem>
                  </Col>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="购房合同：">
                      <Select  disabled={!props.canEdit} {...getFieldProps('purchaseContract', { rules: [{ required: true, message: '必填' }] }) } >
                        <Option value="0">有</Option>
                        <Option value="1">无</Option>
                      </Select>
                    </FormItem>
                  </Col>
                </Row>
                <Row>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="租赁合同：">
                      <Select  disabled={!props.canEdit} {...getFieldProps('leaseContract', { rules: [{ required: true, message: '必填' }] }) } >
                        <Option value="0">有</Option>
                        <Option value="1">无</Option>
                      </Select>
                    </FormItem>
                  </Col>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="抵押贷款合同：">
                      <Select  disabled={!props.canEdit} {...getFieldProps('mortgageLoanContract', { rules: [{ required: true, message: '必填' }] }) } >
                        <Option value="0">有</Option>
                        <Option value="1">无</Option>
                      </Select>
                    </FormItem>
                  </Col>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="贷款卡：">
                      <Select  disabled={!props.canEdit} {...getFieldProps('loanCard', { rules: [{ required: true, message: '必填' }] }) } >
                        <Option value="0">有</Option>
                        <Option value="1">无</Option>
                      </Select>
                    </FormItem>
                  </Col>
                </Row>
                <Row>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="征信报告：">
                      <Select  disabled={!props.canEdit} {...getFieldProps('creditReport', { rules: [{ required: true, message: '必填' }] }) } >
                        <Option value="0">有</Option>
                        <Option value="1">无</Option>
                      </Select>
                    </FormItem>
                  </Col>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="满二或满五：">
                      <Select  disabled={!props.canEdit} {...getFieldProps('twoOrFive', { rules: [{ required: true, message: '必填' }] }) } >
                        <Option value="0">满二</Option>
                        <Option value="1">满五</Option>
                      </Select>
                    </FormItem>
                  </Col>
                </Row>
              </div>
              <div id="s3">
                <h2>房屋中介信息</h2>
                <Row>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="门名名称：">
                      <Input disabled={!props.canEdit}  {...getFieldProps('doorName') } type="text" autoComplete="off" />
                    </FormItem>
                  </Col>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="负责人姓名：">
                      <Input disabled={!props.canEdit}  {...getFieldProps('principalName') } type="text" autoComplete="off" />
                    </FormItem>
                  </Col>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="负责人联系方式：">
                      <Input disabled={!props.canEdit}  {...getFieldProps('principalPhone') } type="text" autoComplete="off" />
                    </FormItem>
                  </Col>
                </Row>
                <Row>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="税款详情：">
                      <Input disabled={!props.canEdit}  {...getFieldProps('taxDetails') } type="text" autoComplete="off" />
                    </FormItem>
                  </Col>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="正常价格(净得)/万：">
                      <Input disabled={!props.canEdit}  {...getFieldProps('normalPrice') } type="text" autoComplete="off" />
                    </FormItem>
                  </Col>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="快速成交价格(净得)/万：">
                      <Input disabled={!props.canEdit}  {...getFieldProps('fastTransactionPrice') } type="text" autoComplete="off" />
                    </FormItem>
                  </Col>
                </Row>
              </div>
              <div id="s4">
                <h2>房屋快出值信息</h2>
                <Row>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="学校：">
                      <Select  disabled={!props.canEdit} {...getFieldProps('school', { rules: [{ required: true, message: '必填' }] }) } >
                        <Option value="0">有</Option>
                        <Option value="1">无</Option>
                      </Select>
                    </FormItem>
                  </Col>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="医院：">
                      <Select  disabled={!props.canEdit} {...getFieldProps('hospital', { rules: [{ required: true, message: '必填' }] }) } >
                        <Option value="0">有</Option>
                        <Option value="1">无</Option>
                      </Select>
                    </FormItem>
                  </Col>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="购物等配套情况：">
                      <Select  disabled={!props.canEdit} {...getFieldProps('shopping', { rules: [{ required: true, message: '必填' }] }) } >
                        <Option value="0">有</Option>
                        <Option value="1">无</Option>
                      </Select>
                    </FormItem>
                  </Col>
                </Row>
                <Row>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="银行可否代为提前还款：">
                      <Select  disabled={!props.canEdit} {...getFieldProps('bankPrepayment', { rules: [{ required: true, message: '必填' }] }) } >
                        <Option value="0">是</Option>
                        <Option value="1">否</Option>
                      </Select>
                    </FormItem>
                  </Col>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="房屋快出值：">
                      <Input disabled={!props.canEdit}  {...getFieldProps('housingValueFaster') } type="text" autoComplete="off" />
                    </FormItem>
                  </Col>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="房产交易产生的税费：">
                      <Input disabled={!props.canEdit}  {...getFieldProps('propertyTaxes') } type="text" autoComplete="off" />
                    </FormItem>
                  </Col>
                </Row>
              </div>
              <div id="s5">
                <h2>调查员</h2>
                <Row>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="调查员：">
                      <Input disabled={!props.canEdit}  {...getFieldProps('investigator') } type="text" autoComplete="off" />
                    </FormItem>
                  </Col>
                  <Col span="7">
                    <FormItem  {...formItemLayout} label="调查日期：">
                      <DatePicker disabled={!props.canEdit}  {...getFieldProps('surveyTime') } style={{ width: "210px" }}/>
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