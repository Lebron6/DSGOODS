package com.ocean.dsgoods.api;

/**
 * Created by Administrator on 2018/1/4.
 */

public class BaseUrl {

    private static BaseUrl baseUrl;

    public static BaseUrl getInstence() {
        if (baseUrl == null) {
            return new BaseUrl();
        }
        return baseUrl;
    }

//    public String ipAddress = "http://cu.idalc.com/";   //线上服务器
    public String ipAddress  = "http://cu.oceanscm.com/";   //测试服务器

    /**
     * 注册
     */
    public String getUserRegisterUrl() {
        return ipAddress + "/member/login/register/";
    }

    /**
     * 注册发送验证码
     */
    public String sendSMS() {
        return ipAddress + "/member/login/send_sms/";
    }

    /**
     * 密码登录
     */
    public String passwordLogin() {
        return ipAddress + "/member/login/login_password/";
    }

    /**
     * 忘记密码
     */
    public String passwordForget() {
        return ipAddress + "/member/login/password_forget/";
    }

    /**
     * 个人资料
     */
    public String getInfo() {
        return ipAddress + "/member/userapp/getInfo/";
    }

    /**
     * 个人资料修改初始化
     */
    public String getInfoInit() {
        return ipAddress + "/member/userapp/getInfoInit/";
    }

    /**
     * 个人资料保存
     */
    public String saveInfo() {
        return ipAddress + "/member/userapp/saveInfo/";
    }

    /**
     * 修改头像
     * @return
     */
    public String changeHeadimg() {
        return ipAddress + "/member/userapp/changeHeadimg/";
    }

    /**
     * 验证原密码
     */
    public String confirmPassword() {
        return ipAddress + "/member/userapp/confirmPassword/";
    }

    /**
     * 修改密码
     */
    public String savePassword() {
        return ipAddress + "/member/userapp/savePassword/";
    }

    /**
     * 发送邮箱验证码
     */
    public String sendMail() {
        return ipAddress + "/member/user/sendMail/";
    }

    /**
     * 修改邮箱
     */
    public String saveEmail() {
        return ipAddress + "/member/user/saveEmail/";
    }

    /**
     * 发送短信验证码
     */
    public String sendSms() {
        return ipAddress + "/member/user/sendSms/";
    }

    /**
     * 修改手机号
     */
    public String savePhone() {
        return ipAddress + "/member/user/savePhone/";
    }

    /**
     * 企业信息查看
     */
    public String getCompany() {
        return ipAddress + "/member/user/getCompany/";
    }

    /**
     * 企业信息保存
     */
    public String saveCompany() {
        return ipAddress + "/member/user/saveCompany/";
    }

    /**
     * 上传图片
     */
    public String uploadFile() {
        return ipAddress + "/member/uploads/uploadfile/";
    }

    /**
     * 获取地址
     */
    public String getArea() {
        return ipAddress + "/member/userapp/getArea/";
    }

    /**
     * 员工列表
     */
    public String staffIndex() {
        return ipAddress + "/member/user/staffIndex/";
    }

    /**
     * 员工添加初始化
     */
    public String initUser() {
        return ipAddress + "/member/user/initUser/";
    }

    /**
     * 员工添加
     */
    public String staffAdd() {
        return ipAddress + "/member/user/staffAdd/";
    }

    /**
     * 员工删除
     */
    public String staffDelete() {
        return ipAddress + "/member/userapp/staffDelete/";
    }

    /**
     * 员工修改初始化
     */
    public String staffEdit() {
        return ipAddress + "/member/user/staffEdit/";
    }

    /**
     * 员工修改
     */
    public String staffUpdate() {
        return ipAddress + "/member/user/staffUpdate/";
    }

    /**
     * 全部合同
     */
    public String constractCheckList() {
        return ipAddress + "/constract/constractt/constract_check_list/";
    }

    /**
     * 合同详情
     */
    public String constractCheckInfo() {
        return ipAddress + "/constract/constractt/constract_check_info/";
    }

