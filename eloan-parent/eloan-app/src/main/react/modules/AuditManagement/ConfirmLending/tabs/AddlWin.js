
import React from 'react';
import {
    Button,
    Modal,
    Tabs,
} from 'antd';
import HistoryApprove from '../../../CommonForm/HistoryApprove';

import ProcessInformation from '../../../CommonForm/ProcessInformation';
import DocumentsFill from '../../../CommonForm/DocumentsFill';
import DocumentsFillBack from '../../../CommonForm/DocumentsFillBack';
import DocumentsGive from '../../../CommonForm/DocumentsGive'; 
import DocumentsFillLend from '../../../CommonForm/DocumentsFillLend';
import CardList from '../../../CommonForm/CardList';
import BorrowingNeeds from '../../../CommonForm/BorrowingNeeds';
import AuthorityCardTab from '../../../CommonForm/AuthorityCardTab';
import ForInterviewing from '../../../CommonForm/ForInterviewing';
const NavLine = require("../../../utils/NavLine"); 
var BasiInformation = require("../../../CommonForm/BasiInformation");
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
    //验证
    validateTab1() {//验证第一个tab页
        var validation = this.refs.DocumentsFill;
        var validation2 = this.refs.DocumentsFillLend;
        var validation3 = this.refs.DocumentsGiveSum;
        var validation4 = this.refs.DocumentsFillBack;
        var validation5 = this.refs.DocumentsGive;
        var me = this;
        var A = false;
        var B = false;
        var C = false;
        var D = false;
        var E = false;
        validation.validateFields((errors, values) => {
            if (!!errors) {
                console.log('Errors in form!!!');
                return;
            } else {
                A = true;
            }
        })
        validation2.validateFields((errors, values) => {
            if (!!errors) {
                console.log('Errors in form!!!');
                return;
            } else {
                B = true;
            }
        })
        validation3.validateFields((errors, values) => {
            if (!!errors) {
                console.log('Errors in form!!!');
                return;
            } else {
                C = true;
            }
        })
        validation4.validateFields((errors, values) => {
            if (!!errors) {
                console.log('Errors in form!!!');
                return;
            } else {
                D = true;
            }
        })
        validation5.validateFields((errors, values) => {
            if (!!errors) {
                console.log('Errors in form!!!');
                return;
            } else {
                E = true;
            }
        })
        return A && B && C && D && E
    },

    validate2(isvalid1) {
        var me = this;
        var validation2 = this.refs.DocumentsFillLend.validateFields;
        validation2((errors, values) => {
            if (!!errors || !isvalid1) {
                me.handleTabClick("1");
                return
            } else {
                var ProcessInformation = this.refs.ProcessInformation;
                if (!ProcessInformation) {
                    me.handleTabClick("3");
                    return;
                }
                var validation3 = this.refs.DocumentsGiveSum.validateFields;
                validation3((errors, values) => {
                    if (!!errors) {
                        me.handleTabClick("3");
                        return;
                    } else {
                        var state = this.state;
                        var props = this.props;
                        var selectRecord = props.record;
                        var postdata = {};
                        var commentData = {};
                        var housBorrowingBasics = this.refs.BasiInformation.getFieldsValue();
                        var housPropertyInformation = this.refs.HousingInformation.getFieldsValue();
                        var plBorrowRequirement = this.refs.BorrowingNeeds.getFieldsValue();
                        var remarkData = this.refs.ProcessInformation.getFieldsValue();
                        var data = this.refs.NewEvaOrg.getFieldsValue();
                        var formData = []
                        data.keys.forEach(item => {
                            let obj = {};
                            console.log(data)
                            console.log(item)
                            obj.assessmentAgencies = item;
                            obj.assessedValue = data[item + "assessedValue"];
                            obj.id = data[item + "id"];
                            formData.push(obj)
                        });
                        if (formData.length == 0) {
                            //新增评分机构记录为空
                            Modal.error({
                                title: '请新增一条评分机构记录'
                            });
                            return;
                        }
                        postdata.housAssessmentAgencies = formData;
                        postdata.housBorrowingBasics = housBorrowingBasics;
                        postdata.housPropertyInformation = housPropertyInformation;
                        postdata.plBorrowRequirement = plBorrowRequirement;
                        commentData.comment = remarkData.nextStep;
                        postdata.remarkComment = remarkData.remarkComment;
                        postdata.consultId = selectRecord.consultId;
                        postdata.nextStep = remarkData.nextStep;
                        postdata.processStateCode = selectRecord.processStateCode;
                        postdata.projectId = selectRecord.projectId;
                        postdata.processInstanceId = selectRecord.processInstanceId;
                        Utils.ajaxData({
                            url: '/modules/workflow/controller/ProcessTaskController/completeTask.htm',
                            method: 'post',
                            data: {
                                taskId: this.props.record.taskId,
                                serviceVariables: JSON.stringify(postdata),
                                processVariables: JSON.stringify(commentData)
                            },
                            type: 'json',
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
                    }
                })
            }
        })
    },
    handleOk() {  
        var evaOrgValid = false;
        if (!this.validateTab1()) {
            return
        }
         if (evaOrgValid) {
            this.validate2(true)
        }         
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
    render() {
        var props = this.props;
        var state = this.state;
        var modalBtns = [
            <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
            <button key="button" className="ant-btn ant-btn-primary"  onClick={this.handleOk}>
                保存
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
                                        <DocumentsFill ref="DocumentsFill" canEdit={props.canEdit} />
                                    </div>
                                    <div id="s2">
                                        <h2>放款单</h2>
                                        <DocumentsFillLend ref="DocumentsFillLend" canEdit={props.canEdit} />
                                    </div>
                                    <div id="s3">
                                        <h2>打款单</h2>
                                        <DocumentsGiveSum ref="DocumentsGiveSum" canEdit={props.canEdit} />
                                        <CardList ref="CardList" canEdit={props.canEdit} />
                                    </div>
                                    <div id="s4">
                                        <h2>返费签单</h2>
                                        <DocumentsFillBack ref="DocumentsFillBack" canEdit={props.canEdit} />
                                    </div>
                                    <div id="s5">
                                        <h2>代收服务费</h2>
                                        <DocumentsGive ref="DocumentsGive" canEdit={props.canEdit} />
                                    </div>
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