//房本照片
import React from 'react';
import {
    Button,
    Form,
    Input,
    Modal,
    Select,
    Upload,
    message
} from 'antd';

import ShowPhotoWin from './ShowPhotoWin'
const createForm = Form.create;
const Option = Select.Option;
var LenderId = React.createClass({
    getInitialState() {
        return { 
            picData: [],
            visible: false,
        }
    },
    showModal(title, canEdit) { 
        var selectRecord = this.props.selectRecord;
        var btype = this.props.btype;
        Utils.ajaxData({
            url: '/modules/common/PubAttachmentAction/queryAll.htm',
            data: {
                search: JSON.stringify({
                    projectId: selectRecord && selectRecord.projectId,
                    processInstanceId: selectRecord && selectRecord.processInstanceId,
                    btype: btype,
                })
            },
            callback: (result) => { 
                this.setState({
                    canEdit: canEdit,
                    visible: true,
                    title: title,
                    picData:result.data,
                });
            }
        });
    },
    hideModal() {
        this.setState({
            visible: false
        });
    },
    render() {
        var canEdit = this.props.canEdit;
        var selectRecord = this.props.selectRecord; 
        var btype = this.props.btype;
        var isFirst = true;
        const uploadProps = {
            name: 'Filedata',
            action: '/modules/common/PubAttachmentAction/upload.htm',
            multiple: true,
            data: {
                data: JSON.stringify({
                    projectId: selectRecord && selectRecord.projectId,
                    processInstanceId: selectRecord && selectRecord.processInstanceId,
                    btype: btype,
                })
            },
            onChange(info) {
                if (info.file.status !== 'uploading') {
                   // console.log(info.file, info.fileList);
                }
                if (info.file.status === 'done' && info.file.response && info.file.response.code == 200) {
                    isFirst=true;
                    message.destroy();
                    message.success(`${info.file.name} 上传成功。`); 
                } else if (info.file.status === 'error'){
                    isFirst=true;
                    message.destroy();
                    message.error(`${info.file.name} 上传失败。`);
                } else if(info.file.response&&info.file.response.code == 400){
                    isFirst=true;
                    message.destroy();
                    message.error(`${info.file.name} ${info.file.response.msg}。`);
                }
                else {
                    if(info.file.status=="uploading"&&isFirst){
                       message.loading(`${info.file.name} 上传中.......`, 0);
                       isFirst=false
                    }
                    
                }
            },
            beforeUpload(file) {
                var size = file.size;
                if(size>5242880){
                     message.error('上传文件不能大于5M！');
                     return false;
                }
                const isJPG = file.type === 'image/jpeg';
                if (!isJPG) {
                    message.error('只能上传 JPG 文件哦！');
                }
                return isJPG; 
            },
        };
        var state = this.state;
        return (<span style={this.props.style}>
            <span style={{  marginTop: 25,marginBottom:15 }} >
                <span style={{ marginRight: 25, }}>{this.props.title}</span>
                {canEdit ? <Upload {...uploadProps}> <a style={{ marginRight: 25, }}>上传附件</a> </Upload> : null}
                <a style={{ marginRight: 25, }} onClick={this.showModal.bind(this, '查看附件', canEdit) }>查看附件</a>
            </span >

            <ShowPhotoWin ref="ShowPhotoWin" showModal={this.showModal.bind(this, '查看附件', canEdit) }
                visible={state.visible} canEdit={this.props.canEdit}
                title={state.title} hideModal={this.hideModal}
                record={state.selectedrecord}
                canEdit={state.canEdit}
                picData={state.picData}
                />
        </span>
        )
    }
});
export default LenderId;