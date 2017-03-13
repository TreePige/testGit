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
const createForm = Form.create;
const Option = Select.Option;
var RoomThisPhoto = React.createClass({
    getInitialState() {
        return {
            orgData: [],
            keys: [],
            showAddOrg: false
        }
    },
    componentWillReceiveProps() {
        Utils.ajaxData({
            url: '/modules/common/action/ComboAction/queryDic.htm',
            method: 'get',
            data: {
                typeCode: "EVAKUATION_ORGANIZATION"
            },
            callback: (result) => {
                this.setState({orgData:result.data});
            }
        });

    },
    componentDidMount() {
        Utils.ajaxData({
            url: '/modules/common/action/ComboAction/queryDic.htm',
            method: 'get',
            data: {
                typeCode: "EVAKUATION_ORGANIZATION"
            },
            callback: (result) => {
            this.setState({orgData:result.data});
            }
        });

    },
    onSelectOrg(value) {
        this.selectVlaue = value;
    },
    getName(data) {
        var selectData = this.state.orgData;
        var name;
        selectData.forEach(item => {
            if (item.value == data) {
                name = item.text
                return
            }
        })
        return name;
    },
    render() {
        const { getFieldProps, getFieldValue } = this.props.form;
        getFieldProps('keys', {
            initialValue: [],
        });
        var canUpload = true;
        const uploadProps = {
            name: 'Filedata',
            action: '/modules/common/PubBizAttachmentAction/upload.htm',
            multiple: true,
            data: {
                data: {}
            },
            onChange(info) {
                if (info.file.status !== 'uploading') {
                    console.log(info.file, info.fileList);
                }
                if (info.file.status === 'done') {
                    message.success(`${info.file.name} 上传成功。`);
                    // me.refreshList();
                } else if (info.file.status === 'error') {
                    message.error(`${info.file.name} 上传失败。`);
                }
            },
            beforeUpload(file) {
                const isJPG = file.type === 'image/jpeg';
                if (!isJPG) {
                    message.error('只能上传 JPG 文件哦！');
                }
                return isJPG;
            },
        };
        return( 
            <div style={{  marginLeft: 25 ,marginTop:50}}  >
               <span>房本照片</span>
               <Upload {...uploadProps}> <a style={{  marginLeft: 25 ,marginTop:50}}>上传附件</a> </Upload>
               <a style={{  marginLeft: 25 ,marginTop:50}}>查看附件</a>
            </div >
        )
    }
});
RoomThisPhoto = createForm()(RoomThisPhoto);
export default RoomThisPhoto;