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
var UploadPic = require("../../../CommonForm/UploadPic");
var ProcessInformation = require("../../../CommonForm/ProcessInformation");
import BorrowingNeeds from '../../../CommonForm/BorrowingNeeds';
var roleId = window.roleId;
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
        if (this.refs.BasiInformation) {
            this.refs.BasiInformation.resetFields();
        }
        if (this.refs.HousingInformation) {
            this.refs.HousingInformation.resetFields();
        }
        if (this.refs.BorrowingNeeds) {
            this.refs.BorrowingNeeds.resetFields();
        }
        this.props.hideModal();
        this.changeTabState()
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
        })
    },
    navLineData: {
        "房产信息": "#s1",
        "基本信息": "#s2",
        "借款需求": "#s3",
    },

    handleOk() {
        var props = this.props;
        if (props.title == "新增申请") {
            this.handleOk1();
        } else if (props.title == "申请进件") {
            if (roleId == "customerServiceStaffA") {
                this.handleOk3();
            } else {
                this.handleOk2();
            }
        }
    },
    validateTab1() {//验证第一个tab页 
        var me = this;
        var A = false;
        var B = false;
        var C = false;
        this.refs.HousingInformation.validateFields((errors, values) => {
            if (!!errors) {
                return;
            } else {
                A = true;
            }
        })
        this.refs.BasiInformation.validateFields((errors, values) => {
            if (!!errors) {
                return;
            } else {
                B = true;
            }
        })
        this.refs.BorrowingNeeds.validateFields((errors, values) => {
            if (!!errors) {
                return;
            } else {
                C = true;
            }
        })
        return A && B && C
    },
    handleOk1() {

        if (this.validateTab1()) {
            var state = this.state;
            var props = this.props;
            var creditConsultFrom = {};
            var housBorrowingBasics = this.refs.BasiInformation.getFieldsValue();
            var housPropertyInformation = this.refs.HousingInformation.getFieldsValue();
            var plBorrowRequirement = this.refs.BorrowingNeeds.getFieldsValue();
            housPropertyInformation.id = this.state.idData
            creditConsultFrom.housBorrowingBasics = housBorrowingBasics;
            creditConsultFrom.housPropertyInformation = housPropertyInformation;
            creditConsultFrom.plBorrowRequirement = plBorrowRequirement;
            creditConsultFrom.nextStep = "1";
            Utils.ajaxData({
                url: '/modules/common/action/plConsultAction/addConsult.htm',
                method: 'post',
                data: {
                    creditConsultFrom: JSON.stringify(creditConsultFrom)
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
    },
    handleOk2() {

        if (!this.validateTab1()) {
            return
        }
        var NewEvaOrgsValidation = this.refs.NewEvaOrg.validateFields;
        var evaOrgValid = false;
        NewEvaOrgsValidation((errors, values) => {
            if (!!errors) {
                Modal.error({
                    title: '评估机构分值不能为空',
                    onOk: () => {
                        this.handleTabClick("1");
                    }
                });
                return;
            }
            else {
                evaOrgValid = true;
            }
        });
        var NewEvaOrgData = this.refs.NewEvaOrg.getFieldsValue();
        if (NewEvaOrgData.keys == 0) {
            //新增评分机构记录为空
            Modal.error({
                title: '请新增一条评分机构记录',
                onOk: () => {
                    this.handleTabClick("1");
                }
            });
            return;
        }
        if (evaOrgValid) {
            this.validate2(true)
        }
    },
    validate2() {
        var me = this;
        var ProcessInformation = this.refs.ProcessInformation;
        if (!ProcessInformation) {
            me.handleTabClick("3");
            return;
        }
        var validation3 = this.refs.ProcessInformation.validateFields;
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
    },


    handleOk3() {
        var me = this;
        var HousingInformation = this.refs.HousingInformation;
        if (!HousingInformation) {
            me.handleTabClick("1");
            return;
        }

        var NewEvaOrgsValidation = this.refs.NewEvaOrg.validateFields;
        var evaOrgValid = false;
        NewEvaOrgsValidation((errors, values) => {
            if (!!errors) {
                Modal.error({
                    title: '评估机构分值不能为空',
                    onOk: () => {
                        this.handleTabClick("1");
                    }
                });
                return;
            }
            else {
                evaOrgValid = true;
            }
        });
        var NewEvaOrgData = this.refs.NewEvaOrg.getFieldsValue();
        if (NewEvaOrgData.keys == 0) {
            //新增评分机构记录为空
            Modal.error({
                title: '请新增一条评分机构记录',
                onOk: () => {
                    this.handleTabClick("1");
                }
            });
            return;
        }
        if (evaOrgValid) {
            this.validate3(true)
        }
    },
    validate3() {
        var me = this;
        var ProcessInformation = this.refs.ProcessInformation;
        if (!ProcessInformation) {
            me.handleTabClick("3");
            return;
        }
        var validation3 = this.refs.ProcessInformation.validateFields;
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
                var housPropertyInformation = this.refs.HousingInformation.getFieldsValue();
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
                postdata.housPropertyInformation = housPropertyInformation;
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
    },
    handleSave() {
        var state = this.state;
        var props = this.props;
        var selectRecord = props.record;
        var postdata = {};

        var housPropertyInformation = this.refs.HousingInformation.getFieldsValue();

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
        postdata.housAssessmentAgencies = formData;

        postdata.housPropertyInformation = housPropertyInformation;

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
    handleSav2() {
        var state = this.state;
        var props = this.props;
        var housPropertyInformation = this.refs.HousingInformation.getFieldsValue();
        Utils.ajaxData({
            url: '/modules/instance/HousPropertyInformationAction/saveOrUpdateHousPropertyInformation.htm',
            method: 'post',
            data: {
                status: "create",
                housPropertyInformation: JSON.stringify(housPropertyInformation)
            },
            type: 'json',
            callback: (result) => {
                if (result.code == 200) {
                    Modal.success({
                        title: result.msg,
                    });
                    this.setState({
                        idData: result.id
                    })
                } else {
                    Modal.error({
                        title: result.msg,
                    });
                }
            }
        });
    },
    showTip() {
        Modal.info({
            title: '请先保存草稿信息'
        })
    },
    render() {
        var props = this.props;
        console.log(props)
        var state = this.state;
        var modalBtns = [
            <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
            <button key="button" className="ant-btn ant-btn-primary" disabled={!props.canEdit}  onClick={this.handleOk}>
                提 交
            </button>
        ];
        if (props.title == "申请进件" && state.activekey == "1") {
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
        var item = [];
        if (props.title == "申请进件" || props.title == "查看") {
            item = [
                <TabPane tab="审批历史" key="2">
                    <HistoryApprove ref="HistoryApprove" canEdit={props.canEdit} record={props.record}/>
                </TabPane>,
                <TabPane tab="流程信息" key="3">
                    <ProcessInformation ref="ProcessInformation" canEdit={props.canEdit} record={props.record}/>
                </TabPane>
            ];
        }
        var roleId = window.roleId;
        var need = (
            <TabPane tab="房产评估" key="1">
                <div style={{ position: "relative" }}>
                    <div className="navLine-wrap" onScroll={NavLineUtils._handleSpy.bind(this, this.navLineData) }>
                        <div className="col-22 navLine-wrap-left" >
                            <div id="s1">
                                <h2>房产信息</h2>
                                <HousingInformation ref="HousingInformation" canEdit={props.canEdit} record={props.record} title={props.title}/>
                                {this.props.title == '申请进件' || this.props.title == '查看' ? <NewEvaOrg ref="NewEvaOrg" canEdit={props.canEdit} record={props.record}/> : null}

                                {
                                    state.idData ?
                                        <UploadPic ref="RoomThisPhoto" style={{ marginLeft: 25 }} title="房本照片" btype="HOUSE" canEdit={props.canEdit} selectRecord={props.record} idData={state.idData}/>
                                        : <a onClick={this.showTip} style={{ marginLeft: "20px" }}>上传附件</a>
                                }
                                {this.props.title == '新增申请' ? <button key="submit" className="ant-btn" style={{ marginLeft: "15px" }} onClick={this.handleSav2}>保存草稿</button> : null}
                            </div>
                            <div id="s2">
                                <h2>基本信息</h2>
                                <BasiInformation ref="BasiInformation" canEdit={props.canEdit}/>
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
        )
        if (roleId == "customerServiceStaffA" && props.title == "申请进件"||roleId == "customerServiceStaffA" && props.title == "查看") {
            var idData = props.id
            need = (
                <TabPane tab="房产信息" key="1">
                    <HousingInformation ref="HousingInformation" canEdit={props.canEdit} record={props.record} title={props.title}/>
                    {this.props.title == '申请进件' || this.props.title == '查看' ? <NewEvaOrg ref="NewEvaOrg" canEdit={props.canEdit} record={props.record}/> : null}
                    <UploadPic ref="RoomThisPhoto" style={{ marginLeft: 25 }} title="房本照片" btype="HOUSE" canEdit={props.canEdit} selectRecord={props.record} idData={idData}/>
                </TabPane>
            )
        }
        return (
            <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel}   width="1200"  footer={modalBtns} >
                <Tabs defaultActiveKey="1" onTabClick={this.handleTabClick} activeKey={this.state.activekey}>
                    {need}
                    {item}
                </Tabs>
            </Modal>
        );
    }
});
export default ReviewWin;
