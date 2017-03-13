import React from 'react';
import Reflux from 'reflux';
import AppActions from '../../frame/actions/AppActions';
import MenuDataStore from '../../frame/stores/MenuDataStore';
import reqwest from 'reqwest';
export default React.createClass({
  mixins: [
    Reflux.connect(MenuDataStore, "menuData")
  ],
  getInitialState() {
    return {
      menuData: [],
      assetsData: {},
      investmentData: {}
    }
  },
  handleClick(tabName,tabId) { 
    AppActions.setTabActiveKey(tabName,tabId);
  },
  getMenuByData() {
    var me = this;
    var SubMenuNodes = [];
    if (this.state.menuData.length) {
      SubMenuNodes = this.state.menuData.map((item, key) => {
        let itemNoades = item.label != "查询统计管理" && item.children && item.children.map((child, i) => {
          if (!child.children) {
            return (<li onClick={this.handleClick.bind(null,child.label,child.scriptid)}>
                <i className={`iconfont ${child.iconCls} tool-icon`}></i>
                <p>{child.label}</p>
              </li>)
          } else {
            var childChild = child.children;
            let childrenNodes = childChild.map((son, j) => {

              return (<li onClick={this.handleClick.bind(null,son.label,son.scriptid)}>
                  <i className={`iconfont ${son.iconCls} tool-icon`}></i>
                  <p>{son.label}</p>
                </li>)
            });
            return childrenNodes;
          }
        });
        return itemNoades;
      });
    }
    return (<ul className="tool">{SubMenuNodes}</ul>);
  },
  componentDidMount() {
    /* var me = this;
     Utils.sendAjax({
       url: '/admin/integratedQuery/getWorkStationData',
       method: 'get',
       callback: (result) => {
         var data = result.data;
         me.setState({
           investmentData: data
         });
       }
     });*/

  },
  render() {
    var SubMenuNodes = this.getMenuByData();
    var assetsData = this.state.assetsData;
    var investmentData = this.state.investmentData;

    return (
      <div> 
          <div className="topnav">
            常用工具
            <i className="iconfont icon-gongjuxiang"></i>
          </div>
          {SubMenuNodes} 
      </div>
    )
  }
});