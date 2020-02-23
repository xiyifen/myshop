package com.xiyifen.myshop.common.result;

public class ResultCode {

    public static final int OK=200;                     // 请求成功
    public static final int CREATED=201; //创建成功
    public static final int DELETED=204; // 删除成功
    public static final int BAD_REQUEST=400;  // 请求的地址不存在或者包含不支持的参数
    public static final int UNAUTHORIZED=401; //未授权
    public static final int FORBIDDEN=403;  //被禁止访问
    public static final int NOT_FOUND=404;  // 请求的资源不存在
    public static final int UNPROCESABLE_ENTITY=422;  // [POST/PUT/PATCH] 当创建一个对象时，发生一个验证错误
    public static final int INTERNAL_SERVER_ERROR=500;  // 内部错误



}
