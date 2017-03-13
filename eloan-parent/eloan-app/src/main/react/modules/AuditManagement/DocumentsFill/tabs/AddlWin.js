import React from 'react';
import {
    Button,
    Modal,
    Tabs,
} from 'antd';
import ProcessInformation from './ProcessInformation';
import DocumentsFill from '../../../CommonForm/DocumentsFill';
import DocumentsFillBack from '../../../CommonForm/DocumentsFillBack';
import DocumentsFillLend from '../../../CommonForm/DocumentsFillLend';
import CardList from '../../../CommonForm/CardList';
const NavLine = require("../../../utils/NavLine");
const TabPane = Tabs.TabPane;
const objectAssign = require('object-assign');
var AddlWin = React.createClass({
    getInitialState() {
        var selectRecord = this.props.record;
        return {
            status: {},
            activekey: "1",
            idData: selectRecord ? selectRecord.id : null,
            formData: {}
        };
    },
    handleCancel() {
        this.props.hideModal();
        this.changeTabState();
    },
    getDataSet() {
        //放款卡数据集
        var data = this.refs.CardList.getFieldsValue();
        var cardList = [];
        data.keys.forEach(item => {
            let obj = {};
            obj.id = item;
            obj.accountHolderName = data[item + "accountHolderName"];
            obj.cardid = data[item + "cardid"];
            obj.account = data[item + "account"];
            obj.bankFlag = data[item + "bankFlag"];
            cardList.push(obj);
        });
        var sumAccount = data.sumAccount;
        return { cardList, sumAccount }
    },
    //验证第一个tab页  
    validateTab1() {
        //验证打款总额等于打款卡总额 
        var {cardList, sumAccount} = this.getDataSet();//放款卡数据集
        var cardAccountSum = 0;
        cardList.forEach(item => {
            cardAccountSum += parseFloat(item.account)
        });
        if (cardAccountSum != sumAccount) {
            Modal.error({
                title: '打款总额不等于打款金额总和'
            });
            return;
        }

        var A = false;
        var B = false;
        var c = false;
        this.refs.DocumentsFillLend.validateFields((errors, values) => {
            if (!!errors) {
                return;
            } else {
                A = true;
            }
        })
        this.refs.DocumentsFillBack.validateFields((errors, values) => {
            if (!!errors) {
                return;
            } else {
                B = true;
            }
        })
        this.refs.CardList.validateFields((errors, values) => {
            if (!!errors) {
                return;
            }
            else {
                c = true;
            }
        });
        return A && B && c
    },
    handleOk() {
        if (!this.validateTab1()) {
            this.handleTabClick("1");
            return
        }
        else {
            this.validate2()
        }
    },
    validate2() {
        var me = this;
        var state = this.state;
        var props = this.props;
        var selectRecord = props.record;
        var ProcessInformation = this.refs.ProcessInformation;
        if (!ProcessInformation) {
            me.handleTabClick("2");
            return;
        }
        ProcessInformation.validateFields((errors, values) => {
            if (!!errors) {
                me.handleTabClick("2");
                return;
            } else {

                var postdata = {};
                var DocumentsFill = this.refs.DocumentsFill.getFieldsValue();
                var DocumentsFillLend = this.refs.DocumentsFillLend.getFieldsValue();
                var DocumentsFillBack = this.refs.DocumentsFillBack.getFieldsValue();
                var {cardList} = this.getDataSet();//放款卡数据集
                if (DocumentsFillBack.returnRate == 0) {
                    Modal.confirm({
                        title: '该笔贷款佣金为0，确定要继续吗',
                        onOk() {
                            me.submitData({ DocumentsFill, DocumentsFillLend, DocumentsFillBack });
                        },
                        onCancel() { },
                    });
                }
                else this.submitData({ DocumentsFill, cardList, DocumentsFillLend, DocumentsFillBack });
            }
        })
    },
    submitData(data) {
        var me = this;
        var props = this.props;
        var selectedrecord = this.props.record;
        Utils.ajaxData({
            url: '/modules/workflow/controller/ProcessTaskController/completeTask.htm',
            data: {
                taskId: selectedrecord.taskId,
                serviceVariables: JSON.stringify(data),
                processVariables: JSON.stringify({ "comment": "pass" })
            },
            callback: (result) => {
                if (result.code == 200) {
                    Modal.success({
                        title: '提交数据成功',
                        onOk() {
                            me.refreshList();
                        }
                    })
                }
                else  {
                    Modal.error({
                        title: result.msg
                    });
                }
            }
        });
    },
    handleTabClick(key) {
        this.setState({
            activekey: key
        })
    },
    changeTabState() {
        this.setState({
            idData: null,
            activekey: 1,
        })
    },
    navLineData: {
        "基本信息": "#s1",
        "放款单": "#s2",
        "打款单": "#s3",
        "返费签单": "#s4",
        "代收服务费": "#s5",
    },
    //保存草稿
    handleSave() {
        var me = this;
        var state = this.state;
        var props = this.props;
        var selectRecord = props.record;
        var postdata = {};
        //获取表单的信息
        var DocumentsFill = this.refs.DocumentsFill.getFieldsValue();//基本信息
        var DocumentsFillLend = this.refs.DocumentsFillLend.getFieldsValue();//lend 放款单
        var DocumentsFillBack = this.refs.DocumentsFillBack.getFieldsValue();//housLoanfees 返费签单 代收服务费
        var {cardList} = this.getDataSet();//放款卡数据集
        //数据类型转换
        DocumentsFillBack.returnRate=Number(DocumentsFillBack.returnRate);
        DocumentsFillBack.returnBank=String(DocumentsFillBack.returnBank);
        DocumentsFillBack.serviceBank=String(DocumentsFillBack.serviceBank);
        var processInstanceId = selectRecord.processInstanceId;
        var projectId = selectRecord.projectId;
        Utils.ajaxData({
            url: ' /modules/audit/HousBillsAction/saveLoanBillsInfoDraft.htm',
            method: 'post',
            data: {
                params: JSON.stringify({
                    "lend": DocumentsFillLend,
                    "housLoanfees": DocumentsFillBack,
                    "loans": cardList,
                    "processInstanceId": processInstanceId,
                    "projectId": projectId,
                })
            },
            type: 'json',
            callback: (result) => {
                if (result.code == 200) {
                    Modal.success({
                        title: result.msg,
                        onOk(){
                            props.showModal();
                        }
                    });
                }
                else  {
                    Modal.error({
                        title: result.msg
                    });
                }
            }
        });
    },
    changeSingleRate(e) {

        var repaymentRate = Number(this.props.housBillBasicInfo.repaymentRate);
        //成单利率=返费点位+月息
        var singleRate = Number(e.target.value) + repaymentRate;
        this.refs.DocumentsFill.setFieldsValue({ singleRate:singleRate })
    },
    render() {
        var props = this.props;
        var state = this.state;
        var modalBtns = [
            <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
            <button key="button1" className="ant-btn" onClick={this.handleSave}>保存草稿</button>,
            <button key="button2" className="ant-btn ant-btn-primary"  onClick={this.handleOk}>
                提交
            </button>
        ];
        if (!props.canEdit) {
            modalBtns = [
                <button key="back" className="ant-btn" onClick={this.handleCancel}>返回</button>
            ]
        }
        return (
            <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel}   width="1000"  footer={modalBtns} >
                <Tabs defaultActiveKey="1" onTabClick={this.handleTabClick} activeKey={this.state.activekey}>
                    <TabPane tab="放款单填写" key="1">
                        <div style={{ position: "relative" }}>
                            <div className="navLine-wrap" onScroll={NavLineUtils._handleSpy.bind(this, this.navLineData) }>
                                <div className="col-22 navLine-wrap-left" >
                                    <div id="s1">
                                        <h2>基本信息</h2>
                                        <DocumentsFill ref="DocumentsFill"  canEdit={props.canEdit}/>
                                    </div>
                                    <div id="s2">
                                        <h2>放款单</h2>
                                        <DocumentsFillLend ref="DocumentsFillLend" housBillBasicInfo={props.housBillBasicInfo} canEdit={props.canEdit} />
                                    </div>
                                    <div id="s3">
                                        <h2>打款单</h2>
                                        <CardList ref="CardList" canEdit={props.canEdit} housBillBasicInfo={props.housBillBasicInfo} />
                                    </div>
                                    <DocumentsFillBack ref="DocumentsFillBack"
                                        housBillBasicInfo={props.housBillBasicInfo}
                                        canEdit={props.canEdit}
                                        changeSingleRate={this.changeSingleRate}/>
                                </div>
                                <div className="navLine-wrap-right" >
                                    <NavLine navLineData={this.navLineData} activeItem="#s1" ref="NavLine"/>
                                </div>
                            </div>
                        </div>
                    </TabPane>
                    <TabPane tab="处理意见" key="2">
                        <ProcessInformation ref="ProcessInformation" canEdit={props.canEdit} />
                    </TabPane>
                </Tabs>
            </Modal>
        );
    }
});
export default AddlWin;
