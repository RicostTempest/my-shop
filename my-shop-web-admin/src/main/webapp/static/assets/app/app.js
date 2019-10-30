var App = function () {

    //ickeck
    var _masterCheckbox;
    var _checkbox;

    //用于存放ID的数组
    var _idArray;

    /**
     * 私有方法初始化ICheck
     */
    var handlerInitCheckBox = function () {
        // 激活 iCheck
        $('input[type="checkbox"].minimal, input[type="radio"].minimal').iCheck({
            checkboxClass: 'icheckbox_minimal-blue',
            radioClass   : 'iradio_minimal-blue'
        });

        //获取控制端Checkbox
        _masterCheckbox = $('input[type="checkbox"].minimal.icheck_master');
        //获取全部Checkbox集合
        _checkbox = $('input[type="checkbox"].minimal');
    };

    /**
     * Checkbox全选
     */
    var handlerCheckboxAll = function () {
        _masterCheckbox.on("ifClicked", function (e) {
            // 当前状态已选中，点击后应取消选择
            if (e.target.checked) {
                _checkbox.iCheck("uncheck");
            }

            // 当前状态未选中，点击后应全选
            else {
                _checkbox.iCheck("check");
            }
        });
    };

    var handlerDeleteMulti = function (url) {
        _idArray = new Array();

        //push the elected id to array
        _checkbox.each(function () {
            var _id = $(this).attr("id");
            if(_id != null && _id != "undefine" && $(this).is(":checked")){
                _idArray.push(_id);
            }
        });

        if(_idArray.length === 0){
            $("#model-message").html("没选择任何数据项");
        }else{
            $("#model-message").html("确定要删除吗？");
        }

        //显示弹窗
        $("#modal-default").modal("show");

        //绑定确定事件
        $("#checkboxOk").bind("click",function () {
            del();
        });

        /**
         * 当前私有函数的私有函数
         * 只在父函数中能被调用
         */
        function del() {
            $("#modal-default").modal("hide");

            if(_idArray.length == 0){
                //...
            }
            else{
                setTimeout(function () {
                    $.ajax({
                        "url":url,
                        "type":"POST",
                        "data":{"ids":_idArray.toString()},
                        "dataType":"JSON",
                        "success":function (data) {
                            if(data.status == 200){
                                console.log(data)
                                window.location.reload();
                            }
                            //删除失败
                            else{
                                $("#checkboxOk").unbind("click");
                                //强制更改绑定事件
                                $("#checkboxOk").bind("click",function () {
                                    $("#modal-default").modal("hide");
                                });
                                $("#modal-default").modal("show");
                                $("#model-message").html(data.message);
                            }
                        }
                    });
                },500);

            }
        }
    };


    return{
        init:function () {
            handlerInitCheckBox();
            handlerCheckboxAll();
        },

        getCheckbox: function () {
            return _checkbox;
        },

        deleteMulti:function (url) {
            return handlerDeleteMulti(url)
        }
    }
}();

$(document).ready(function () {
    App.init();
});