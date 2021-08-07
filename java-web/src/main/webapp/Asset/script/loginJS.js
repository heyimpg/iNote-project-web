$(document).ready(function ()
    {
        $(".alert-success").hide(3000);
        $(".alert-danger").hide(3000);
    }
)

//Check password Login
function myfun()
{
    var password = $("#password").val();
    var password_confirm =  $("#password_confirm").val();
    if (password  != password_confirm)
        $("#message_check").text("Không khớp với trường mật khẩu!");
    else
        $("#message_check").text("Hợp lệ");
}