var App = function () {
    var _masterCheckbox;
    var _checkbox;

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

    return{
        init:function () {
            handlerInitCheckBox();
            handlerCheckboxAll();
        },

        getCheckbox: function () {
            return _checkbox;
        }
    }
}();

$(document).ready(function () {
    App.init();
});