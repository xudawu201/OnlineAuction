//验证表单的js
function yanzheng(){
    var kind_name = document.getElementById("kind_name").value;
    if(kind_name == ""){
        alert("类型名称不能为空");
        return false;
    }
    alert("添加类型成功！");
    return true;
}