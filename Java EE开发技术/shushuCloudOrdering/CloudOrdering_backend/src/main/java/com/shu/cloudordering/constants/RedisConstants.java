package com.shu.cloudordering.constants;

public class RedisConstants {
    public static final String LOGIN_CODE_KEY = "login:code:";
    public static final Long LOGIN_CODE_TTL = 15L;
    public static final String LOGIN_USER_KEY = "login:token:";
    public static final Long LOGIN_USER_TTL = 7L;

    public static final Long CACHE_NULL_TTL = 10L;

    public static final Long CACHE_SHOP_TTL = 30L;
    public static final String CACHE_SHOP_KEY = "cache:shop:";

    public static final Long CACHE_ORDER_BYUID_TTL = 30L;
    public static final String CACHE_ORDER_BYUID_KEY = "cache:order_byuid:";

    public static final Long CACHE_VENDOR_DETAIL_TTL = 30L;
    public static final String CACHE_VENDOR_DETAIL_KEY = "cache:vendor_detail:";

    public static final Long CACHE_VENDOR_LIST_TTL = 30L;
    public static final String CACHE_VENDOR_LIST_KEY = "cache:vendor_list";

    public static final String LOCK_SHOP_KEY = "lock:shop:";
    public static final Long LOCK_SHOP_TTL = 10L;

    public static final String SECKILL_STOCK_KEY = "seckill:stock:";

    public static final String SHOP_TYPE_LIST_KEY = "shop_type_list";
}
