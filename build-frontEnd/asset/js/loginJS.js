function myfun()
{
    var password = $("#password").val();
                var password_confirm =  $("#password_confirm").val();
                if (password  != password_confirm)
                    $("#message_check").text("Không khớp với trường mật khẩu!");
                // alert("ok");
                else
                     $("#message_check").text("ok");
}