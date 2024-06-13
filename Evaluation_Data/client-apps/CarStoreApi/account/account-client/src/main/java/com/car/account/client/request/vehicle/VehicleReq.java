package com.car.account.client.request.vehicle;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author linjiang.xie
 * @date 2020/12/19 18:31
 */
@Data
@ApiModel
public class VehicleReq {
    @ApiModelProperty(value = "数据ID",name = "uuid")
    private String uuid;

    @ApiModelProperty(value = "车牌号",name = "plateNumber")
    private String plateNumber;

    @ApiModelProperty(value = "行驶证照片地址",name = "licenseImageUrl")
    private String licenseImageUrl;

    @ApiModelProperty(value = "VIN编码",name = "vinCode")
    private String vinCode;

    @ApiModelProperty(value = "发动机型号",name = "engineType")
    private String engineType;

    @ApiModelProperty(value = "行驶证编号",name = "licenseNumber")
    private String licenseNumber;

    @ApiModelProperty(value = "行驶证登记日期",name = "licenseRegisterDate")
    private String licenseRegisterDate;

    @ApiModelProperty(value = "购买日期",name = "buyDate")
    private String buyDate;

    @ApiModelProperty(value = "车辆排放等级",name = "emissionLevels")
    private String emissionLevels;

    @ApiModelProperty(value = "排量",name = "displacement")
    private String displacement;

    @ApiModelProperty(value = "车辆类型",name = "vehicleType")
    private String vehicleType;

    @ApiModelProperty(value = "车辆品牌",name = "vehicleBrand")
    private String vehicleBrand;

    @ApiModelProperty(value = "车型",name = "vehicleModel")
    private String vehicleModel;


    @ApiModelProperty(value = "燃油类型",name = "fuelType")
    private String fuelType;


    @ApiModelProperty(value = "后处理系统",name = "afterTreatmentSystem")
    private String afterTreatmentSystem;
}
