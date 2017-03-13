import React from 'react';
import {
    Button,
    Modal,
    Tabs,
} from 'antd';
import BasiInformation from '../../../CommonForm/BasiInformation3';
import HistoryApprove from '../../../CommonForm/HistoryApprove';
import ProcessInformation from '../../../CommonForm/ProcessInformation';
import ContractGenerate from '../../../CommonForm/ContractGenerate';
const TabPane = Tabs.TabPane;
const objectAssign = require('object-assign');
var ContractWin = React.createClass({
    getInitialState() {
        var selectRecord = this.props.record;
        return {
            status: {},
            activekey: "1", 
            formData: {}
        };
    },
    handleCancel() {
        this.props.hideModal();
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
                values.automaticWithholdingTime = values.automaticWithholdingTime&&DateFormat.formatDate(values.automaticWithholdingTime);
            } 
            var data = objectAssign({}, {
                farcAsChannel: JSON.stringify(values)
            }, {
                    status: 'create'
                });
                console.log(this.state)
            if (this.state.idData||title == "编辑") { 
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
    render() {
        var props = this.props;
        var state = this.state;
        var modalBtns = [
            <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
        ];
        return (
            <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel}   width="1200"  footer={modalBtns} >
                 <Tabs defaultActiveKey="1" onTabClick={this.handleTabClick} activeKey={this.state.activekey}>
                    <TabPane tab="V房贷合同生成页面" key="1">
                        <ContractGenerate ref="ContractGenerate" canEdit={props.canEdit} />
                    </TabPane>
                     <TabPane tab="附件上传页面" key="2">
                        <BasiInformation ref="BasiInformation" canEdit={props.canEdit} />
                    </TabPane>
                    <TabPane tab="合同查看详情页面" key="3">
                        <BasiInformation ref="BasiInformation" canEdit={props.canEdit} />
                    </TabPane>
                </Tabs>
            </Modal>
        );
    }
});
export default ContractWin;
