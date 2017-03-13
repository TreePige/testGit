//新增机构评估
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
var UploadPhoto = require("./UploadPhoto");
const createForm = Form.create;
const FormItem = Form.Item;
const Option = Select.Option;
var NewEvaOrg = React.createClass({
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
                this.setState({ orgData: result.data });
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
                this.setState({ orgData: result.data });
            }
        });

    },
    remove(k) {
        const { form } = this.props;
        var id = form.getFieldValue(`${k}id`);
        if (id) {
            //删除后台数据
            Utils.ajaxData({
                url: '/modules/instance/HousAssessmentAgenciesAction/deleteById.htm',
                method: 'get',
                data: {
                    id:id
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
        if (!this.selectVlaue) {
            Modal.info({
                title: '请选择评估机构'
            });
            return
        }
        if (keys.indexOf(this.selectVlaue) > -1) {
            Modal.info({
                title: '已有' + this.getName(this.selectVlaue) + '评估'
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
        var props = this.props;
        var canEdit = this.props.canEdit
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
            return (<tr key={k}>
                <td key={`k1`}>
                    {name}
                </td>
                <td key={`k2`}>
                    <FormItem >
                        <Input {...getFieldProps(`${k}assessedValue`, {
                            rules: [{
                                required: true, message: "  "
                            }]
                        }) } disabled={!this.props.canEdit}  type="text"/>
                    </FormItem>
                </td>
                <td key={`k3`}>
                    {canEdit ? <a onClick={() => this.remove(k) }>删除</a> : null}
                    {canUpload ? <UploadPhoto  title="" btype={`evaOrg${k}`} canEdit={this.props.canEdit} selectRecord={this.props.record}/> : null}
                </td>
                <td key={`k4`} style={{ display: "none" }}>
                    <Input {...getFieldProps(`${k}id`) } />
                </td>
            </tr >
            );
        });
        var button = (
            <div>
                <Select onChange={this.onSelectOrg} style={{ width: '200px', marginLeft: 25 }}>
                    {
                        this.state.orgData.map(item => {
                            return <Option key={item.value} value={item.value}>{item.text}</Option>
                        })
                    }
                </Select>
                <Button onClick={this.add} style={{ marginLeft: 8 }}>新增机构评分值</Button>
            </div>
        );
        if (this.props.record && this.props.record.processStateCode == "usertask-businessExchange") {
            button = ""
        }
        return <Form  form={this.props.form}>

            {canEdit ? button : null}

            <table style={{ width: 950, marginLeft: 25, marginBottom: 15 }} className="addTable" ref="addTable" >
                <tbody>
                    <tr>
                        <td width="30%">
                            评估机构
                        </td>
                        <td>
                            分值
                        </td>
                        <td >
                            操作
                        </td>
                    </tr>
                    {getFieldValue('keys').length ? formItems : null}
                </tbody>
            </table >
        </Form>
    }
});
NewEvaOrg = createForm()(NewEvaOrg);
export default NewEvaOrg;