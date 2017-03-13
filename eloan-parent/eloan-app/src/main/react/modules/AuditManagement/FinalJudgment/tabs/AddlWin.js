
import React from 'react';
import {
    Button,
    Modal,
    Tabs,
} from 'antd';
import HistoryApprove from '../../../CommonForm/HistoryApprove';
import ProcessInformation from '../../../CommonForm/ProcessInformation';
import BorrowingNeeds from '../../../CommonForm/BorrowingNeeds';
import AuthorityCardTab from '../../../CommonForm/AuthorityCardTab';
const NavLine = require("./../../../utils/NavLine");
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
var RealEstate = require("../../../CommonForm/RealEstate");
var MarriageInformation = require("../../../CommonForm/MarriageInformation");
var CreditInformat = require("../../../CommonForm/CreditInformat");
var RiskControl = require("../../../CommonForm/RiskControl");
var UploadPic = require("../../../CommonForm/UploadPic");
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
        if (this.refs.ProcessInformation) {
            this.refs.ProcessInformation.resetFields();
        }
        this.props.hideModal();
        this.changeTabState();
    },
    handleOk() {
        var me = this;
        var validation2 = this.refs.BorrowingNeeds.validateFields;
        validation2((errors, values) => {
            if (!!errors) {
                me.handleTabClick("1");
            } else {
                var ProcessInformation = this.refs.ProcessInformation;
                if (!ProcessInformation) {
                    me.handleTabClick("5");
                    return;
                }
                var validation6 = this.refs.ProcessInformation.validateFields;
                validation6((errors, values) => {
                    if (!!errors) {
                        me.handleTabClick("5");
                        return
                    } else {
                        var state = this.state;
                        var props = this.props;
                        var selectRecord = props.record;
                        var postdata = {};
                        var commentData = {};
                        var remarkData = this.refs.ProcessInformation.getFieldsValue();
                        var plBorrowRequirement = this.refs.BorrowingNeeds.getFieldsValue();
                        commentData.comment = remarkData.nextStep;
                        postdata.remarkComment = remarkData.remarkComment;
                        postdata.receiveType = remarkData.receiveType;
                        postdata.amountComment = remarkData.amountComment;
                        postdata.householdAssignee = remarkData.householdAssignee;
                        postdata.plBorrowRequirement = plBorrowRequirement;
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
    handleSave() {
        var state = this.state;
        var props = this.props;
        var selectRecord = props.record;
        var postdata = {};
        var plBorrowRequirement = this.refs.BorrowingNeeds.getFieldsValue();
        postdata.plBorrowRequirement = plBorrowRequirement;
        postdata.consultId = selectRecord.consultId;
        postdata.processStateCode = selectRecord.processStateCode;
        postdata.projectId = selectRecord.projectId;
        postdata.processInstanceId = selectRecord.processInstanceId;
        Utils.ajaxData({
            url: '/modules/common/action/ServiceFormDraftSaverAction/saveFormDraft.htm'
            , method: 'post'
            , data: {
                formName: 'preliminary-evaluation-form',
                serviceVariables: JSON.stringify(postdata)
            }
            , type: 'json'
            , callback: (result) => {
                if (result.code == 200) {
                    Modal.success({
                        title: result.msg
                    });

                }
                else if (result.code == 400) {
                    Modal.error({
                        title: result.msg
                    });
                }
            }
        });
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
            if (key == 4) {
                Utils.ajaxData({
                    url: '/modules/audit/HousFaceTrialAction/getFaceAuditInfo.htm',
                    data: {
                        processInstanceId: selectedrecord.processInstanceId
                    },
                    callback: (result) => {
                        var housFaceTrial = result.data.housFaceTrial;
                        var housMarriageInformation = result.data.housMarriageInformation;
                        var housControlInformation = result.data.housControlInformation
                        if (housMarriageInformation) {
                            if (housMarriageInformation.maritalStatus) {
                                housMarriageInformation.maritalStatus = String(housMarriageInformation.maritalStatus);
                            } else {
                                housMarriageInformation.maritalStatus = ""
                            }

                        }
                        if (housFaceTrial) {
                            if (housFaceTrial.propertyProperties) {
                                housFaceTrial.propertyProperties = String(housFaceTrial.propertyProperties);
                            } else {
                                housFaceTrial.propertyProperties = ""
                            }
                            if (housFaceTrial.propertyListedProof) {
                                housFaceTrial.propertyListedProof = String(housFaceTrial.propertyListedProof);
                            } else {
                                housFaceTrial.propertyListedProof = ""
                            }
                            if (housFaceTrial.planningPurposes) {
                                housFaceTrial.planningPurposes = String(housFaceTrial.planningPurposes);
                            } else {
                                housFaceTrial.planningPurposes = ""
                            }
                            if (housFaceTrial.keyDiskQuery) {
                                housFaceTrial.keyDiskQuery = String(housFaceTrial.keyDiskQuery);
                            } else {
                                housFaceTrial.keyDiskQuery = ""
                            }

                        }
                        if (housControlInformation) {
                            housControlInformation.followEnterprisesCategory = String(housControlInformation.followEnterprisesCategory);
                        } else {
                            housControlInformation.followEnterprisesCategory = ""
                        }

                        this.refs.RealEstate.setFieldsValue(result.data.housFaceTrial);
                        this.refs.MarriageInformation.setFieldsValue(result.data.housMarriageInformation);
                        this.refs.CreditInformat.setFieldsValue(result.data.housCreditInformation);
                        this.refs.RiskControl.setFieldsValue(result.data.housControlInformation);
                    }
                });
            }
        })
    },
    changeTabState() {
        this.setState({
            idData: null,
            activekey: 1,
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
    navLineData3: {
        "房产信息": "#a1",
        "婚姻信息": "#a2",
        "征信信息": "#a3",
        "风控信息": "#a4",
    },
    render() {
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
                <button key="back" className="ant-btn" onClick={this.handleCancel}>返回</button>
            ]
        }
        if (props.title == "终审" && state.activekey == 1) {
            modalBtns = [
                <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
                <button key="submit" className="ant-btn" onClick={this.handleSave}>保存草稿</button>,
                <button key="button" className="ant-btn ant-btn-primary" disabled={!props.canEdit}  onClick={this.handleOk}>
                    提 交
                </button>
            ];
        }
        var canEditBasicInfo = props.canEditBasicInfo;
        var idData = props.id
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
                                        <NewQueryOrg ref="NewQueryOrg" canEdit={false} record={props.record}/>
                                    </div>
                                    <div id="b4">
                                        <h2>借款需求</h2>
                                        <BorrowingNeeds ref="BorrowingNeeds" canEdit={props.canEdit} />
                                    </div>
                                </div>
                                <div className="navLine-wrap-right" >
                                    <NavLine navLineData={this.navLineData1} activeItem="#b1" ref="NavLine"/>
                                </div>
                            </div>
                        </div>
                    </TabPane>
                    <TabPane tab="审批历史" key="2">
                        <HistoryApprove ref="HistoryApprove" canEdit={props.canEdit} record={props.record}/>
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
                    <TabPane tab="面审" key="4">

                        <div style={{ position: "relative" }}>
                            <div className="navLine-wrap" onScroll={NavLineUtils._handleSpy.bind(this, this.navLineData3) }>
                                <div className="col-22 navLine-wrap-left" >
                                    <div id="a1">
                                        <h2>房产信息</h2>
                                        <RealEstate ref="RealEstate" canEdit={false} />
                                        <UploadPhoto style={{ marginLeft: 25 }} title="户口本" btype="HOUSEHOLD" canEdit={false} selectRecord={props.record}/>
                                    </div>
                                    <div id="a2" style={{ marginTop: "10px" }}>
                                        <h2>婚姻信息</h2>
                                        <MarriageInformation ref="MarriageInformation" canEdit={false} />
                                        <UploadPhoto style={{ marginLeft: 25 }} title="婚姻证明" btype="MARRIAGE" canEdit={false} selectRecord={props.record}/>
                                    </div>
                                    <div id="a3" style={{ marginTop: "10px" }}>
                                        <h2>征信信息</h2>
                                        <CreditInformat ref="CreditInformat" canEdit={false} />
                                        <UploadPhoto style={{ marginLeft: 25 }} title="征信资料" btype="CREDIT" canEdit={false} selectRecord={props.record}/>
                                    </div>
                                    <div id="a4" style={{ marginTop: "10px" }}>
                                        <h2>风控信息</h2>
                                        <RiskControl ref="RiskControl" canEdit={false} record={props.record}/>
                                        <UploadPhoto style={{ marginLeft: 25 }} title="风控单" btype="RISK" canEdit={false} selectRecord={props.record}/>
                                    </div>
                                </div>
                                <div className="navLine-wrap-right" >
                                    <NavLine navLineData={this.navLineData3} activeItem="#a1" ref="NavLine"/>
                                </div>
                            </div>
                        </div>
                    </TabPane>
                    <TabPane tab="流程信息" key="5">
                        <ProcessInformation ref="ProcessInformation" canEdit={props.canEdit} record={props.record}/>
                    </TabPane>
                </Tabs>
            </Modal>
        );
    }
});
export default AddlWin;