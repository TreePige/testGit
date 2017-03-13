
import React from 'react';
import {
    Button,
    Modal,
    Tabs,
} from 'antd';
import HistoryApprove from '../../../CommonForm/HistoryApprove';
import ProcessInformation from '../../../CommonForm/ProcessInformation';
import BorrowingNeeds from '../../../CommonForm/BorrowingNeeds';
const NavLine = require("./../../../utils/NavLine");
var NewEvaOrg = require("../../../CommonForm/NewEvaOrg");
var NewQueryOrg = require("../../../CommonForm/NewQueryOrg");
var BasiInformation = require("../../../CommonForm/BasiInformation");
var HousingInformation = require("../../../CommonForm/HousingInformation");
var UploadPhoto = require("../../../CommonForm/UploadPhoto");
const TabPane = Tabs.TabPane;
var UploadPic = require("../../../CommonForm/UploadPic");
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
        this.refs.HousingInformation.resetFields();
        this.refs.BasiInformation.resetFields();
        this.refs.BorrowingNeeds.resetFields();
        this.refs.NewEvaOrg.resetFields();
        // this.refs.ProcessInformation.resetFields();
        this.refs.NewQueryOrg.resetFields();
        this.props.hideModal();
        this.changeTabState()
        
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
                        var queryData = this.refs.NewQueryOrg.getFieldsValue();
                        postdata.housEnquiryInstitution = this.transformQueryData(queryData);
                        postdata.housAssessmentAgencies = formData;
                        postdata.housBorrowingBasics = housBorrowingBasics;
                        postdata.housPropertyInformation = housPropertyInformation;
                        postdata.plBorrowRequirement = plBorrowRequirement;
                        commentData.comment = remarkData.nextStep;

                        postdata.remarkComment = remarkData.remarkComment;
                        postdata.receiveType = remarkData.receiveType;
                        postdata.amountComment = remarkData.amountComment;
                        postdata.manualAssignee = remarkData.manualAssignee;


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
        "房产信息": "#s1",
        "基本信息": "#s2",
        "信息筛查": "#s3",
        "借款需求": "#s4",
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
        var canEdit;
        console.log(this.props.record)
        if (this.props.record && this.props.record.processStateCode == "usertask-householdTaskAssign") {
            console.log(111)
            canEdit = false;
        } else {
            canEdit = props.canEdit;
        }
        var idData=props.id
        return (
            <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel}   width="1200"  footer={modalBtns} >

                <Tabs defaultActiveKey="1" onTabClick={this.handleTabClick} activeKey={this.state.activekey}>
                    <TabPane tab="基本信息" key="1" >
                        <div style={{ position: "relative" }}>
                            <div className="navLine-wrap" onScroll={NavLineUtils._handleSpy.bind(this, this.navLineData) }>
                                <div className="col-22 navLine-wrap-left" >
                                    <div id="s1">
                                        <h2>房产信息</h2>
                                        <HousingInformation ref="HousingInformation" canEdit={canEdit}/>

                                        <NewEvaOrg ref="NewEvaOrg" canEdit={false} record={props.record}/>
                                        <UploadPic ref="RoomThisPhoto" style={{ marginLeft: 25 }} title="房本照片" btype="HOUSE" canEdit={false} selectRecord={props.record} idData={idData}/>
                                    </div>
                                    <div id="s2">
                                        <h2>基本信息</h2>
                                        <BasiInformation ref="BasiInformation" canEdit={canEdit}/>
                                        <UploadPhoto style={{ marginLeft: 25 }} ref="IdCardPhodo" title="身份证照片" btype="IDCARD" canEdit={false} selectRecord={props.record}/>

                                    </div>
                                    <div id="s3">
                                        <h2>信息筛查</h2>
                                        <NewQueryOrg ref="NewQueryOrg" canEdit={false} record={props.record}/>
                                    </div>
                                    <div id="s4">
                                        <h2>借款需求</h2>
                                        <BorrowingNeeds ref="BorrowingNeeds" canEdit={canEdit} />
                                    </div>
                                </div>
                                <div className="navLine-wrap-right" >
                                    <NavLine navLineData={this.navLineData} activeItem="#s1" ref="NavLine"/>
                                </div>
                            </div>
                        </div>
                    </TabPane>
                    <TabPane tab="审批历史" key="2">
                        <HistoryApprove record={props.record}/>
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