/*
global
alertify: false
*/

/**
 * Create a new account.
 */
function signup() { // eslint-disable-line no-unused-vars
  if ($('#create-user-form').parsley().validate()) {
    $.ajax({
      type: 'POST',
      url: '/create_user',
      dataType: 'json',
      data: $('#create-user-form').serialize(),
      success: function(result) {
        if (result == 'email_exists') {
          const message = 'E-mail já cadastrado';
          alertify.notify(message, 'error', 10);
        } else if (result == 'mobile_exists') {
          const message = 'Celular já cadastrado';
          alertify.notify(message, 'error', 10);
        } else if(result == "register_success") {
          alertify.notify('Cadastro efetuado com sucesso', 'success', 10);
          //document.getElementById('login-button').click();
          setTimeout(function() {
            window.location = "/login"
        }, 5000);
        };
      },
    });
  }
}
