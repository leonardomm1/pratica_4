
function xxxxx() { // eslint-disable-line no-unused-vars
  if ($('#create-user-form').parsley().validate()) {
    $.ajax({
      type: 'POST',
      url: '/create_user',
      dataType: 'json',
      data: $('#create-user-form').serialize(),
      success: function(result) {

        if (result == 'cadastro_atualizado') {
          const message = 'Seu cadastro foi atualizado!';
          alertify.notify(message, 'success', 10);
          setTimeout(function() {
            window.location = "/home/"
          }, 5000);
        };

      },
    });
  }
};



function check_cep() {

  var cep = $('#cep').val().replaceAll('-', '');
  if(cep.length != 8) {
    alertify.notify('O CEP deve ter 8 dígitos', 'error', 5);
    return;
  };

  var ceps = {
    '13020240': ['Rua Paulo Setúbal', 'Botafogo', 'Campinas', 'SP'],
    '13020000': ['Rua Gusmão de Andrade', 'Cambuí', 'Campinas', 'SP'],
  };

  result = ceps[cep];

  alertify.notify('CEP encontrado com sucesso', 'success', 5);

  $('#address').val(result[0]);
  $('#city').val(result[2]);
  $('#state').val(result[3]);

  $('input[name="address"]').val(result[0]);
  $('input[name="city"]').val(result[2]);
  $('input[name="state"]').val(result[3]);

  $('#address').prop('readonly', true);
  $('#city').prop('readonly', true);
  $('#state').prop('readonly', true);

};



function check_cep_index() {

  var cep = $('#cep').val().replaceAll('-', '');
  if(cep.length != 8) {
    //alertify.notify('O CEP deve ter 8 dígitos', 'error', 5);
    return;
  };

  var ceps = {
    '13020240': ['Rua Paulo Setúbal', 'Botafogo', 'Campinas', 'SP'],
    '13020000': ['Rua Gusmão de Andrade', 'Cambuí', 'Campinas', 'SP'],
  };

  result = ceps[cep];

  if(cep in ceps) {
    //alertify.alert('Que bom!', 'Nós atendemos a sua região :-)');
    /*alerty.alert('Nós atendemos a sua região :-)', {
      title: 'Que bom!', 
      time: 5000
    }, function(){})*/
    alerty.confirm(
      'Nós atendemos a sua região :-)',
      {title: 'Que bom!', cancelLabel: ' ', okLabel: 'OK'},
      function() {
        //alerty.toasts('this is ok callback', {place: 'top'})
      },
      function() {
        //alerty.toasts('this is cancel callback')
      }
    );
  } else {
    //alertify.alert('Que Pena!', 'Ainda não chegamos na sua região :(');
    /*alerty.alert('Ainda não chegamos na sua região :(', {
      title: 'Que Pena!', 
      time: 5000
    }, function(){})*/

    alerty.confirm(
      'Ainda não chegamos na sua região :(', 
      {title: 'Que pena!', cancelLabel: ' ', okLabel: 'OK'},
      function() {
        //alerty.toasts('this is ok callback', {place: 'top'})
      },
      function() {
        //alerty.toasts('this is cancel callback')
      }
    );

  }
  

  $('#address').val(result[0]);
  $('#city').val(result[2]);
  $('#state').val(result[3]);

  $('input[name="address"]').val(result[0]);
  $('input[name="city"]').val(result[2]);
  $('input[name="state"]').val(result[3]);

  $('#address').prop('readonly', true);
  $('#city').prop('readonly', true);
  $('#state').prop('readonly', true);

};


