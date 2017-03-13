var Reflux = require('reflux');
var reqwest = require('reqwest');

var Actions = require('../Actions');

export default Reflux.createStore({
    listenables: [Actions],
    formData: {},
    onSetFormData(record) {
        this.update(record)
    },
    update(record) {
        this.trigger({
            formData: record
        });
    }
});