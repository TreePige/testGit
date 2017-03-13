import React from 'react';
import { Upload,  Modal } from 'antd';

var reqwest = require('reqwest');
export default React.createClass({

  getInitialState() {
    return {
      fileList: [],
      formData: {}
    };
  },
  queryFormData(selectRecord) {
    var me = this;
    if (!selectRecord)
      return
    var type = this.props.type;
    var projectId = selectRecord.projectId;
    reqwest({
      url: '/modules/common/PubAttachmentAction/query.htm'
      , method: 'get'
      , data: {
        search: JSON.stringify({
          "projectId": projectId,
          "btype": type
        })
      }
      , type: 'json'
      , success: (result) => {
        var data = result.data;
        var length = data.length;
        var fileList = [];
        for (let i = 0; i < length; i++) {
          let row = data[i];
          let file = {};
          file.url = row.uri;
          file.id = row.id;
          fileList.push(file);
        }
        me.setState({
          fileList: fileList
        });
      }
    });

  },
  componentWillReceiveProps(nextProps) {
    if (this.props.selectRecord && this.props.selectRecord != nextProps.selectRecord) {
      this.queryFormData(nextProps.selectRecord);
    }
  },
  componentDidMount() {
    if (this.props.selectRecord)
      this.queryFormData(this.props.selectRecord);
  },
  handleChange(info) {
    console.log(info)
    if (info.file.status === 'done') {
      console.log("handleChange", info);
      this.queryFormData(this.props.selectRecord);
    }
  }, 
  Delete(id) {
    var me = this;
    reqwest({
      url: '/modules/common/deletes3.htm',
      method: 'post',
      data: {
        ids: id
      },
      type: 'json',
      success: (result) => {
        if (result.success) {
          var fileList = me.state.fileList;
          fileList = fileList.filter((file) => {
            if (file.id != id)
              return true;
          });
          me.setState({
            fileList
          });
        }
        else {
          Modal.error({
            title: result.msg
          });
        }
      }
    });
  },
  downLoad() {
    var me = this;
    var state = this.state;
    var formData = state.formData;
    var selectRecord = this.props.selectRecord;
    var id = selectRecord.projectId;
    var type = this.props.type;
    reqwest({
      url: '/modules/common/Attachment/downloadZip.htm?',
      data: {
        "where.attach.project_id": id,
        fileExistCheck: true,
        "where.attach.rd_btype": type
      },
      method: 'POST',
      type: 'json',
      success: (result) => {
        var success = result.success;
        var msg = <span dangerouslySetInnerHTML={{ __html: result.msg }}></span>;
        if (!success) {
          Modal.error({
            title: msg
          });
          return;
        }
        window.location.href = '/modules/common/Attachment/downloadZip.htm?where.attach.project_id=' + id + "&fileExistCheck=false&where.attach.rd_btype=" + type;
      }
    });
  },
  render() {
    var formData = this.state.formData;
    var props = this.props;
    if (!props.selectRecord)
      return null
    var selectRecord = props.selectRecord;
    var type = this.props.type;
    var images = "";
    images = this.state.fileList.map((file, index) => {
      return <div className="img" key ={index} >
        <a href={file.url} target="view_window"><img src={file.url} /></a>
        {props.canEdit ? <button key="delete" type="button" className="ant-btn" onClick={this.Delete.bind(this, file.id) }><i className="anticon anticon-delete"></i>删除</button> : null}
      </div>
    });
    var postDate = {
      rd_operatorid: 1,
      processInstanceId: selectRecord.processInstanceId,
      project_id: selectRecord.projectId,
      rd_btype: type
    };
    var uploadProps = {
       name:"Filedata",
       multiple:true,
       accept:"image/*",
       data:{
         data:JSON.stringify(postDate)
       },
       action: '/modules/common/upload.htm;jsessionid=undefined',
       onChange: this.handleChange 
     }; 
    return (<div className="uploadFile">
      {images}
      <div className="img">
        <div className="uploadBg uploadPlus" >
          {props.canEdit ? <Upload {...props}> 
            <div className="ant-upload-text"></div>
          </Upload> : null}
        </div>
        <Modal visible={this.state.priviewVisible} footer={null} onCancel={this.handleCancel}>
          <img alt="example" src={this.state.priviewImage} />
        </Modal>
        <button key="downLoad" type="button" className="ant-btn" onClick={this.downLoad} style={{ marginTop: '5px' }}><i className="anticon anticon-download-line"></i>打包下载</button>
      </div>
    </div>

    )
  }
}); 
