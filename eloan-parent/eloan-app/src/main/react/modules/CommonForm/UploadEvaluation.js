import React from 'react';
import antd from 'antd';   
var Select = antd.Select;
var Option = Select.Option;
var Modal = antd.Modal;    
var reqwest = require('reqwest'); 
var Attachment = require('./Attachment');  
const NavLine = require("../utils/NavLine");
export default React.createClass({
   getInitialState() { 
     return {
         fileList:[],
         formData:{}
       };
   },  
  changeValue(e) { 
      var newValue = this.state.formData;
      var name = e.target.name;
      newValue[name] = e.target.value;
      this.setState({formData:newValue});
  }, 
  selectChange(name,value) { 
    var newValue = this.state.formData; 
      newValue[name] = value; 
    this.setState({formData:newValue});
  },
  navLineData:{
      "入库信息":"#s1",
  },
   _isInView(el,target) {
    var container = target;
    var winH = container.clientHeight,
          scrollTop = container.scrollTop,
          scrollBottom = scrollTop + winH,
          elTop = el.offsetTop,
          elBottom = elTop + el.offsetHeight - 100;

        return (elTop < scrollBottom) && (elBottom > scrollTop); 
  }, 
  _handleSpy(e){ 
    var navLineData =  this.navLineData;
    var items = Object.keys(navLineData).map((key,i)=>{
      return navLineData[key].substring(1);
    });
    var targetItems = items.map(function (item) {
      return document.getElementById(item);
    });
    var hasInViewAlready = false;
    targetItems.forEach((el,index)=>{
      if(!hasInViewAlready){
          if(this._isInView(el,e.target)){
          this.refs.NavLine.handleClickItem('#'+items[index]);
          hasInViewAlready = true;
        }
      } 
    });
  },
  handleOk() {
    this.props.handleOk()   
  },
  handleCancel() {
    this.props.hideAddModal();
  },
  render() {    
    var formData= this.state.formData;
    var props = this.props; 
    if(!props.selectRecord)
      return null
    var selectRecord = props.selectRecord;
  
    return(
      <div style={{position: "relative"}}>
        <div className="navLine-wrap" onScroll={this._handleSpy} style={{ marginTop: "20px" }}>>
          <div className="col-20 navLine-wrap-left" >
              <div id="s1" >
                <h2>入库信息</h2>
                <Attachment selectRecord={selectRecord} type ={"Storage_Pic"} canEdit={props.canEdit}/>
              </div>  
          </div>
          <div className="navLine-wrap-right" >
            <NavLine navLineData={this.navLineData} activeItem="#s1" ref="NavLine"/>
          </div>
         </div>
      </div>
    )
  }
});