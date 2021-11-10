package com.example.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
public class Rule {
    private Integer id;

    @NotNull(message = "aid 不能为空")
    private Integer aid;

    @NotNull(message = "platform 不能为空")
    @NotBlank(message = "platform 不能为空")
    private String platform;

    @NotNull(message = "download_url 不能为空")
    @NotBlank(message = "download_url 不能为空")
    private String download_url;

    @NotNull(message = "update_version_code 不能为空")
    @NotBlank(message = "update_version_code 不能为空")
    private String update_version_code;

    @NotNull(message = "md5 不能为空")
    @NotBlank(message = "md5 不能为空")
    private String md5;

    private String device_id_list;

    @NotNull(message = "max_update_version_code 不能为空")
    @NotBlank(message = "max_update_version_code 不能为空")
    private String max_update_version_code;

    @NotNull(message = "min_update_version_code 不能为空")
    @NotBlank(message = "min_update_version_code 不能为空")
    private String min_update_version_code;

    private Integer max_os_api;

    private Integer min_os_api;

    @NotNull(message = "cpu_arch 不能为空")
    @NotBlank(message = "cpu_arch 不能为空")
    private String cpu_arch;

    @NotNull(message = "channel 不能为空")
    @NotBlank(message = "channel 不能为空")
    private String channel;

    @NotNull(message = "title 不能为空")
    @NotBlank(message = "title 不能为空")
    private String title;

    @NotNull(message = "update_tips 不能为空")
    @NotBlank(message = "update_tips 不能为空")
    private String update_tips;

    @NotNull(message = "status 不能为空")
    private Integer status;

    private Integer download_times;
}