//新增机构查询
import React from 'react';
import {
    Button,
    Form,
    Input,
    Modal,
    Select,
    Upload,
    message,
    Checkbox
} from 'antd';
var UploadPhoto = require("./UploadPhoto");
const createForm = Form.create;
const Option = Select.Option;
var Org = [];
Utils.ajaxData({
    url: '/modules/common/action/ComboAction/queryDic.htm',
    method: 'get',
    data: {
        typeCode: "QUERY_INSTITUTION"
    },
    type: 'json',
    callback: (result) => {
        Org = result.data;
    }
});
var NewQueryOrg = React.createClass({
    getInitialState() {
        return {
            orgData: [],
            keys: [],
            showAddOrg: false
        }
    },
    remove(k) {
        const { form } = this.props;
        var id = form.getFieldValue(`${k}id`);
        if (id) {
            Utils.ajaxData({
                url: '/modules/instance/HousEnquiryInstitutionAction/deleteById.htm',
                method: 'get',
                data: {
                    id: id
                },
                callback: (result) => {
                    if (result.code == 200) {
                        Modal.info({
                            title: '删除成功'
                        });
                    }
                }
            });
        }
        let keys = form.getFieldValue('keys');
        keys = keys.filter((key) => {
            return key !== k;
        });
        form.setFieldsValue({
            keys,
        });
    },
    add() {
        const { form } = this.props;
        let keys = form.getFieldValue('keys');
        if (keys.indexOf(this.selectVlaue) > -1) {
            Modal.info({
                title: '已有' + this.getName(this.selectVlaue) + '查询'
            });
            return
        }
        else {
            keys = keys.concat(this.selectVlaue);
            form.setFieldsValue({
                keys
            });
        }
    },
    onSelectOrg(value) {
        this.selectVlaue = value;
    },
    getName(data) {
        var selectData = Org;
        var name;
        selectData.forEach(item => {
            if (item.value == data) {
                name = item.text
                return
            }
        })
        return name;
    },
    getOrg() {
        return Org.map((item, index) => {
            return <Option key={item.value} >{item.text}</Option>
        })
    },
    handleOk() {
        var data = this.props.form.getFieldsValue();
        var formData = []
        data.keys.forEach(item => {
            let obj = {};
            //是建委
            if (item == 1) {
                obj.id = item;
                obj.remark = data[item + "remark"];
                obj.mortgage = data[item + "mortgage"];
                obj.seizure = data[item + "seizure"];
                obj.businessOccupancy = data[item + "businessOccupancy"];
                obj.netSignedOccupancy = data[item + "netSignedOccupancy"];
                obj.hochstbetragshypothek = data[item + "hochstbetragshypothek"];
            }
            //是安融
            else if (item == 4) {
                obj.id = item;
                obj.remark = data[item + "remark"];
                obj.legalProcessPerformed = data[item + "legalProcessPerformed"];
                obj.affiliate = data[item + "affiliate"];
            }
            else {
                obj.id = item;
                obj.remark = data[item + "remark"];
                obj.legalProcessPerformed = data[item + "legalProcessPerformed"];
            }
            formData.push(obj)
        });
        console.log(formData)
    },
    render() {
        var props = this.props;
        var canEdit = this.props.canEdit
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
        const formItems = getFieldValue('keys').map((k) => {

            let name = this.getName(k);
            // console.log(k)
            if (k == 1) {
                return (<div key={k} style={{ marginTop: 15, marginLeft: 25 }}>
                    <Input {...getFieldProps(`${k}id`) } type='hidden'/>
                    {name}
                    {canEdit ? <a onClick={() => this.remove(k) } style={{ marginLeft: 25 }}>删除</a> : null}
                    <UploadPhoto title="" btype={`queryOrg${k}`} canEdit={this.props.canEdit} selectRecord={this.props.record}/>
                    <Checkbox {...getFieldProps(`${k}mortgage`, { valuePropName: 'checked' }) } disabled={!props.canEdit} style={{ marginRight: 8 }}>有无抵押</Checkbox>
                    <Checkbox {...getFieldProps(`${k}seizure`, { valuePropName: 'checked' }) } disabled={!props.canEdit} style={{ marginRight: 8 }}>有无查封</Checkbox>
                    <Checkbox {...getFieldProps(`${k}businessOccupancy`, { valuePropName: 'checked' }) } disabled={!props.canEdit} style={{ marginRight: 8 }}>有无业务占用</Checkbox>
                    <Checkbox {...getFieldProps(`${k}netSignedOccupancy`, { valuePropName: 'checked' }) } disabled={!props.canEdit} style={{ marginRight: 8 }}>网签占用</Checkbox>
                    <Checkbox {...getFieldProps(`${k}hochstbetragshypothek`, { valuePropName: 'checked' }) } disabled={!props.canEdit} style={{ marginRight: 8 }}>是否为最高额抵押</Checkbox>
                    备注：<Input {...getFieldProps(`${k}remark`) } disabled={!props.canEdit} style={{ width: 200 }}/>
                </div >
                );
            }
            else if (k == 4) {
                return (<div key={k} style={{ marginTop: 15, marginLeft: 25 }}>
                    <Input {...getFieldProps(`${k}id`) }  type='hidden'/>
                    {name}
                    {canEdit ? <a onClick={() => this.remove(k) } style={{ marginLeft: 25 }}>删除</a> : null}
                    <UploadPhoto title="" btype={`queryOrg${k}`} canEdit={this.props.canEdit} selectRecord={this.props.record}/>
                    <Checkbox {...getFieldProps(`${k}legalProcessPerformed`, { valuePropName: 'checked' }) } disabled={!props.canEdit} style={{ marginRight: 8 }}>是否有进入法律程序的被执行信息</Checkbox>
                    <Checkbox {...getFieldProps(`${k}affiliate`, { valuePropName: 'checked' }) } disabled={!props.canEdit} style={{ marginRight: 8 }}>是否有关联企业</Checkbox>
                    备注：<Input {...getFieldProps(`${k}remark`, { initialValue: '' }) } disabled={!props.canEdit} style={{ width: 200 }}/>
                </div >
                );
            }
            else {
                return (<div key={k} style={{ marginTop: 15, marginLeft: 25 }}>
                    <Input {...getFieldProps(`${k}id`) }  type='hidden'/>
                    {name}
                    {canEdit ? <a onClick={() => this.remove(k) } style={{ marginLeft: 25 }}>删除</a> : null}
                    <UploadPhoto title="" btype={`queryOrg${k}`} canEdit={this.props.canEdit} selectRecord={this.props.record}/>

                    <Checkbox {...getFieldProps(`${k}legalProcessPerformed`, { valuePropName: 'checked' }) } disabled={!props.canEdit} style={{ marginRight: 8 }}>是否有进入法律程序的被执行信息</Checkbox>
                    备注：<Input {...getFieldProps(`${k}remark`, { initialValue: '' }) } disabled={!props.canEdit} style={{ width: 200 }}/>
                </div >
                );
            }

        });
        var button = [
            <Select onChange={this.onSelectOrg} style={{ width: '200px', marginLeft: 25 }}>
                {this.getOrg() }
            </Select>,
            <Button onClick={this.add} style={{ marginLeft: 8 }}>新增查询</Button>
        ]
        return <Form  form={this.props.form}>
            {canEdit ? button : <p style={{ lineHeight: "50px", marginLeft: "20px" }}>暂无记录查询</p>}

            {getFieldValue('keys').length ? formItems : null}
        </Form>
    }
});
NewQueryOrg = createForm()(NewQueryOrg);
export default NewQueryOrg;