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
        if (this.refs.NextDoorInformation) {
            this.refs.NextDoorInformation.resetFields();
        }
        if (this.refs.DataListing) {
            this.refs.DataListing.resetFields();
        }
        if (this.refs.RealtorInformation) {
            this.refs.RealtorInformation.resetFields();
        }
        if (this.refs.HousesValueQuickly) {
            this.refs.HousesValueQuickly.resetFields();
        }
        if (this.refs.Investigators) {
            this.refs.Investigators.resetFields();
        }
        this.props.hideModal();
        this.changeTabState();
    },
    handleOk() {
        var validation = this.refs.NextDoorInformation;//下户信息
        var validation2 = this.refs.DataListing
        var validation3 = this.refs.RealtorInformation
        var validation4 = this.refs.HousesValueQuickly
        var validation5 = this.refs.Investigators
        if (!validation) {
            this.handleTabClick("3");
            return;
        }
        var me = this;
        var NextDoorInformation = false;
        var DataListing = false;
        var RealtorInformation = false;
        var HousesValueQuickly = false;
        var Investigators = false;

        validation.validateFields((errors, values) => {
            if (!!errors) {
                me.handleTabClick("3");
                return;
            } else {
                NextDoorInformation = true;
            }
        })
        validation2.validateFields((errors, values) => {
            console.log(arguments)
            if (!!errors) {
                me.handleTabClick("3");
                return;
            } else {
                DataListing = true;
            }
        })
        validation3.validateFields((errors, values) => {
            if (!!errors) {
                me.handleTabClick("3");
                return;
            } else {
                RealtorInformation = true;
            }
        })
        validation4.validateFields((errors, values) => {
            if (!!errors) {
                me.handleTabClick("3");
                return;
            } else {
                HousesValueQuickly = true;
            }
        })
        validation5.validateFields((errors, values) => {
            if (!!errors) {
                me.handleTabClick("3");
                return;
            } else {
                Investigators = true;
            }
        });
        console.log(DataListing)
        if (NextDoorInformation && DataListing && RealtorInformation && HousesValueQuickly && Investigators) {

            var ProcessInformation = this.refs.ProcessInformation;
            if (!ProcessInformation) {
                me.handleTabClick("4");
                return;
            }
            var validation6 = this.refs.ProcessInformation.validateFields;
            validation6((errors, values) => {
                if (!!errors) {
                    me.handleTabClick("4");
                    return
                } else {
                    var state = this.state;
                    var props = this.props;
                    var selectRecord = props.record;
                    var postdata = {};
                    var commentData = {};

                    var remarkData = this.refs.ProcessInformation.getFieldsValue();

                    var queryData = this.refs.NewQueryOrg.getFieldsValue();
                    postdata.housEnquiryInstitution = this.transformQueryData(queryData);
                    var housOritoInformation = this.refs.NextDoorInformation.getFieldsValue();
                    var housDataList = this.refs.DataListing.getFieldsValue();
                    var Investigators = this.refs.Investigators.getFieldsValue();
                    var housIntermediaryInformation = this.refs.RealtorInformation.getFieldsValue();
                    var housQuickInformation = this.refs.HousesValueQuickly.getFieldsValue();
                    // housOritoInformation.surveyTime = housQuickInformation.surveyTime && DateFormat.formatDate(housQuickInformation.surveyTime);
                    housOritoInformation.surveyTime = Investigators.surveyTime
                    housOritoInformation.investigator = housQuickInformation.investigator
                    postdata.housOritoInformation = housOritoInformation;
                    postdata.housDataList = housDataList;
                    postdata.housIntermediaryInformation = housIntermediaryInformation;
                    postdata.housQuickInformation = housQuickInformation;


                    commentData.comment = remarkData.nextStep;
                    postdata.housingValueFaster = remarkData.housingValueFaster
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

    },
    //提交转换查询机构数据
    transformQueryData(data) {
        var formData = [];
        data.keys.forEach(item => {
            let obj = {};
            //是建委
            if (item == 1) {
                obj.id = data[item + "id"];
                obj.institutionType = item;
                obj.remark = data[item + "remark"];
                obj.mortgage = data[item + "mortgage"] ? 1 : 0;
                obj.seizure = data[item + "seizure"] ? 1 : 0;
                obj.businessOccupancy = data[item + "businessOccupancy"] ? 1 : 0;
                obj.netSignedOccupancy = data[item + "netSignedOccupancy"] ? 1 : 0;
                obj.hochstbetragshypothek = data[item + "hochstbetragshypothek"] ? 1 : 0;
            }
            //是安融
            else if (item == 4) {
                obj.id = data[item + "id"];
                obj.institutionType = item;
                obj.remark = data[item + "remark"];
                obj.legalProcessPerformed = data[item + "legalProcessPerformed"] ? 1 : 0;
                obj.affiliate = data[item + "affiliate"] ? 1 : 0;
            }
            else {
                obj.id = data[item + "id"];
                obj.institutionType = item;
                obj.remark = data[item + "remark"];
                obj.legalProcessPerformed = data[item + "legalProcessPerformed"] ? 1 : 0;
            }
            formData.push(obj)
        });
        return formData
    },
    handleSave() {
        var state = this.state;
        var props = this.props;
        var selectRecord = props.record;
        var postdata = {};
        var housOritoInformation = this.refs.NextDoorInformation.getFieldsValue();
        var housDataList = this.refs.DataListing.getFieldsValue();
        var Investigators = this.refs.Investigators.getFieldsValue();
        var housIntermediaryInformation = this.refs.RealtorInformation.getFieldsValue();
        var housQuickInformation = this.refs.HousesValueQuickly.getFieldsValue();
        // housOritoInformation.surveyTime = Investigators.surveyTime && DateFormat.formatDate(Investigators.surveyTime);
        housOritoInformation.surveyTime = Investigators.surveyTime
        housOritoInformation.investigator = Investigators.investigator;

        postdata.housOritoInformation = housOritoInformation;
        postdata.housDataList = housDataList;
        postdata.housIntermediaryInformation = housIntermediaryInformation;
        postdata.housQuickInformation = housQuickInformation;
        postdata.consultId = selectRecord.consultId;
        postdata.processStateCode = selectRecord.processStateCode;
        postdata.projectId = selectRecord.projectId;
        postdata.processInstanceId = selectRecord.processInstanceId;
        Utils.ajaxData({
            url: '/modules/warrant/HousOritoInformationAction/saveDraft.htm'
            , method: 'post'
            , data: {
                searchParams: JSON.stringify(postdata)
            }
            , type: 'json'
            , callback: (result) => {
                if (result.code == 200) {
                    Modal.success({
                        title: result.msg

                    });
                    this.handleTabClick("3");
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
    render() {
        var props = this.props;
        var state = this.state;
        var modalBtns = [
            <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
            <button key="button" className="ant-btn ant-btn-primary"  onClick={this.handleOk}>
                提交
            </button>
        ];
        if (!props.canEdit) {
            modalBtns = [
                <button key="back" className="ant-btn" onClick={this.handleCancel}>返回</button>
            ]
        }
        if (props.title == "下户登记" && state.activekey == 3) {
            modalBtns = [
                <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
                <button key="submit" className="ant-btn" onClick={this.handleSave}>保存草稿</button>,
                <button key="button" className="ant-btn ant-btn-primary" disabled={!props.canEdit}  onClick={this.handleOk}>
                    提 交
                </button>
            ];
        }
        var canEdit = props.canEdit;
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
                                        <NextDoorInformation ref="NextDoorInformation" canEdit={props.canEdit} />
                                        <UploadPhoto style={{ marginLeft: 25 }} title="下户照片" btype="NEXTDOOR" canEdit={props.canEdit} selectRecord={props.record}/>
                                    </div>
                                    <div id="s2">
                                        <h2>资料清单</h2>
                                        <DataListing ref="DataListing" canEdit={props.canEdit} />
                                    </div>
                                    <div id="s3">
                                        <h2>房屋中介信息</h2>
                                        <RealtorInformation ref="RealtorInformation" canEdit={props.canEdit} />
                                        <UploadPhoto style={{ marginLeft: 25 }} title="中介名片" btype="REALTOR" canEdit={props.canEdit} selectRecord={props.record}/>
                                    </div>
                                    <div id="s4">
                                        <h2>房屋快出值信息</h2>
                                        <HousesValueQuickly ref="HousesValueQuickly" canEdit={props.canEdit} />
                                        <UploadPhoto style={{ marginLeft: 25 }} title="核行照片" btype="QUICKLY" canEdit={props.canEdit} selectRecord={props.record}/>
                                    </div>
                                    <div id="s5">
                                        <h2>调查员</h2>
                                        <Investigators ref="Investigators" canEdit={props.canEdit} />
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
export default AddlWin;
