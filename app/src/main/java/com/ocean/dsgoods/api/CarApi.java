package com.ocean.dsgoods.api;

import com.ocean.dsgoods.entity.AddInitOne;
import com.ocean.dsgoods.entity.AddInitTwo;
import com.ocean.dsgoods.entity.AlreadyAddGoods;
import com.ocean.dsgoods.entity.ApiResponse;
import com.ocean.dsgoods.entity.Area;
import com.ocean.dsgoods.entity.BillDetailsData;
import com.ocean.dsgoods.entity.BillList;
import com.ocean.dsgoods.entity.BindWillList;
import com.ocean.dsgoods.entity.CompanyInfo;
import com.ocean.dsgoods.entity.ContractData;
import com.ocean.dsgoods.entity.ContractDetails;
import com.ocean.dsgoods.entity.ContractList;
import com.ocean.dsgoods.entity.GoodList;
import com.ocean.dsgoods.entity.HomeData;
import com.ocean.dsgoods.entity.HomeSearchData;
import com.ocean.dsgoods.entity.InfoInit;
import com.ocean.dsgoods.entity.LoadingListData;
import com.ocean.dsgoods.entity.LoginResult;
import com.ocean.dsgoods.entity.ManualBindData;
import com.ocean.dsgoods.entity.OperateTrackData;
import com.ocean.dsgoods.entity.PickupData;
import com.ocean.dsgoods.entity.QuotationData;
import com.ocean.dsgoods.entity.RegisterResult;
import com.ocean.dsgoods.entity.ScanGoodInfo;
import com.ocean.dsgoods.entity.ScanResult;
import com.ocean.dsgoods.entity.ScanUpdata;
import com.ocean.dsgoods.entity.SettingResult;
import com.ocean.dsgoods.entity.ShppedDetailsData;
import com.ocean.dsgoods.entity.ShppedQuotationData;
import com.ocean.dsgoods.entity.SignName;
import com.ocean.dsgoods.entity.StaffAddInit;
import com.ocean.dsgoods.entity.StaffList;
import com.ocean.dsgoods.entity.Address;
import com.ocean.dsgoods.entity.SupplierList;
import com.ocean.dsgoods.entity.Track;
import com.ocean.dsgoods.entity.Trail;
import com.ocean.dsgoods.entity.TransportationType;
import com.ocean.dsgoods.entity.TurnoverInfo;
import com.ocean.dsgoods.entity.UpLoadResult;
import com.ocean.dsgoods.entity.WayList;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;

/**
 * Created by James on 2020/9/4.
 */

public interface CarApi {

    String Content_Type = "translate?doctype=json&jsonversion=&type=&keyfrom=&model=&mid=&imei=&vendor=&screen=&ssid=&network=&abtest=";

    /**
     * 发送验证码
     *
     * @param phone
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> sendSMS(@Field("phone") String phone);

    /**
     * 注册
     *
     * @return
     */
    @POST(Content_Type)
    @Multipart
    Call<ApiResponse<RegisterResult>> register(@PartMap Map<String, RequestBody> map, @Part MultipartBody.Part part);

    /**
     * 注册//未上传营业执照
     *
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<RegisterResult>> register(@Field("company_name") String company_name,
                                               @Field("phone") String phone,
                                               @Field("code") String code,
                                               @Field("password") String password);

    /**
     * 密码登录
     *
     * @param phone
     * @param password
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<LoginResult>> userLogin(@Field("company_no") String company_no, @Field("phone") String phone, @Field("password") String password);

    /**
     * 忘记密码
     *
     * @param phone
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> passwordForget(@Field("company_no") String company_no, @Field("phone") String phone, @Field("code") String code, @Field("password") String password);

    /**
     * 设置页面信息获取（包含个人资料）
     *
     * @param token
     * @return
     */
    @GET(Content_Type)
    Call<ApiResponse<SettingResult>> settinInfo(@Header("token") String token);

    /**
     * 个人资料修改初始化
     *
     * @param token
     * @return
     */
    @GET(Content_Type)
    Call<ApiResponse<InfoInit>> getInfoInit(@Header("token") String token);

