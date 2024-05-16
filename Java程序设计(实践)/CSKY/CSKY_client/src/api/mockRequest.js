//该文档用于axios二次封装
import NProgress from 'nprogress' // 引入nprogress插件
import 'nprogress/nprogress.css'  // 这个nprogress样式必须引入
import axios from "axios"

//1.利用axios对象的方法create插件一个axios对象实例
//2.requests其实就是axios
const requests = axios.create({
    //基础路径：发送请求时，路径中会出现api
    baseURL: '/mock',
    //超时事件
    timeout: 5000

})
//请求拦截器
requests.interceptors.request.use(config => {
    //配置对象,在发送请求之前做些什么
    //进度条开始动
    NProgress.start()
    NProgress.set(0.0);     // Sorta same as .start()
    NProgress.set(0.4);
    NProgress.set(1.0);     // Sorta same as .done()
    return config
}, error => {
    this.$message.error(error.messages)
})
//响应拦截器
requests.interceptors.response.use((response) => {
    // 2xx 范围内的状态码都会触发该函数。
    // 对响应数据做点什么
    //进度条结束
    NProgress.done()
    return response
}, error => {
    this.$message.error(error.messages)
})

//暴露axios
export default requests
