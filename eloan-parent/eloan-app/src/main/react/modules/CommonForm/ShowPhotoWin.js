import React from 'react';
import antd from 'antd';
import {
    Button, 
    Modal, 
    message
} from 'antd';
var reqwest = require('reqwest');
export default React.createClass({
    getInitialState() {
        return { visible: false };
    },
    handleOk() {
        console.log('点击了确定');
        this.setState({
            visible: false,
        });
    },
    handleCancel(e) {
        this.props.hideModal();
    },
    deletePic(id){
        var selectRecord = this.props.selectRecord;
        var btype = this.props.btype;
        Utils.ajaxData({
            url: '/modules/common/PubAttachmentAction/deletes.htm',
            data: {
                ids:JSON.stringify([id])
            },
            callback: (result) => {
                message.info(result.msg);
                this.props.showModal();
            }
        });
    },
    demoImageItems(){
        var picData =this.props.picData;
        if(picData.length){
            return picData.map((item,index)=>{
               return (<div key={index} className="img">
                   <a href={item.filePath} target="_blank"><img src={item.filePath} style={{width:150,height:150}} /></a>
                    {this.props.canEdit?<button key="back" className="ant-btn" onClick={this.deletePic.bind(null,item.id)}>删除</button>:null}
                   </div>
               ) 
            });
        }
        else return <div>暂未上传图片</div>
    },
    render() {
       var  modalBtns = [
                <button key="back" className="ant-btn" onClick={this.handleCancel}>关闭</button>
            ];
        return (
            <Modal title={this.props.title} visible={this.props.visible}  width="900" 
                onOk={this.handleOk} onCancel={this.handleCancel}  footer={modalBtns}
                >
                <div className="uploadFile">
                {this.demoImageItems()}
                </div>
            </Modal>
        );
    },
});