    /**
     * 个人资料保存
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> infoSave(@Header("token") String token, @Field("sw_name") String sw_name,
                               @Field("sw_sex") String sw_sex, @Field("sw_department") String sw_department,
                               @Field("sw_position") String sw_position);

    /**
     * 企业信息保存
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> saveCompany(@Header("token") String token, @Field("mobile") String mobile, @Field("name") String name,
                                  @Field("taxNum") String taxNum, @Field("province") String province,
                                  @Field("city") String city, @Field("town") String town,
                                  @Field("addressDetail") String addressDetail,
                                  @Field("bank") String bank, @Field("bankNum") String bankNum,
                                  @Field("license") String license);

    /**
     * 修改头像
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @Multipart
    Call<ApiResponse> changeImage(@Header("token") String token, @Part MultipartBody.Part part);

    /**
     * 原密码验证
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> confirmPassword(@Header("token") String token, @Field("old_password") String password
    );

    /**
     * 新密码修改
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> changePassword(@Header("token") String token, @Field("new_password") String password
    );

    /**
     * 发送验证码到新手机号
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> sendSmsNew(@Header("token") String token, @Field("phone") String phone
    );

    /**
     * 发送邮箱验证码
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> sendEmailCode(@Header("token") String token, @Field("email") String email
    );

    /**
     * 修改邮箱
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> saveEmail(@Header("token") String token, @Field("email") String email, @Field("code") String code
    );

    /**
     * 新手机号修改
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> changePhone(@Header("token") String token, @Field("phone") String phone, @Field("code") String code
    );

    /**
     * 企业信息查看
     *
     * @param token
     * @return
     */
    @GET(Content_Type)
    Call<ApiResponse<CompanyInfo>> getCompanyInfo(@Header("token") String token
    );

    /**
     * 获取地址
     *
     * @param token
     * @return
     */
    @GET(Content_Type)
    Call<Area> getArea(@Header("token") String token
    );


    /**
     * 上传图片
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @Multipart
    Call<ApiResponse<UpLoadResult>> upLoadFile(@Header("token") String token, @Part MultipartBody.Part part);

    /**
     * 员工列表
     *
     * @param token
     * @return
     */
    @GET(Content_Type)
    Call<ApiResponse<StaffList>> staffList(@Header("token") String token
    );

    /**
     * 删除员工
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> deleteStaff(@Header("token") String token, @Field("ids") String ids
    );

    /**
     * 员工添加初始化
     *
     * @param token
     * @return
     */
    @GET(Content_Type)
    Call<StaffAddInit> staffAddInit(@Header("token") String token
    );

    /**
     * 员工添加
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> staffAdd(@Header("token") String token, @Field("name") String name
            , @Field("phone") String phone, @Field("email") String email, @Field("password") String password, @Field("sex") String sex
            , @Field("department") String department, @Field("position") String position, @Field("remarks") String remarks, @Field("auth") String auth
    );

    /**
     * 合同列表
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<ContractList>> contractList(@Header("token") String token, @Field("status") String status, @Field("page") String page
    );

    /**
     * 合同详情
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<ContractDetails>> contractDetails(@Header("token") String token, @Field("co_id") String coid
    );

    /**
     * 驳回合同
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> contractReject(@Header("token") String token, @Field("constract_sn") String constract_sn, @Field("reject_remarks") String reject_remarks
    );

    /**
     * 通过合同
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> constractSure(@Header("token") String token, @Field("constract_sn") String constract_sn
    );

    /**
     * 驳回提单
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> BillReject(@Header("token") String token, @Field("dp_id") String dp_id, @Field("reject") String reject_remarks
    );

    /**
     * 作废提单
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> toVoidReject(@Header("token") String token, @Field("dp_id") String dp_id, @Field("invalid_reason") String invalid_reason
    );

    /**
     * 提单列表
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<BillList>> take_list(@Header("token") String token, @Field("page") String page, @Field("type") String type, @Field("status") String status
    );

    /**
     * 提单列表
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<BillList>> take_list(@Header("token") String token, @Field("page") String page, @Field("type") String type
    );

    /**
     * 提单删除
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> orderDelete(@Header("token") String token, @Field("dp_id") String dp_id
    );

    /**
     * 提单通过
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> orderSure(@Header("token") String token, @Field("dp_id") String dp_id
    );

    /**
     * 提单详情
     *
     * @param token
     * @return
     */
    @GET(Content_Type)
    Call<ApiResponse<BillDetailsData>> orderDetails(@Header("token") String token, @Query("dp_id") String dp_id
    );

    /**
     * 提单详情（编辑）
     *
     * @param token
     * @return
     */
    @GET(Content_Type)
    Call<ApiResponse<BillDetailsData>> orderEditDetails(@Header("token") String token, @Query("dp_id") String dp_id
    );

    /**
     * 提货地址
     *
     * @param token
     * @return
     */
    @GET(Content_Type)
    Call<ApiResponse<Address>> tAddress(@Header("token") String token, @Query("t_id") String t_id, @Query("page") String page
    );

    /**
     * 提单详情（编辑）
     *
     * @param token
     * @return
     */
    @GET(Content_Type)
    Call<ApiResponse<SupplierList>> getSupplier(@Header("token") String token, @Query("page") String page
    );

    /**
     * 搜索供应商
     *
     * @param token
     * @return
     */
    @GET(Content_Type)
    Call<ApiResponse<SupplierList>> getSupplier(@Header("token") String token, @Query("page") String page, @Query("keyword") String keyword
    );

