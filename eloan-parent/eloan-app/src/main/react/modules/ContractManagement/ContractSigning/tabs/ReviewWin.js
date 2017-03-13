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
var ProcessInformation = require("../../../CommonForm/ProcessInformation");
import BorrowingNeeds from '../../../CommonForm/BorrowingNeeds';
import ContractGenerate from '../../../CommonForm/ContractGenerate';
import GenerateContract from './GenerateContract';
var ReviewWin = React.createClass({
    getInitialState() {
        var selectRecord = this.props.record;
        return {
            status: {},
            activekey: "1",
            formData: {},
            tabVisual:false,
        };
    },
    handleCancel() {
        this.props.hideModal();
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
        if (!this.validateTab1()) {
            return
        }
        else {
            this.validate2(true)
        }

    },
    validateTab1() {//验证第一个tab页
        var validation = this.refs.ContractGenerate;
        var me = this;
        var A = false;
        validation.validateFields((errors, values) => {
            if (!!errors) {
                console.log('Errors in form!!!');
                return;
            } else {
                A = true;
            }
        })
        return A
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
    showTab() {
        this.setState({tabVisual:true })
    },
    render() {
        var props = this.props;
        var state = this.state;
        var modalBtns = [
            <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
            <button key="submit" className="ant-btn" onClick={this.handleOk}>保存</button>,
        ];
        //点击显示tabs
        var item;
        if (state.tabVisual){
            item = (
                <Tabs defaultActiveKey="1" onTabClick={this.handleTabClick} activeKey={this.state.activekey}>
                    <TabPane tab="房屋买卖合同" key="1">
                        <GenerateContract ref="GenerateContract" canEdit={props.canEdit} />
                    </TabPane>
                    <TabPane tab="房产折价抵偿协议" key="2">
                        <GenerateContract ref="GenerateContract" canEdit={props.canEdit} />
                    </TabPane>
                    <TabPane tab="债权转让通知书" key="3">
                        <GenerateContract ref="GenerateContract" canEdit={props.canEdit} />
                    </TabPane>
                </Tabs>
            )
        }
        return (
            <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel}   width="1200"  footer={modalBtns} >
                <ContractGenerate ref="ContractGenerate" canEdit={props.canEdit} showTab={this.showTab}/>
                {item}
            </Modal>                
        );
    }
});
export default ReviewWin;
