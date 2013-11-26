var Controller = function() {
    
    
};

Controller.prototype.isWideScreen = function() {
    return window.innerWidth > 1600;
};

Controller.prototype.getForm = function(formId) {
    var form = document.getElementById(formId);
};

Controller.prototype.validateForm = function(formId) {
    var form = document.getElementById(formId);
    
    return true;
};

Controller.prototype.getCount = function() {
    return 2;
}
