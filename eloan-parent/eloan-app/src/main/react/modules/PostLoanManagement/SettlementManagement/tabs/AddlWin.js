import React from 'react';
import {
    Button,
    Modal,
    Tabs,
} from 'antd';
import SettleInfo from '../../../CommonForm/SettleInfo';
const NavLine = require("./../../../utils/NavLine");
import SettlementMangWin from '../../../CommonForm/SettlementMangWin';
const TabPane = Tabs.TabPane;
const objectAssign = require('object-assign');
var ReviewWin = React.createClass({
    getInitialState() {
        var selectRecord = this.props.record;
        return {
            status: {},
            activekey: "1",
            formData: {}
        };
    },
    componentWillReceiveProps(nextProps) {
        console.log(nextProps.formData)
        this.refs.NewQueryOrg && this.refs.NewQueryOrg.setFieldsValue(nextProps.formData)
    },
    handleCancel() {
        this.props.hideModal();
    },
    handleTabClick(key) {
        this.setState({
            activekey: key
        }, () => {
            if (key == 6) {

                this.refs.ApprovalOpinions.setFieldsValue({ loanAgreementCode: 22 });
            }
        })
    },
    navLineData: {
        "基本信息": "#s1"
    },
    //整体提交数据
    handleOk() {

  
        var me = this;
        var validation2 = this.refs.MortRegistratWin.validateFields;
        validation2((errors, values) => {
            if (!!errors || !isvalid1) {
                me.handleTabClick("1");
            } else {
                var SettleInfo = this.refs.SettleInfo;
                if (!SettleInfo) {
                    me.handleTabClick("2");
                    return;
                }
                var validation3 = this.refs.SettleInfo.validateFields;
                validation3((errors, values) => {
                    if (!!errors) {
                        return
                    } else {
                        var state = this.state;
                        var props = this.props;
                        var selectRecord = props.record;
                        var postdata = {};
                        var commentData = {};

                        var remarkData = this.refs.SettleInfo.getFieldsValue();
                        var Charge = this.refs.SettlementMangWin.getFieldsValue();
                        postdata.housLowerCostBasicInfo = Charge;

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
                            url: '/modules/workflow/controller/ProcessTaskController/completeTask.htm11111111',
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
        return (
            <Modal title={props.title} visible={props.visible} destroyInactiveTabPane onCancel={this.handleCancel}   width="900"  footer={modalBtns} >
                <Tabs defaultActiveKey="1" onTabClick={this.handleTabClick} activeKey={this.state.activekey}>
                    <TabPane tab="基本信息" key="1">
                        <div style={{ position: "relative" }}>
                            <SettlementMangWin ref="SettlementMangWin" canEdit={props.canEdit} />
                        </div>
                    </TabPane>
                    <TabPane tab="流程信息" key="4">
                        <SettleInfo ref="SettleInfo" canEdit={props.canEdit} record={props.record}/>
                    </TabPane>
                </Tabs>
            </Modal>
        );
    }
});
export default ReviewWin;
