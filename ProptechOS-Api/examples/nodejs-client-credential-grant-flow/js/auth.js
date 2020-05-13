var $cardDiv = $('#card-div');
$cardDiv.hide();

function signIn() {
  fetch('/login').then(function(response) {
    // The response is a Response instance.
    // You parse the data into a useable format using `.json()`
    return response.json();
  }).then(function(data) {
    $cardDiv.show();
    $('#token-type').html('Token type: ' + data.token_type);
    $('#token-expires').html('Token expires in: ' + data.expires_in);
    $('#token-div').html(data.access_token);
  });
}