    /**
     * 选择合同
     *
     * @param token
     * @return
     */
    @GET(Content_Type)
    Call<ApiResponse<ContractData>> selectContract(@Header("token") String token, @Query("t_id") String t_id, @Query("page") String page
    );

    /**
     * 货物清单(编辑)
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<GoodList>> index_goods_list(@Header("token") String token, @Field("dp_id") String dp_id, @Field("page") String page, @Field("id") String id
    );

    /**
     * 运输要求配置数据
     *
     * @param token
     * @return
     */
    @GET(Content_Type)
    Call<TransportationType> trConfig(@Header("token") String token
    );

    /**
     * 编辑提货计划
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> operateEdit(@Header("token") String token, @Field("dp_id") String dp_id, @Field("co_id") String co_id,
                                  @Field("w_id") String w_id, @Field("sa_id") String sa_id, @Field("startTime") String startTime,
                                  @Field("endTime") String endTime, @Field("arrivalTime") String arrivalTime, @Field("aorder") String aorder,
                                  @Field("ynum") String ynum, @Field("delivery") String delivery, @Field("tsTime") String tsTime,
                                  @Field("transport") String transport, @Field("needCar") String needCar, @Field("settleSty") String settleSty,
                                  @Field("allWeight") String allWeight, @Field("allVolume") String allVolume, @Field("fileInfo") String fileInfo,
                                  @Field("remarks") String remarks, @Field("price") String price, @Field("goodsList") String goodsList
    );

    /**
     * 添加
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> operateAdd(@Header("token") String token, @Field("t_id") String t_id, @Field("co_id") String co_id,
                                 @Field("w_id") String w_id, @Field("sa_id") String sa_id, @Field("startTime") String startTime,
                                 @Field("endTime") String endTime, @Field("arrivalTime") String arrivalTime, @Field("aorder") String aorder,
                                 @Field("ynum") String ynum, @Field("delivery") String delivery, @Field("tsTime") String tsTime,
                                 @Field("transport") String transport, @Field("needCar") String needCar, @Field("settleSty") String settleSty,
                                 @Field("allWeight") String allWeight, @Field("allVolume") String allVolume, @Field("fileInfo") String fileInfo,
                                 @Field("remarks") String remarks, @Field("price") String price, @Field("goodsList") String goodsList
    );

    /**
     * 报价单
     *
     * @param token
     * @return
     */
    @GET(Content_Type)
    Call<ApiResponse<QuotationData>> quotation(@Header("token") String token, @Query("q_id") String q_id
    );

    /**
     * 新建运单-选择物流公司
     *
     * @param token
     * @return
     */
    @GET(Content_Type)
    Call<ApiResponse<AddInitOne>> addInitOne(@Header("token") String token, @Query("page") String page
    );

    /**
     * 提货车辆轨迹
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<OperateTrackData> operateTrack(@Header("token") String token, @Field("dp_id") String dp_id
    );

    /**
     * 装车清单
     *
     * @param token
     * @return
     */
    @GET(Content_Type)
    Call<ApiResponse<LoadingListData>> loadingGoodsList(@Header("token") String token, @Query("id") String id
    );

    /**
     * 装车驳回
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> affirmTake(@Header("token") String token, @Field("id") String id, @Field("type") String type, @Field("rejectReason") String rejectReason
    );

    /**
     * 新建运单-选择物流公司
     *
     * @param token
     * @return
     */
    @GET(Content_Type)
    Call<AddInitTwo> addInitTwo(@Header("token") String token, @Query("t_id") String t_id, @Query("plw_id") String plw_id
    );

    /**
     * 运单列表
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<WayList>> waybillList(@Header("token") String token, @Field("page") String page, @Field("type") String type, @Field("status") String status
    );

    /**
     * 通过运单
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> shipperConfirm(@Header("token") String token, @Field("wa_id") String wa_id
    );

    /**
     * 运单详情
     * @param token type1我发运的 2我收的
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<ShppedDetailsData>> waybillInfo(@Header("token") String token, @Field("type") String type, @Field("wa_id") String wa_id
    );

    /**
     * 运单货物列表
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<ShppedQuotationData>> waybillGoods(@Header("token") String token, @Field("type") String type, @Field("wa_id") String wa_id, @Field("page") String page
    );

    /**
     * 驳回运单
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> shipperReject(@Header("token") String token, @Field("wa_id") String wa_id, @Field("reject") String reject
    );

    /**
     * 回单确认
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> shipperAffirm(@Header("token") String token, @Field("wa_id") String wa_id, @Field("img") String img, @Field("time") String time
    );


    /**
     * 回单确认-上传图片
     *
     * @return
     */
    @POST(Content_Type)
    @Multipart
    Call<ApiResponse<UpLoadResult>> dispatchUploads(@Header("token") String token, @Header("type") String type, @Part MultipartBody.Part part);

