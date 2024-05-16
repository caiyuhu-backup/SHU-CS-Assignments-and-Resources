// 这里的vm，就是我们在vue文件里面的this，所以我们能在这里获取vuex的变量，比如存放在里面的token变量
const install = (Vue, vm) => {
  // 此为自定义配置参数，具体参数见上方说明
  Vue.prototype.$u.http.setConfig({
    // baseUrl: 'https://api.itbear666.top/cloudorder',
    baseUrl: "http://localhost:8849/cloudorder",
    loadingText: "努力加载中~", // 请求loading中的文字提示
    // 设置为json，返回后会对数据进行一次JSON.parse()
    dataType: "json",
    showLoading: true, // 是否显示请求中的loading
    loadingTime: 800, // 在此时间内，请求还没回来的话，就显示加载中动画，单位ms
    originalData: false, // 是否在拦截器中返回服务端的原始数据
    loadingMask: true, // 展示loading的时候，是否给一个透明的蒙层，防止触摸穿透
    // 配置请求头信息
    header: {
      "content-type": "application/json;charset=UTF-8;",
    },
  });

  // 请求拦截，配置Token等参数
  Vue.prototype.$u.http.interceptor.request = (config) => {
    // 引用token
    // 方式一，存放在vuex的token，假设使用了uView封装的vuex方式
    config.header.TOKEN = vm.vuex_token;
    config.header["Access-Control-Allow-Origin"] = "*";
    // 最后需要将config进行return
    return config;
    // 如果return一个false值，则会取消本次请求
    // if(config.url == '/user/rest') return false // 取消某次请求
  };

  // 响应拦截，判断状态码是否通过
  Vue.prototype.$u.http.interceptor.response = (res) => {
    const { code, data, message } = res;
    if (code === 200) {
      return data;
    } else {
      uni.showToast({
        title: message,
        icon: "error",
      });
      setTimeout(() => {
        uni.hideToast();
      }, 800);
      return false;
    }
  };
};

export default {
  install,
};
