import React from 'react';
import {
    Button,
    Modal,
    Tabs,
} from 'antd';

const TabPane = Tabs.TabPane;
const objectAssign = require('object-assign');
import ProcessInformation from '../../../CommonForm/ProcessInformation';
import UploadContract from './UploadContract';
var ReviewWin = React.createClass({
    getInitialState() {
        var selectRecord = this.props.record;
        return {
            status: {},
            activekey: "1",
            formData: {},
            visible: false
        };
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
    handleCancel() {
        this.props.hideModal();
    },
    render() {
        var props = this.props;
        var state = this.state;
        var modalBtns = [
            <button key="back" className="ant-btn" onClick={this.handleCancel}>返 回</button>,
            <button key="submit" className="ant-btn" onClick={this.handleOk}>提交</button>,
        ];
        return (
            <Modal title={props.title} visible={props.visible} onCancel={this.handleCancel}   width="1200"  footer={modalBtns} >
                <Tabs defaultActiveKey="1" onTabClick={this.handleTabClick} activeKey={this.state.activekey}>
                    <TabPane tab="上传合同" key="1">
                        <UploadContract ref="UploadContract" canEdit={props.canEdit} record={props.record}/>
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