    /**
     * 驳回合同
     */
    public String constractReject() {
        return ipAddress + "/constract/constractt/constract_reject/";
    }

    /**
     * 通过合同
     */
    public String constractSure() {
        return ipAddress + "/constract/constractt/constract_sure/";
    }

    /**
     * 提货地址
     */
    public String take_address() {
        return ipAddress + "//takeGoods/index/take_address/";
    }

    /**
     * 收货地址
     */
    public String shipping_address() {
        return ipAddress + "/takeGoods/index/shipping_address/";
    }

    /**
     * 供应商列表
     */
    public String supplier_list() {
        return ipAddress + "/takeGoods/index/supplier_list/";
    }

    /**
     * 合同列表
     */
    public String contract_list() {
        return ipAddress + "/takeGoods/index/contract_list/";
    }

    /**
     * 合同详情
     */
    public String contract_info() {
        return ipAddress + "/takeGoods/index/contract_info/";
    }

    /**
     * 报价单详情
     */
    public String quotation_info() {
        return ipAddress + "/takeGoods/index/quotation_info/";
    }

    /**
     * 货物清单(添加和编辑)
     */
    public String goods_list() {
        return ipAddress + "/takeGoods/index/goods_list/";
    }

    /**
     * 运输要求配置数据
     */
    public String config() {
        return ipAddress + "/takeGoods/index/config/";
    }

    /**
     * 添加提货计划
     */
    public String add_take() {
        return ipAddress + "/takeGoods/operate/add_take/";
    }

    /**
     * 提单列表
     */
    public String take_list() {
        return ipAddress + "/takeGoods/operate/take_list/";
    }

    /**
     * 货物清单
     */
    public String operate_goods_list() {
        return ipAddress + "/takeGoods/operate/goods_list/";
    }

    /**
     * 提货单作废
     */
    public String invalid_order() {
        return ipAddress + "/takeGoods/operate/invalid_order/";
    }

    /**
     * 提货单删除
     */
    public String del_order() {
        return ipAddress + "/takeGoods/operate/del_order/";
    }

    /**
     * 提货单通过
     */
    public String pass_order() {
        return ipAddress + "/takeGoods/operate/pass_order/";
    }

    /**
     * 提货单驳回
     */
    public String reject_order() {
        return ipAddress + "/takeGoods/operate/reject_order/";
    }

    /**
     * 提货单详情
     */
    public String take_info() {
        return ipAddress + "/takeGoods/operate/take_info/";
    }

    /**
     * 提货单详情(编辑)
     */
    public String edit_info() {
        return ipAddress + "/takeGoods/operate/edit_info/";
    }

    /**
     * 编辑提货计划
     */
    public String operate_edit() {
        return ipAddress + "/takeGoods/operate/edit/";
    }

    /**
     * 提货车辆轨迹
     */
    public String operate_track() {
        return ipAddress + "/takeGoods/operate/track/";
    }

    /**
     * 新建运单-选择物流公司
     */
    public String addInitOne() {
        return ipAddress + "/waybill/waybill/addInitOne/";
    }

    /**
     * 装车清单
     */
    public String loadingGoodsList() {
        return ipAddress + "/takeGoods/loading/goods_list_ios/";
    }

    /**
     * 装车驳回
     */
    public String affirmTake() {
        return ipAddress + "/takeGoods/loading/affirm_take/";
    }

    /**
     * 新建运单-选择提货单
     */
    public String addInitTwo() {
        return ipAddress + "/waybill/waybill/addInitTwo/";
    }

    /**
     * 我发运的-运单列表
     */
    public String waybillList() {
        return ipAddress + "/waybill/shipper/waybill_list/";
    }

    /**
     * 通过运单
     */
    public String shipperConfirm() {
        return ipAddress + "/waybill/shipper/confirm/";
    }

    /**
     * 运单详情
     */
    public String waybillInfo() {
        return ipAddress + "/waybill/shipper/waybill_info/";
    }

