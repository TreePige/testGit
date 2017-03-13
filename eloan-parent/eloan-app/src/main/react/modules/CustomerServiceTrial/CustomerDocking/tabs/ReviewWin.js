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
var NewEvaOrg = require("../../../CommonForm/NewEvaOrg");
var HousingInformation = require("../../../CommonForm/HousingInformation");
var BasiInformation = require("../../../CommonForm/BasiInformation");
var UploadPhoto = require("../../../CommonForm/UploadPhoto");
var ProcessInformation = require("../../../CommonForm/ProcessInformation");
import BorrowingNeeds from '../../../CommonForm/BorrowingNeeds';
var UploadPic = require("../../../CommonForm/UploadPic");
var ReviewWin = React.createClass({
    getInitialState() {
        var selectRecord = this.props.record;
        return {
            status: {
                idData: ""
            },
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
        this.refs.NewEvaOrg.resetFields();
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
        this.setState({
            activekey: key
        }, () => {
        })
    },
    navLineData: {
        "房产信息": "#s1",
        "基本信息": "#s2",
        "借款需求": "#s3",
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
                    me.handleTabClick("3");
                    return;
                }
                var validation3 = this.refs.ProcessInformation.validateFields;
                validation3((errors, values) => {
                    if (!!errors) {
                        me.handleTabClick("3");
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
                        //housPropertyInformation.id = this.props.id
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
        var housBorrowingBasics = this.refs.BasiInformation.getFieldsValue();
        var housPropertyInformation = this.refs.HousingInformation.getFieldsValue();
        var plBorrowRequirement = this.refs.BorrowingNeeds.getFieldsValue();
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
        housPropertyInformation.id = this.props.id
        postdata.housAssessmentAgencies = formData;
        postdata.housBorrowingBasics = housBorrowingBasics;
        postdata.housPropertyInformation = housPropertyInformation;
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
    render() {
        var props = this.props;
        var state = this.state;
        var button;
        var modalBtns = [
            <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
            <button key="button" className="ant-btn ant-btn-primary" disabled={!props.canEdit}  onClick={this.handleOk}>
                提 交
            </button>
        ];
        if (props.title == "处理" && state.activekey == "1") {
            modalBtns = [
                <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
                <button key="submit" className="ant-btn" onClick={this.handleSave}>保存草稿</button>,
                <button key="button" className="ant-btn ant-btn-primary" disabled={!props.canEdit}  onClick={this.handleOk}>
                    提 交
                </button>
            ];
        }
        if (!props.canEdit) {
            modalBtns = [
                <button key="back" className="ant-btn" onClick={this.handleCancel}>关闭</button>
            ];
        }
        var NewEva = (
            <NewEvaOrg ref="NewEvaOrg" canEdit={props.canEdit} record={props.record}/>
        )
        var idData = props.id

        return (
            <Modal title={props.title} visible={props.visible}  onCancel={this.handleCancel}   width="1200"  footer={modalBtns} >
                <Tabs defaultActiveKey="1" onTabClick={this.handleTabClick} activeKey={this.state.activekey}>
                    <TabPane tab="房产评估" key="1">
                        <div style={{ position: "relative" }}>
                            <div className="navLine-wrap" onScroll={NavLineUtils._handleSpy.bind(this, this.navLineData) }>
                                <div className="col-22 navLine-wrap-left" >
                                    <div id="s1">
                                        <h2>房产信息</h2>
                                        <HousingInformation ref="HousingInformation" canEdit={props.canEdit}/>
                                        {NewEva}

                                        <UploadPic ref="RoomThisPhoto" style={{ marginLeft: 25 }} title="房本照片" btype="HOUSE" canEdit={props.canEdit} selectRecord={props.record} idData={idData}/>
                                    </div>
                                    <div id="s2">
                                        <h2>基本信息</h2>
                                        <BasiInformation ref="BasiInformation" canEdit={props.canEdit}/>
                                        <UploadPhoto ref="IdCardPhodo" style={{ marginLeft: 25 }}title="身份证照片" btype="IDCARD" canEdit={props.canEdit} selectRecord={props.record}/>
                                    </div>
                                    <div id="s3">
                                        <h2>借款需求</h2>
                                        <BorrowingNeeds ref="BorrowingNeeds" canEdit={props.canEdit} />
                                    </div>
                                </div>
                                <div className="navLine-wrap-right" >
                                    <NavLine navLineData={this.navLineData} activeItem="#s1" ref="NavLine"/>
                                </div>
                            </div>
                        </div>
                    </TabPane>

                    <TabPane tab="审批历史" key="2">
                        <HistoryApprove ref="HistoryApprove" canEdit={props.canEdit} record={props.record}/>
                    </TabPane>
                    <TabPane tab="流程信息" key="3">
                        <ProcessInformation ref="ProcessInformation" canEdit={props.canEdit} record={props.record}/>
                    </TabPane>
                </Tabs>
            </Modal>
        );
    }
});
export default ReviewWin;
