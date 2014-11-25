function StatusBarColor () {
	
};

/*
* set color of statusbar
*/
StatusBarColor.prototype.setColor = function (red, green, blue) {
	cordova.exec(null, null, 'StatusBarColor', 'setColor', [red, green, blue]);
};

module.exports = new StatusBarColor();