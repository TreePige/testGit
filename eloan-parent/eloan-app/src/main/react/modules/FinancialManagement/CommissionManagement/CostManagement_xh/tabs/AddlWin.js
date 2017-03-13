import React from 'react';
import {
    Button,
    Modal,
    Tabs,
} from 'antd';
import ProcessInformation from '../../../CommonForm/ProcessInformation';
const NavLine = require("./../../../utils/NavLine");
import Charge_xh from '../../../CommonForm/Charge_xh';
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
     handleCancel() {
        this.refs.Charge_xh.resetFields();
        this.props.hideModal();
        // this.refs.ProcessInformation.resetFields();
    },
    componentWillReceiveProps(nextProps) {
        console.log(nextProps.formData)
        this.refs.NewQueryOrg && this.refs.NewQueryOrg.setFieldsValue(nextProps.formData)
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
        "基本信息": "#s1",
        "下户费收取": "#S4",
    },
    //整体提交数据
    handleOk() {


        var me = this;
        var validation2 = this.refs.Charge_xh.validateFields;
        validation2((errors, values) => {
            if (!!errors) {
                me.handleTabClick("1");
            } else {
                var ProcessInformation = this.refs.ProcessInformation;
                if (!ProcessInformation) {
                    me.handleTabClick("2");
                    return;
                }
                var validation3 = this.refs.ProcessInformation.validateFields;
                validation3((errors, values) => {
                    if (!!errors) {
                        me.handleTabClick("2");
                        return
                    } else {
                        var state = this.state;
                        var props = this.props;
                        var selectRecord = props.record;
                        var postdata = {};
                        var commentData = {};
                        var housLowerCost = {};
                        var remarkData = this.refs.ProcessInformation.getFieldsValue();
                        var Charge = this.refs.Charge_xh.getFieldsValue();

                        housLowerCost.financeSpecialist = Charge.financeSpecialist
                        housLowerCost.receiveType = Charge.receiveType
                        housLowerCost.receivableAccount = Charge.receivableAccount
                        housLowerCost.actualFee = Charge.actualFee
                        postdata.housLowerCost=housLowerCost

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
            <Modal title={props.title} visible={props.visible} destroyInactiveTabPane onCancel={this.handleCancel}   width="1200"  footer={modalBtns} >
                <Tabs defaultActiveKey="1" onTabClick={this.handleTabClick} activeKey={this.state.activekey}>
                    <TabPane tab="客服初评" key="1">
                        <div style={{ position: "relative" }}>
                            <div className="navLine-wrap" onScroll={NavLineUtils._handleSpy.bind(this, this.navLineData) }>
                                <div className="col-22 navLine-wrap-left" >
                                    <Charge_xh ref="Charge_xh" canEdit={props.canEdit} />
                                </div>
                                <div className="navLine-wrap-right" >
                                    <NavLine navLineData={this.navLineData} activeItem="#s1" ref="NavLine"/>
                                </div>
                            </div>
                        </div>
                    </TabPane>
                    <TabPane tab="流程信息" key="2">
                        <ProcessInformation ref="ProcessInformation" canEdit={props.canEdit} record={props.record}/>
                    </TabPane>
                </Tabs>
            </Modal>
        );
    }
});
export default ReviewWin;