    /**
     * 运单货物列表
     */
    public String waybillGoods() {
        return ipAddress + "/waybill/shipper/waybill_goods/";
    }

    /**
     * 驳回运单
     */
    public String shipperReject() {
        return ipAddress + "/waybill/shipper/reject/";
    }

    /**
     * 回单确认
     */
    public String shipperAffirm() {
        return ipAddress + "/waybill/shipper/affirm/";
    }

    /**
     * 回单确认-上传图片
     */
    public String dispatchUploads() {
        return ipAddress + "/waybill/dispatch/uploads/";
    }

    /**
     * 运单列表-我收的
     */
    public String pickupList() {
        return ipAddress + "/waybill/shipper/pickup_list/";
    }

    /**
     * 运单添加包装-列表
     */
    public String bindWillList() {
        return ipAddress + "/waybill/waybillbind/bindwilllist/";
    }

    /**
     * 已添加货物列表
     */
    public String wayBillBindGoodList() {
        return ipAddress + "/waybill/waybillbind/good_list/";
    }

    /**
     * 已扫码货物清单
     */
    public String scanGoodInfo() {
        return ipAddress + "/waybill/waybillbind/scan_good_info/";
    }

    /**
     * 扫码
     */
    public String scanQr() {
        return ipAddress + "/waybill/waybillbind/scanqr/";
    }

    /**
     * 已扫码货物清单-解除绑定
     */
    public String unbindGood() {
        return ipAddress + "/waybill/waybillbind/unbind_good/";
    }

    /**
     * 手工绑定列表
     */
    public String handBindList() {
        return ipAddress + "/waybill/handbind/handbindlist/";
    }

    /**
     * 点击选择包装详情
     */
    public String handBindInfo() {
        return ipAddress + "/waybill/handbind/handbindinfo/";
    }

    /**
     * 手工绑定提交
     */
    public String handBindSave() {
        return ipAddress + "/waybill/handbind/handbindsave/";
    }

    /**
     * 首页
     */
    public String shipperHome() {
        return ipAddress + "/waybill/shipper/home/";
    }

    /**
     * 首页查找
     */
    public String homeSearch() {
        return ipAddress + "/waybill/shipper/home_search/";
    }

    /**
     * 获取周转箱信息
     */
    public String enchaseRevolveGet() {
        return ipAddress + "/packing/enchase/revolve_get/";
    }

    /**
     * 周转箱装货
     */
    public String revolveGoodsSave() {
        return ipAddress + "/packing/enchase/revolve_goods_save/";
    }

    /**
     * 周转箱装货-编辑保存
     */
    public String revolveEditSave() {
        return ipAddress + "/packing/enchase/revolve_edit_save/";
    }

    /**
     * 周转箱解除绑定
     */
    public String revolveUnbind() {
        return ipAddress + "/packing/enchase/revolve_unbind/";
    }

    /**
     * 获取普通箱信息
     */
    public String enchasePackingGet() {
        return ipAddress + "/packing/enchase/packing_get/";
    }

    /**
     * 普通包装装货
     */
    public String packingGoodsSave() {
        return ipAddress + "/packing/enchase/packing_goods_save/";
    }

    /**
     * 普通箱解除绑定
     */
    public String packingUnbind() {
        return ipAddress + "/packing/enchase/packing_unbind/";
    }

    /**
     * 普通箱装货-编辑保存
     */
    public String packingEditSave() {
        return ipAddress + "/packing/enchase/packing_edit_save/";
    }


    /**
     * 运单地图轨迹
     */
    public String mapTrailList() {
        return ipAddress + "/waybill/shipper/map_trail_list/";
    }

    /**
     * 运单轨迹
     */
    public String trailList() {
        return ipAddress + "/waybill/shipper/trail_list/";
    }

    /**
     * 获取签收人名称
     */
    public String getSignName() {
        return ipAddress + "/waybill/shipper/get_sign_name/";
    }

    /**
     * 运单签收
     */
    public String shipperSign() {
        return ipAddress + "//waybill/shipper/sign/";
    }
}
