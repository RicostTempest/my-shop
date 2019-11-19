var App = function () {

    //ickeck
    var _masterCheckbox;
    var _checkbox;

    //用于存放ID的数组
    var _idArray;

    //默认的DropZone对象
    var defaultDropzoneOpts = {
        url: "",
        paramName:"dropFile",  //后台接受的参数名称规定为 dropFile 在属性 MultipartFile dropFile中匹配
        maxFiles: 1,// 一次性上传的文件数量上限
        maxFilesize: 2, // 文件大小，单位：MB
        acceptedFiles: ".jpg,.gif,.png,.jpeg", // 上传的类型
        addRemoveLinks: true,
        parallelUploads: 1,// 一次上传的文件数量
        dictDefaultMessage: '拖动文件至此或者点击上传',
        dictMaxFilesExceeded: "您最多只能上传" + this.maxFiles + "个文件！",
        dictResponseError: '文件上传失败!',
        dictInvalidFileType: "文件类型只能是*.jpg,*.gif,*.png,*.jpeg。",
        dictFallbackMessage: "浏览器不受支持",
        dictFileTooBig: "文件过大上传文件最大支持.",
        dictRemoveLinks: "删除",
        dictCancelUpload: "取消",
    };

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

    /**
     * 实现删除操作
     * @param url 调用的控制器方法路径
     */
    var handlerDeleteData = function (url) {
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
                        //解除事件绑定
                        $("#checkboxOk").unbind("click");
                        if(data.status == 200){
                            $("#checkboxOk").bind("click",function () {
                                window.location.reload();
                            });
                        }
                        //删除失败
                        else{
                            //强制更改绑定事件
                            $("#checkboxOk").bind("click",function () {
                                $("#modal-default").modal("hide");
                            });
                        }
                        $("#modal-default").modal("show");
                        $("#model-message").html(data.message);
                    }
                });
            },500);

        }
    };

    /**
     * 单项删除
     * @param url
     * @param id
     * @param msg
     */
    var handlerDeleteSignel = function (url, id, msg) {
        //可选参数的设置，防止报错
        if (!msg == null){
            msg = null;
        }

        _idArray = new Array();
        _idArray.push(id);

        $("#model-message").html(msg == null ? "确定删除数据吗？" : msg);
        //点击删除按钮时弹框
        $("#modal-default").modal("show");

        //绑定删除事件
        $("#checkboxOk").bind("click",function () {
            handlerDeleteData(url);
        });
    };

    /**
     * 多项删除
     * @param url
     */
    var handlerDeleteMulti = function (url) {
        _idArray = new Array();

        //push the elected id to array
        _checkbox.each(function () {
            var _id = $(this).attr("id");
            if(_id != null && _id != "undefine" && $(this).is(":checked")){
                _idArray.push(_id);
            }
        });
        //判断用户是否选择了数据项
        if(_idArray.length === 0){
            $("#model-message").html("没选择任何数据项");
        }else{
            $("#model-message").html("确定要删除吗？");
        }

        //点击删除按钮时弹框
        $("#modal-default").modal("show");

        //为确定键绑定删除方法
        $("#checkboxOk").bind("click",function () {
            handlerDeleteData(url);
        });

    };

    /**
     * 初始化DataTables
     */
    var handlerInitDataTables = function (url, columns) {
        var _dataTable = $("#dataTable").DataTable({
            "info":true,
            "lengthChange":false,
            "ordering":false,
            "processing":true,
            "searching":false,
            "serverSide":true,
            "deferRender":true,
            "ajax":{
                "url":url
            },
            "columns":columns,
            "language": {
                "sProcessing": "处理中...",
                "sLengthMenu": "显示 _MENU_ 项结果",
                "sZeroRecords": "没有匹配结果",
                "sInfo": "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
                "sInfoEmpty": "显示第 0 至 0 项结果，共 0 项",
                "sInfoFiltered": "(由 _MAX_ 项结果过滤)",
                "sInfoPostFix": "",
                "sSearch": "搜索:",
                "sUrl": "",
                "sEmptyTable": "表中数据为空",
                "sLoadingRecords": "载入中...",
                "sInfoThousands": ",",
                "oPaginate": {
                    "sFirst": "首页",
                    "sPrevious": "上页",
                    "sNext": "下页",
                    "sLast": "末页"
                },
                "oAria": {
                    "sSortAscending": ": 以升序排列此列",
                    "sSortDescending": ": 以降序排列此列"
                }
            },
            "drawCallback": function( settings ) {
                handlerInitCheckBox();
                handlerCheckboxAll();
            }
        });
        return _dataTable;
    };

    /**
     * 初始化zTree
     * @param url
     * @param autoParam
     * @param callback
     */
    var handlerInitZTree = function (url,autoParam,callback) {
        var setting = {
            view:{
                selectedMulti:false
            },
            async: {
                enable: true,
                url:url,
                autoParam:autoParam
            }
        };
        $.fn.zTree.init($("#myTree"), setting);
        $("#checkboxOk").bind("click", function () {
            var zTree = $.fn.zTree.getZTreeObj("myTree");
            var nodes = zTree.getSelectedNodes();

            if(nodes.length == 0){
                alert("请选择一个结点");
            }
            else{
                callback(nodes);
            }
        })
    };
    
    var handlerInitDropzone = function (opts) {
        //关闭自动发现功能
        Dropzone.autoDiscover = false;

        //使defaultDropzoneOpts继承来自opts的内部属性
        $.extend(defaultDropzoneOpts, opts);

        var myDropzone = new Dropzone(defaultDropzoneOpts.id, defaultDropzoneOpts);
    };

    /**
     * 查看详情，通过Ajax请求html的方式进行装载，将需要显示的HTML代码加入弹框中
     * @param url
     */
    var handlerShowDetail = function (url) {
        $.ajax({
            url:url,
            type:"get",
            dataType:"html",
            success:function (data) {
                $("#model-detail-body").html(data);
                $("#modal-detail").modal("show");
            }
        });
    };


    return{
        init:function () {
            handlerInitCheckBox();
            handlerCheckboxAll();
        },
        deleteSignel:function(url,id,msg){
            return handlerDeleteSignel(url,id,msg);
        },
        deleteMulti:function (url) {
            return handlerDeleteMulti(url);
        },
        initDataTables:function (url, columns) {
            return handlerInitDataTables(url,columns);
        },
        showDetail:function (url) {
            handlerShowDetail(url);
        },
        /**
         * 初始化ZTree
         * @param url
         * @param autoParam
         * @param callback
         */
        initZTree:function (url,autoParam,callback) {
            handlerInitZTree(url,autoParam,callback);
        },

        /**
         * 初始化Dropzone
         * @param opts
         */
        initDropzone:function (opts) {
            handlerInitDropzone(opts);
        }

    }
}();

$(document).ready(function () {
    App.init();
});