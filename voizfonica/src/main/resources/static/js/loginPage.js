function showPassword() {
    var x = document.getElementById("password");
    if (x.type === "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }
}

function showConfirmPassword() {
    var x = document.getElementById("confirm_password");
    if (x.type === "password") {
        x.type = "text";
    } else {
        x.type = "password";
    }
}


var check = function() {
    if (document.getElementById('password').value ==
        document.getElementById('confirm_password').value) {
        document.getElementById('showNotMatch').style.color = 'green';
        document.getElementById('showNotMatch').innerHTML = 'Passwords match';
    } else {
        document.getElementById('showNotMatch').style.color = 'red';
        document.getElementById('showNotMatch').innerHTML = 'Passwords do not match';
    }
}
