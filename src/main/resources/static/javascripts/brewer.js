var Brewer = Brewer || {};  //Verifica se já foi criado o Brewer, caso contrário cria um novo Brewer;

Brewer.MaskMoney = (function () {
	
	//Construtor
	function MaskMoney() {
		this.decimal = $('.js-decimal');
		this.plain = $('.js-plain');
	}
	
	MaskMoney.prototype.enable = function() {
		this.decimal.maskMoney({decimal: ',', thousands: '.'});
		this.plain.maskMoney({ precision: 0, thousands: '.' });
	}
	
	return MaskMoney;
}());

$(function() {
	var maskMoney = new Brewer.MaskMoney();
	maskMoney.enable();
});