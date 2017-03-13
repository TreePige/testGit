
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
var Refund_xh = require("../../../CommonForm/Refund_xh");
var Commission = require("../../../CommonForm/Commission");
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
                        return
                    } else {
                        var state = this.state;
                        var props = this.props;
                        var selectRecord = props.record;
                        var postdata = {};
                        var commentData = {};
                        var remarkData = this.refs.ProcessInformation.getFieldsValue();                 
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
        return (
            <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel}   width="1200"  footer={modalBtns} >

                <Tabs defaultActiveKey="1" onTabClick={this.handleTabClick} activeKey={this.state.activekey}>
                    <TabPane tab="基本信息" key="1" >
                        <div style={{ position: "relative" }}>
                            <div className="navLine-wrap" onScroll={NavLineUtils._handleSpy.bind(this, this.navLineData) }>
                                <div className="col-22 navLine-wrap-left" >
                                    <Commission ref="Commission" canEdit={!props.canEdit} title={props.title}/>
                                </div>
                                <div className="navLine-wrap-right" >
                                    <NavLine navLineData={this.navLineData} activeItem="#s1" ref="NavLine"/>
                                </div>
                            </div>
                        </div>
                    </TabPane>
                </Tabs>
            </Modal>
        );
    }
});
export default AddlWin;