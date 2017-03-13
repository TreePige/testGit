/* 房产评估*/
import React from 'react';
import {
  Tabs,
} from 'antd';
const TabPane = Tabs.TabPane;
import Pending from './tabs/Pending/index';
import Processed from './tabs/Processed/index';
import List from './tabs/Pending/List';
import SeachForm from './tabs/Pending/SeachForm';
const Index = React.createClass({
  getInitialState() {
    return {
      activekey: "1",
      params: {}
    }
  },
  handleTabClick(key) {
    this.setState({
      activekey: key
    })
  },
  passParams(params) {
    this.setState({
      params: params
    });
  },
  render() {
    var roleId = window.roleId;
    var pro = (
      <Tabs defaultActiveKey="1" onTabClick={this.handleTabClick} activeKey={this.state.activekey} animation={null}>
        <TabPane tab="待处理" key="1">
          <Pending />
        </TabPane>
        <TabPane tab="已处理" key="2" >
          <Processed />
        </TabPane>
      </Tabs>
    )
    if (roleId == "declarationStaff" || roleId == "customerServiceStaffB") {
      pro = (
        <div>
          <div className="block-panel">
            <SeachForm passParams={this.passParams}/>
          </div>
          <List params={this.state.params}/>
        </div>
      )
    }
    return (
      <div>
        { pro }
      </div>
    )
  }

});

export default Index;
