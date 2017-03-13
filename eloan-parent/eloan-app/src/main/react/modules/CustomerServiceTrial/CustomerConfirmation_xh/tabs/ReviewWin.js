import React from 'react';
import {
    Button,
    Modal,
    Tabs,
} from 'antd';
const TabPane = Tabs.TabPane;
const objectAssign = require('object-assign');
const NavLine = require("./../../../utils/NavLine");
import HistoryApprove from '../../../CommonForm/HistoryApprove';
import BorrowingNeeds from '../../../CommonForm/BorrowingNeeds';
import ProcessInformation from '../../../CommonForm/ProcessInformation';
import AuthorityCardTab from '../../../CommonForm/AuthorityCardTab';
var NewEvaOrg = require("../../../CommonForm/NewEvaOrg");
var NewQueryOrg = require("../../../CommonForm/NewQueryOrg");
var BasiInformation = require("../../../CommonForm/BasiInformation");
var HousingInformation = require("../../../CommonForm/HousingInformation");
var NextDoorInformation = require("../../../CommonForm/NextDoorInformation");
var DataListing = require("../../../CommonForm/DataListing");
var RealtorInformation = require("../../../CommonForm/RealtorInformation");
var UploadPhoto = require("../../../CommonForm/UploadPhoto");
var HousesValueQuickly = require("../../../CommonForm/HousesValueQuickly");
var Investigators = require("../../../CommonForm/Investigators");
var UploadPic = require("../../../CommonForm/UploadPic");
var ReviewWin = React.createClass({
    getInitialState() {
        var selectRecord = this.props.record;
        return {
            status: {},
            activekey: "1",
            formData: {}
        };
    },
    handleCancel() {
        if (this.refs.ProcessInformation) {
            this.refs.ProcessInformation.resetFields();
        }
        this.refs.HousingInformation.resetFields();
        this.refs.BasiInformation.resetFields();
        this.refs.BorrowingNeeds.resetFields();
        // this.refs.ProcessInformation.resetFields();
        this.props.hideModal();
        this.changeTabState();
    },
    changeTabState() {
        this.setState({
            idData: null,
            activekey: 1,
        })
    },
    handleTabClick(key) {
        var selectedrecord = this.props.record;
        this.setState({
            activekey: key
        }, () => {
            if (key == 3) {
                Utils.ajaxData({
                    url: '/modules/warrant/HousOritoInformationAction/getHousInfo.htm',
                    data: {
                        processInstanceId: selectedrecord.processInstanceId
                    },
                    callback: (result) => {
                        var housOritoInformation = result.data.housOritoInformation;
                        var housDataList = result.data.housDataList;
                        var housIntermediaryInformation = result.data.housIntermediaryInformation;
                        var housQuickInformation = result.data.housQuickInformation;
                        if (housOritoInformation) {
                            if (housOritoInformation.housingOrientation) {
                                housOritoInformation.housingOrientation = String(housOritoInformation.housingOrientation);
                            } else {
                                housOritoInformation.housingOrientation = ""
                            }
                            if (housOritoInformation.livingConditions) {
                                housOritoInformation.livingConditions = String(housOritoInformation.livingConditions);
                            } else {
                                housOritoInformation.livingConditions = ""
                            }
                            if (housOritoInformation.livingPeople) {
                                housOritoInformation.livingPeople = String(housOritoInformation.livingPeople);
                            } else {
                                housOritoInformation.livingPeople = ""
                            }
                            this.refs.NextDoorInformation.setFieldsValue(housOritoInformation);
                            this.refs.Investigators.setFieldsValue(housOritoInformation);
                        }
                        if (housDataList) {
                            this.refs.DataListing.setFieldsValue(housDataList);
                        }
                        if (housIntermediaryInformation) {
                            this.refs.RealtorInformation.setFieldsValue(housIntermediaryInformation);
                        }
                        if (housIntermediaryInformation) {
                            this.refs.RealtorInformation.setFieldsValue(housIntermediaryInformation);
                        }
                        if (HousesValueQuickly) {
                            this.refs.HousesValueQuickly.setFieldsValue(housQuickInformation);
                        }
                    }
                });
            }
        })
    },
    navLineData1: {
        "房产信息": "#b1",
        "基本信息": "#b2",
        "信息筛查": "#b3",
        "借款需求": "#b4",
    },
    navLineData2: {
        "下户信息": "#s1",
        "资料清单": "#s2",
        "房屋中介信息": "#s3",
        "房屋快出值信息": "#s4",
        "调查员": "#s5",
    },
    handleOk() {
        var validation = this.refs.HousingInformation.validateFields;
        var me = this;
        validation((errors, values) => {
            if (!!errors) {
                me.handleTabClick("1");
                me.validate2(false);
            }
            else {
                me.validate2(true);
            }
        })
    },
    validate2(isvalid1) {
        var me = this;
        var validation2 = this.refs.BasiInformation.validateFields;
        validation2((errors, values) => {
            if (!!errors || !isvalid1) {
                me.handleTabClick("1");
            } else {
                var ProcessInformation = this.refs.ProcessInformation;
                if (!ProcessInformation) {
                    me.handleTabClick("4");
                    return;
                }
                var validation3 = this.refs.ProcessInformation.validateFields;
                validation3((errors, values) => {
                    if (!!errors) {
                        me.handleTabClick("4");
                        return
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
                        postdata.housBorrowingBasics = housBorrowingBasics;
                        postdata.housPropertyInformation = housPropertyInformation;
                        postdata.plBorrowRequirement = plBorrowRequirement;
                        commentData.comment = remarkData.nextStep;
                        postdata.amountComment = remarkData.amountComment
                        postdata.remarkComment = remarkData.remarkComment;
                        postdata.receiveType = remarkData.receiveType;
                        postdata.amountComment = remarkData.amountComment;
                        postdata.householdAssignee = remarkData.householdAssignee;
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
                                            this.handleCancel();
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

    render() {
        var props = this.props;
        var state = this.state;
        var modalBtns = [
            <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
            <button key="button" className="ant-btn ant-btn-primary" disabled={!props.canEdit}  onClick={this.handleOk}>
                提 交
            </button>
        ];
        if (!props.canEdit) {
            modalBtns = [
                <button key="back" className="ant-btn" onClick={this.handleCancel}>关闭</button>
            ];
        }
        var idData = props.id
        var canEditBasicInfo = props.canEditBasicInfo;
        return (
            <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel}   width="1200"  footer={modalBtns} >
                <Tabs defaultActiveKey="1" onTabClick={this.handleTabClick} activeKey={this.state.activekey}>
                    <TabPane tab="基本信息" key="1">
                        <div style={{ position: "relative" }}>
                            <div className="navLine-wrap" onScroll={NavLineUtils._handleSpy.bind(this, this.navLineData1) }>
                                <div className="col-22 navLine-wrap-left" >
                                    <div id="b1">
                                        <h2>房产信息</h2>
                                        <HousingInformation ref="HousingInformation" canEdit={canEditBasicInfo}/>
                                        <NewEvaOrg ref="NewEvaOrg" canEdit={false} record={props.record}/>
                                        <UploadPic ref="RoomThisPhoto" style={{ marginLeft: 25 }} title="房本照片" btype="HOUSE" canEdit={false} selectRecord={props.record} idData={idData}/>
                                    </div>
                                    <div id="b2">
                                        <h2>基本信息</h2>
                                        <BasiInformation ref="BasiInformation" canEdit={canEditBasicInfo}/>
                                        <UploadPhoto style={{ marginLeft: 25 }} title="身份证照片" btype="IDCARD" canEdit={false} selectRecord={props.record}/>
                                    </div>
                                    <div id="b3">
                                        <h2>信息筛查</h2>
                                        <NewQueryOrg ref="NewQueryOrg"/>
                                    </div>
                                    <div id="b4">
                                        <h2>借款需求</h2>
                                        <BorrowingNeeds ref="BorrowingNeeds" canEdit={canEditBasicInfo} />
                                    </div>
                                </div>
                                <div className="navLine-wrap-right" >
                                    <NavLine navLineData={this.navLineData1} activeItem="#b1" ref="NavLine"/>
                                </div>
                            </div>
                        </div>
                    </TabPane>
                    <TabPane tab="审批历史" key="2">
                        <HistoryApprove record={props.record}/>
                    </TabPane>
                    <TabPane tab="权证下户" key="3">
                        <div style={{ position: "relative" }}>
                            <div className="navLine-wrap" onScroll={NavLineUtils._handleSpy.bind(this, this.navLineData2) }>
                                <div className="col-22 navLine-wrap-left" >
                                    <div id="s1">
                                        <h2>下户信息</h2>
                                        <NextDoorInformation ref="NextDoorInformation" canEdit={false} />
                                        <UploadPhoto style={{ marginLeft: 25 }} title="下户照片" btype="NEXTDOOR" canEdit={false} selectRecord={props.record}/>
                                    </div>
                                    <div id="s2">
                                        <h2>资料清单</h2>
                                        <DataListing ref="DataListing" canEdit={false} />
                                    </div>
                                    <div id="s3">
                                        <h2>房屋中介信息</h2>
                                        <RealtorInformation ref="RealtorInformation" canEdit={false} />
                                        <UploadPhoto style={{ marginLeft: 25 }} title="中介名片" btype="REALTOR" canEdit={false} selectRecord={props.record}/>
                                    </div>
                                    <div id="s4">
                                        <h2>房屋快出值信息</h2>
                                        <HousesValueQuickly ref="HousesValueQuickly" canEdit={false} />
                                        <UploadPhoto style={{ marginLeft: 25 }} title="核行照片" btype="QUICKLY" canEdit={false} selectRecord={props.record}/>
                                    </div>
                                    <div id="s5">
                                        <h2>调查员</h2>
                                        <Investigators ref="Investigators" canEdit={false} />
                                    </div>

                                </div>
                                <div className="navLine-wrap-right" >
                                    <NavLine navLineData={this.navLineData2} activeItem="#s1" ref="NavLine"/>
                                </div>
                            </div>
                        </div>
                    </TabPane>

                    <TabPane tab="流程信息" key="4">
                        <ProcessInformation ref="ProcessInformation" canEdit={props.canEdit} record={props.record}/>
                    </TabPane>
                </Tabs>
            </Modal>
        );
    }
});
export default ReviewWin;
