var Reflux = require('reflux');
var reqwest = require('reqwest');

var Actions = require('../Actions');

export default Reflux.createStore({
    listenables: [Actions],
    formData: {},
    onSetFormData(record) {
        if(record&&record.depositAmount){
            record.assetsIsInsured=String(record.assetsIsInsured);
        }
        if(record&&record.depositAmount){
            record.depositAmount=String(record.depositAmount);
        }
        if(record&&record.accountBank){
            record.accountBank=String(record.accountBank);
        }
        
        
        this.update(record)
    },
    update(record) {
        this.trigger({
            formData: record
        });
    }
});