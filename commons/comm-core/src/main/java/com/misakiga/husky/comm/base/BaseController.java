package com.misakiga.husky.comm.base;

import com.misakiga.husky.comm.business.BusinessStatus;
import com.misakiga.husky.comm.dto.ResponseResult;

/**
 * 基础控制类，结果返回功能
 * @author MISAKIGA
 * @date 2020-3-27
 */
public class BaseController {

    /**
     * 构建成功结果带信息
     * @return {@link ResponseResult}
     */
    public <T> ResponseResult<T> success(){
        return success(null);
    }
    /**
     * 构建成功结果带信息
     * @param msg 响应信息
     * @param code 响应状态码
     * @return {@link ResponseResult}
     */
    public <T> ResponseResult<T> success(Integer code,String msg){
        return success(code,msg,null);
    }
    /**
     * 构建成功结果带数据
     * @param code 响应状态码
     * @param data 响应数据
     * @return {@link ResponseResult}
     */
    public <T> ResponseResult<T> success(Integer code,T data){
        return success(code,null,data);
    }
    /**
     * 构建成功结果带信息和数据
     * @param code 响应状态码
     * @param msg 响应信息
     * @param data 响应数据
     * @param <T> 响应数据格式
     * @return {@link ResponseResult}
     */
    public <T> ResponseResult<T> success(Integer code, String msg, T data){
        return new ResponseResult<>(code,msg,data);
    }
    /**
     * 构建成功结果带信息
     * @param msg 响应信息
     * @return {@link ResponseResult}
     */
    public <T> ResponseResult<T> success(String msg){
        return success(msg,null);
    }

    /**
     * 构建成功结果带数据
     * @param data 响应数据
     * @return {@link ResponseResult}
     */
    public <T> ResponseResult<T> success(T data){
        return new ResponseResult<>(BusinessStatus.OK.getCode(),null,data);
    }

    /**
     * 构建成功结果带信息和数据
     * @param msg 返回信息
     * @param data 响应数据
     * @param <T> 响应数据格式
     * @return {@link ResponseResult}
     */
    public <T> ResponseResult<T> success(String msg, T data){
        return new ResponseResult<>(BusinessStatus.OK.getCode(),msg,data);
    }

    /**
     * 构建失败结果带信息
     * @param code 状态码
     * @param msg 信息
     * @return {@link ResponseResult}
     */
    public <T> ResponseResult<T> failure(Integer code,String msg){
        return failure(code,msg,null);
    }
    /**
     * 构建失败结果带数据
     * @param code 状态码
     * @param data 响应数据
     * @return {@link ResponseResult}
     */
    public <T> ResponseResult<T> failure(Integer code,T data){
        return failure(code,null,data);
    }
    /**
     * 构建失败结果带信息
     * @param code 状态码
     * @param msg 信息
     * @param data 响应数据
     * @return {@link ResponseResult}
     */
    public <T> ResponseResult<T> failure(Integer code,String msg, T data){
        return new ResponseResult<>(BusinessStatus.FAIL.getCode(),msg,data);
    }
    /**
     * 构建失败结果带信息
     * @return {@link ResponseResult}
     */
    public <T> ResponseResult<T> failure(){
        return failure(null);
    }
    /**
     * 构建失败结果带信息
     * @param msg 响应信息
     * @return {@link ResponseResult}
     */
    public <T> ResponseResult<T> failure(String msg){
        return failure(msg,null);
    }
    /**
     * 构建失败结果带数据
     * @param data 响应数据
     * @return 返回
     */
    public <T> ResponseResult<T> failure(T data){
        return new ResponseResult<>(BusinessStatus.FAIL.getCode(),null,data);
    }

    /**
     * 构建失败结果带信息和数据
     * @param msg 返回信息
     * @param data 响应数据
     * @param <T> 响应数据格式
     * @return 一个指定数据格式的响应信息
     */
    public <T> ResponseResult<T> failure(String msg,T data){
        return new ResponseResult<>(BusinessStatus.FAIL.getCode(),msg,data);
    }
}
