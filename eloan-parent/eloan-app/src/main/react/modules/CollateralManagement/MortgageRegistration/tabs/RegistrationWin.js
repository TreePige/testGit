import React from 'react';
import {
    Button,
    Modal,
    Tabs,
} from 'antd';
import ProcessInformation from '../../../CommonForm/ProcessInformation';
//const NavLine = require("./../../../utils/NavLine");
import MortRegistratWin from '../../../CommonForm/MortRegistratWin';
const TabPane = Tabs.TabPane;
const objectAssign = require('object-assign');
var ReviewWin = React.createClass({
    getInitialState() {
        var selectRecord = this.props.record;
        return {
            activekey: "1",
            formData: {},
            loading:false
        };
    },
    componentWillReceiveProps(nextProps) {
        //this.setState({activekey:"1"})
        //this.refs.MortRegistratWin && this.refs.MortRegistratWin.setFieldsValue(nextProps.formData)
    },
    handleCancel() {
        this.handleTabClick("1");
        this.props.hideModal();
    },
    handleTabClick(key) {
        this.setState({
            activekey: key
        }/*, () => {
            if (key == 6) {

                this.refs.ApprovalOpinions.setFieldsValue({ loanAgreementCode: 22 });
            }
        }*/)
    },
    //整体提交数据
    handleOk() {

  
        var me = this;
        var TabValid1 = this.refs.MortRegistratWin.validateFields;
        TabValid1((errors, values) => {
            if (!!errors) {
                me.handleTabClick("1");
            } else {
                var ProcessInformation = this.refs.ProcessInformation;
                if (!ProcessInformation) {
                    me.handleTabClick("2");
                    return;
                }
                var TabValid2 = this.refs.ProcessInformation.validateFields;
                TabValid2((errors, values) => {
                    if (!!errors) {
                        me.handleTabClick("2");
                        return;
                    } else {
                        var state = this.state;
                        var props = this.props;
                        var selectRecord = props.record;
                        var postdata = {};
                        var commentData = {};

                        var remarkData = this.refs.ProcessInformation.getFieldsValue();
                        var Charge = this.refs.MortRegistratWin.getFieldsValue();
                        postdata.housMortgageRegistration = Charge;
                        commentData.comment = remarkData.nextStep;
                        postdata.remarkComment = remarkData.remarkComment;
                        postdata.consultId = selectRecord.consultId;
                        postdata.nextStep = remarkData.nextStep;
                        postdata.processStateCode = selectRecord.processStateCode;
                        postdata.projectId = selectRecord.projectId;
                        postdata.processInstanceId = selectRecord.processInstanceId;
                        Utils.ajaxSubmit({
                            me:me,
                            url: '/modules/workflow/controller/ProcessTaskController/completeTask.htm',
                            method: 'post',
                            data: {
                                taskId: props.record.taskId,
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
            <Button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</Button>,
            <Button key="button" className="ant-btn ant-btn-primary" loading={state.loading} disabled={!props.canEdit}  onClick={this.handleOk}>
                提 交
            </Button>
        ];
        if (!props.canEdit) {
            modalBtns = [
                <Button key="back" className="ant-btn" onClick={this.handleCancel}>关闭</Button>
            ];
        }
        return (
            <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel}   width="900"  footer={modalBtns} >
                <Tabs defaultActiveKey="1" onTabClick={this.handleTabClick} activeKey={this.state.activekey}>
                    <TabPane tab="基本信息" key="1">
                        <div style={{ position: "relative" }}>
                            <div className="navLine-wrap">
                                <MortRegistratWin ref="MortRegistratWin" canEdit={props.canEdit} record={props.record}/>
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
