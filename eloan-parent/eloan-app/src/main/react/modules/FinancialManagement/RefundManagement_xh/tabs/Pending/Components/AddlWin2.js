
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
var Refund_xh = require("../../../../../CommonForm/Refund_xh");
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
            <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel}   width="800"  footer={modalBtns} >
                <Tabs defaultActiveKey="1" onTabClick={this.handleTabClick} activeKey={this.state.activekey}>
                    <TabPane tab="下户费退费" key="1">
                        <div style={{ position: "relative" }}>
                            <div className="navLine-wrap" onScroll={NavLineUtils._handleSpy.bind(this, this.navLineData) }>
                                <div className="col-22 navLine-wrap-left" >
                                    <Refund_xh ref="Refund_xh" canEdit={props.canEdit}/>
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