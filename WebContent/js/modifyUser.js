//验证表单
function yanzheng(){
    var yuanpassword = document.getElementById("yuanpassword").value;
    var newpassword = document.getElementById("newpassword").value;
    var password = document.getElementById("password").value;
    if(yuanpassword == "" || newpassword == "" || password == ""){
        alert("密码不能为空");
        return false;
    }else if(newpassword != password){
        alert("两次密码不相等");
        return false;
    }
    return true;
}
//验证原密码是否正确
function yuanPasswordIsOk(error){
    if(error == "oloPasswordError"){
        alert("原密码错误！修改失败");
    }
    else if(error == "newPassWord=oldPassWord"){
        alert("新密码和原密码相同！修改失败");
    }
    else if(error == "systemError"){
        alert("系统错误！修改失败");
    }
}