    /**
     * 运单列表-我收的
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<PickupData>> pickupList(@Header("token") String token, @Field("page") String page, @Field("type") String type
    );

    /**
     * 运单添加包装-列表
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<BindWillList>> bindWillList(@Header("token") String token, @Field("page") String page
    );

    /**
     * 已添加货物列表
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<AlreadyAddGoods> wayBillBindGoodList(@Header("token") String token, @Field("page") String page, @Field("wa_id") String wa_id
    );

    /**
     * 已扫码货物清单
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<ScanGoodInfo>> scanGoodInfo(@Header("token") String token, @Field("g_id") String g_id, @Field("wa_id") String wa_id
    );

    /**
     * 扫码-周转箱
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ScanUpdata> scanRv(@Header("token") String token, @Field("wa_id") String wa_id,
                         @Field("origina_sn") String origina_sn,
                          @Field("type") String type
    );

    /**
     * 扫码-普通包装
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ScanUpdata> scanPk(@Header("token") String token, @Field("wa_id") String wa_id,
                          @Field("pk_num") String pk_num,
                          @Field("type") String type
    );

    /**
     * 已扫码货物清单-解除绑定
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> unbindGood(@Header("token") String token, @Field("num_sn") String num_sn,
                                 @Field("g_id") String g_id, @Field("wa_id") String wa_id
    );

    /**
     * 手工绑定列表
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<ManualBindData>> handBindList(@Header("token") String token, @Field("page") String page,
                                                   @Field("wa_id") String wa_id
    );

    /**
     * 点击选择包装详情
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<ScanGoodInfo>> handBindInfo(@Header("token") String token, @Field("g_id") String g_id, @Field("wa_id") String wa_id
    );

    /**
     * 手工绑定提交
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> handBindSave(@Header("token") String token, @Field("already_jnum") String already_jnum,
                                   @Field("already_num") String already_num, @Field("listmodel") String listmodel,
                                   @Field("jnum") String jnum, @Field("num") String num
    );

    /**
     * 首页
     *
     * @param token
     * @return
     */
    @GET(Content_Type)
    Call<ApiResponse<HomeData>> shipperHome(@Header("token") String token
    );

    /**
     * 首页查找
     *
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<HomeSearchData>> homeSearch(@Header("token") String token, @Field("wa_num") String wa_num
    );

    /**
     * 获取周转箱信息
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<TurnoverInfo>> enchaseRevolveGet(@Header("token") String token, @Field("rv_num") String rv_num, @Field("rv_id") String rv_id
    );

    /**
     * 周转箱解绑
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> revolveUnbind(@Header("token") String token, @Field("rv_num") String rv_num
    );

    /**
     * 箱解绑
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> packingUnbind(@Header("token") String token, @Field("pk_num") String pk_num, @Field("pk_id") String pk_id
    );


    /**
     * 周转箱装货
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> revolveGoodsSave(@Header("token") String token, @Field("rv_num") String rv_num,
                                       @Field("g_id") String g_id,  @Field("take_num") String take_num,
                                       @Field("num") String num
    );

    /**
     * 周转箱装货-编辑保存
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> revolveEditSave(@Header("token") String token, @Field("rv_num") String rv_num,  @Field("take_num") String take_num,
                                      @Field("num") String num
    );

    /**
     * 获取普通箱信息
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse<TurnoverInfo>> enchasePackingGet(@Header("token") String token, @Field("pk_num") String pk_num, @Field("pk_id") String pk_id
    );

    /**
     * 普通箱装货
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> packingGoodsSave(@Header("token") String token, @Field("pk_num") String pk_num,
                                       @Field("g_id") String g_id,  @Field("pk_id") String pk_id,
                                       @Field("num") String num
    );

    /**
     * 周转箱装货-编辑保存
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> packageEditSave(@Header("token") String token, @Field("pk_num") String pk_num,  @Field("pk_id") String pk_id,
                                      @Field("num") String num
    );


 /**
     * 运单地图轨迹
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<Track> mapTrailList(@Header("token") String token, @Field("wa_id") String wa_id, @Field("type") String type
    );

    /**
     * 运单轨迹
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<Trail> trailList(@Header("token") String token, @Field("wa_id") String wa_id, @Field("type") String type
    );

    /**
     * 获取签收人名称
     * @param token
     * @return
     */
    @GET(Content_Type)
    Call<SignName> signName(@Header("token") String token
    );

    /**
     * 运单签收
     * @param token
     * @return
     */
    @POST(Content_Type)
    @FormUrlEncoded
    Call<ApiResponse> shipperSign(@Header("token") String token, @Field("wa_id") String wa_id, @Field("img") String img, @Field("status") String status, @Field("remarks") String remarks
    );
}