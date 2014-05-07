$(document).ready(function(){
	var height = $(window).height();
	$('#conteudo').css('height',(height-95-3-5)+'px');
	$('#bordaLateralPagina').css('height',(height-95)+'px');
});

function atualizaValorTotalTransporte(){
	alert("xegou na funcao");
//	document.getElementById('formIncFuncionario:dataTableIncTransporte:hiddenAtualizaTabela').click();
	
//	var elementos = document.getElementById("formIncFuncionario:dataTableIncTransporte_data");
//	var qtd = elementos.childElementCount;
	
//	alert("qtd de linhas:" +qtd);
//	
//	for(var i= 0; i< qtd ; i++ ){
//		var linha = elementos.children[i];
//		
//		alert("peguei a linha" + i);
//		
////		if(linha.attributes[0].value == "true"){
//			
////			if(linha.children[3].children[0].children[0].style.display == "none"){
////				linha.attributtes[0].value = "false";
////				alert("setou false em area-selected");
////				linha.children[0].children[0].children[0].children[0].checked = "false";
////				alert("setou false no checked do checkbox daquela linha");
////			}
////		}
//	}
//	alert("terminou");
	
//	alert('renderizou');
}



function validarCPF(Objcpf){
	var cpf = Objcpf.value;
	exp = /\.|\-/g;
	cpf = cpf.toString().replace( exp, "" ); 
	var digitoDigitado = eval(cpf.charAt(9)+cpf.charAt(10));
	var soma1=0, soma2=0;
	var vlr =11;
	
	for(var i=0;i<9;i++){
		soma1+=eval(cpf.charAt(i)*(vlr-1));
		soma2+=eval(cpf.charAt(i)*vlr);
		vlr--;
	}	
	soma1 = (((soma1*10)%11)==10 ? 0:((soma1*10)%11));
	soma2=(((soma2+(2*soma1))*10)%11);
	
	var digitoGerado=(soma1*10)+soma2;
	if(digitoGerado!=digitoDigitado){
		alert('CPF Invalido!');		
	}	
}


function validarCNPJ(ObjCnpj){
	var cnpj = ObjCnpj.value;
	var valida = new Array(6,5,4,3,2,9,8,7,6,5,4,3,2);
	var dig1= new Number;
	var dig2= new Number;
	
	exp = /\.|\-|\//g;
	cnpj = cnpj.toString().replace( exp, "" ); 
	var digito = new Number(eval(cnpj.charAt(12)+cnpj.charAt(13)));
		
	for(var i = 0; i<valida.length; i++){
		dig1 += (i>0? (cnpj.charAt(i-1)*valida[i]):0);	
		dig2 += cnpj.charAt(i)*valida[i];	
	}
	dig1 = (((dig1%11)<2)? 0:(11-(dig1%11)));
	dig2 = (((dig2%11)<2)? 0:(11-(dig2%11)));
	
	if(((dig1*10)+dig2) != digito){
		alert('CNPJ Invalido!');
	}	
		
}

function validaCampoDecimal(objDecimal){
	var valor = objDecimal.value;
	var er = RegExp('^([0-9]*),([0-9]{2})$');
	if(!(er.test(valor))){
		alert("O campo valor está incorreto.");
	}
}






function validaCampoNumerico(event){
	//apenas numeros
	if((event.keyCode >= 48 && event.keyCode <= 57) || (event.keyCode >= 48 && event.keyCode <= 57)){
	  return true;
	}else{
	  event.keyCode = 0;
	  event.returnValue=false;
	}
	
}

function reterCopiarColar(event){
	//ctrl+v
	if(event.ctrlKey){
		if(event.shiftKey){
			return true;
		}else if(event.keyCode==86){
			event.keyCode=0; 
			event.returnValue=false;
		}
	}
}




