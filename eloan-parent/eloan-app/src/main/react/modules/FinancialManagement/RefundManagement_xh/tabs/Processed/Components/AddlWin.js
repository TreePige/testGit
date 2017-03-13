
import React from 'react';
import {
    Button,
    Modal,
    Tabs,
} from 'antd';
import HistoryApprove from '../../../../../CommonForm/HistoryApprove';
import ProcessInformation from '../../../../../CommonForm/ProcessInformation';
import BorrowingNeeds from '../../../../../CommonForm/BorrowingNeeds';
import AuthorityCardTab from '../../../../../CommonForm/AuthorityCardTab';
import ForInterviewing from '../../../../../CommonForm/ForInterviewing';
const NavLine = require("./../../../../../utils/NavLine");
var NewEvaOrg = require("../../../../../CommonForm/NewEvaOrg");
var NewQueryOrg = require("../../../../../CommonForm/NewQueryOrg");
var BasiInformation = require("../../../../../CommonForm/BasiInformation");
var HousingInformation = require("../../../../../CommonForm/HousingInformation");
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
    handleOk(e) {
        e.preventDefault();
        var me = this;
        var props = me.props;
        var record = props.record;
        var title = props.title;
        var url = "/modules/channel/FarcAsChannelAction/saveOrUpdateFarcAsChannel.htm";
        this.refs.BasiInformation.validateFields((errors, values) => {
            if (!!errors) {
                console.log('Errors in form!!!');
                return;
            }
            if (!(typeof values.automaticWithholdingTime === 'string')) {
                values.automaticWithholdingTime = values.automaticWithholdingTime && DateFormat.formatDate(values.automaticWithholdingTime);
            }
            var data = objectAssign({}, {
                farcAsChannel: JSON.stringify(values)
            }, {
                    status: 'create'
                });
            console.log(this.state)
            if (this.state.idData || title == "编辑") {
                values.id = values.id ? values.id : this.state.idData;
                var data = objectAssign({}, {
                    farcAsChannel: JSON.stringify(values)
                }, {
                        status: 'update'
                    });
            }
            Utils.ajaxData({
                url: url,
                data: data,
                callback: (result) => {
                    if (result.code == 200) {
                        Modal.success({
                            title: result.msg,
                            onOk: () => {
                                this.setState({
                                    idData: result.id
                                })
                            }
                        });

                    } else {
                        Modal.error({
                            title: result.msg,
                        });
                    }
                }
            });
        });
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
        "信息筛查": "#S3",
        "借款需求": "#S4",
    },
    render() {
        var props = this.props;
        var state = this.state;
        var modalBtns = [
            <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
            <button key="button" className="ant-btn ant-btn-primary"  onClick={this.handleOk}>
                保存
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
                    <TabPane tab="基本信息" key="1">
                        <div style={{ position: "relative" }}>
                            <div className="navLine-wrap" onScroll={NavLineUtils._handleSpy.bind(this, this.navLineData) }>
                                <div className="col-22 navLine-wrap-left" >
                                    <HousingInformation ref="HousingInformation" canEdit={props.canEdit}/>
                                    <NewEvaOrg ref="NewEvaOrg" canEdit={props.canEdit}/>
                                    <BasiInformation ref="BasiInformation" canEdit={props.canEdit}/>
                                    <NewQueryOrg ref="NewQueryOrg" canEdit={props.canEdit}/>
                                    <BorrowingNeeds ref="BorrowingNeeds" canEdit={props.canEdit} />
                                </div>
                                <div className="navLine-wrap-right" >
                                    <NavLine navLineData={this.navLineData} activeItem="#s1" ref="NavLine"/>
                                </div>
                            </div>
                        </div>
                    </TabPane>
                    <TabPane tab="审批历史" key="2">
                        <HistoryApprove />
                    </TabPane>
                    <TabPane tab="权证下户" key="3">
                        <AuthorityCardTab  ref="AuthorityCardTab" canEdit={props.canEdit}/>
                    </TabPane>
                    <TabPane tab="面审" key="4">
                        <ForInterviewing ref="ForInterviewing" canEdit={props.canEdit} />
                    </TabPane>
                    <TabPane tab="流程信息" key="5">
                        <ProcessInformation ref="ProcessInformation" canEdit={props.canEdit} />
                    </TabPane>
                </Tabs>
            </Modal>
        );
    }
});
export default AddlWin;